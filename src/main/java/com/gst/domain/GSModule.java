package com.gst.domain;

import com.gst.context.GSAbstractObject;
import com.gst.service.GSAuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GSModule extends GSAbstractObject {
  private static final Logger logger = LoggerFactory.getLogger(GSModule.class);
  private static final long serialVersionUID = -7569581804679643864L;
  
  private String remark;
  
  private final GSAuthorizationService authService;
  
  public GSModule(GSAuthorizationService service) {
    this.authService = service;
  }
  
  public String getRemark() {
    return remark;
  }
  
  public void setRemark(String remark) {
    this.remark = remark;
  }
  
  public List<GSSystemFunction> getFunctionList() {
    return authService.getModuleFunctions(this);
  }
}
