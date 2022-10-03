package com.gst.persistence.entity;

import com.gst.domain.GSMember;
import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSADMDA_ID;

import javax.persistence.*;

/*
 * 角色程式設定(ADMI04)
 * 資料表名稱: ADMDA
 *
 * pk: COMPANY, DA001
 */
@Entity
@Table(name = "ADMDA")
@IdClass(GSADMDA_ID.class)
public class GSADMDA extends GSDomainEntityBase {
  
  private static final long serialVersionUID = -4153574393944156736L;
  
  // 角色代號
  @Id
  @Column(name = "DA001", columnDefinition = "NCHAR(10)", nullable = false)
  private String DA001;
  
  // 程式代號
  @Id
  @Column(name = "DA002", columnDefinition = "NVARCHAR(20) ", nullable = false)
  private String DA002;
  
  // 使用權限
  @Column(name = "DA003", columnDefinition = "NVARCHAR(7) default 'NNNNNNN'")
  private String DA003;
  
  // 備註
  @Column(name = "DA004", columnDefinition = "NVARCHAR(255) default ''")
  private String DA004;
  
  public GSADMDA() {
    setDA003("NNNNNNN");
  }
  
  public GSADMDA(GSMember creator) {
    super(creator);
    setDA003("NNNNNNN");
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
  
  public String getDA003() {
    return DA003;
  }
  
  public void setDA003(String dA003) {
    DA003 = dA003;
  }
  
  public String getDA004() {
    return DA004;
  }
  
  public void setDA004(String dA004) {
    DA004 = dA004;
  }
}
