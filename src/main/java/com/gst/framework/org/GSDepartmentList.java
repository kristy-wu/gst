package com.gst.framework.org;

import com.dcsplab.vdui.components.VDButton;
import com.dcsplab.vdui.layout.VDFlexBoxLayout;
import com.dcsplab.vdui.layout.VDHorizontalLayout;
import com.dcsplab.vdui.layout.VDLayoutContainer;
import com.dcsplab.vdui.layout.VDVerticalLayout;
import com.gst.context.GSContext;
import com.gst.domain.GSAuthorization;
import com.gst.domain.GSAuthorization.Action;
import com.gst.domain.GSDepartment;
import com.gst.domain.GSMember;
import com.gst.domain.GSSystemFunction;
import com.gst.framework.GSDetailFormBase.ViewMode;
import com.gst.framework.components.*;
import com.gst.framework.components.GSOrgTreeGrid.TreeType;
import com.gst.framework.layout.GSMainLayout;
import com.gst.service.GSAuthorizationService;
import com.gst.service.GSOrganizationService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/*
 * 部門資料 CMSI02
 */
@Route(value = "org/department", layout = GSMainLayout.class)
public class GSDepartmentList extends GSCommonViewFrame implements GSFormChangeHandler {
  
  public static final String HANDLER_ID = "CMSI02";
  
  private static final Logger logger = LoggerFactory.getLogger(GSDepartmentList.class);
  
  private static final long serialVersionUID = 52585548781936141L;
  
  private final GSMember contextUser;
  
  private GSSystemFunction func;
  
  private GSOrgTreeGrid orgTree;
  
  private boolean hasAccess = false;
  
  private Registration resizeListener;
  
  @Autowired
  GSAuthorizationService authService;
  
  @Autowired
  GSOrganizationService orgService;
  
  public GSDepartmentList() {
    setTitleKey("gst.app.Navigator.Org.Department");
    setId("gst-department-view");
    
    contextUser = GSContext.getCurrentMember();
  }
  
  @PostConstruct
  private void init() {
    hasAccess = contextUser.hasAccess(HANDLER_ID, Action.Search);
    if (hasAccess) {
      func = authService.getSystemFunction(contextUser.getCompanyId(), getHandlerID());
      orgTree = new GSOrgTreeGrid(TreeType.DeptList, func);
      setViewContent(createContent());
    } else {
      GSAccessDeniedScreen deniedScreen = new GSAccessDeniedScreen();
      setViewContent(deniedScreen);
    }
  }
  
  private VDFlexBoxLayout createContent() {
    boolean hasCreateAccess = contextUser.hasAccess(func, GSAuthorization.Action.Create);
    hasCreateAccess = contextUser.hasManagerAccess() || hasCreateAccess;
    /*
    logger.info(
        "Person[" + contextUser.getName() + "] has create access[" + func + "]:" + hasCreateAccess);*/
    
    Icon refreshIcon = VaadinIcon.REFRESH.create();
    VDButton refreshButton = new VDButton("重新整理");
    refreshButton.setIcon(refreshIcon);
    refreshButton.addClickListener(e -> refreshGrid());
    
    Icon icon = VaadinIcon.FILE_ADD.create();
    VDButton addButton = new VDButton("新增部門");
    addButton.setIcon(icon);
    if (hasCreateAccess) {
      addButton.addClickListener(
        e -> {
          try {
            GSDepartment dept = orgService.createDepartment(contextUser);
            showDetailFrame(dept);
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        });
    } else {
      addButton.setEnabled(false);
    }
    
    HorizontalLayout actionLayout = new VDHorizontalLayout();
    actionLayout.add(refreshButton, addButton);
    
    VerticalLayout gridLayout = new VDVerticalLayout();
    gridLayout.setSizeFull();
    gridLayout.add(orgTree);
    
    return new VDLayoutContainer(actionLayout, gridLayout);
  }
  
  private void showDetailFrame(GSDepartment dept) {
    GSDepartmentDetailForm detailForm = new GSDepartmentDetailForm();
    detailForm.setViewMode(ViewMode.New);
    detailForm.setChangeHandler(this);
    detailForm.setDetail(dept);
    
    GSCommonDetailDialog dialog = new GSCommonDetailDialog(detailForm);
    dialog.setWidth("600px");
    dialog.setHeight("300px");
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
  }
  
  private void refreshGrid() {
    if (orgTree == null) {
      return;
    }
    orgTree.onChange();
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
    ml.getAppBar().setTitle(getTranslation("gst.app.Navigator.Org.Department"));
  }
  
  @Override
  protected void onDetach(DetachEvent detachEvent) {
    if (hasAccess) {
      resizeListener.remove();
    }
    super.onDetach(detachEvent);
  }
  
  @Override
  public void onChange() {
    refreshGrid();
  }
  
  @Override
  public String getHandlerID() {
    return HANDLER_ID;
  }
}
