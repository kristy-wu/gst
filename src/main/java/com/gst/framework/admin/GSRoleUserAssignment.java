package com.gst.framework.admin;

import com.dcsplab.vdui.components.VDButton;
import com.dcsplab.vdui.components.VDGrid;
import com.dcsplab.vdui.components.VDLabel;
import com.dcsplab.vdui.layout.VDFlexBoxLayout;
import com.dcsplab.vdui.layout.size.Horizontal;
import com.dcsplab.vdui.util.css.BoxSizing;
import com.gst.context.GSContext;
import com.gst.domain.*;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.framework.components.GSOrgTreeGrid;
import com.gst.framework.components.GSOrgTreeGrid.TreeType;
import com.gst.service.GSAuthorizationService;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class GSRoleUserAssignment extends GSDetailFormBase {
  
  private static final long serialVersionUID = 8867656044540741595L;
  
  private static final Logger logger = LoggerFactory.getLogger(GSRoleUserAssignment.class);
  
  private final GSMember contextUser;
  
  private GSOrgTreeGrid orgTree;
  
  private VDGrid<GSOrganization> selectedOrg;
  
  public GSRoleUserAssignment() {
    contextUser = GSContext.getCurrentMember();
    setHideBasicInfo(true);
    initFields();
  }
  
  private void initFields() {
    VDLabel leftTitle = new VDLabel("公司人員清單");
    orgTree = new GSOrgTreeGrid(TreeType.PersonSelection);
    orgTree.setSelectionMode(SelectionMode.MULTI);
    
    VerticalLayout leftLayout = new VerticalLayout();
    leftLayout.setWidth("280px");
    leftLayout.add(leftTitle, orgTree);
    
    VDLabel rightTitle = new VDLabel("指派人員");
    selectedOrg = new VDGrid<>();
    selectedOrg.setSelectionMode(SelectionMode.MULTI);
    selectedOrg
      .addColumn(GSOrganization::getName)
      .setWidth("200px")
      .setFlexGrow(0)
      .setHeader("人員")
      .setSortable(false);
    VerticalLayout rightLayout = new VerticalLayout();
    rightLayout.setWidth("280px");
    rightLayout.add(rightTitle, selectedOrg);
    
    VDButton goRight = new VDButton();
    goRight.setIcon(VaadinIcon.CARET_RIGHT.create());
    goRight.addClickListener(e -> addAssignment());
    
    VDButton goLeft = new VDButton();
    goLeft.setIcon(VaadinIcon.CARET_LEFT.create());
    goLeft.addClickListener(e -> removeAssignment());
    
    VerticalLayout assignLayout = new VerticalLayout();
    assignLayout.add(goRight, goLeft);
    assignLayout.setHorizontalComponentAlignment(Alignment.CENTER, goRight, goLeft);
    HorizontalLayout center = new HorizontalLayout();
    center.setVerticalComponentAlignment(Alignment.CENTER, assignLayout);
    center.setWidth("60px");
    center.add(assignLayout);
    
    VDFlexBoxLayout content = new VDFlexBoxLayout(leftLayout, center, rightLayout);
    content.setFlexDirection(FlexDirection.ROW);
    content.setBoxSizing(BoxSizing.BORDER_BOX);
    content.setHeightFull();
    content.setMargin(Horizontal.XS);
    
    add(content);
  }
  
  private void addAssignment() {
    Set<GSOrganization> items = orgTree.getSelectedItems();
    selectedOrg.addItems(items);
    orgTree.deselectAll();
  }
  
  private void removeAssignment() {
    Set<GSOrganization> items = selectedOrg.getSelectedItems();
    selectedOrg.removeItems(items);
    selectedOrg.deselectAll();
  }
  
  @Override
  public String getDetailTitle() {
    return getHeader();
  }
  
  @Override
  public void setChangeHandler(GSFormChangeHandler handler) {
  }
  
  @Override
  public void onSave() {
    ArrayList<GSMember> personList = new ArrayList<>();
    
    Collection<GSOrganization> items = selectedOrg.getItems();
    for (GSOrganization obj : items) {
      if (obj instanceof GSDepartment) {
        GSDepartment dept = (GSDepartment) obj;
        personList.addAll(dept.getMemberList());
      } else {
        GSMember person = (GSMember) obj;
        personList.add(person);
      }
    }
    
    GSAuthorizationService authService = GSContext.getApplication().getAuthorizationService();
    GSRole role = (GSRole) getDetail();
    List<GSRoleAccess> accessList = role.getAccessList();
    ArrayList<GSAuthorization> authList = new ArrayList<>(accessList);
    
    for (GSMember person : personList) {
      try {
        person.addAccess(authList);
        authService.updateMemberAccess(contextUser, person);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  
  @Override
  public void onCancel() {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void onClose() {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void onDelete() {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public boolean isValueChanged() {
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public void populateDetail() {
    GSRole role = (GSRole) getDetail();
    String header = role.getId() + "-" + role.getName() + ": 指派成員";
    setHeader(header);
  }
}
