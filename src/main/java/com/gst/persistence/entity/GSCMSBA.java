package com.gst.persistence.entity;

import com.gst.domain.GSMember;
import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSCMSBA_ID;

import javax.persistence.*;
import java.util.Date;

/*
 * 部門資料維護(CMSI02)
 * 資料表名稱: CMSBA
 *
 * pk: COMPANY, BA001
 */
@Entity
@Table(name = "CMSBA")
@IdClass(GSCMSBA_ID.class)
public class GSCMSBA extends GSDomainEntityBase {
  
  private static final long serialVersionUID = 2425149193174010032L;
  
  @Id
  @Column(name = "BA001", columnDefinition = "NCHAR(20)", nullable = false)
  private String BA001;
  
  @Column(name = "BA002", columnDefinition = "NVARCHAR(40) default ''")
  private String BA002;
  
  /*
   此線別最新報工時間，於製令報工時寫入
  */
  @Column(name = "BA905", columnDefinition = "DATETIME")
  private Date BA905;
  
  public GSCMSBA() {
  }
  
  public GSCMSBA(GSMember creator) {
    super(creator);
  }
  
  public String getBA001() {
    return BA001;
  }
  
  public void setBA001(String bA001) {
    BA001 = bA001;
  }
  
  public String getBA002() {
    return BA002;
  }
  
  public void setBA002(String bA002) {
    BA002 = bA002;
  }
  
  public Date getBA905() {
    return BA905;
  }
  
  public void setBA905(Date BA905) {
    this.BA905 = BA905;
  }
}
