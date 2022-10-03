package com.gst.persistence.entity;

import com.gst.domain.GSMember;
import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSCMSCA_ID;

import javax.persistence.*;
import java.util.Date;

/*
 * 使用者資料維護(CMSI03)
 * 資料表名稱: CMSCA
 *
 * pk: COMPANY, CA001
 */
@Entity
@Table(name = "CMSCA")
@IdClass(GSCMSCA_ID.class)
public class GSCMSCA extends GSDomainEntityBase {
  
  private static final long serialVersionUID = 3007001846148031156L;
  
  @Id
  @Column(name = "CA001", columnDefinition = "NCHAR(20)", nullable = false)
  private String CA001;
  
  @Column(name = "CA002", columnDefinition = "NVARCHAR(30) default ''")
  private String CA002;
  
  @Column(name = "CA003", columnDefinition = "NVARCHAR(20)", nullable = false)
  private String CA003;
  
  @Column(name = "CA004", columnDefinition = "VARCHAR(60)", nullable = false)
  private String CA004;
  
  @Column(name = "CA005", columnDefinition = "SMALLDATETIME default null")
  @Temporal(TemporalType.DATE)
  private Date CA005;
  
  @Column(name = "CA006", columnDefinition = "SMALLDATETIME", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date CA006;
  
  public GSCMSCA() {
  }
  
  public GSCMSCA(GSMember creator) {
    super(creator);
  }
  
  public String getCA001() {
    return CA001;
  }
  
  public void setCA001(String cA001) {
    CA001 = cA001;
  }
  
  public String getCA002() {
    return CA002;
  }
  
  public void setCA002(String cA002) {
    CA002 = cA002;
  }
  
  public String getCA003() {
    return CA003;
  }
  
  public void setCA003(String cA003) {
    CA003 = cA003;
  }
  
  public String getCA004() {
    return CA004;
  }
  
  public void setCA004(String cA004) {
    CA004 = cA004;
  }
  
  public Date getCA005() {
    return CA005;
  }
  
  public void setCA005(Date cA005) {
    CA005 = cA005;
  }
  
  public Date getCA006() {
    return CA006;
  }
  
  public void setCA006(Date cA006) {
    CA006 = cA006;
  }
}
