package com.gst.framework.admin;

import com.dcsplab.vdui.UIConstants;
import com.dcsplab.vdui.components.VDButton;
import com.dcsplab.vdui.components.VDGrid;
import com.dcsplab.vdui.components.VDLabel;
import com.dcsplab.vdui.layout.VDHorizontalLayout;
import com.dcsplab.vdui.layout.VDLayoutContainer;
import com.dcsplab.vdui.layout.VDVerticalLayout;
import com.gst.context.GSContext;
import com.gst.domain.GSAuthorization.Action;
import com.gst.domain.GSMember;
import com.gst.framework.GSDetailFormBase.ViewMode;
import com.gst.framework.components.GSAccessDeniedScreen;
import com.gst.framework.components.GSCommonDetailDialog;
import com.gst.framework.components.GSCommonViewFrame;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.framework.layout.GSMainLayout;
import com.gst.service.GSOrganizationService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collection;

/*
 * 使用者程式權限設定 ADMI05
 */
@Route(value = "adm/useraccess", layout = GSMainLayout.class)
public class GSMemberAccessList extends GSCommonViewFrame implements GSFormChangeHandler {
  
  public static final String USER_ACCESS_GRID_ID = "gst-admin-memberaccess-grid";
  
  private static final String HANDLER_ID = "ADMI05";
  
  private static final long serialVersionUID = -8709818181187792811L;
  private static final Logger logger = LoggerFactory.getLogger(GSMemberAccessList.class);
  
  private final GSMember contextUser;
  
  private VDGrid<GSMember> grid;
  
  private Registration resizeListener;
  
  private boolean hasAccess = false;
  
  @Autowired
  GSOrganizationService orgService;
  
  public GSMemberAccessList() {
    setTitleKey("gst.app.Navigator.System.FunctionUser");
    setId("gst-admin-memberaccess");
    
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
    VDButton refreshButton = new VDButton("重新整理");
    refreshButton.setIcon(VaadinIcon.REFRESH.create());
    refreshButton.addClickListener(e -> refreshGrid(true));
    
    HorizontalLayout actionLayout = new VDHorizontalLayout();
    actionLayout.add(refreshButton);
    
    VerticalLayout gridLayout = new VDVerticalLayout();
    gridLayout.setSizeFull();
    gridLayout.add(grid);
    
    return new VDLayoutContainer(actionLayout, gridLayout);
  }
  
  private void createGrid() {
    grid = new VDGrid<>();
    grid.setId(USER_ACCESS_GRID_ID);
    
    Grid.Column<GSMember> idColumn =
      grid.addColumn(GSMember::getId)
        .setWidth("100px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader("員工編號")
        .setSortable(false);
    
    Grid.Column<GSMember> nameColumn =
      grid.addColumn(new ComponentRenderer<>(this::createPersonInfo))
        .setWidth("150px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader("員工姓名")
        .setSortable(false);
    
    Grid.Column<GSMember> mgrColumn =
      grid.addColumn(new ComponentRenderer<>(this::createMgrAccessDisplay))
        .setWidth("100px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader("管理權限")
        .setSortable(false);
    
    Grid.Column<GSMember> accessColumn =
      grid.addComponentColumn(
          item -> {
            Icon editIcon = VaadinIcon.DIPLOMA.create();
            editIcon.setColor(UIConstants.DEFAULT_ICON_ENABLED_COLOR);
            editIcon.getStyle().set("cursor", "pointer");
            editIcon.setSize("14px");
            editIcon.addClickListener(e -> showDetailFrame(item));
            
            return editIcon;
          })
        .setWidth("150px")
        .setResizable(true)
        .setHeader("程式權限")
        .setFlexGrow(0)
        .setSortable(false);
    
    VDLabel gridTitle = new VDLabel("系統使用者");
    HorizontalLayout gridTitleLayout = new HorizontalLayout();
    gridTitleLayout.setVerticalComponentAlignment(FlexComponent.Alignment.BASELINE, gridTitle);
    gridTitleLayout.add(gridTitle);
    
    HeaderRow topRow = grid.prependHeaderRow();
    HeaderRow.HeaderCell titleActions = topRow.join(idColumn, nameColumn, mgrColumn, accessColumn);
    titleActions.setComponent(gridTitleLayout);
    
    refreshGrid(true);
  }
  
  private Div createMgrAccessDisplay(GSMember member) {
    Div display = new Div();
    
    if (member.hasManagerAccess()) {
      Icon checkIcon = VaadinIcon.CHECK.create();
      checkIcon.setColor(UIConstants.DEFAULT_ICON_ENABLED_COLOR);
      checkIcon.setSize("13px");
      display.add(checkIcon);
    }
    
    return display;
  }
  
  private Span createPersonInfo(GSMember member) {
    Span deptLabel = new Span();
    deptLabel.setText(member.getName());
    return deptLabel;
  }
  
  private void showDetailFrame(GSMember data) {
    GSMemberAccessSetting detailForm = new GSMemberAccessSetting();
    
    detailForm.setDetail(data);
    detailForm.setChangeHandler(this);
    detailForm.setViewMode(ViewMode.ConfirmClose);
    
    GSCommonDetailDialog dialog = new GSCommonDetailDialog(detailForm);
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
  }
  
  private Collection<GSMember> getDataList() {
    return orgService.getCompanyMemberList(contextUser.getCompanyId());
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
  
  @Override
  protected void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);
    getUI()
      .ifPresent(
        ui -> {
          if (hasAccess) {
            Page page = ui.getPage();
            resizeListener =
              page.addBrowserWindowResizeListener(
                event -> updateVisibleColumns(event.getWidth()));
            page.retrieveExtendedClientDetails(
              details -> updateVisibleColumns(details.getBodyClientWidth()));
          }
        });
  }
  
  @Override
  protected void onDetach(DetachEvent detachEvent) {
    if (hasAccess) {
      resizeListener.remove();
      resizeListener = null;
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
}
