package com.gst.persistence.entity;

import com.gst.domain.GSMember;
import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSADMEB_ID;

import javax.persistence.*;

/*
 * 使用者程式權限設定(ADMI05) 單身
 * 資料表名稱: ADMEB
 *
 * pk: COMPANY, EB001, EB002
 */
@Entity
@Table(name = "ADMEB")
@IdClass(GSADMEB_ID.class)
public class GSADMEB extends GSDomainEntityBase {
  
  private static final long serialVersionUID = 3970979150770288097L;
  
  // 使用者代號
  @Id
  @Column(name = "EB001", columnDefinition = "NCHAR(10)", nullable = false)
  private String EB001;
  
  // 程式代號
  @Id
  @Column(name = "EB002", columnDefinition = "NVARCHAR(20)", nullable = false)
  private String EB002;
  
  // 使用權限
  @Column(name = "EB003", columnDefinition = "NVARCHAR(7) default 'NNNNNNN'")
  private String EB003;
  
  // 備註
  @Column(name = "EB004", columnDefinition = "NVARCHAR(255) default ''")
  private String EB004;
  
  public GSADMEB() {
  }
  
  public GSADMEB(GSMember creator) {
    super(creator);
  }
  
  public String getEB001() {
    return EB001;
  }
  
  public void setEB001(String eB001) {
    EB001 = eB001;
  }
  
  public String getEB002() {
    return EB002;
  }
  
  public void setEB002(String eB002) {
    EB002 = eB002;
  }
  
  public String getEB003() {
    return EB003;
  }
  
  public void setEB003(String eB003) {
    EB003 = eB003;
  }
  
  public String getEB004() {
    return EB004;
  }
  
  public void setEB004(String eB004) {
    EB004 = eB004;
  }
}
