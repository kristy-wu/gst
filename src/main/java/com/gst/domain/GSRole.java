package com.gst.domain;

import com.gst.context.GSContext;
import com.gst.service.GSAuthorizationService;

import java.util.ArrayList;
import java.util.List;

public class GSRole extends GSAuthorization {
  
  private static final long serialVersionUID = -3865923105159845387L;
  private final GSAuthorizationService authService;
  
  private List<GSRoleAccess> accessList;
  
  private String remark;
  
  public GSRole(GSAuthorizationService service) {
    this.authService = service;
  }
  
  public void persist() {
    GSMember contextUser = GSContext.getCurrentMember();
    try {
      authService.updateRole(contextUser, this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public String getRemark() {
    return remark;
  }
  
  public void setRemark(String remark) {
    this.remark = remark;
  }
  
  public List<GSRoleAccess> getAccessList() {
    if (accessList == null) {
      accessList = authService.getRoleAccessList(this);
    }
    return accessList;
  }
  
  public void setAccessList(List<GSRoleAccess> list) {
    if (list != null) {
      accessList = list;
    }
  }
  
  private void mergeAccess(GSRoleAccess currentAccess, GSRoleAccess newAccess) {
    currentAccess.setCreateAccess(currentAccess.hasCreateAccess() || newAccess.hasCreateAccess());
    currentAccess.setSearchAccess(currentAccess.hasSearchAccess() || newAccess.hasSearchAccess());
    currentAccess.setModifyAccess(currentAccess.hasModifyAccess() || newAccess.hasModifyAccess());
    currentAccess.setDeleteAccess(currentAccess.hasDeleteAccess() || newAccess.hasDeleteAccess());
    currentAccess.setConfirmAccess(
      currentAccess.hasConfirmAccess() || newAccess.hasConfirmAccess());
    currentAccess.setCancelAccess(currentAccess.hasCancelAccess() || newAccess.hasCancelAccess());
    currentAccess.setObsoleteAccess(
      currentAccess.hasObsoleteAccess() || newAccess.hasObsoleteAccess());
  }
  
  public void addAccess(List<GSRoleAccess> assignedAccessList) {
    ArrayList<GSRoleAccess> newAccessList = new ArrayList<>();
    
    List<GSRoleAccess> currentAccessList = getAccessList();
    
    for (GSRoleAccess assignedAccess : assignedAccessList) {
      String assignedId = assignedAccess.getFunctionId();
      
      boolean merged = false;
      for (GSRoleAccess currentAccess : currentAccessList) {
        String currentId = currentAccess.getFunctionId();
        if (currentId.equals(assignedId)) {
          mergeAccess(currentAccess, assignedAccess);
          merged = true;
          break;
        }
      }
      
      if (!merged) {
        newAccessList.add(assignedAccess);
      }
    }
    
    newAccessList.addAll(currentAccessList);
    
    accessList.clear();
    accessList.addAll(newAccessList);
    updateAccess();
  }
  
  public void updateAccess() {
    try {
      GSMember contextUser = GSContext.getCurrentMember();
      authService.updateAccessListOfRole(contextUser, this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
