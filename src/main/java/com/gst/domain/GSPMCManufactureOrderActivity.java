package com.gst.domain;

import com.gst.context.GSAbstractObject;
import com.gst.service.GSPMCService;

import java.util.Date;

/**
 * MESTA 生產入庫報工作業
 */
public class GSPMCManufactureOrderActivity extends GSAbstractObject {
  private static final long serialVersionUID = -280341908326250874L;
  
  // 報工部門 TA001
  private String deptId;
  
  // 報工人員 TA002
  private String memberId;
  
  // 製令單別 TA003
  private String manufactureOrderTypeId;
  
  // 製令單號 TA004
  private String manufactureOrderId;
  
  // 開工時間 TA005
  private Date startTime;
  
  // 完工時間 TA006
  private Date finishTime;
  
  // 無效工時(min) TA007  20201/04/21 --> 開工時間 - 前次報工時間
  private Double voidTime;
  
  // 報工數量 TA008
  private Double workingAmount;
  
  // 審核狀態 TA009
  private String reviewStatus;
  
  // 生產人數 TA010
  private Double workingHeadCount;
  
  // 報工包裝數量 TA011
  private Double packAmount;
  
  // 預設批號 TA012
  private String defaultLotNumber;
  
  private String TA501;
  private String TA502;
  private String TA503;
  
  // 無效工時類別 TA504
  private GSWorkType workType;
  
  // 關聯單據 TA490
  private String relatedManufactureOrder;
  
  private final GSPMCService m_Service;
  
  public GSPMCManufactureOrderActivity(GSPMCService service) {
    m_Service = service;
  }
  
  public String getDeptId() {
    return deptId;
  }
  
  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }
  
  public String getMemberId() {
    return memberId;
  }
  
  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }
  
  public String getManufactureOrderTypeId() {
    return manufactureOrderTypeId;
  }
  
  public void setManufactureOrderTypeId(String manufactureOrderTypeId) {
    this.manufactureOrderTypeId = manufactureOrderTypeId;
  }
  
  public String getManufactureOrderId() {
    return manufactureOrderId;
  }
  
  public void setManufactureOrderId(String manufactureOrderId) {
    this.manufactureOrderId = manufactureOrderId;
  }
  
  public Date getStartTime() {
    return startTime;
  }
  
  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }
  
  public Date getFinishTime() {
    return finishTime;
  }
  
  public void setFinishTime(Date finishTime) {
    this.finishTime = finishTime;
  }
  
  public Double getVoidTime() {
    return voidTime;
  }
  
  public void setVoidTime(Double voidTime) {
    this.voidTime = voidTime;
  }
  
  public Double getWorkingAmount() {
    return workingAmount;
  }
  
  public void setWorkingAmount(Double workingAmount) {
    this.workingAmount = workingAmount;
  }
  
  public String getReviewStatus() {
    return reviewStatus;
  }
  
  public void setReviewStatus(String reviewStatus) {
    this.reviewStatus = reviewStatus;
  }
  
  public Double getWorkingHeadCount() {
    return workingHeadCount;
  }
  
  public void setWorkingHeadCount(Double workingHeadCount) {
    this.workingHeadCount = workingHeadCount;
  }
  
  public Double getPackAmount() {
    return packAmount;
  }
  
  public void setPackAmount(Double packAmount) {
    this.packAmount = packAmount;
  }
  
  public String getDefaultLotNumber() {
    return defaultLotNumber;
  }
  
  public void setDefaultLotNumber(String defaultLotNumber) {
    this.defaultLotNumber = defaultLotNumber;
  }
  
  public String getTA501() {
    return TA501;
  }
  
  public void setTA501(String TA501) {
    this.TA501 = TA501;
  }
  
  public String getTA502() {
    return TA502;
  }
  
  public void setTA502(String TA502) {
    this.TA502 = TA502;
  }
  
  public String getTA503() {
    return TA503;
  }
  
  public void setTA503(String TA503) {
    this.TA503 = TA503;
  }
  
  public GSWorkType getWorkType() {
    return workType;
  }
  
  public void setWorkType(GSWorkType workType) {
    this.workType = workType;
  }
  
  public String getRelatedManufactureOrder() {
    return relatedManufactureOrder;
  }
  
  public void setRelatedManufactureOrder(String relatedManufactureOrder) {
    this.relatedManufactureOrder = relatedManufactureOrder;
  }
}
