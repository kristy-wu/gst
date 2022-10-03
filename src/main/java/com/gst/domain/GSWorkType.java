package com.gst.domain;

import com.gst.context.GSAbstractObject;
import com.gst.service.GSPMCService;

public class GSWorkType extends GSAbstractObject {
  private static final long serialVersionUID = 2758313018640025085L;
  
  private String typeNameEng;
  
  private final GSPMCService service;
  
  public GSWorkType(GSPMCService service) {
    this.service = service;
  }
  
  public String getTypeNameEng() {
    return typeNameEng;
  }
  
  public void setTypeNameEng(String typeNameEng) {
    this.typeNameEng = typeNameEng;
  }
  
  public String getNameDisplay() {
    return this.getName() + "(" + this.getTypeNameEng() + ")";
  }
}
