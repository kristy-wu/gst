package com.gst.persistence.entity;

import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSCMSMR_ID;

import javax.persistence.*;

@Entity
@Table(name = "CMSMR")
@IdClass(GSCMSMR_ID.class)
public class GSCMSMR extends GSDomainEntityBase {
  private static final long serialVersionUID = 6220763787561786347L;

  @Id
  @Column(name = "MR001", columnDefinition = "NCHAR(1)", nullable = false)
  private String MR001;

  @Id
  @Column(name = "MR002", columnDefinition = "NCHAR(6)", nullable = false)
  private String MR002;

  @Column(name = "MR003", columnDefinition = "NVARCHAR(30)")
  private String MR003;

  @Column(name = "MR004", columnDefinition = "NVARCHAR(80)")
  private String MR004;

  @Column(name = "MR005", columnDefinition = "NVARCHAR(255)")
  private String MR005;

  public GSCMSMR() {

  }

  public String getMR001() {
    return MR001;
  }

  public void setMR001(String MR001) {
    this.MR001 = MR001;
  }

  public String getMR002() {
    return MR002;
  }

  public void setMR002(String MR002) {
    this.MR002 = MR002;
  }

  public String getMR003() {
    return MR003;
  }

  public void setMR003(String MR003) {
    this.MR003 = MR003;
  }

  public String getMR004() {
    return MR004;
  }

  public void setMR004(String MR004) {
    this.MR004 = MR004;
  }

  public String getMR005() {
    return MR005;
  }

  public void setMR005(String MR005) {
    this.MR005 = MR005;
  }
}
