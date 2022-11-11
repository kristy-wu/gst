package com.gst.persistence.entity;

import com.gst.domain.GSMember;
import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSCMSGA_ID;

import javax.persistence.*;

/*
 * 製程資料建立作業(CMSI08)
 * 資料表名稱: CMSGA
 *
 * pk:COMPANY, GA001
 */

@Entity
@Table(name = "CMSGA")
@IdClass(GSCMSGA_ID.class)
public class GSCMSGA extends GSDomainEntityBase {

  private static final long serialVersionUID = -9114234538403115094L;

  // 製程代號
  @Id
  @Column(name = "GA001", columnDefinition = "NVARCHAR(4)", nullable = false)
  private String GA001;

  // 製程名稱
  @Column(name = "GA002", columnDefinition = "NVARCHAR(40) default ''")
  private String GA002;

  // 製程敘述
  @Column(name = "GA003", columnDefinition = "NVARCHAR(255) default ''")
  private String GA003;

  // 性質
  @Column(name = "GA004", columnDefinition = "NVARCHAR(1) default ''")
  private String GA004;

  // 線別/廠商代號
  @Column(name = "GA005", columnDefinition = "NVARCHAR(10) default ''")
  private String GA005;

  // 發包人員
  @Column(name = "GA006", columnDefinition = "NVARCHAR(10) default ''")
  private String GA006;

  // 備註
  @Column(name = "GA007", columnDefinition = "NVARCHAR(255) default ''")
  private String GA007;

  // 固定前置天數
  @Column(name = "GA008", columnDefinition = "NUMERIC(3,0) default 0")
  private Integer GA008;

  // 變動前置天數
  @Column(name = "GA009", columnDefinition = "NUMERIC(3,0) default 0")
  private Integer GA009;

  // 批量
  @Column(name = "GA010", columnDefinition = "NUMERIC(16,3) default 0")
  private Double GA010;

  public GSCMSGA(GSMember creator) {
    super(creator);
  }

  public GSCMSGA() {

  }

  public String getGA001() {
    return GA001;
  }

  public void setGA001(String GA001) {
    this.GA001 = GA001;
  }

  public String getGA002() {
    return GA002;
  }

  public void setGA002(String GA002) {
    this.GA002 = GA002;
  }

  public String getGA003() {
    return GA003;
  }

  public void setGA003(String GA003) {
    this.GA003 = GA003;
  }

  public String getGA004() {
    return GA004;
  }

  public void setGA004(String GA004) {
    this.GA004 = GA004;
  }

  public String getGA005() {
    return GA005;
  }

  public void setGA005(String GA005) {
    this.GA005 = GA005;
  }

  public String getGA006() {
    return GA006;
  }

  public void setGA006(String GA006) {
    this.GA006 = GA006;
  }

  public String getGA007() {
    return GA007;
  }

  public void setGA007(String GA007) {
    this.GA007 = GA007;
  }

  public Integer getGA008() {
    return GA008;
  }

  public void setGA008(Integer GA008) {
    this.GA008 = GA008;
  }

  public Integer getGA009() {
    return GA009;
  }

  public void setGA009(Integer GA009) {
    this.GA009 = GA009;
  }

  public Double getGA010() {
    return GA010;
  }

  public void setGA010(Double GA010) {
    this.GA010 = GA010;
  }
}
