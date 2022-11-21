package com.gst.domain;

import com.gst.context.GSAbstractObject;

/*
   MESAB xxxxx
   key: companyId, AB001, AB002
*/

public class GSPMCManufactureOrderRoutingProcess extends GSAbstractObject {

    private static final long serialVersionUID = 8852352441199193309L;

    // 製令單別 AB001
    private String manufactureOrderTypeId;

    // 製令單號 AB002
    private String manufactureOrderId;

    // 加工順序 AB003
    private String AB003;

    // 製程代號 AB004
    private String AB004;

    // 預計開工日 AB008
    private String AB008;

    // 預計完工日 AB009
    private String AB009;

    // 投入數量 AB010
    private Double AB010;

    // 完成數量 AB011
    private Double AB011;

    // 報廢數量 AB012
    private Double AB012;

    // 重工投入 AB013
    private Double AB013;

    // 重工完成 AB014
    private Double AB014;

    // 撥轉數量 AB015
    private Double AB015;

    // 待轉數量 AB017
    private Double AB017;

    // 標準人時(秒) AB022
    private Double AB022;

    // 實際人時(秒) AB023
    private Double AB023;

    // 製程敘述 AB024
    private String AB024;

    // 實際開工日 AB030
    private String AB030;

    // 實際完工日 AB031
    private String AB031;

    // 備註 AB034
    private String remark;

    // 檢驗天數 AB037
    private Double AB037;

    // 投入包裝數量 AB038
    private Double AB038;

    // 完成包裝數量 AB039
    private Double AB039;

    // 報廢包裝數量 AB040
    private Double AB040;

    // 重工投入包裝 AB041
    private Double AB041;

    // 重工完成包裝 AB042
    private Double AB042;

    // 生產次數 AB501
    private Double AB501;

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

    public String getAB003() {
        return AB003;
    }

    public void setAB003(String AB003) {
        this.AB003 = AB003;
    }

    public String getAB004() {
        return AB004;
    }

    public void setAB004(String AB004) {
        this.AB004 = AB004;
    }

    public String getAB008() {
        return AB008;
    }

    public void setAB008(String AB008) {
        this.AB008 = AB008;
    }

    public String getAB009() {
        return AB009;
    }

    public void setAB009(String AB009) {
        this.AB009 = AB009;
    }

    public Double getAB010() {
        return AB010;
    }

    public void setAB010(Double AB010) {
        this.AB010 = AB010;
    }

    public Double getAB011() {
        return AB011;
    }

    public void setAB011(Double AB011) {
        this.AB011 = AB011;
    }

    public Double getAB012() {
        return AB012;
    }

    public void setAB012(Double AB012) {
        this.AB012 = AB012;
    }

    public Double getAB013() {
        return AB013;
    }

    public void setAB013(Double AB013) {
        this.AB013 = AB013;
    }

    public Double getAB014() {
        return AB014;
    }

    public void setAB014(Double AB014) {
        this.AB014 = AB014;
    }

    public Double getAB015() {
        return AB015;
    }

    public void setAB015(Double AB015) {
        this.AB015 = AB015;
    }

    public Double getAB017() {
        return AB017;
    }

    public void setAB017(Double AB017) {
        this.AB017 = AB017;
    }

    public Double getAB022() {
        return AB022;
    }

    public void setAB022(Double AB022) {
        this.AB022 = AB022;
    }

    public Double getAB023() {
        return AB023;
    }

    public void setAB023(Double AB023) {
        this.AB023 = AB023;
    }

    public String getAB024() {
        return AB024;
    }

    public void setAB024(String AB024) {
        this.AB024 = AB024;
    }

    public String getAB030() {
        return AB030;
    }

    public void setAB030(String AB030) {
        this.AB030 = AB030;
    }

    public String getAB031() {
        return AB031;
    }

    public void setAB031(String AB031) {
        this.AB031 = AB031;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getAB037() {
        return AB037;
    }

    public void setAB037(Double AB037) {
        this.AB037 = AB037;
    }

    public Double getAB038() {
        return AB038;
    }

    public void setAB038(Double AB038) {
        this.AB038 = AB038;
    }

    public Double getAB039() {
        return AB039;
    }

    public void setAB039(Double AB039) {
        this.AB039 = AB039;
    }

    public Double getAB040() {
        return AB040;
    }

    public void setAB040(Double AB040) {
        this.AB040 = AB040;
    }

    public Double getAB041() {
        return AB041;
    }

    public void setAB041(Double AB041) {
        this.AB041 = AB041;
    }

    public Double getAB042() {
        return AB042;
    }

    public void setAB042(Double AB042) {
        this.AB042 = AB042;
    }

    public Double getAB501() {
        return AB501;
    }

    public void setAB501(Double AB501) {
        this.AB501 = AB501;
    }
}
