package com.gst.mes.pmc;

import com.dcsplab.common.DLDateUtils;
import com.dcsplab.common.DLDateUtils.Resolution;
import com.dcsplab.vdui.UIConstants;
import com.dcsplab.vdui.components.VDComboBox;
import com.dcsplab.vdui.components.VDGrid;
import com.dcsplab.vdui.components.VDTextField;
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
import com.gst.framework.components.GSCommonViewFrame;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.framework.components.GSPMCDetailDialog;
import com.gst.framework.layout.GSMainLayout;
import com.gst.framework.util.GSDateStringConverter;
import com.gst.mes.pmc.GSPMCGridHelper.GSGridCellDiv;
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
 * 生產入庫審核作業
 */
@Route(value = "pmc/stockinreview", layout = GSMainLayout.class)
public class GSPMCManufactureOrderActivityReviewList extends GSCommonViewFrame
  implements GSFormChangeHandler, GSBroadcastListener {
  
  private static final long serialVersionUID = -7072562522597046779L;
  private static final Logger logger =
    LoggerFactory.getLogger(GSPMCManufactureOrderActivityReviewList.class);
  
  public static final String TITLE = "生產入庫審核作業";
  public static final String HANDLER_ID = "PMCD05";
  public static final String GRID_ID = "gst-pmc-stockinreview-grid";
  
  // 審核單狀態 - 作廢
  public static final String TYPE_OBSOLETE = "O";
  
  // 審核單狀態 - 已審核
  public static final String TYPE_REVIEWED = "Y";
  
  // 審核單狀態 - 未審核
  public static final String TYPE_NREVIEWED = "N";
  
  // 審核單狀態 - 人工結案
  public static final String TYPE_CLOSED = "C";
  
  // 審核單狀態 - 系統自動結案
  public static final String TYPE_CLOSED_BY_SYSTEM = "S";
  
  public static final String TA008DisplayColor = "#0000E3"; // 報工數量
  
  public static final String TA011DisplayColor = "#00A600"; // 報工包裝數量
  
  private final GSDateStringConverter dateStringConverter = new GSDateStringConverter();
  
  private VDGrid<GSPMCManufactureOrderActivity> grid;
  
  private Registration resizeListener;
  
  private final GSMember contextUser;
  
  private boolean hasSearchAccess = false;
  
  private boolean hasConfirmAccess = false;
  
  // 審核狀態
  private VDComboBox<String> reviewTypeFilter;
  
  // 製令單號
  private VDTextField PMCOrderFilter;
  
  private VDComboBox<GSDepartment> deptFilter;
  
  private boolean filterTriggeredByOrderText = false;
  
  // private boolean filterTriggeredByDeptText = false;
  
  @Autowired
  private GSPMCService PMCService;
  
  public GSPMCManufactureOrderActivityReviewList() {
    setTitleKey(TITLE);
    setId("gst-pmc-manufacturorder-review");
    
    contextUser = GSContext.getCurrentMember();
  }
  
  @PostConstruct
  private void init() {
    hasSearchAccess = contextUser.hasAccess(HANDLER_ID, Action.Search);
    hasConfirmAccess = contextUser.hasAccess(HANDLER_ID, Action.Confirm);
    
    if (hasSearchAccess) {
      setViewContent(createContent());
    } else {
      GSAccessDeniedScreen deniedScreen = new GSAccessDeniedScreen();
      setViewContent(deniedScreen);
    }
  }
  
  private Component createContent() {
    ArrayList<String> types = new ArrayList<>();
    types.add("未審核");
    types.add("已審核");
    types.add("作廢");
    types.add("結案");
    types.add("全部");
    reviewTypeFilter = new VDComboBox<>();
    reviewTypeFilter.setPlaceholder("選擇審核狀態");
    reviewTypeFilter.setItems(types);
    reviewTypeFilter.setRenderer(
      TemplateRenderer.<String>of("<div>[[item.val]]<br></div>")
        .withProperty("val", String::toString));
    reviewTypeFilter.setItemLabelGenerator(String::toString);
    reviewTypeFilter.setClearButtonVisible(true);
    
    reviewTypeFilter.addValueChangeListener(
      e -> {
        filterTriggeredByOrderText = false;
        refreshGrid(true);
      });
    reviewTypeFilter.setValue("未審核");
    
    List<GSDepartment> assignedDepts = contextUser.getAssignments();
    
    deptFilter = new VDComboBox<>();
    deptFilter.setPlaceholder("選擇線別");
    deptFilter.setItems(assignedDepts);
    deptFilter.setRenderer(
      TemplateRenderer.<GSDepartment>of("<div>[[item.name]]<br><small>[[item.id]]</small></div>")
        .withProperty("id", GSDepartment::getId)
        .withProperty("name", GSDepartment::getName));
    deptFilter.setItemLabelGenerator(GSDepartment::getName);
    deptFilter.setClearButtonVisible(true);
    deptFilter.addValueChangeListener(
      e -> {
        refreshGrid(true);
          /*if (e.getValue() != null) {
            filterTriggeredByDeptText = true;
          }
          refreshGrid(true);
          filterTriggeredByDeptText = false;*/
      });
    GSDepartment mainDept = contextUser.getMainDepartment();
    deptFilter.setValue(mainDept);
    
    PMCOrderFilter = new VDTextField();
    PMCOrderFilter.setPlaceholder("製令單號");
    PMCOrderFilter.setClearButtonVisible(true);
    PMCOrderFilter.addValueChangeListener(
      e -> {
        if (!e.getValue().trim().isEmpty()) {
          filterTriggeredByOrderText = true;
        }
        refreshGrid(true);
        filterTriggeredByOrderText = false;
      });
    
    HorizontalLayout actions = new VDHorizontalLayout();
    actions.add(reviewTypeFilter, deptFilter, PMCOrderFilter);
    
    createGrid();
    
    VerticalLayout gridLayout = new VDVerticalLayout();
    gridLayout.setSizeFull();
    gridLayout.add(grid);
    
    return new VDLayoutContainer(actions, gridLayout);
  }
  
  private void createGrid() {
    grid = new VDGrid<>();
    grid.setId(GRID_ID);
    grid.setClassNameGenerator(
      activity -> {
        GSPMCManufactureOrder m_order =
          PMCService.getManufactureOrder(
            activity.getCompanyId(),
            activity.getManufactureOrderTypeId(),
            activity.getManufactureOrderId());
        if (m_order.isUrgentReported()) {
          return "pmc-dashboard-mostatus-urgentreported";
        } else if (m_order.isUrgent()) {
          return "pmc-dashboard-mostatus-urgent";
        }
        return null;
      });
    
    grid.addComponentColumn(this::createReviewLink)
      .setResizable(false)
      .setAutoWidth(true)
      .setHeader("審核")
      .setFlexGrow(0)
      .setSortable(false);
    
    grid.addComponentColumn(this::createTA009Display)
      .setResizable(false)
      .setAutoWidth(true)
      .setHeader("審核狀態")
      .setFlexGrow(0)
      .setSortable(false);
    
    grid.addComponentColumn(this::createLineStatusDisplay)
      .setResizable(false)
      .setAutoWidth(true)
      .setHeader("製令狀態")
      .setFlexGrow(0)
      .setSortable(false);
    
    grid.addComponentColumn(this::createLineIDDisplay)
      .setResizable(false)
      .setAutoWidth(true)
      .setHeader("線別")
      .setFlexGrow(0)
      .setSortable(false);
    
    grid.addComponentColumn(this::createTA003Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("單別")
      .setSortable(false);
    
    grid.addComponentColumn(this::createTA004Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("單號")
      .setSortable(false);
    
    grid.addComponentColumn(this::createPartDisplay)
      .setResizable(true)
      .setAutoWidth(true)
      .setHeader("品號")
      .setFlexGrow(0)
      .setSortable(true);
    
    grid.addComponentColumn(this::createLineTA009Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("預計開工日")
      .setSortable(false);
    
    grid.addComponentColumn(this::createLineTA012Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("實際開工日")
      .setSortable(false);
    
    grid.addComponentColumn(this::createTA002Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("報工人員")
      .setSortable(false);
    
    grid.addComponentColumn(this::createTA005Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("開工時間")
      .setSortable(false);
    
    grid.addComponentColumn(this::createTA006Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("報工時間")
      .setSortable(false);
    
    grid.addComponentColumn(this::createTA008Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("報工數量")
      .setSortable(false);
    
    grid.addComponentColumn(this::createTA011Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("報工包裝數量")
      .setSortable(false);
    
    grid.addComponentColumn(this::createTA490Display)
      .setResizable(true)
      .setWidth("120px")
      .setFlexGrow(0)
      .setHeader("關聯單據")
      .setSortable(false);
    
    refreshGrid(true);
  }
  
  private GSGridCellDiv getCellComponent(GSPMCManufactureOrderActivity data) {
    GSPMCGridHelper.GSGridCellDiv display = new GSPMCGridHelper.GSGridCellDiv();
    
    GSPMCManufactureOrder m_order =
      PMCService.getManufactureOrder(
        data.getCompanyId(), data.getManufactureOrderTypeId(), data.getManufactureOrderId());
    
    if (m_order.isUrgentReported()) {
      display.addClassName("gst-grid-cell-urgentreported");
    } else if (m_order.isUrgent()) {
      display.addClassName("gst-grid-cell-urgent");
    } else {
      display.addClassName("gst-grid-cell-normal");
    }
    return display;
  }
  
  private Component createReviewLink(GSPMCManufactureOrderActivity data) {
    Icon icon = new Icon(VaadinIcon.CLIPBOARD_USER);
    icon.setSize("16px");
    
    if (hasConfirmAccess) {
      icon.setColor(UIConstants.DEFAULT_ICON_ENABLED_COLOR);
      icon.getStyle().set("cursor", "pointer");
      icon.addClickListener(e -> openReviewForm(data));
    } else {
      icon.setColor(UIConstants.DEFAULT_ICON_DISABLED_COLOR);
    }
    
    return icon;
  }
  
  private void openReviewForm(GSPMCManufactureOrderActivity data) {
    GSPMCManufactureOrderActivityReviewDetail detailForm =
      new GSPMCManufactureOrderActivityReviewDetail();
    detailForm.setViewMode(ViewMode.PMCReview);
    detailForm.setChangeHandler(this);
    detailForm.setDetail(data);
    
    GSPMCDetailDialog dialog = new GSPMCDetailDialog(detailForm);
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
  }
  
  private void refreshGrid(boolean reload) {
    if (grid == null) {
      return;
    }
    
    if (reload) {
      grid.setItems(getDataList());
    } else {
      grid.refresh();
    }
  }
  
  public void updateRow(GSPMCManufactureOrderActivity data) {
    refreshGrid(true);
    
    GSPMCManufactureOrder m_order =
      PMCService.getManufactureOrder(
        data.getCompanyId(), data.getManufactureOrderTypeId(), data.getManufactureOrderId());
    
    if (m_order != null) {
      logger.info("broadcasting production line change...");
      GSBroadcastMessage message = new GSBroadcastMessage();
      message.setContent(m_order);
      
      if (getUI().isPresent()) {
        GSPMCBroadcaster.broadcast(getUI().get(), message);
      }
    }
  }
  
  private Collection<GSPMCManufactureOrderActivity> getDataList() {
    List<GSPMCManufactureOrderActivity> visiblePMCItems = new ArrayList<>();
    if (contextUser == null) {
      return visiblePMCItems;
    }
    
    List<GSDepartment> assignedDepartments = contextUser.getAssignments();
    String type = reviewTypeFilter.getValue();
    String orderFilterText = PMCOrderFilter.getValue().trim();
    
    GSDepartment selectedDepartment = deptFilter.getValue();
    if (selectedDepartment != null) {
      logger.info("Dept[" + selectedDepartment.getId().trim() + "] selected.");
    }
    
    for (GSDepartment department : assignedDepartments) {
      String departmentId = department.getId().trim();
      if (selectedDepartment != null && !departmentId.equals(selectedDepartment.getId().trim())) {
        continue;
      }
      
      if (filterTriggeredByOrderText) {
        List<GSPMCManufactureOrderActivity> reportList =
          PMCService.getMOActivityReportsByProductLine(department, orderFilterText);
        visiblePMCItems.addAll(reportList);
      } else {
        if ("全部".equals(type)) {
          List<GSPMCManufactureOrderActivity> reportList =
            PMCService.getMOActivityReportsByProductLine(department);
          visiblePMCItems.addAll(reportList);
        } else if ("作廢".equals(type)) {
          List<GSPMCManufactureOrderActivity> reportList =
            PMCService.getMOActivityReportsByReviewStatus(department, TYPE_OBSOLETE);
          visiblePMCItems.addAll(reportList);
          
        } else if ("已審核".equals(type)) {
          List<GSPMCManufactureOrderActivity> reportList =
            PMCService.getMOActivityReportsByReviewStatus(department, TYPE_REVIEWED);
          visiblePMCItems.addAll(reportList);
        } else if ("未審核".equals(type)) {
          List<GSPMCManufactureOrderActivity> reportList =
            PMCService.getMOActivityReportsByReviewStatus(department, TYPE_NREVIEWED);
          visiblePMCItems.addAll(reportList);
        } else if ("結案".equals(type)) {
          List<GSPMCManufactureOrderActivity> reportList1 =
            PMCService.getMOActivityReportsByReviewStatus(department, TYPE_CLOSED);
          visiblePMCItems.addAll(reportList1);
          
          List<GSPMCManufactureOrderActivity> reportList2 =
            PMCService.getMOActivityReportsByReviewStatus(department, TYPE_CLOSED_BY_SYSTEM);
          visiblePMCItems.addAll(reportList2);
        }
      }
    }
    
    return visiblePMCItems;
  }
  
  private Component createLineStatusDisplay(GSPMCManufactureOrderActivity data) {
    GSPMCManufactureOrder m_order =
      PMCService.getManufactureOrder(
        data.getCompanyId(), data.getManufactureOrderTypeId(), data.getManufactureOrderId());
    
    GSGridCellDiv display = getCellComponent(data);
    String text = "未結案";
    if ("Y".equals(m_order.getStatusCode().trim())) {
      display.getStyle().set("color", "blue");
      text = "結案";
    }
    display.setText(text);
    
    return display;
  }
  
  private Component createLineTA009Display(GSPMCManufactureOrderActivity data) {
    GSGridCellDiv display = getCellComponent(data);
    
    GSPMCManufactureOrder m_order =
      PMCService.getManufactureOrder(
        data.getCompanyId(), data.getManufactureOrderTypeId(), data.getManufactureOrderId());
    
    String strTA009 =
      dateStringConverter.convertToPresentation(m_order.getEstimateStartDate(), null);
    display.setText(strTA009);
    return display;
  }
  
  private Component createLineTA012Display(GSPMCManufactureOrderActivity data) {
    GSGridCellDiv display = getCellComponent(data);
    
    GSPMCManufactureOrder m_order =
      PMCService.getManufactureOrder(
        data.getCompanyId(), data.getManufactureOrderTypeId(), data.getManufactureOrderId());
    
    String strTA012 = dateStringConverter.convertToPresentation(m_order.getActualStartDate(), null);
    display.setText(strTA012);
    
    return display;
  }
  
  /*
   * 線別
   */
  private Component createLineIDDisplay(GSPMCManufactureOrderActivity data) {
    GSGridCellDiv display = getCellComponent(data);
    display.setText(data.getDeptId());
    return display;
  }
  
  private Component createTA003Display(GSPMCManufactureOrderActivity data) {
    GSGridCellDiv display = getCellComponent(data);
    display.setText(data.getManufactureOrderTypeId());
    return display;
  }
  
  private Component createTA004Display(GSPMCManufactureOrderActivity data) {
    GSGridCellDiv display = getCellComponent(data);
    display.setText(data.getManufactureOrderId());
    return display;
  }
  
  /*
   * 品號
   */
  private Component createPartDisplay(GSPMCManufactureOrderActivity data) {
    GSGridCellDiv display = getCellComponent(data);
    
    GSPMCManufactureOrder m_order =
      PMCService.getManufactureOrder(
        data.getCompanyId(), data.getManufactureOrderTypeId(), data.getManufactureOrderId());
    
    if (!m_order.isUrgent()) {
      display.getStyle().set("color", TA008DisplayColor);
    }
    display.getStyle().set("font-weight", "bold");
    display.setText(m_order.getPartNumber());
    
    return display;
  }
  
  private Component createTA002Display(GSPMCManufactureOrderActivity data) {
    GSGridCellDiv display = getCellComponent(data);
    display.setText(data.getMemberId());
    return display;
  }
  
  private Component createTA008Display(GSPMCManufactureOrderActivity data) {
    GSGridCellDiv display = getCellComponent(data);
    
    GSPMCManufactureOrder m_order =
      PMCService.getManufactureOrder(
        data.getCompanyId(), data.getManufactureOrderTypeId(), data.getManufactureOrderId());
    
    display.setText("" + data.getWorkingAmount());
    
    if (!m_order.isUrgent()) {
      display.getStyle().set("color", TA008DisplayColor);
    }
    
    display.getStyle().set("font-weight", "bold");
    display.getStyle().set("font-size", "13px");
    display.getStyle().set("text-align", "right");
    display.getStyle().set("padding-right", "2px");
    
    return display;
  }
  
  private Component createTA011Display(GSPMCManufactureOrderActivity data) {
    GSGridCellDiv display = getCellComponent(data);
    
    GSPMCManufactureOrder m_order =
      PMCService.getManufactureOrder(
        data.getCompanyId(), data.getManufactureOrderTypeId(), data.getManufactureOrderId());
    if (!m_order.isUrgent()) {
      display.getStyle().set("color", TA011DisplayColor);
    }
    display.getStyle().set("font-weight", "bold");
    display.getStyle().set("font-size", "13px");
    display.getStyle().set("text-align", "right");
    display.getStyle().set("padding-right", "2px");
    
    display.setText("" + data.getPackAmount());
    
    return display;
  }
  
  /*
   * 審核狀態
   * Y: 已審核, N: 未審核
   */
  private Component createTA009Display(GSPMCManufactureOrderActivity data) {
    
    GSGridCellDiv display = getCellComponent(data);
    
    GSPMCManufactureOrder m_order =
      PMCService.getManufactureOrder(
        data.getCompanyId(), data.getManufactureOrderTypeId(), data.getManufactureOrderId());
    
    String color;
    boolean isOrderClosed = "Y".equals(m_order.getStatusCode());
    String reviewStatus = data.getReviewStatus();
    switch (reviewStatus) {
      case TYPE_REVIEWED:
        display.setText("已審核");
        color = "grey";
        break;
      case TYPE_NREVIEWED:
        display.setText("未審核");
        color = "#00A600";
        if (isOrderClosed) {
          color = "#D94600";
        }
        break;
      case TYPE_OBSOLETE:
        display.setText("作廢");
        color = "grey";
        break;
      case TYPE_CLOSED:
      case TYPE_CLOSED_BY_SYSTEM:
        display.setText("結案");
        color = "grey";
        break;
      default:
        display.setText("未知狀態");
        color = "red";
        break;
    }
    
    if (!m_order.isUrgent()) {
      display.getStyle().set("color", color);
    }
    
    return display;
  }
  
  private Component createTA005Display(GSPMCManufactureOrderActivity data) {
    GSGridCellDiv display = getCellComponent(data);
    
    Date startTime = data.getStartTime();
    if (startTime != null) {
      display.setText(DLDateUtils.format(startTime, Resolution.Minute));
    }
    
    return display;
  }
  
  private Component createTA006Display(GSPMCManufactureOrderActivity data) {
    GSGridCellDiv display = getCellComponent(data);
    
    Date finishTime = data.getFinishTime();
    if (finishTime != null) {
      display.setText(DLDateUtils.format(finishTime, Resolution.Minute));
    }
    
    return display;
  }
  
  private Component createTA490Display(GSPMCManufactureOrderActivity data) {
    GSGridCellDiv display = getCellComponent(data);
    
    String strTA490 = data.getRelatedManufactureOrder();
    display.setText(strTA490);
    
    return display;
  }
  
  @Override
  protected void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);
    
    getUI()
      .ifPresent(
        ui -> {
          if (hasSearchAccess) {
            UUID uuid = UUID.randomUUID();
            String UIID = "gst-mo-activity-review-" + uuid;
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
    
    // set AppBar title
    GSMainLayout ml = GSMainLayout.get();
    ml.getAppBar().setTitle(getTranslation(TITLE));
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
    refreshGrid(false);
  }
  
  @Override
  public String getHandlerID() {
    return HANDLER_ID;
  }
  
  @Override
  public void receiveBroadcast(final UI ui, GSBroadcastMessage message) {
    try {
      if (ui == null || ui.isClosing() || !ui.isVisible()) {
        return;
      }
      
      VaadinSession session = ui.getSession();
      if (session == null) {
        return;
      }
      
      ui.access(
        () -> {
          refreshGrid(true);
          Notification.show(
            UIConstants.PMCReviewNotification, 2000, Notification.Position.MIDDLE);
        });
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
  }
}
