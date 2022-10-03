package com.gst.persistence.entity;

import com.gst.domain.GSMember;
import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSCMSFA_ID;

import javax.persistence.*;

/*
 * 工時類別建立作業(CMS07)
 * 資料表名稱: CMSFA
 *
 * pk: COMPANY, FA001
 */
@Entity
@Table(name = "CMSFA")
@IdClass(GSCMSFA_ID.class)
public class GSCMSFA extends GSDomainEntityBase {
  
  private static final long serialVersionUID = 2765933148663754702L;
  
  // 工時代號
  @Id
  @Column(name = "FA001", columnDefinition = "NVARCHAR(20)", nullable = false)
  private String FA001;
  
  // 工時名稱
  @Column(name = "FA002", columnDefinition = "NVARCHAR(60)", nullable = false)
  private String FA002;
  
  // 工時名稱 - 英文
  @Column(name = "FA003", columnDefinition = "NVARCHAR(60)")
  private String FA003;
  
  public GSCMSFA() {
  }
  
  public GSCMSFA(GSMember creator) {
    super(creator);
  }
  
  public String getFA001() {
    return FA001;
  }
  
  public void setFA001(String fA001) {
    FA001 = fA001;
  }
  
  public String getFA002() {
    return FA002;
  }
  
  public void setFA002(String fA002) {
    FA002 = fA002;
  }
  
  public String getFA003() {
    return FA003;
  }
  
  public void setFA003(String FA003) {
    this.FA003 = FA003;
  }
}
