package com.gst.framework.org;

import com.dcsplab.vdui.UIConstants;
import com.dcsplab.vdui.components.VDButton;
import com.dcsplab.vdui.components.VDGrid;
import com.dcsplab.vdui.components.VDLabel;
import com.dcsplab.vdui.layout.VDFlexBoxLayout;
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
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.shared.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Route(value = "org/personnel", layout = GSMainLayout.class)
@RouteAlias(value = "org", layout = GSMainLayout.class)
public class GSPersonnelList extends GSCommonViewFrame implements GSFormChangeHandler {
  
  public static final String HANDLER_ID = "CMSI03";
  public static final String PERSONNEL_GRID_ID = "GSAdminPersonnelGrid";
  public static final String COLUMN_PERSONNEL_ID = "gst.grid.personnel.ID";
  public static final String COLUMN_PERSONNEL_NAME = "gst.grid.personnel.Name";
  
  // public static final String COLUMN_COMPANY = "gst.grid.common.Company";
  
  // public static final String COLUMN_CREATEDATE = "gst.grid.common.CreateDate";
  public static final String COLUMN_PERSONNEL_DEPT = "gst.grid.personnel.Department";
  public static final String COLUMN_PERSONNEL_ACTIVATED = "gst.grid.personnel.ActivatedDate";
  public static final String COLUMN_PERSONNEL_DISABLED = "gst.grid.personnel.DisabledDate";
  
  private static final Logger logger = LoggerFactory.getLogger(GSPersonnelList.class);
  private static final long serialVersionUID = 2337957615942176801L;
  
  private VDGrid<GSMember> grid;
  
  private Registration resizeListener;
  
  private boolean hasAccess = false;
  
  private boolean hasCreateAccess = false;
  
  private boolean hasModifyAccess = false;
  
  private boolean hasDeleteAccess = false;
  
  @Autowired
  GSOrganizationService orgService;
  
  private final GSMember contextUser;
  
  public GSPersonnelList() {
    setTitleKey("gst.app.Navigator.Org.Personnel");
    setId("gst-personnel-view");
    
    contextUser = GSContext.getCurrentMember();
  }
  
  @PostConstruct
  private void init() {
    
    hasAccess = contextUser.hasAccess(HANDLER_ID, Action.Search);
    hasCreateAccess = contextUser.hasAccess(HANDLER_ID, Action.Create);
    hasModifyAccess = contextUser.hasAccess(HANDLER_ID, Action.Modify);
    hasDeleteAccess = contextUser.hasAccess(HANDLER_ID, Action.Delete);
    
    if (hasAccess) {
      createGrid();
      setViewContent(createContent());
    } else {
      GSAccessDeniedScreen deniedScreen = new GSAccessDeniedScreen();
      setViewContent(deniedScreen);
    }
  }
  
  private VDFlexBoxLayout createContent() {
    VDButton addButton = new VDButton("新增員工");
    Icon icon = VaadinIcon.FILE_ADD.create();
    if (hasCreateAccess) {
      addButton.addClickListener(
        e -> {
          try {
            GSMember member = orgService.createMember(contextUser);
            showDetailFrame(member, ViewMode.New);
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        });
    } else {
      addButton.setEnabled(false);
    }
    addButton.setIcon(icon);
    
    Icon refreshIcon = VaadinIcon.REFRESH.create();
    VDButton refreshButton = new VDButton("重新整理");
    refreshButton.setIcon(refreshIcon);
    refreshButton.addClickListener(e -> refreshGrid(true));
    
    HorizontalLayout actionLayout = new VDHorizontalLayout();
    actionLayout.add(refreshButton, addButton);
    
    VerticalLayout gridLayout = new VDVerticalLayout();
    gridLayout.setSizeFull();
    gridLayout.add(grid);
    
    return new VDLayoutContainer(actionLayout, gridLayout);
  }
  
  private void showDetailFrame(GSMember member, ViewMode mode) {
    GSPersonnelDetailForm detailForm = new GSPersonnelDetailForm();
    detailForm.setViewMode(mode);
    detailForm.setChangeHandler(this);
    detailForm.setDetail(member);
    
    GSCommonDetailDialog dialog = new GSCommonDetailDialog(detailForm);
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
  }
  
  private Span createDepartmentInfo(GSMember member) {
    Span deptLabel = new Span();
    String dept = member.getCompanyId() + "-" + member.getDeptId();
    
    deptLabel.setText(dept);
    return deptLabel;
  }
  
  private void createGrid() {
    grid = new VDGrid<>();
    grid.setId(PERSONNEL_GRID_ID);
    
    Icon headerIcon = VaadinIcon.EDIT.create();
    headerIcon.setColor(UIConstants.DEFAULT_ICON_ENABLED_COLOR);
    headerIcon.setSize("14px");
    Grid.Column<GSMember> editColumn =
      grid.addComponentColumn(
          item -> {
            Icon editIcon = new Icon(VaadinIcon.EDIT);
            editIcon.setSize("14px");
            editIcon.getStyle().set("cursor", "pointer");
            editIcon.setColor(UIConstants.DEFAULT_ICON_ENABLED_COLOR);
            
            if (hasDeleteAccess) {
              editIcon.addClickListener(e -> showDetailFrame(item, ViewMode.Delete));
            } else if (hasModifyAccess) {
              editIcon.addClickListener(e -> showDetailFrame(item, ViewMode.Modify));
            } else {
              editIcon.addClickListener(e -> showDetailFrame(item, ViewMode.View));
            }
            
            return editIcon;
          })
        .setWidth("40px")
        .setResizable(true)
        .setHeader("")
        .setFlexGrow(0)
        .setSortable(false);
    
    String headerPersonnelId = getTranslation(COLUMN_PERSONNEL_ID);
    Grid.Column<GSMember> idColumn =
      grid.addColumn(GSMember::getId)
        .setWidth("100px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader(headerPersonnelId)
        .setSortable(true);
    
    String headerName = getTranslation(COLUMN_PERSONNEL_NAME);
    Grid.Column<GSMember> nameColumn =
      grid.addColumn(GSMember::getName)
        .setWidth("150px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader(headerName)
        .setSortable(false);
    
    String headerDept = getTranslation(COLUMN_PERSONNEL_DEPT);
    Grid.Column<GSMember> deptColumn =
      grid.addColumn(new ComponentRenderer<>(this::createDepartmentInfo))
        .setWidth("150px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader(headerDept)
        .setSortable(true);
    
    String headerActive = getTranslation(COLUMN_PERSONNEL_ACTIVATED);
    Grid.Column<GSMember> actDateColumn =
      grid.addColumn(GSMember::getActiveDate)
        .setWidth("150px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader(headerActive)
        .setSortable(true);
    
    String headerDisable = getTranslation(COLUMN_PERSONNEL_DISABLED);
    Grid.Column<GSMember> endDateColumn =
      grid.addColumn(GSMember::getObsoleteDate)
        .setWidth("150px")
        .setResizable(true)
        .setFlexGrow(0)
        .setHeader(headerDisable)
        .setSortable(true);
    
    VDLabel gridTitle = new VDLabel("公司人員");
    HorizontalLayout gridTitleLayout = new HorizontalLayout();
    gridTitleLayout.setVerticalComponentAlignment(FlexComponent.Alignment.BASELINE, gridTitle);
    gridTitleLayout.add(gridTitle);
    
    HeaderRow topRow = grid.prependHeaderRow();
    HeaderRow.HeaderCell titleActions =
      topRow.join(editColumn, idColumn, nameColumn, deptColumn, actDateColumn, endDateColumn);
    titleActions.setComponent(gridTitleLayout);
    
    refreshGrid(true);
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
    refreshGrid(true);
  }
  
  @Override
  public String getHandlerID() {
    return HANDLER_ID;
  }
}
