package com.gst.persistence.entity;

import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSMESAA_ID;

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
    @Column(name = "AB004", columnDefinition = "NVARCHAR(4)", nullable = false)
    private String AB004;

    // 0008 預計開工日
    @Column(name = "AB008", columnDefinition = "NVARCHAR(8)")
    private String AB008;

    // 0009 預計完工日
    @Column(name = "AB009", columnDefinition = "NVARCHAR(8)")
    private String AB009;

    // 0010 投入數量
    @Column(name = "AB010", columnDefinition = "NUMERIC(16)")
    private String AB010;

    // 0011 完成數量
    @Column(name = "AB011", columnDefinition = "NUMERIC(16)")
    private String AB011;

    // 0012 報廢數量
    @Column(name = "AB012", columnDefinition = "NUMERIC(16,3)")
    private Double AB012;

    // 0013 重工投入
    @Column(name = "AB013", columnDefinition = "NUMERIC(16,3)")
    private Double AB013;

    // 0014 重工完成
    @Column(name = "AB014", columnDefinition = "NUMERIC(16,3)")
    private Double AB014;

    // 0015 撥轉數量
    @Column(name = "AB015", columnDefinition = "NUMERIC(16,3)")
    private Double AB015;

    // 0017 待轉數量
    @Column(name = "AB017", columnDefinition = "NUMERIC(16,3)")
    private Double AB017;

    ////////////

    // 0012 報廢數量
    @Column(name = "AB012", columnDefinition = "NUMERIC(16,3)")
    private Double AB012;

    // 0012 報廢數量
    @Column(name = "AB012", columnDefinition = "NUMERIC(16,3)")
    private Double AB012;

    // 0012 報廢數量
    @Column(name = "AB012", columnDefinition = "NUMERIC(16,3)")
    private Double AB012;

    // 0012 報廢數量
    @Column(name = "AB012", columnDefinition = "NUMERIC(16,3)")
    private Double AB012;





}
