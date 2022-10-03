package com.gst.persistence.entity;

import com.gst.domain.GSMember;
import com.gst.persistence.GSDomainEntityBase;
import com.gst.persistence.key.GSMOCTA_ID;

import javax.persistence.*;
import java.util.Date;

/*
 * 未結案製令
 * 資料表名稱: MOCTA
 *
 * pk: COMPANY, TA001, TA002
 */

@Entity
@Table(name = "MOCTA")
@IdClass(GSMOCTA_ID.class)
public class GSMOCTA extends GSDomainEntityBase {
  
  private static final long serialVersionUID = -699348313900992506L;
  
  @Id
  @Column(name = "TA001", columnDefinition = "NCHAR(4)", nullable = false)
  private String TA001;
  
  @Id
  @Column(name = "TA002", columnDefinition = "NCHAR(11)", nullable = false)
  private String TA002;
  
  @Column(name = "TA003", columnDefinition = "NVARCHAR(8) default ''")
  private String TA003;
  
  @Column(name = "TA006", columnDefinition = "NVARCHAR(40) default ''")
  private String TA006;
  
  @Column(name = "TA007", columnDefinition = "NVARCHAR(6) default ''")
  private String TA007;
  
  @Column(name = "TA009", columnDefinition = "NVARCHAR(8) default ''")
  private String TA009;
  
  @Column(name = "TA010", columnDefinition = "NVARCHAR(8) default ''")
  private String TA010;
  
  @Column(name = "TA011", columnDefinition = "NVARCHAR(1) default ''")
  private String TA011;
  
  @Column(name = "TA012", columnDefinition = "NVARCHAR(8) default ''")
  private String TA012;
  
  @Column(name = "TA013", columnDefinition = "NVARCHAR(1) default ''")
  private String TA013;
  
  @Column(name = "TA014", columnDefinition = "NVARCHAR(8) default ''")
  private String TA014;
  
  @Column(name = "TA015", columnDefinition = "NUMERIC(16,3) default 0")
  private Double TA015;
  
  @Column(name = "TA016", columnDefinition = "NUMERIC(16,3) default 0")
  private Double TA016;
  
  @Column(name = "TA017", columnDefinition = "NUMERIC(16,3) default 0")
  private Double TA017;
  
  @Column(name = "TA018", columnDefinition = "NUMERIC(16,3) default 0")
  private Double TA018;
  
  @Column(name = "TA020", columnDefinition = "NVARCHAR(10)  default ''")
  private String TA020;
  
  @Column(name = "TA021", columnDefinition = "NVARCHAR(10)  default ''")
  private String TA021;
  
  @Column(name = "TA026", columnDefinition = "NVARCHAR(4)   default ''")
  private String TA026;
  
  @Column(name = "TA027", columnDefinition = "NVARCHAR(11)  default ''")
  private String TA027;
  
  @Column(name = "TA028", columnDefinition = "NVARCHAR(4)   default ''")
  private String TA028;
  
  @Column(name = "TA029", columnDefinition = "NVARCHAR(255) default ''")
  private String TA029;
  
  @Column(name = "TA032", columnDefinition = "NVARCHAR(10)  default ''")
  private String TA032;
  
  @Column(name = "TA034", columnDefinition = "NVARCHAR(120) default ''")
  private String TA034;
  
  @Column(name = "TA035", columnDefinition = "NVARCHAR(120) default ''")
  private String TA035;
  
  @Column(name = "TA038", columnDefinition = "NVARCHAR(1)   default ''")
  private String TA038;
  
  @Column(name = "TA039", columnDefinition = "NVARCHAR(1)   default ''")
  private String TA039;
  
  @Column(name = "TA040", columnDefinition = "NVARCHAR(8)   default ''")
  private String TA040;
  
  @Column(name = "TA044", columnDefinition = "NVARCHAR(1)   default ''")
  private String TA044;
  
  @Column(name = "TA045", columnDefinition = "NUMERIC(16,3) default 0")
  private Double TA045;
  
  @Column(name = "TA046", columnDefinition = "NUMERIC(16,3) default 0")
  private Double TA046;
  
  @Column(name = "TA047", columnDefinition = "NUMERIC(16,3) default 0")
  private Double TA047;
  
  @Column(name = "TA048", columnDefinition = "NVARCHAR(6) default ''")
  private String TA048;
  
  @Column(name = "TA051", columnDefinition = "NVARCHAR(40) default ''")
  private String TA051;
  
  @Column(name = "TA063", columnDefinition = "NVARCHAR(20) default ''")
  private String TA063;
  
  @Column(name = "TA092", columnDefinition = "NVARCHAR(1) default ''")
  private String TA092;
  
  @Column(name = "TA501", columnDefinition = "NUMERIC(4,0) default 999")
  private Double TA501;
  
  @Column(name = "TA502", columnDefinition = "DATETIME default null")
  @Temporal(TemporalType.TIMESTAMP)
  private Date TA502;
  
  @Column(name = "TA503", columnDefinition = "DATETIME default null")
  @Temporal(TemporalType.TIMESTAMP)
  private Date TA503;
  
  @Column(name = "TA504", columnDefinition = "DATETIME default null")
  @Temporal(TemporalType.TIMESTAMP)
  private Date TA504;
  
  @Transient
  private boolean urgent = false; // 急件
  
  @Transient
  private boolean urgentReported = false; // 急件且已報工
  
  public GSMOCTA() {
  }
  
  public GSMOCTA(GSMember creator) {
    super(creator);
  }
  
  public String getTA001() {
    return TA001;
  }
  
  public void setTA001(String tA001) {
    TA001 = tA001;
  }
  
  public String getTA002() {
    return TA002;
  }
  
  public void setTA002(String tA002) {
    TA002 = tA002;
  }
  
  public String getTA003() {
    return TA003;
  }
  
  public void setTA003(String tA003) {
    TA003 = tA003;
  }
  
  public String getTA006() {
    return TA006;
  }
  
  public void setTA006(String tA006) {
    TA006 = tA006;
  }
  
  public String getTA007() {
    return TA007;
  }
  
  public void setTA007(String tA007) {
    TA007 = tA007;
  }
  
  public String getTA009() {
    return TA009;
  }
  
  public void setTA009(String tA009) {
    TA009 = tA009;
  }
  
  public String getTA010() {
    return TA010;
  }
  
  public void setTA010(String tA010) {
    TA010 = tA010;
  }
  
  public String getTA011() {
    return TA011;
  }
  
  public void setTA011(String tA011) {
    TA011 = tA011;
  }
  
  public String getTA012() {
    return TA012;
  }
  
  public void setTA012(String tA012) {
    TA012 = tA012;
  }
  
  public String getTA013() {
    return TA013;
  }
  
  public void setTA013(String tA013) {
    TA013 = tA013;
  }
  
  public String getTA014() {
    return TA014;
  }
  
  public void setTA014(String tA014) {
    TA014 = tA014;
  }
  
  public Double getTA015() {
    return TA015;
  }
  
  public void setTA015(Double tA015) {
    TA015 = tA015;
  }
  
  public Double getTA016() {
    return TA016;
  }
  
  public void setTA016(Double tA016) {
    TA016 = tA016;
  }
  
  public Double getTA017() {
    return TA017;
  }
  
  public void setTA017(Double tA017) {
    TA017 = tA017;
  }
  
  public Double getTA018() {
    return TA018;
  }
  
  public void setTA018(Double tA018) {
    TA018 = tA018;
  }
  
  public String getTA020() {
    return TA020;
  }
  
  public void setTA020(String tA020) {
    TA020 = tA020;
  }
  
  public String getTA021() {
    return TA021;
  }
  
  public void setTA021(String tA021) {
    TA021 = tA021;
  }
  
  public String getTA026() {
    return TA026;
  }
  
  public void setTA026(String tA026) {
    TA026 = tA026;
  }
  
  public String getTA027() {
    return TA027;
  }
  
  public void setTA027(String tA027) {
    TA027 = tA027;
  }
  
  public String getTA028() {
    return TA028;
  }
  
  public void setTA028(String tA028) {
    TA028 = tA028;
  }
  
  public String getTA029() {
    return TA029;
  }
  
  public void setTA029(String tA029) {
    TA029 = tA029;
  }
  
  public String getTA032() {
    return TA032;
  }
  
  public void setTA032(String tA032) {
    TA032 = tA032;
  }
  
  public String getTA034() {
    return TA034;
  }
  
  public void setTA034(String tA034) {
    TA034 = tA034;
  }
  
  public String getTA035() {
    return TA035;
  }
  
  public void setTA035(String tA035) {
    TA035 = tA035;
  }
  
  public String getTA038() {
    return TA038;
  }
  
  public void setTA038(String tA038) {
    TA038 = tA038;
  }
  
  public String getTA039() {
    return TA039;
  }
  
  public void setTA039(String tA039) {
    TA039 = tA039;
  }
  
  public String getTA040() {
    return TA040;
  }
  
  public void setTA040(String tA040) {
    TA040 = tA040;
  }
  
  public String getTA044() {
    return TA044;
  }
  
  public void setTA044(String tA044) {
    TA044 = tA044;
    updateState();
  }
  
  public Double getTA045() {
    return TA045;
  }
  
  public void setTA045(Double tA045) {
    TA045 = tA045;
  }
  
  public Double getTA046() {
    return TA046;
  }
  
  public void setTA046(Double tA046) {
    TA046 = tA046;
  }
  
  public Double getTA047() {
    return TA047;
  }
  
  public void setTA047(Double tA047) {
    TA047 = tA047;
  }
  
  public String getTA048() {
    return TA048;
  }
  
  public void setTA048(String tA048) {
    TA048 = tA048;
  }
  
  public String getTA051() {
    return TA051;
  }
  
  public void setTA051(String tA051) {
    TA051 = tA051;
  }
  
  public String getTA063() {
    return TA063;
  }
  
  public void setTA063(String TA063) {
    this.TA063 = TA063;
  }
  
  public String getTA092() {
    return TA092;
  }
  
  public void setTA092(String tA092) {
    TA092 = tA092;
  }
  
  public Double getTA501() {
    return TA501;
  }
  
  public void setTA501(Double tA501) {
    TA501 = tA501;
  }
  
  public Date getTA502() {
    return TA502;
  }
  
  public void setTA502(Date tA502) {
    TA502 = tA502;
    updateState();
  }
  
  public Date getTA503() {
    return TA503;
  }
  
  public void setTA503(Date tA503) {
    TA503 = tA503;
  }
  
  public Date getTA504() {
    return TA504;
  }
  
  public void setTA504(Date tA504) {
    TA504 = tA504;
  }
  
  public boolean isUrgent() {
    return urgent;
  }
  
  public boolean isUrgentReported() {
    return urgentReported;
  }
  
  @PostLoad
  public void updateState() {
    urgent = "Y".equals(TA044.trim());
    urgentReported = urgent & (TA502 != null);
  }
}
