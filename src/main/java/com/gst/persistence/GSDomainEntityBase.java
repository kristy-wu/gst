package com.gst.persistence;

import com.dcsplab.common.DLDateUtils;
import com.dcsplab.common.DLDateUtils.Resolution;
import com.gst.context.GSAbstractObject;
import com.gst.domain.GSMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class GSDomainEntityBase implements Serializable {
  
  private static final Logger logger = LoggerFactory.getLogger(GSDomainEntityBase.class);
  
  private static final long serialVersionUID = -109088572278545615L;
  
  // 公司別代號
  @Id
  @Column(name = "COMPANY", columnDefinition = "NVARCHAR(20)", nullable = false)
  private String company;
  
  // 建立者代號
  @Column(name = "CREATOR", columnDefinition = "NVARCHAR(20) default null")
  private String creator;
  
  // 建立者部門
  @Column(name = "USR_GROUP", columnDefinition = "NVARCHAR(20) default null")
  private String userGroup;
  
  // 建立日期
  @Column(name = "CREATE_DATE", columnDefinition = "NVARCHAR(8) default null")
  private String createDate;
  
  // 修改者帳號
  @Column(name = "MODIFIER", columnDefinition = "NVARCHAR(20) default null")
  private String modifier;
  
  // 修改日期
  @Column(name = "MODI_DATE", columnDefinition = "NVARCHAR(8) default null")
  private String modifyDate;
  
  // 異動次數
  @Column(name = "FLAG", columnDefinition = "NUMERIC(3,0) default null")
  private Integer flag;
  
  // 建立時間
  @Column(name = "CREATE_TIME", columnDefinition = "NVARCHAR(8) default null")
  private String createTime;
  
  // 建立時電腦名稱
  @Column(name = "CREATE_AP", columnDefinition = "NVARCHAR(50) default null")
  private String createAP;
  
  // 建立時程式代號
  @Column(name = "CREATE_PRID", columnDefinition = "NVARCHAR(50) default null")
  private String createPRID;
  
  // 修改時間
  @Column(name = "MODI_TIME", columnDefinition = "NVARCHAR(8) default null")
  private String modifyTime;
  
  // 修改時電腦名稱
  @Column(name = "MODI_AP", columnDefinition = "NVARCHAR(50) default null")
  private String modifyAP;
  
  // 修改時程式代號
  @Column(name = "MODI_PRID", columnDefinition = "NVARCHAR(50) default null")
  private String modifyPRID;
  
  public GSDomainEntityBase() {
    setDefault();
  }
  
  public GSDomainEntityBase(GSMember creator) {
    setDefault();
    if (creator != null) {
      setCompany(creator.getCompanyId());
      setCreator(creator.getId());
      setUserGroup(creator.getUsrGroup());
      setModifier(creator.getId());
    }
  }
  
  private void setDefault() {
    company = null;
    creator = modifier = "";
    userGroup = "";
    createDate = modifyDate = DLDateUtils.format(Resolution.SimpleDate);
    flag = 0;
    createTime = modifyTime = DLDateUtils.format(Resolution.Time);
    createAP = modifyAP = createPRID = modifyPRID = "";
  }
  
  public void setBaseAttribute(GSAbstractObject object) {
    setCompany(object.getCompanyId());
    setCreator(object.getCreator());
    setUserGroup(object.getUsrGroup());
    setCreateDate(object.getCreateDate());
    setModifier(object.getModifier());
    setModifyDate(object.getModifyDate());
    setFlag(object.getFlag());
    setCreateTime(object.getCreateTime());
    setCreateAP(object.getCreateAP());
    setCreatePRID(object.getCreatePRID());
    setModifyTime(object.getModifyTime());
    setModifyAP(object.getModifyAP());
    setModifyPRID(object.getModifyPRID());
  }
  
  public void setModifiedBy(GSMember member) {
    String modifyDate = DLDateUtils.format(Resolution.SimpleDate);
    String modifyTime = DLDateUtils.format(Resolution.Time);
    
    setModifier(member.getId());
    setModifyDate(modifyDate);
    setModifyTime(modifyTime);
    setFlag(getFlag() + 1);
  }
  
  public String getCompany() {
    return company;
  }
  
  public void setCompany(String company) {
    this.company = company;
  }
  
  public String getCreator() {
    return creator;
  }
  
  public void setCreator(String creator) {
    this.creator = creator;
  }
  
  public String getUserGroup() {
    return userGroup;
  }
  
  public void setUserGroup(String userGroup) {
    this.userGroup = userGroup;
  }
  
  public String getCreateDate() {
    return createDate;
  }
  
  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }
  
  public String getModifier() {
    return modifier;
  }
  
  public void setModifier(String modifier) {
    this.modifier = modifier;
  }
  
  public String getModifyDate() {
    return modifyDate;
  }
  
  public void setModifyDate(String modifyDate) {
    this.modifyDate = modifyDate;
  }
  
  public int getFlag() {
    if (flag == null) return 1;
    return flag;
  }
  
  public void setFlag(int flag) {
    this.flag = flag;
  }
  
  public String getCreateTime() {
    return createTime;
  }
  
  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }
  
  public String getCreateAP() {
    return createAP;
  }
  
  public void setCreateAP(String createAP) {
    this.createAP = createAP;
  }
  
  public String getCreatePRID() {
    return createPRID;
  }
  
  public void setCreatePRID(String createPRID) {
    this.createPRID = createPRID;
  }
  
  public String getModifyTime() {
    return modifyTime;
  }
  
  public void setModifyTime(String modifyTime) {
    this.modifyTime = modifyTime;
  }
  
  public String getModifyAP() {
    return modifyAP;
  }
  
  public void setModifyAP(String modifyAP) {
    this.modifyAP = modifyAP;
  }
  
  public String getModifyPRID() {
    return modifyPRID;
  }
  
  public void setModifyPRID(String modifyPRID) {
    this.modifyPRID = modifyPRID;
  }
}
