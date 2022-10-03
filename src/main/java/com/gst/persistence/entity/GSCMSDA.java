package com.gst.persistence.entity;

import com.gst.domain.GSMember;
import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSCMSDA_ID;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/*
 * 公佈欄類別維護(CMS05)  單頭
 * 資料表名稱: CMSDA
 *
 * pk: COMPANY, DA001
 */
@Entity
@Table(name = "CMSDA")
@IdClass(GSCMSDA_ID.class)
public class GSCMSDA extends GSDomainEntityBase {
  
  private static final long serialVersionUID = -862149964456961517L;
  
  // 公佈欄類別代號
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GS_BULLETINTYPE_SEQ")
  @GenericGenerator(
    name = "GS_BULLETINTYPE_SEQ",
    strategy = "com.gst.persistence.idgen.GSBulletinClassIDGenerator")
  @Column(name = "DA001", columnDefinition = "NCHAR(20)", nullable = false)
  private String DA001;
  
  // 公佈欄類別名稱
  @Column(name = "DA002", columnDefinition = "NVARCHAR(40) default ''")
  private String DA002;
  
  public GSCMSDA() {
  }
  
  public GSCMSDA(GSMember creator) {
    super(creator);
  }
  
  public String getDA001() {
    return DA001;
  }
  
  public void setDA001(String dA001) {
    DA001 = dA001;
  }
  
  public String getDA002() {
    return DA002;
  }
  
  public void setDA002(String dA002) {
    DA002 = dA002;
  }
}
