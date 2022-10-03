package com.gst.framework.admin;

import com.dcsplab.vdui.UIConstants;
import com.dcsplab.vdui.components.VDGrid;
import com.dcsplab.vdui.components.VDLabel;
import com.dcsplab.vdui.layout.VDLayoutContainer;
import com.dcsplab.vdui.layout.VDVerticalLayout;
import com.gst.context.GSContext;
import com.gst.domain.GSAuthorization.Action;
import com.gst.domain.GSMember;
import com.gst.domain.GSModule;
import com.gst.framework.GSDetailFormBase.ViewMode;
import com.gst.framework.components.GSAccessDeniedScreen;
import com.gst.framework.components.GSCommonDetailDialog;
import com.gst.framework.components.GSCommonViewFrame;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.framework.layout.GSMainLayout;
import com.gst.service.GSAuthorizationService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Route(value = "adm/module", layout = GSMainLayout.class)
public class GSModuleList extends GSCommonViewFrame implements GSFormChangeHandler {
  
  public static final String HANDLER_ID = "ADMI01";
  public static final String MODULE_GRID_ID = "gst-admin-module-grid";
  public static final String COLUMN_COMPANY = "gst.grid.common.Company";
  public static final String COLUMN_CREATEDATE = "gst.grid.common.CreateDate";
  public static final String COLUMN_MODULE_ID = "gst.grid.module.ID";
  public static final String COLUMN_MODULE_NAME = "gst.grid.module.Name";
  public static final String COLUMN_MODULE_REMARK = "gst.grid.module.Remark";
  
  private static final long serialVersionUID = 3455801203836952397L;
  
  private final GSMember contextUser;
  
  private VDGrid<GSModule> grid;
  
  @Autowired
  GSAuthorizationService authService;
  
  public GSModuleList() {
    setTitleKey("gst.app.Navigator.System.Module");
    setId("gst-admin-module");
    
    contextUser = GSContext.getCurrentMember();
  }
  
  @PostConstruct
  private void init() {
    boolean hasAccess = contextUser.hasAccess(HANDLER_ID, Action.Search);
    if (hasAccess) {
      createGrid();
      setViewContent(createContent());
    } else {
      GSAccessDeniedScreen deniedScreen = new GSAccessDeniedScreen();
      setViewContent(deniedScreen);
    }
  }
  
  private Component createContent() {
    VDVerticalLayout gridLayout = new VDVerticalLayout();
    gridLayout.setSizeFull();
    gridLayout.add(grid);
    
    return new VDLayoutContainer(gridLayout);
  }
  
  private void createGrid() {
    grid = new VDGrid<>();
    grid.setId(MODULE_GRID_ID);
    
    Icon editIcon = VaadinIcon.EDIT.create();
    editIcon.setColor(UIConstants.DEFAULT_ICON_ENABLED_COLOR);
    editIcon.setSize("14px");
    Grid.Column<GSModule> editColumn =
      grid.addComponentColumn(this::createEditLink)
        .setWidth("40px")
        .setResizable(true)
        .setHeader("")
        .setFlexGrow(0)
        .setSortable(false);
    
    String headerId = getTranslation(COLUMN_MODULE_ID);
    Grid.Column<GSModule> idColumn =
      grid.addColumn(GSModule::getId)
        .setWidth("80px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader(headerId)
        .setSortable(false);
    
    String headerName = getTranslation(COLUMN_MODULE_NAME);
    Grid.Column<GSModule> nameColumn =
      grid.addColumn(GSModule::getName)
        .setWidth("200px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader(headerName)
        .setSortable(false);
    
    String headerDept = getTranslation(COLUMN_MODULE_REMARK);
    Grid.Column<GSModule> remarkColumn =
      grid.addColumn(GSModule::getRemark)
        .setWidth("300px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader(headerDept)
        .setSortable(false);
    
    VDLabel gridTitle = new VDLabel("系統模組");
    HorizontalLayout gridTitleLayout = new HorizontalLayout();
    gridTitleLayout.setVerticalComponentAlignment(FlexComponent.Alignment.BASELINE, gridTitle);
    gridTitleLayout.add(gridTitle);
    
    HeaderRow topRow = grid.prependHeaderRow();
    HeaderRow.HeaderCell titleActions = topRow.join(editColumn, idColumn, nameColumn, remarkColumn);
    titleActions.setComponent(gridTitleLayout);
    
    refreshGrid(true);
  }
  
  private Collection<GSModule> getDataList() {
    return authService.getModuleList(contextUser.getCompanyId());
  }
  
  private void refreshGrid(boolean reload) {
    if (reload) {
      grid.setItems(getDataList());
    } else {
      grid.refresh();
    }
  }
  
  private void showDetailFrame(GSModule module) {
    GSModuleDetailForm detailForm = new GSModuleDetailForm();
    detailForm.setViewMode(ViewMode.Modify);
    detailForm.setChangeHandler(this);
    detailForm.setDetail(module);
    
    GSCommonDetailDialog dialog = new GSCommonDetailDialog(detailForm);
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
  }
  
  private Component createEditLink(GSModule module) {
    Icon editIcon = new Icon(VaadinIcon.EDIT);
    editIcon.setColor("#00b4f0");
    editIcon.getStyle().set("cursor", "pointer");
    editIcon.setSize("14px");
    editIcon.addClickListener(e -> showDetailFrame(module));
    return editIcon;
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
