package com.gst.persistence.entity;

import com.gst.domain.GSMember;
import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSCMSEA_ID;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;

/*
 * 公佈欄資料維護(CMS06)
 * 資料表名稱: CMSEA
 *
 * pk: COMPANY, EA001, EA002
 */

@Entity
@Table(name = "CMSEA")
@IdClass(GSCMSEA_ID.class)
public class GSCMSEA extends GSDomainEntityBase {
  
  private static final long serialVersionUID = -7093217386375494611L;
  
  @Id
  @Column(name = "EA001", columnDefinition = "NCHAR(20)", nullable = false)
  private String EA001;
  
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GS_BULLETINITEM_SEQ")
  @GenericGenerator(
    name = "GS_BULLETINITEM_SEQ",
    strategy = "com.gst.persistence.idgen.GSBulletinItemIDGenerator")
  @Column(name = "EA002", columnDefinition = "NVARCHAR(20)", nullable = false)
  private String EA002;
  
  @Column(name = "EA003", columnDefinition = "DATE", nullable = false)
  private Date EA003;
  
  @Column(name = "EA004", columnDefinition = "DATE", nullable = false)
  private Date EA004;
  
  @Column(name = "EA005", columnDefinition = "NVARCHAR(20)", nullable = false)
  private String EA005;
  
  public GSCMSEA() {
  }
  
  public GSCMSEA(GSMember creator) {
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
  
  public Date getEA003() {
    return EA003;
  }
  
  public void setEA003(Date eA003) {
    EA003 = eA003;
  }
  
  public Date getEA004() {
    return EA004;
  }
  
  public void setEA004(Date eA004) {
    EA004 = eA004;
  }
  
  public String getEA005() {
    return EA005;
  }
  
  public void setEA005(String eA005) {
    EA005 = eA005;
  }
}
