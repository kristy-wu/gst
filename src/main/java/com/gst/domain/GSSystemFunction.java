package com.gst.domain;

import com.gst.service.GSAuthorizationService;

public class GSSystemFunction extends GSAuthorization {
  
  private static final long serialVersionUID = 2254539965812636584L;
  
  private final GSAuthorizationService authService;
  
  private String type;
  private String module;
  private String remark;
  
  
  public GSSystemFunction(GSAuthorizationService service) {
    this.authService = service;
  }
  
  public String getType() {
    return type;
  }
  
  public void setType(String type) {
    this.type = type;
  }
  
  public String getModule() {
    return module;
  }
  
  public void setModule(String module) {
    this.module = module;
  }
  
  public String getRemark() {
    return remark;
  }
  
  public void setRemark(String remark) {
    this.remark = remark;
  }
  
  @Override
  public String toString() {
    return "System Function [" + getId() + ":" + getName() + "]";
  }
}
