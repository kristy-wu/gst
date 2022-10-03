package com.gst.persistence.entity;

import com.gst.domain.GSMember;
import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSADMBA_ID;

import javax.persistence.*;

/*
 * 程式資料維護(ADMI02)
 * 資料表名稱: ADMBA
 *
 * pk: COMPANY, BA001
 */
@Entity
@Table(name = "ADMBA")
@IdClass(GSADMBA_ID.class)
public class GSADMBA extends GSDomainEntityBase {
  
  private static final long serialVersionUID = 2707696409358852094L;
  
  // 程式代號
  @Id
  @Column(name = "BA001", columnDefinition = "NCHAR(20)", nullable = false)
  private String BA001;
  
  // 程式名稱
  @Column(name = "BA002", columnDefinition = "NVARCHAR(40) default ''")
  private String BA002;
  
  // 程式類型
  @Column(name = "BA003", columnDefinition = "NVARCHAR(1) default ''")
  private String BA003;
  
  // 模組代號
  @Column(name = "BA004", columnDefinition = "NVARCHAR(3) default ''")
  private String BA004;
  
  // 備註
  @Column(name = "BA005", columnDefinition = "NVARCHAR(255) default ''")
  private String BA005;
  
  public GSADMBA() {
  }
  
  public GSADMBA(GSMember creator) {
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
  
  public String getBA003() {
    return BA003;
  }
  
  public void setBA003(String bA003) {
    BA003 = bA003;
  }
  
  public String getBA004() {
    return BA004;
  }
  
  public void setBA004(String bA004) {
    BA004 = bA004;
  }
  
  public String getBA005() {
    return BA005;
  }
  
  public void setBA005(String bA005) {
    BA005 = bA005;
  }
}
