package com.gst.persistence.entity;

import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSCMSDB_ID;

import javax.persistence.*;

/*
 * 公佈欄類別維護(CMS05)  單身
 * 資料表名稱: CMSDB
 *
 * pk: COMPANY, DB001, DB002
 */
@Entity
@Table(name = "CMSDB")
@IdClass(GSCMSDB_ID.class)
public class GSCMSDB extends GSDomainEntityBase {
  
  private static final long serialVersionUID = -5771640264393298828L;
  
  // 公佈欄類別代號
  @Id
  @Column(name = "DB001", columnDefinition = "NCHAR(20)", nullable = false)
  private String DB001;
  
  // 部門代號
  @Id
  @Column(name = "DB002", columnDefinition = "NVARCHAR(20)", nullable = false)
  private String DB002;
  
  public GSCMSDB() {
  }
  
  public String getDB001() {
    return DB001;
  }
  
  public void setDB001(String dB001) {
    DB001 = dB001;
  }
  
  public String getDB002() {
    return DB002;
  }
  
  public void setDB002(String dB002) {
    DB002 = dB002;
  }
}
