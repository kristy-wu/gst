package com.gst.persistence.entity;

import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSMESAA_ID;

import javax.persistence.*;


/*
 * 製程資料建立作業(CMSI08)
 * 資料表名稱: MESAA
 *
 * pk:COMPANY, AA001, AA002,
 */

@Entity
@Table(name = "MESAA")
@IdClass(GSMESAA_ID.class)
public class GSMESAA extends GSDomainEntityBase {

    private static final long serialVersionUID = 3110800494453058351L;

    // 0001 製令單別
    @Id
    @Column(name = "AA001", columnDefinition = "NCHAR(4)", nullable = false)
    private String AA001;

    // 0002 製令單號
    @Id
    @Column(name = "AA002", columnDefinition = "NCHAR(11)", nullable = false)
    private String AA002;

    // 0003 開單日期
    @Column(name = "AA003", columnDefinition = "NVARCHAR(8)", nullable = false)
    private String AA003;

    // 0004 產品品號
    @Column(name = "AA006", columnDefinition = "NVARCHAR(40)")
    private String AA006;

    // 0005 單位
    @Column(name = "AA007", columnDefinition = "NVARCHAR(4)")
    private String AA007;

    // 0006 預計開工
    @Column(name = "AA009", columnDefinition = "NVARCHAR(8)")
    private String AA009;

    // 0007 預計完工
    @Column(name = "AA010", columnDefinition = "NVARCHAR(8)")
    private String AA010;

    // 0008 狀態碼
    @Column(name = "AA011", columnDefinition = "NVARCHAR(1)")
    private String AA011;

    // 0009 確認碼
    @Column(name = "AA013", columnDefinition = "NVARCHAR(1)")
    private String AA013;

    // 0010 預計產量
    @Column(name = "AA015", columnDefinition = "NUMERIC(16,3)")
    private Double AA015;

    // 0011 已生產量
    @Column(name = "AA017", columnDefinition = "NUMERIC(16,3)")
    private Double AA017;

    // 0012 報廢數量
    @Column(name = "AA018", columnDefinition = "NUMERIC(16,3)")
    private Double AA018;

    // 0013 入庫庫別
    @Column(name = "AA020", columnDefinition = "NVARCHAR(10)")
    private String AA020;

    // 0014 生產線別
    @Column(name = "AA021", columnDefinition = "NVARCHAR(10)")
    private String AA021;

    // 0015 母製令別
    @Column(name = "AA024", columnDefinition = "NVARCHAR(4)")
    private String AA024;

    // 0016 母製令編號
    @Column(name = "AA025", columnDefinition = "NVARCHAR(11)")
    private String AA025;

    // 0017 訂單單別
    @Column(name = "AA026", columnDefinition = "NVARCHAR(4)")
    private String AA026;

    // 0018 訂單單號
    @Column(name = "AA027", columnDefinition = "NVARCHAR(11)")
    private String AA027;

    // 0019 訂單序號
    @Column(name = "AA028", columnDefinition = "NVARCHAR(4)")
    private String AA028;

    // 0020 備註
    @Column(name = "AA029", columnDefinition = "NVARCHAR(255)")
    private String AA029;

    // 0021 計劃批號
    @Column(name = "AA033", columnDefinition = "NVARCHAR(20)")
    private String AA033;

    // 0022 產品品名
    @Column(name = "AA034", columnDefinition = "NVARCHAR(120)")
    private String AA034;

    // 0023 產品規格
    @Column(name = "AA035", columnDefinition = "NVARCHAR(120)")
    private String AA035;

    // 0024 預計開工
    @Column(name = "AA036", columnDefinition = "NVARCHAR(1)")
    private String AA036;

    // 0025 預計完工
    @Column(name = "AA037", columnDefinition = "NVARCHAR(1)")
    private String AA037;

    // 0026 確認日
    @Column(name = "AA040", columnDefinition = "NVARCHAR(8)")
    private String AA040;

    // 0027 急料
    @Column(name = "AA044", columnDefinition = "NVARCHAR(1)")
    private String AA044;

    // 0028 包裝單位
    @Column(name = "AA048", columnDefinition = "NVARCHAR(6)")
    private String AA048;

    // 0029 客戶品號
    @Column(name = "AA051", columnDefinition = "NVARCHAR(40)")
    private String AA051;

    // 0030 途程品號
    @Column(name = "AA501", columnDefinition = "NVARCHAR(40)")
    private String AA501;

    // 0031 產生途程碼
    @Column(name = "AA502", columnDefinition = "NVARCHAR(1)")
    private String AA502;

    public GSMESAA() {
    }

    public String getAA001() {
        return AA001;
    }

    public void setAA001(String AA001) {
        this.AA001 = AA001;
    }

    public String getAA002() {
        return AA002;
    }

    public void setAA002(String AA002) {
        this.AA002 = AA002;
    }

    public String getAA003() {
        return AA003;
    }

    public void setAA003(String AA003) {
        this.AA003 = AA003;
    }

    public String getAA006() {
        return AA006;
    }

    public void setAA006(String AA006) {
        this.AA006 = AA006;
    }

    public String getAA007() {
        return AA007;
    }

    public void setAA007(String AA007) {
        this.AA007 = AA007;
    }

    public String getAA009() {
        return AA009;
    }

    public void setAA009(String AA009) {
        this.AA009 = AA009;
    }

    public String getAA010() {
        return AA010;
    }

    public void setAA010(String AA010) {
        this.AA010 = AA010;
    }

    public String getAA011() {
        return AA011;
    }

    public void setAA011(String AA011) {
        this.AA011 = AA011;
    }

    public String getAA013() {
        return AA013;
    }

    public void setAA013(String AA013) {
        this.AA013 = AA013;
    }

    public Double getAA015() {
        return AA015;
    }

    public void setAA015(Double AA015) {
        this.AA015 = AA015;
    }

    public Double getAA017() {
        return AA017;
    }

    public void setAA017(Double AA017) {
        this.AA017 = AA017;
    }

    public Double getAA018() {
        return AA018;
    }

    public void setAA018(Double AA018) {
        this.AA018 = AA018;
    }

    public String getAA020() {
        return AA020;
    }

    public void setAA020(String AA020) {
        this.AA020 = AA020;
    }

    public String getAA021() {
        return AA021;
    }

    public void setAA021(String AA021) {
        this.AA021 = AA021;
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

    public String getAA026() {
        return AA026;
    }

    public void setAA026(String AA026) {
        this.AA026 = AA026;
    }

    public String getAA027() {
        return AA027;
    }

    public void setAA027(String AA027) {
        this.AA027 = AA027;
    }

    public String getAA028() {
        return AA028;
    }

    public void setAA028(String AA028) {
        this.AA028 = AA028;
    }

    public String getAA029() {
        return AA029;
    }

    public void setAA029(String AA029) {
        this.AA029 = AA029;
    }

    public String getAA033() {
        return AA033;
    }

    public void setAA033(String AA033) {
        this.AA033 = AA033;
    }

    public String getAA034() {
        return AA034;
    }

    public void setAA034(String AA034) {
        this.AA034 = AA034;
    }

    public String getAA035() {
        return AA035;
    }

    public void setAA035(String AA035) {
        this.AA035 = AA035;
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

    public String getAA040() {
        return AA040;
    }

    public void setAA040(String AA040) {
        this.AA040 = AA040;
    }

    public String getAA044() {
        return AA044;
    }

    public void setAA044(String AA044) {
        this.AA044 = AA044;
    }

    public String getAA048() {
        return AA048;
    }

    public void setAA048(String AA048) {
        this.AA048 = AA048;
    }

    public String getAA051() {
        return AA051;
    }

    public void setAA051(String AA051) {
        this.AA051 = AA051;
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
