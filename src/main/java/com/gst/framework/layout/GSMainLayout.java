package com.gst.framework.layout;

import com.dcsplab.vdui.layout.VDFlexBoxLayout;
import com.dcsplab.vdui.navigation.bar.AppBar;
import com.dcsplab.vdui.navigation.bar.TabBar;
import com.dcsplab.vdui.navigation.drawer.VDNavigationDrawer;
import com.dcsplab.vdui.navigation.drawer.VDNavigationItem;
import com.dcsplab.vdui.navigation.drawer.VDNavigationMenu;
import com.dcsplab.vdui.util.UIUtils;
import com.dcsplab.vdui.util.css.Overflow;
import com.gst.broadcast.GSBroadcastListener;
import com.gst.broadcast.GSBroadcastMessage;
import com.gst.broadcast.GSBulletinBroadcaster;
import com.gst.bulletin.GSBulletinBoardView;
import com.gst.bulletin.GSBulletinClassification;
import com.gst.bulletin.GSBulletinItemList;
import com.gst.context.GSContext;
import com.gst.domain.GSMember;
import com.gst.framework.Home;
import com.gst.framework.admin.*;
import com.gst.framework.org.GSDepartmentList;
import com.gst.framework.org.GSPersonnelList;
import com.gst.mes.pmc.*;
import com.gst.framework.admin.GSManuProcessManagement;
import com.gst.service.GSBulletinService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.ReconnectDialogConfiguration;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.*;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import static com.gst.GSTApplicationServlet.SERVER_RECONNECT_ALERT;

@Push
@CssImport(value = "./styles/styles.css", include = "lumo-badge")
@CssImport(value = "./styles/dcsplab-mainlayout.css")
@CssImport(value = "./styles/components/floating-action-button.css", themeFor = "vaadin-button")
@CssImport(value = "./styles/components/grid.css", themeFor = "vaadin-grid")
@CssImport("./styles/lumo/border-radius.css")
@CssImport("./styles/lumo/icon-size.css")
@CssImport("./styles/lumo/margin.css")
@CssImport("./styles/lumo/padding.css")
@CssImport("./styles/lumo/shadow.css")
@CssImport("./styles/lumo/spacing.css")
@CssImport("./styles/lumo/typography.css")
@CssImport("./styles/misc/box-shadow-borders.css")
@JsModule("@vaadin/vaadin-lumo-styles/badge")
@JsModule("@vaadin/vaadin-lumo-styles/presets/compact.js")
@CssImport(value = "./styles/dcsplab-grid.css", themeFor = "vaadin-grid")
@PWA(
    name = "GST MES",
    shortName = "GST MES",
    iconPath = "./images/favicons/favicon-Small.png",
    enableInstallPrompt = false)
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
public class GSMainLayout extends VDFlexBoxLayout
    implements RouterLayout, BootstrapListener, AfterNavigationObserver, GSBroadcastListener {

  private static final Logger logger = LoggerFactory.getLogger(GSMainLayout.class);

  private static final String CLASS_NAME = "root";

  private static final long serialVersionUID = -8870823009792425501L;

  // private Div appHeaderOuter;

  private final boolean navigationTabs = false;

  private VDNavigationDrawer naviDrawer;

  private VDFlexBoxLayout column;

  private Div appHeaderInner;
  // private Div appFooterInner;

  // private Div appFooterOuter;

  private VDFlexBoxLayout viewContainer;

  private TabBar tabBar;

  private AppBar appBar;

  public GSMainLayout() {
    ReconnectDialogConfiguration configuration = UI.getCurrent().getReconnectDialogConfiguration();
    configuration.setDialogText(SERVER_RECONNECT_ALERT);

    VaadinSession.getCurrent()
        .setErrorHandler(
            (ErrorHandler)
                errorEvent -> {
                  logger.error("Uncaught UI exception", errorEvent.getThrowable());
                  Notification.show("We are sorry, but an internal error occurred.");
                });

    addClassName(CLASS_NAME);
    setFlexDirection(FlexDirection.COLUMN);
    setSizeFull();

    // Initialise the UI building blocks
    initStructure();

    // Populate the navigation drawer
    initNaviItems();

    // Configure the headers and footers (optional)
    initHeadersAndFooters();
  }

  public static GSMainLayout get() {
    return (GSMainLayout)
        UI.getCurrent()
            .getChildren()
            .filter(component -> component.getClass() == GSMainLayout.class)
            .findFirst()
            .get();
  }

  @PostConstruct
  public void initContext() {

    GSMember member = GSContext.getCurrentMember();
    appBar.setUserName(member.getName());

    GSBulletinBroadcaster.register(UI.getCurrent(), this);

    GSBulletinService bulletinService = GSContext.getApplication().getBulletinService();
    GSBulletinBroadcaster.pushBulletin(bulletinService);
  }

  /**
   * Initialise the required components and containers.
   */
  private void initStructure() {
    naviDrawer = new VDNavigationDrawer();

    viewContainer = new VDFlexBoxLayout();
    viewContainer.addClassName(CLASS_NAME + "__view-container");
    viewContainer.setOverflow(Overflow.HIDDEN);

    column = new VDFlexBoxLayout(viewContainer);
    column.addClassName(CLASS_NAME + "__column");
    column.setFlexDirection(FlexDirection.COLUMN);
    column.setFlexGrow(1, viewContainer);
    column.setOverflow(Overflow.HIDDEN);

    VDFlexBoxLayout row = new VDFlexBoxLayout(naviDrawer, column);
    row.addClassName(CLASS_NAME + "__row");
    row.setFlexGrow(1, column);
    row.setOverflow(Overflow.HIDDEN);
    add(row);
    setFlexGrow(1, row);
  }

  /**
   * Initialise the navigation items.
   */
  private void initNaviItems() {
    VDNavigationMenu menu = naviDrawer.getMenu();

    // --{{{ System Management
    VDNavigationItem management =
        menu.addNaviItem(VaadinIcon.MEDAL, getTranslation("gst.app.Navigator.System.Title"), null);

    // 模組資料維護作業 ADMI01
    menu.addNaviItem(
        management, getTranslation("gst.app.Navigator.System.Module"), GSModuleList.class);

    // 程式資料維護作業 ADMI02
    menu.addNaviItem(
        management, getTranslation("gst.app.Navigator.System.Function"), GSFunctionList.class);

    // 角色資料維護作業 ADMI03
    menu.addNaviItem(management, getTranslation("gst.app.Navigator.System.Role"), GSRoleList.class);

    // 使用者程式權限設定 ADMI05
    menu.addNaviItem(
        management,
        getTranslation("gst.app.Navigator.System.FunctionUser"),
        GSMemberAccessList.class);

    // 模組指派角色 ADMB01
    menu.addNaviItem(
        management,
        getTranslation("gst.app.Navigator.System.ModuleRole"),
        GSModuleAssignment.class);

    // 角色指派使用者 ADMB02
    menu.addNaviItem(
        management, getTranslation("gst.app.Navigator.System.RoleUser"), GSRoleUser.class);

    // 工時類別建立作業 ADMI06
    menu.addNaviItem(
        management, getTranslation("gst.app.Navigator.System.WorkType"), GSWorkTypeList.class);

    //製程資料建立作業
    menu.addNaviItem(management, getTranslation("gst.app.Navigator.Proofing.ProcMan"),
        GSManuProcessManagement.class);
    // --}}}

    // --{{{ Organization
    VDNavigationItem organization =
        menu.addNaviItem(
            VaadinIcon.INSTITUTION, getTranslation("gst.app.Navigator.Org.Title"), null);

    // 公司基本資料建立 CMSI01
    /* menu.addNaviItem(
        organization, getTranslation("gst.app.Navigator.Org.Company"), GSCompanyList.class); */

    // 部門資料建立作業 CMSI02
    menu.addNaviItem(
        organization, getTranslation("gst.app.Navigator.Org.Department"), GSDepartmentList.class);

    // 使用者資料建立作業 CMSI03
    menu.addNaviItem(
        organization, getTranslation("gst.app.Navigator.Org.Personnel"), GSPersonnelList.class);
    // --}}}

    // --{{{ Bulletin Board
    VDNavigationItem bulletinboard =
        menu.addNaviItem(
            VaadinIcon.MODAL_LIST, getTranslation("gst.app.Navigator.BulletinBoard.Title"), null);

    // 系統公佈欄 CMSI07
    menu.addNaviItem(
        bulletinboard,
        getTranslation("gst.app.Navigator.BulletinBoard.Overview"),
        GSBulletinBoardView.class);

    // 公佈欄類別維護 CMSI05
    menu.addNaviItem(
        bulletinboard,
        getTranslation("gst.app.Navigator.BulletinBoard.Class"),
        GSBulletinClassification.class);

    // 公佈欄資料維護作業 CMSI06
    menu.addNaviItem(
        bulletinboard,
        getTranslation("gst.app.Navigator.BulletinBoard.Item"),
        GSBulletinItemList.class);
    // --}}}

    // --{{{ Work In Process
    VDNavigationItem wip =
        menu.addNaviItem(VaadinIcon.COGS, getTranslation("gst.app.Navigator.WIP.Title"), null);

    // 現場生產看板 PMCD01
    menu.addNaviItem(wip, getTranslation("gst.app.Navigator.WIP.DashBoard"), GSPMCDashBoard.class);

    // 現場生產排程 PMCD02
    menu.addNaviItem(
        wip, getTranslation("gst.app.Navigator.WIP.Schedule"), GSPMCProductLineScheduler.class);

    // 生產入庫報工 PMCD03
    menu.addNaviItem(
        wip, getTranslation("gst.app.Navigator.WIP.HubOrder"), GSPMCManufactureOrderActivityOverview.class);

    // 現場工時調整作業 PMCD04
    menu.addNaviItem(
        wip,
        getTranslation("gst.app.Navigator.WIP.WokHourManagement"),
        GSPMCWorkHourManagement.class);

    // 生產入庫審核作業 PMCD05
    menu.addNaviItem(
        wip, getTranslation("gst.app.Navigator.WIP.HubReview"), GSPMCManufactureOrderActivityReviewList.class);

    /*  2021/03/14 Dustin 已於其他系統實作，故隱藏
    //品保生產入庫報表 PMCD06
    menu.addNaviItem(wip, getTranslation("gst.app.Navigator.WIP.QAReport"),
    		GSPMCQAReport.class);

    //工時統計報表 PMCD07
    menu.addNaviItem(wip,
    		getTranslation("gst.app.Navigator.WIP.WorkHourReport"),
    		GSPMCWorkHourReport.class);

    //生管製令開立 PMCD08
    menu.addNaviItem(wip, getTranslation("gst.app.Navigator.WIP.WorkOrder"),
    		GSPMCWorkOrder.class);

     */

    // 製令維護作業 PMCD09
    /*
    menu.addNaviItem(wip,
    		getTranslation("gst.app.Navigator.WIP.PMCLineManagement"),
    		GSPMCProductLineManagement.class);
    */
    // --}}}

    // --{{{ Proofing Module, 打樣管理模組
    VDNavigationItem PAS =
        menu.addNaviItem(VaadinIcon.COMPILE, getTranslation("gst.app.Navigator.Proofing.Title"), null);
    // --}}}
  }

  /*
  private void setAppHeaderOuter(Component... components) {
  	if (appHeaderOuter == null) {
  		appHeaderOuter = new Div();
  		appHeaderOuter.addClassName("app-header-outer");
  		getElement().insertChild(0, appHeaderOuter.getElement());
  	}
  	appHeaderOuter.removeAll();
  	appHeaderOuter.add(components);
  }
  */

  /**
   * Configure the app's inner and outer headers and footers.
   */
  private void initHeadersAndFooters() {
    // setAppHeaderOuter();
    // setAppFooterInner();
    // setAppFooterOuter();

    // Default inner header setup:
    // - When using tabbed navigation the view title, user avatar and main
    // menu button will appear in the TabBar.
    // - When tabbed navigation is turned off they appear in the AppBar.

    appBar = new AppBar("User Panel", AppBar.APP_TYPE_USER);

    // Tabbed navigation
    if (navigationTabs) {
      tabBar = new TabBar();
      UIUtils.setTheme(Lumo.DARK, tabBar);

      // Shift-click to add a new tab
      for (VDNavigationItem item : naviDrawer.getMenu().getNaviItems()) {
        item.addClickListener(
            e -> {
              if (e.getButton() == 0 && e.isShiftKey()) {
                tabBar.setSelectedTab(
                    tabBar.addClosableTab(item.getText(), item.getNavigationTarget()));
              }
            });
      }
      appBar.getAvatar().setVisible(false);
      setAppHeaderInner(tabBar, appBar);

      // Default navigation
    } else {
      UIUtils.setTheme(Lumo.DARK, appBar);
      setAppHeaderInner(appBar);
    }
  }

  /*
  private void setAppFooterInner(Component... components) {
  	if (appFooterInner == null) {
  		appFooterInner = new Div();
  		appFooterInner.addClassName("app-footer-inner");
  		column.getElement().insertChild(column.getElement().getChildCount(),
  				appFooterInner.getElement());
  	}
  	appFooterInner.removeAll();
  	appFooterInner.add(components);
  }

  private void setAppFooterOuter(Component... components) {
  	if (appFooterOuter == null) {
  		appFooterOuter = new Div();
  		appFooterOuter.addClassName("app-footer-outer");
  		getElement().insertChild(getElement().getChildCount(),
  				appFooterOuter.getElement());
  	}
  	appFooterOuter.removeAll();
  	appFooterOuter.add(components);
  }
  */

  private void setAppHeaderInner(Component... components) {
    if (appHeaderInner == null) {
      appHeaderInner = new Div();
      appHeaderInner.addClassName("app-header-inner");
      column.getElement().insertChild(0, appHeaderInner.getElement());
    }
    appHeaderInner.removeAll();
    appHeaderInner.add(components);
  }

  @Override
  public void showRouterLayoutContent(HasElement content) {
    this.viewContainer.getElement().appendChild(content.getElement());
  }

  public VDNavigationDrawer getNaviDrawer() {
    return naviDrawer;
  }

  public AppBar getAppBar() {
    return appBar;
  }

  @Override
  public void afterNavigation(AfterNavigationEvent event) {
    if (navigationTabs) {
      afterNavigationWithTabs(event);
    } else {
      afterNavigationWithoutTabs(event);
    }
  }

  private void afterNavigationWithTabs(AfterNavigationEvent e) {
    VDNavigationItem active = getActiveItem(e);
    if (active == null) {
      if (tabBar.getTabCount() == 0) {
        tabBar.addClosableTab("", Home.class);
      }
    } else {
      if (tabBar.getTabCount() > 0) {
        tabBar.updateSelectedTab(active.getText(), active.getNavigationTarget());
      } else {
        tabBar.addClosableTab(active.getText(), active.getNavigationTarget());
      }
    }
    appBar.getMenuIcon().setVisible(false);
  }

  private VDNavigationItem getActiveItem(AfterNavigationEvent e) {
    for (VDNavigationItem item : naviDrawer.getMenu().getNaviItems()) {
      if (item.isHighlighted(e)) {
        return item;
      }
    }
    return null;
  }

  private void afterNavigationWithoutTabs(AfterNavigationEvent e) {
    VDNavigationItem active = getActiveItem(e);
    if (active != null) {
      getAppBar().setTitle(active.getText());
    }
  }

  @Override
  public void receiveBroadcast(UI ui, GSBroadcastMessage message) {
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
            String bulletinMessage = message.getMessage();
            if (appBar != null) {
              appBar.setBulletinMessage(bulletinMessage);
            }
          });
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
  }

  @Override
  public void modifyBootstrapPage(BootstrapPageResponse response) {
    final Element head = response.getDocument().head();
    head.append("<link rel=\"shortcut icon\" href=\"images/favicons/favicon-Small.ico\">");
  }

  /*
  @Override
  public void configurePage(InitialPageSettings settings) {
    settings.addMetaTag("apple-mobile-web-app-capable", "yes");
    settings.addMetaTag("apple-mobile-web-app-status-bar-style", "black");
    settings.addFavIcon("icon", "images/favicons/favicon.ico", "256x256");

    LoadingIndicatorConfiguration conf = settings.getLoadingIndicatorConfiguration();
    // disable default theme -> loading indicator will not be shown
    // conf.setApplyDefaultTheme(false);
    conf.setFirstDelay(100);
  }*/
}
