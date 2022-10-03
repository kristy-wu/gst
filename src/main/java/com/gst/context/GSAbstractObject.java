package com.gst.context;

import com.gst.persistence.GSDomainEntityBase;

import java.io.Serializable;

public abstract class GSAbstractObject implements GSDomainObject, Serializable {
  
  private static final long serialVersionUID = -1679212807736204306L;
  
  private String companyId;
  
  private String creator;
  
  private String usrGroup;
  
  private String createDate;
  
  private String createTime;
  
  private String modifier;
  
  private String modifyDate;
  
  private String modifyTime;
  
  private String createAP;
  
  private String createPRID;
  
  private String modifyAP;
  
  private String modifyPRID;
  
  private int flag;
  
  private String id;
  
  private String name;
  
  public GSAbstractObject() {
  }
  
  public void setBaseAttribute(GSDomainEntityBase base) {
    setCompanyId(base.getCompany());
    setCreator(base.getCreator());
    setUsrGroup(base.getUserGroup());
    setCreateDate(base.getCreateDate());
    setModifier(base.getModifier());
    setModifyDate(base.getModifyDate());
    setFlag(base.getFlag());
    setCreateTime(base.getCreateTime());
    setCreateAP(base.getCreateAP());
    setCreatePRID(base.getCreatePRID());
    setModifyTime(base.getModifyTime());
    setModifyAP(base.getModifyAP());
    setModifyPRID(base.getModifyPRID());
  }
  
  public String getId() {
    if (id != null) {
      return id.trim();
    }
    return null;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getCompanyId() {
    return companyId.trim();
  }
  
  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }
  
  public String getCreator() {
    return creator;
  }
  
  public void setCreator(String creator) {
    this.creator = creator;
  }
  
  public String getUsrGroup() {
    return usrGroup;
  }
  
  public void setUsrGroup(String usrGroup) {
    this.usrGroup = usrGroup;
  }
  
  public String getCreateDate() {
    return createDate;
  }
  
  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }
  
  public String getCreateTime() {
    return createTime;
  }
  
  public void setCreateTime(String createTime) {
    this.createTime = createTime;
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
  
  public String getModifyTime() {
    return modifyTime;
  }
  
  public void setModifyTime(String modifyTime) {
    this.modifyTime = modifyTime;
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
  
  public int getFlag() {
    return flag;
  }
  
  public void setFlag(int flag) {
    this.flag = flag;
  }
}
