package com.gst.domain;

import com.gst.service.GSOrganizationService;

import java.util.ArrayList;
import java.util.List;

public class GSCompany extends GSOrganization {
  
  private static final long serialVersionUID = -5557075828135221609L;
  
  private String shortName;
  private String dbCategory;
  private String dbIP;
  private String dbLoginId;
  private String dbPassword;
  
  private final GSOrganizationService orgService;
  
  public GSCompany(GSOrganizationService service) {
    this.orgService = service;
  }
  
  public String getShortName() {
    return shortName;
  }
  
  public void setShortName(String shortName) {
    this.shortName = shortName;
  }
  
  public String getDbCategory() {
    return dbCategory;
  }
  
  public void setDbCategory(String dbCategory) {
    this.dbCategory = dbCategory;
  }
  
  public String getDbIP() {
    return dbIP;
  }
  
  public void setDbIP(String dbIP) {
    this.dbIP = dbIP;
  }
  
  public String getDbLoginId() {
    return dbLoginId;
  }
  
  public void setDbLoginId(String dbLoginId) {
    this.dbLoginId = dbLoginId;
  }
  
  public String getDbPassword() {
    return dbPassword;
  }
  
  public void setDbPassword(String dbPassword) {
    this.dbPassword = dbPassword;
  }
  
  public List<GSDepartment> getDepartmentList() {
    return orgService.getDepartmentList(this);
  }
  
  @Override
  public long getChildCount() {
    return getDepartmentList().size();
  }
  
  @Override
  public boolean hasChildren() {
    return getChildCount() > 0;
  }
  
  public List<GSOrganization> getChildren() {
    List<GSDepartment> depts = getDepartmentList();
    return new ArrayList<>(depts);
  }
}
