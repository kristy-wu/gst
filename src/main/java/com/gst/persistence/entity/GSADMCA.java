package com.gst.persistence.entity;

import com.gst.domain.GSMember;
import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSADMCA_ID;

import javax.persistence.*;

/*
 * 角色資料維護(ADMI03)
 * 資料表名稱: ADMCA
 *
 * pk: COMPANY, CA001
 */
@Entity
@Table(name = "ADMCA")
@IdClass(GSADMCA_ID.class)
public class GSADMCA extends GSDomainEntityBase {
  
  private static final long serialVersionUID = -7315341299295732707L;
  
  // 角色代號
  @Id
  @Column(name = "CA001", columnDefinition = "NCHAR(10)", nullable = false)
  private String CA001;
  
  // 角色名稱
  @Column(name = "CA002", columnDefinition = "NVARCHAR(30) default ''")
  private String CA002;
  
  // 備註
  @Column(name = "CA003", columnDefinition = "NVARCHAR(255) default ''")
  private String CA003;
  
  public GSADMCA() {
  }
  
  public GSADMCA(GSMember creator) {
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
}
