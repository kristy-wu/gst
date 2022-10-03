package com.gst.persistence.entity;

import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSINVMB_ID;

import javax.persistence.*;

@Entity
@Table(name = "INVMB")
@IdClass(GSINVMB_ID.class)
public class GSINVMB extends GSDomainEntityBase {

  private static final long serialVersionUID = 3880134362957730549L;

  @Id
  @Column(name = "MB001", columnDefinition = "NCHAR(40)", nullable = false)
  private String MB001;

  @Column(name = "MB002", columnDefinition = "NVARCHAR(120)")
  private String MB002;

  @Column(name = "MB003", columnDefinition = "NVARCHAR(120)")
  private String MB003;

  @Column(name = "MB004", columnDefinition = "NVARCHAR(6)")
  private String MB004;

  @Column(name = "MB005", columnDefinition = "NVARCHAR(6)")
  private String MB005;

  @Column(name = "MB006", columnDefinition = "NVARCHAR(6)")
  private String MB006;

  @Column(name = "MB007", columnDefinition = "NVARCHAR(6)")
  private String MB007;

  @Column(name = "MB008", columnDefinition = "NVARCHAR(6)")
  private String MB008;

  @Column(name = "MB009", columnDefinition = "NVARCHAR(255)")
  private String MB009;

  @Column(name = "MB030", columnDefinition = "NVARCHAR(8)")
  private String MB030;

  @Column(name = "MB031", columnDefinition = "NVARCHAR(8)")
  private String MB031;

  @Column(name = "MB032", columnDefinition = "NVARCHAR(10)")
  private String MB032;

  @Column(name = "MB048", columnDefinition = "NVARCHAR(4)")
  private String MB048;

  @Column(name = "MB049", columnDefinition = "NUMERIC(21,6)")
  private String MB049;

  @Column(name = "MB050", columnDefinition = "NUMERIC(21,6)")
  private String MB050;


  public GSINVMB() {

  }

  public String getMB001() {
    return MB001;
  }

  public void setMB001(String MB001) {
    this.MB001 = MB001;
  }

  public String getMB002() {
    return MB002;
  }

  public void setMB002(String MB002) {
    this.MB002 = MB002;
  }

  public String getMB003() {
    return MB003;
  }

  public void setMB003(String MB003) {
    this.MB003 = MB003;
  }

  public String getMB004() {
    return MB004;
  }

  public void setMB004(String MB004) {
    this.MB004 = MB004;
  }

  public String getMB005() {
    return MB005;
  }

  public void setMB005(String MB005) {
    this.MB005 = MB005;
  }

  public String getMB006() {
    return MB006;
  }

  public void setMB006(String MB006) {
    this.MB006 = MB006;
  }

  public String getMB007() {
    return MB007;
  }

  public void setMB007(String MB007) {
    this.MB007 = MB007;
  }

  public String getMB008() {
    return MB008;
  }

  public void setMB008(String MB008) {
    this.MB008 = MB008;
  }

  public String getMB009() {
    return MB009;
  }

  public void setMB009(String MB009) {
    this.MB009 = MB009;
  }

  public String getMB030() {
    return MB030;
  }

  public void setMB030(String MB030) {
    this.MB030 = MB030;
  }

  public String getMB031() {
    return MB031;
  }

  public void setMB031(String MB031) {
    this.MB031 = MB031;
  }

  public String getMB032() {
    return MB032;
  }

  public void setMB032(String MB032) {
    this.MB032 = MB032;
  }

  public String getMB048() {
    return MB048;
  }

  public void setMB048(String MB048) {
    this.MB048 = MB048;
  }

  public String getMB049() {
    return MB049;
  }

  public void setMB049(String MB049) {
    this.MB049 = MB049;
  }

  public String getMB050() {
    return MB050;
  }

  public void setMB050(String MB050) {
    this.MB050 = MB050;
  }
}
