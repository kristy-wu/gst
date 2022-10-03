package com.gst.framework.org;

import com.dcsplab.vdui.components.VDButton;
import com.dcsplab.vdui.components.VDLabel;
import com.dcsplab.vdui.components.VDMultiSelectListBox;
import com.dcsplab.vdui.layout.VDFlexBoxLayout;
import com.dcsplab.vdui.layout.size.Horizontal;
import com.dcsplab.vdui.util.css.BoxSizing;
import com.gst.domain.GSDepartment;
import com.gst.domain.GSMember;
import com.gst.domain.GSOrganization;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.framework.components.GSOrgTreeGrid;
import com.gst.framework.components.GSOrgTreeGrid.TreeType;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class GSPersonnelSelectDept extends GSDetailFormBase {
  
  private static final long serialVersionUID = -8473344805739850027L;
  private static final Logger logger = LoggerFactory.getLogger(GSPersonnelSelectDept.class);
  
  private GSOrgTreeGrid deptList;
  
  private VDMultiSelectListBox<GSDepartment> selectedList;
  
  private GSFormChangeHandler handler;
  
  public GSPersonnelSelectDept() {
    setHideBasicInfo(true);
    initFields();
  }
  
  private void initFields() {
    VDLabel leftTitle = new VDLabel("公司部門清單");
    
    deptList = new GSOrgTreeGrid(TreeType.DeptOnly);
    deptList.setSelectionMode(SelectionMode.MULTI);
    deptList.setWidth("250px");
    deptList.setHeight("240px");
    
    VerticalLayout leftLayout = new VerticalLayout();
    leftLayout.add(leftTitle, deptList);
    
    VDLabel rightTitle = new VDLabel("已設定部門");
    selectedList = new VDMultiSelectListBox<>();
    selectedList.setWidth("250px");
    selectedList.setHeight("240px");
    selectedList.setRenderer(
      new ComponentRenderer<Label, GSDepartment>(
        item -> {
          VDLabel deptLabel = new VDLabel();
          deptLabel.setFontSize(12);
          String deptId = item.getId();
          String deptName = item.getName();
          deptLabel.setText(deptId + "-[" + deptName + "]");
          return deptLabel;
        }));
    
    VerticalLayout rightLayout = new VerticalLayout();
    rightLayout.add(rightTitle, selectedList);
    
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
    center.setWidthFull();
    center.setHeightFull();
    center.add(assignLayout);
    
    VDFlexBoxLayout content = new VDFlexBoxLayout(leftLayout, center, rightLayout);
    content.setFlexDirection(FlexDirection.ROW);
    content.setBoxSizing(BoxSizing.BORDER_BOX);
    content.setHeightFull();
    content.setMargin(Horizontal.XS);
    
    add(content);
  }
  
  private void addAssignment() {
    Set<GSOrganization> items = deptList.getSelectedItems();
    if (items.size() < 1) {
      return;
    }
    
    Collection<GSDepartment> currentList = selectedList.getList();
    
    ArrayList<GSDepartment> ary = new ArrayList<>();
    for (GSOrganization item : items) {
      GSDepartment dept = (GSDepartment) item;
      boolean existed = false;
      for (GSDepartment selectedDept : currentList) {
        if (dept.getId().equals(selectedDept.getId())) {
          existed = true;
          break;
        }
      }
      
      if (!existed) {
        ary.add(dept);
      }
    }
    
    selectedList.addItems(ary);
    deptList.deselectAll();
  }
  
  private void removeAssignment() {
    Set<GSDepartment> items = selectedList.getSelectedItems();
    selectedList.removeList(items);
    selectedList.deselectAll();
  }
  
  @Override
  public String getDetailTitle() {
    return getHeader();
  }
  
  @Override
  public void setChangeHandler(GSFormChangeHandler h) {
    handler = h;
  }
  
  @Override
  public void onSave() {
    GSPersonnelDetailForm detailForm = (GSPersonnelDetailForm) handler;
    Collection<GSDepartment> items = selectedList.getList();
    detailForm.updateDepartmentList(items);
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
  public boolean validate() {
    boolean pass = true;
    
    Collection<GSDepartment> items = selectedList.getList();
    if (items.size() < 1) {
      pass = false;
      Notification.show("必須設定至少一個部門!", 2000, Position.MIDDLE);
    }
    return pass;
  }
  
  @Override
  public boolean isValueChanged() {
    return false;
  }
  
  private void refreshAssignedDepartments() {
    GSPersonnelDetailForm personDetail = (GSPersonnelDetailForm) handler;
    Collection<GSDepartment> assignedDepts = personDetail.getAssignedDepartments();
    selectedList.setItems(assignedDepts);
  }
  
  @Override
  public void populateDetail() {
    GSMember member = (GSMember) getDetail();
    setHeader("指派部門: " + member.getId() + "-" + member.getName());
    refreshAssignedDepartments();
  }
}
