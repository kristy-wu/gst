package com.gst.domain;

import com.gst.context.GSContext;
import com.gst.service.GSAuthorizationService;
import com.gst.service.GSOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GSMember extends GSOrganization {
  private static final Logger logger = LoggerFactory.getLogger(GSMember.class);
  private static final long serialVersionUID = -5236523671786904551L;
  
  private boolean managerAccess;
  private String deptId;
  private String password;
  private Date activeDate;
  private Date obsoleteDate;
  
  private final GSOrganizationService orgService;
  
  private final List<GSMemberAccess> accessList;
  
  private List<GSDepartment> relatedDepartments;
  
  public GSMember(GSOrganizationService service) {
    this.orgService = service;
    accessList = new ArrayList<>();
    relatedDepartments = new ArrayList<>();
  }
  
  public boolean hasManagerAccess() {
    return managerAccess;
  }
  
  public void setManagerAccess(boolean hasAccess) {
    managerAccess = hasAccess;
  }
  
  public String getDeptId() {
    return deptId;
  }
  
  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public Date getActiveDate() {
    return activeDate;
  }
  
  public void setActiveDate(Date activeDate) {
    this.activeDate = activeDate;
  }
  
  public Date getObsoleteDate() {
    return obsoleteDate;
  }
  
  public void setObsoleteDate(Date obsoleteDate) {
    this.obsoleteDate = obsoleteDate;
  }
  
  public GSDepartment getMainDepartment() {
    if (getDeptId() == null || getDeptId().trim().isEmpty()) {
      return null;
    }
    return orgService.getDepartment(getCompanyId(), getDeptId());
  }
  
  public void setMainDepartment(GSDepartment dept) {
    if (dept != null) {
      setDeptId(dept.getId());
    }
  }
  
  public List<GSDepartment> getAssignments() {
    return orgService.getAssignedDepts(this);
  }
  
  public List<GSDepartment> getRelatedDepartments() {
    return relatedDepartments;
  }
  
  public void setRelatedDepartments(List<GSDepartment> relatedDepartments) {
    this.relatedDepartments = relatedDepartments;
  }
  
  public boolean hasAccess(String funcId, GSAuthorization.Action action) {
    GSAuthorizationService authService = GSContext.getApplication().getAuthorizationService();
    if (authService.hasAdministratorAccess(this)) {
      return true;
    }
    
    GSSystemFunction func = authService.getSystemFunction(getCompanyId(), funcId);
    return hasAccess(func, action);
  }
  
  public boolean hasAccess(GSSystemFunction func, GSAuthorization.Action action) {
    
    String funcId = func.getId().trim();
    
    GSAuthorizationService authService = GSContext.getApplication().getAuthorizationService();
    Iterator<GSMemberAccess> iter = authService.getMemberAccessList(this).iterator();
    GSMemberAccess funcAssigned = null;
    while (iter.hasNext()) {
      GSMemberAccess access = iter.next();
      String accessId = access.getFunctionId().trim();
      if (accessId.equals(funcId)) {
        funcAssigned = access;
        break;
      }
    }
    
    if (funcAssigned != null) {
      return funcAssigned.hasAccess(action);
    }
    
    logger.info("User[" + this.getName() + "] Function[" + func.getName() + "] access denied.");
    return false;
  }
  
  public void addAccess(List<GSAuthorization> funcList) throws Exception {
    for (GSAuthorization obj : funcList) {
      if (obj instanceof GSMemberAccess) {
        GSMemberAccess access = (GSMemberAccess) obj;
        accessList.add(access);
      } else if (obj instanceof GSRoleAccess) {
        accessList.add(GSMemberAccess.transition(this, obj));
      }
    }
  }
  
  public void clearAccess() {
    accessList.clear();
  }
  
  public void updateAccess(GSMember contextUser) {
    
    GSAuthorizationService authService = GSContext.getApplication().getAuthorizationService();
    try {
      authService.updateMemberAccess(contextUser, this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public List<GSMemberAccess> getAccessList() {
    return accessList;
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
    return new ArrayList<>();
  }
}
