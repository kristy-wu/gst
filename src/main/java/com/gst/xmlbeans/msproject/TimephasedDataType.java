//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失.
// 產生時間: 2021.03.03 於 01:55:21 PM CST
//

package com.gst.xmlbeans.msproject;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
  name = "TimephasedDataType",
  propOrder = {"type", "uid", "start", "finish", "unit", "value"})
public class TimephasedDataType {
  
  @XmlElement(name = "Type")
  protected BigInteger type;
  
  @XmlElement(name = "UID", required = true)
  protected BigInteger uid;
  
  @XmlElement(name = "Start")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar start;
  
  @XmlElement(name = "Finish")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar finish;
  
  @XmlElement(name = "Unit")
  protected BigInteger unit;
  
  @XmlElement(name = "Value")
  protected String value;
  
  /**
   * 取得 type 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getType() {
    return type;
  }
  
  /**
   * 設定 type 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setType(BigInteger value) {
    this.type = value;
  }
  
  /**
   * 取得 uid 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getUID() {
    return uid;
  }
  
  /**
   * 設定 uid 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setUID(BigInteger value) {
    this.uid = value;
  }
  
  /**
   * 取得 start 特性的值.
   *
   * @return possible object is {@link XMLGregorianCalendar }
   */
  public XMLGregorianCalendar getStart() {
    return start;
  }
  
  /**
   * 設定 start 特性的值.
   *
   * @param value allowed object is {@link XMLGregorianCalendar }
   */
  public void setStart(XMLGregorianCalendar value) {
    this.start = value;
  }
  
  /**
   * 取得 finish 特性的值.
   *
   * @return possible object is {@link XMLGregorianCalendar }
   */
  public XMLGregorianCalendar getFinish() {
    return finish;
  }
  
  /**
   * 設定 finish 特性的值.
   *
   * @param value allowed object is {@link XMLGregorianCalendar }
   */
  public void setFinish(XMLGregorianCalendar value) {
    this.finish = value;
  }
  
  /**
   * 取得 unit 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getUnit() {
    return unit;
  }
  
  /**
   * 設定 unit 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setUnit(BigInteger value) {
    this.unit = value;
  }
  
  /**
   * 取得 value 特性的值.
   *
   * @return possible object is {@link String }
   */
  public String getValue() {
    return value;
  }
  
  /**
   * 設定 value 特性的值.
   *
   * @param value allowed object is {@link String }
   */
  public void setValue(String value) {
    this.value = value;
  }
}
