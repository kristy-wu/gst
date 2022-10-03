package com.gst.mes.pmc;

import com.dcsplab.vdui.UIConstants;
import com.dcsplab.vdui.components.VDGrid;
import com.dcsplab.vdui.layout.VDLayoutContainer;
import com.dcsplab.vdui.layout.VDVerticalLayout;
import com.gst.broadcast.GSBroadcastListener;
import com.gst.broadcast.GSBroadcastMessage;
import com.gst.broadcast.GSPMCBroadcaster;
import com.gst.context.GSContext;
import com.gst.domain.GSAuthorization.Action;
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
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/*
 * 現場生產看板 PMCD01
 */
@Route(value = "pmc/dashboard", layout = GSMainLayout.class)
// @RouteAlias(value = "", layout = GSMainLayout.class)
public class GSPMCDashBoard extends GSCommonViewFrame
  implements GSFormChangeHandler, GSBroadcastListener {
  
  public static final String HANDLER_ID = "PMCD01";
  
  public static final String GRID_ID = "gst-pmc-dashboard-grid";
  public static final String TITLE = "gst.app.Navigator.WIP.DashBoard";
  
  private static final long serialVersionUID = 1033658661842481638L;
  private static final Logger logger = LoggerFactory.getLogger(GSPMCDashBoard.class);
  
  private final byte[] _lock_ = new byte[0];
  
  private final GSMember contextUser;
  
  private VDGrid<GSPMCManufactureOrder> grid;
  
  private Registration resizeListener;
  
  private boolean hasAccess = false;
  
  @Autowired
  private GSPMCService PMCService;
  
  public GSPMCDashBoard() {
    setTitleKey(TITLE);
    setId("gst-pmc-dashboard");
    contextUser = GSContext.getCurrentMember();
  }
  
  @PostConstruct
  private void init() {
    hasAccess = contextUser.hasAccess(HANDLER_ID, Action.Search);
    if (hasAccess) {
      createGrid();
      setViewContent(createContent());
    } else {
      GSAccessDeniedScreen deniedScreen = new GSAccessDeniedScreen();
      setViewContent(deniedScreen);
    }
  }
  
  private Component createContent() {
    VerticalLayout gridLayout = new VDVerticalLayout();
    gridLayout.setSizeFull();
    gridLayout.add(grid);
    
    return new VDLayoutContainer(gridLayout);
  }
  
  private Collection<GSPMCManufactureOrder> getDataList() {
    return PMCService.getActiveManufactureOrders(contextUser.getCompanyId());
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
    
    // grid.addComponentColumn(this::createTA021Display)
    grid.addColumn(GSPMCManufactureOrder::getProductLine)
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
          String productName_1 = item1.getProductName().trim();
          String productName_2 = item2.getProductName().trim();
          
          String m_orderId_1 = item1.getManufactureOrderId().trim();
          String m_orderId_2 = item2.getManufactureOrderId().trim();
          int a = productName_1.compareTo(productName_2);
          if (a == 0) {
            return m_orderId_1.compareTo(m_orderId_2);
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
    // grid.addComponentColumn(this::createTA029Display)
    grid.addColumn(GSPMCManufactureOrder::getRemark)
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
          String currentStatus_1;
          String currentStatus_2;
          
          // 前次加工時間
          Date dTA502_1 = item1.getLastStartTime();
          
          // 目前開工時間
          Date dTA503_1 = item1.getStartTime();
          if (dTA503_1 != null) {
            currentStatus_1 = "0"; // "生產中";
          } else {
            if (dTA502_1 != null) {
              currentStatus_1 = "1"; // "暫停";
            } else {
              currentStatus_1 = "2"; // "未生產";
            }
          }
          
          // 前次加工時間
          Date dTA502_2 = item2.getLastStartTime();
          
          // 目前開工時間
          Date dTA503_2 = item2.getStartTime();
          if (dTA503_2 != null) {
            currentStatus_2 = "0"; // "生產中";
          } else {
            if (dTA502_2 != null) {
              currentStatus_2 = "1"; // "暫停";
            } else {
              currentStatus_2 = "2"; // "未生產";
            }
          }
          
          String m_orderId_1 = item1.getManufactureOrderId().trim();
          String m_orderId_2 = item2.getManufactureOrderId().trim();
          int a = currentStatus_1.compareTo(currentStatus_2);
          if (a == 0) {
            return m_orderId_1.compareTo(m_orderId_2);
          }
          return a;
        });
    
    // grid.addComponentColumn(this::createTA001Display)
    grid.addColumn(GSPMCManufactureOrder::getManufactureOrderTypeId)
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
    
    // grid.addComponentColumn(this::createTA002Display)
    grid.addColumn(GSPMCManufactureOrder::getManufactureOrderId)
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
  
  private Component createTA029Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA029Display(data);
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
  
  @Override
  protected void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);
    
    getUI()
      .ifPresent(
        ui -> {
          if (hasAccess) {
            UUID uuid = UUID.randomUUID();
            String UIID = "gst-pmc-dashboard-" + uuid;
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
    if (hasAccess) {
      resizeListener.remove();
      resizeListener = null;
      GSPMCBroadcaster.unregister(detachEvent.getUI());
    }
    
    super.onDetach(detachEvent);
  }
  
  @Override
  public void onChange() {
    refreshGrid(true);
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
