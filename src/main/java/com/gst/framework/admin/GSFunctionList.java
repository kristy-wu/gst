package com.gst.framework.admin;

import com.dcsplab.vdui.UIConstants;
import com.dcsplab.vdui.components.VDGrid;
import com.dcsplab.vdui.components.VDLabel;
import com.dcsplab.vdui.layout.VDLayoutContainer;
import com.dcsplab.vdui.layout.VDVerticalLayout;
import com.gst.context.GSContext;
import com.gst.domain.GSAuthorization.Action;
import com.gst.domain.GSMember;
import com.gst.domain.GSSystemFunction;
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

@Route(value = "adm/function", layout = GSMainLayout.class)
public class GSFunctionList extends GSCommonViewFrame implements GSFormChangeHandler {
  
  public static final String HANDLER_ID = "ADMI02";
  
  public static final String TITLE = "gst.app.Navigator.System.Function";
  
  private static final String FUNCTION_GRID_ID = "gst-admin-function-grid";
  
  private static final long serialVersionUID = 8861948539566386015L;
  
  private final GSMember contextUser;
  
  @Autowired
  GSAuthorizationService authService;
  
  private Registration resizeListener;
  
  private VDGrid<GSSystemFunction> grid;
  
  private boolean hasAccess;
  
  public GSFunctionList() {
    setTitleKey(TITLE);
    setId("gst-admin-function");
    
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
    grid.setId(FUNCTION_GRID_ID);
    
    Icon editIcon = VaadinIcon.EDIT.create();
    editIcon.setColor(UIConstants.DEFAULT_ICON_ENABLED_COLOR);
    editIcon.setSize("14px");
    Grid.Column<GSSystemFunction> editColumn =
      grid.addComponentColumn(this::createEditLink)
        .setWidth("40px")
        .setResizable(true)
        .setHeader("")
        .setFlexGrow(0)
        .setSortable(false);
    
    // String headerId = getTranslation(COLUMN_MODULE_ID);
    Grid.Column<GSSystemFunction> idColumn =
      grid.addColumn(GSSystemFunction::getId)
        .setWidth("80px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader("程式代號")
        .setSortable(false);
    
    // String headerName = getTranslation(COLUMN_MODULE_NAME);
    Grid.Column<GSSystemFunction> nameColumn =
      grid.addColumn(GSSystemFunction::getName)
        .setWidth("300px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader("程式名稱")
        .setSortable(false);
    
    // String headerDept = getTranslation(COLUMN_MODULE_REMARK);
    Grid.Column<GSSystemFunction> typeColumn =
      grid.addColumn(GSSystemFunction::getType)
        .setWidth("80px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader("類別")
        .setSortable(false);
    
    Grid.Column<GSSystemFunction> modColumn =
      grid.addColumn(GSSystemFunction::getModule)
        .setWidth("80px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader("模組代號")
        .setSortable(false);
    
    Grid.Column<GSSystemFunction> remarkColumn =
      grid.addColumn(GSSystemFunction::getRemark)
        .setWidth("200px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader("備註")
        .setSortable(false);
    
    VDLabel gridTitle = new VDLabel("系統程式");
    HorizontalLayout gridTitleLayout = new HorizontalLayout();
    gridTitleLayout.setVerticalComponentAlignment(FlexComponent.Alignment.BASELINE, gridTitle);
    gridTitleLayout.add(gridTitle);
    
    HeaderRow topRow = grid.prependHeaderRow();
    HeaderRow.HeaderCell titleActions =
      topRow.join(editColumn, idColumn, nameColumn, typeColumn, modColumn, remarkColumn);
    titleActions.setComponent(gridTitleLayout);
    
    refreshGrid(true);
  }
  
  private Collection<GSSystemFunction> getDataList() {
    return authService.getSystemFunctionList(contextUser.getCompanyId());
  }
  
  private void refreshGrid(boolean reload) {
    if (reload) {
      grid.setItems(getDataList());
    } else {
      grid.refresh();
    }
  }
  
  private void showDetailFrame(GSSystemFunction data) {
    GSFunctionDetailForm detailForm = new GSFunctionDetailForm();
    detailForm.setViewMode(ViewMode.Modify);
    detailForm.setDetail(data);
    detailForm.setChangeHandler(this);
    
    GSCommonDetailDialog dialog = new GSCommonDetailDialog(detailForm);
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
  }
  
  private Component createEditLink(GSSystemFunction data) {
    Icon editIcon = new Icon(VaadinIcon.EDIT);
    editIcon.setColor("#00b4f0");
    editIcon.getStyle().set("cursor", "pointer");
    editIcon.setSize("14px");
    editIcon.addClickListener(e -> showDetailFrame(data));
    return editIcon;
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
    
    GSMainLayout ml = GSMainLayout.get();
    ml.getAppBar().setTitle(getTranslation(TITLE));
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
