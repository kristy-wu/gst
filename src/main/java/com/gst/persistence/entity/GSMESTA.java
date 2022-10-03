package com.gst.persistence.entity;

import com.gst.domain.GSMember;
import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSMESTA_ID;

import javax.persistence.*;
import java.util.Date;

/*
 * 生產入庫報工作業()
 * 資料表名稱: MESTA
 *
 * pk: COMPANY, TA001, TA002, TA005
 */
@Entity
@Table(name = "MESTA")
@IdClass(GSMESTA_ID.class)
public class GSMESTA extends GSDomainEntityBase {
  
  private static final long serialVersionUID = 7494937325999680254L;
  
  // 報工部門
  @Id
  @Column(name = "TA001", columnDefinition = "NVARCHAR(10)", nullable = false)
  private String TA001;
  
  // 報工人員
  @Id
  @Column(name = "TA002", columnDefinition = "NVARCHAR(10)", nullable = false)
  private String TA002;
  
  // 製令單別
  @Column(name = "TA003", columnDefinition = "NCHAR(4)", nullable = false)
  private String TA003;
  
  // 製令單號
  @Column(name = "TA004", columnDefinition = "NCHAR(11)", nullable = false)
  private String TA004;
  
  // 開工時間
  @Id
  @Column(name = "TA005", columnDefinition = "DATE", nullable = false)
  private Date TA005;
  
  // 完工時間
  @Column(name = "TA006", columnDefinition = "DATE default NULL")
  private Date TA006;
  
  // 花費工時(min),  20201/04/21 --> 本次報工時間-前次報工時間
  @Column(name = "TA007", columnDefinition = "NUMERIC(5,0)", nullable = false)
  private Double TA007;
  
  // 報工數量
  @Column(name = "TA008", columnDefinition = "NUMERIC(16,0)", nullable = false)
  private Double TA008;
  
  // 審核狀態
  @Column(name = "TA009", columnDefinition = "NCHAR(1) default 'N'", nullable = false)
  private String TA009;
  
  // 生產人數
  @Column(name = "TA010", columnDefinition = "NUMERIC(5,2) default 0", nullable = false)
  private Double TA010;
  
  // 報工包裝數量
  @Column(name = "TA011", columnDefinition = "NUMERIC(16,0)", nullable = false)
  private Double TA011;
  
  // 預設批號
  @Column(name = "TA012", columnDefinition = "NCHAR(20)")
  private String TA012;
  
  @Column(name = "TA501", columnDefinition = "NCHAR(1) DEFAULT ''")
  private String TA501;
  
  @Column(name = "TA502", columnDefinition = "NCHAR(1) DEFAULT ''")
  private String TA502;
  
  @Column(name = "TA503", columnDefinition = "NCHAR(1)")
  private String TA503;
  
  // 工時代號
  @Column(name = "TA504", columnDefinition = "NCHAR(20)")
  private String TA504;
  
  // 關聯單據
  @Column(name = "TA490", columnDefinition = "NCHAR(13)")
  private String TA490;
  
  public GSMESTA() {
  }
  
  public GSMESTA(GSMember creator) {
    super(creator);
  }
  
  public String getTA490() {
    return TA490;
  }
  
  public void setTA490(String TA490) {
    this.TA490 = TA490;
  }
  
  public String getTA012() {
    return TA012;
  }
  
  public void setTA012(String TA012) {
    this.TA012 = TA012;
  }
  
  public String getTA001() {
    return TA001;
  }
  
  public void setTA001(String tA001) {
    TA001 = tA001;
  }
  
  public String getTA002() {
    return TA002;
  }
  
  public void setTA002(String tA002) {
    TA002 = tA002;
  }
  
  public String getTA003() {
    return TA003;
  }
  
  public void setTA003(String tA003) {
    TA003 = tA003;
  }
  
  public String getTA004() {
    return TA004;
  }
  
  public void setTA004(String tA004) {
    TA004 = tA004;
  }
  
  public Date getTA005() {
    return TA005;
  }
  
  public void setTA005(Date tA005) {
    TA005 = tA005;
  }
  
  public Date getTA006() {
    return TA006;
  }
  
  public void setTA006(Date tA006) {
    TA006 = tA006;
  }
  
  public Double getTA007() {
    return TA007;
  }
  
  public void setTA007(Double tA007) {
    TA007 = tA007;
  }
  
  public Double getTA008() {
    return TA008;
  }
  
  public void setTA008(Double tA008) {
    TA008 = tA008;
  }
  
  public String getTA009() {
    return TA009;
  }
  
  public void setTA009(String tA009) {
    TA009 = tA009;
  }
  
  public Double getTA010() {
    return TA010;
  }
  
  public void setTA010(Double tA010) {
    TA010 = tA010;
  }
  
  public Double getTA011() {
    return TA011;
  }
  
  public void setTA011(Double tA011) {
    TA011 = tA011;
  }
  
  public String getTA503() {
    return TA503;
  }
  
  public void setTA503(String TA503) {
    this.TA503 = TA503;
  }
  
  public String getTA504() {
    return TA504;
  }
  
  public void setTA504(String TA504) {
    this.TA504 = TA504;
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
}
