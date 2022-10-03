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
import com.gst.domain.GSPMCManufactureOrderActivity;
import com.gst.framework.GSDetailFormBase.ViewMode;
import com.gst.framework.components.GSAccessDeniedScreen;
import com.gst.framework.components.GSCommonDetailDialog;
import com.gst.framework.components.GSCommonViewFrame;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.framework.layout.GSMainLayout;
import com.gst.mes.pmc.GSPMCProductLineControl.PMCLineStatus;
import com.gst.service.GSOrganizationService;
import com.gst.service.GSPMCService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
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
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.*;

/*
 * 現場生產入庫報工
 */
@Route(value = "pmc/stockin", layout = GSMainLayout.class)
public class GSPMCManufactureOrderActivityOverview extends GSCommonViewFrame
  implements GSFormChangeHandler, GSBroadcastListener {
  private static final long serialVersionUID = -1424874017037612589L;
  private static final Logger logger =
    LoggerFactory.getLogger(GSPMCManufactureOrderActivityOverview.class);
  
  public static final String HANDLER_ID = "PMCD03";
  public static final String INBOUND_REVIEW_ID = "PMCD05";
  
  public static final String GRID_ID = "gst-pmc-stockin-grid";
  
  private final byte[] _lock_ = new byte[0];
  
  private final GSMember contextUser;
  
  private VDGrid<GSPMCManufactureOrder> grid;
  
  @Autowired
  private GSOrganizationService orgService;
  
  @Autowired
  private GSPMCService PMCService;
  
  /*
   停工時間最小值(min), 超過此時間，開工時需填寫無效工時類別
   低於此時間則可直接開工
  */
  @Value("${gst.mes.pmc.suspend_timeout}")
  private String suspendTimeout;
  
  private Registration resizeListener;
  
  private boolean hasSearchAccess;
  
  private boolean hasModifyAccess;
  
  private boolean hasCreateInboundReviewAccess;
  
  private VDComboBox<GSDepartment> deptFilter;
  
  private GSPMCManufactureOrder current_m_order;
  
  public GSPMCManufactureOrderActivityOverview() {
    setTitleKey("現場生產入庫報工");
    setId("gst-pmc-stockin-report");
    contextUser = GSContext.getCurrentMember();
  }
  
  @PostConstruct
  private void init() {
    hasSearchAccess = contextUser.hasAccess(HANDLER_ID, Action.Search);
    hasModifyAccess = contextUser.hasAccess(HANDLER_ID, Action.Modify);
    hasCreateInboundReviewAccess = contextUser.hasAccess(INBOUND_REVIEW_ID, Action.Create);
    
    if (hasSearchAccess) {
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
    
    HorizontalLayout actionLayout = new VDHorizontalLayout();
    actionLayout.add(refresh, deptFilter);
    
    createGrid();
    
    VerticalLayout gridLayout = new VDVerticalLayout();
    gridLayout.setSizeFull();
    gridLayout.add(grid);
    
    return new VDLayoutContainer(actionLayout, gridLayout);
  }
  
  private Collection<GSPMCManufactureOrder> getDataList() {
    List<GSPMCManufactureOrder> orderList = new ArrayList<>();
    
    GSDepartment selectedProductline = deptFilter.getValue();
    if (selectedProductline != null) {
      orderList.addAll(PMCService.getActiveManufactureOrders(selectedProductline));
    } else {
      List<GSDepartment> assignedProductLines = contextUser.getAssignments();
      for (GSDepartment productLine : assignedProductLines) {
        orderList.addAll(PMCService.getActiveManufactureOrders(productLine));
      }
    }
    return orderList;
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
    
    grid.addComponentColumn(this::createKickoffLink)
      .setResizable(false)
      .setAutoWidth(true)
      .setHeader("開工")
      .setFlexGrow(0)
      .setSortable(false)
      .setFrozen(true);
    
    grid.addComponentColumn(this::createInboundReviewLink)
      .setResizable(false)
      .setAutoWidth(true)
      .setHeader("報工")
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
      .setSortable(true)
      .setFrozen(true)
      .setComparator(
        (item1, item2) -> {
          String prodName_1 = item1.getProductName().trim();
          String prodName_2 = item2.getProductName().trim();
          
          String orderId_1 = item1.getManufactureOrderId().trim();
          String orderId_2 = item2.getManufactureOrderId().trim();
          int a = prodName_1.compareTo(prodName_2);
          if (a == 0) {
            return orderId_1.compareTo(orderId_2);
          }
          return a;
        });
    
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
    
    // 顯示未審核數量
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
      .setSortable(false);
    
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
      .setSortable(true)
      .setComparator(
        (item1, item2) -> {
          String TA092_1;
          String TA092_2;
          
          // 前次加工時間
          Date dTA502_1 = item1.getLastStartTime();
          
          // 目前開工時間
          Date dTA503_1 = item1.getStartTime();
          if (dTA503_1 != null) {
            TA092_1 = "0"; // "生產中";
          } else {
            if (dTA502_1 != null) {
              TA092_1 = "1"; // "暫停";
            } else {
              TA092_1 = "2"; // "未生產";
            }
          }
          
          // 前次加工時間
          Date dTA502_2 = item2.getLastStartTime();
          
          // 目前開工時間
          Date dTA503_2 = item2.getStartTime();
          if (dTA503_2 != null) {
            TA092_2 = "0"; // "生產中";
          } else {
            if (dTA502_2 != null) {
              TA092_2 = "1"; // "暫停";
            } else {
              TA092_2 = "2"; // "未生產";
            }
          }
          
          String orderId_1 = item1.getManufactureOrderId().trim();
          String orderId_2 = item2.getManufactureOrderId().trim();
          int a = TA092_1.compareTo(TA092_2);
          if (a == 0) {
            return orderId_1.compareTo(orderId_2);
          }
          return a;
        });
    
    grid.addComponentColumn(this::createTA001Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("單別")
      .setSortable(true)
      .setComparator(
        (item1, item2) -> {
          String TA001_1 = item1.getManufactureOrderTypeId().trim();
          String TA001_2 = item2.getManufactureOrderTypeId().trim();
          
          String TA002_1 = item1.getManufactureOrderId().trim();
          String TA002_2 = item2.getManufactureOrderId().trim();
          
          int a = TA001_1.compareTo(TA001_2);
          if (a == 0) {
            return TA002_1.compareTo(TA002_2);
          }
          return a;
        });
    
    grid.addComponentColumn(this::createTA002Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("單號")
      .setSortable(true)
      .setComparator(
        (item1, item2) -> {
          String TA001_1 = item1.getManufactureOrderTypeId().trim();
          String TA001_2 = item2.getManufactureOrderTypeId().trim();
          
          String TA002_1 = item1.getManufactureOrderId().trim();
          String TA002_2 = item2.getManufactureOrderId().trim();
          
          int a = TA001_1.compareTo(TA001_2);
          if (a == 0) {
            return TA002_1.compareTo(TA002_2);
          }
          return a;
        });
    
    refreshGrid(true);
  }
  
  public Grid<GSPMCManufactureOrder> getGrid() {
    return grid;
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
  
  private Component createKickoffLink(GSPMCManufactureOrder data) {
    
    Icon icon = new Icon(VaadinIcon.USER);
    icon.setSize("16px");
    
    if (hasModifyAccess) {
      icon.getStyle().set("cursor", "pointer");
      
      if (data.getStartTime() != null) { // 已開工
        icon.setColor(UIConstants.DEFAULT_ICON_DISABLED_COLOR);
      } else {
        icon.setColor(UIConstants.DEFAULT_ICON_ENABLED_COLOR);
        icon.addClickListener(
          e -> {
            try {
              openPMCStartingForm(data);
            } catch (Exception ex) {
              ex.printStackTrace();
            }
          });
      }
    } else {
      icon.setColor(UIConstants.DEFAULT_ICON_DISABLED_COLOR);
    }
    return icon;
  }
  
  private Component createInboundReviewLink(GSPMCManufactureOrder data) {
    Icon icon = new Icon(VaadinIcon.STOCK);
    icon.setSize("16px");
    
    if (hasModifyAccess && hasCreateInboundReviewAccess) {
      if (data.getStartTime() != null) {
        icon.getStyle().set("cursor", "pointer");
        icon.setColor(UIConstants.DEFAULT_ICON_ENABLED_COLOR);
        icon.addClickListener(e -> openPMCClosingForm(data));
      } else {
        icon.setColor(UIConstants.DEFAULT_ICON_DISABLED_COLOR);
      }
    } else {
      icon.setColor(UIConstants.DEFAULT_ICON_DISABLED_COLOR);
    }
    
    return icon;
  }
  
  private void notifyChange(GSPMCManufactureOrder data) {
    logger.info("broadcasting manufacture order change...");
    
    GSPMCProductLineControl.resetProductLine(data.getProductLine().trim());
    GSBroadcastMessage message = new GSBroadcastMessage();
    message.setContent(data);
    GSPMCBroadcaster.broadcast(UI.getCurrent(), message);
  }
  
  public boolean kickoffCheck(GSPMCManufactureOrder data) {
    String lineId = data.getProductLine().trim();
    String orderId =
      data.getManufactureOrderTypeId().trim() + "-" + data.getManufactureOrderId().trim();
    
    PMCLineStatus lineStatus = GSPMCProductLineControl.addLock(lineId);
    if (lineStatus != PMCLineStatus.Locked) {
      refreshGrid(true);
      logger.info("[" + orderId + "] 無法開工 - [" + lineId + "] 存在已開工製令, 不可多重開工!");
      Notification.show("[" + lineId + "] 存在已開工製令, 不可多重開工!", 3000, Notification.Position.MIDDLE);
      return false;
    }
    
    return true;
  }

  /*public boolean __kickoff(GSPMCManufactureOrder data) {

    String productLineId = data.getProductLine().trim();
    String m_orderId =
        data.getManufactureOrderTypeId().trim() + "-" + data.getManufactureOrderId().trim();
    logger.info("[" + productLineId + ": " + m_orderId + "] 準備開工...");

    try {
      Calendar clndr = Calendar.getInstance();
      data.setStartTime(clndr.getTime());

      // 2021/06/10  若實際開工日TA012空白，則寫入目前日期
      String actualStartDate = data.getActualStartDate();
      if (actualStartDate == null || actualStartDate.trim().isEmpty()) {
        String today = DLDateUtils.format(clndr.getTime(), DLDateUtils.Resolution.SimpleDate);
        data.setActualStartDate(today);
      }
      PMCService.updateManufactureOrder(contextUser,data);
      grid.refreshItem(data);
      notifyChange(data);

      logger.info("[" + productLineId + ": " + m_orderId + "] 開工程序成功完成.");
      String msg = m_orderId + " 已開工.";
      Notification.show(msg, 2000, Notification.Position.MIDDLE);
    } catch (Exception e) {
      logger.error("GSMOCTA persisted object does not exist!", e);
      Notification.show(
          "GSMOCTA persisted object does not exist!", 3000, Notification.Position.MIDDLE);
      return false;
    }

    return true;
  }*/
  
  /*
    開工時判斷無效工時長度，若小於十分鐘，則直接開工
    MESTA 開工記錄-無效工時項目寫入 004
  */
  private void openPMCStartingForm(GSPMCManufactureOrder m_order) {
    GSPMCProductLineControl.refresh();
    
    current_m_order = m_order;
    
    GSDepartment productLine =
      orgService.getDepartment(m_order.getCompanyId(), m_order.getProductLine());
    Date reportTime = productLine.getLastFinishTime();
    Date now = Calendar.getInstance().getTime();
    if (reportTime == null) {
      reportTime = now;
    }
    
    int timeout = 10;
    try {
      timeout = Integer.parseInt(suspendTimeout);
    } catch (Exception e) {
      logger.error("gst.mes.pmc.suspend_timeout is not properly set.");
    }
    
    long milliTimeout = (long) timeout * 60 * 1000;
    long diff = now.getTime() - reportTime.getTime();
    if (diff <= milliTimeout) { // 小於十分鐘，則直接開工
      if (!kickoffCheck(m_order)) { // 檢查線別是否已開工
        return;
      }
      
      GSPMCManufactureOrderActivity m_activity =
        PMCService.createManufactureOrderActivity(contextUser, m_order);
      PMCService.updateManufactureOrderActivity(contextUser, m_activity);
      PMCService.kickoffManufactureOrder(contextUser, m_order);
      
      this.onChange();
      return;
    }
    
    GSPMCManufactureOrderActivityKickoffDetail detailForm =
      new GSPMCManufactureOrderActivityKickoffDetail();
    detailForm.setViewMode(ViewMode.ConfirmClose);
    detailForm.setChangeHandler(this);
    detailForm.setDetail(m_order);
    
    GSCommonDetailDialog dialog = new GSCommonDetailDialog(detailForm);
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
  }
  
  // 報工
  private void openPMCClosingForm(GSPMCManufactureOrder m_order) {
    current_m_order = m_order;
    
    GSPMCManufactureOrderActivityReportDetail detailForm =
      new GSPMCManufactureOrderActivityReportDetail();
    detailForm.setViewMode(ViewMode.ConfirmClose);
    detailForm.setChangeHandler(this);
    detailForm.setDetail(m_order);
    
    GSCommonDetailDialog dialog = new GSCommonDetailDialog(detailForm);
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
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
  
  public void orderReported(GSPMCManufactureOrder data) {
    // refreshGrid(true);
    grid.refreshItem(data);
    notifyChange(data);
  }
  
  @Override
  protected void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);
    
    getUI()
      .ifPresent(
        ui -> {
          if (hasSearchAccess) {
            UUID uuid = UUID.randomUUID();
            String UIID = "gst-mo-activity-overview-" + uuid;
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
    if (hasSearchAccess) {
      resizeListener.remove();
      resizeListener = null;
      
      GSPMCBroadcaster.unregister(detachEvent.getUI());
    }
    
    super.onDetach(detachEvent);
  }
  
  @Override
  public void onChange() {
    if (current_m_order != null) {
      grid.refreshItem(current_m_order);
      notifyChange(current_m_order);
    }
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
