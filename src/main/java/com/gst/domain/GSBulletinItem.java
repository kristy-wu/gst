package com.gst.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.gst.context.GSAbstractObject;
import com.gst.service.GSBulletinService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class GSBulletinItem extends GSAbstractObject {
  
  private static final long serialVersionUID = -660883582526970457L;
  
  private String classId;
  
  private Date startDate;
  
  private Date endDate;
  
  @JsonFormat(pattern = "yyyy-MM-dd")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate startDateLocal;
  
  @JsonFormat(pattern = "yyyy-MM-dd")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate endDateLocal;
  
  private String content;
  
  @JsonCreator
  public GSBulletinItem() {
  }
  
  public GSBulletinItem(GSBulletinService service) {
  }
  
  public String getClassId() {
    return classId;
  }
  
  public void setClassId(String classId) {
    this.classId = classId;
  }
  
  public Date getStartDate() {
    return startDate;
  }
  
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
    startDateLocal = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }
  
  public LocalDate getStartDateLocal() {
    return startDateLocal;
    /*
    if (startDate == null) {
      return null;
    }
    return startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();*/
  }
  
  public void setStartDateLocal(LocalDate start) {
    startDateLocal = start;
    if (start != null) {
      startDate =
        java.util.Date.from(start.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    } else {
      startDate = null;
    }
  }
  
  public Date getEndDate() {
    return endDate;
  }
  
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
    endDateLocal = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }
  
  public LocalDate getEndDateLocal() {
    return endDateLocal;

    /*if (endDate == null) {
      return null;
    }
    return endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();*/
  }
  
  public void setEndDateLocal(LocalDate end) {
    endDateLocal = end;
    if (end != null) {
      endDate = java.util.Date.from(end.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    } else {
      endDate = null;
    }
  }
  
  public String getContent() {
    return content;
  }
  
  public void setContent(String content) {
    this.content = content;
  }
  
  @Override
  public String toString() {
    return getCompanyId() + "_" + getClassId() + "_" + getId();
  }
}
