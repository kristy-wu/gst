package com.gst.mes.pmc;

import com.dcsplab.vdui.UIConstants;
import com.dcsplab.vdui.components.VDButton;
import com.dcsplab.vdui.components.VDComboBox;
import com.dcsplab.vdui.components.VDGrid;
import com.dcsplab.vdui.layout.VDHorizontalLayout;
import com.dcsplab.vdui.layout.VDLayoutContainer;
import com.dcsplab.vdui.layout.VDVerticalLayout;
import com.gst.broadcast.GSBroadcastListener;
import com.gst.broadcast.GSBroadcastMessage;
import com.gst.broadcast.GSPMCBroadcaster;
import com.gst.context.GSContext;
import com.gst.domain.GSAuthorization.Action;
import com.gst.domain.GSDepartment;
import com.gst.domain.GSMember;
import com.gst.domain.GSPMCManufactureOrder;
import com.gst.framework.components.GSAccessDeniedScreen;
import com.gst.framework.components.GSCommonViewFrame;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.framework.layout.GSMainLayout;
import com.gst.service.GSPMCService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.*;

/*
 * 現場生產排程
 */
@Route(value = "pmc/scheduler", layout = GSMainLayout.class)
public class GSPMCProductLineScheduler extends GSCommonViewFrame
  implements GSFormChangeHandler, GSBroadcastListener {
  
  public static final String HANDLER_ID = "PMCD02";
  public static final String GRID_ID = "gst-productline-scheduler-grid";
  
  private static final Logger logger = LoggerFactory.getLogger(GSPMCProductLineScheduler.class);
  private static final long serialVersionUID = -8209546584609702634L;
  
  private final GSMember contextUser;
  
  private final byte[] _lock_ = new byte[0];
  
  private VDGrid<GSPMCManufactureOrder> grid;
  
  private Registration resizeListener;
  
  private boolean hasAccess = false;
  
  private VDComboBox<GSDepartment> deptFilter;
  
  @Autowired
  private GSPMCService PMCService;
  
  public GSPMCProductLineScheduler() {
    setTitleKey("現場生產排程");
    setId("get-pmc-scheduler");
    
    contextUser = GSContext.getCurrentMember();
  }
  
  @PostConstruct
  private void init() {
    hasAccess = contextUser.hasAccess(HANDLER_ID, Action.Search);
    if (hasAccess) {
      setViewContent(createContent());
    } else {
      GSAccessDeniedScreen deniedScreen = new GSAccessDeniedScreen();
      setViewContent(deniedScreen);
    }
  }
  
  private Component createContent() {
    VDButton refresh = new VDButton("重新整理");
    refresh.setIcon(VaadinIcon.REFRESH.create());
    refresh.addClickListener(e -> refreshGrid(true));
    
    deptFilter = new VDComboBox<>();
    deptFilter.setRenderer(
      TemplateRenderer.<GSDepartment>of("<div>[[item.name]]<br><small>[[item.id]]</small></div>")
        .withProperty("id", GSDepartment::getId)
        .withProperty("name", GSDepartment::getName));
    deptFilter.setItemLabelGenerator(GSDepartment::getName);
    deptFilter.setClearButtonVisible(true);
    
    deptFilter.addValueChangeListener(e -> refreshGrid(true));
    
    List<GSDepartment> depts = contextUser.getAssignments();
    deptFilter.setItems(depts);
    deptFilter.setValue(contextUser.getMainDepartment());
    
    HorizontalLayout actions = new VDHorizontalLayout();
    actions.add(refresh, deptFilter);
    
    createGrid();
    
    VerticalLayout gridLayout = new VDVerticalLayout();
    gridLayout.setSizeFull();
    gridLayout.add(grid);
    
    return new VDLayoutContainer(actions, gridLayout);
  }
  
  private Collection<GSPMCManufactureOrder> getDataList() {
    List<GSPMCManufactureOrder> visibleOrders = new ArrayList<>();
    if (contextUser == null) {
      return visibleOrders;
    }
    
    GSDepartment selectedDept = deptFilter.getValue();
    if (selectedDept != null) {
      List<GSPMCManufactureOrder> orderList = PMCService.getActiveManufactureOrders(selectedDept);
      visibleOrders.addAll(orderList);
    } else {
      List<GSDepartment> depts = contextUser.getAssignments();
      for (GSDepartment dept : depts) {
        List<GSPMCManufactureOrder> orderList = PMCService.getActiveManufactureOrders(dept);
        visibleOrders.addAll(orderList);
      }
    }
    
    return visibleOrders;
  }
  
  private void createGrid() {
    grid = new VDGrid<>();
    grid.setId(GRID_ID);
    grid.setClassNameGenerator(
      m_order -> {
        if (m_order.isUrgentReported()) {
          return "pmc-dashboard-mostatus-urgentreported";
        } else if (m_order.isUrgent()) {
          return "pmc-dashboard-mostatus-urgent";
        }
        return null;
      });
    
    grid.addComponentColumn(this::createReorderLink)
      .setResizable(false)
      .setAutoWidth(true)
      .setHeader("")
      .setFlexGrow(0)
      .setSortable(false)
      .setFrozen(true);
    
    grid.addComponentColumn(this::createTA021Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("生產單位")
      .setSortable(false)
      .setFrozen(true);
    
    grid.addComponentColumn(this::createTA006Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("產品品號")
      .setSortable(false)
      .setFrozen(true);
    
    // 顯示預計產量及單位
    grid.addComponentColumn(this::createTA015Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("預計產量")
      .setSortable(false)
      .setFrozen(true);
    
    // 備註
    grid.addComponentColumn(this::createTA029Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("備註")
      .setSortable(false)
      .setFrozen(true);
    
    // 顯示包裝數量及單位
    grid.addComponentColumn(this::createTA045Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("包裝數量")
      .setSortable(false);
    
    // 顯示已生產數量及單位
    grid.addComponentColumn(this::createTA017Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("已生產數量")
      .setSortable(false);
    
    grid.addComponentColumn(this::createMinusTA017Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("未審核數量")
      .setSortable(false);
    
    grid.addComponentColumn(this::createTA009Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("預計開工日")
      .setSortable(false);
    
    grid.addComponentColumn(this::createTA010Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("預計完工日")
      .setSortable(false);
    
    grid.addComponentColumn(this::createTA012Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("實際開工日")
      .setSortable(false);
    
    grid.addComponentColumn(this::createTA501Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("生產順序")
      .setSortable(false)
      .setKey("PMCOrder");
    
    grid.addComponentColumn(this::createTA502Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("前次加工時間")
      .setSortable(false);
    
    grid.addComponentColumn(this::createTA503Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("目前開工時間")
      .setSortable(false);
    
    grid.addComponentColumn(this::createTA092Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("目前生產狀態")
      .setSortable(false);
    
    grid.addComponentColumn(this::createTA001Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("單別")
      .setSortable(false);
    
    grid.addComponentColumn(this::createTA002Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("單號")
      .setSortable(false);
    
    refreshGrid(true);
  }
  
  private void refreshGrid(boolean reload) {
    if (grid == null) {
      return;
    }
    
    synchronized (_lock_) {
      if (reload) {
        grid.setItems(getDataList());
      } else {
        grid.refresh();
      }
    }
  }
  
  private Component createReorderLink(GSPMCManufactureOrder data) {
    Icon topIcon = new Icon(VaadinIcon.ANGLE_DOUBLE_UP);
    topIcon.setColor("#1ae917");
    topIcon.getStyle().set("cursor", "pointer");
    topIcon.setSize("16px");
    topIcon.addClickListener(e -> moveTop(data));
    
    Icon upIcon = new Icon(VaadinIcon.ANGLE_UP);
    upIcon.setColor("#1ae917");
    upIcon.getStyle().set("cursor", "pointer");
    upIcon.getStyle().set("margin-left", "3px");
    upIcon.setSize("16px");
    upIcon.addClickListener(e -> moveUp(data));
    
    Icon downIcon = new Icon(VaadinIcon.ANGLE_DOWN);
    downIcon.setColor("#e91727");
    downIcon.getStyle().set("cursor", "pointer");
    downIcon.getStyle().set("margin-left", "3px");
    downIcon.setSize("16px");
    downIcon.addClickListener(e -> moveDown(data));
    
    Icon bottomIcon = new Icon(VaadinIcon.ANGLE_DOUBLE_DOWN);
    bottomIcon.setColor("#e91727");
    bottomIcon.getStyle().set("cursor", "pointer");
    bottomIcon.getStyle().set("margin-left", "3px");
    bottomIcon.setSize("16px");
    bottomIcon.addClickListener(e -> moveBottom(data));
    
    HorizontalLayout action = new HorizontalLayout();
    action.add(topIcon, upIcon, downIcon, bottomIcon);
    
    return action;
  }
  
  private Component createTA001Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA001Display(data);
  }
  
  private Component createTA002Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA002Display(data);
  }
  
  private Component createTA021Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA021Display(data);
  }
  
  private Component createTA006Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA006Display(data);
  }
  
  private Component createTA009Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA009Display(data);
  }
  
  private Component createTA010Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA010Display(data);
  }
  
  private Component createTA012Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA012Display(data);
  }
  
  private Component createTA015Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA015Display(data);
  }
  
  private Component createTA017Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA017Display(data);
  }
  
  private Component createMinusTA017Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getMinusTA017Display(data);
  }
  
  private Component createTA029Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA029Display(data);
  }
  
  private Component createTA045Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA045Display(data);
  }
  
  private Component createTA501Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA501Display(data);
  }
  
  private Component createTA502Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA502Display(data);
  }
  
  private Component createTA503Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA503Display(data);
  }
  
  private Component createTA092Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA092Display(data);
  }
  
  private int findFirstIndexOfDept(String deptId) {
    int idx = -1;
    
    Collection<GSPMCManufactureOrder> items = grid.getItems();
    Iterator<GSPMCManufactureOrder> iter = items.iterator();
    int i = 0;
    while (iter.hasNext()) {
      GSPMCManufactureOrder item = iter.next();
      
      String productLine = item.getProductLine().trim();
      if (productLine.equals(deptId)) {
        idx = i;
        break;
      }
      
      i++;
    }
    
    return idx;
  }
  
  private int findLastIndexOfDept(String deptId) {
    int idx = -1;
    
    Collection<GSPMCManufactureOrder> items = grid.getItems();
    Iterator<GSPMCManufactureOrder> iter = items.iterator();
    int i = 0;
    while (iter.hasNext()) {
      GSPMCManufactureOrder item = iter.next();
      
      String productLine = item.getProductLine().trim();
      if (productLine.equals(deptId)) {
        idx = i;
      }
      
      i++;
    }
    
    return idx;
  }
  
  private void moveTop(GSPMCManufactureOrder data) {
    Double currentSequence = data.getWorkingSequence();
    if (currentSequence.intValue() == 1) {
      return;
    }
    
    synchronized (_lock_) {
      String deptId = data.getProductLine().trim();
      int start = findFirstIndexOfDept(deptId);
      int end = findLastIndexOfDept(deptId);
      
      ArrayList<GSPMCManufactureOrder> subList = grid.subset(start, end);
      Iterator<GSPMCManufactureOrder> iter = subList.iterator();
      double d = 2;
      while (iter.hasNext()) {
        GSPMCManufactureOrder item = iter.next();
        Double sequence = item.getWorkingSequence();
        if (sequence.intValue() == GSPMCProductLineControl.PMC_INCOMING_SEQUENCE.intValue()
          || sequence.intValue() == GSPMCProductLineControl.PMC_REPORTED_SEQUENCE.intValue()) {
          continue;
        }
        
        if (item.equals(data)) {
          continue;
        }
        
        item.setWorkingSequence(d++);
        updatePMCLineSequence(item);
      }
      
      data.setWorkingSequence(1d);
      updatePMCLineSequence(data);
    }
    
    refreshGrid(true);
    
    Object[] body = {data};
    notifyChange(body);
  }
  
  private void moveBottom(GSPMCManufactureOrder data) {
    Double currentSequence = data.getWorkingSequence();
    if (currentSequence.intValue() == GSPMCProductLineControl.PMC_INCOMING_SEQUENCE.intValue()
      || currentSequence.intValue() == GSPMCProductLineControl.PMC_REPORTED_SEQUENCE.intValue()) {
      return;
    }
    
    synchronized (_lock_) {
      String deptId = data.getProductLine().trim();
      int start = findFirstIndexOfDept(deptId);
      int end = findLastIndexOfDept(deptId);
      
      ArrayList<GSPMCManufactureOrder> subList = grid.subset(start, end);
      Iterator<GSPMCManufactureOrder> iter = subList.iterator();
      double d = 1;
      while (iter.hasNext()) {
        GSPMCManufactureOrder item = iter.next();
        String itemDeptId = item.getProductLine().trim();
        if (!deptId.equals(itemDeptId)) {
          continue;
        }
        
        if (item == data) {
          continue;
        }
        
        Double sequence = item.getWorkingSequence();
        if (sequence.intValue() == GSPMCProductLineControl.PMC_INCOMING_SEQUENCE.intValue()
          || sequence.intValue() == GSPMCProductLineControl.PMC_REPORTED_SEQUENCE.intValue()) {
          break;
        }
        
        item.setWorkingSequence(d++);
        updatePMCLineSequence(item);
      }
      
      data.setWorkingSequence(d);
      updatePMCLineSequence(data);
    }
    
    refreshGrid(true);
    
    Object[] body = {data};
    notifyChange(body);
  }
  
  private void moveUp(GSPMCManufactureOrder data) {
    Double currentSequence = data.getWorkingSequence();
    if (currentSequence.intValue() == 1) {
      return;
    }
    
    GSPMCManufactureOrder lastItem = null;
    synchronized (_lock_) {
      String deptId = data.getProductLine().trim();
      int start = findFirstIndexOfDept(deptId);
      int end = findLastIndexOfDept(deptId);
      
      ArrayList<GSPMCManufactureOrder> subList = grid.subset(start, end);
      Iterator<GSPMCManufactureOrder> iter = subList.iterator();
      int idx = -1;
      int i = start;
      while (iter.hasNext()) {
        GSPMCManufactureOrder item = iter.next();
        String itemDeptId = item.getProductLine().trim();
        if (!deptId.equals(itemDeptId)) {
          i++;
          continue;
        }
        
        if (item == data) {
          break;
        }
        
        Double sequence = item.getWorkingSequence();
        if (sequence.intValue() < GSPMCProductLineControl.PMC_INCOMING_SEQUENCE.intValue()
          && sequence.intValue() < data.getWorkingSequence().intValue()) {
          lastItem = item;
          idx = i;
        }
        
        i++;
      }
      
      if (lastItem == null) {
        data.setWorkingSequence(1.0d);
        idx = start;
      } else {
        Double target = lastItem.getWorkingSequence();
        if (currentSequence.intValue() == GSPMCProductLineControl.PMC_INCOMING_SEQUENCE.intValue()
          || currentSequence.intValue()
          == GSPMCProductLineControl.PMC_REPORTED_SEQUENCE.intValue()) {
          data.setWorkingSequence(target + 1);
          idx++;
        } else {
          data.setWorkingSequence(target);
          lastItem.setWorkingSequence(currentSequence);
        }
        updatePMCLineSequence(lastItem);
      }
      
      updatePMCLineSequence(data);
      
      Collection<GSPMCManufactureOrder> items = grid.getItems();
      ArrayList<GSPMCManufactureOrder> list = new ArrayList<>(items);
      list.remove(data);
      list.add(idx, data);
      
      items.clear();
      items.addAll(list);
      
      grid.refresh();
    }
    
    Object[] body = {data, lastItem};
    notifyChange(body);
  }
  
  private void moveDown(GSPMCManufactureOrder data) {
    Double currentSequence = data.getWorkingSequence();
    if (currentSequence.intValue() == GSPMCProductLineControl.PMC_INCOMING_SEQUENCE.intValue()
      || currentSequence.intValue() == GSPMCProductLineControl.PMC_REPORTED_SEQUENCE.intValue()) {
      return;
    }
    
    GSPMCManufactureOrder firstItem = null;
    synchronized (_lock_) {
      String deptId = data.getProductLine().trim();
      int start = findFirstIndexOfDept(deptId);
      int end = findLastIndexOfDept(deptId);
      
      ArrayList<GSPMCManufactureOrder> subList = grid.subset(start, end);
      Iterator<GSPMCManufactureOrder> iter = subList.iterator();
      int idx = -1;
      int i = start;
      while (iter.hasNext()) {
        GSPMCManufactureOrder item = iter.next();
        String itemDeptId = item.getProductLine().trim();
        if (!deptId.equals(itemDeptId)) {
          i++;
          continue;
        }
        
        if (item == data) {
          i++;
          continue;
        }
        
        Double sequence = item.getWorkingSequence();
        if (sequence.intValue() == GSPMCProductLineControl.PMC_INCOMING_SEQUENCE.intValue()
          || sequence.intValue() == GSPMCProductLineControl.PMC_REPORTED_SEQUENCE.intValue()) {
          firstItem = item;
          idx = i;
          break;
        }
        
        if (sequence.intValue() > data.getWorkingSequence().intValue()) {
          firstItem = item;
          idx = i;
          break;
        }
        
        i++;
      }
      
      if (firstItem == null) {
        return;
      }
      
      Double target = firstItem.getWorkingSequence();
      if (target.intValue() >= GSPMCProductLineControl.PMC_INCOMING_SEQUENCE.intValue()) {
        firstItem.setWorkingSequence(data.getWorkingSequence());
        data.setWorkingSequence(data.getWorkingSequence() + 1);
      } else {
        firstItem.setWorkingSequence(data.getWorkingSequence());
        data.setWorkingSequence(target);
      }
      
      updatePMCLineSequence(data);
      updatePMCLineSequence(firstItem);
      
      Collection<GSPMCManufactureOrder> items = grid.getItems();
      ArrayList<GSPMCManufactureOrder> list = new ArrayList<>(items);
      list.remove(data);
      list.add(idx, data);
      
      items.clear();
      items.addAll(list);
      
      grid.refresh();
    }
    
    Object[] body = {data, firstItem};
    notifyChange(body);
  }
  
  private void notifyChange(Object... data) {
    logger.info("broadcasting production line change...");
    getUI()
      .ifPresent(
        ui -> {
          GSBroadcastMessage message = new GSBroadcastMessage();
          message.setContent(data);
          GSPMCBroadcaster.broadcast(getUI().get(), message);
        });
  }
  
  private void updatePMCLineSequence(GSPMCManufactureOrder data) {
    PMCService.updateManufactureOrder(contextUser, data);
  }
  
  @Override
  protected void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);
    
    getUI()
      .ifPresent(
        ui -> {
          if (hasAccess) {
            UUID uuid = UUID.randomUUID();
            String UIID = "gst-productline-scheduler-" + uuid;
            ui.setId(UIID);
            
            Page page = ui.getPage();
            resizeListener =
              page.addBrowserWindowResizeListener(
                event -> updateVisibleColumns(event.getWidth()));
            page.retrieveExtendedClientDetails(
              details -> updateVisibleColumns(details.getBodyClientWidth()));
            
            GSPMCBroadcaster.register(ui, this);
          }
        });
  }
  
  @Override
  protected void onDetach(DetachEvent detachEvent) {
    if (hasAccess) {
      resizeListener.remove();
      resizeListener = null;
      
      GSPMCBroadcaster.unregister(detachEvent.getUI());
    }
    
    super.onDetach(detachEvent);
  }
  
  @Override
  public void onChange() {
  }
  
  @Override
  public String getHandlerID() {
    return HANDLER_ID;
  }
  
  @Override
  public void receiveBroadcast(final UI ui, GSBroadcastMessage message) {
    try {
      if (ui == null || ui.isClosing() || !ui.isVisible()) {
        logger.info("UI is null. Skip processing message broadcasting.");
        return;
      } else if (ui.isClosing() || !ui.isVisible()) {
        logger.info("UI is null. Skip processing message broadcasting.");
        return;
      }
      
      VaadinSession session = ui.getSession();
      if (session == null) {
        logger.info(
          "UI["
            + ui.getId()
            + "] does not connect to any session. Skip processing message broadcasting.");
        return;
      }
      
      ui.access(
        () -> {
          refreshGrid(true);
          Notification.show(
            UIConstants.PMCDataChangeNotification, 2000, Notification.Position.MIDDLE);
        });
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
  }
}
