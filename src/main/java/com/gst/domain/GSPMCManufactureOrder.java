package com.gst.domain;

import com.gst.context.GSAbstractObject;
import com.gst.service.GSPMCService;

import java.util.Date;

/*
 製令: MOCTA
   key: companyId, TA001, TA002

 開工動作:
   1. 檢查 product line 是否存在已開工製令
   2. 設定目前開工時間 TA503 = now
   3. 2021/06/10  若實際開工日TA012空白，則寫入目前日期

 報工動作:  確認已寫入開工時間，否則無法報工
   1. 清除目前開工時間 TA503 = null
*/
public class GSPMCManufactureOrder extends GSAbstractObject {
  
  private static final long serialVersionUID = 9061898589013862533L;
  
  // 製令單別 TA001
  private String manufactureOrderTypeId;
  
  // 製令單號 TA002
  private String manufactureOrderId;
  
  // 開單日期 TA003
  private String orderCreateDate;
  
  // 產品品號 TA006
  private String partNumber;
  
  // 物料單位 TA007
  private String materialUnit;
  
  // 預計開工日 TA009
  private String estimateStartDate;
  
  // 預計完工日 TA010
  private String estimateFinishDate;
  
  // 狀態碼 TA011
  private String statusCode;
  
  // 實際開工日:(TA012)
  private String actualStartDate;
  
  // 確認碼:(TA013)
  private String checkCode;
  
  // ? TA014
  private String TA014;
  
  // 預計產量:(TA015)
  private Double estimateAmount;
  
  // 已改領套數:(TA016)
  private Double pickAmount;
  
  // 已生產數:(TA017)
  private Double finishedAmount;
  
  // 報廢數:(TA018)
  private Double obsoleteAmount;
  
  // 入庫庫別:(TA020)
  private String inventoryLocation;
  
  // 生產線別:(TA021)
  private String productLine;
  
  // 訂單單別:(TA026)
  private String salesOrderType;
  
  // 訂單單號:(TA027)
  private String salesOrderId;
  
  // 訂單序號:(TA028)
  private String salesOrderSeries;
  
  // 備註:(TA029)
  private String remark;
  
  // 加工廠商:(TA032)
  private String outsourcingVendor;
  
  // 產品品名:(TA034)
  private String productName;
  
  // 產品規格:(TA035)
  private String productSpec;
  
  // 實際開工:(TA038)
  private String TA038;
  
  // 實際完工:(TA039)
  private String TA039;
  
  // 確認日:(TA040)
  private String reviewDate;
  
  // 急料:(TA044)
  private boolean urgent;
  
  // 預計生產包裝量:(TA045)
  private Double estimatePackAmount;
  
  // 已生產包裝量:(TA046)
  private Double finishedPackAmount;
  
  // 報廢包裝量:(TA047)
  private Double obsoletePackAmount;
  
  // 包裝單位:(TA048)
  private String packingUnit;
  
  // 客戶品號:(TA051)
  private String customerPartNumber;
  
  // ?
  private String TA063;
  
  // 目前生產狀態 TA092
  private String currentStatus;
  
  // 生產順序 TA501
  private Double workingSequence;
  
  // 前次加工時間 TA502
  private Date lastStartTime;
  
  // 目前開工時間 TA503
  private Date startTime;
  
  // ?
  private Date TA504;
  
  // private final GSMember member;
  
  private final GSPMCService m_Service;
  
  public GSPMCManufactureOrder(GSPMCService service) {
    this.m_Service = service;
    // member = GSContext.getCurrentMember();
  }
  
  public String getManufactureOrderTypeId() {
    return manufactureOrderTypeId;
  }
  
  public void setManufactureOrderTypeId(String manufactureOrderTypeId) {
    this.manufactureOrderTypeId = manufactureOrderTypeId;
  }
  
  // 取得未審核數量
  public double getUnreviewedAmount() {
    return m_Service.getManufactureOrderUnreviewedAmount(this);
  }
  
  public String getManufactureOrderId() {
    return manufactureOrderId;
  }
  
  public void setManufactureOrderId(String manufactureOrderId) {
    this.manufactureOrderId = manufactureOrderId;
  }
  
  public String getOrderCreateDate() {
    return orderCreateDate;
  }
  
  public void setOrderCreateDate(String orderCreateDate) {
    this.orderCreateDate = orderCreateDate;
  }
  
  public String getPartNumber() {
    return partNumber;
  }
  
  public void setPartNumber(String partNumber) {
    this.partNumber = partNumber;
  }
  
  public String getMaterialUnit() {
    return materialUnit;
  }
  
  public void setMaterialUnit(String materialUnit) {
    this.materialUnit = materialUnit;
  }
  
  public String getEstimateStartDate() {
    return estimateStartDate;
  }
  
  public void setEstimateStartDate(String estimateStartDate) {
    this.estimateStartDate = estimateStartDate;
  }
  
  public String getEstimateFinishDate() {
    return estimateFinishDate;
  }
  
  public void setEstimateFinishDate(String estimateFinishDate) {
    this.estimateFinishDate = estimateFinishDate;
  }
  
  public String getStatusCode() {
    return statusCode;
  }
  
  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }
  
  public String getActualStartDate() {
    return actualStartDate;
  }
  
  public void setActualStartDate(String actualStartDate) {
    this.actualStartDate = actualStartDate;
  }
  
  public String getCheckCode() {
    return checkCode;
  }
  
  public void setCheckCode(String checkCode) {
    this.checkCode = checkCode;
  }
  
  public String getTA014() {
    return TA014;
  }
  
  public void setTA014(String TA014) {
    this.TA014 = TA014;
  }
  
  public Double getEstimateAmount() {
    return estimateAmount;
  }
  
  public void setEstimateAmount(Double estimateAmount) {
    this.estimateAmount = estimateAmount;
  }
  
  public Double getPickAmount() {
    return pickAmount;
  }
  
  public void setPickAmount(Double pickAmount) {
    this.pickAmount = pickAmount;
  }
  
  public Double getFinishedAmount() {
    return finishedAmount;
  }
  
  public void setFinishedAmount(Double finishedAmount) {
    this.finishedAmount = finishedAmount;
  }
  
  public Double getObsoleteAmount() {
    return obsoleteAmount;
  }
  
  public void setObsoleteAmount(Double obsoleteAmount) {
    this.obsoleteAmount = obsoleteAmount;
  }
  
  public String getInventoryLocation() {
    return inventoryLocation;
  }
  
  public void setInventoryLocation(String inventoryLocation) {
    this.inventoryLocation = inventoryLocation;
  }
  
  public String getProductLine() {
    return productLine;
  }
  
  public void setProductLine(String productLine) {
    this.productLine = productLine;
  }
  
  public String getSalesOrderType() {
    return salesOrderType;
  }
  
  public void setSalesOrderType(String salesOrderType) {
    this.salesOrderType = salesOrderType;
  }
  
  public String getSalesOrderId() {
    return salesOrderId;
  }
  
  public void setSalesOrderId(String salesOrderId) {
    this.salesOrderId = salesOrderId;
  }
  
  public String getSalesOrderSeries() {
    return salesOrderSeries;
  }
  
  public void setSalesOrderSeries(String salesOrderSeries) {
    this.salesOrderSeries = salesOrderSeries;
  }
  
  public String getRemark() {
    return remark;
  }
  
  public void setRemark(String remark) {
    this.remark = remark;
  }
  
  public String getOutsourcingVendor() {
    return outsourcingVendor;
  }
  
  public void setOutsourcingVendor(String outsourcingVendor) {
    this.outsourcingVendor = outsourcingVendor;
  }
  
  public String getProductName() {
    return productName;
  }
  
  public void setProductName(String productName) {
    this.productName = productName;
  }
  
  public String getProductSpec() {
    return productSpec;
  }
  
  public void setProductSpec(String productSpec) {
    this.productSpec = productSpec;
  }
  
  public String getTA038() {
    return TA038;
  }
  
  public void setTA038(String TA038) {
    this.TA038 = TA038;
  }
  
  public String getTA039() {
    return TA039;
  }
  
  public void setTA039(String TA039) {
    this.TA039 = TA039;
  }
  
  public String getReviewDate() {
    return reviewDate;
  }
  
  public void setReviewDate(String reviewDate) {
    this.reviewDate = reviewDate;
  }
  
  public boolean isUrgent() {
    return urgent;
  }
  
  public void setUrgent(boolean urgent) {
    this.urgent = urgent;
  }
  
  public boolean isUrgentReported() {
    return urgent && (lastStartTime != null);
  }
  
  public Double getEstimatePackAmount() {
    return estimatePackAmount;
  }
  
  public void setEstimatePackAmount(Double estimatePackAmount) {
    this.estimatePackAmount = estimatePackAmount;
  }
  
  public Double getFinishedPackAmount() {
    return finishedPackAmount;
  }
  
  public void setFinishedPackAmount(Double finishedPackAmount) {
    this.finishedPackAmount = finishedPackAmount;
  }
  
  public Double getObsoletePackAmount() {
    return obsoletePackAmount;
  }
  
  public void setObsoletePackAmount(Double obsoletePackAmount) {
    this.obsoletePackAmount = obsoletePackAmount;
  }
  
  public String getPackingUnit() {
    return packingUnit;
  }
  
  public void setPackingUnit(String packingUnit) {
    this.packingUnit = packingUnit;
  }
  
  public String getCustomerPartNumber() {
    return customerPartNumber;
  }
  
  public void setCustomerPartNumber(String customerPartNumber) {
    this.customerPartNumber = customerPartNumber;
  }
  
  public String getTA063() {
    return TA063;
  }
  
  public void setTA063(String TA063) {
    this.TA063 = TA063;
  }
  
  public String getCurrentStatus() {
    return currentStatus;
  }
  
  public void setCurrentStatus(String currentStatus) {
    this.currentStatus = currentStatus;
  }
  
  public Double getWorkingSequence() {
    return workingSequence;
  }
  
  public void setWorkingSequence(Double workingSequence) {
    this.workingSequence = workingSequence;
  }
  
  public Date getLastStartTime() {
    return lastStartTime;
  }
  
  public void setLastStartTime(Date lastStartTime) {
    this.lastStartTime = lastStartTime;
  }
  
  public Date getStartTime() {
    return startTime;
  }
  
  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }
  
  public Date getTA504() {
    return TA504;
  }
  
  public void setTA504(Date TA504) {
    this.TA504 = TA504;
  }
}
