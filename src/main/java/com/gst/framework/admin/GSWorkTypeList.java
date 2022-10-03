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
import com.gst.domain.GSWorkType;
import com.gst.framework.GSDetailFormBase.ViewMode;
import com.gst.framework.components.GSAccessDeniedScreen;
import com.gst.framework.components.GSCommonDetailDialog;
import com.gst.framework.components.GSCommonViewFrame;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.framework.layout.GSMainLayout;
import com.gst.service.GSPMCService;
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
 * 工時類別建立作業 ADMI06
 */
@Route(value = "adm/worktype", layout = GSMainLayout.class)
public class GSWorkTypeList extends GSCommonViewFrame implements GSFormChangeHandler {
  
  public static final String HANDLER_ID = "ADMI06";
  
  private static final String FUNCTION_GRID_ID = "gst-admin-worktype-grid";
  
  private static final Logger logger = LoggerFactory.getLogger(GSWorkTypeList.class);
  private static final long serialVersionUID = 5762341839732388672L;
  
  private final GSMember contextUser;
  
  private VDGrid<GSWorkType> grid;
  
  private Registration resizeListener;
  
  private boolean hasAccess = false;
  
  @Autowired
  private GSPMCService PMCService;
  
  public GSWorkTypeList() {
    setTitleKey("gst.app.Navigator.System.WorkType");
    setId("gst-admin-worktype");
    
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
  
  private void createGrid() {
    grid = new VDGrid<>();
    grid.setId(FUNCTION_GRID_ID);
    
    Icon editIcon = VaadinIcon.EDIT.create();
    editIcon.setColor(UIConstants.DEFAULT_ICON_ENABLED_COLOR);
    editIcon.setSize("14px");
    
    Grid.Column<GSWorkType> editColumn =
      grid.addComponentColumn(this::createEditLink)
        .setWidth("40px")
        .setResizable(true)
        .setHeader("")
        .setFlexGrow(0)
        .setSortable(false);
    
    // String headerId = getTranslation(COLUMN_MODULE_ID);
    Grid.Column<GSWorkType> idColumn =
      grid.addColumn(GSWorkType::getId)
        .setWidth("150px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader("工時類別代號")
        .setSortable(false);
    
    // String headerName = getTranslation(COLUMN_MODULE_NAME);
    Grid.Column<GSWorkType> nameColumn =
      grid.addColumn(GSWorkType::getName)
        .setWidth("250px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader("工時類別名稱")
        .setSortable(false);
    
    Grid.Column<GSWorkType> nameEnColumn =
      grid.addColumn(GSWorkType::getTypeNameEng)
        .setWidth("300px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader("工時類別名稱-Eng.")
        .setSortable(false);
    
    VDLabel gridTitle = new VDLabel("系統工時類別");
    HorizontalLayout gridTitleLayout = new HorizontalLayout();
    gridTitleLayout.setVerticalComponentAlignment(FlexComponent.Alignment.BASELINE, gridTitle);
    gridTitleLayout.add(gridTitle);
    
    HeaderRow topRow = grid.prependHeaderRow();
    HeaderRow.HeaderCell titleActions = topRow.join(editColumn, idColumn, nameColumn, nameEnColumn);
    titleActions.setComponent(gridTitleLayout);
    
    refreshGrid(true);
  }
  
  private Component createContent() {
    VDButton refreshButton = new VDButton("重新整理");
    refreshButton.setIcon(VaadinIcon.REFRESH.create());
    refreshButton.addClickListener(
      e -> {
        refreshGrid(true);
      });
    
    VDButton addButton = new VDButton("新增工時類別");
    addButton.setIcon(VaadinIcon.FILE_ADD.create());
    addButton.addClickListener(
      e -> {
        GSWorkType workType = PMCService.createWorkType(contextUser);
        showDetailFrame(workType, ViewMode.New);
      });
    
    HorizontalLayout actionLayout = new VDHorizontalLayout();
    actionLayout.add(refreshButton, addButton);
    
    VerticalLayout gridLayout = new VDVerticalLayout();
    gridLayout.setSizeFull();
    gridLayout.add(grid);
    
    return new VDLayoutContainer(actionLayout, gridLayout);
  }
  
  private Collection<GSWorkType> getDataList() {
    return PMCService.getWorkTypeList(contextUser.getCompanyId());
  }
  
  private Component createEditLink(GSWorkType data) {
    Icon editIcon = new Icon(VaadinIcon.EDIT);
    editIcon.setColor("#00b4f0");
    editIcon.getStyle().set("cursor", "pointer");
    editIcon.setSize("14px");
    editIcon.addClickListener(e -> showDetailFrame(data, ViewMode.Delete));
    return editIcon;
  }
  
  private void showDetailFrame(GSWorkType data, ViewMode mode) {
    GSWorkTypeDetailForm detailForm = new GSWorkTypeDetailForm();
    detailForm.setViewMode(mode);
    detailForm.setChangeHandler(this);
    detailForm.setDetail(data);
    
    GSCommonDetailDialog dialog = new GSCommonDetailDialog(detailForm);
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
