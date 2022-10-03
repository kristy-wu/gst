package com.gst.persistence.entity;

import com.gst.domain.GSMember;
import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSADMEA_ID;

import javax.persistence.*;

/*
 * 使用者程式權限設定(ADMI05) 單頭
 * 資料表名稱: ADMEA
 *
 * pk: COMPANY, EA001
 */
@Entity
@Table(name = "ADMEA")
@IdClass(GSADMEA_ID.class)
public class GSADMEA extends GSDomainEntityBase {
  
  private static final long serialVersionUID = 6745053162976958209L;
  
  // 使用者代號
  @Id
  @Column(name = "EA001", columnDefinition = "NCHAR(10)", nullable = false)
  private String EA001;
  
  // 管理者權限
  @Column(name = "EA002", columnDefinition = "NVARCHAR(1) default 'N'")
  private String EA002;
  
  public GSADMEA() {
  }
  
  public GSADMEA(GSMember creator) {
    super(creator);
  }
  
  public String getEA001() {
    return EA001;
  }
  
  public void setEA001(String eA001) {
    EA001 = eA001;
  }
  
  public String getEA002() {
    return EA002;
  }
  
  public void setEA002(String eA002) {
    EA002 = eA002;
  }
}
