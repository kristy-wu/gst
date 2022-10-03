package com.gst.domain;

import com.gst.context.GSContext;
import com.gst.service.GSAuthorizationService;

public class GSRoleAccess extends GSAuthorization {
  
  private static final long serialVersionUID = 6335356871545602379L;
  
  private final GSAuthorizationService authService;
  
  private String roleId;
  
  private String functionId;
  
  private String accessString;
  
  private String remark;
  
  /*
    private GSADMDA_ID entityKey;

    private GSADMDA entity;
  */
  public GSRoleAccess(GSAuthorizationService service) {
    this.authService = service;
  }
  
  @Override
  public String getId() {
    return getRoleId() + "-" + getFunctionId();
  }
  
  public String getRoleId() {
    return roleId;
  }
  
  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }
  
  public String getFunctionId() {
    return functionId;
  }
  
  public void setFunctionId(String functionId) {
    this.functionId = functionId;
  }
  
  public String getAccessString() {
    return accessString;
  }
  
  public void setAccessString(String accessString) {
    this.accessString = accessString;
  }
  
  public String getRemark() {
    return remark;
  }
  
  public void setRemark(String remark) {
    this.remark = remark;
  }
  
  public void persist() {
    try {
      GSMember contextUser = GSContext.getCurrentMember();
      authService.updateRoleAccess(contextUser, this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static GSRoleAccess transition(GSRole role, GSSystemFunction func) throws Exception {
    GSAuthorizationService service = GSContext.getApplication().getAuthorizationService();
    return service.transitionFunctionAccess(role, func);
  }
}
