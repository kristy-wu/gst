package com.gst.persistence.entity;

import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSADMSY_ID;

import javax.persistence.*;

/*
 * 系統資料設定檔
 * 資料表名稱: ADMSY
 *
 * pk: SY001
 */
@Entity
@Table(name = "ADMSY")
@IdClass(GSADMSY_ID.class)
public class GSADMSY extends GSDomainEntityBase {
  private static final long serialVersionUID = 2781109227221631170L;

  @Id
  @Column(name = "SY001", columnDefinition = "NCHAR(10)", nullable = false)
  private String SY001;

  @Column(name = "SY002", columnDefinition = "NCHAR(50)", nullable = false)
  private String SY002;

  @Column(name = "SY003", columnDefinition = "NVARCHAR(255)")
  private String SY003;

  public GSADMSY() {

  }

  public String getSY001() {
    return SY001;
  }

  public void setSY001(String SY001) {
    this.SY001 = SY001;
  }

  public String getSY002() {
    return SY002;
  }

  public void setSY002(String SY002) {
    this.SY002 = SY002;
  }

  public String getSY003() {
    return SY003;
  }

  public void setSY003(String SY003) {
    this.SY003 = SY003;
  }
}
