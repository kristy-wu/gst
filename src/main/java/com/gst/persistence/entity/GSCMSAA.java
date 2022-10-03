package com.gst.persistence.entity;

import com.gst.domain.GSMember;
import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSCMSAA_ID;

import javax.persistence.*;

/*
 * 公司基本資料維護(CMSI01)
 * 資料表名稱: CMSAA
 *
 * pk: COMPANY, AA001, AA004
 */
@Entity
@Table(name = "CMSAA")
@IdClass(GSCMSAA_ID.class)
public class GSCMSAA extends GSDomainEntityBase {
  
  private static final long serialVersionUID = -1289386405186751906L;
  
  /* 公司代號 */
  @Id
  @Column(name = "AA001", columnDefinition = "NCHAR(20)", nullable = false)
  private String AA001;
  
  /* 公司簡稱 */
  @Column(name = "AA002", columnDefinition = "NVARCHAR(20)", nullable = false)
  private String AA002;
  
  /* 公司全名 */
  @Column(name = "AA003", columnDefinition = "NVARCHAR(40) default ''")
  private String AA003;
  
  /* 資料庫代號 */
  @Id
  @Column(name = "AA004", columnDefinition = "NVARCHAR(20)", nullable = false)
  private String AA004;
  
  /* 資料庫IP */
  // @Column(name = "AA005", columnDefinition = "NVARCHAR(20)")
  // private String AA005;
  
  /* 資料庫登入帳號 */
  // @Column(name = "AA006", columnDefinition = "NVARCHAR(100)")
  // private String AA006;
  
  /* 資料庫登入密碼 */
  // @Column(name = "AA007", columnDefinition = "NVARCHAR(100)")
  // private String AA007;
  
  public GSCMSAA() {
  }
  
  public GSCMSAA(GSMember creator) {
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

  /*public String getAA005() {
    return AA005;
  }

  public void setAA005(String AA005) {
    this.AA005 = AA005;
  }

  public String getAA006() {
    return AA006;
  }

  public void setAA006(String AA006) {
    this.AA006 = AA006;
  }

  public String getAA007() {
    return AA007;
  }

  public void setAA007(String AA007) {
    this.AA007 = AA007;
  }*/
}
