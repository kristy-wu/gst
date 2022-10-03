package com.gst.persistence.entity;

import com.gst.domain.GSMember;
import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSADMMA_ID;

import javax.persistence.*;

/*
 * 未使用
 * 資料表名稱: ADMMA
 *
 * pk: COMPANY, AA001
 */
@Entity
@Table(name = "ADMMA")
@IdClass(GSADMMA_ID.class)
public class GSADMMA extends GSDomainEntityBase {
  
  private static final long serialVersionUID = 3162743337848428253L;
  
  @Id
  @Column(name = "AA001", columnDefinition = "NCHAR(3)", nullable = false)
  private String MA001;
  
  @Id
  @Column(name = "AA002", columnDefinition = "NVARCHAR(40) default ''")
  private String MA002;
  
  @Column(name = "AA003", columnDefinition = "NVARCHAR(255) default ''")
  private String MA003;
  
  @Column(name = "AA004", columnDefinition = "NVARCHAR(1) default ''")
  private String MA004;
  
  public GSADMMA() {
  }
  
  public GSADMMA(GSMember creator) {
    super(creator);
  }
  
  public String getMA001() {
    return MA001;
  }
  
  public void setMA001(String mA001) {
    MA001 = mA001;
  }
  
  public String getMA002() {
    return MA002;
  }
  
  public void setMA002(String mA002) {
    MA002 = mA002;
  }
  
  public String getMA003() {
    return MA003;
  }
  
  public void setMA003(String mA003) {
    MA003 = mA003;
  }
  
  public String getMA004() {
    return MA004;
  }
  
  public void setMA004(String mA004) {
    MA004 = mA004;
  }
}
