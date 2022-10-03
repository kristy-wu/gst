package com.gst.persistence.entity;

import com.gst.domain.GSMember;
import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSCMSCB_ID;

import javax.persistence.*;

/*
 * 使用者資料維護(CMSI03)
 * 資料表名稱: CMSCB
 *
 * pk: COMPANY, CB001, CB002
 */
@Entity
@Table(name = "CMSCB")
@IdClass(GSCMSCB_ID.class)
public class GSCMSCB extends GSDomainEntityBase {
  
  private static final long serialVersionUID = 2548708562155474101L;
  
  /* 員工代號 */
  @Id
  @Column(name = "CB001", columnDefinition = "NCHAR(20)", nullable = false)
  private String CB001;
  
  /* 部門代號 */
  @Id
  @Column(name = "CB002", columnDefinition = "NVARCHAR(20)", nullable = false)
  private String CB002;
  
  public GSCMSCB() {
  }
  
  public GSCMSCB(GSMember creator) {
    super(creator);
  }
  
  public String getCB001() {
    return CB001;
  }
  
  public void setCB001(String cB001) {
    CB001 = cB001;
  }
  
  public String getCB002() {
    return CB002;
  }
  
  public void setCB002(String cB002) {
    CB002 = cB002;
  }
}
