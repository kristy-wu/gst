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
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Route(value = "adm/role", layout = GSMainLayout.class)
public class GSRoleList extends GSCommonViewFrame implements GSFormChangeHandler {
  
  public static final String HANDLER_ID = "ADMI03";
  public static final String ROLE_GRID_ID = "gst-admin-role-grid";
  
  private static final long serialVersionUID = -4302725591965479735L;
  
  private final GSMember contextUser;
  
  private VDGrid<GSRole> grid;
  
  private Registration resizeListener;
  
  @Autowired
  private GSAuthorizationService authService;
  
  private final boolean hasAccess;
  
  public GSRoleList() {
    setTitleKey("gst.app.Navigator.System.Role");
    setId("gst-admin-role");
    
    contextUser = GSContext.getCurrentMember();
    hasAccess = contextUser.hasAccess(HANDLER_ID, Action.Search);
  }
  
  @PostConstruct
  private void init() {
    if (hasAccess) {
      createGrid();
      setViewContent(createContent());
    } else {
      GSAccessDeniedScreen deniedScreen = new GSAccessDeniedScreen();
      setViewContent(deniedScreen);
    }
  }
  
  private Component createContent() {
    VDButton addButton = new VDButton("新增角色");
    addButton.setIcon(VaadinIcon.FILE_ADD.create());
    addButton.addClickListener(
      e -> {
        try {
          GSRole role = authService.createRole(contextUser);
          showDetailFrame(role, ViewMode.New);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      });
    
    VDButton refreshButton = new VDButton("重新整理");
    refreshButton.setIcon(VaadinIcon.REFRESH.create());
    refreshButton.addClickListener(e -> refreshGrid(true));
    
    HorizontalLayout actionLayout = new VDHorizontalLayout();
    actionLayout.add(refreshButton, addButton);
    
    VerticalLayout gridLayout = new VDVerticalLayout();
    gridLayout.setSizeFull();
    gridLayout.add(grid);
    
    return new VDLayoutContainer(actionLayout, gridLayout);
  }
  
  private void createGrid() {
    grid = new VDGrid<>();
    grid.setId(ROLE_GRID_ID);
    
    Icon editIcon = VaadinIcon.EDIT.create();
    editIcon.setColor(UIConstants.DEFAULT_ICON_ENABLED_COLOR);
    editIcon.setSize("14px");
    Grid.Column<GSRole> editColumn =
      grid.addComponentColumn(this::createEditLink)
        .setWidth("40px")
        .setResizable(true)
        .setHeader("")
        .setFlexGrow(0)
        .setSortable(false);
    
    // String headerId = getTranslation(COLUMN_MODULE_ID);
    Grid.Column<GSRole> idColumn =
      grid.addColumn(GSRole::getId)
        .setWidth("80px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader("角色編號")
        .setSortable(false);
    
    // String headerName = getTranslation(COLUMN_MODULE_NAME);
    Grid.Column<GSRole> nameColumn =
      grid.addColumn(GSRole::getName)
        .setWidth("150px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader("角色名稱")
        .setSortable(false);
    
    Grid.Column<GSRole> accessColumn =
      grid.addComponentColumn(this::createAccessLink)
        .setWidth("80px")
        .setResizable(true)
        .setHeader("權限")
        .setFlexGrow(0)
        .setSortable(false);
    
    Grid.Column<GSRole> remarkColumn =
      grid.addColumn(GSRole::getRemark)
        .setWidth("300px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader("備註")
        .setSortable(false);
    
    VDLabel gridTitle = new VDLabel("系統角色");
    HorizontalLayout gridTitleLayout = new HorizontalLayout();
    gridTitleLayout.setVerticalComponentAlignment(FlexComponent.Alignment.BASELINE, gridTitle);
    gridTitleLayout.add(gridTitle);
    
    HeaderRow topRow = grid.prependHeaderRow();
    HeaderRow.HeaderCell titleActions =
      topRow.join(editColumn, idColumn, nameColumn, accessColumn, remarkColumn);
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
  
  private void showDetailFrame(GSRole data, ViewMode mode) {
    GSRoleDetailForm detailForm = new GSRoleDetailForm();
    detailForm.setViewMode(mode);
    detailForm.setChangeHandler(this);
    detailForm.setDetail(data);
    
    GSCommonDetailDialog dialog = new GSCommonDetailDialog(detailForm);
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
  }
  
  private void showAccessFrame(GSRole data) {
    GSRoleAccessSetting accessEditor = new GSRoleAccessSetting();
    accessEditor.setDetail(data);
    accessEditor.setChangeHandler(this);
    accessEditor.setViewMode(ViewMode.Modify);
    
    GSCommonDetailDialog dialog = new GSCommonDetailDialog(accessEditor);
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
  }
  
  private Component createEditLink(GSRole data) {
    Icon icon = new Icon(VaadinIcon.EDIT);
    icon.setColor("#00b4f0");
    icon.getStyle().set("cursor", "pointer");
    icon.setSize("14px");
    icon.addClickListener(e -> showDetailFrame(data, ViewMode.Delete));
    return icon;
  }
  
  private Component createAccessLink(GSRole data) {
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
    refreshGrid(true);
  }
  
  @Override
  public String getHandlerID() {
    return HANDLER_ID;
  }
}
