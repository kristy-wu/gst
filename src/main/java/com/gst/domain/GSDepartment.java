package com.gst.domain;

import com.gst.service.GSOrganizationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GSDepartment extends GSOrganization {
  
  private static final long serialVersionUID = -8965188704705671650L;
  
  /* 最新報工時間 */
  private Date lastFinishTime;
  
  private final GSOrganizationService orgService;
  
  public GSDepartment(GSOrganizationService service) {
    this.orgService = service;
  }
  
  public Date getLastFinishTime() {
    return lastFinishTime;
  }
  
  public void setLastFinishTime(Date lastFinishTime) {
    this.lastFinishTime = lastFinishTime;
  }
  
  public List<GSMember> getMemberList() {
    return orgService.getMemberList(this);
  }
  
  public int getMemberCount() {
    return orgService.getMemberCount(this);
  }
  
  @Override
  public long getChildCount() {
    return 0;
  }
  
  @Override
  public boolean hasChildren() {
    return false;
  }
  
  public List<GSOrganization> getChildren() {
    List<GSMember> list = getMemberList();
    return new ArrayList<>(list);
  }
}
