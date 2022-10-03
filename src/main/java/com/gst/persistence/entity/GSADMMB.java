package com.gst.persistence.entity;

import com.gst.domain.GSMember;
import com.gst.persistence.GSDomainEntityBase;

/*
 * 未使用
 * 資料表名稱: ADMMB
 *
 * pk: COMPANY, MB001, MB002
 */
// @Entity
// @Table(name = "ADMMB")
// @IdClass(GSADMMB_ID.class)
public class GSADMMB extends GSDomainEntityBase {
  
  private static final long serialVersionUID = 2350257565398499023L;
  
  // 角色代號
  // @Id
  // @Column(name = "MB001", columnDefinition = "NVARCHAR(10)", nullable = false)
  private String MB001;
  
  // 使用者代號
  // @Id
  // @Column(name = "MB002", columnDefinition = "NCHAR(20)", nullable = false)
  private String MB002;
  
  public GSADMMB() {
  }
  
  public GSADMMB(GSMember creator) {
    super(creator);
  }
  
  public String getMB001() {
    return MB001;
  }
  
  public void setMB001(String mB001) {
    MB001 = mB001;
  }
  
  public String getMB002() {
    return MB002;
  }
  
  public void setMB002(String mB002) {
    MB002 = mB002;
  }
}
