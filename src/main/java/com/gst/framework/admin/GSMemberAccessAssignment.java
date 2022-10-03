package com.gst.framework.admin;

import com.dcsplab.vdui.components.VDLabel;
import com.gst.context.GSContext;
import com.gst.domain.*;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.service.GSAuthorizationService;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class GSMemberAccessAssignment extends GSDetailFormBase {
  
  private static final long serialVersionUID = 6067269436777662427L;
  private static final Logger logger = LoggerFactory.getLogger(GSMemberAccessAssignment.class);
  
  private final GSMember member;
  
  private final Filter accessTypeFilter;
  
  private MultiSelectListBox<GSSystemFunction> systemFunctionBox;
  
  private GSFormChangeHandler handler;
  
  private final GSAuthorizationService authService;
  
  public GSMemberAccessAssignment(Filter filter) {
    this.setHideBasicInfo(true);
    this.accessTypeFilter = filter;
    authService = GSContext.getApplication().getAuthorizationService();
    member = GSContext.getCurrentMember();
  }
  
  private List<GSSystemFunction> getExistingFunctions() {
    List<GSSystemFunction> list = new ArrayList<>();
    
    if (accessTypeFilter == Filter.ForRole) {
      GSRoleAccessSetting parent = (GSRoleAccessSetting) handler;
      Collection<GSRoleAccess> currentFunctions = parent.getCurrentAccessList();
      for (GSRoleAccess roleAccess : currentFunctions) {
        GSSystemFunction systemFunction =
          authService.getSystemFunction(roleAccess.getCompanyId(), roleAccess.getFunctionId());
        list.add(systemFunction);
      }
    } else if (accessTypeFilter == Filter.ForUser) {
      GSMemberAccessSetting parent = (GSMemberAccessSetting) handler;
      Collection<GSMemberAccess> currentFunctions = parent.getCurrentAccessList();
      for (GSMemberAccess memberAccess : currentFunctions) {
        GSSystemFunction systemFunction =
          authService.getSystemFunction(
            memberAccess.getCompanyId(), memberAccess.getFunctionId());
        list.add(systemFunction);
      }
    }
    
    return list;
  }
  
  private void createFunctionList() {
    List<GSSystemFunction> assignedFunctions = getExistingFunctions();
    
    List<GSSystemFunction> functionList = authService.getSystemFunctionList(member.getCompanyId());
    Iterator<GSSystemFunction> functionIterator = functionList.iterator();
    while (functionIterator.hasNext()) {
      GSSystemFunction dbFunction = functionIterator.next();
      for (GSSystemFunction assignedFunction : assignedFunctions) {
        String assignedCompanyId = assignedFunction.getCompanyId();
        String assignedFunctionId = assignedFunction.getId();
        if (dbFunction.getCompanyId().equals(assignedCompanyId)
          && dbFunction.getId().equals(assignedFunctionId)) {
          functionIterator.remove();
          break;
        }
      }
    }
    
    systemFunctionBox = new MultiSelectListBox<>();
    systemFunctionBox.setSizeFull();
    systemFunctionBox.setItems(functionList);
    systemFunctionBox.setRenderer(
      new ComponentRenderer<>(
        item -> {
          String a = item.getId();
          String b = item.getName();
          VDLabel row = new VDLabel(b + "(" + a + ")");
          row.getStyle().set("font-size", "12px");
          return row;
        }));
    
    HorizontalLayout functionArea = new HorizontalLayout();
    functionArea.setHeight("300px");
    functionArea.setWidthFull();
    functionArea.add(systemFunctionBox);
    
    add(functionArea);
  }
  
  @Override
  public String getDetailTitle() {
    return "選擇系統功能";
  }
  
  @Override
  public void setChangeHandler(GSFormChangeHandler handler) {
    this.handler = handler;
  }
  
  @Override
  public void onSave() {
    if (accessTypeFilter == Filter.ForRole) {
      updateGSAdminRoleAccessForm();
    } else if (accessTypeFilter == Filter.ForUser) {
      updateGSAdminUserAccessForm();
    }
  }
  
  @Override
  public void onCancel() {
  }
  
  @Override
  public void onClose() {
  }
  
  @Override
  public void onDelete() {
  }
  
  @Override
  public boolean isValueChanged() {
    return false;
  }
  
  private void updateGSAdminRoleAccessForm() {
    Set<GSSystemFunction> selectedFunctions = systemFunctionBox.getSelectedItems();
    if (selectedFunctions.size() < 1) {
      return;
    }
    
    GSRole role = (GSRole) getDetail();
    ArrayList<GSRoleAccess> roleAccessList = new ArrayList<>();
    try {
      for (GSSystemFunction function : selectedFunctions) {
        GSRoleAccess access = GSRoleAccess.transition(role, function);
        roleAccessList.add(access);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    GSRoleAccessSetting parent = (GSRoleAccessSetting) handler;
    parent.addAccess(roleAccessList);
  }
  
  private void updateGSAdminUserAccessForm() {
    Set<GSSystemFunction> selectedFunctions = systemFunctionBox.getSelectedItems();
    if (selectedFunctions.size() < 1) {
      return;
    }
    
    GSMember user = (GSMember) getDetail();
    ArrayList<GSMemberAccess> newAccess = new ArrayList<>();
    try {
      for (GSSystemFunction function : selectedFunctions) {
        GSMemberAccess access = GSMemberAccess.transition(user, function);
        newAccess.add(access);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    GSMemberAccessSetting parent = (GSMemberAccessSetting) handler;
    parent.addAccess(newAccess);
  }
  
  @Override
  public void populateDetail() {
    createFunctionList();
  }
  
  @Override
  public Icon getDetailHeaderIcon() {
    return VaadinIcon.COGS.create();
  }
  
  public enum Filter {
    ForUser,
    ForRole
  }
}
