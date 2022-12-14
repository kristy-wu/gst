package com.gst.persistence.entity;

import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSMESAB_ID;

import javax.persistence.*;


/*
 * xxxxx(xxxx)
 * 資料表名稱: MESAB
 *
 * pk:COMPANY, AB001, AB002, AB004
 */

@Entity
@Table(name = "MESAB")
@IdClass(GSMESAB_ID.class)
public class GSMESAB extends GSDomainEntityBase {
    private static final long serialVersionUID = 5109098707159740588L;

    // 0001 製令單別
    @Id
    @Column(name = "AB001", columnDefinition = "NCHAR(4)", nullable = false)
    private String AB001;

    // 0002 製令單號
    @Id
    @Column(name = "AB002", columnDefinition = "NCHAR(11)", nullable = false)
    private String AB002;

    // 0003 加工順序
    @Column(name = "AB003", columnDefinition = "NCHAR(4)")
    private String AB003;

    // 0004 製程代號
    @Column(name = "AB004", columnDefinition = "NVARCHAR(4)")
    private String AB004;

    // 0008 預計開工日
    @Column(name = "AB008", columnDefinition = "NVARCHAR(8)")
    private String AB008;

    // 0009 預計完工日
    @Column(name = "AB009", columnDefinition = "NVARCHAR(8)")
    private String AB009;

    // 0010 投入數量
    @Column(name = "AB010", columnDefinition = "NUMERIC(16) default 0")
    private Double AB010;

    // 0011 完成數量
    @Column(name = "AB011", columnDefinition = "NUMERIC(16) default 0")
    private Double AB011;

    // 0012 報廢數量
    @Column(name = "AB012", columnDefinition = "NUMERIC(16,3) default 0")
    private Double AB012;

    // 0013 重工投入
    @Column(name = "AB013", columnDefinition = "NUMERIC(16,3) default 0")
    private Double AB013;

    // 0014 重工完成
    @Column(name = "AB014", columnDefinition = "NUMERIC(16,3) default 0")
    private Double AB014;

    // 0015 撥轉數量
    @Column(name = "AB015", columnDefinition = "NUMERIC(16,3) default 0")
    private Double AB015;

    // 0017 待轉數量
    @Column(name = "AB017", columnDefinition = "NUMERIC(16,3) default 0")
    private Double AB017;

    // 0022 標準人時(秒)
    @Column(name = "AB022", columnDefinition = "NUMERIC(8,0) default 0")
    private Double AB022;

    // 0023 實際人時(秒)
    @Column(name = "AB023", columnDefinition = "NUMERIC(8,0) default 0")
    private Double AB023;

    // 0024 製程敘述
    @Column(name = "AB024", columnDefinition = "NVARCHAR(255) default ''")
    private String AB024;

    // 0030 實際開工日
    @Column(name = "AB030", columnDefinition = "NVARCHAR(8)")
    private String AB030;

    // 0031 實際完工日
    @Column(name = "AB031", columnDefinition = "NVARCHAR(8)")
    private String AB031;

    // 0034 備註
    @Column(name = "AB034", columnDefinition = "NVARCHAR(255) default ''")
    private String AB034;

    // 0037 檢驗天數
    @Column(name = "AB037", columnDefinition = "NUMERIC(3,0) default 0")
    private Double AB037;

    // 0038 投入包裝數量
    @Column(name = "AB038", columnDefinition = "NUMERIC(16,0) default 0")
    private Double AB038;

    // 0039 完成包裝數量
    @Column(name = "AB039", columnDefinition = "NUMERIC(16,0) default 0")
    private Double AB039;

    // 0040 報廢包裝數量
    @Column(name = "AB040", columnDefinition = "NUMERIC(16,3) default 0")
    private Double AB040;

    // 0041 重工投入包裝
    @Column(name = "AB041", columnDefinition = "NUMERIC(16,3) default 0")
    private Double AB041;

    // 0042 重工完成包裝
    @Column(name = "AB042", columnDefinition = "NUMERIC(16,3) default 0")
    private Double AB042;

    // 0043 生產次數
    @Column(name = "AB501", columnDefinition = "NUMERIC(16,3) default 0")
    private Double AB501;

    public GSMESAB() {
    }

    public String getAB001() {
        return AB001;
    }

    public void setAB001(String AB001) {
        this.AB001 = AB001;
    }

    public String getAB002() {
        return AB002;
    }

    public void setAB002(String AB002) {
        this.AB002 = AB002;
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

    public String getAB034() {
        return AB034;
    }

    public void setAB034(String AB034) {
        this.AB034 = AB034;
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
