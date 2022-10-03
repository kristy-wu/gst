package com.gst.framework.admin;

import com.dcsplab.vdui.components.VDGrid;
import com.dcsplab.vdui.components.VDLabel;
import com.dcsplab.vdui.layout.VDLayoutContainer;
import com.dcsplab.vdui.layout.VDVerticalLayout;
import com.gst.context.GSContext;
import com.gst.domain.GSAuthorization.Action;
import com.gst.domain.GSMember;
import com.gst.domain.GSRole;
import com.gst.framework.GSDetailFormBase.ViewMode;
import com.gst.framework.components.GSAccessDeniedScreen;
import com.gst.framework.components.GSCommonDetailDialog;
import com.gst.framework.components.GSCommonViewFrame;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.framework.layout.GSMainLayout;
import com.gst.service.GSAuthorizationService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collection;

/*
 * 角色指派使用者(ADMB02)
 */
@Route(value = "adm/userroles", layout = GSMainLayout.class)
public class GSRoleUser extends GSCommonViewFrame implements GSFormChangeHandler {
  
  public static final String HANDLER_ID = "ADMB02";
  public static final String ROLE_GRID_ID = "gst-admin-role-grid";
  
  private static final Logger logger = LoggerFactory.getLogger(GSRoleUser.class);
  private static final long serialVersionUID = 8777577418757660847L;
  
  private final GSMember contextUser;
  
  private VDGrid<GSRole> grid;
  
  private Registration resizeListener;
  
  private boolean hasAccess = false;
  
  @Autowired
  GSAuthorizationService authService;
  
  public GSRoleUser() {
    setTitleKey("gst.app.Navigator.System.RoleUser");
    setId("gst-admin-role-users");
    
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
  
  private void createGrid() {
    grid = new VDGrid<>();
    grid.setId(ROLE_GRID_ID);
    
    // String headerId = getTranslation(COLUMN_MODULE_ID);
    Grid.Column<GSRole> idColumn =
      grid.addColumn(GSRole::getId)
        .setWidth("100px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader("角色編號")
        .setSortable(false);
    
    // String headerName = getTranslation(COLUMN_MODULE_NAME);
    Grid.Column<GSRole> nameColumn =
      grid.addColumn(GSRole::getName)
        .setWidth("250px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader("角色名稱")
        .setSortable(false);
    
    Grid.Column<GSRole> memberColumn =
      grid.addComponentColumn(this::createAssignmentLink)
        .setWidth("200px")
        .setResizable(true)
        .setHeader("指派人員")
        .setFlexGrow(0)
        .setSortable(false);
    
    VDLabel gridTitle = new VDLabel("系統角色");
    HorizontalLayout gridTitleLayout = new HorizontalLayout();
    gridTitleLayout.setVerticalComponentAlignment(FlexComponent.Alignment.BASELINE, gridTitle);
    gridTitleLayout.add(gridTitle);
    
    HeaderRow topRow = grid.prependHeaderRow();
    HeaderRow.HeaderCell titleActions = topRow.join(idColumn, nameColumn, memberColumn);
    titleActions.setComponent(gridTitleLayout);
    
    refreshGrid(true);
  }
  
  private Collection<GSRole> getDataList() {
    return authService.getRoleList(contextUser.getCompanyId());
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
  
  private void showAccessFrame(GSRole data) {
    GSRoleUserAssignment assignForm = new GSRoleUserAssignment();
    assignForm.setDetail(data);
    assignForm.setChangeHandler(this);
    assignForm.setViewMode(ViewMode.ConfirmClose);
    
    GSCommonDetailDialog dialog = new GSCommonDetailDialog(assignForm);
    dialog.setWidth("680px");
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
  }
  
  private Component createAssignmentLink(GSRole data) {
    Icon icon = new Icon(VaadinIcon.DIPLOMA);
    icon.setColor("#00b4f0");
    icon.getStyle().set("cursor", "pointer");
    icon.setSize("16px");
    icon.addClickListener(e -> showAccessFrame(data));
    return icon;
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
    // refreshGrid(true);
  }
  
  @Override
  public String getHandlerID() {
    return HANDLER_ID;
  }
}
