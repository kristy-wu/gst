package com.gst.persistence.entity;

import com.gst.domain.GSMember;
import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSADMAA_ID;

import javax.persistence.*;

/*
 * 模組資料維護(ADMI01)
 * 資料表名稱: ADMAA
 *
 * pk: COMPANY, AA001
 */
@Entity
@Table(name = "ADMAA")
@IdClass(GSADMAA_ID.class)
public class GSADMAA extends GSDomainEntityBase {
  
  private static final long serialVersionUID = -2440501025605684006L;
  
  @Id
  @Column(name = "AA001", columnDefinition = "NCHAR(3)", nullable = false)
  private String AA001;
  
  @Column(name = "AA002", columnDefinition = "NVARCHAR(40) default ''")
  private String AA002;
  
  @Column(name = "AA003", columnDefinition = "NVARCHAR(255) default ''")
  private String AA003;
  
  @Column(name = "AA004", columnDefinition = "NVARCHAR(1) default ''")
  private String AA004;
  
  @Column(name = "AA005", columnDefinition = "NVARCHAR(20) default ''")
  private String AA005;
  
  @Column(name = "AA006", columnDefinition = "NVARCHAR(20) default ''")
  private String AA006;
  
  @Column(name = "AA007", columnDefinition = "NVARCHAR(20) default ''")
  private String AA007;
  
  public GSADMAA() {
  }
  
  public GSADMAA(GSMember creator) {
    super(creator);
  }
  
  public String getAA001() {
    return AA001;
  }
  
  public void setAA001(String aA001) {
    AA001 = aA001;
  }
  
  public String getAA002() {
    return AA002;
  }
  
  public void setAA002(String aA002) {
    AA002 = aA002;
  }
  
  public String getAA003() {
    return AA003;
  }
  
  public void setAA003(String aA003) {
    AA003 = aA003;
  }
  
  public String getAA004() {
    return AA004;
  }
  
  public void setAA004(String aA004) {
    AA004 = aA004;
  }
  
  public String getAA005() {
    return AA005;
  }
  
  public void setAA005(String aA005) {
    AA005 = aA005;
  }
  
  public String getAA006() {
    return AA006;
  }
  
  public void setAA006(String aA006) {
    AA006 = aA006;
  }
  
  public String getAA007() {
    return AA007;
  }
  
  public void setAA007(String aA007) {
    AA007 = aA007;
  }
}
