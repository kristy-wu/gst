package com.gst.domain;

import com.gst.context.GSContext;
import com.gst.service.GSAuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GSMemberAccess extends GSAuthorization {
  
  private static final long serialVersionUID = -3896503888200682311L;
  private static final Logger logger = LoggerFactory.getLogger(GSMemberAccess.class);
  
  private String memberId;
  private String functionId;
  private String accessString;
  private String remark;
  
  private final GSAuthorizationService authService;
  
  public GSMemberAccess(GSAuthorizationService service) {
    this.authService = service;
  }
  
  public static GSMemberAccess transition(GSMember member, GSAuthorization auth) throws Exception {
    GSMember contextUser = GSContext.getCurrentMember();
    GSAuthorizationService service = GSContext.getApplication().getAuthorizationService();
    GSMemberAccess access = service.createMemberAccess(contextUser);
    access.setCompanyId(member.getCompanyId());
    access.setFlag(1);
    
    String acc = auth.buildAccString();
    access.populateAccess(acc);
    access.setMemberId(member.getId());
    
    String functionId;
    if (auth instanceof GSMemberAccess) {
      functionId = ((GSMemberAccess) auth).getFunctionId();
    } else if (auth instanceof GSSystemFunction) {
      functionId = auth.getId();
    } else {
      functionId = ((GSRoleAccess) auth).getFunctionId();
    }
    access.setFunctionId(functionId);
    access.setAccessString(acc);
    access.setRemark("");
    
    return access;
  }
  
  public String getMemberId() {
    return memberId;
  }
  
  public void setMemberId(String memberId) {
    this.memberId = memberId;
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
  
  @Override
  public String getId() {
    return getMemberId() + ":" + getFunctionId();
  }
  
  @Override
  public String getName() {
    return getId();
  }
}
