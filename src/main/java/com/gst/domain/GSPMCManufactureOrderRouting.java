package com.gst.domain;

import com.gst.context.GSAbstractObject;
import com.gst.service.GSPMCService;

/*
   MESAA xxxxx
   key: companyId, AA001, AA002
*/

public class GSPMCManufactureOrderRouting extends GSAbstractObject {

    private static final long serialVersionUID = -7183390946140691218L;

    // 製令單別 AA001
    private String manufactureOrderTypeId;

    // 製令單號 AA002
    private String manufactureOrderId;

    // 開單日期 AA003
    private String orderCreateDate;

    // 產品品號 AA006
    private String partNumber;

    // 單位 AA007
    private String materialUnit;

    // 預計開工 AA009
    private String estimateStartDate;

    // 預計完工 AA010
    private String estimateFinishDate;

    // 狀態碼 AA011
    private String statusCode;

    // 確認碼:AA013
    private String checkCode;

    // 預計產量:AA015
    private Double estimateAmount;

    // 已生產數:AA017
    private Double finishedAmount;

    // 報廢數:AA018
    private Double obsoleteAmount;

    // 入庫庫別:AA020
    private String inventoryLocation;

    // 生產線別:AA021
    private String productLine;

    // 母製令別:AA024
    private String AA024;

    // 母製令編號:AA025
    private String AA025;

    // 訂單單別:(AA026)
    private String salesOrderType;

    // 訂單單號:(AA027)
    private String salesOrderId;

    // 訂單序號:AA028
    private String salesOrderSeries;

    // 備註:(AA029)
    private String remark;

    // 備註:(AA033)
    private String AA033;

    // 產品品名:(AA034)
    private String productName;

    // 產品規格:(AA035)
    private String productSpec;

    // 預計開工:(AA036)
    private String AA036;

    // 預計完工:(AA037)
    private String AA037;

    // 確認日:(AA040)
    private String reviewDate;

    // 急料:(AA044)
    private boolean AA044;

    // 包裝單位:AA048
    private String packingUnit;

    // 客戶品號:AA051
    private String customerPartNumber;

    // 途程品號:AA501
    private String AA501;

    // 產生途程碼:AA502
    private String AA502;


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

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public Double getEstimateAmount() {
        return estimateAmount;
    }

    public void setEstimateAmount(Double estimateAmount) {
        this.estimateAmount = estimateAmount;
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

    public String getAA024() {
        return AA024;
    }

    public void setAA024(String AA024) {
        this.AA024 = AA024;
    }

    public String getAA025() {
        return AA025;
    }

    public void setAA025(String AA025) {
        this.AA025 = AA025;
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

    public String getAA033() {
        return AA033;
    }

    public void setAA033(String AA033) {
        this.AA033 = AA033;
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

    public String getAA036() {
        return AA036;
    }

    public void setAA036(String AA036) {
        this.AA036 = AA036;
    }

    public String getAA037() {
        return AA037;
    }

    public void setAA037(String AA037) {
        this.AA037 = AA037;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public boolean isAA044() {
        return AA044;
    }

    public void setAA044(boolean AA044) {
        this.AA044 = AA044;
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

    public String getAA501() {
        return AA501;
    }

    public void setAA501(String AA501) {
        this.AA501 = AA501;
    }

    public String getAA502() {
        return AA502;
    }

    public void setAA502(String AA502) {
        this.AA502 = AA502;
    }

}
