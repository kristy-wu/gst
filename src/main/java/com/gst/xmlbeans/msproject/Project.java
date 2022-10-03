//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation,
// v2.2.8-b130911.1802 所產生
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失.
// 產生時間: 2021.03.03 於 01:55:21 PM CST
//

package com.gst.xmlbeans.msproject;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
  name = "",
  propOrder = {
    "saveVersion",
    "uid",
    "name",
    "title",
    "subject",
    "category",
    "company",
    "manager",
    "author",
    "creationDate",
    "revision",
    "lastSaved",
    "scheduleFromStart",
    "startDate",
    "finishDate",
    "fyStartDate",
    "criticalSlackLimit",
    "currencyDigits",
    "currencySymbol",
    "currencyCode",
    "currencySymbolPosition",
    "calendarUID",
    "defaultStartTime",
    "defaultFinishTime",
    "minutesPerDay",
    "minutesPerWeek",
    "daysPerMonth",
    "defaultTaskType",
    "defaultFixedCostAccrual",
    "defaultStandardRate",
    "defaultOvertimeRate",
    "durationFormat",
    "workFormat",
    "editableActualCosts",
    "honorConstraints",
    "earnedValueMethod",
    "insertedProjectsLikeSummary",
    "multipleCriticalPaths",
    "newTasksEffortDriven",
    "newTasksEstimated",
    "splitsInProgressTasks",
    "spreadActualCost",
    "spreadPercentComplete",
    "taskUpdatesResource",
    "fiscalYearStart",
    "weekStartDay",
    "moveCompletedEndsBack",
    "moveRemainingStartsBack",
    "moveRemainingStartsForward",
    "moveCompletedEndsForward",
    "baselineForEarnedValue",
    "autoAddNewResourcesAndTasks",
    "statusDate",
    "currentDate",
    "microsoftProjectServerURL",
    "autolink",
    "newTaskStartDate",
    "defaultTaskEVMethod",
    "projectExternallyEdited",
    "extendedCreationDate",
    "actualsInSync",
    "removeFileProperties",
    "adminProject",
    "outlineCodes",
    "wbsMasks",
    "extendedAttributes",
    "calendars",
    "tasks",
    "resources",
    "assignments"
  })
@XmlRootElement(name = "Project")
public class Project {
  
  @XmlElement(name = "SaveVersion", required = true)
  protected BigInteger saveVersion;
  
  @XmlElement(name = "UID")
  protected String uid;
  
  @XmlElement(name = "Name")
  protected String name;
  
  @XmlElement(name = "Title")
  protected String title;
  
  @XmlElement(name = "Subject")
  protected String subject;
  
  @XmlElement(name = "Category")
  protected String category;
  
  @XmlElement(name = "Company")
  protected String company;
  
  @XmlElement(name = "Manager")
  protected String manager;
  
  @XmlElement(name = "Author")
  protected String author;
  
  @XmlElement(name = "CreationDate")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar creationDate;
  
  @XmlElement(name = "Revision")
  protected BigInteger revision;
  
  @XmlElement(name = "LastSaved")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar lastSaved;
  
  @XmlElement(name = "ScheduleFromStart", defaultValue = "true")
  protected Boolean scheduleFromStart;
  
  @XmlElement(name = "StartDate")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar startDate;
  
  @XmlElement(name = "FinishDate")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar finishDate;
  
  @XmlElement(name = "FYStartDate")
  protected BigInteger fyStartDate;
  
  @XmlElement(name = "CriticalSlackLimit")
  protected BigInteger criticalSlackLimit;
  
  @XmlElement(name = "CurrencyDigits")
  protected BigInteger currencyDigits;
  
  @XmlElement(name = "CurrencySymbol")
  protected String currencySymbol;
  
  @XmlElement(name = "CurrencyCode", required = true)
  protected String currencyCode;
  
  @XmlElement(name = "CurrencySymbolPosition")
  protected BigInteger currencySymbolPosition;
  
  @XmlElement(name = "CalendarUID")
  protected BigInteger calendarUID;
  
  @XmlElement(name = "DefaultStartTime")
  @XmlSchemaType(name = "time")
  protected XMLGregorianCalendar defaultStartTime;
  
  @XmlElement(name = "DefaultFinishTime")
  @XmlSchemaType(name = "time")
  protected XMLGregorianCalendar defaultFinishTime;
  
  @XmlElement(name = "MinutesPerDay")
  protected BigInteger minutesPerDay;
  
  @XmlElement(name = "MinutesPerWeek")
  protected BigInteger minutesPerWeek;
  
  @XmlElement(name = "DaysPerMonth")
  protected BigInteger daysPerMonth;
  
  @XmlElement(name = "DefaultTaskType", defaultValue = "1")
  protected BigInteger defaultTaskType;
  
  @XmlElement(name = "DefaultFixedCostAccrual")
  protected BigInteger defaultFixedCostAccrual;
  
  @XmlElement(name = "DefaultStandardRate")
  protected Float defaultStandardRate;
  
  @XmlElement(name = "DefaultOvertimeRate")
  protected Float defaultOvertimeRate;
  
  @XmlElement(name = "DurationFormat")
  protected BigInteger durationFormat;
  
  @XmlElement(name = "WorkFormat")
  protected BigInteger workFormat;
  
  @XmlElement(name = "EditableActualCosts", defaultValue = "false")
  protected Boolean editableActualCosts;
  
  @XmlElement(name = "HonorConstraints", defaultValue = "true")
  protected Boolean honorConstraints;
  
  @XmlElement(name = "EarnedValueMethod")
  protected BigInteger earnedValueMethod;
  
  @XmlElement(name = "InsertedProjectsLikeSummary", defaultValue = "true")
  protected Boolean insertedProjectsLikeSummary;
  
  @XmlElement(name = "MultipleCriticalPaths", defaultValue = "false")
  protected Boolean multipleCriticalPaths;
  
  @XmlElement(name = "NewTasksEffortDriven", defaultValue = "true")
  protected Boolean newTasksEffortDriven;
  
  @XmlElement(name = "NewTasksEstimated", defaultValue = "true")
  protected Boolean newTasksEstimated;
  
  @XmlElement(name = "SplitsInProgressTasks", defaultValue = "true")
  protected Boolean splitsInProgressTasks;
  
  @XmlElement(name = "SpreadActualCost", defaultValue = "true")
  protected Boolean spreadActualCost;
  
  @XmlElement(name = "SpreadPercentComplete", defaultValue = "false")
  protected Boolean spreadPercentComplete;
  
  @XmlElement(name = "TaskUpdatesResource")
  protected Boolean taskUpdatesResource;
  
  @XmlElement(name = "FiscalYearStart")
  protected Boolean fiscalYearStart;
  
  @XmlElement(name = "WeekStartDay")
  protected BigInteger weekStartDay;
  
  @XmlElement(name = "MoveCompletedEndsBack", defaultValue = "false")
  protected Boolean moveCompletedEndsBack;
  
  @XmlElement(name = "MoveRemainingStartsBack", defaultValue = "false")
  protected Boolean moveRemainingStartsBack;
  
  @XmlElement(name = "MoveRemainingStartsForward", defaultValue = "false")
  protected Boolean moveRemainingStartsForward;
  
  @XmlElement(name = "MoveCompletedEndsForward", defaultValue = "false")
  protected Boolean moveCompletedEndsForward;
  
  @XmlElement(name = "BaselineForEarnedValue")
  protected BigInteger baselineForEarnedValue;
  
  @XmlElement(name = "AutoAddNewResourcesAndTasks", defaultValue = "true")
  protected Boolean autoAddNewResourcesAndTasks;
  
  @XmlElement(name = "StatusDate")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar statusDate;
  
  @XmlElement(name = "CurrentDate")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar currentDate;
  
  @XmlElement(name = "MicrosoftProjectServerURL")
  protected Boolean microsoftProjectServerURL;
  
  @XmlElement(name = "Autolink")
  protected Boolean autolink;
  
  @XmlElement(name = "NewTaskStartDate")
  protected BigInteger newTaskStartDate;
  
  @XmlElement(name = "DefaultTaskEVMethod")
  protected BigInteger defaultTaskEVMethod;
  
  @XmlElement(name = "ProjectExternallyEdited")
  protected Boolean projectExternallyEdited;
  
  @XmlElement(name = "ExtendedCreationDate")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar extendedCreationDate;
  
  @XmlElement(name = "ActualsInSync")
  protected Boolean actualsInSync;
  
  @XmlElement(name = "RemoveFileProperties")
  protected Boolean removeFileProperties;
  
  @XmlElement(name = "AdminProject")
  protected Boolean adminProject;
  
  @XmlElement(name = "OutlineCodes")
  protected OutlineCodes outlineCodes;
  
  @XmlElement(name = "WBSMasks")
  protected WBSMasks wbsMasks;
  
  @XmlElement(name = "ExtendedAttributes")
  protected ExtendedAttributes extendedAttributes;
  
  @XmlElement(name = "Calendars")
  protected Calendars calendars;
  
  @XmlElement(name = "Tasks")
  protected Tasks tasks;
  
  @XmlElement(name = "Resources")
  protected Resources resources;
  
  @XmlElement(name = "Assignments")
  protected Assignments assignments;
  
  /**
   * 取得 saveVersion 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getSaveVersion() {
    return saveVersion;
  }
  
  /**
   * 設定 saveVersion 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setSaveVersion(BigInteger value) {
    this.saveVersion = value;
  }
  
  /**
   * 取得 uid 特性的值.
   *
   * @return possible object is {@link String }
   */
  public String getUID() {
    return uid;
  }
  
  /**
   * 設定 uid 特性的值.
   *
   * @param value allowed object is {@link String }
   */
  public void setUID(String value) {
    this.uid = value;
  }
  
  /**
   * 取得 name 特性的值.
   *
   * @return possible object is {@link String }
   */
  public String getName() {
    return name;
  }
  
  /**
   * 設定 name 特性的值.
   *
   * @param value allowed object is {@link String }
   */
  public void setName(String value) {
    this.name = value;
  }
  
  /**
   * 取得 title 特性的值.
   *
   * @return possible object is {@link String }
   */
  public String getTitle() {
    return title;
  }
  
  /**
   * 設定 title 特性的值.
   *
   * @param value allowed object is {@link String }
   */
  public void setTitle(String value) {
    this.title = value;
  }
  
  /**
   * 取得 subject 特性的值.
   *
   * @return possible object is {@link String }
   */
  public String getSubject() {
    return subject;
  }
  
  /**
   * 設定 subject 特性的值.
   *
   * @param value allowed object is {@link String }
   */
  public void setSubject(String value) {
    this.subject = value;
  }
  
  /**
   * 取得 category 特性的值.
   *
   * @return possible object is {@link String }
   */
  public String getCategory() {
    return category;
  }
  
  /**
   * 設定 category 特性的值.
   *
   * @param value allowed object is {@link String }
   */
  public void setCategory(String value) {
    this.category = value;
  }
  
  /**
   * 取得 company 特性的值.
   *
   * @return possible object is {@link String }
   */
  public String getCompany() {
    return company;
  }
  
  /**
   * 設定 company 特性的值.
   *
   * @param value allowed object is {@link String }
   */
  public void setCompany(String value) {
    this.company = value;
  }
  
  /**
   * 取得 manager 特性的值.
   *
   * @return possible object is {@link String }
   */
  public String getManager() {
    return manager;
  }
  
  /**
   * 設定 manager 特性的值.
   *
   * @param value allowed object is {@link String }
   */
  public void setManager(String value) {
    this.manager = value;
  }
  
  /**
   * 取得 author 特性的值.
   *
   * @return possible object is {@link String }
   */
  public String getAuthor() {
    return author;
  }
  
  /**
   * 設定 author 特性的值.
   *
   * @param value allowed object is {@link String }
   */
  public void setAuthor(String value) {
    this.author = value;
  }
  
  /**
   * 取得 creationDate 特性的值.
   *
   * @return possible object is {@link XMLGregorianCalendar }
   */
  public XMLGregorianCalendar getCreationDate() {
    return creationDate;
  }
  
  /**
   * 設定 creationDate 特性的值.
   *
   * @param value allowed object is {@link XMLGregorianCalendar }
   */
  public void setCreationDate(XMLGregorianCalendar value) {
    this.creationDate = value;
  }
  
  /**
   * 取得 revision 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getRevision() {
    return revision;
  }
  
  /**
   * 設定 revision 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setRevision(BigInteger value) {
    this.revision = value;
  }
  
  /**
   * 取得 lastSaved 特性的值.
   *
   * @return possible object is {@link XMLGregorianCalendar }
   */
  public XMLGregorianCalendar getLastSaved() {
    return lastSaved;
  }
  
  /**
   * 設定 lastSaved 特性的值.
   *
   * @param value allowed object is {@link XMLGregorianCalendar }
   */
  public void setLastSaved(XMLGregorianCalendar value) {
    this.lastSaved = value;
  }
  
  /**
   * 取得 scheduleFromStart 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isScheduleFromStart() {
    return scheduleFromStart;
  }
  
  /**
   * 設定 scheduleFromStart 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setScheduleFromStart(Boolean value) {
    this.scheduleFromStart = value;
  }
  
  /**
   * 取得 startDate 特性的值.
   *
   * @return possible object is {@link XMLGregorianCalendar }
   */
  public XMLGregorianCalendar getStartDate() {
    return startDate;
  }
  
  /**
   * 設定 startDate 特性的值.
   *
   * @param value allowed object is {@link XMLGregorianCalendar }
   */
  public void setStartDate(XMLGregorianCalendar value) {
    this.startDate = value;
  }
  
  /**
   * 取得 finishDate 特性的值.
   *
   * @return possible object is {@link XMLGregorianCalendar }
   */
  public XMLGregorianCalendar getFinishDate() {
    return finishDate;
  }
  
  /**
   * 設定 finishDate 特性的值.
   *
   * @param value allowed object is {@link XMLGregorianCalendar }
   */
  public void setFinishDate(XMLGregorianCalendar value) {
    this.finishDate = value;
  }
  
  /**
   * 取得 fyStartDate 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getFYStartDate() {
    return fyStartDate;
  }
  
  /**
   * 設定 fyStartDate 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setFYStartDate(BigInteger value) {
    this.fyStartDate = value;
  }
  
  /**
   * 取得 criticalSlackLimit 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getCriticalSlackLimit() {
    return criticalSlackLimit;
  }
  
  /**
   * 設定 criticalSlackLimit 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setCriticalSlackLimit(BigInteger value) {
    this.criticalSlackLimit = value;
  }
  
  /**
   * 取得 currencyDigits 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getCurrencyDigits() {
    return currencyDigits;
  }
  
  /**
   * 設定 currencyDigits 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setCurrencyDigits(BigInteger value) {
    this.currencyDigits = value;
  }
  
  /**
   * 取得 currencySymbol 特性的值.
   *
   * @return possible object is {@link String }
   */
  public String getCurrencySymbol() {
    return currencySymbol;
  }
  
  /**
   * 設定 currencySymbol 特性的值.
   *
   * @param value allowed object is {@link String }
   */
  public void setCurrencySymbol(String value) {
    this.currencySymbol = value;
  }
  
  /**
   * 取得 currencyCode 特性的值.
   *
   * @return possible object is {@link String }
   */
  public String getCurrencyCode() {
    return currencyCode;
  }
  
  /**
   * 設定 currencyCode 特性的值.
   *
   * @param value allowed object is {@link String }
   */
  public void setCurrencyCode(String value) {
    this.currencyCode = value;
  }
  
  /**
   * 取得 currencySymbolPosition 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getCurrencySymbolPosition() {
    return currencySymbolPosition;
  }
  
  /**
   * 設定 currencySymbolPosition 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setCurrencySymbolPosition(BigInteger value) {
    this.currencySymbolPosition = value;
  }
  
  /**
   * 取得 calendarUID 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getCalendarUID() {
    return calendarUID;
  }
  
  /**
   * 設定 calendarUID 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setCalendarUID(BigInteger value) {
    this.calendarUID = value;
  }
  
  /**
   * 取得 defaultStartTime 特性的值.
   *
   * @return possible object is {@link XMLGregorianCalendar }
   */
  public XMLGregorianCalendar getDefaultStartTime() {
    return defaultStartTime;
  }
  
  /**
   * 設定 defaultStartTime 特性的值.
   *
   * @param value allowed object is {@link XMLGregorianCalendar }
   */
  public void setDefaultStartTime(XMLGregorianCalendar value) {
    this.defaultStartTime = value;
  }
  
  /**
   * 取得 defaultFinishTime 特性的值.
   *
   * @return possible object is {@link XMLGregorianCalendar }
   */
  public XMLGregorianCalendar getDefaultFinishTime() {
    return defaultFinishTime;
  }
  
  /**
   * 設定 defaultFinishTime 特性的值.
   *
   * @param value allowed object is {@link XMLGregorianCalendar }
   */
  public void setDefaultFinishTime(XMLGregorianCalendar value) {
    this.defaultFinishTime = value;
  }
  
  /**
   * 取得 minutesPerDay 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getMinutesPerDay() {
    return minutesPerDay;
  }
  
  /**
   * 設定 minutesPerDay 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setMinutesPerDay(BigInteger value) {
    this.minutesPerDay = value;
  }
  
  /**
   * 取得 minutesPerWeek 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getMinutesPerWeek() {
    return minutesPerWeek;
  }
  
  /**
   * 設定 minutesPerWeek 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setMinutesPerWeek(BigInteger value) {
    this.minutesPerWeek = value;
  }
  
  /**
   * 取得 daysPerMonth 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getDaysPerMonth() {
    return daysPerMonth;
  }
  
  /**
   * 設定 daysPerMonth 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setDaysPerMonth(BigInteger value) {
    this.daysPerMonth = value;
  }
  
  /**
   * 取得 defaultTaskType 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getDefaultTaskType() {
    return defaultTaskType;
  }
  
  /**
   * 設定 defaultTaskType 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setDefaultTaskType(BigInteger value) {
    this.defaultTaskType = value;
  }
  
  /**
   * 取得 defaultFixedCostAccrual 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getDefaultFixedCostAccrual() {
    return defaultFixedCostAccrual;
  }
  
  /**
   * 設定 defaultFixedCostAccrual 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setDefaultFixedCostAccrual(BigInteger value) {
    this.defaultFixedCostAccrual = value;
  }
  
  /**
   * 取得 defaultStandardRate 特性的值.
   *
   * @return possible object is {@link Float }
   */
  public Float getDefaultStandardRate() {
    return defaultStandardRate;
  }
  
  /**
   * 設定 defaultStandardRate 特性的值.
   *
   * @param value allowed object is {@link Float }
   */
  public void setDefaultStandardRate(Float value) {
    this.defaultStandardRate = value;
  }
  
  /**
   * 取得 defaultOvertimeRate 特性的值.
   *
   * @return possible object is {@link Float }
   */
  public Float getDefaultOvertimeRate() {
    return defaultOvertimeRate;
  }
  
  /**
   * 設定 defaultOvertimeRate 特性的值.
   *
   * @param value allowed object is {@link Float }
   */
  public void setDefaultOvertimeRate(Float value) {
    this.defaultOvertimeRate = value;
  }
  
  /**
   * 取得 durationFormat 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getDurationFormat() {
    return durationFormat;
  }
  
  /**
   * 設定 durationFormat 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setDurationFormat(BigInteger value) {
    this.durationFormat = value;
  }
  
  /**
   * 取得 workFormat 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getWorkFormat() {
    return workFormat;
  }
  
  /**
   * 設定 workFormat 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setWorkFormat(BigInteger value) {
    this.workFormat = value;
  }
  
  /**
   * 取得 editableActualCosts 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isEditableActualCosts() {
    return editableActualCosts;
  }
  
  /**
   * 設定 editableActualCosts 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setEditableActualCosts(Boolean value) {
    this.editableActualCosts = value;
  }
  
  /**
   * 取得 honorConstraints 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isHonorConstraints() {
    return honorConstraints;
  }
  
  /**
   * 設定 honorConstraints 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setHonorConstraints(Boolean value) {
    this.honorConstraints = value;
  }
  
  /**
   * 取得 earnedValueMethod 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getEarnedValueMethod() {
    return earnedValueMethod;
  }
  
  /**
   * 設定 earnedValueMethod 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setEarnedValueMethod(BigInteger value) {
    this.earnedValueMethod = value;
  }
  
  /**
   * 取得 insertedProjectsLikeSummary 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isInsertedProjectsLikeSummary() {
    return insertedProjectsLikeSummary;
  }
  
  /**
   * 設定 insertedProjectsLikeSummary 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setInsertedProjectsLikeSummary(Boolean value) {
    this.insertedProjectsLikeSummary = value;
  }
  
  /**
   * 取得 multipleCriticalPaths 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isMultipleCriticalPaths() {
    return multipleCriticalPaths;
  }
  
  /**
   * 設定 multipleCriticalPaths 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setMultipleCriticalPaths(Boolean value) {
    this.multipleCriticalPaths = value;
  }
  
  /**
   * 取得 newTasksEffortDriven 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isNewTasksEffortDriven() {
    return newTasksEffortDriven;
  }
  
  /**
   * 設定 newTasksEffortDriven 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setNewTasksEffortDriven(Boolean value) {
    this.newTasksEffortDriven = value;
  }
  
  /**
   * 取得 newTasksEstimated 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isNewTasksEstimated() {
    return newTasksEstimated;
  }
  
  /**
   * 設定 newTasksEstimated 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setNewTasksEstimated(Boolean value) {
    this.newTasksEstimated = value;
  }
  
  /**
   * 取得 splitsInProgressTasks 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isSplitsInProgressTasks() {
    return splitsInProgressTasks;
  }
  
  /**
   * 設定 splitsInProgressTasks 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setSplitsInProgressTasks(Boolean value) {
    this.splitsInProgressTasks = value;
  }
  
  /**
   * 取得 spreadActualCost 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isSpreadActualCost() {
    return spreadActualCost;
  }
  
  /**
   * 設定 spreadActualCost 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setSpreadActualCost(Boolean value) {
    this.spreadActualCost = value;
  }
  
  /**
   * 取得 spreadPercentComplete 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isSpreadPercentComplete() {
    return spreadPercentComplete;
  }
  
  /**
   * 設定 spreadPercentComplete 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setSpreadPercentComplete(Boolean value) {
    this.spreadPercentComplete = value;
  }
  
  /**
   * 取得 taskUpdatesResource 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isTaskUpdatesResource() {
    return taskUpdatesResource;
  }
  
  /**
   * 設定 taskUpdatesResource 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setTaskUpdatesResource(Boolean value) {
    this.taskUpdatesResource = value;
  }
  
  /**
   * 取得 fiscalYearStart 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isFiscalYearStart() {
    return fiscalYearStart;
  }
  
  /**
   * 設定 fiscalYearStart 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setFiscalYearStart(Boolean value) {
    this.fiscalYearStart = value;
  }
  
  /**
   * 取得 weekStartDay 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getWeekStartDay() {
    return weekStartDay;
  }
  
  /**
   * 設定 weekStartDay 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setWeekStartDay(BigInteger value) {
    this.weekStartDay = value;
  }
  
  /**
   * 取得 moveCompletedEndsBack 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isMoveCompletedEndsBack() {
    return moveCompletedEndsBack;
  }
  
  /**
   * 設定 moveCompletedEndsBack 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setMoveCompletedEndsBack(Boolean value) {
    this.moveCompletedEndsBack = value;
  }
  
  /**
   * 取得 moveRemainingStartsBack 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isMoveRemainingStartsBack() {
    return moveRemainingStartsBack;
  }
  
  /**
   * 設定 moveRemainingStartsBack 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setMoveRemainingStartsBack(Boolean value) {
    this.moveRemainingStartsBack = value;
  }
  
  /**
   * 取得 moveRemainingStartsForward 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isMoveRemainingStartsForward() {
    return moveRemainingStartsForward;
  }
  
  /**
   * 設定 moveRemainingStartsForward 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setMoveRemainingStartsForward(Boolean value) {
    this.moveRemainingStartsForward = value;
  }
  
  /**
   * 取得 moveCompletedEndsForward 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isMoveCompletedEndsForward() {
    return moveCompletedEndsForward;
  }
  
  /**
   * 設定 moveCompletedEndsForward 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setMoveCompletedEndsForward(Boolean value) {
    this.moveCompletedEndsForward = value;
  }
  
  /**
   * 取得 baselineForEarnedValue 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getBaselineForEarnedValue() {
    return baselineForEarnedValue;
  }
  
  /**
   * 設定 baselineForEarnedValue 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setBaselineForEarnedValue(BigInteger value) {
    this.baselineForEarnedValue = value;
  }
  
  /**
   * 取得 autoAddNewResourcesAndTasks 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isAutoAddNewResourcesAndTasks() {
    return autoAddNewResourcesAndTasks;
  }
  
  /**
   * 設定 autoAddNewResourcesAndTasks 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setAutoAddNewResourcesAndTasks(Boolean value) {
    this.autoAddNewResourcesAndTasks = value;
  }
  
  /**
   * 取得 statusDate 特性的值.
   *
   * @return possible object is {@link XMLGregorianCalendar }
   */
  public XMLGregorianCalendar getStatusDate() {
    return statusDate;
  }
  
  /**
   * 設定 statusDate 特性的值.
   *
   * @param value allowed object is {@link XMLGregorianCalendar }
   */
  public void setStatusDate(XMLGregorianCalendar value) {
    this.statusDate = value;
  }
  
  /**
   * 取得 currentDate 特性的值.
   *
   * @return possible object is {@link XMLGregorianCalendar }
   */
  public XMLGregorianCalendar getCurrentDate() {
    return currentDate;
  }
  
  /**
   * 設定 currentDate 特性的值.
   *
   * @param value allowed object is {@link XMLGregorianCalendar }
   */
  public void setCurrentDate(XMLGregorianCalendar value) {
    this.currentDate = value;
  }
  
  /**
   * 取得 microsoftProjectServerURL 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isMicrosoftProjectServerURL() {
    return microsoftProjectServerURL;
  }
  
  /**
   * 設定 microsoftProjectServerURL 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setMicrosoftProjectServerURL(Boolean value) {
    this.microsoftProjectServerURL = value;
  }
  
  /**
   * 取得 autolink 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isAutolink() {
    return autolink;
  }
  
  /**
   * 設定 autolink 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setAutolink(Boolean value) {
    this.autolink = value;
  }
  
  /**
   * 取得 newTaskStartDate 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getNewTaskStartDate() {
    return newTaskStartDate;
  }
  
  /**
   * 設定 newTaskStartDate 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setNewTaskStartDate(BigInteger value) {
    this.newTaskStartDate = value;
  }
  
  /**
   * 取得 defaultTaskEVMethod 特性的值.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getDefaultTaskEVMethod() {
    return defaultTaskEVMethod;
  }
  
  /**
   * 設定 defaultTaskEVMethod 特性的值.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setDefaultTaskEVMethod(BigInteger value) {
    this.defaultTaskEVMethod = value;
  }
  
  /**
   * 取得 projectExternallyEdited 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isProjectExternallyEdited() {
    return projectExternallyEdited;
  }
  
  /**
   * 設定 projectExternallyEdited 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setProjectExternallyEdited(Boolean value) {
    this.projectExternallyEdited = value;
  }
  
  /**
   * 取得 extendedCreationDate 特性的值.
   *
   * @return possible object is {@link XMLGregorianCalendar }
   */
  public XMLGregorianCalendar getExtendedCreationDate() {
    return extendedCreationDate;
  }
  
  /**
   * 設定 extendedCreationDate 特性的值.
   *
   * @param value allowed object is {@link XMLGregorianCalendar }
   */
  public void setExtendedCreationDate(XMLGregorianCalendar value) {
    this.extendedCreationDate = value;
  }
  
  /**
   * 取得 actualsInSync 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isActualsInSync() {
    return actualsInSync;
  }
  
  /**
   * 設定 actualsInSync 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setActualsInSync(Boolean value) {
    this.actualsInSync = value;
  }
  
  /**
   * 取得 removeFileProperties 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isRemoveFileProperties() {
    return removeFileProperties;
  }
  
  /**
   * 設定 removeFileProperties 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setRemoveFileProperties(Boolean value) {
    this.removeFileProperties = value;
  }
  
  /**
   * 取得 adminProject 特性的值.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isAdminProject() {
    return adminProject;
  }
  
  /**
   * 設定 adminProject 特性的值.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setAdminProject(Boolean value) {
    this.adminProject = value;
  }
  
  /**
   * 取得 outlineCodes 特性的值.
   *
   * @return possible object is {@link OutlineCodes }
   */
  public OutlineCodes getOutlineCodes() {
    return outlineCodes;
  }
  
  /**
   * 設定 outlineCodes 特性的值.
   *
   * @param value allowed object is {@link OutlineCodes }
   */
  public void setOutlineCodes(OutlineCodes value) {
    this.outlineCodes = value;
  }
  
  /**
   * 取得 wbsMasks 特性的值.
   *
   * @return possible object is {@link WBSMasks }
   */
  public WBSMasks getWBSMasks() {
    return wbsMasks;
  }
  
  /**
   * 設定 wbsMasks 特性的值.
   *
   * @param value allowed object is {@link WBSMasks }
   */
  public void setWBSMasks(WBSMasks value) {
    this.wbsMasks = value;
  }
  
  /**
   * 取得 extendedAttributes 特性的值.
   *
   * @return possible object is {@link ExtendedAttributes }
   */
  public ExtendedAttributes getExtendedAttributes() {
    return extendedAttributes;
  }
  
  /**
   * 設定 extendedAttributes 特性的值.
   *
   * @param value allowed object is {@link ExtendedAttributes }
   */
  public void setExtendedAttributes(ExtendedAttributes value) {
    this.extendedAttributes = value;
  }
  
  /**
   * 取得 calendars 特性的值.
   *
   * @return possible object is {@link Calendars }
   */
  public Calendars getCalendars() {
    return calendars;
  }
  
  /**
   * 設定 calendars 特性的值.
   *
   * @param value allowed object is {@link Calendars }
   */
  public void setCalendars(Calendars value) {
    this.calendars = value;
  }
  
  /**
   * 取得 tasks 特性的值.
   *
   * @return possible object is {@link Tasks }
   */
  public Tasks getTasks() {
    return tasks;
  }
  
  /**
   * 設定 tasks 特性的值.
   *
   * @param value allowed object is {@link Tasks }
   */
  public void setTasks(Tasks value) {
    this.tasks = value;
  }
  
  /**
   * 取得 resources 特性的值.
   *
   * @return possible object is {@link Resources }
   */
  public Resources getResources() {
    return resources;
  }
  
  /**
   * 設定 resources 特性的值.
   *
   * @param value allowed object is {@link Resources }
   */
  public void setResources(Resources value) {
    this.resources = value;
  }
  
  /**
   * 取得 assignments 特性的值.
   *
   * @return possible object is {@link Assignments }
   */
  public Assignments getAssignments() {
    return assignments;
  }
  
  /**
   * 設定 assignments 特性的值.
   *
   * @param value allowed object is {@link Assignments }
   */
  public void setAssignments(Assignments value) {
    this.assignments = value;
  }
  
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(
    name = "",
    propOrder = {"assignment"})
  public static class Assignments {
    
    @XmlElement(name = "Assignment")
    protected List<Assignment> assignment;
    
    /**
     * Gets the value of the assignment property.
     *
     * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the assignment property.
     *
     * <p>For example, to add a new item, do as follows:
     *
     * <pre>
     *    getAssignment().add(newItem);
     * </pre>
     *
     * <p>Objects of the following type(s) are allowed in the list {@link Assignment }
     */
    public List<Assignment> getAssignment() {
      if (assignment == null) {
        assignment = new ArrayList<Assignment>();
      }
      return this.assignment;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(
      name = "",
      propOrder = {
        "uid",
        "taskUID",
        "resourceUID",
        "percentWorkComplete",
        "actualCost",
        "actualFinish",
        "actualOvertimeCost",
        "actualOvertimeWork",
        "actualStart",
        "actualWork",
        "acwp",
        "confirmed",
        "cost",
        "costRateTable",
        "costVariance",
        "cv",
        "delay",
        "finish",
        "finishVariance",
        "hyperlink",
        "hyperlinkAddress",
        "hyperlinkSubAddress",
        "workVariance",
        "hasFixedRateUnits",
        "fixedMaterial",
        "levelingDelay",
        "levelingDelayFormat",
        "linkedFields",
        "milestone",
        "notes",
        "overallocated",
        "overtimeCost",
        "overtimeWork",
        "peakUnits",
        "regularWork",
        "remainingCost",
        "remainingOvertimeCost",
        "remainingOvertimeWork",
        "remainingWork",
        "responsePending",
        "start",
        "stop",
        "resume",
        "startVariance",
        "summary",
        "sv",
        "units",
        "updateNeeded",
        "vac",
        "work",
        "workContour",
        "bcws",
        "bcwp",
        "bookingType",
        "actualWorkProtected",
        "actualOvertimeWorkProtected",
        "creationDate",
        "assnOwner",
        "assnOwnerGuid",
        "budgetCost",
        "budgetWork",
        "extendedAttribute",
        "baseline",
        "f404000",
        "f404001",
        "f404002",
        "f404003",
        "f404004",
        "f404005",
        "f404006",
        "f404007",
        "f404008",
        "f404009",
        "f40400A",
        "f40400B",
        "f40400C",
        "f40400D",
        "f40400E",
        "f40400F",
        "f404010",
        "f404011",
        "f404012",
        "f404013",
        "f404014",
        "f404015",
        "f404016",
        "f404017",
        "f404018",
        "f404019",
        "f40401A",
        "f40401B",
        "f40401C",
        "f40401D",
        "f40401E",
        "f40401F",
        "f404020",
        "f404021",
        "f404022",
        "f404023",
        "f404024",
        "f404025",
        "f404026",
        "f404027",
        "f404028",
        "f404029",
        "f40402A",
        "f40402B",
        "f40402C",
        "f40402D",
        "f40402E",
        "f40402F",
        "f404030",
        "f404031",
        "f404032",
        "f404033",
        "f404034",
        "f404035",
        "f404036",
        "f404037",
        "f404038",
        "f404039",
        "f40403A",
        "f40403B",
        "f40403C",
        "f40403D",
        "f40403E",
        "f40403F",
        "f404040",
        "f404041",
        "f404042",
        "f404043",
        "f404044",
        "f404045",
        "f404046",
        "f404047",
        "f404048",
        "f404049",
        "f40404A",
        "f40404B",
        "f40404C",
        "f40404D",
        "f40404E",
        "f40404F",
        "f404050",
        "f404051",
        "f404052",
        "f404053",
        "f404054",
        "f404055",
        "f404056",
        "f404057",
        "f404058",
        "f404059",
        "f40405A",
        "f40405B",
        "f40405C",
        "f40405D",
        "f40405E",
        "f40405F",
        "f404060",
        "f404061",
        "f404062",
        "f404063",
        "f404064",
        "f404065",
        "f404066",
        "f404067",
        "f404068",
        "f404069",
        "f40406A",
        "f40406B",
        "f40406C",
        "f40406D",
        "f40406E",
        "f40406F",
        "f404070",
        "f404071",
        "f404072",
        "f404073",
        "f404074",
        "f404075",
        "f404076",
        "f404077",
        "f404078",
        "f404079",
        "f40407A",
        "f40407B",
        "f40407C",
        "f40407D",
        "f40407E",
        "f40407F",
        "f404080",
        "f404081",
        "f404082",
        "f404083",
        "f404084",
        "f404085",
        "f404086",
        "f404087",
        "f404088",
        "f404089",
        "f40408A",
        "f40408B",
        "f40408C",
        "f40408D",
        "f40408E",
        "f40408F",
        "f404090",
        "f404091",
        "f404092",
        "f404093",
        "f404094",
        "f404095",
        "f404096",
        "f404097",
        "f404098",
        "f404099",
        "f40409A",
        "f40409B",
        "f40409C",
        "f40409D",
        "f40409E",
        "f40409F",
        "f4040A0",
        "f4040A1",
        "f4040A2",
        "f4040A3",
        "f4040A4",
        "f4040A5",
        "f4040A6",
        "f4040A7",
        "f4040A8",
        "f4040A9",
        "f4040Aa",
        "f4040Ab",
        "f4040Ac",
        "f4040Ad",
        "f4040Ae",
        "f4040Af",
        "f4040B0",
        "f4040B1",
        "f4040B2",
        "f4040B3",
        "f4040B4",
        "f4040B5",
        "f4040B6",
        "f4040B7",
        "f4040B8",
        "f4040B9",
        "f4040Ba",
        "f4040Bb",
        "f4040Bc",
        "f4040Bd",
        "f4040Be",
        "f4040Bf",
        "f4040C0",
        "f4040C1",
        "f4040C2",
        "f4040C3",
        "f4040C4",
        "f4040C5",
        "f4040C6",
        "f4040C7",
        "f4040C8",
        "timephasedData"
      })
    public static class Assignment {
      
      @XmlElement(name = "UID", required = true)
      protected BigInteger uid;
      
      @XmlElement(name = "TaskUID")
      protected BigInteger taskUID;
      
      @XmlElement(name = "ResourceUID")
      protected BigInteger resourceUID;
      
      @XmlElement(name = "PercentWorkComplete")
      protected BigInteger percentWorkComplete;
      
      @XmlElement(name = "ActualCost")
      protected BigDecimal actualCost;
      
      @XmlElement(name = "ActualFinish")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar actualFinish;
      
      @XmlElement(name = "ActualOvertimeCost")
      protected BigDecimal actualOvertimeCost;
      
      @XmlElement(name = "ActualOvertimeWork")
      protected Duration actualOvertimeWork;
      
      @XmlElement(name = "ActualStart")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar actualStart;
      
      @XmlElement(name = "ActualWork")
      protected Duration actualWork;
      
      @XmlElement(name = "ACWP")
      protected Float acwp;
      
      @XmlElement(name = "Confirmed")
      protected Boolean confirmed;
      
      @XmlElement(name = "Cost")
      protected BigDecimal cost;
      
      @XmlElement(name = "CostRateTable")
      protected BigInteger costRateTable;
      
      @XmlElement(name = "CostVariance")
      protected Float costVariance;
      
      @XmlElement(name = "CV")
      protected Float cv;
      
      @XmlElement(name = "Delay")
      protected BigInteger delay;
      
      @XmlElement(name = "Finish")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar finish;
      
      @XmlElement(name = "FinishVariance")
      protected BigInteger finishVariance;
      
      @XmlElement(name = "Hyperlink")
      protected String hyperlink;
      
      @XmlElement(name = "HyperlinkAddress")
      protected String hyperlinkAddress;
      
      @XmlElement(name = "HyperlinkSubAddress")
      protected String hyperlinkSubAddress;
      
      @XmlElement(name = "WorkVariance")
      protected Float workVariance;
      
      @XmlElement(name = "HasFixedRateUnits")
      protected Boolean hasFixedRateUnits;
      
      @XmlElement(name = "FixedMaterial")
      protected Boolean fixedMaterial;
      
      @XmlElement(name = "LevelingDelay")
      protected BigInteger levelingDelay;
      
      @XmlElement(name = "LevelingDelayFormat")
      protected BigInteger levelingDelayFormat;
      
      @XmlElement(name = "LinkedFields")
      protected Boolean linkedFields;
      
      @XmlElement(name = "Milestone")
      protected Boolean milestone;
      
      @XmlElement(name = "Notes")
      protected String notes;
      
      @XmlElement(name = "Overallocated")
      protected Boolean overallocated;
      
      @XmlElement(name = "OvertimeCost")
      protected BigDecimal overtimeCost;
      
      @XmlElement(name = "OvertimeWork")
      protected Duration overtimeWork;
      
      @XmlElement(name = "PeakUnits")
      protected Float peakUnits;
      
      @XmlElement(name = "RegularWork")
      protected Duration regularWork;
      
      @XmlElement(name = "RemainingCost")
      protected BigDecimal remainingCost;
      
      @XmlElement(name = "RemainingOvertimeCost")
      protected BigDecimal remainingOvertimeCost;
      
      @XmlElement(name = "RemainingOvertimeWork")
      protected Duration remainingOvertimeWork;
      
      @XmlElement(name = "RemainingWork")
      protected Duration remainingWork;
      
      @XmlElement(name = "ResponsePending")
      protected Boolean responsePending;
      
      @XmlElement(name = "Start")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar start;
      
      @XmlElement(name = "Stop")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar stop;
      
      @XmlElement(name = "Resume")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar resume;
      
      @XmlElement(name = "StartVariance")
      protected BigInteger startVariance;
      
      @XmlElement(name = "Summary")
      protected Boolean summary;
      
      @XmlElement(name = "SV")
      protected Float sv;
      
      @XmlElement(name = "Units")
      protected Float units;
      
      @XmlElement(name = "UpdateNeeded")
      protected Boolean updateNeeded;
      
      @XmlElement(name = "VAC")
      protected Float vac;
      
      @XmlElement(name = "Work")
      protected Duration work;
      
      @XmlElement(name = "WorkContour")
      protected BigInteger workContour;
      
      @XmlElement(name = "BCWS")
      protected Float bcws;
      
      @XmlElement(name = "BCWP")
      protected Float bcwp;
      
      @XmlElement(name = "BookingType")
      protected BigInteger bookingType;
      
      @XmlElement(name = "ActualWorkProtected")
      protected Duration actualWorkProtected;
      
      @XmlElement(name = "ActualOvertimeWorkProtected")
      protected Duration actualOvertimeWorkProtected;
      
      @XmlElement(name = "CreationDate")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar creationDate;
      
      @XmlElement(name = "AssnOwner")
      protected String assnOwner;
      
      @XmlElement(name = "AssnOwnerGuid")
      protected String assnOwnerGuid;
      
      @XmlElement(name = "BudgetCost")
      protected BigDecimal budgetCost;
      
      @XmlElement(name = "BudgetWork")
      protected Duration budgetWork;
      
      @XmlElement(name = "ExtendedAttribute")
      protected List<ExtendedAttribute> extendedAttribute;
      
      @XmlElement(name = "Baseline")
      protected List<Baseline> baseline;
      
      protected Object f404000;
      
      protected Object f404001;
      
      protected Object f404002;
      
      protected Object f404003;
      
      protected Object f404004;
      
      protected Object f404005;
      
      protected Object f404006;
      
      protected Object f404007;
      
      protected Object f404008;
      
      protected Object f404009;
      
      @XmlElement(name = "f40400a")
      protected Object f40400A;
      
      @XmlElement(name = "f40400b")
      protected Object f40400B;
      
      @XmlElement(name = "f40400c")
      protected Object f40400C;
      
      @XmlElement(name = "f40400d")
      protected Object f40400D;
      
      @XmlElement(name = "f40400e")
      protected Object f40400E;
      
      @XmlElement(name = "f40400f")
      protected Object f40400F;
      
      protected Object f404010;
      
      protected Object f404011;
      
      protected Object f404012;
      
      protected Object f404013;
      
      protected Object f404014;
      
      protected Object f404015;
      
      protected Object f404016;
      
      protected Object f404017;
      
      protected Object f404018;
      
      protected Object f404019;
      
      @XmlElement(name = "f40401a")
      protected Object f40401A;
      
      @XmlElement(name = "f40401b")
      protected Object f40401B;
      
      @XmlElement(name = "f40401c")
      protected Object f40401C;
      
      @XmlElement(name = "f40401d")
      protected Object f40401D;
      
      @XmlElement(name = "f40401e")
      protected Object f40401E;
      
      @XmlElement(name = "f40401f")
      protected Object f40401F;
      
      protected Object f404020;
      
      protected Object f404021;
      
      protected Object f404022;
      
      protected Object f404023;
      
      protected Object f404024;
      
      protected Object f404025;
      
      protected Object f404026;
      
      protected Object f404027;
      
      protected Object f404028;
      
      protected Object f404029;
      
      @XmlElement(name = "f40402a")
      protected Object f40402A;
      
      @XmlElement(name = "f40402b")
      protected Object f40402B;
      
      @XmlElement(name = "f40402c")
      protected Object f40402C;
      
      @XmlElement(name = "f40402d")
      protected Object f40402D;
      
      @XmlElement(name = "f40402e")
      protected Object f40402E;
      
      @XmlElement(name = "f40402f")
      protected Object f40402F;
      
      protected Object f404030;
      
      protected Object f404031;
      
      protected Object f404032;
      
      protected Object f404033;
      
      protected Object f404034;
      
      protected Object f404035;
      
      protected Object f404036;
      
      protected Object f404037;
      
      protected Object f404038;
      
      protected Object f404039;
      
      @XmlElement(name = "f40403a")
      protected Object f40403A;
      
      @XmlElement(name = "f40403b")
      protected Object f40403B;
      
      @XmlElement(name = "f40403c")
      protected Object f40403C;
      
      @XmlElement(name = "f40403d")
      protected Object f40403D;
      
      @XmlElement(name = "f40403e")
      protected Object f40403E;
      
      @XmlElement(name = "f40403f")
      protected Object f40403F;
      
      protected Object f404040;
      
      protected Object f404041;
      
      protected Object f404042;
      
      protected Object f404043;
      
      protected Object f404044;
      
      protected Object f404045;
      
      protected Object f404046;
      
      protected Object f404047;
      
      protected Object f404048;
      
      protected Object f404049;
      
      @XmlElement(name = "f40404a")
      protected Object f40404A;
      
      @XmlElement(name = "f40404b")
      protected Object f40404B;
      
      @XmlElement(name = "f40404c")
      protected Object f40404C;
      
      @XmlElement(name = "f40404d")
      protected Object f40404D;
      
      @XmlElement(name = "f40404e")
      protected Object f40404E;
      
      @XmlElement(name = "f40404f")
      protected Object f40404F;
      
      protected Object f404050;
      
      protected Object f404051;
      
      protected Object f404052;
      
      protected Object f404053;
      
      protected Object f404054;
      
      protected Object f404055;
      
      protected Object f404056;
      
      protected Object f404057;
      
      protected Object f404058;
      
      protected Object f404059;
      
      @XmlElement(name = "f40405a")
      protected Object f40405A;
      
      @XmlElement(name = "f40405b")
      protected Object f40405B;
      
      @XmlElement(name = "f40405c")
      protected Object f40405C;
      
      @XmlElement(name = "f40405d")
      protected Object f40405D;
      
      @XmlElement(name = "f40405e")
      protected Object f40405E;
      
      @XmlElement(name = "f40405f")
      protected Object f40405F;
      
      protected Object f404060;
      
      protected Object f404061;
      
      protected Object f404062;
      
      protected Object f404063;
      
      protected Object f404064;
      
      protected Object f404065;
      
      protected Object f404066;
      
      protected Object f404067;
      
      protected Object f404068;
      
      protected Object f404069;
      
      @XmlElement(name = "f40406a")
      protected Object f40406A;
      
      @XmlElement(name = "f40406b")
      protected Object f40406B;
      
      @XmlElement(name = "f40406c")
      protected Object f40406C;
      
      @XmlElement(name = "f40406d")
      protected Object f40406D;
      
      @XmlElement(name = "f40406e")
      protected Object f40406E;
      
      @XmlElement(name = "f40406f")
      protected Object f40406F;
      
      protected Object f404070;
      
      protected Object f404071;
      
      protected Object f404072;
      
      protected Object f404073;
      
      protected Object f404074;
      
      protected Object f404075;
      
      protected Object f404076;
      
      protected Object f404077;
      
      protected Object f404078;
      
      protected Object f404079;
      
      @XmlElement(name = "f40407a")
      protected Object f40407A;
      
      @XmlElement(name = "f40407b")
      protected Object f40407B;
      
      @XmlElement(name = "f40407c")
      protected Object f40407C;
      
      @XmlElement(name = "f40407d")
      protected Object f40407D;
      
      @XmlElement(name = "f40407e")
      protected Object f40407E;
      
      @XmlElement(name = "f40407f")
      protected Object f40407F;
      
      protected Object f404080;
      
      protected Object f404081;
      
      protected Object f404082;
      
      protected Object f404083;
      
      protected Object f404084;
      
      protected Object f404085;
      
      protected Object f404086;
      
      protected Object f404087;
      
      protected Object f404088;
      
      protected Object f404089;
      
      @XmlElement(name = "f40408a")
      protected Object f40408A;
      
      @XmlElement(name = "f40408b")
      protected Object f40408B;
      
      @XmlElement(name = "f40408c")
      protected Object f40408C;
      
      @XmlElement(name = "f40408d")
      protected Object f40408D;
      
      @XmlElement(name = "f40408e")
      protected Object f40408E;
      
      @XmlElement(name = "f40408f")
      protected Object f40408F;
      
      protected Object f404090;
      
      protected Object f404091;
      
      protected Object f404092;
      
      protected Object f404093;
      
      protected Object f404094;
      
      protected Object f404095;
      
      protected Object f404096;
      
      protected Object f404097;
      
      protected Object f404098;
      
      protected Object f404099;
      
      @XmlElement(name = "f40409a")
      protected Object f40409A;
      
      @XmlElement(name = "f40409b")
      protected Object f40409B;
      
      @XmlElement(name = "f40409c")
      protected Object f40409C;
      
      @XmlElement(name = "f40409d")
      protected Object f40409D;
      
      @XmlElement(name = "f40409e")
      protected Object f40409E;
      
      @XmlElement(name = "f40409f")
      protected Object f40409F;
      
      @XmlElement(name = "f4040a0")
      protected Object f4040A0;
      
      @XmlElement(name = "f4040a1")
      protected Object f4040A1;
      
      @XmlElement(name = "f4040a2")
      protected Object f4040A2;
      
      @XmlElement(name = "f4040a3")
      protected Object f4040A3;
      
      @XmlElement(name = "f4040a4")
      protected Object f4040A4;
      
      @XmlElement(name = "f4040a5")
      protected Object f4040A5;
      
      @XmlElement(name = "f4040a6")
      protected Object f4040A6;
      
      @XmlElement(name = "f4040a7")
      protected Object f4040A7;
      
      @XmlElement(name = "f4040a8")
      protected Object f4040A8;
      
      @XmlElement(name = "f4040a9")
      protected Object f4040A9;
      
      @XmlElement(name = "f4040aa")
      protected Object f4040Aa;
      
      @XmlElement(name = "f4040ab")
      protected Object f4040Ab;
      
      @XmlElement(name = "f4040ac")
      protected Object f4040Ac;
      
      @XmlElement(name = "f4040ad")
      protected Object f4040Ad;
      
      @XmlElement(name = "f4040ae")
      protected Object f4040Ae;
      
      @XmlElement(name = "f4040af")
      protected Object f4040Af;
      
      @XmlElement(name = "f4040b0")
      protected Object f4040B0;
      
      @XmlElement(name = "f4040b1")
      protected Object f4040B1;
      
      @XmlElement(name = "f4040b2")
      protected Object f4040B2;
      
      @XmlElement(name = "f4040b3")
      protected Object f4040B3;
      
      @XmlElement(name = "f4040b4")
      protected Object f4040B4;
      
      @XmlElement(name = "f4040b5")
      protected Object f4040B5;
      
      @XmlElement(name = "f4040b6")
      protected Object f4040B6;
      
      @XmlElement(name = "f4040b7")
      protected Object f4040B7;
      
      @XmlElement(name = "f4040b8")
      protected Object f4040B8;
      
      @XmlElement(name = "f4040b9")
      protected Object f4040B9;
      
      @XmlElement(name = "f4040ba")
      protected Object f4040Ba;
      
      @XmlElement(name = "f4040bb")
      protected Object f4040Bb;
      
      @XmlElement(name = "f4040bc")
      protected Object f4040Bc;
      
      @XmlElement(name = "f4040bd")
      protected Object f4040Bd;
      
      @XmlElement(name = "f4040be")
      protected Object f4040Be;
      
      @XmlElement(name = "f4040bf")
      protected Object f4040Bf;
      
      @XmlElement(name = "f4040c0")
      protected Object f4040C0;
      
      @XmlElement(name = "f4040c1")
      protected Object f4040C1;
      
      @XmlElement(name = "f4040c2")
      protected Object f4040C2;
      
      @XmlElement(name = "f4040c3")
      protected Object f4040C3;
      
      @XmlElement(name = "f4040c4")
      protected Object f4040C4;
      
      @XmlElement(name = "f4040c5")
      protected Object f4040C5;
      
      @XmlElement(name = "f4040c6")
      protected Object f4040C6;
      
      @XmlElement(name = "f4040c7")
      protected Object f4040C7;
      
      @XmlElement(name = "f4040c8")
      protected Object f4040C8;
      
      @XmlElement(name = "TimephasedData")
      protected List<TimephasedDataType> timephasedData;
      
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
       * 取得 taskUID 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getTaskUID() {
        return taskUID;
      }
      
      /**
       * 設定 taskUID 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setTaskUID(BigInteger value) {
        this.taskUID = value;
      }
      
      /**
       * 取得 resourceUID 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getResourceUID() {
        return resourceUID;
      }
      
      /**
       * 設定 resourceUID 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setResourceUID(BigInteger value) {
        this.resourceUID = value;
      }
      
      /**
       * 取得 percentWorkComplete 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getPercentWorkComplete() {
        return percentWorkComplete;
      }
      
      /**
       * 設定 percentWorkComplete 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setPercentWorkComplete(BigInteger value) {
        this.percentWorkComplete = value;
      }
      
      /**
       * 取得 actualCost 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getActualCost() {
        return actualCost;
      }
      
      /**
       * 設定 actualCost 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setActualCost(BigDecimal value) {
        this.actualCost = value;
      }
      
      /**
       * 取得 actualFinish 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getActualFinish() {
        return actualFinish;
      }
      
      /**
       * 設定 actualFinish 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setActualFinish(XMLGregorianCalendar value) {
        this.actualFinish = value;
      }
      
      /**
       * 取得 actualOvertimeCost 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getActualOvertimeCost() {
        return actualOvertimeCost;
      }
      
      /**
       * 設定 actualOvertimeCost 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setActualOvertimeCost(BigDecimal value) {
        this.actualOvertimeCost = value;
      }
      
      /**
       * 取得 actualOvertimeWork 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getActualOvertimeWork() {
        return actualOvertimeWork;
      }
      
      /**
       * 設定 actualOvertimeWork 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setActualOvertimeWork(Duration value) {
        this.actualOvertimeWork = value;
      }
      
      /**
       * 取得 actualStart 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getActualStart() {
        return actualStart;
      }
      
      /**
       * 設定 actualStart 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setActualStart(XMLGregorianCalendar value) {
        this.actualStart = value;
      }
      
      /**
       * 取得 actualWork 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getActualWork() {
        return actualWork;
      }
      
      /**
       * 設定 actualWork 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setActualWork(Duration value) {
        this.actualWork = value;
      }
      
      /**
       * 取得 acwp 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getACWP() {
        return acwp;
      }
      
      /**
       * 設定 acwp 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setACWP(Float value) {
        this.acwp = value;
      }
      
      /**
       * 取得 confirmed 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isConfirmed() {
        return confirmed;
      }
      
      /**
       * 設定 confirmed 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setConfirmed(Boolean value) {
        this.confirmed = value;
      }
      
      /**
       * 取得 cost 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getCost() {
        return cost;
      }
      
      /**
       * 設定 cost 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setCost(BigDecimal value) {
        this.cost = value;
      }
      
      /**
       * 取得 costRateTable 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getCostRateTable() {
        return costRateTable;
      }
      
      /**
       * 設定 costRateTable 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setCostRateTable(BigInteger value) {
        this.costRateTable = value;
      }
      
      /**
       * 取得 costVariance 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getCostVariance() {
        return costVariance;
      }
      
      /**
       * 設定 costVariance 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setCostVariance(Float value) {
        this.costVariance = value;
      }
      
      /**
       * 取得 cv 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getCV() {
        return cv;
      }
      
      /**
       * 設定 cv 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setCV(Float value) {
        this.cv = value;
      }
      
      /**
       * 取得 delay 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getDelay() {
        return delay;
      }
      
      /**
       * 設定 delay 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setDelay(BigInteger value) {
        this.delay = value;
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
       * 取得 finishVariance 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getFinishVariance() {
        return finishVariance;
      }
      
      /**
       * 設定 finishVariance 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setFinishVariance(BigInteger value) {
        this.finishVariance = value;
      }
      
      /**
       * 取得 hyperlink 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getHyperlink() {
        return hyperlink;
      }
      
      /**
       * 設定 hyperlink 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setHyperlink(String value) {
        this.hyperlink = value;
      }
      
      /**
       * 取得 hyperlinkAddress 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getHyperlinkAddress() {
        return hyperlinkAddress;
      }
      
      /**
       * 設定 hyperlinkAddress 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setHyperlinkAddress(String value) {
        this.hyperlinkAddress = value;
      }
      
      /**
       * 取得 hyperlinkSubAddress 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getHyperlinkSubAddress() {
        return hyperlinkSubAddress;
      }
      
      /**
       * 設定 hyperlinkSubAddress 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setHyperlinkSubAddress(String value) {
        this.hyperlinkSubAddress = value;
      }
      
      /**
       * 取得 workVariance 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getWorkVariance() {
        return workVariance;
      }
      
      /**
       * 設定 workVariance 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setWorkVariance(Float value) {
        this.workVariance = value;
      }
      
      /**
       * 取得 hasFixedRateUnits 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isHasFixedRateUnits() {
        return hasFixedRateUnits;
      }
      
      /**
       * 設定 hasFixedRateUnits 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setHasFixedRateUnits(Boolean value) {
        this.hasFixedRateUnits = value;
      }
      
      /**
       * 取得 fixedMaterial 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isFixedMaterial() {
        return fixedMaterial;
      }
      
      /**
       * 設定 fixedMaterial 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setFixedMaterial(Boolean value) {
        this.fixedMaterial = value;
      }
      
      /**
       * 取得 levelingDelay 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getLevelingDelay() {
        return levelingDelay;
      }
      
      /**
       * 設定 levelingDelay 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setLevelingDelay(BigInteger value) {
        this.levelingDelay = value;
      }
      
      /**
       * 取得 levelingDelayFormat 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getLevelingDelayFormat() {
        return levelingDelayFormat;
      }
      
      /**
       * 設定 levelingDelayFormat 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setLevelingDelayFormat(BigInteger value) {
        this.levelingDelayFormat = value;
      }
      
      /**
       * 取得 linkedFields 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isLinkedFields() {
        return linkedFields;
      }
      
      /**
       * 設定 linkedFields 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setLinkedFields(Boolean value) {
        this.linkedFields = value;
      }
      
      /**
       * 取得 milestone 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isMilestone() {
        return milestone;
      }
      
      /**
       * 設定 milestone 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setMilestone(Boolean value) {
        this.milestone = value;
      }
      
      /**
       * 取得 notes 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getNotes() {
        return notes;
      }
      
      /**
       * 設定 notes 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setNotes(String value) {
        this.notes = value;
      }
      
      /**
       * 取得 overallocated 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isOverallocated() {
        return overallocated;
      }
      
      /**
       * 設定 overallocated 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setOverallocated(Boolean value) {
        this.overallocated = value;
      }
      
      /**
       * 取得 overtimeCost 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getOvertimeCost() {
        return overtimeCost;
      }
      
      /**
       * 設定 overtimeCost 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setOvertimeCost(BigDecimal value) {
        this.overtimeCost = value;
      }
      
      /**
       * 取得 overtimeWork 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getOvertimeWork() {
        return overtimeWork;
      }
      
      /**
       * 設定 overtimeWork 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setOvertimeWork(Duration value) {
        this.overtimeWork = value;
      }
      
      /**
       * 取得 peakUnits 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getPeakUnits() {
        return peakUnits;
      }
      
      /**
       * 設定 peakUnits 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setPeakUnits(Float value) {
        this.peakUnits = value;
      }
      
      /**
       * 取得 regularWork 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getRegularWork() {
        return regularWork;
      }
      
      /**
       * 設定 regularWork 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setRegularWork(Duration value) {
        this.regularWork = value;
      }
      
      /**
       * 取得 remainingCost 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getRemainingCost() {
        return remainingCost;
      }
      
      /**
       * 設定 remainingCost 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setRemainingCost(BigDecimal value) {
        this.remainingCost = value;
      }
      
      /**
       * 取得 remainingOvertimeCost 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getRemainingOvertimeCost() {
        return remainingOvertimeCost;
      }
      
      /**
       * 設定 remainingOvertimeCost 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setRemainingOvertimeCost(BigDecimal value) {
        this.remainingOvertimeCost = value;
      }
      
      /**
       * 取得 remainingOvertimeWork 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getRemainingOvertimeWork() {
        return remainingOvertimeWork;
      }
      
      /**
       * 設定 remainingOvertimeWork 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setRemainingOvertimeWork(Duration value) {
        this.remainingOvertimeWork = value;
      }
      
      /**
       * 取得 remainingWork 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getRemainingWork() {
        return remainingWork;
      }
      
      /**
       * 設定 remainingWork 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setRemainingWork(Duration value) {
        this.remainingWork = value;
      }
      
      /**
       * 取得 responsePending 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isResponsePending() {
        return responsePending;
      }
      
      /**
       * 設定 responsePending 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setResponsePending(Boolean value) {
        this.responsePending = value;
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
       * 取得 stop 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getStop() {
        return stop;
      }
      
      /**
       * 設定 stop 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setStop(XMLGregorianCalendar value) {
        this.stop = value;
      }
      
      /**
       * 取得 resume 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getResume() {
        return resume;
      }
      
      /**
       * 設定 resume 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setResume(XMLGregorianCalendar value) {
        this.resume = value;
      }
      
      /**
       * 取得 startVariance 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getStartVariance() {
        return startVariance;
      }
      
      /**
       * 設定 startVariance 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setStartVariance(BigInteger value) {
        this.startVariance = value;
      }
      
      /**
       * 取得 summary 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isSummary() {
        return summary;
      }
      
      /**
       * 設定 summary 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setSummary(Boolean value) {
        this.summary = value;
      }
      
      /**
       * 取得 sv 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getSV() {
        return sv;
      }
      
      /**
       * 設定 sv 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setSV(Float value) {
        this.sv = value;
      }
      
      /**
       * 取得 units 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getUnits() {
        return units;
      }
      
      /**
       * 設定 units 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setUnits(Float value) {
        this.units = value;
      }
      
      /**
       * 取得 updateNeeded 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isUpdateNeeded() {
        return updateNeeded;
      }
      
      /**
       * 設定 updateNeeded 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setUpdateNeeded(Boolean value) {
        this.updateNeeded = value;
      }
      
      /**
       * 取得 vac 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getVAC() {
        return vac;
      }
      
      /**
       * 設定 vac 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setVAC(Float value) {
        this.vac = value;
      }
      
      /**
       * 取得 work 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getWork() {
        return work;
      }
      
      /**
       * 設定 work 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setWork(Duration value) {
        this.work = value;
      }
      
      /**
       * 取得 workContour 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getWorkContour() {
        return workContour;
      }
      
      /**
       * 設定 workContour 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setWorkContour(BigInteger value) {
        this.workContour = value;
      }
      
      /**
       * 取得 bcws 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getBCWS() {
        return bcws;
      }
      
      /**
       * 設定 bcws 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setBCWS(Float value) {
        this.bcws = value;
      }
      
      /**
       * 取得 bcwp 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getBCWP() {
        return bcwp;
      }
      
      /**
       * 設定 bcwp 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setBCWP(Float value) {
        this.bcwp = value;
      }
      
      /**
       * 取得 bookingType 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getBookingType() {
        return bookingType;
      }
      
      /**
       * 設定 bookingType 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setBookingType(BigInteger value) {
        this.bookingType = value;
      }
      
      /**
       * 取得 actualWorkProtected 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getActualWorkProtected() {
        return actualWorkProtected;
      }
      
      /**
       * 設定 actualWorkProtected 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setActualWorkProtected(Duration value) {
        this.actualWorkProtected = value;
      }
      
      /**
       * 取得 actualOvertimeWorkProtected 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getActualOvertimeWorkProtected() {
        return actualOvertimeWorkProtected;
      }
      
      /**
       * 設定 actualOvertimeWorkProtected 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setActualOvertimeWorkProtected(Duration value) {
        this.actualOvertimeWorkProtected = value;
      }
      
      /**
       * 取得 creationDate 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getCreationDate() {
        return creationDate;
      }
      
      /**
       * 設定 creationDate 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
      }
      
      /**
       * 取得 assnOwner 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getAssnOwner() {
        return assnOwner;
      }
      
      /**
       * 設定 assnOwner 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setAssnOwner(String value) {
        this.assnOwner = value;
      }
      
      /**
       * 取得 assnOwnerGuid 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getAssnOwnerGuid() {
        return assnOwnerGuid;
      }
      
      /**
       * 設定 assnOwnerGuid 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setAssnOwnerGuid(String value) {
        this.assnOwnerGuid = value;
      }
      
      /**
       * 取得 budgetCost 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getBudgetCost() {
        return budgetCost;
      }
      
      /**
       * 設定 budgetCost 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setBudgetCost(BigDecimal value) {
        this.budgetCost = value;
      }
      
      /**
       * 取得 budgetWork 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getBudgetWork() {
        return budgetWork;
      }
      
      /**
       * 設定 budgetWork 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setBudgetWork(Duration value) {
        this.budgetWork = value;
      }
      
      /**
       * Gets the value of the extendedAttribute property.
       *
       * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
       * modification you make to the returned list will be present inside the JAXB object. This is
       * why there is not a <CODE>set</CODE> method for the extendedAttribute property.
       *
       * <p>For example, to add a new item, do as follows:
       *
       * <pre>
       *    getExtendedAttribute().add(newItem);
       * </pre>
       *
       * <p>Objects of the following type(s) are allowed in the list {@link ExtendedAttribute }
       */
      public List<ExtendedAttribute> getExtendedAttribute() {
        if (extendedAttribute == null) {
          extendedAttribute = new ArrayList<ExtendedAttribute>();
        }
        return this.extendedAttribute;
      }
      
      /**
       * Gets the value of the baseline property.
       *
       * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
       * modification you make to the returned list will be present inside the JAXB object. This is
       * why there is not a <CODE>set</CODE> method for the baseline property.
       *
       * <p>For example, to add a new item, do as follows:
       *
       * <pre>
       *    getBaseline().add(newItem);
       * </pre>
       *
       * <p>Objects of the following type(s) are allowed in the list {@link Baseline }
       */
      public List<Baseline> getBaseline() {
        if (baseline == null) {
          baseline = new ArrayList<Baseline>();
        }
        return this.baseline;
      }
      
      /**
       * 取得 f404000 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404000() {
        return f404000;
      }
      
      /**
       * 設定 f404000 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404000(Object value) {
        this.f404000 = value;
      }
      
      /**
       * 取得 f404001 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404001() {
        return f404001;
      }
      
      /**
       * 設定 f404001 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404001(Object value) {
        this.f404001 = value;
      }
      
      /**
       * 取得 f404002 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404002() {
        return f404002;
      }
      
      /**
       * 設定 f404002 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404002(Object value) {
        this.f404002 = value;
      }
      
      /**
       * 取得 f404003 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404003() {
        return f404003;
      }
      
      /**
       * 設定 f404003 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404003(Object value) {
        this.f404003 = value;
      }
      
      /**
       * 取得 f404004 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404004() {
        return f404004;
      }
      
      /**
       * 設定 f404004 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404004(Object value) {
        this.f404004 = value;
      }
      
      /**
       * 取得 f404005 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404005() {
        return f404005;
      }
      
      /**
       * 設定 f404005 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404005(Object value) {
        this.f404005 = value;
      }
      
      /**
       * 取得 f404006 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404006() {
        return f404006;
      }
      
      /**
       * 設定 f404006 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404006(Object value) {
        this.f404006 = value;
      }
      
      /**
       * 取得 f404007 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404007() {
        return f404007;
      }
      
      /**
       * 設定 f404007 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404007(Object value) {
        this.f404007 = value;
      }
      
      /**
       * 取得 f404008 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404008() {
        return f404008;
      }
      
      /**
       * 設定 f404008 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404008(Object value) {
        this.f404008 = value;
      }
      
      /**
       * 取得 f404009 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404009() {
        return f404009;
      }
      
      /**
       * 設定 f404009 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404009(Object value) {
        this.f404009 = value;
      }
      
      /**
       * 取得 f40400A 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40400A() {
        return f40400A;
      }
      
      /**
       * 設定 f40400A 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40400A(Object value) {
        this.f40400A = value;
      }
      
      /**
       * 取得 f40400B 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40400B() {
        return f40400B;
      }
      
      /**
       * 設定 f40400B 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40400B(Object value) {
        this.f40400B = value;
      }
      
      /**
       * 取得 f40400C 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40400C() {
        return f40400C;
      }
      
      /**
       * 設定 f40400C 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40400C(Object value) {
        this.f40400C = value;
      }
      
      /**
       * 取得 f40400D 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40400D() {
        return f40400D;
      }
      
      /**
       * 設定 f40400D 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40400D(Object value) {
        this.f40400D = value;
      }
      
      /**
       * 取得 f40400E 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40400E() {
        return f40400E;
      }
      
      /**
       * 設定 f40400E 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40400E(Object value) {
        this.f40400E = value;
      }
      
      /**
       * 取得 f40400F 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40400F() {
        return f40400F;
      }
      
      /**
       * 設定 f40400F 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40400F(Object value) {
        this.f40400F = value;
      }
      
      /**
       * 取得 f404010 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404010() {
        return f404010;
      }
      
      /**
       * 設定 f404010 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404010(Object value) {
        this.f404010 = value;
      }
      
      /**
       * 取得 f404011 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404011() {
        return f404011;
      }
      
      /**
       * 設定 f404011 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404011(Object value) {
        this.f404011 = value;
      }
      
      /**
       * 取得 f404012 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404012() {
        return f404012;
      }
      
      /**
       * 設定 f404012 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404012(Object value) {
        this.f404012 = value;
      }
      
      /**
       * 取得 f404013 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404013() {
        return f404013;
      }
      
      /**
       * 設定 f404013 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404013(Object value) {
        this.f404013 = value;
      }
      
      /**
       * 取得 f404014 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404014() {
        return f404014;
      }
      
      /**
       * 設定 f404014 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404014(Object value) {
        this.f404014 = value;
      }
      
      /**
       * 取得 f404015 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404015() {
        return f404015;
      }
      
      /**
       * 設定 f404015 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404015(Object value) {
        this.f404015 = value;
      }
      
      /**
       * 取得 f404016 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404016() {
        return f404016;
      }
      
      /**
       * 設定 f404016 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404016(Object value) {
        this.f404016 = value;
      }
      
      /**
       * 取得 f404017 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404017() {
        return f404017;
      }
      
      /**
       * 設定 f404017 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404017(Object value) {
        this.f404017 = value;
      }
      
      /**
       * 取得 f404018 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404018() {
        return f404018;
      }
      
      /**
       * 設定 f404018 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404018(Object value) {
        this.f404018 = value;
      }
      
      /**
       * 取得 f404019 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404019() {
        return f404019;
      }
      
      /**
       * 設定 f404019 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404019(Object value) {
        this.f404019 = value;
      }
      
      /**
       * 取得 f40401A 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40401A() {
        return f40401A;
      }
      
      /**
       * 設定 f40401A 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40401A(Object value) {
        this.f40401A = value;
      }
      
      /**
       * 取得 f40401B 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40401B() {
        return f40401B;
      }
      
      /**
       * 設定 f40401B 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40401B(Object value) {
        this.f40401B = value;
      }
      
      /**
       * 取得 f40401C 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40401C() {
        return f40401C;
      }
      
      /**
       * 設定 f40401C 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40401C(Object value) {
        this.f40401C = value;
      }
      
      /**
       * 取得 f40401D 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40401D() {
        return f40401D;
      }
      
      /**
       * 設定 f40401D 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40401D(Object value) {
        this.f40401D = value;
      }
      
      /**
       * 取得 f40401E 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40401E() {
        return f40401E;
      }
      
      /**
       * 設定 f40401E 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40401E(Object value) {
        this.f40401E = value;
      }
      
      /**
       * 取得 f40401F 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40401F() {
        return f40401F;
      }
      
      /**
       * 設定 f40401F 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40401F(Object value) {
        this.f40401F = value;
      }
      
      /**
       * 取得 f404020 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404020() {
        return f404020;
      }
      
      /**
       * 設定 f404020 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404020(Object value) {
        this.f404020 = value;
      }
      
      /**
       * 取得 f404021 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404021() {
        return f404021;
      }
      
      /**
       * 設定 f404021 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404021(Object value) {
        this.f404021 = value;
      }
      
      /**
       * 取得 f404022 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404022() {
        return f404022;
      }
      
      /**
       * 設定 f404022 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404022(Object value) {
        this.f404022 = value;
      }
      
      /**
       * 取得 f404023 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404023() {
        return f404023;
      }
      
      /**
       * 設定 f404023 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404023(Object value) {
        this.f404023 = value;
      }
      
      /**
       * 取得 f404024 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404024() {
        return f404024;
      }
      
      /**
       * 設定 f404024 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404024(Object value) {
        this.f404024 = value;
      }
      
      /**
       * 取得 f404025 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404025() {
        return f404025;
      }
      
      /**
       * 設定 f404025 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404025(Object value) {
        this.f404025 = value;
      }
      
      /**
       * 取得 f404026 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404026() {
        return f404026;
      }
      
      /**
       * 設定 f404026 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404026(Object value) {
        this.f404026 = value;
      }
      
      /**
       * 取得 f404027 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404027() {
        return f404027;
      }
      
      /**
       * 設定 f404027 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404027(Object value) {
        this.f404027 = value;
      }
      
      /**
       * 取得 f404028 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404028() {
        return f404028;
      }
      
      /**
       * 設定 f404028 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404028(Object value) {
        this.f404028 = value;
      }
      
      /**
       * 取得 f404029 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404029() {
        return f404029;
      }
      
      /**
       * 設定 f404029 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404029(Object value) {
        this.f404029 = value;
      }
      
      /**
       * 取得 f40402A 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40402A() {
        return f40402A;
      }
      
      /**
       * 設定 f40402A 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40402A(Object value) {
        this.f40402A = value;
      }
      
      /**
       * 取得 f40402B 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40402B() {
        return f40402B;
      }
      
      /**
       * 設定 f40402B 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40402B(Object value) {
        this.f40402B = value;
      }
      
      /**
       * 取得 f40402C 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40402C() {
        return f40402C;
      }
      
      /**
       * 設定 f40402C 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40402C(Object value) {
        this.f40402C = value;
      }
      
      /**
       * 取得 f40402D 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40402D() {
        return f40402D;
      }
      
      /**
       * 設定 f40402D 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40402D(Object value) {
        this.f40402D = value;
      }
      
      /**
       * 取得 f40402E 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40402E() {
        return f40402E;
      }
      
      /**
       * 設定 f40402E 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40402E(Object value) {
        this.f40402E = value;
      }
      
      /**
       * 取得 f40402F 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40402F() {
        return f40402F;
      }
      
      /**
       * 設定 f40402F 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40402F(Object value) {
        this.f40402F = value;
      }
      
      /**
       * 取得 f404030 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404030() {
        return f404030;
      }
      
      /**
       * 設定 f404030 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404030(Object value) {
        this.f404030 = value;
      }
      
      /**
       * 取得 f404031 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404031() {
        return f404031;
      }
      
      /**
       * 設定 f404031 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404031(Object value) {
        this.f404031 = value;
      }
      
      /**
       * 取得 f404032 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404032() {
        return f404032;
      }
      
      /**
       * 設定 f404032 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404032(Object value) {
        this.f404032 = value;
      }
      
      /**
       * 取得 f404033 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404033() {
        return f404033;
      }
      
      /**
       * 設定 f404033 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404033(Object value) {
        this.f404033 = value;
      }
      
      /**
       * 取得 f404034 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404034() {
        return f404034;
      }
      
      /**
       * 設定 f404034 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404034(Object value) {
        this.f404034 = value;
      }
      
      /**
       * 取得 f404035 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404035() {
        return f404035;
      }
      
      /**
       * 設定 f404035 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404035(Object value) {
        this.f404035 = value;
      }
      
      /**
       * 取得 f404036 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404036() {
        return f404036;
      }
      
      /**
       * 設定 f404036 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404036(Object value) {
        this.f404036 = value;
      }
      
      /**
       * 取得 f404037 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404037() {
        return f404037;
      }
      
      /**
       * 設定 f404037 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404037(Object value) {
        this.f404037 = value;
      }
      
      /**
       * 取得 f404038 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404038() {
        return f404038;
      }
      
      /**
       * 設定 f404038 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404038(Object value) {
        this.f404038 = value;
      }
      
      /**
       * 取得 f404039 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404039() {
        return f404039;
      }
      
      /**
       * 設定 f404039 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404039(Object value) {
        this.f404039 = value;
      }
      
      /**
       * 取得 f40403A 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40403A() {
        return f40403A;
      }
      
      /**
       * 設定 f40403A 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40403A(Object value) {
        this.f40403A = value;
      }
      
      /**
       * 取得 f40403B 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40403B() {
        return f40403B;
      }
      
      /**
       * 設定 f40403B 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40403B(Object value) {
        this.f40403B = value;
      }
      
      /**
       * 取得 f40403C 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40403C() {
        return f40403C;
      }
      
      /**
       * 設定 f40403C 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40403C(Object value) {
        this.f40403C = value;
      }
      
      /**
       * 取得 f40403D 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40403D() {
        return f40403D;
      }
      
      /**
       * 設定 f40403D 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40403D(Object value) {
        this.f40403D = value;
      }
      
      /**
       * 取得 f40403E 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40403E() {
        return f40403E;
      }
      
      /**
       * 設定 f40403E 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40403E(Object value) {
        this.f40403E = value;
      }
      
      /**
       * 取得 f40403F 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40403F() {
        return f40403F;
      }
      
      /**
       * 設定 f40403F 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40403F(Object value) {
        this.f40403F = value;
      }
      
      /**
       * 取得 f404040 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404040() {
        return f404040;
      }
      
      /**
       * 設定 f404040 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404040(Object value) {
        this.f404040 = value;
      }
      
      /**
       * 取得 f404041 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404041() {
        return f404041;
      }
      
      /**
       * 設定 f404041 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404041(Object value) {
        this.f404041 = value;
      }
      
      /**
       * 取得 f404042 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404042() {
        return f404042;
      }
      
      /**
       * 設定 f404042 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404042(Object value) {
        this.f404042 = value;
      }
      
      /**
       * 取得 f404043 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404043() {
        return f404043;
      }
      
      /**
       * 設定 f404043 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404043(Object value) {
        this.f404043 = value;
      }
      
      /**
       * 取得 f404044 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404044() {
        return f404044;
      }
      
      /**
       * 設定 f404044 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404044(Object value) {
        this.f404044 = value;
      }
      
      /**
       * 取得 f404045 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404045() {
        return f404045;
      }
      
      /**
       * 設定 f404045 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404045(Object value) {
        this.f404045 = value;
      }
      
      /**
       * 取得 f404046 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404046() {
        return f404046;
      }
      
      /**
       * 設定 f404046 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404046(Object value) {
        this.f404046 = value;
      }
      
      /**
       * 取得 f404047 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404047() {
        return f404047;
      }
      
      /**
       * 設定 f404047 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404047(Object value) {
        this.f404047 = value;
      }
      
      /**
       * 取得 f404048 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404048() {
        return f404048;
      }
      
      /**
       * 設定 f404048 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404048(Object value) {
        this.f404048 = value;
      }
      
      /**
       * 取得 f404049 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404049() {
        return f404049;
      }
      
      /**
       * 設定 f404049 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404049(Object value) {
        this.f404049 = value;
      }
      
      /**
       * 取得 f40404A 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40404A() {
        return f40404A;
      }
      
      /**
       * 設定 f40404A 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40404A(Object value) {
        this.f40404A = value;
      }
      
      /**
       * 取得 f40404B 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40404B() {
        return f40404B;
      }
      
      /**
       * 設定 f40404B 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40404B(Object value) {
        this.f40404B = value;
      }
      
      /**
       * 取得 f40404C 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40404C() {
        return f40404C;
      }
      
      /**
       * 設定 f40404C 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40404C(Object value) {
        this.f40404C = value;
      }
      
      /**
       * 取得 f40404D 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40404D() {
        return f40404D;
      }
      
      /**
       * 設定 f40404D 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40404D(Object value) {
        this.f40404D = value;
      }
      
      /**
       * 取得 f40404E 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40404E() {
        return f40404E;
      }
      
      /**
       * 設定 f40404E 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40404E(Object value) {
        this.f40404E = value;
      }
      
      /**
       * 取得 f40404F 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40404F() {
        return f40404F;
      }
      
      /**
       * 設定 f40404F 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40404F(Object value) {
        this.f40404F = value;
      }
      
      /**
       * 取得 f404050 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404050() {
        return f404050;
      }
      
      /**
       * 設定 f404050 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404050(Object value) {
        this.f404050 = value;
      }
      
      /**
       * 取得 f404051 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404051() {
        return f404051;
      }
      
      /**
       * 設定 f404051 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404051(Object value) {
        this.f404051 = value;
      }
      
      /**
       * 取得 f404052 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404052() {
        return f404052;
      }
      
      /**
       * 設定 f404052 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404052(Object value) {
        this.f404052 = value;
      }
      
      /**
       * 取得 f404053 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404053() {
        return f404053;
      }
      
      /**
       * 設定 f404053 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404053(Object value) {
        this.f404053 = value;
      }
      
      /**
       * 取得 f404054 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404054() {
        return f404054;
      }
      
      /**
       * 設定 f404054 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404054(Object value) {
        this.f404054 = value;
      }
      
      /**
       * 取得 f404055 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404055() {
        return f404055;
      }
      
      /**
       * 設定 f404055 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404055(Object value) {
        this.f404055 = value;
      }
      
      /**
       * 取得 f404056 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404056() {
        return f404056;
      }
      
      /**
       * 設定 f404056 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404056(Object value) {
        this.f404056 = value;
      }
      
      /**
       * 取得 f404057 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404057() {
        return f404057;
      }
      
      /**
       * 設定 f404057 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404057(Object value) {
        this.f404057 = value;
      }
      
      /**
       * 取得 f404058 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404058() {
        return f404058;
      }
      
      /**
       * 設定 f404058 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404058(Object value) {
        this.f404058 = value;
      }
      
      /**
       * 取得 f404059 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404059() {
        return f404059;
      }
      
      /**
       * 設定 f404059 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404059(Object value) {
        this.f404059 = value;
      }
      
      /**
       * 取得 f40405A 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40405A() {
        return f40405A;
      }
      
      /**
       * 設定 f40405A 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40405A(Object value) {
        this.f40405A = value;
      }
      
      /**
       * 取得 f40405B 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40405B() {
        return f40405B;
      }
      
      /**
       * 設定 f40405B 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40405B(Object value) {
        this.f40405B = value;
      }
      
      /**
       * 取得 f40405C 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40405C() {
        return f40405C;
      }
      
      /**
       * 設定 f40405C 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40405C(Object value) {
        this.f40405C = value;
      }
      
      /**
       * 取得 f40405D 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40405D() {
        return f40405D;
      }
      
      /**
       * 設定 f40405D 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40405D(Object value) {
        this.f40405D = value;
      }
      
      /**
       * 取得 f40405E 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40405E() {
        return f40405E;
      }
      
      /**
       * 設定 f40405E 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40405E(Object value) {
        this.f40405E = value;
      }
      
      /**
       * 取得 f40405F 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40405F() {
        return f40405F;
      }
      
      /**
       * 設定 f40405F 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40405F(Object value) {
        this.f40405F = value;
      }
      
      /**
       * 取得 f404060 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404060() {
        return f404060;
      }
      
      /**
       * 設定 f404060 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404060(Object value) {
        this.f404060 = value;
      }
      
      /**
       * 取得 f404061 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404061() {
        return f404061;
      }
      
      /**
       * 設定 f404061 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404061(Object value) {
        this.f404061 = value;
      }
      
      /**
       * 取得 f404062 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404062() {
        return f404062;
      }
      
      /**
       * 設定 f404062 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404062(Object value) {
        this.f404062 = value;
      }
      
      /**
       * 取得 f404063 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404063() {
        return f404063;
      }
      
      /**
       * 設定 f404063 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404063(Object value) {
        this.f404063 = value;
      }
      
      /**
       * 取得 f404064 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404064() {
        return f404064;
      }
      
      /**
       * 設定 f404064 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404064(Object value) {
        this.f404064 = value;
      }
      
      /**
       * 取得 f404065 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404065() {
        return f404065;
      }
      
      /**
       * 設定 f404065 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404065(Object value) {
        this.f404065 = value;
      }
      
      /**
       * 取得 f404066 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404066() {
        return f404066;
      }
      
      /**
       * 設定 f404066 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404066(Object value) {
        this.f404066 = value;
      }
      
      /**
       * 取得 f404067 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404067() {
        return f404067;
      }
      
      /**
       * 設定 f404067 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404067(Object value) {
        this.f404067 = value;
      }
      
      /**
       * 取得 f404068 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404068() {
        return f404068;
      }
      
      /**
       * 設定 f404068 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404068(Object value) {
        this.f404068 = value;
      }
      
      /**
       * 取得 f404069 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404069() {
        return f404069;
      }
      
      /**
       * 設定 f404069 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404069(Object value) {
        this.f404069 = value;
      }
      
      /**
       * 取得 f40406A 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40406A() {
        return f40406A;
      }
      
      /**
       * 設定 f40406A 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40406A(Object value) {
        this.f40406A = value;
      }
      
      /**
       * 取得 f40406B 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40406B() {
        return f40406B;
      }
      
      /**
       * 設定 f40406B 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40406B(Object value) {
        this.f40406B = value;
      }
      
      /**
       * 取得 f40406C 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40406C() {
        return f40406C;
      }
      
      /**
       * 設定 f40406C 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40406C(Object value) {
        this.f40406C = value;
      }
      
      /**
       * 取得 f40406D 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40406D() {
        return f40406D;
      }
      
      /**
       * 設定 f40406D 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40406D(Object value) {
        this.f40406D = value;
      }
      
      /**
       * 取得 f40406E 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40406E() {
        return f40406E;
      }
      
      /**
       * 設定 f40406E 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40406E(Object value) {
        this.f40406E = value;
      }
      
      /**
       * 取得 f40406F 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40406F() {
        return f40406F;
      }
      
      /**
       * 設定 f40406F 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40406F(Object value) {
        this.f40406F = value;
      }
      
      /**
       * 取得 f404070 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404070() {
        return f404070;
      }
      
      /**
       * 設定 f404070 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404070(Object value) {
        this.f404070 = value;
      }
      
      /**
       * 取得 f404071 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404071() {
        return f404071;
      }
      
      /**
       * 設定 f404071 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404071(Object value) {
        this.f404071 = value;
      }
      
      /**
       * 取得 f404072 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404072() {
        return f404072;
      }
      
      /**
       * 設定 f404072 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404072(Object value) {
        this.f404072 = value;
      }
      
      /**
       * 取得 f404073 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404073() {
        return f404073;
      }
      
      /**
       * 設定 f404073 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404073(Object value) {
        this.f404073 = value;
      }
      
      /**
       * 取得 f404074 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404074() {
        return f404074;
      }
      
      /**
       * 設定 f404074 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404074(Object value) {
        this.f404074 = value;
      }
      
      /**
       * 取得 f404075 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404075() {
        return f404075;
      }
      
      /**
       * 設定 f404075 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404075(Object value) {
        this.f404075 = value;
      }
      
      /**
       * 取得 f404076 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404076() {
        return f404076;
      }
      
      /**
       * 設定 f404076 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404076(Object value) {
        this.f404076 = value;
      }
      
      /**
       * 取得 f404077 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404077() {
        return f404077;
      }
      
      /**
       * 設定 f404077 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404077(Object value) {
        this.f404077 = value;
      }
      
      /**
       * 取得 f404078 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404078() {
        return f404078;
      }
      
      /**
       * 設定 f404078 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404078(Object value) {
        this.f404078 = value;
      }
      
      /**
       * 取得 f404079 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404079() {
        return f404079;
      }
      
      /**
       * 設定 f404079 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404079(Object value) {
        this.f404079 = value;
      }
      
      /**
       * 取得 f40407A 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40407A() {
        return f40407A;
      }
      
      /**
       * 設定 f40407A 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40407A(Object value) {
        this.f40407A = value;
      }
      
      /**
       * 取得 f40407B 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40407B() {
        return f40407B;
      }
      
      /**
       * 設定 f40407B 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40407B(Object value) {
        this.f40407B = value;
      }
      
      /**
       * 取得 f40407C 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40407C() {
        return f40407C;
      }
      
      /**
       * 設定 f40407C 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40407C(Object value) {
        this.f40407C = value;
      }
      
      /**
       * 取得 f40407D 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40407D() {
        return f40407D;
      }
      
      /**
       * 設定 f40407D 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40407D(Object value) {
        this.f40407D = value;
      }
      
      /**
       * 取得 f40407E 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40407E() {
        return f40407E;
      }
      
      /**
       * 設定 f40407E 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40407E(Object value) {
        this.f40407E = value;
      }
      
      /**
       * 取得 f40407F 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40407F() {
        return f40407F;
      }
      
      /**
       * 設定 f40407F 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40407F(Object value) {
        this.f40407F = value;
      }
      
      /**
       * 取得 f404080 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404080() {
        return f404080;
      }
      
      /**
       * 設定 f404080 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404080(Object value) {
        this.f404080 = value;
      }
      
      /**
       * 取得 f404081 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404081() {
        return f404081;
      }
      
      /**
       * 設定 f404081 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404081(Object value) {
        this.f404081 = value;
      }
      
      /**
       * 取得 f404082 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404082() {
        return f404082;
      }
      
      /**
       * 設定 f404082 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404082(Object value) {
        this.f404082 = value;
      }
      
      /**
       * 取得 f404083 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404083() {
        return f404083;
      }
      
      /**
       * 設定 f404083 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404083(Object value) {
        this.f404083 = value;
      }
      
      /**
       * 取得 f404084 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404084() {
        return f404084;
      }
      
      /**
       * 設定 f404084 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404084(Object value) {
        this.f404084 = value;
      }
      
      /**
       * 取得 f404085 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404085() {
        return f404085;
      }
      
      /**
       * 設定 f404085 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404085(Object value) {
        this.f404085 = value;
      }
      
      /**
       * 取得 f404086 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404086() {
        return f404086;
      }
      
      /**
       * 設定 f404086 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404086(Object value) {
        this.f404086 = value;
      }
      
      /**
       * 取得 f404087 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404087() {
        return f404087;
      }
      
      /**
       * 設定 f404087 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404087(Object value) {
        this.f404087 = value;
      }
      
      /**
       * 取得 f404088 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404088() {
        return f404088;
      }
      
      /**
       * 設定 f404088 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404088(Object value) {
        this.f404088 = value;
      }
      
      /**
       * 取得 f404089 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404089() {
        return f404089;
      }
      
      /**
       * 設定 f404089 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404089(Object value) {
        this.f404089 = value;
      }
      
      /**
       * 取得 f40408A 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40408A() {
        return f40408A;
      }
      
      /**
       * 設定 f40408A 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40408A(Object value) {
        this.f40408A = value;
      }
      
      /**
       * 取得 f40408B 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40408B() {
        return f40408B;
      }
      
      /**
       * 設定 f40408B 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40408B(Object value) {
        this.f40408B = value;
      }
      
      /**
       * 取得 f40408C 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40408C() {
        return f40408C;
      }
      
      /**
       * 設定 f40408C 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40408C(Object value) {
        this.f40408C = value;
      }
      
      /**
       * 取得 f40408D 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40408D() {
        return f40408D;
      }
      
      /**
       * 設定 f40408D 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40408D(Object value) {
        this.f40408D = value;
      }
      
      /**
       * 取得 f40408E 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40408E() {
        return f40408E;
      }
      
      /**
       * 設定 f40408E 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40408E(Object value) {
        this.f40408E = value;
      }
      
      /**
       * 取得 f40408F 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40408F() {
        return f40408F;
      }
      
      /**
       * 設定 f40408F 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40408F(Object value) {
        this.f40408F = value;
      }
      
      /**
       * 取得 f404090 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404090() {
        return f404090;
      }
      
      /**
       * 設定 f404090 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404090(Object value) {
        this.f404090 = value;
      }
      
      /**
       * 取得 f404091 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404091() {
        return f404091;
      }
      
      /**
       * 設定 f404091 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404091(Object value) {
        this.f404091 = value;
      }
      
      /**
       * 取得 f404092 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404092() {
        return f404092;
      }
      
      /**
       * 設定 f404092 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404092(Object value) {
        this.f404092 = value;
      }
      
      /**
       * 取得 f404093 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404093() {
        return f404093;
      }
      
      /**
       * 設定 f404093 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404093(Object value) {
        this.f404093 = value;
      }
      
      /**
       * 取得 f404094 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404094() {
        return f404094;
      }
      
      /**
       * 設定 f404094 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404094(Object value) {
        this.f404094 = value;
      }
      
      /**
       * 取得 f404095 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404095() {
        return f404095;
      }
      
      /**
       * 設定 f404095 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404095(Object value) {
        this.f404095 = value;
      }
      
      /**
       * 取得 f404096 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404096() {
        return f404096;
      }
      
      /**
       * 設定 f404096 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404096(Object value) {
        this.f404096 = value;
      }
      
      /**
       * 取得 f404097 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404097() {
        return f404097;
      }
      
      /**
       * 設定 f404097 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404097(Object value) {
        this.f404097 = value;
      }
      
      /**
       * 取得 f404098 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404098() {
        return f404098;
      }
      
      /**
       * 設定 f404098 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404098(Object value) {
        this.f404098 = value;
      }
      
      /**
       * 取得 f404099 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF404099() {
        return f404099;
      }
      
      /**
       * 設定 f404099 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF404099(Object value) {
        this.f404099 = value;
      }
      
      /**
       * 取得 f40409A 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40409A() {
        return f40409A;
      }
      
      /**
       * 設定 f40409A 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40409A(Object value) {
        this.f40409A = value;
      }
      
      /**
       * 取得 f40409B 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40409B() {
        return f40409B;
      }
      
      /**
       * 設定 f40409B 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40409B(Object value) {
        this.f40409B = value;
      }
      
      /**
       * 取得 f40409C 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40409C() {
        return f40409C;
      }
      
      /**
       * 設定 f40409C 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40409C(Object value) {
        this.f40409C = value;
      }
      
      /**
       * 取得 f40409D 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40409D() {
        return f40409D;
      }
      
      /**
       * 設定 f40409D 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40409D(Object value) {
        this.f40409D = value;
      }
      
      /**
       * 取得 f40409E 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40409E() {
        return f40409E;
      }
      
      /**
       * 設定 f40409E 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40409E(Object value) {
        this.f40409E = value;
      }
      
      /**
       * 取得 f40409F 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF40409F() {
        return f40409F;
      }
      
      /**
       * 設定 f40409F 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF40409F(Object value) {
        this.f40409F = value;
      }
      
      /**
       * 取得 f4040A0 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040A0() {
        return f4040A0;
      }
      
      /**
       * 設定 f4040A0 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040A0(Object value) {
        this.f4040A0 = value;
      }
      
      /**
       * 取得 f4040A1 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040A1() {
        return f4040A1;
      }
      
      /**
       * 設定 f4040A1 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040A1(Object value) {
        this.f4040A1 = value;
      }
      
      /**
       * 取得 f4040A2 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040A2() {
        return f4040A2;
      }
      
      /**
       * 設定 f4040A2 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040A2(Object value) {
        this.f4040A2 = value;
      }
      
      /**
       * 取得 f4040A3 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040A3() {
        return f4040A3;
      }
      
      /**
       * 設定 f4040A3 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040A3(Object value) {
        this.f4040A3 = value;
      }
      
      /**
       * 取得 f4040A4 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040A4() {
        return f4040A4;
      }
      
      /**
       * 設定 f4040A4 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040A4(Object value) {
        this.f4040A4 = value;
      }
      
      /**
       * 取得 f4040A5 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040A5() {
        return f4040A5;
      }
      
      /**
       * 設定 f4040A5 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040A5(Object value) {
        this.f4040A5 = value;
      }
      
      /**
       * 取得 f4040A6 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040A6() {
        return f4040A6;
      }
      
      /**
       * 設定 f4040A6 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040A6(Object value) {
        this.f4040A6 = value;
      }
      
      /**
       * 取得 f4040A7 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040A7() {
        return f4040A7;
      }
      
      /**
       * 設定 f4040A7 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040A7(Object value) {
        this.f4040A7 = value;
      }
      
      /**
       * 取得 f4040A8 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040A8() {
        return f4040A8;
      }
      
      /**
       * 設定 f4040A8 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040A8(Object value) {
        this.f4040A8 = value;
      }
      
      /**
       * 取得 f4040A9 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040A9() {
        return f4040A9;
      }
      
      /**
       * 設定 f4040A9 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040A9(Object value) {
        this.f4040A9 = value;
      }
      
      /**
       * 取得 f4040Aa 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040Aa() {
        return f4040Aa;
      }
      
      /**
       * 設定 f4040Aa 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040Aa(Object value) {
        this.f4040Aa = value;
      }
      
      /**
       * 取得 f4040Ab 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040Ab() {
        return f4040Ab;
      }
      
      /**
       * 設定 f4040Ab 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040Ab(Object value) {
        this.f4040Ab = value;
      }
      
      /**
       * 取得 f4040Ac 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040Ac() {
        return f4040Ac;
      }
      
      /**
       * 設定 f4040Ac 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040Ac(Object value) {
        this.f4040Ac = value;
      }
      
      /**
       * 取得 f4040Ad 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040Ad() {
        return f4040Ad;
      }
      
      /**
       * 設定 f4040Ad 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040Ad(Object value) {
        this.f4040Ad = value;
      }
      
      /**
       * 取得 f4040Ae 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040Ae() {
        return f4040Ae;
      }
      
      /**
       * 設定 f4040Ae 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040Ae(Object value) {
        this.f4040Ae = value;
      }
      
      /**
       * 取得 f4040Af 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040Af() {
        return f4040Af;
      }
      
      /**
       * 設定 f4040Af 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040Af(Object value) {
        this.f4040Af = value;
      }
      
      /**
       * 取得 f4040B0 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040B0() {
        return f4040B0;
      }
      
      /**
       * 設定 f4040B0 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040B0(Object value) {
        this.f4040B0 = value;
      }
      
      /**
       * 取得 f4040B1 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040B1() {
        return f4040B1;
      }
      
      /**
       * 設定 f4040B1 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040B1(Object value) {
        this.f4040B1 = value;
      }
      
      /**
       * 取得 f4040B2 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040B2() {
        return f4040B2;
      }
      
      /**
       * 設定 f4040B2 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040B2(Object value) {
        this.f4040B2 = value;
      }
      
      /**
       * 取得 f4040B3 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040B3() {
        return f4040B3;
      }
      
      /**
       * 設定 f4040B3 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040B3(Object value) {
        this.f4040B3 = value;
      }
      
      /**
       * 取得 f4040B4 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040B4() {
        return f4040B4;
      }
      
      /**
       * 設定 f4040B4 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040B4(Object value) {
        this.f4040B4 = value;
      }
      
      /**
       * 取得 f4040B5 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040B5() {
        return f4040B5;
      }
      
      /**
       * 設定 f4040B5 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040B5(Object value) {
        this.f4040B5 = value;
      }
      
      /**
       * 取得 f4040B6 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040B6() {
        return f4040B6;
      }
      
      /**
       * 設定 f4040B6 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040B6(Object value) {
        this.f4040B6 = value;
      }
      
      /**
       * 取得 f4040B7 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040B7() {
        return f4040B7;
      }
      
      /**
       * 設定 f4040B7 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040B7(Object value) {
        this.f4040B7 = value;
      }
      
      /**
       * 取得 f4040B8 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040B8() {
        return f4040B8;
      }
      
      /**
       * 設定 f4040B8 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040B8(Object value) {
        this.f4040B8 = value;
      }
      
      /**
       * 取得 f4040B9 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040B9() {
        return f4040B9;
      }
      
      /**
       * 設定 f4040B9 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040B9(Object value) {
        this.f4040B9 = value;
      }
      
      /**
       * 取得 f4040Ba 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040Ba() {
        return f4040Ba;
      }
      
      /**
       * 設定 f4040Ba 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040Ba(Object value) {
        this.f4040Ba = value;
      }
      
      /**
       * 取得 f4040Bb 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040Bb() {
        return f4040Bb;
      }
      
      /**
       * 設定 f4040Bb 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040Bb(Object value) {
        this.f4040Bb = value;
      }
      
      /**
       * 取得 f4040Bc 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040Bc() {
        return f4040Bc;
      }
      
      /**
       * 設定 f4040Bc 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040Bc(Object value) {
        this.f4040Bc = value;
      }
      
      /**
       * 取得 f4040Bd 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040Bd() {
        return f4040Bd;
      }
      
      /**
       * 設定 f4040Bd 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040Bd(Object value) {
        this.f4040Bd = value;
      }
      
      /**
       * 取得 f4040Be 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040Be() {
        return f4040Be;
      }
      
      /**
       * 設定 f4040Be 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040Be(Object value) {
        this.f4040Be = value;
      }
      
      /**
       * 取得 f4040Bf 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040Bf() {
        return f4040Bf;
      }
      
      /**
       * 設定 f4040Bf 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040Bf(Object value) {
        this.f4040Bf = value;
      }
      
      /**
       * 取得 f4040C0 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040C0() {
        return f4040C0;
      }
      
      /**
       * 設定 f4040C0 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040C0(Object value) {
        this.f4040C0 = value;
      }
      
      /**
       * 取得 f4040C1 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040C1() {
        return f4040C1;
      }
      
      /**
       * 設定 f4040C1 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040C1(Object value) {
        this.f4040C1 = value;
      }
      
      /**
       * 取得 f4040C2 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040C2() {
        return f4040C2;
      }
      
      /**
       * 設定 f4040C2 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040C2(Object value) {
        this.f4040C2 = value;
      }
      
      /**
       * 取得 f4040C3 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040C3() {
        return f4040C3;
      }
      
      /**
       * 設定 f4040C3 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040C3(Object value) {
        this.f4040C3 = value;
      }
      
      /**
       * 取得 f4040C4 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040C4() {
        return f4040C4;
      }
      
      /**
       * 設定 f4040C4 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040C4(Object value) {
        this.f4040C4 = value;
      }
      
      /**
       * 取得 f4040C5 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040C5() {
        return f4040C5;
      }
      
      /**
       * 設定 f4040C5 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040C5(Object value) {
        this.f4040C5 = value;
      }
      
      /**
       * 取得 f4040C6 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040C6() {
        return f4040C6;
      }
      
      /**
       * 設定 f4040C6 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040C6(Object value) {
        this.f4040C6 = value;
      }
      
      /**
       * 取得 f4040C7 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040C7() {
        return f4040C7;
      }
      
      /**
       * 設定 f4040C7 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040C7(Object value) {
        this.f4040C7 = value;
      }
      
      /**
       * 取得 f4040C8 特性的值.
       *
       * @return possible object is {@link Object }
       */
      public Object getF4040C8() {
        return f4040C8;
      }
      
      /**
       * 設定 f4040C8 特性的值.
       *
       * @param value allowed object is {@link Object }
       */
      public void setF4040C8(Object value) {
        this.f4040C8 = value;
      }
      
      /**
       * Gets the value of the timephasedData property.
       *
       * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
       * modification you make to the returned list will be present inside the JAXB object. This is
       * why there is not a <CODE>set</CODE> method for the timephasedData property.
       *
       * <p>For example, to add a new item, do as follows:
       *
       * <pre>
       *    getTimephasedData().add(newItem);
       * </pre>
       *
       * <p>Objects of the following type(s) are allowed in the list {@link TimephasedDataType }
       */
      public List<TimephasedDataType> getTimephasedData() {
        if (timephasedData == null) {
          timephasedData = new ArrayList<TimephasedDataType>();
        }
        return this.timephasedData;
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
        name = "",
        propOrder = {
          "timephasedData", "number", "start", "finish",
          "work", "cost", "bcws", "bcwp"
        })
      public static class Baseline {
        
        @XmlElement(name = "TimephasedData")
        protected List<TimephasedDataType> timephasedData;
        
        @XmlElement(name = "Number", required = true)
        protected String number;
        
        @XmlElement(name = "Start")
        protected String start;
        
        @XmlElement(name = "Finish")
        protected String finish;
        
        @XmlElement(name = "Work")
        protected Duration work;
        
        @XmlElement(name = "Cost")
        protected String cost;
        
        @XmlElement(name = "BCWS")
        protected Float bcws;
        
        @XmlElement(name = "BCWP")
        protected Float bcwp;
        
        /**
         * Gets the value of the timephasedData property.
         *
         * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore
         * any modification you make to the returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the timephasedData property.
         *
         * <p>For example, to add a new item, do as follows:
         *
         * <pre>
         *    getTimephasedData().add(newItem);
         * </pre>
         *
         * <p>Objects of the following type(s) are allowed in the list {@link TimephasedDataType }
         */
        public List<TimephasedDataType> getTimephasedData() {
          if (timephasedData == null) {
            timephasedData = new ArrayList<TimephasedDataType>();
          }
          return this.timephasedData;
        }
        
        /**
         * 取得 number 特性的值.
         *
         * @return possible object is {@link String }
         */
        public String getNumber() {
          return number;
        }
        
        /**
         * 設定 number 特性的值.
         *
         * @param value allowed object is {@link String }
         */
        public void setNumber(String value) {
          this.number = value;
        }
        
        /**
         * 取得 start 特性的值.
         *
         * @return possible object is {@link String }
         */
        public String getStart() {
          return start;
        }
        
        /**
         * 設定 start 特性的值.
         *
         * @param value allowed object is {@link String }
         */
        public void setStart(String value) {
          this.start = value;
        }
        
        /**
         * 取得 finish 特性的值.
         *
         * @return possible object is {@link String }
         */
        public String getFinish() {
          return finish;
        }
        
        /**
         * 設定 finish 特性的值.
         *
         * @param value allowed object is {@link String }
         */
        public void setFinish(String value) {
          this.finish = value;
        }
        
        /**
         * 取得 work 特性的值.
         *
         * @return possible object is {@link Duration }
         */
        public Duration getWork() {
          return work;
        }
        
        /**
         * 設定 work 特性的值.
         *
         * @param value allowed object is {@link Duration }
         */
        public void setWork(Duration value) {
          this.work = value;
        }
        
        /**
         * 取得 cost 特性的值.
         *
         * @return possible object is {@link String }
         */
        public String getCost() {
          return cost;
        }
        
        /**
         * 設定 cost 特性的值.
         *
         * @param value allowed object is {@link String }
         */
        public void setCost(String value) {
          this.cost = value;
        }
        
        /**
         * 取得 bcws 特性的值.
         *
         * @return possible object is {@link Float }
         */
        public Float getBCWS() {
          return bcws;
        }
        
        /**
         * 設定 bcws 特性的值.
         *
         * @param value allowed object is {@link Float }
         */
        public void setBCWS(Float value) {
          this.bcws = value;
        }
        
        /**
         * 取得 bcwp 特性的值.
         *
         * @return possible object is {@link Float }
         */
        public Float getBCWP() {
          return bcwp;
        }
        
        /**
         * 設定 bcwp 特性的值.
         *
         * @param value allowed object is {@link Float }
         */
        public void setBCWP(Float value) {
          this.bcwp = value;
        }
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
        name = "",
        propOrder = {"fieldID", "value", "valueGUID", "durationFormat"})
      public static class ExtendedAttribute {
        
        @XmlElement(name = "FieldID")
        protected String fieldID;
        
        @XmlElement(name = "Value")
        protected String value;
        
        @XmlElement(name = "ValueGUID")
        protected BigInteger valueGUID;
        
        @XmlElement(name = "DurationFormat")
        protected BigInteger durationFormat;
        
        /**
         * 取得 fieldID 特性的值.
         *
         * @return possible object is {@link String }
         */
        public String getFieldID() {
          return fieldID;
        }
        
        /**
         * 設定 fieldID 特性的值.
         *
         * @param value allowed object is {@link String }
         */
        public void setFieldID(String value) {
          this.fieldID = value;
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
        
        /**
         * 取得 valueGUID 特性的值.
         *
         * @return possible object is {@link BigInteger }
         */
        public BigInteger getValueGUID() {
          return valueGUID;
        }
        
        /**
         * 設定 valueGUID 特性的值.
         *
         * @param value allowed object is {@link BigInteger }
         */
        public void setValueGUID(BigInteger value) {
          this.valueGUID = value;
        }
        
        /**
         * 取得 durationFormat 特性的值.
         *
         * @return possible object is {@link BigInteger }
         */
        public BigInteger getDurationFormat() {
          return durationFormat;
        }
        
        /**
         * 設定 durationFormat 特性的值.
         *
         * @param value allowed object is {@link BigInteger }
         */
        public void setDurationFormat(BigInteger value) {
          this.durationFormat = value;
        }
      }
    }
  }
  
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(
    name = "",
    propOrder = {"calendar"})
  public static class Calendars {
    
    @XmlElement(name = "Calendar", required = true)
    protected List<Calendar> calendar;
    
    /**
     * Gets the value of the calendar property.
     *
     * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the calendar property.
     *
     * <p>For example, to add a new item, do as follows:
     *
     * <pre>
     *    getCalendar().add(newItem);
     * </pre>
     *
     * <p>Objects of the following type(s) are allowed in the list {@link Calendar }
     */
    public List<Calendar> getCalendar() {
      if (calendar == null) {
        calendar = new ArrayList<Calendar>();
      }
      return this.calendar;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(
      name = "",
      propOrder = {
        "uid",
        "name",
        "isBaseCalendar",
        "baseCalendarUID",
        "weekDays",
        "exceptions",
        "workWeeks"
      })
    public static class Calendar {
      
      @XmlElement(name = "UID", required = true)
      protected BigInteger uid;
      
      @XmlElement(name = "Name")
      protected String name;
      
      @XmlElement(name = "IsBaseCalendar")
      protected Boolean isBaseCalendar;
      
      @XmlElement(name = "BaseCalendarUID")
      protected BigInteger baseCalendarUID;
      
      @XmlElement(name = "WeekDays")
      protected WeekDays weekDays;
      
      @XmlElement(name = "Exceptions")
      protected Exceptions exceptions;
      
      @XmlElement(name = "WorkWeeks")
      protected WorkWeeks workWeeks;
      
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
       * 取得 name 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getName() {
        return name;
      }
      
      /**
       * 設定 name 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setName(String value) {
        this.name = value;
      }
      
      /**
       * 取得 isBaseCalendar 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isIsBaseCalendar() {
        return isBaseCalendar;
      }
      
      /**
       * 設定 isBaseCalendar 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setIsBaseCalendar(Boolean value) {
        this.isBaseCalendar = value;
      }
      
      /**
       * 取得 baseCalendarUID 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getBaseCalendarUID() {
        return baseCalendarUID;
      }
      
      /**
       * 設定 baseCalendarUID 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setBaseCalendarUID(BigInteger value) {
        this.baseCalendarUID = value;
      }
      
      /**
       * 取得 weekDays 特性的值.
       *
       * @return possible object is {@link WeekDays }
       */
      public WeekDays getWeekDays() {
        return weekDays;
      }
      
      /**
       * 設定 weekDays 特性的值.
       *
       * @param value allowed object is {@link WeekDays }
       */
      public void setWeekDays(WeekDays value) {
        this.weekDays = value;
      }
      
      /**
       * 取得 exceptions 特性的值.
       *
       * @return possible object is {@link Exceptions }
       */
      public Exceptions getExceptions() {
        return exceptions;
      }
      
      /**
       * 設定 exceptions 特性的值.
       *
       * @param value allowed object is {@link Exceptions }
       */
      public void setExceptions(Exceptions value) {
        this.exceptions = value;
      }
      
      /**
       * 取得 workWeeks 特性的值.
       *
       * @return possible object is {@link WorkWeeks }
       */
      public WorkWeeks getWorkWeeks() {
        return workWeeks;
      }
      
      /**
       * 設定 workWeeks 特性的值.
       *
       * @param value allowed object is {@link WorkWeeks }
       */
      public void setWorkWeeks(WorkWeeks value) {
        this.workWeeks = value;
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
        name = "",
        propOrder = {"exception"})
      public static class Exceptions {
        
        @XmlElement(name = "Exception")
        protected List<Exception> exception;
        
        /**
         * Gets the value of the exception property.
         *
         * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore
         * any modification you make to the returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the exception property.
         *
         * <p>For example, to add a new item, do as follows:
         *
         * <pre>
         *    getException().add(newItem);
         * </pre>
         *
         * <p>Objects of the following type(s) are allowed in the list {@link Exception }
         */
        public List<Exception> getException() {
          if (exception == null) {
            exception = new ArrayList<Exception>();
          }
          return this.exception;
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(
          name = "",
          propOrder = {
            "enteredByOccurrences",
            "timePeriod",
            "occurrences",
            "name",
            "type",
            "period",
            "daysOfWeek",
            "monthItem",
            "monthPosition",
            "month",
            "monthDay",
            "dayWorking",
            "workingTimes"
          })
        public static class Exception {
          
          @XmlElement(name = "EnteredByOccurrences")
          protected Boolean enteredByOccurrences;
          
          @XmlElement(name = "TimePeriod")
          protected TimePeriod timePeriod;
          
          @XmlElement(name = "Occurrences")
          protected BigInteger occurrences;
          
          @XmlElement(name = "Name")
          protected String name;
          
          @XmlElement(name = "Type")
          protected BigInteger type;
          
          @XmlElement(name = "Period")
          protected BigInteger period;
          
          @XmlElement(name = "DaysOfWeek")
          protected BigInteger daysOfWeek;
          
          @XmlElement(name = "MonthItem")
          protected BigInteger monthItem;
          
          @XmlElement(name = "MonthPosition")
          protected BigInteger monthPosition;
          
          @XmlElement(name = "Month")
          protected BigInteger month;
          
          @XmlElement(name = "MonthDay")
          protected BigInteger monthDay;
          
          @XmlElement(name = "DayWorking")
          protected Boolean dayWorking;
          
          @XmlElement(name = "WorkingTimes")
          protected WorkingTimes workingTimes;
          
          /**
           * 取得 enteredByOccurrences 特性的值.
           *
           * @return possible object is {@link Boolean }
           */
          public Boolean isEnteredByOccurrences() {
            return enteredByOccurrences;
          }
          
          /**
           * 設定 enteredByOccurrences 特性的值.
           *
           * @param value allowed object is {@link Boolean }
           */
          public void setEnteredByOccurrences(Boolean value) {
            this.enteredByOccurrences = value;
          }
          
          /**
           * 取得 timePeriod 特性的值.
           *
           * @return possible object is {@link TimePeriod }
           */
          public TimePeriod getTimePeriod() {
            return timePeriod;
          }
          
          /**
           * 設定 timePeriod 特性的值.
           *
           * @param value allowed object is {@link TimePeriod }
           */
          public void setTimePeriod(TimePeriod value) {
            this.timePeriod = value;
          }
          
          /**
           * 取得 occurrences 特性的值.
           *
           * @return possible object is {@link BigInteger }
           */
          public BigInteger getOccurrences() {
            return occurrences;
          }
          
          /**
           * 設定 occurrences 特性的值.
           *
           * @param value allowed object is {@link BigInteger }
           */
          public void setOccurrences(BigInteger value) {
            this.occurrences = value;
          }
          
          /**
           * 取得 name 特性的值.
           *
           * @return possible object is {@link String }
           */
          public String getName() {
            return name;
          }
          
          /**
           * 設定 name 特性的值.
           *
           * @param value allowed object is {@link String }
           */
          public void setName(String value) {
            this.name = value;
          }
          
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
           * 取得 period 特性的值.
           *
           * @return possible object is {@link BigInteger }
           */
          public BigInteger getPeriod() {
            return period;
          }
          
          /**
           * 設定 period 特性的值.
           *
           * @param value allowed object is {@link BigInteger }
           */
          public void setPeriod(BigInteger value) {
            this.period = value;
          }
          
          /**
           * 取得 daysOfWeek 特性的值.
           *
           * @return possible object is {@link BigInteger }
           */
          public BigInteger getDaysOfWeek() {
            return daysOfWeek;
          }
          
          /**
           * 設定 daysOfWeek 特性的值.
           *
           * @param value allowed object is {@link BigInteger }
           */
          public void setDaysOfWeek(BigInteger value) {
            this.daysOfWeek = value;
          }
          
          /**
           * 取得 monthItem 特性的值.
           *
           * @return possible object is {@link BigInteger }
           */
          public BigInteger getMonthItem() {
            return monthItem;
          }
          
          /**
           * 設定 monthItem 特性的值.
           *
           * @param value allowed object is {@link BigInteger }
           */
          public void setMonthItem(BigInteger value) {
            this.monthItem = value;
          }
          
          /**
           * 取得 monthPosition 特性的值.
           *
           * @return possible object is {@link BigInteger }
           */
          public BigInteger getMonthPosition() {
            return monthPosition;
          }
          
          /**
           * 設定 monthPosition 特性的值.
           *
           * @param value allowed object is {@link BigInteger }
           */
          public void setMonthPosition(BigInteger value) {
            this.monthPosition = value;
          }
          
          /**
           * 取得 month 特性的值.
           *
           * @return possible object is {@link BigInteger }
           */
          public BigInteger getMonth() {
            return month;
          }
          
          /**
           * 設定 month 特性的值.
           *
           * @param value allowed object is {@link BigInteger }
           */
          public void setMonth(BigInteger value) {
            this.month = value;
          }
          
          /**
           * 取得 monthDay 特性的值.
           *
           * @return possible object is {@link BigInteger }
           */
          public BigInteger getMonthDay() {
            return monthDay;
          }
          
          /**
           * 設定 monthDay 特性的值.
           *
           * @param value allowed object is {@link BigInteger }
           */
          public void setMonthDay(BigInteger value) {
            this.monthDay = value;
          }
          
          /**
           * 取得 dayWorking 特性的值.
           *
           * @return possible object is {@link Boolean }
           */
          public Boolean isDayWorking() {
            return dayWorking;
          }
          
          /**
           * 設定 dayWorking 特性的值.
           *
           * @param value allowed object is {@link Boolean }
           */
          public void setDayWorking(Boolean value) {
            this.dayWorking = value;
          }
          
          /**
           * 取得 workingTimes 特性的值.
           *
           * @return possible object is {@link WorkingTimes }
           */
          public WorkingTimes getWorkingTimes() {
            return workingTimes;
          }
          
          /**
           * 設定 workingTimes 特性的值.
           *
           * @param value allowed object is {@link WorkingTimes }
           */
          public void setWorkingTimes(WorkingTimes value) {
            this.workingTimes = value;
          }
          
          @XmlAccessorType(XmlAccessType.FIELD)
          @XmlType(
            name = "",
            propOrder = {"fromDate", "toDate"})
          public static class TimePeriod {
            
            @XmlElement(name = "FromDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar fromDate;
            
            @XmlElement(name = "ToDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar toDate;
            
            /**
             * 取得 fromDate 特性的值.
             *
             * @return possible object is {@link XMLGregorianCalendar }
             */
            public XMLGregorianCalendar getFromDate() {
              return fromDate;
            }
            
            /**
             * 設定 fromDate 特性的值.
             *
             * @param value allowed object is {@link XMLGregorianCalendar }
             */
            public void setFromDate(XMLGregorianCalendar value) {
              this.fromDate = value;
            }
            
            /**
             * 取得 toDate 特性的值.
             *
             * @return possible object is {@link XMLGregorianCalendar }
             */
            public XMLGregorianCalendar getToDate() {
              return toDate;
            }
            
            /**
             * 設定 toDate 特性的值.
             *
             * @param value allowed object is {@link XMLGregorianCalendar }
             */
            public void setToDate(XMLGregorianCalendar value) {
              this.toDate = value;
            }
          }
          
          @XmlAccessorType(XmlAccessType.FIELD)
          @XmlType(
            name = "",
            propOrder = {"workingTime"})
          public static class WorkingTimes {
            
            @XmlElement(name = "WorkingTime")
            protected List<WorkingTime> workingTime;
            
            /**
             * Gets the value of the workingTime property.
             *
             * <p>This accessor method returns a reference to the live list, not a snapshot.
             * Therefore any modification you make to the returned list will be present inside the
             * JAXB object. This is why there is not a <CODE>set</CODE> method for the workingTime
             * property.
             *
             * <p>For example, to add a new item, do as follows:
             *
             * <pre>
             *    getWorkingTime().add(newItem);
             * </pre>
             *
             * <p>Objects of the following type(s) are allowed in the list {@link WorkingTime }
             */
            public List<WorkingTime> getWorkingTime() {
              if (workingTime == null) {
                workingTime = new ArrayList<WorkingTime>();
              }
              return this.workingTime;
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(
              name = "",
              propOrder = {"fromTime", "toTime"})
            public static class WorkingTime {
              
              @XmlElement(name = "FromTime")
              @XmlSchemaType(name = "time")
              protected XMLGregorianCalendar fromTime;
              
              @XmlElement(name = "ToTime")
              @XmlSchemaType(name = "time")
              protected XMLGregorianCalendar toTime;
              
              /**
               * 取得 fromTime 特性的值.
               *
               * @return possible object is {@link XMLGregorianCalendar }
               */
              public XMLGregorianCalendar getFromTime() {
                return fromTime;
              }
              
              /**
               * 設定 fromTime 特性的值.
               *
               * @param value allowed object is {@link XMLGregorianCalendar }
               */
              public void setFromTime(XMLGregorianCalendar value) {
                this.fromTime = value;
              }
              
              /**
               * 取得 toTime 特性的值.
               *
               * @return possible object is {@link XMLGregorianCalendar }
               */
              public XMLGregorianCalendar getToTime() {
                return toTime;
              }
              
              /**
               * 設定 toTime 特性的值.
               *
               * @param value allowed object is {@link XMLGregorianCalendar }
               */
              public void setToTime(XMLGregorianCalendar value) {
                this.toTime = value;
              }
            }
          }
        }
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
        name = "",
        propOrder = {"weekDay"})
      public static class WeekDays {
        
        @XmlElement(name = "WeekDay")
        protected List<WeekDay> weekDay;
        
        /**
         * Gets the value of the weekDay property.
         *
         * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore
         * any modification you make to the returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the weekDay property.
         *
         * <p>For example, to add a new item, do as follows:
         *
         * <pre>
         *    getWeekDay().add(newItem);
         * </pre>
         *
         * <p>Objects of the following type(s) are allowed in the list {@link WeekDay }
         */
        public List<WeekDay> getWeekDay() {
          if (weekDay == null) {
            weekDay = new ArrayList<WeekDay>();
          }
          return this.weekDay;
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(
          name = "",
          propOrder = {"dayType", "dayWorking", "timePeriod", "workingTimes"})
        public static class WeekDay {
          
          @XmlElement(name = "DayType", required = true)
          protected BigInteger dayType;
          
          @XmlElement(name = "DayWorking")
          protected Boolean dayWorking;
          
          @XmlElement(name = "TimePeriod")
          protected TimePeriod timePeriod;
          
          @XmlElement(name = "WorkingTimes")
          protected WorkingTimes workingTimes;
          
          /**
           * 取得 dayType 特性的值.
           *
           * @return possible object is {@link BigInteger }
           */
          public BigInteger getDayType() {
            return dayType;
          }
          
          /**
           * 設定 dayType 特性的值.
           *
           * @param value allowed object is {@link BigInteger }
           */
          public void setDayType(BigInteger value) {
            this.dayType = value;
          }
          
          /**
           * 取得 dayWorking 特性的值.
           *
           * @return possible object is {@link Boolean }
           */
          public Boolean isDayWorking() {
            return dayWorking;
          }
          
          /**
           * 設定 dayWorking 特性的值.
           *
           * @param value allowed object is {@link Boolean }
           */
          public void setDayWorking(Boolean value) {
            this.dayWorking = value;
          }
          
          /**
           * 取得 timePeriod 特性的值.
           *
           * @return possible object is {@link TimePeriod }
           */
          public TimePeriod getTimePeriod() {
            return timePeriod;
          }
          
          /**
           * 設定 timePeriod 特性的值.
           *
           * @param value allowed object is {@link TimePeriod }
           */
          public void setTimePeriod(TimePeriod value) {
            this.timePeriod = value;
          }
          
          /**
           * 取得 workingTimes 特性的值.
           *
           * @return possible object is {@link WorkingTimes }
           */
          public WorkingTimes getWorkingTimes() {
            return workingTimes;
          }
          
          /**
           * 設定 workingTimes 特性的值.
           *
           * @param value allowed object is {@link WorkingTimes }
           */
          public void setWorkingTimes(WorkingTimes value) {
            this.workingTimes = value;
          }
          
          @XmlAccessorType(XmlAccessType.FIELD)
          @XmlType(
            name = "",
            propOrder = {"fromDate", "toDate"})
          public static class TimePeriod {
            
            @XmlElement(name = "FromDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar fromDate;
            
            @XmlElement(name = "ToDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar toDate;
            
            /**
             * 取得 fromDate 特性的值.
             *
             * @return possible object is {@link XMLGregorianCalendar }
             */
            public XMLGregorianCalendar getFromDate() {
              return fromDate;
            }
            
            /**
             * 設定 fromDate 特性的值.
             *
             * @param value allowed object is {@link XMLGregorianCalendar }
             */
            public void setFromDate(XMLGregorianCalendar value) {
              this.fromDate = value;
            }
            
            /**
             * 取得 toDate 特性的值.
             *
             * @return possible object is {@link XMLGregorianCalendar }
             */
            public XMLGregorianCalendar getToDate() {
              return toDate;
            }
            
            /**
             * 設定 toDate 特性的值.
             *
             * @param value allowed object is {@link XMLGregorianCalendar }
             */
            public void setToDate(XMLGregorianCalendar value) {
              this.toDate = value;
            }
          }
          
          @XmlAccessorType(XmlAccessType.FIELD)
          @XmlType(
            name = "",
            propOrder = {"workingTime"})
          public static class WorkingTimes {
            
            @XmlElement(name = "WorkingTime")
            protected List<WorkingTime> workingTime;
            
            /**
             * Gets the value of the workingTime property.
             *
             * <p>This accessor method returns a reference to the live list, not a snapshot.
             * Therefore any modification you make to the returned list will be present inside the
             * JAXB object. This is why there is not a <CODE>set</CODE> method for the workingTime
             * property.
             *
             * <p>For example, to add a new item, do as follows:
             *
             * <pre>
             *    getWorkingTime().add(newItem);
             * </pre>
             *
             * <p>Objects of the following type(s) are allowed in the list {@link WorkingTime }
             */
            public List<WorkingTime> getWorkingTime() {
              if (workingTime == null) {
                workingTime = new ArrayList<WorkingTime>();
              }
              return this.workingTime;
            }
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(
              name = "",
              propOrder = {"fromTime", "toTime"})
            public static class WorkingTime {
              
              @XmlElement(name = "FromTime")
              @XmlSchemaType(name = "time")
              protected XMLGregorianCalendar fromTime;
              
              @XmlElement(name = "ToTime")
              @XmlSchemaType(name = "time")
              protected XMLGregorianCalendar toTime;
              
              /**
               * 取得 fromTime 特性的值.
               *
               * @return possible object is {@link XMLGregorianCalendar }
               */
              public XMLGregorianCalendar getFromTime() {
                return fromTime;
              }
              
              /**
               * 設定 fromTime 特性的值.
               *
               * @param value allowed object is {@link XMLGregorianCalendar }
               */
              public void setFromTime(XMLGregorianCalendar value) {
                this.fromTime = value;
              }
              
              /**
               * 取得 toTime 特性的值.
               *
               * @return possible object is {@link XMLGregorianCalendar }
               */
              public XMLGregorianCalendar getToTime() {
                return toTime;
              }
              
              /**
               * 設定 toTime 特性的值.
               *
               * @param value allowed object is {@link XMLGregorianCalendar }
               */
              public void setToTime(XMLGregorianCalendar value) {
                this.toTime = value;
              }
            }
          }
        }
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
        name = "",
        propOrder = {"workWeek"})
      public static class WorkWeeks {
        
        @XmlElement(name = "WorkWeek")
        protected List<WorkWeek> workWeek;
        
        /**
         * Gets the value of the workWeek property.
         *
         * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore
         * any modification you make to the returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the workWeek property.
         *
         * <p>For example, to add a new item, do as follows:
         *
         * <pre>
         *    getWorkWeek().add(newItem);
         * </pre>
         *
         * <p>Objects of the following type(s) are allowed in the list {@link WorkWeek }
         */
        public List<WorkWeek> getWorkWeek() {
          if (workWeek == null) {
            workWeek = new ArrayList<WorkWeek>();
          }
          return this.workWeek;
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(
          name = "",
          propOrder = {"timePeriod", "name", "weekDay"})
        public static class WorkWeek {
          
          @XmlElement(name = "TimePeriod")
          protected TimePeriod timePeriod;
          
          @XmlElement(name = "Name")
          protected String name;
          
          @XmlElement(name = "WeekDay")
          protected List<WeekDay> weekDay;
          
          /**
           * 取得 timePeriod 特性的值.
           *
           * @return possible object is {@link TimePeriod }
           */
          public TimePeriod getTimePeriod() {
            return timePeriod;
          }
          
          /**
           * 設定 timePeriod 特性的值.
           *
           * @param value allowed object is {@link TimePeriod }
           */
          public void setTimePeriod(TimePeriod value) {
            this.timePeriod = value;
          }
          
          /**
           * 取得 name 特性的值.
           *
           * @return possible object is {@link String }
           */
          public String getName() {
            return name;
          }
          
          /**
           * 設定 name 特性的值.
           *
           * @param value allowed object is {@link String }
           */
          public void setName(String value) {
            this.name = value;
          }
          
          /**
           * Gets the value of the weekDay property.
           *
           * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore
           * any modification you make to the returned list will be present inside the JAXB object.
           * This is why there is not a <CODE>set</CODE> method for the weekDay property.
           *
           * <p>For example, to add a new item, do as follows:
           *
           * <pre>
           *    getWeekDay().add(newItem);
           * </pre>
           *
           * <p>Objects of the following type(s) are allowed in the list {@link WeekDay }
           */
          public List<WeekDay> getWeekDay() {
            if (weekDay == null) {
              weekDay = new ArrayList<WeekDay>();
            }
            return this.weekDay;
          }
          
          /**
           * anonymous complex type 的 Java 類別.
           *
           * <p>下列綱要片段會指定此類別中包含的預期內容.
           *
           * <pre>
           * &lt;complexType>
           *   &lt;complexContent>
           *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
           *       &lt;sequence>
           *         &lt;element name="FromDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
           *         &lt;element name="ToDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
           *       &lt;/sequence>
           *     &lt;/restriction>
           *   &lt;/complexContent>
           * &lt;/complexType>
           * </pre>
           */
          @XmlAccessorType(XmlAccessType.FIELD)
          @XmlType(
            name = "",
            propOrder = {"fromDate", "toDate"})
          public static class TimePeriod {
            
            @XmlElement(name = "FromDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar fromDate;
            
            @XmlElement(name = "ToDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar toDate;
            
            /**
             * 取得 fromDate 特性的值.
             *
             * @return possible object is {@link XMLGregorianCalendar }
             */
            public XMLGregorianCalendar getFromDate() {
              return fromDate;
            }
            
            /**
             * 設定 fromDate 特性的值.
             *
             * @param value allowed object is {@link XMLGregorianCalendar }
             */
            public void setFromDate(XMLGregorianCalendar value) {
              this.fromDate = value;
            }
            
            /**
             * 取得 toDate 特性的值.
             *
             * @return possible object is {@link XMLGregorianCalendar }
             */
            public XMLGregorianCalendar getToDate() {
              return toDate;
            }
            
            /**
             * 設定 toDate 特性的值.
             *
             * @param value allowed object is {@link XMLGregorianCalendar }
             */
            public void setToDate(XMLGregorianCalendar value) {
              this.toDate = value;
            }
          }
          
          @XmlAccessorType(XmlAccessType.FIELD)
          @XmlType(
            name = "",
            propOrder = {"dayType", "dayWorking"})
          public static class WeekDay {
            
            @XmlElement(name = "DayType", required = true)
            protected BigInteger dayType;
            
            @XmlElement(name = "DayWorking")
            protected Boolean dayWorking;
            
            /**
             * 取得 dayType 特性的值.
             *
             * @return possible object is {@link BigInteger }
             */
            public BigInteger getDayType() {
              return dayType;
            }
            
            /**
             * 設定 dayType 特性的值.
             *
             * @param value allowed object is {@link BigInteger }
             */
            public void setDayType(BigInteger value) {
              this.dayType = value;
            }
            
            /**
             * 取得 dayWorking 特性的值.
             *
             * @return possible object is {@link Boolean }
             */
            public Boolean isDayWorking() {
              return dayWorking;
            }
            
            /**
             * 設定 dayWorking 特性的值.
             *
             * @param value allowed object is {@link Boolean }
             */
            public void setDayWorking(Boolean value) {
              this.dayWorking = value;
            }
          }
        }
      }
    }
  }
  
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(
    name = "",
    propOrder = {"extendedAttribute"})
  public static class ExtendedAttributes {
    
    @XmlElement(name = "ExtendedAttribute")
    protected List<ExtendedAttribute> extendedAttribute;
    
    /**
     * Gets the value of the extendedAttribute property.
     *
     * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the extendedAttribute property.
     *
     * <p>For example, to add a new item, do as follows:
     *
     * <pre>
     *    getExtendedAttribute().add(newItem);
     * </pre>
     *
     * <p>Objects of the following type(s) are allowed in the list {@link ExtendedAttribute }
     */
    public List<ExtendedAttribute> getExtendedAttribute() {
      if (extendedAttribute == null) {
        extendedAttribute = new ArrayList<ExtendedAttribute>();
      }
      return this.extendedAttribute;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(
      name = "",
      propOrder = {
        "fieldID",
        "fieldName",
        "cfType",
        "guid",
        "elemType",
        "maxMultiValues",
        "userDef",
        "alias",
        "secondaryPID",
        "autoRollDown",
        "defaultGuid",
        "ltuid",
        "phoneticAlias",
        "rollupType",
        "calculationType",
        "formula",
        "restrictValues",
        "valuelistSortOrder",
        "appendNewValues",
        "_default",
        "valueList"
      })
    public static class ExtendedAttribute {
      
      @XmlElement(name = "FieldID")
      protected String fieldID;
      
      @XmlElement(name = "FieldName")
      protected String fieldName;
      
      @XmlElement(name = "CFType")
      protected BigInteger cfType;
      
      @XmlElement(name = "Guid")
      protected String guid;
      
      @XmlElement(name = "ElemType")
      protected BigInteger elemType;
      
      @XmlElement(name = "MaxMultiValues")
      protected BigInteger maxMultiValues;
      
      @XmlElement(name = "UserDef")
      protected Boolean userDef;
      
      @XmlElement(name = "Alias")
      protected String alias;
      
      @XmlElement(name = "SecondaryPID")
      protected String secondaryPID;
      
      @XmlElement(name = "AutoRollDown")
      protected Boolean autoRollDown;
      
      @XmlElement(name = "DefaultGuid")
      protected String defaultGuid;
      
      @XmlElement(name = "Ltuid")
      protected String ltuid;
      
      @XmlElement(name = "PhoneticAlias")
      protected String phoneticAlias;
      
      @XmlElement(name = "RollupType")
      protected BigInteger rollupType;
      
      @XmlElement(name = "CalculationType")
      protected BigInteger calculationType;
      
      @XmlElement(name = "Formula")
      protected String formula;
      
      @XmlElement(name = "RestrictValues")
      protected Boolean restrictValues;
      
      @XmlElement(name = "ValuelistSortOrder")
      protected BigInteger valuelistSortOrder;
      
      @XmlElement(name = "AppendNewValues")
      protected Boolean appendNewValues;
      
      @XmlElement(name = "Default")
      protected String _default;
      
      @XmlElement(name = "ValueList")
      protected ValueList valueList;
      
      /**
       * 取得 fieldID 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getFieldID() {
        return fieldID;
      }
      
      /**
       * 設定 fieldID 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setFieldID(String value) {
        this.fieldID = value;
      }
      
      /**
       * 取得 fieldName 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getFieldName() {
        return fieldName;
      }
      
      /**
       * 設定 fieldName 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setFieldName(String value) {
        this.fieldName = value;
      }
      
      /**
       * 取得 cfType 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getCFType() {
        return cfType;
      }
      
      /**
       * 設定 cfType 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setCFType(BigInteger value) {
        this.cfType = value;
      }
      
      /**
       * 取得 guid 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getGuid() {
        return guid;
      }
      
      /**
       * 設定 guid 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setGuid(String value) {
        this.guid = value;
      }
      
      /**
       * 取得 elemType 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getElemType() {
        return elemType;
      }
      
      /**
       * 設定 elemType 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setElemType(BigInteger value) {
        this.elemType = value;
      }
      
      /**
       * 取得 maxMultiValues 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getMaxMultiValues() {
        return maxMultiValues;
      }
      
      /**
       * 設定 maxMultiValues 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setMaxMultiValues(BigInteger value) {
        this.maxMultiValues = value;
      }
      
      /**
       * 取得 userDef 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isUserDef() {
        return userDef;
      }
      
      /**
       * 設定 userDef 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setUserDef(Boolean value) {
        this.userDef = value;
      }
      
      /**
       * 取得 alias 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getAlias() {
        return alias;
      }
      
      /**
       * 設定 alias 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setAlias(String value) {
        this.alias = value;
      }
      
      /**
       * 取得 secondaryPID 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getSecondaryPID() {
        return secondaryPID;
      }
      
      /**
       * 設定 secondaryPID 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setSecondaryPID(String value) {
        this.secondaryPID = value;
      }
      
      /**
       * 取得 autoRollDown 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isAutoRollDown() {
        return autoRollDown;
      }
      
      /**
       * 設定 autoRollDown 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setAutoRollDown(Boolean value) {
        this.autoRollDown = value;
      }
      
      /**
       * 取得 defaultGuid 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getDefaultGuid() {
        return defaultGuid;
      }
      
      /**
       * 設定 defaultGuid 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setDefaultGuid(String value) {
        this.defaultGuid = value;
      }
      
      /**
       * 取得 ltuid 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getLtuid() {
        return ltuid;
      }
      
      /**
       * 設定 ltuid 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setLtuid(String value) {
        this.ltuid = value;
      }
      
      /**
       * 取得 phoneticAlias 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getPhoneticAlias() {
        return phoneticAlias;
      }
      
      /**
       * 設定 phoneticAlias 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setPhoneticAlias(String value) {
        this.phoneticAlias = value;
      }
      
      /**
       * 取得 rollupType 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getRollupType() {
        return rollupType;
      }
      
      /**
       * 設定 rollupType 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setRollupType(BigInteger value) {
        this.rollupType = value;
      }
      
      /**
       * 取得 calculationType 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getCalculationType() {
        return calculationType;
      }
      
      /**
       * 設定 calculationType 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setCalculationType(BigInteger value) {
        this.calculationType = value;
      }
      
      /**
       * 取得 formula 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getFormula() {
        return formula;
      }
      
      /**
       * 設定 formula 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setFormula(String value) {
        this.formula = value;
      }
      
      /**
       * 取得 restrictValues 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isRestrictValues() {
        return restrictValues;
      }
      
      /**
       * 設定 restrictValues 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setRestrictValues(Boolean value) {
        this.restrictValues = value;
      }
      
      /**
       * 取得 valuelistSortOrder 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getValuelistSortOrder() {
        return valuelistSortOrder;
      }
      
      /**
       * 設定 valuelistSortOrder 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setValuelistSortOrder(BigInteger value) {
        this.valuelistSortOrder = value;
      }
      
      /**
       * 取得 appendNewValues 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isAppendNewValues() {
        return appendNewValues;
      }
      
      /**
       * 設定 appendNewValues 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setAppendNewValues(Boolean value) {
        this.appendNewValues = value;
      }
      
      /**
       * 取得 default 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getDefault() {
        return _default;
      }
      
      /**
       * 設定 default 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setDefault(String value) {
        this._default = value;
      }
      
      /**
       * 取得 valueList 特性的值.
       *
       * @return possible object is {@link ValueList }
       */
      public ValueList getValueList() {
        return valueList;
      }
      
      /**
       * 設定 valueList 特性的值.
       *
       * @param value allowed object is {@link ValueList }
       */
      public void setValueList(ValueList value) {
        this.valueList = value;
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
        name = "",
        propOrder = {"value"})
      public static class ValueList {
        
        @XmlElement(name = "Value", required = true)
        protected List<Value> value;
        
        /**
         * Gets the value of the value property.
         *
         * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore
         * any modification you make to the returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the value property.
         *
         * <p>For example, to add a new item, do as follows:
         *
         * <pre>
         *    getValue().add(newItem);
         * </pre>
         *
         * <p>Objects of the following type(s) are allowed in the list {@link Value }
         */
        public List<Value> getValue() {
          if (value == null) {
            value = new ArrayList<Value>();
          }
          return this.value;
        }
        
        /**
         * anonymous complex type 的 Java 類別.
         *
         * <p>下列綱要片段會指定此類別中包含的預期內容.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;all>
         *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}integer"/>
         *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Phonetic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/all>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(
          name = "",
          propOrder = {})
        public static class Value {
          
          @XmlElement(name = "ID", required = true)
          protected BigInteger id;
          
          @XmlElement(name = "Value")
          protected String value;
          
          @XmlElement(name = "Description")
          protected String description;
          
          @XmlElement(name = "Phonetic")
          protected String phonetic;
          
          /**
           * 取得 id 特性的值.
           *
           * @return possible object is {@link BigInteger }
           */
          public BigInteger getID() {
            return id;
          }
          
          /**
           * 設定 id 特性的值.
           *
           * @param value allowed object is {@link BigInteger }
           */
          public void setID(BigInteger value) {
            this.id = value;
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
          
          /**
           * 取得 description 特性的值.
           *
           * @return possible object is {@link String }
           */
          public String getDescription() {
            return description;
          }
          
          /**
           * 設定 description 特性的值.
           *
           * @param value allowed object is {@link String }
           */
          public void setDescription(String value) {
            this.description = value;
          }
          
          /**
           * 取得 phonetic 特性的值.
           *
           * @return possible object is {@link String }
           */
          public String getPhonetic() {
            return phonetic;
          }
          
          /**
           * 設定 phonetic 特性的值.
           *
           * @param value allowed object is {@link String }
           */
          public void setPhonetic(String value) {
            this.phonetic = value;
          }
        }
      }
    }
  }
  
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(
    name = "",
    propOrder = {"outlineCode"})
  public static class OutlineCodes {
    
    @XmlElement(name = "OutlineCode")
    protected List<OutlineCode> outlineCode;
    
    /**
     * Gets the value of the outlineCode property.
     *
     * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the outlineCode property.
     *
     * <p>For example, to add a new item, do as follows:
     *
     * <pre>
     *    getOutlineCode().add(newItem);
     * </pre>
     *
     * <p>Objects of the following type(s) are allowed in the list {@link OutlineCode }
     */
    public List<OutlineCode> getOutlineCode() {
      if (outlineCode == null) {
        outlineCode = new ArrayList<OutlineCode>();
      }
      return this.outlineCode;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(
      name = "",
      propOrder = {
        "guid",
        "fieldID",
        "fieldName",
        "alias",
        "phoneticAlias",
        "values",
        "enterprise",
        "enterpriseOutlineCodeAlias",
        "resourceSubstitutionEnabled",
        "leafOnly",
        "allLevelsRequired",
        "onlyTableValuesAllowed",
        "masks"
      })
    public static class OutlineCode {
      
      @XmlElement(name = "Guid", required = true)
      protected String guid;
      
      @XmlElement(name = "FieldID")
      protected String fieldID;
      
      @XmlElement(name = "FieldName")
      protected String fieldName;
      
      @XmlElement(name = "Alias")
      protected String alias;
      
      @XmlElement(name = "PhoneticAlias")
      protected String phoneticAlias;
      
      @XmlElement(name = "Values")
      protected Values values;
      
      @XmlElement(name = "Enterprise")
      protected Boolean enterprise;
      
      @XmlElement(name = "EnterpriseOutlineCodeAlias")
      protected BigInteger enterpriseOutlineCodeAlias;
      
      @XmlElement(name = "ResourceSubstitutionEnabled")
      protected Boolean resourceSubstitutionEnabled;
      
      @XmlElement(name = "LeafOnly")
      protected Boolean leafOnly;
      
      @XmlElement(name = "AllLevelsRequired")
      protected Boolean allLevelsRequired;
      
      @XmlElement(name = "OnlyTableValuesAllowed")
      protected Boolean onlyTableValuesAllowed;
      
      @XmlElement(name = "Masks")
      protected Masks masks;
      
      /**
       * 取得 guid 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getGuid() {
        return guid;
      }
      
      /**
       * 設定 guid 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setGuid(String value) {
        this.guid = value;
      }
      
      /**
       * 取得 fieldID 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getFieldID() {
        return fieldID;
      }
      
      /**
       * 設定 fieldID 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setFieldID(String value) {
        this.fieldID = value;
      }
      
      /**
       * 取得 fieldName 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getFieldName() {
        return fieldName;
      }
      
      /**
       * 設定 fieldName 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setFieldName(String value) {
        this.fieldName = value;
      }
      
      /**
       * 取得 alias 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getAlias() {
        return alias;
      }
      
      /**
       * 設定 alias 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setAlias(String value) {
        this.alias = value;
      }
      
      /**
       * 取得 phoneticAlias 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getPhoneticAlias() {
        return phoneticAlias;
      }
      
      /**
       * 設定 phoneticAlias 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setPhoneticAlias(String value) {
        this.phoneticAlias = value;
      }
      
      /**
       * 取得 values 特性的值.
       *
       * @return possible object is {@link Values }
       */
      public Values getValues() {
        return values;
      }
      
      /**
       * 設定 values 特性的值.
       *
       * @param value allowed object is {@link Values }
       */
      public void setValues(Values value) {
        this.values = value;
      }
      
      /**
       * 取得 enterprise 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isEnterprise() {
        return enterprise;
      }
      
      /**
       * 設定 enterprise 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setEnterprise(Boolean value) {
        this.enterprise = value;
      }
      
      /**
       * 取得 enterpriseOutlineCodeAlias 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getEnterpriseOutlineCodeAlias() {
        return enterpriseOutlineCodeAlias;
      }
      
      /**
       * 設定 enterpriseOutlineCodeAlias 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setEnterpriseOutlineCodeAlias(BigInteger value) {
        this.enterpriseOutlineCodeAlias = value;
      }
      
      /**
       * 取得 resourceSubstitutionEnabled 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isResourceSubstitutionEnabled() {
        return resourceSubstitutionEnabled;
      }
      
      /**
       * 設定 resourceSubstitutionEnabled 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setResourceSubstitutionEnabled(Boolean value) {
        this.resourceSubstitutionEnabled = value;
      }
      
      /**
       * 取得 leafOnly 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isLeafOnly() {
        return leafOnly;
      }
      
      /**
       * 設定 leafOnly 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setLeafOnly(Boolean value) {
        this.leafOnly = value;
      }
      
      /**
       * 取得 allLevelsRequired 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isAllLevelsRequired() {
        return allLevelsRequired;
      }
      
      /**
       * 設定 allLevelsRequired 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setAllLevelsRequired(Boolean value) {
        this.allLevelsRequired = value;
      }
      
      /**
       * 取得 onlyTableValuesAllowed 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isOnlyTableValuesAllowed() {
        return onlyTableValuesAllowed;
      }
      
      /**
       * 設定 onlyTableValuesAllowed 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setOnlyTableValuesAllowed(Boolean value) {
        this.onlyTableValuesAllowed = value;
      }
      
      /**
       * 取得 masks 特性的值.
       *
       * @return possible object is {@link Masks }
       */
      public Masks getMasks() {
        return masks;
      }
      
      /**
       * 設定 masks 特性的值.
       *
       * @param value allowed object is {@link Masks }
       */
      public void setMasks(Masks value) {
        this.masks = value;
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
        name = "",
        propOrder = {"mask"})
      public static class Masks {
        
        @XmlElement(name = "Mask")
        protected List<Mask> mask;
        
        /**
         * Gets the value of the mask property.
         *
         * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore
         * any modification you make to the returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the mask property.
         *
         * <p>For example, to add a new item, do as follows:
         *
         * <pre>
         *    getMask().add(newItem);
         * </pre>
         *
         * <p>Objects of the following type(s) are allowed in the list {@link Mask }
         */
        public List<Mask> getMask() {
          if (mask == null) {
            mask = new ArrayList<Mask>();
          }
          return this.mask;
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(
          name = "",
          propOrder = {"level", "type", "length", "separator"})
        public static class Mask {
          
          @XmlElement(name = "Level")
          protected BigInteger level;
          
          @XmlElement(name = "Type")
          protected BigInteger type;
          
          @XmlElement(name = "Length")
          protected BigInteger length;
          
          @XmlElement(name = "Separator")
          protected String separator;
          
          /**
           * 取得 level 特性的值.
           *
           * @return possible object is {@link BigInteger }
           */
          public BigInteger getLevel() {
            return level;
          }
          
          /**
           * 設定 level 特性的值.
           *
           * @param value allowed object is {@link BigInteger }
           */
          public void setLevel(BigInteger value) {
            this.level = value;
          }
          
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
           * 取得 length 特性的值.
           *
           * @return possible object is {@link BigInteger }
           */
          public BigInteger getLength() {
            return length;
          }
          
          /**
           * 設定 length 特性的值.
           *
           * @param value allowed object is {@link BigInteger }
           */
          public void setLength(BigInteger value) {
            this.length = value;
          }
          
          /**
           * 取得 separator 特性的值.
           *
           * @return possible object is {@link String }
           */
          public String getSeparator() {
            return separator;
          }
          
          /**
           * 設定 separator 特性的值.
           *
           * @param value allowed object is {@link String }
           */
          public void setSeparator(String value) {
            this.separator = value;
          }
        }
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
        name = "",
        propOrder = {"value"})
      public static class Values {
        
        @XmlElement(name = "Value")
        protected List<Value> value;
        
        /**
         * Gets the value of the value property.
         *
         * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore
         * any modification you make to the returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the value property.
         *
         * <p>For example, to add a new item, do as follows:
         *
         * <pre>
         *    getValue().add(newItem);
         * </pre>
         *
         * <p>Objects of the following type(s) are allowed in the list {@link Value }
         */
        public List<Value> getValue() {
          if (value == null) {
            value = new ArrayList<Value>();
          }
          return this.value;
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(
          name = "",
          propOrder = {})
        public static class Value {
          
          @XmlElement(name = "ValueID")
          protected BigInteger valueID;
          
          @XmlElement(name = "FieldGUID", required = true)
          protected String fieldGUID;
          
          @XmlElement(name = "Type", required = true)
          protected BigInteger type;
          
          @XmlElement(name = "ParentValueID")
          protected BigInteger parentValueID;
          
          @XmlElement(name = "Value")
          protected String value;
          
          @XmlElement(name = "Description")
          protected String description;
          
          /**
           * 取得 valueID 特性的值.
           *
           * @return possible object is {@link BigInteger }
           */
          public BigInteger getValueID() {
            return valueID;
          }
          
          /**
           * 設定 valueID 特性的值.
           *
           * @param value allowed object is {@link BigInteger }
           */
          public void setValueID(BigInteger value) {
            this.valueID = value;
          }
          
          /**
           * 取得 fieldGUID 特性的值.
           *
           * @return possible object is {@link String }
           */
          public String getFieldGUID() {
            return fieldGUID;
          }
          
          /**
           * 設定 fieldGUID 特性的值.
           *
           * @param value allowed object is {@link String }
           */
          public void setFieldGUID(String value) {
            this.fieldGUID = value;
          }
          
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
           * 取得 parentValueID 特性的值.
           *
           * @return possible object is {@link BigInteger }
           */
          public BigInteger getParentValueID() {
            return parentValueID;
          }
          
          /**
           * 設定 parentValueID 特性的值.
           *
           * @param value allowed object is {@link BigInteger }
           */
          public void setParentValueID(BigInteger value) {
            this.parentValueID = value;
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
          
          /**
           * 取得 description 特性的值.
           *
           * @return possible object is {@link String }
           */
          public String getDescription() {
            return description;
          }
          
          /**
           * 設定 description 特性的值.
           *
           * @param value allowed object is {@link String }
           */
          public void setDescription(String value) {
            this.description = value;
          }
        }
      }
    }
  }
  
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(
    name = "",
    propOrder = {"resource"})
  public static class Resources {
    
    @XmlElement(name = "Resource")
    protected List<Resource> resource;
    
    /**
     * Gets the value of the resource property.
     *
     * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the resource property.
     *
     * <p>For example, to add a new item, do as follows:
     *
     * <pre>
     *    getResource().add(newItem);
     * </pre>
     *
     * <p>Objects of the following type(s) are allowed in the list {@link Resource }
     */
    public List<Resource> getResource() {
      if (resource == null) {
        resource = new ArrayList<Resource>();
      }
      return this.resource;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(
      name = "",
      propOrder = {
        "uid",
        "id",
        "name",
        "type",
        "isNull",
        "initials",
        "phonetics",
        "ntAccount",
        "materialLabel",
        "code",
        "group",
        "workGroup",
        "emailAddress",
        "hyperlink",
        "hyperlinkAddress",
        "hyperlinkSubAddress",
        "maxUnits",
        "peakUnits",
        "overAllocated",
        "availableFrom",
        "availableTo",
        "start",
        "finish",
        "canLevel",
        "accrueAt",
        "work",
        "regularWork",
        "overtimeWork",
        "actualWork",
        "remainingWork",
        "actualOvertimeWork",
        "remainingOvertimeWork",
        "percentWorkComplete",
        "standardRate",
        "standardRateFormat",
        "cost",
        "overtimeRate",
        "overtimeRateFormat",
        "overtimeCost",
        "costPerUse",
        "actualCost",
        "actualOvertimeCost",
        "remainingCost",
        "remainingOvertimeCost",
        "workVariance",
        "costVariance",
        "sv",
        "cv",
        "acwp",
        "calendarUID",
        "notes",
        "bcws",
        "bcwp",
        "isGeneric",
        "isInactive",
        "isEnterprise",
        "bookingType",
        "actualWorkProtected",
        "actualOvertimeWorkProtected",
        "activeDirectoryGUID",
        "creationDate",
        "extendedAttribute",
        "baseline",
        "outlineCode",
        "isCostResource",
        "assnOwner",
        "assnOwnerGuid",
        "isBudget",
        "availabilityPeriods",
        "rates",
        "timephasedData"
      })
    public static class Resource {
      
      @XmlElement(name = "UID", required = true)
      protected BigInteger uid;
      
      @XmlElement(name = "ID")
      protected BigInteger id;
      
      @XmlElement(name = "Name")
      protected String name;
      
      @XmlElement(name = "Type")
      protected BigInteger type;
      
      @XmlElement(name = "IsNull")
      protected Boolean isNull;
      
      @XmlElement(name = "Initials")
      protected String initials;
      
      @XmlElement(name = "Phonetics")
      protected String phonetics;
      
      @XmlElement(name = "NTAccount")
      protected String ntAccount;
      
      @XmlElement(name = "MaterialLabel")
      protected String materialLabel;
      
      @XmlElement(name = "Code")
      protected String code;
      
      @XmlElement(name = "Group")
      protected String group;
      
      @XmlElement(name = "WorkGroup")
      protected BigInteger workGroup;
      
      @XmlElement(name = "EmailAddress")
      protected String emailAddress;
      
      @XmlElement(name = "Hyperlink")
      protected String hyperlink;
      
      @XmlElement(name = "HyperlinkAddress")
      protected String hyperlinkAddress;
      
      @XmlElement(name = "HyperlinkSubAddress")
      protected String hyperlinkSubAddress;
      
      @XmlElement(name = "MaxUnits", defaultValue = "1.0")
      protected Float maxUnits;
      
      @XmlElement(name = "PeakUnits")
      protected Float peakUnits;
      
      @XmlElement(name = "OverAllocated")
      protected Boolean overAllocated;
      
      @XmlElement(name = "AvailableFrom")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar availableFrom;
      
      @XmlElement(name = "AvailableTo")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar availableTo;
      
      @XmlElement(name = "Start")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar start;
      
      @XmlElement(name = "Finish")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar finish;
      
      @XmlElement(name = "CanLevel")
      protected Boolean canLevel;
      
      @XmlElement(name = "AccrueAt")
      protected BigInteger accrueAt;
      
      @XmlElement(name = "Work")
      protected Duration work;
      
      @XmlElement(name = "RegularWork")
      protected Duration regularWork;
      
      @XmlElement(name = "OvertimeWork")
      protected Duration overtimeWork;
      
      @XmlElement(name = "ActualWork")
      protected Duration actualWork;
      
      @XmlElement(name = "RemainingWork")
      protected Duration remainingWork;
      
      @XmlElement(name = "ActualOvertimeWork")
      protected Duration actualOvertimeWork;
      
      @XmlElement(name = "RemainingOvertimeWork")
      protected Duration remainingOvertimeWork;
      
      @XmlElement(name = "PercentWorkComplete")
      protected BigInteger percentWorkComplete;
      
      @XmlElement(name = "StandardRate")
      protected BigDecimal standardRate;
      
      @XmlElement(name = "StandardRateFormat")
      protected BigInteger standardRateFormat;
      
      @XmlElement(name = "Cost")
      protected BigDecimal cost;
      
      @XmlElement(name = "OvertimeRate")
      protected BigDecimal overtimeRate;
      
      @XmlElement(name = "OvertimeRateFormat")
      protected BigInteger overtimeRateFormat;
      
      @XmlElement(name = "OvertimeCost")
      protected BigDecimal overtimeCost;
      
      @XmlElement(name = "CostPerUse")
      protected BigDecimal costPerUse;
      
      @XmlElement(name = "ActualCost")
      protected BigDecimal actualCost;
      
      @XmlElement(name = "ActualOvertimeCost")
      protected BigDecimal actualOvertimeCost;
      
      @XmlElement(name = "RemainingCost")
      protected BigDecimal remainingCost;
      
      @XmlElement(name = "RemainingOvertimeCost")
      protected BigDecimal remainingOvertimeCost;
      
      @XmlElement(name = "WorkVariance")
      protected Float workVariance;
      
      @XmlElement(name = "CostVariance")
      protected Float costVariance;
      
      @XmlElement(name = "SV")
      protected Float sv;
      
      @XmlElement(name = "CV")
      protected Float cv;
      
      @XmlElement(name = "ACWP")
      protected Float acwp;
      
      @XmlElement(name = "CalendarUID")
      protected BigInteger calendarUID;
      
      @XmlElement(name = "Notes")
      protected String notes;
      
      @XmlElement(name = "BCWS")
      protected Float bcws;
      
      @XmlElement(name = "BCWP")
      protected Float bcwp;
      
      @XmlElement(name = "IsGeneric")
      protected Boolean isGeneric;
      
      @XmlElement(name = "IsInactive")
      protected Boolean isInactive;
      
      @XmlElement(name = "IsEnterprise")
      protected Boolean isEnterprise;
      
      @XmlElement(name = "BookingType")
      protected BigInteger bookingType;
      
      @XmlElement(name = "ActualWorkProtected")
      protected Duration actualWorkProtected;
      
      @XmlElement(name = "ActualOvertimeWorkProtected")
      protected Duration actualOvertimeWorkProtected;
      
      @XmlElement(name = "ActiveDirectoryGUID")
      protected String activeDirectoryGUID;
      
      @XmlElement(name = "CreationDate")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar creationDate;
      
      @XmlElement(name = "ExtendedAttribute")
      protected List<ExtendedAttribute> extendedAttribute;
      
      @XmlElement(name = "Baseline")
      protected List<Baseline> baseline;
      
      @XmlElement(name = "OutlineCode")
      protected List<OutlineCode> outlineCode;
      
      @XmlElement(name = "IsCostResource")
      protected Boolean isCostResource;
      
      @XmlElement(name = "AssnOwner")
      protected String assnOwner;
      
      @XmlElement(name = "AssnOwnerGuid")
      protected String assnOwnerGuid;
      
      @XmlElement(name = "IsBudget")
      protected Boolean isBudget;
      
      @XmlElement(name = "AvailabilityPeriods")
      protected AvailabilityPeriods availabilityPeriods;
      
      @XmlElement(name = "Rates")
      protected Rates rates;
      
      @XmlElement(name = "TimephasedData")
      protected List<TimephasedDataType> timephasedData;
      
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
       * 取得 id 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getID() {
        return id;
      }
      
      /**
       * 設定 id 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setID(BigInteger value) {
        this.id = value;
      }
      
      /**
       * 取得 name 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getName() {
        return name;
      }
      
      /**
       * 設定 name 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setName(String value) {
        this.name = value;
      }
      
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
       * 取得 isNull 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isIsNull() {
        return isNull;
      }
      
      /**
       * 設定 isNull 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setIsNull(Boolean value) {
        this.isNull = value;
      }
      
      /**
       * 取得 initials 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getInitials() {
        return initials;
      }
      
      /**
       * 設定 initials 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setInitials(String value) {
        this.initials = value;
      }
      
      /**
       * 取得 phonetics 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getPhonetics() {
        return phonetics;
      }
      
      /**
       * 設定 phonetics 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setPhonetics(String value) {
        this.phonetics = value;
      }
      
      /**
       * 取得 ntAccount 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getNTAccount() {
        return ntAccount;
      }
      
      /**
       * 設定 ntAccount 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setNTAccount(String value) {
        this.ntAccount = value;
      }
      
      /**
       * 取得 materialLabel 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getMaterialLabel() {
        return materialLabel;
      }
      
      /**
       * 設定 materialLabel 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setMaterialLabel(String value) {
        this.materialLabel = value;
      }
      
      /**
       * 取得 code 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getCode() {
        return code;
      }
      
      /**
       * 設定 code 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setCode(String value) {
        this.code = value;
      }
      
      /**
       * 取得 group 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getGroup() {
        return group;
      }
      
      /**
       * 設定 group 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setGroup(String value) {
        this.group = value;
      }
      
      /**
       * 取得 workGroup 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getWorkGroup() {
        return workGroup;
      }
      
      /**
       * 設定 workGroup 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setWorkGroup(BigInteger value) {
        this.workGroup = value;
      }
      
      /**
       * 取得 emailAddress 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getEmailAddress() {
        return emailAddress;
      }
      
      /**
       * 設定 emailAddress 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setEmailAddress(String value) {
        this.emailAddress = value;
      }
      
      /**
       * 取得 hyperlink 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getHyperlink() {
        return hyperlink;
      }
      
      /**
       * 設定 hyperlink 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setHyperlink(String value) {
        this.hyperlink = value;
      }
      
      /**
       * 取得 hyperlinkAddress 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getHyperlinkAddress() {
        return hyperlinkAddress;
      }
      
      /**
       * 設定 hyperlinkAddress 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setHyperlinkAddress(String value) {
        this.hyperlinkAddress = value;
      }
      
      /**
       * 取得 hyperlinkSubAddress 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getHyperlinkSubAddress() {
        return hyperlinkSubAddress;
      }
      
      /**
       * 設定 hyperlinkSubAddress 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setHyperlinkSubAddress(String value) {
        this.hyperlinkSubAddress = value;
      }
      
      /**
       * 取得 maxUnits 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getMaxUnits() {
        return maxUnits;
      }
      
      /**
       * 設定 maxUnits 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setMaxUnits(Float value) {
        this.maxUnits = value;
      }
      
      /**
       * 取得 peakUnits 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getPeakUnits() {
        return peakUnits;
      }
      
      /**
       * 設定 peakUnits 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setPeakUnits(Float value) {
        this.peakUnits = value;
      }
      
      /**
       * 取得 overAllocated 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isOverAllocated() {
        return overAllocated;
      }
      
      /**
       * 設定 overAllocated 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setOverAllocated(Boolean value) {
        this.overAllocated = value;
      }
      
      /**
       * 取得 availableFrom 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getAvailableFrom() {
        return availableFrom;
      }
      
      /**
       * 設定 availableFrom 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setAvailableFrom(XMLGregorianCalendar value) {
        this.availableFrom = value;
      }
      
      /**
       * 取得 availableTo 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getAvailableTo() {
        return availableTo;
      }
      
      /**
       * 設定 availableTo 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setAvailableTo(XMLGregorianCalendar value) {
        this.availableTo = value;
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
       * 取得 canLevel 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isCanLevel() {
        return canLevel;
      }
      
      /**
       * 設定 canLevel 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setCanLevel(Boolean value) {
        this.canLevel = value;
      }
      
      /**
       * 取得 accrueAt 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getAccrueAt() {
        return accrueAt;
      }
      
      /**
       * 設定 accrueAt 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setAccrueAt(BigInteger value) {
        this.accrueAt = value;
      }
      
      /**
       * 取得 work 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getWork() {
        return work;
      }
      
      /**
       * 設定 work 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setWork(Duration value) {
        this.work = value;
      }
      
      /**
       * 取得 regularWork 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getRegularWork() {
        return regularWork;
      }
      
      /**
       * 設定 regularWork 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setRegularWork(Duration value) {
        this.regularWork = value;
      }
      
      /**
       * 取得 overtimeWork 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getOvertimeWork() {
        return overtimeWork;
      }
      
      /**
       * 設定 overtimeWork 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setOvertimeWork(Duration value) {
        this.overtimeWork = value;
      }
      
      /**
       * 取得 actualWork 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getActualWork() {
        return actualWork;
      }
      
      /**
       * 設定 actualWork 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setActualWork(Duration value) {
        this.actualWork = value;
      }
      
      /**
       * 取得 remainingWork 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getRemainingWork() {
        return remainingWork;
      }
      
      /**
       * 設定 remainingWork 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setRemainingWork(Duration value) {
        this.remainingWork = value;
      }
      
      /**
       * 取得 actualOvertimeWork 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getActualOvertimeWork() {
        return actualOvertimeWork;
      }
      
      /**
       * 設定 actualOvertimeWork 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setActualOvertimeWork(Duration value) {
        this.actualOvertimeWork = value;
      }
      
      /**
       * 取得 remainingOvertimeWork 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getRemainingOvertimeWork() {
        return remainingOvertimeWork;
      }
      
      /**
       * 設定 remainingOvertimeWork 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setRemainingOvertimeWork(Duration value) {
        this.remainingOvertimeWork = value;
      }
      
      /**
       * 取得 percentWorkComplete 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getPercentWorkComplete() {
        return percentWorkComplete;
      }
      
      /**
       * 設定 percentWorkComplete 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setPercentWorkComplete(BigInteger value) {
        this.percentWorkComplete = value;
      }
      
      /**
       * 取得 standardRate 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getStandardRate() {
        return standardRate;
      }
      
      /**
       * 設定 standardRate 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setStandardRate(BigDecimal value) {
        this.standardRate = value;
      }
      
      /**
       * 取得 standardRateFormat 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getStandardRateFormat() {
        return standardRateFormat;
      }
      
      /**
       * 設定 standardRateFormat 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setStandardRateFormat(BigInteger value) {
        this.standardRateFormat = value;
      }
      
      /**
       * 取得 cost 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getCost() {
        return cost;
      }
      
      /**
       * 設定 cost 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setCost(BigDecimal value) {
        this.cost = value;
      }
      
      /**
       * 取得 overtimeRate 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getOvertimeRate() {
        return overtimeRate;
      }
      
      /**
       * 設定 overtimeRate 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setOvertimeRate(BigDecimal value) {
        this.overtimeRate = value;
      }
      
      /**
       * 取得 overtimeRateFormat 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getOvertimeRateFormat() {
        return overtimeRateFormat;
      }
      
      /**
       * 設定 overtimeRateFormat 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setOvertimeRateFormat(BigInteger value) {
        this.overtimeRateFormat = value;
      }
      
      /**
       * 取得 overtimeCost 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getOvertimeCost() {
        return overtimeCost;
      }
      
      /**
       * 設定 overtimeCost 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setOvertimeCost(BigDecimal value) {
        this.overtimeCost = value;
      }
      
      /**
       * 取得 costPerUse 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getCostPerUse() {
        return costPerUse;
      }
      
      /**
       * 設定 costPerUse 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setCostPerUse(BigDecimal value) {
        this.costPerUse = value;
      }
      
      /**
       * 取得 actualCost 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getActualCost() {
        return actualCost;
      }
      
      /**
       * 設定 actualCost 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setActualCost(BigDecimal value) {
        this.actualCost = value;
      }
      
      /**
       * 取得 actualOvertimeCost 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getActualOvertimeCost() {
        return actualOvertimeCost;
      }
      
      /**
       * 設定 actualOvertimeCost 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setActualOvertimeCost(BigDecimal value) {
        this.actualOvertimeCost = value;
      }
      
      /**
       * 取得 remainingCost 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getRemainingCost() {
        return remainingCost;
      }
      
      /**
       * 設定 remainingCost 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setRemainingCost(BigDecimal value) {
        this.remainingCost = value;
      }
      
      /**
       * 取得 remainingOvertimeCost 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getRemainingOvertimeCost() {
        return remainingOvertimeCost;
      }
      
      /**
       * 設定 remainingOvertimeCost 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setRemainingOvertimeCost(BigDecimal value) {
        this.remainingOvertimeCost = value;
      }
      
      /**
       * 取得 workVariance 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getWorkVariance() {
        return workVariance;
      }
      
      /**
       * 設定 workVariance 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setWorkVariance(Float value) {
        this.workVariance = value;
      }
      
      /**
       * 取得 costVariance 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getCostVariance() {
        return costVariance;
      }
      
      /**
       * 設定 costVariance 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setCostVariance(Float value) {
        this.costVariance = value;
      }
      
      /**
       * 取得 sv 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getSV() {
        return sv;
      }
      
      /**
       * 設定 sv 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setSV(Float value) {
        this.sv = value;
      }
      
      /**
       * 取得 cv 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getCV() {
        return cv;
      }
      
      /**
       * 設定 cv 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setCV(Float value) {
        this.cv = value;
      }
      
      /**
       * 取得 acwp 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getACWP() {
        return acwp;
      }
      
      /**
       * 設定 acwp 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setACWP(Float value) {
        this.acwp = value;
      }
      
      /**
       * 取得 calendarUID 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getCalendarUID() {
        return calendarUID;
      }
      
      /**
       * 設定 calendarUID 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setCalendarUID(BigInteger value) {
        this.calendarUID = value;
      }
      
      /**
       * 取得 notes 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getNotes() {
        return notes;
      }
      
      /**
       * 設定 notes 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setNotes(String value) {
        this.notes = value;
      }
      
      /**
       * 取得 bcws 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getBCWS() {
        return bcws;
      }
      
      /**
       * 設定 bcws 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setBCWS(Float value) {
        this.bcws = value;
      }
      
      /**
       * 取得 bcwp 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getBCWP() {
        return bcwp;
      }
      
      /**
       * 設定 bcwp 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setBCWP(Float value) {
        this.bcwp = value;
      }
      
      /**
       * 取得 isGeneric 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isIsGeneric() {
        return isGeneric;
      }
      
      /**
       * 設定 isGeneric 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setIsGeneric(Boolean value) {
        this.isGeneric = value;
      }
      
      /**
       * 取得 isInactive 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isIsInactive() {
        return isInactive;
      }
      
      /**
       * 設定 isInactive 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setIsInactive(Boolean value) {
        this.isInactive = value;
      }
      
      /**
       * 取得 isEnterprise 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isIsEnterprise() {
        return isEnterprise;
      }
      
      /**
       * 設定 isEnterprise 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setIsEnterprise(Boolean value) {
        this.isEnterprise = value;
      }
      
      /**
       * 取得 bookingType 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getBookingType() {
        return bookingType;
      }
      
      /**
       * 設定 bookingType 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setBookingType(BigInteger value) {
        this.bookingType = value;
      }
      
      /**
       * 取得 actualWorkProtected 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getActualWorkProtected() {
        return actualWorkProtected;
      }
      
      /**
       * 設定 actualWorkProtected 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setActualWorkProtected(Duration value) {
        this.actualWorkProtected = value;
      }
      
      /**
       * 取得 actualOvertimeWorkProtected 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getActualOvertimeWorkProtected() {
        return actualOvertimeWorkProtected;
      }
      
      /**
       * 設定 actualOvertimeWorkProtected 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setActualOvertimeWorkProtected(Duration value) {
        this.actualOvertimeWorkProtected = value;
      }
      
      /**
       * 取得 activeDirectoryGUID 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getActiveDirectoryGUID() {
        return activeDirectoryGUID;
      }
      
      /**
       * 設定 activeDirectoryGUID 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setActiveDirectoryGUID(String value) {
        this.activeDirectoryGUID = value;
      }
      
      /**
       * 取得 creationDate 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getCreationDate() {
        return creationDate;
      }
      
      /**
       * 設定 creationDate 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
      }
      
      /**
       * Gets the value of the extendedAttribute property.
       *
       * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
       * modification you make to the returned list will be present inside the JAXB object. This is
       * why there is not a <CODE>set</CODE> method for the extendedAttribute property.
       *
       * <p>For example, to add a new item, do as follows:
       *
       * <pre>
       *    getExtendedAttribute().add(newItem);
       * </pre>
       *
       * <p>Objects of the following type(s) are allowed in the list {@link ExtendedAttribute }
       */
      public List<ExtendedAttribute> getExtendedAttribute() {
        if (extendedAttribute == null) {
          extendedAttribute = new ArrayList<ExtendedAttribute>();
        }
        return this.extendedAttribute;
      }
      
      /**
       * Gets the value of the baseline property.
       *
       * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
       * modification you make to the returned list will be present inside the JAXB object. This is
       * why there is not a <CODE>set</CODE> method for the baseline property.
       *
       * <p>For example, to add a new item, do as follows:
       *
       * <pre>
       *    getBaseline().add(newItem);
       * </pre>
       *
       * <p>Objects of the following type(s) are allowed in the list {@link Baseline }
       */
      public List<Baseline> getBaseline() {
        if (baseline == null) {
          baseline = new ArrayList<Baseline>();
        }
        return this.baseline;
      }
      
      /**
       * Gets the value of the outlineCode property.
       *
       * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
       * modification you make to the returned list will be present inside the JAXB object. This is
       * why there is not a <CODE>set</CODE> method for the outlineCode property.
       *
       * <p>For example, to add a new item, do as follows:
       *
       * <pre>
       *    getOutlineCode().add(newItem);
       * </pre>
       *
       * <p>Objects of the following type(s) are allowed in the list {@link OutlineCode }
       */
      public List<OutlineCode> getOutlineCode() {
        if (outlineCode == null) {
          outlineCode = new ArrayList<OutlineCode>();
        }
        return this.outlineCode;
      }
      
      /**
       * 取得 isCostResource 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isIsCostResource() {
        return isCostResource;
      }
      
      /**
       * 設定 isCostResource 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setIsCostResource(Boolean value) {
        this.isCostResource = value;
      }
      
      /**
       * 取得 assnOwner 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getAssnOwner() {
        return assnOwner;
      }
      
      /**
       * 設定 assnOwner 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setAssnOwner(String value) {
        this.assnOwner = value;
      }
      
      /**
       * 取得 assnOwnerGuid 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getAssnOwnerGuid() {
        return assnOwnerGuid;
      }
      
      /**
       * 設定 assnOwnerGuid 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setAssnOwnerGuid(String value) {
        this.assnOwnerGuid = value;
      }
      
      /**
       * 取得 isBudget 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isIsBudget() {
        return isBudget;
      }
      
      /**
       * 設定 isBudget 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setIsBudget(Boolean value) {
        this.isBudget = value;
      }
      
      /**
       * 取得 availabilityPeriods 特性的值.
       *
       * @return possible object is {@link AvailabilityPeriods }
       */
      public AvailabilityPeriods getAvailabilityPeriods() {
        return availabilityPeriods;
      }
      
      /**
       * 設定 availabilityPeriods 特性的值.
       *
       * @param value allowed object is {@link AvailabilityPeriods }
       */
      public void setAvailabilityPeriods(AvailabilityPeriods value) {
        this.availabilityPeriods = value;
      }
      
      /**
       * 取得 rates 特性的值.
       *
       * @return possible object is {@link Rates }
       */
      public Rates getRates() {
        return rates;
      }
      
      /**
       * 設定 rates 特性的值.
       *
       * @param value allowed object is {@link Rates }
       */
      public void setRates(Rates value) {
        this.rates = value;
      }
      
      /**
       * Gets the value of the timephasedData property.
       *
       * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
       * modification you make to the returned list will be present inside the JAXB object. This is
       * why there is not a <CODE>set</CODE> method for the timephasedData property.
       *
       * <p>For example, to add a new item, do as follows:
       *
       * <pre>
       *    getTimephasedData().add(newItem);
       * </pre>
       *
       * <p>Objects of the following type(s) are allowed in the list {@link TimephasedDataType }
       */
      public List<TimephasedDataType> getTimephasedData() {
        if (timephasedData == null) {
          timephasedData = new ArrayList<TimephasedDataType>();
        }
        return this.timephasedData;
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
        name = "",
        propOrder = {"availabilityPeriod"})
      public static class AvailabilityPeriods {
        
        @XmlElement(name = "AvailabilityPeriod")
        protected List<AvailabilityPeriod> availabilityPeriod;
        
        /**
         * Gets the value of the availabilityPeriod property.
         *
         * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore
         * any modification you make to the returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the availabilityPeriod property.
         *
         * <p>For example, to add a new item, do as follows:
         *
         * <pre>
         *    getAvailabilityPeriod().add(newItem);
         * </pre>
         *
         * <p>Objects of the following type(s) are allowed in the list {@link AvailabilityPeriod }
         */
        public List<AvailabilityPeriod> getAvailabilityPeriod() {
          if (availabilityPeriod == null) {
            availabilityPeriod = new ArrayList<AvailabilityPeriod>();
          }
          return this.availabilityPeriod;
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(
          name = "",
          propOrder = {"availableFrom", "availableTo", "availableUnits"})
        public static class AvailabilityPeriod {
          
          @XmlElement(name = "AvailableFrom")
          @XmlSchemaType(name = "dateTime")
          protected XMLGregorianCalendar availableFrom;
          
          @XmlElement(name = "AvailableTo")
          @XmlSchemaType(name = "dateTime")
          protected XMLGregorianCalendar availableTo;
          
          @XmlElement(name = "AvailableUnits")
          protected Float availableUnits;
          
          /**
           * 取得 availableFrom 特性的值.
           *
           * @return possible object is {@link XMLGregorianCalendar }
           */
          public XMLGregorianCalendar getAvailableFrom() {
            return availableFrom;
          }
          
          /**
           * 設定 availableFrom 特性的值.
           *
           * @param value allowed object is {@link XMLGregorianCalendar }
           */
          public void setAvailableFrom(XMLGregorianCalendar value) {
            this.availableFrom = value;
          }
          
          /**
           * 取得 availableTo 特性的值.
           *
           * @return possible object is {@link XMLGregorianCalendar }
           */
          public XMLGregorianCalendar getAvailableTo() {
            return availableTo;
          }
          
          /**
           * 設定 availableTo 特性的值.
           *
           * @param value allowed object is {@link XMLGregorianCalendar }
           */
          public void setAvailableTo(XMLGregorianCalendar value) {
            this.availableTo = value;
          }
          
          /**
           * 取得 availableUnits 特性的值.
           *
           * @return possible object is {@link Float }
           */
          public Float getAvailableUnits() {
            return availableUnits;
          }
          
          /**
           * 設定 availableUnits 特性的值.
           *
           * @param value allowed object is {@link Float }
           */
          public void setAvailableUnits(Float value) {
            this.availableUnits = value;
          }
        }
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
        name = "",
        propOrder = {"number", "work", "cost", "bcws", "bcwp"})
      public static class Baseline {
        
        @XmlElement(name = "Number", required = true)
        protected BigInteger number;
        
        @XmlElement(name = "Work")
        protected Duration work;
        
        @XmlElement(name = "Cost")
        protected Float cost;
        
        @XmlElement(name = "BCWS")
        protected Float bcws;
        
        @XmlElement(name = "BCWP")
        protected Float bcwp;
        
        /**
         * 取得 number 特性的值.
         *
         * @return possible object is {@link BigInteger }
         */
        public BigInteger getNumber() {
          return number;
        }
        
        /**
         * 設定 number 特性的值.
         *
         * @param value allowed object is {@link BigInteger }
         */
        public void setNumber(BigInteger value) {
          this.number = value;
        }
        
        /**
         * 取得 work 特性的值.
         *
         * @return possible object is {@link Duration }
         */
        public Duration getWork() {
          return work;
        }
        
        /**
         * 設定 work 特性的值.
         *
         * @param value allowed object is {@link Duration }
         */
        public void setWork(Duration value) {
          this.work = value;
        }
        
        /**
         * 取得 cost 特性的值.
         *
         * @return possible object is {@link Float }
         */
        public Float getCost() {
          return cost;
        }
        
        /**
         * 設定 cost 特性的值.
         *
         * @param value allowed object is {@link Float }
         */
        public void setCost(Float value) {
          this.cost = value;
        }
        
        /**
         * 取得 bcws 特性的值.
         *
         * @return possible object is {@link Float }
         */
        public Float getBCWS() {
          return bcws;
        }
        
        /**
         * 設定 bcws 特性的值.
         *
         * @param value allowed object is {@link Float }
         */
        public void setBCWS(Float value) {
          this.bcws = value;
        }
        
        /**
         * 取得 bcwp 特性的值.
         *
         * @return possible object is {@link Float }
         */
        public Float getBCWP() {
          return bcwp;
        }
        
        /**
         * 設定 bcwp 特性的值.
         *
         * @param value allowed object is {@link Float }
         */
        public void setBCWP(Float value) {
          this.bcwp = value;
        }
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
        name = "",
        propOrder = {"fieldID", "value", "valueGUID", "durationFormat"})
      public static class ExtendedAttribute {
        
        @XmlElement(name = "FieldID")
        protected String fieldID;
        
        @XmlElement(name = "Value")
        protected String value;
        
        @XmlElement(name = "ValueGUID")
        protected BigInteger valueGUID;
        
        @XmlElement(name = "DurationFormat")
        protected BigInteger durationFormat;
        
        /**
         * 取得 fieldID 特性的值.
         *
         * @return possible object is {@link String }
         */
        public String getFieldID() {
          return fieldID;
        }
        
        /**
         * 設定 fieldID 特性的值.
         *
         * @param value allowed object is {@link String }
         */
        public void setFieldID(String value) {
          this.fieldID = value;
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
        
        /**
         * 取得 valueGUID 特性的值.
         *
         * @return possible object is {@link BigInteger }
         */
        public BigInteger getValueGUID() {
          return valueGUID;
        }
        
        /**
         * 設定 valueGUID 特性的值.
         *
         * @param value allowed object is {@link BigInteger }
         */
        public void setValueGUID(BigInteger value) {
          this.valueGUID = value;
        }
        
        /**
         * 取得 durationFormat 特性的值.
         *
         * @return possible object is {@link BigInteger }
         */
        public BigInteger getDurationFormat() {
          return durationFormat;
        }
        
        /**
         * 設定 durationFormat 特性的值.
         *
         * @param value allowed object is {@link BigInteger }
         */
        public void setDurationFormat(BigInteger value) {
          this.durationFormat = value;
        }
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
        name = "",
        propOrder = {"fieldID", "valueID", "valueGUID"})
      public static class OutlineCode {
        
        @XmlElement(name = "FieldID")
        protected String fieldID;
        
        @XmlElement(name = "ValueID")
        protected BigInteger valueID;
        
        @XmlElement(name = "ValueGUID")
        protected BigInteger valueGUID;
        
        /**
         * 取得 fieldID 特性的值.
         *
         * @return possible object is {@link String }
         */
        public String getFieldID() {
          return fieldID;
        }
        
        /**
         * 設定 fieldID 特性的值.
         *
         * @param value allowed object is {@link String }
         */
        public void setFieldID(String value) {
          this.fieldID = value;
        }
        
        /**
         * 取得 valueID 特性的值.
         *
         * @return possible object is {@link BigInteger }
         */
        public BigInteger getValueID() {
          return valueID;
        }
        
        /**
         * 設定 valueID 特性的值.
         *
         * @param value allowed object is {@link BigInteger }
         */
        public void setValueID(BigInteger value) {
          this.valueID = value;
        }
        
        /**
         * 取得 valueGUID 特性的值.
         *
         * @return possible object is {@link BigInteger }
         */
        public BigInteger getValueGUID() {
          return valueGUID;
        }
        
        /**
         * 設定 valueGUID 特性的值.
         *
         * @param value allowed object is {@link BigInteger }
         */
        public void setValueGUID(BigInteger value) {
          this.valueGUID = value;
        }
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
        name = "",
        propOrder = {"rate"})
      public static class Rates {
        
        @XmlElement(name = "Rate")
        protected List<Rate> rate;
        
        /**
         * Gets the value of the rate property.
         *
         * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore
         * any modification you make to the returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the rate property.
         *
         * <p>For example, to add a new item, do as follows:
         *
         * <pre>
         *    getRate().add(newItem);
         * </pre>
         *
         * <p>Objects of the following type(s) are allowed in the list {@link Rate }
         */
        public List<Rate> getRate() {
          if (rate == null) {
            rate = new ArrayList<Rate>();
          }
          return this.rate;
        }
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(
          name = "",
          propOrder = {
            "ratesFrom",
            "ratesTo",
            "rateTable",
            "standardRate",
            "standardRateFormat",
            "overtimeRate",
            "overtimeRateFormat",
            "costPerUse"
          })
        public static class Rate {
          
          @XmlElement(name = "RatesFrom", required = true)
          @XmlSchemaType(name = "dateTime")
          protected XMLGregorianCalendar ratesFrom;
          
          @XmlElement(name = "RatesTo", required = true)
          @XmlSchemaType(name = "dateTime")
          protected XMLGregorianCalendar ratesTo;
          
          @XmlElement(name = "RateTable")
          protected BigInteger rateTable;
          
          @XmlElement(name = "StandardRate")
          protected BigDecimal standardRate;
          
          @XmlElement(name = "StandardRateFormat")
          protected BigInteger standardRateFormat;
          
          @XmlElement(name = "OvertimeRate")
          protected BigDecimal overtimeRate;
          
          @XmlElement(name = "OvertimeRateFormat")
          protected BigInteger overtimeRateFormat;
          
          @XmlElement(name = "CostPerUse")
          protected BigDecimal costPerUse;
          
          /**
           * 取得 ratesFrom 特性的值.
           *
           * @return possible object is {@link XMLGregorianCalendar }
           */
          public XMLGregorianCalendar getRatesFrom() {
            return ratesFrom;
          }
          
          /**
           * 設定 ratesFrom 特性的值.
           *
           * @param value allowed object is {@link XMLGregorianCalendar }
           */
          public void setRatesFrom(XMLGregorianCalendar value) {
            this.ratesFrom = value;
          }
          
          /**
           * 取得 ratesTo 特性的值.
           *
           * @return possible object is {@link XMLGregorianCalendar }
           */
          public XMLGregorianCalendar getRatesTo() {
            return ratesTo;
          }
          
          /**
           * 設定 ratesTo 特性的值.
           *
           * @param value allowed object is {@link XMLGregorianCalendar }
           */
          public void setRatesTo(XMLGregorianCalendar value) {
            this.ratesTo = value;
          }
          
          /**
           * 取得 rateTable 特性的值.
           *
           * @return possible object is {@link BigInteger }
           */
          public BigInteger getRateTable() {
            return rateTable;
          }
          
          /**
           * 設定 rateTable 特性的值.
           *
           * @param value allowed object is {@link BigInteger }
           */
          public void setRateTable(BigInteger value) {
            this.rateTable = value;
          }
          
          /**
           * 取得 standardRate 特性的值.
           *
           * @return possible object is {@link BigDecimal }
           */
          public BigDecimal getStandardRate() {
            return standardRate;
          }
          
          /**
           * 設定 standardRate 特性的值.
           *
           * @param value allowed object is {@link BigDecimal }
           */
          public void setStandardRate(BigDecimal value) {
            this.standardRate = value;
          }
          
          /**
           * 取得 standardRateFormat 特性的值.
           *
           * @return possible object is {@link BigInteger }
           */
          public BigInteger getStandardRateFormat() {
            return standardRateFormat;
          }
          
          /**
           * 設定 standardRateFormat 特性的值.
           *
           * @param value allowed object is {@link BigInteger }
           */
          public void setStandardRateFormat(BigInteger value) {
            this.standardRateFormat = value;
          }
          
          /**
           * 取得 overtimeRate 特性的值.
           *
           * @return possible object is {@link BigDecimal }
           */
          public BigDecimal getOvertimeRate() {
            return overtimeRate;
          }
          
          /**
           * 設定 overtimeRate 特性的值.
           *
           * @param value allowed object is {@link BigDecimal }
           */
          public void setOvertimeRate(BigDecimal value) {
            this.overtimeRate = value;
          }
          
          /**
           * 取得 overtimeRateFormat 特性的值.
           *
           * @return possible object is {@link BigInteger }
           */
          public BigInteger getOvertimeRateFormat() {
            return overtimeRateFormat;
          }
          
          /**
           * 設定 overtimeRateFormat 特性的值.
           *
           * @param value allowed object is {@link BigInteger }
           */
          public void setOvertimeRateFormat(BigInteger value) {
            this.overtimeRateFormat = value;
          }
          
          /**
           * 取得 costPerUse 特性的值.
           *
           * @return possible object is {@link BigDecimal }
           */
          public BigDecimal getCostPerUse() {
            return costPerUse;
          }
          
          /**
           * 設定 costPerUse 特性的值.
           *
           * @param value allowed object is {@link BigDecimal }
           */
          public void setCostPerUse(BigDecimal value) {
            this.costPerUse = value;
          }
        }
      }
    }
  }
  
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(
    name = "",
    propOrder = {"task"})
  public static class Tasks {
    
    @XmlElement(name = "Task")
    protected List<Task> task;
    
    /**
     * Gets the value of the task property.
     *
     * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the task property.
     *
     * <p>For example, to add a new item, do as follows:
     *
     * <pre>
     *    getTask().add(newItem);
     * </pre>
     *
     * <p>Objects of the following type(s) are allowed in the list {@link Task }
     */
    public List<Task> getTask() {
      if (task == null) {
        task = new ArrayList<Task>();
      }
      return this.task;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(
      name = "",
      propOrder = {
        "uid",
        "id",
        "name",
        "type",
        "isNull",
        "createDate",
        "contact",
        "wbs",
        "wbsLevel",
        "outlineNumber",
        "outlineLevel",
        "priority",
        "start",
        "finish",
        "duration",
        "durationFormat",
        "work",
        "stop",
        "resume",
        "resumeValid",
        "effortDriven",
        "recurring",
        "overAllocated",
        "estimated",
        "milestone",
        "summary",
        "critical",
        "isSubproject",
        "isSubprojectReadOnly",
        "subprojectName",
        "externalTask",
        "externalTaskProject",
        "earlyStart",
        "earlyFinish",
        "lateStart",
        "lateFinish",
        "startVariance",
        "finishVariance",
        "workVariance",
        "freeSlack",
        "totalSlack",
        "fixedCost",
        "fixedCostAccrual",
        "percentComplete",
        "percentWorkComplete",
        "cost",
        "overtimeCost",
        "overtimeWork",
        "actualStart",
        "actualFinish",
        "actualDuration",
        "actualCost",
        "actualOvertimeCost",
        "actualWork",
        "actualOvertimeWork",
        "regularWork",
        "remainingDuration",
        "remainingCost",
        "remainingWork",
        "remainingOvertimeCost",
        "remainingOvertimeWork",
        "acwp",
        "cv",
        "constraintType",
        "calendarUID",
        "constraintDate",
        "deadline",
        "levelAssignments",
        "levelingCanSplit",
        "levelingDelay",
        "levelingDelayFormat",
        "preLeveledStart",
        "preLeveledFinish",
        "hyperlink",
        "hyperlinkAddress",
        "hyperlinkSubAddress",
        "ignoreResourceCalendar",
        "notes",
        "hideBar",
        "rollup",
        "bcws",
        "bcwp",
        "physicalPercentComplete",
        "earnedValueMethod",
        "predecessorLink",
        "actualWorkProtected",
        "actualOvertimeWorkProtected",
        "extendedAttribute",
        "baseline",
        "outlineCode",
        "isPublished",
        "statusManager",
        "commitmentStart",
        "commitmentFinish",
        "commitmentType",
        "timephasedData"
      })
    public static class Task {
      
      @XmlElement(name = "UID", required = true)
      protected BigInteger uid;
      
      @XmlElement(name = "ID")
      protected BigInteger id;
      
      @XmlElement(name = "Name")
      protected String name;
      
      @XmlElement(name = "Type")
      protected BigInteger type;
      
      @XmlElement(name = "IsNull")
      protected Boolean isNull;
      
      @XmlElement(name = "CreateDate")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar createDate;
      
      @XmlElement(name = "Contact")
      protected String contact;
      
      @XmlElement(name = "WBS")
      protected String wbs;
      
      @XmlElement(name = "WBSLevel")
      protected String wbsLevel;
      
      @XmlElement(name = "OutlineNumber")
      protected String outlineNumber;
      
      @XmlElement(name = "OutlineLevel")
      protected BigInteger outlineLevel;
      
      @XmlElement(name = "Priority")
      protected BigInteger priority;
      
      @XmlElement(name = "Start")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar start;
      
      @XmlElement(name = "Finish")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar finish;
      
      @XmlElement(name = "Duration")
      protected Duration duration;
      
      @XmlElement(name = "DurationFormat")
      protected BigInteger durationFormat;
      
      @XmlElement(name = "Work")
      protected Duration work;
      
      @XmlElement(name = "Stop")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar stop;
      
      @XmlElement(name = "Resume")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar resume;
      
      @XmlElement(name = "ResumeValid")
      protected Boolean resumeValid;
      
      @XmlElement(name = "EffortDriven")
      protected Boolean effortDriven;
      
      @XmlElement(name = "Recurring")
      protected Boolean recurring;
      
      @XmlElement(name = "OverAllocated")
      protected Boolean overAllocated;
      
      @XmlElement(name = "Estimated")
      protected Boolean estimated;
      
      @XmlElement(name = "Milestone")
      protected Boolean milestone;
      
      @XmlElement(name = "Summary")
      protected Boolean summary;
      
      @XmlElement(name = "Critical")
      protected Boolean critical;
      
      @XmlElement(name = "IsSubproject")
      protected Boolean isSubproject;
      
      @XmlElement(name = "IsSubprojectReadOnly")
      protected Boolean isSubprojectReadOnly;
      
      @XmlElement(name = "SubprojectName")
      protected String subprojectName;
      
      @XmlElement(name = "ExternalTask")
      protected Boolean externalTask;
      
      @XmlElement(name = "ExternalTaskProject")
      protected String externalTaskProject;
      
      @XmlElement(name = "EarlyStart")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar earlyStart;
      
      @XmlElement(name = "EarlyFinish")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar earlyFinish;
      
      @XmlElement(name = "LateStart")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar lateStart;
      
      @XmlElement(name = "LateFinish")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar lateFinish;
      
      @XmlElement(name = "StartVariance")
      protected BigInteger startVariance;
      
      @XmlElement(name = "FinishVariance")
      protected BigInteger finishVariance;
      
      @XmlElement(name = "WorkVariance")
      protected Float workVariance;
      
      @XmlElement(name = "FreeSlack")
      protected BigInteger freeSlack;
      
      @XmlElement(name = "TotalSlack")
      protected BigInteger totalSlack;
      
      @XmlElement(name = "FixedCost")
      protected Float fixedCost;
      
      @XmlElement(name = "FixedCostAccrual")
      protected String fixedCostAccrual;
      
      @XmlElement(name = "PercentComplete")
      protected BigInteger percentComplete;
      
      @XmlElement(name = "PercentWorkComplete")
      protected BigInteger percentWorkComplete;
      
      @XmlElement(name = "Cost")
      protected BigDecimal cost;
      
      @XmlElement(name = "OvertimeCost")
      protected BigDecimal overtimeCost;
      
      @XmlElement(name = "OvertimeWork")
      protected Duration overtimeWork;
      
      @XmlElement(name = "ActualStart")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar actualStart;
      
      @XmlElement(name = "ActualFinish")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar actualFinish;
      
      @XmlElement(name = "ActualDuration")
      protected Duration actualDuration;
      
      @XmlElement(name = "ActualCost")
      protected BigDecimal actualCost;
      
      @XmlElement(name = "ActualOvertimeCost")
      protected BigDecimal actualOvertimeCost;
      
      @XmlElement(name = "ActualWork")
      protected Duration actualWork;
      
      @XmlElement(name = "ActualOvertimeWork")
      protected Duration actualOvertimeWork;
      
      @XmlElement(name = "RegularWork")
      protected Duration regularWork;
      
      @XmlElement(name = "RemainingDuration")
      protected Duration remainingDuration;
      
      @XmlElement(name = "RemainingCost")
      protected BigDecimal remainingCost;
      
      @XmlElement(name = "RemainingWork")
      protected Duration remainingWork;
      
      @XmlElement(name = "RemainingOvertimeCost")
      protected BigDecimal remainingOvertimeCost;
      
      @XmlElement(name = "RemainingOvertimeWork")
      protected Duration remainingOvertimeWork;
      
      @XmlElement(name = "ACWP")
      protected Float acwp;
      
      @XmlElement(name = "CV")
      protected Float cv;
      
      @XmlElement(name = "ConstraintType")
      protected BigInteger constraintType;
      
      @XmlElement(name = "CalendarUID")
      protected BigInteger calendarUID;
      
      @XmlElement(name = "ConstraintDate")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar constraintDate;
      
      @XmlElement(name = "Deadline")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar deadline;
      
      @XmlElement(name = "LevelAssignments")
      protected Boolean levelAssignments;
      
      @XmlElement(name = "LevelingCanSplit")
      protected Boolean levelingCanSplit;
      
      @XmlElement(name = "LevelingDelay")
      protected BigInteger levelingDelay;
      
      @XmlElement(name = "LevelingDelayFormat")
      protected BigInteger levelingDelayFormat;
      
      @XmlElement(name = "PreLeveledStart")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar preLeveledStart;
      
      @XmlElement(name = "PreLeveledFinish")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar preLeveledFinish;
      
      @XmlElement(name = "Hyperlink")
      protected String hyperlink;
      
      @XmlElement(name = "HyperlinkAddress")
      protected String hyperlinkAddress;
      
      @XmlElement(name = "HyperlinkSubAddress")
      protected String hyperlinkSubAddress;
      
      @XmlElement(name = "IgnoreResourceCalendar")
      protected Boolean ignoreResourceCalendar;
      
      @XmlElement(name = "Notes")
      protected String notes;
      
      @XmlElement(name = "HideBar")
      protected Boolean hideBar;
      
      @XmlElement(name = "Rollup")
      protected Boolean rollup;
      
      @XmlElement(name = "BCWS")
      protected Float bcws;
      
      @XmlElement(name = "BCWP")
      protected Float bcwp;
      
      @XmlElement(name = "PhysicalPercentComplete")
      protected BigInteger physicalPercentComplete;
      
      @XmlElement(name = "EarnedValueMethod")
      protected BigInteger earnedValueMethod;
      
      @XmlElement(name = "PredecessorLink")
      protected List<PredecessorLink> predecessorLink;
      
      @XmlElement(name = "ActualWorkProtected")
      protected Duration actualWorkProtected;
      
      @XmlElement(name = "ActualOvertimeWorkProtected")
      protected Duration actualOvertimeWorkProtected;
      
      @XmlElement(name = "ExtendedAttribute")
      protected List<ExtendedAttribute> extendedAttribute;
      
      @XmlElement(name = "Baseline")
      protected List<Baseline> baseline;
      
      @XmlElement(name = "OutlineCode")
      protected List<OutlineCode> outlineCode;
      
      @XmlElement(name = "IsPublished")
      protected Boolean isPublished;
      
      @XmlElement(name = "StatusManager")
      protected String statusManager;
      
      @XmlElement(name = "CommitmentStart")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar commitmentStart;
      
      @XmlElement(name = "CommitmentFinish")
      @XmlSchemaType(name = "dateTime")
      protected XMLGregorianCalendar commitmentFinish;
      
      @XmlElement(name = "CommitmentType")
      protected BigInteger commitmentType;
      
      @XmlElement(name = "TimephasedData")
      protected List<TimephasedDataType> timephasedData;
      
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
       * 取得 id 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getID() {
        return id;
      }
      
      /**
       * 設定 id 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setID(BigInteger value) {
        this.id = value;
      }
      
      /**
       * 取得 name 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getName() {
        return name;
      }
      
      /**
       * 設定 name 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setName(String value) {
        this.name = value;
      }
      
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
       * 取得 isNull 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isIsNull() {
        return isNull;
      }
      
      /**
       * 設定 isNull 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setIsNull(Boolean value) {
        this.isNull = value;
      }
      
      /**
       * 取得 createDate 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getCreateDate() {
        return createDate;
      }
      
      /**
       * 設定 createDate 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setCreateDate(XMLGregorianCalendar value) {
        this.createDate = value;
      }
      
      /**
       * 取得 contact 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getContact() {
        return contact;
      }
      
      /**
       * 設定 contact 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setContact(String value) {
        this.contact = value;
      }
      
      /**
       * 取得 wbs 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getWBS() {
        return wbs;
      }
      
      /**
       * 設定 wbs 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setWBS(String value) {
        this.wbs = value;
      }
      
      /**
       * 取得 wbsLevel 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getWBSLevel() {
        return wbsLevel;
      }
      
      /**
       * 設定 wbsLevel 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setWBSLevel(String value) {
        this.wbsLevel = value;
      }
      
      /**
       * 取得 outlineNumber 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getOutlineNumber() {
        return outlineNumber;
      }
      
      /**
       * 設定 outlineNumber 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setOutlineNumber(String value) {
        this.outlineNumber = value;
      }
      
      /**
       * 取得 outlineLevel 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getOutlineLevel() {
        return outlineLevel;
      }
      
      /**
       * 設定 outlineLevel 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setOutlineLevel(BigInteger value) {
        this.outlineLevel = value;
      }
      
      /**
       * 取得 priority 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getPriority() {
        return priority;
      }
      
      /**
       * 設定 priority 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setPriority(BigInteger value) {
        this.priority = value;
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
       * 取得 duration 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getDuration() {
        return duration;
      }
      
      /**
       * 設定 duration 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setDuration(Duration value) {
        this.duration = value;
      }
      
      /**
       * 取得 durationFormat 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getDurationFormat() {
        return durationFormat;
      }
      
      /**
       * 設定 durationFormat 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setDurationFormat(BigInteger value) {
        this.durationFormat = value;
      }
      
      /**
       * 取得 work 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getWork() {
        return work;
      }
      
      /**
       * 設定 work 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setWork(Duration value) {
        this.work = value;
      }
      
      /**
       * 取得 stop 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getStop() {
        return stop;
      }
      
      /**
       * 設定 stop 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setStop(XMLGregorianCalendar value) {
        this.stop = value;
      }
      
      /**
       * 取得 resume 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getResume() {
        return resume;
      }
      
      /**
       * 設定 resume 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setResume(XMLGregorianCalendar value) {
        this.resume = value;
      }
      
      /**
       * 取得 resumeValid 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isResumeValid() {
        return resumeValid;
      }
      
      /**
       * 設定 resumeValid 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setResumeValid(Boolean value) {
        this.resumeValid = value;
      }
      
      /**
       * 取得 effortDriven 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isEffortDriven() {
        return effortDriven;
      }
      
      /**
       * 設定 effortDriven 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setEffortDriven(Boolean value) {
        this.effortDriven = value;
      }
      
      /**
       * 取得 recurring 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isRecurring() {
        return recurring;
      }
      
      /**
       * 設定 recurring 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setRecurring(Boolean value) {
        this.recurring = value;
      }
      
      /**
       * 取得 overAllocated 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isOverAllocated() {
        return overAllocated;
      }
      
      /**
       * 設定 overAllocated 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setOverAllocated(Boolean value) {
        this.overAllocated = value;
      }
      
      /**
       * 取得 estimated 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isEstimated() {
        return estimated;
      }
      
      /**
       * 設定 estimated 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setEstimated(Boolean value) {
        this.estimated = value;
      }
      
      /**
       * 取得 milestone 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isMilestone() {
        return milestone;
      }
      
      /**
       * 設定 milestone 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setMilestone(Boolean value) {
        this.milestone = value;
      }
      
      /**
       * 取得 summary 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isSummary() {
        return summary;
      }
      
      /**
       * 設定 summary 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setSummary(Boolean value) {
        this.summary = value;
      }
      
      /**
       * 取得 critical 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isCritical() {
        return critical;
      }
      
      /**
       * 設定 critical 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setCritical(Boolean value) {
        this.critical = value;
      }
      
      /**
       * 取得 isSubproject 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isIsSubproject() {
        return isSubproject;
      }
      
      /**
       * 設定 isSubproject 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setIsSubproject(Boolean value) {
        this.isSubproject = value;
      }
      
      /**
       * 取得 isSubprojectReadOnly 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isIsSubprojectReadOnly() {
        return isSubprojectReadOnly;
      }
      
      /**
       * 設定 isSubprojectReadOnly 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setIsSubprojectReadOnly(Boolean value) {
        this.isSubprojectReadOnly = value;
      }
      
      /**
       * 取得 subprojectName 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getSubprojectName() {
        return subprojectName;
      }
      
      /**
       * 設定 subprojectName 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setSubprojectName(String value) {
        this.subprojectName = value;
      }
      
      /**
       * 取得 externalTask 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isExternalTask() {
        return externalTask;
      }
      
      /**
       * 設定 externalTask 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setExternalTask(Boolean value) {
        this.externalTask = value;
      }
      
      /**
       * 取得 externalTaskProject 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getExternalTaskProject() {
        return externalTaskProject;
      }
      
      /**
       * 設定 externalTaskProject 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setExternalTaskProject(String value) {
        this.externalTaskProject = value;
      }
      
      /**
       * 取得 earlyStart 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getEarlyStart() {
        return earlyStart;
      }
      
      /**
       * 設定 earlyStart 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setEarlyStart(XMLGregorianCalendar value) {
        this.earlyStart = value;
      }
      
      /**
       * 取得 earlyFinish 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getEarlyFinish() {
        return earlyFinish;
      }
      
      /**
       * 設定 earlyFinish 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setEarlyFinish(XMLGregorianCalendar value) {
        this.earlyFinish = value;
      }
      
      /**
       * 取得 lateStart 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getLateStart() {
        return lateStart;
      }
      
      /**
       * 設定 lateStart 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setLateStart(XMLGregorianCalendar value) {
        this.lateStart = value;
      }
      
      /**
       * 取得 lateFinish 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getLateFinish() {
        return lateFinish;
      }
      
      /**
       * 設定 lateFinish 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setLateFinish(XMLGregorianCalendar value) {
        this.lateFinish = value;
      }
      
      /**
       * 取得 startVariance 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getStartVariance() {
        return startVariance;
      }
      
      /**
       * 設定 startVariance 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setStartVariance(BigInteger value) {
        this.startVariance = value;
      }
      
      /**
       * 取得 finishVariance 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getFinishVariance() {
        return finishVariance;
      }
      
      /**
       * 設定 finishVariance 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setFinishVariance(BigInteger value) {
        this.finishVariance = value;
      }
      
      /**
       * 取得 workVariance 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getWorkVariance() {
        return workVariance;
      }
      
      /**
       * 設定 workVariance 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setWorkVariance(Float value) {
        this.workVariance = value;
      }
      
      /**
       * 取得 freeSlack 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getFreeSlack() {
        return freeSlack;
      }
      
      /**
       * 設定 freeSlack 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setFreeSlack(BigInteger value) {
        this.freeSlack = value;
      }
      
      /**
       * 取得 totalSlack 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getTotalSlack() {
        return totalSlack;
      }
      
      /**
       * 設定 totalSlack 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setTotalSlack(BigInteger value) {
        this.totalSlack = value;
      }
      
      /**
       * 取得 fixedCost 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getFixedCost() {
        return fixedCost;
      }
      
      /**
       * 設定 fixedCost 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setFixedCost(Float value) {
        this.fixedCost = value;
      }
      
      /**
       * 取得 fixedCostAccrual 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getFixedCostAccrual() {
        return fixedCostAccrual;
      }
      
      /**
       * 設定 fixedCostAccrual 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setFixedCostAccrual(String value) {
        this.fixedCostAccrual = value;
      }
      
      /**
       * 取得 percentComplete 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getPercentComplete() {
        return percentComplete;
      }
      
      /**
       * 設定 percentComplete 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setPercentComplete(BigInteger value) {
        this.percentComplete = value;
      }
      
      /**
       * 取得 percentWorkComplete 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getPercentWorkComplete() {
        return percentWorkComplete;
      }
      
      /**
       * 設定 percentWorkComplete 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setPercentWorkComplete(BigInteger value) {
        this.percentWorkComplete = value;
      }
      
      /**
       * 取得 cost 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getCost() {
        return cost;
      }
      
      /**
       * 設定 cost 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setCost(BigDecimal value) {
        this.cost = value;
      }
      
      /**
       * 取得 overtimeCost 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getOvertimeCost() {
        return overtimeCost;
      }
      
      /**
       * 設定 overtimeCost 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setOvertimeCost(BigDecimal value) {
        this.overtimeCost = value;
      }
      
      /**
       * 取得 overtimeWork 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getOvertimeWork() {
        return overtimeWork;
      }
      
      /**
       * 設定 overtimeWork 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setOvertimeWork(Duration value) {
        this.overtimeWork = value;
      }
      
      /**
       * 取得 actualStart 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getActualStart() {
        return actualStart;
      }
      
      /**
       * 設定 actualStart 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setActualStart(XMLGregorianCalendar value) {
        this.actualStart = value;
      }
      
      /**
       * 取得 actualFinish 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getActualFinish() {
        return actualFinish;
      }
      
      /**
       * 設定 actualFinish 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setActualFinish(XMLGregorianCalendar value) {
        this.actualFinish = value;
      }
      
      /**
       * 取得 actualDuration 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getActualDuration() {
        return actualDuration;
      }
      
      /**
       * 設定 actualDuration 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setActualDuration(Duration value) {
        this.actualDuration = value;
      }
      
      /**
       * 取得 actualCost 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getActualCost() {
        return actualCost;
      }
      
      /**
       * 設定 actualCost 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setActualCost(BigDecimal value) {
        this.actualCost = value;
      }
      
      /**
       * 取得 actualOvertimeCost 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getActualOvertimeCost() {
        return actualOvertimeCost;
      }
      
      /**
       * 設定 actualOvertimeCost 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setActualOvertimeCost(BigDecimal value) {
        this.actualOvertimeCost = value;
      }
      
      /**
       * 取得 actualWork 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getActualWork() {
        return actualWork;
      }
      
      /**
       * 設定 actualWork 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setActualWork(Duration value) {
        this.actualWork = value;
      }
      
      /**
       * 取得 actualOvertimeWork 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getActualOvertimeWork() {
        return actualOvertimeWork;
      }
      
      /**
       * 設定 actualOvertimeWork 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setActualOvertimeWork(Duration value) {
        this.actualOvertimeWork = value;
      }
      
      /**
       * 取得 regularWork 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getRegularWork() {
        return regularWork;
      }
      
      /**
       * 設定 regularWork 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setRegularWork(Duration value) {
        this.regularWork = value;
      }
      
      /**
       * 取得 remainingDuration 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getRemainingDuration() {
        return remainingDuration;
      }
      
      /**
       * 設定 remainingDuration 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setRemainingDuration(Duration value) {
        this.remainingDuration = value;
      }
      
      /**
       * 取得 remainingCost 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getRemainingCost() {
        return remainingCost;
      }
      
      /**
       * 設定 remainingCost 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setRemainingCost(BigDecimal value) {
        this.remainingCost = value;
      }
      
      /**
       * 取得 remainingWork 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getRemainingWork() {
        return remainingWork;
      }
      
      /**
       * 設定 remainingWork 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setRemainingWork(Duration value) {
        this.remainingWork = value;
      }
      
      /**
       * 取得 remainingOvertimeCost 特性的值.
       *
       * @return possible object is {@link BigDecimal }
       */
      public BigDecimal getRemainingOvertimeCost() {
        return remainingOvertimeCost;
      }
      
      /**
       * 設定 remainingOvertimeCost 特性的值.
       *
       * @param value allowed object is {@link BigDecimal }
       */
      public void setRemainingOvertimeCost(BigDecimal value) {
        this.remainingOvertimeCost = value;
      }
      
      /**
       * 取得 remainingOvertimeWork 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getRemainingOvertimeWork() {
        return remainingOvertimeWork;
      }
      
      /**
       * 設定 remainingOvertimeWork 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setRemainingOvertimeWork(Duration value) {
        this.remainingOvertimeWork = value;
      }
      
      /**
       * 取得 acwp 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getACWP() {
        return acwp;
      }
      
      /**
       * 設定 acwp 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setACWP(Float value) {
        this.acwp = value;
      }
      
      /**
       * 取得 cv 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getCV() {
        return cv;
      }
      
      /**
       * 設定 cv 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setCV(Float value) {
        this.cv = value;
      }
      
      /**
       * 取得 constraintType 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getConstraintType() {
        return constraintType;
      }
      
      /**
       * 設定 constraintType 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setConstraintType(BigInteger value) {
        this.constraintType = value;
      }
      
      /**
       * 取得 calendarUID 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getCalendarUID() {
        return calendarUID;
      }
      
      /**
       * 設定 calendarUID 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setCalendarUID(BigInteger value) {
        this.calendarUID = value;
      }
      
      /**
       * 取得 constraintDate 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getConstraintDate() {
        return constraintDate;
      }
      
      /**
       * 設定 constraintDate 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setConstraintDate(XMLGregorianCalendar value) {
        this.constraintDate = value;
      }
      
      /**
       * 取得 deadline 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getDeadline() {
        return deadline;
      }
      
      /**
       * 設定 deadline 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setDeadline(XMLGregorianCalendar value) {
        this.deadline = value;
      }
      
      /**
       * 取得 levelAssignments 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isLevelAssignments() {
        return levelAssignments;
      }
      
      /**
       * 設定 levelAssignments 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setLevelAssignments(Boolean value) {
        this.levelAssignments = value;
      }
      
      /**
       * 取得 levelingCanSplit 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isLevelingCanSplit() {
        return levelingCanSplit;
      }
      
      /**
       * 設定 levelingCanSplit 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setLevelingCanSplit(Boolean value) {
        this.levelingCanSplit = value;
      }
      
      /**
       * 取得 levelingDelay 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getLevelingDelay() {
        return levelingDelay;
      }
      
      /**
       * 設定 levelingDelay 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setLevelingDelay(BigInteger value) {
        this.levelingDelay = value;
      }
      
      /**
       * 取得 levelingDelayFormat 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getLevelingDelayFormat() {
        return levelingDelayFormat;
      }
      
      /**
       * 設定 levelingDelayFormat 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setLevelingDelayFormat(BigInteger value) {
        this.levelingDelayFormat = value;
      }
      
      /**
       * 取得 preLeveledStart 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getPreLeveledStart() {
        return preLeveledStart;
      }
      
      /**
       * 設定 preLeveledStart 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setPreLeveledStart(XMLGregorianCalendar value) {
        this.preLeveledStart = value;
      }
      
      /**
       * 取得 preLeveledFinish 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getPreLeveledFinish() {
        return preLeveledFinish;
      }
      
      /**
       * 設定 preLeveledFinish 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setPreLeveledFinish(XMLGregorianCalendar value) {
        this.preLeveledFinish = value;
      }
      
      /**
       * 取得 hyperlink 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getHyperlink() {
        return hyperlink;
      }
      
      /**
       * 設定 hyperlink 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setHyperlink(String value) {
        this.hyperlink = value;
      }
      
      /**
       * 取得 hyperlinkAddress 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getHyperlinkAddress() {
        return hyperlinkAddress;
      }
      
      /**
       * 設定 hyperlinkAddress 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setHyperlinkAddress(String value) {
        this.hyperlinkAddress = value;
      }
      
      /**
       * 取得 hyperlinkSubAddress 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getHyperlinkSubAddress() {
        return hyperlinkSubAddress;
      }
      
      /**
       * 設定 hyperlinkSubAddress 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setHyperlinkSubAddress(String value) {
        this.hyperlinkSubAddress = value;
      }
      
      /**
       * 取得 ignoreResourceCalendar 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isIgnoreResourceCalendar() {
        return ignoreResourceCalendar;
      }
      
      /**
       * 設定 ignoreResourceCalendar 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setIgnoreResourceCalendar(Boolean value) {
        this.ignoreResourceCalendar = value;
      }
      
      /**
       * 取得 notes 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getNotes() {
        return notes;
      }
      
      /**
       * 設定 notes 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setNotes(String value) {
        this.notes = value;
      }
      
      /**
       * 取得 hideBar 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isHideBar() {
        return hideBar;
      }
      
      /**
       * 設定 hideBar 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setHideBar(Boolean value) {
        this.hideBar = value;
      }
      
      /**
       * 取得 rollup 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isRollup() {
        return rollup;
      }
      
      /**
       * 設定 rollup 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setRollup(Boolean value) {
        this.rollup = value;
      }
      
      /**
       * 取得 bcws 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getBCWS() {
        return bcws;
      }
      
      /**
       * 設定 bcws 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setBCWS(Float value) {
        this.bcws = value;
      }
      
      /**
       * 取得 bcwp 特性的值.
       *
       * @return possible object is {@link Float }
       */
      public Float getBCWP() {
        return bcwp;
      }
      
      /**
       * 設定 bcwp 特性的值.
       *
       * @param value allowed object is {@link Float }
       */
      public void setBCWP(Float value) {
        this.bcwp = value;
      }
      
      /**
       * 取得 physicalPercentComplete 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getPhysicalPercentComplete() {
        return physicalPercentComplete;
      }
      
      /**
       * 設定 physicalPercentComplete 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setPhysicalPercentComplete(BigInteger value) {
        this.physicalPercentComplete = value;
      }
      
      /**
       * 取得 earnedValueMethod 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getEarnedValueMethod() {
        return earnedValueMethod;
      }
      
      /**
       * 設定 earnedValueMethod 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setEarnedValueMethod(BigInteger value) {
        this.earnedValueMethod = value;
      }
      
      /**
       * Gets the value of the predecessorLink property.
       *
       * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
       * modification you make to the returned list will be present inside the JAXB object. This is
       * why there is not a <CODE>set</CODE> method for the predecessorLink property.
       *
       * <p>For example, to add a new item, do as follows:
       *
       * <pre>
       *    getPredecessorLink().add(newItem);
       * </pre>
       *
       * <p>Objects of the following type(s) are allowed in the list {@link PredecessorLink }
       */
      public List<PredecessorLink> getPredecessorLink() {
        if (predecessorLink == null) {
          predecessorLink = new ArrayList<PredecessorLink>();
        }
        return this.predecessorLink;
      }
      
      /**
       * 取得 actualWorkProtected 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getActualWorkProtected() {
        return actualWorkProtected;
      }
      
      /**
       * 設定 actualWorkProtected 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setActualWorkProtected(Duration value) {
        this.actualWorkProtected = value;
      }
      
      /**
       * 取得 actualOvertimeWorkProtected 特性的值.
       *
       * @return possible object is {@link Duration }
       */
      public Duration getActualOvertimeWorkProtected() {
        return actualOvertimeWorkProtected;
      }
      
      /**
       * 設定 actualOvertimeWorkProtected 特性的值.
       *
       * @param value allowed object is {@link Duration }
       */
      public void setActualOvertimeWorkProtected(Duration value) {
        this.actualOvertimeWorkProtected = value;
      }
      
      /**
       * Gets the value of the extendedAttribute property.
       *
       * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
       * modification you make to the returned list will be present inside the JAXB object. This is
       * why there is not a <CODE>set</CODE> method for the extendedAttribute property.
       *
       * <p>For example, to add a new item, do as follows:
       *
       * <pre>
       *    getExtendedAttribute().add(newItem);
       * </pre>
       *
       * <p>Objects of the following type(s) are allowed in the list {@link ExtendedAttribute }
       */
      public List<ExtendedAttribute> getExtendedAttribute() {
        if (extendedAttribute == null) {
          extendedAttribute = new ArrayList<ExtendedAttribute>();
        }
        return this.extendedAttribute;
      }
      
      /**
       * Gets the value of the baseline property.
       *
       * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
       * modification you make to the returned list will be present inside the JAXB object. This is
       * why there is not a <CODE>set</CODE> method for the baseline property.
       *
       * <p>For example, to add a new item, do as follows:
       *
       * <pre>
       *    getBaseline().add(newItem);
       * </pre>
       *
       * <p>Objects of the following type(s) are allowed in the list {@link Baseline }
       */
      public List<Baseline> getBaseline() {
        if (baseline == null) {
          baseline = new ArrayList<Baseline>();
        }
        return this.baseline;
      }
      
      /**
       * Gets the value of the outlineCode property.
       *
       * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
       * modification you make to the returned list will be present inside the JAXB object. This is
       * why there is not a <CODE>set</CODE> method for the outlineCode property.
       *
       * <p>For example, to add a new item, do as follows:
       *
       * <pre>
       *    getOutlineCode().add(newItem);
       * </pre>
       *
       * <p>Objects of the following type(s) are allowed in the list {@link OutlineCode }
       */
      public List<OutlineCode> getOutlineCode() {
        if (outlineCode == null) {
          outlineCode = new ArrayList<OutlineCode>();
        }
        return this.outlineCode;
      }
      
      /**
       * 取得 isPublished 特性的值.
       *
       * @return possible object is {@link Boolean }
       */
      public Boolean isIsPublished() {
        return isPublished;
      }
      
      /**
       * 設定 isPublished 特性的值.
       *
       * @param value allowed object is {@link Boolean }
       */
      public void setIsPublished(Boolean value) {
        this.isPublished = value;
      }
      
      /**
       * 取得 statusManager 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getStatusManager() {
        return statusManager;
      }
      
      /**
       * 設定 statusManager 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setStatusManager(String value) {
        this.statusManager = value;
      }
      
      /**
       * 取得 commitmentStart 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getCommitmentStart() {
        return commitmentStart;
      }
      
      /**
       * 設定 commitmentStart 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setCommitmentStart(XMLGregorianCalendar value) {
        this.commitmentStart = value;
      }
      
      /**
       * 取得 commitmentFinish 特性的值.
       *
       * @return possible object is {@link XMLGregorianCalendar }
       */
      public XMLGregorianCalendar getCommitmentFinish() {
        return commitmentFinish;
      }
      
      /**
       * 設定 commitmentFinish 特性的值.
       *
       * @param value allowed object is {@link XMLGregorianCalendar }
       */
      public void setCommitmentFinish(XMLGregorianCalendar value) {
        this.commitmentFinish = value;
      }
      
      /**
       * 取得 commitmentType 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getCommitmentType() {
        return commitmentType;
      }
      
      /**
       * 設定 commitmentType 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setCommitmentType(BigInteger value) {
        this.commitmentType = value;
      }
      
      /**
       * Gets the value of the timephasedData property.
       *
       * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
       * modification you make to the returned list will be present inside the JAXB object. This is
       * why there is not a <CODE>set</CODE> method for the timephasedData property.
       *
       * <p>For example, to add a new item, do as follows:
       *
       * <pre>
       *    getTimephasedData().add(newItem);
       * </pre>
       *
       * <p>Objects of the following type(s) are allowed in the list {@link TimephasedDataType }
       */
      public List<TimephasedDataType> getTimephasedData() {
        if (timephasedData == null) {
          timephasedData = new ArrayList<TimephasedDataType>();
        }
        return this.timephasedData;
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
        name = "",
        propOrder = {
          "timephasedData",
          "number",
          "interim",
          "start",
          "finish",
          "duration",
          "durationFormat",
          "estimatedDuration",
          "work",
          "cost",
          "bcws",
          "bcwp",
          "fixedCost"
        })
      public static class Baseline {
        
        @XmlElement(name = "TimephasedData")
        protected List<TimephasedDataType> timephasedData;
        
        @XmlElement(name = "Number")
        protected BigInteger number;
        
        @XmlElement(name = "Interim", defaultValue = "false")
        protected Boolean interim;
        
        @XmlElement(name = "Start")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar start;
        
        @XmlElement(name = "Finish")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar finish;
        
        @XmlElement(name = "Duration")
        protected Duration duration;
        
        @XmlElement(name = "DurationFormat")
        protected BigInteger durationFormat;
        
        @XmlElement(name = "EstimatedDuration")
        protected Boolean estimatedDuration;
        
        @XmlElement(name = "Work")
        protected Duration work;
        
        @XmlElement(name = "Cost")
        protected BigDecimal cost;
        
        @XmlElement(name = "BCWS")
        protected Float bcws;
        
        @XmlElement(name = "BCWP")
        protected Float bcwp;
        
        @XmlElement(name = "FixedCost")
        protected Float fixedCost;
        
        /**
         * Gets the value of the timephasedData property.
         *
         * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore
         * any modification you make to the returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the timephasedData property.
         *
         * <p>For example, to add a new item, do as follows:
         *
         * <pre>
         *    getTimephasedData().add(newItem);
         * </pre>
         *
         * <p>Objects of the following type(s) are allowed in the list {@link TimephasedDataType }
         */
        public List<TimephasedDataType> getTimephasedData() {
          if (timephasedData == null) {
            timephasedData = new ArrayList<TimephasedDataType>();
          }
          return this.timephasedData;
        }
        
        /**
         * 取得 number 特性的值.
         *
         * @return possible object is {@link BigInteger }
         */
        public BigInteger getNumber() {
          return number;
        }
        
        /**
         * 設定 number 特性的值.
         *
         * @param value allowed object is {@link BigInteger }
         */
        public void setNumber(BigInteger value) {
          this.number = value;
        }
        
        /**
         * 取得 interim 特性的值.
         *
         * @return possible object is {@link Boolean }
         */
        public Boolean isInterim() {
          return interim;
        }
        
        /**
         * 設定 interim 特性的值.
         *
         * @param value allowed object is {@link Boolean }
         */
        public void setInterim(Boolean value) {
          this.interim = value;
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
         * 取得 duration 特性的值.
         *
         * @return possible object is {@link Duration }
         */
        public Duration getDuration() {
          return duration;
        }
        
        /**
         * 設定 duration 特性的值.
         *
         * @param value allowed object is {@link Duration }
         */
        public void setDuration(Duration value) {
          this.duration = value;
        }
        
        /**
         * 取得 durationFormat 特性的值.
         *
         * @return possible object is {@link BigInteger }
         */
        public BigInteger getDurationFormat() {
          return durationFormat;
        }
        
        /**
         * 設定 durationFormat 特性的值.
         *
         * @param value allowed object is {@link BigInteger }
         */
        public void setDurationFormat(BigInteger value) {
          this.durationFormat = value;
        }
        
        /**
         * 取得 estimatedDuration 特性的值.
         *
         * @return possible object is {@link Boolean }
         */
        public Boolean isEstimatedDuration() {
          return estimatedDuration;
        }
        
        /**
         * 設定 estimatedDuration 特性的值.
         *
         * @param value allowed object is {@link Boolean }
         */
        public void setEstimatedDuration(Boolean value) {
          this.estimatedDuration = value;
        }
        
        /**
         * 取得 work 特性的值.
         *
         * @return possible object is {@link Duration }
         */
        public Duration getWork() {
          return work;
        }
        
        /**
         * 設定 work 特性的值.
         *
         * @param value allowed object is {@link Duration }
         */
        public void setWork(Duration value) {
          this.work = value;
        }
        
        /**
         * 取得 cost 特性的值.
         *
         * @return possible object is {@link BigDecimal }
         */
        public BigDecimal getCost() {
          return cost;
        }
        
        /**
         * 設定 cost 特性的值.
         *
         * @param value allowed object is {@link BigDecimal }
         */
        public void setCost(BigDecimal value) {
          this.cost = value;
        }
        
        /**
         * 取得 bcws 特性的值.
         *
         * @return possible object is {@link Float }
         */
        public Float getBCWS() {
          return bcws;
        }
        
        /**
         * 設定 bcws 特性的值.
         *
         * @param value allowed object is {@link Float }
         */
        public void setBCWS(Float value) {
          this.bcws = value;
        }
        
        /**
         * 取得 bcwp 特性的值.
         *
         * @return possible object is {@link Float }
         */
        public Float getBCWP() {
          return bcwp;
        }
        
        /**
         * 設定 bcwp 特性的值.
         *
         * @param value allowed object is {@link Float }
         */
        public void setBCWP(Float value) {
          this.bcwp = value;
        }
        
        /**
         * 取得 fixedCost 特性的值.
         *
         * @return possible object is {@link Float }
         */
        public Float getFixedCost() {
          return fixedCost;
        }
        
        /**
         * 設定 fixedCost 特性的值.
         *
         * @param value allowed object is {@link Float }
         */
        public void setFixedCost(Float value) {
          this.fixedCost = value;
        }
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
        name = "",
        propOrder = {"fieldID", "value", "valueGUID", "durationFormat"})
      public static class ExtendedAttribute {
        
        @XmlElement(name = "FieldID")
        protected String fieldID;
        
        @XmlElement(name = "Value")
        protected String value;
        
        @XmlElement(name = "ValueGUID")
        protected BigInteger valueGUID;
        
        @XmlElement(name = "DurationFormat")
        protected BigInteger durationFormat;
        
        /**
         * 取得 fieldID 特性的值.
         *
         * @return possible object is {@link String }
         */
        public String getFieldID() {
          return fieldID;
        }
        
        /**
         * 設定 fieldID 特性的值.
         *
         * @param value allowed object is {@link String }
         */
        public void setFieldID(String value) {
          this.fieldID = value;
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
        
        /**
         * 取得 valueGUID 特性的值.
         *
         * @return possible object is {@link BigInteger }
         */
        public BigInteger getValueGUID() {
          return valueGUID;
        }
        
        /**
         * 設定 valueGUID 特性的值.
         *
         * @param value allowed object is {@link BigInteger }
         */
        public void setValueGUID(BigInteger value) {
          this.valueGUID = value;
        }
        
        /**
         * 取得 durationFormat 特性的值.
         *
         * @return possible object is {@link BigInteger }
         */
        public BigInteger getDurationFormat() {
          return durationFormat;
        }
        
        /**
         * 設定 durationFormat 特性的值.
         *
         * @param value allowed object is {@link BigInteger }
         */
        public void setDurationFormat(BigInteger value) {
          this.durationFormat = value;
        }
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
        name = "",
        propOrder = {"fieldID", "valueID", "valueGUID"})
      public static class OutlineCode {
        
        @XmlElement(name = "FieldID")
        protected String fieldID;
        
        @XmlElement(name = "ValueID")
        protected BigInteger valueID;
        
        @XmlElement(name = "ValueGUID")
        protected BigInteger valueGUID;
        
        /**
         * 取得 fieldID 特性的值.
         *
         * @return possible object is {@link String }
         */
        public String getFieldID() {
          return fieldID;
        }
        
        /**
         * 設定 fieldID 特性的值.
         *
         * @param value allowed object is {@link String }
         */
        public void setFieldID(String value) {
          this.fieldID = value;
        }
        
        /**
         * 取得 valueID 特性的值.
         *
         * @return possible object is {@link BigInteger }
         */
        public BigInteger getValueID() {
          return valueID;
        }
        
        /**
         * 設定 valueID 特性的值.
         *
         * @param value allowed object is {@link BigInteger }
         */
        public void setValueID(BigInteger value) {
          this.valueID = value;
        }
        
        /**
         * 取得 valueGUID 特性的值.
         *
         * @return possible object is {@link BigInteger }
         */
        public BigInteger getValueGUID() {
          return valueGUID;
        }
        
        /**
         * 設定 valueGUID 特性的值.
         *
         * @param value allowed object is {@link BigInteger }
         */
        public void setValueGUID(BigInteger value) {
          this.valueGUID = value;
        }
      }
      
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
        name = "",
        propOrder = {
          "predecessorUID", "type", "crossProject",
          "crossProjectName", "linkLag", "lagFormat"
        })
      public static class PredecessorLink {
        
        @XmlElement(name = "PredecessorUID")
        protected BigInteger predecessorUID;
        
        @XmlElement(name = "Type")
        protected BigInteger type;
        
        @XmlElement(name = "CrossProject")
        protected Boolean crossProject;
        
        @XmlElement(name = "CrossProjectName")
        protected String crossProjectName;
        
        @XmlElement(name = "LinkLag")
        protected BigInteger linkLag;
        
        @XmlElement(name = "LagFormat")
        protected BigInteger lagFormat;
        
        /**
         * 取得 predecessorUID 特性的值.
         *
         * @return possible object is {@link BigInteger }
         */
        public BigInteger getPredecessorUID() {
          return predecessorUID;
        }
        
        /**
         * 設定 predecessorUID 特性的值.
         *
         * @param value allowed object is {@link BigInteger }
         */
        public void setPredecessorUID(BigInteger value) {
          this.predecessorUID = value;
        }
        
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
         * 取得 crossProject 特性的值.
         *
         * @return possible object is {@link Boolean }
         */
        public Boolean isCrossProject() {
          return crossProject;
        }
        
        /**
         * 設定 crossProject 特性的值.
         *
         * @param value allowed object is {@link Boolean }
         */
        public void setCrossProject(Boolean value) {
          this.crossProject = value;
        }
        
        /**
         * 取得 crossProjectName 特性的值.
         *
         * @return possible object is {@link String }
         */
        public String getCrossProjectName() {
          return crossProjectName;
        }
        
        /**
         * 設定 crossProjectName 特性的值.
         *
         * @param value allowed object is {@link String }
         */
        public void setCrossProjectName(String value) {
          this.crossProjectName = value;
        }
        
        /**
         * 取得 linkLag 特性的值.
         *
         * @return possible object is {@link BigInteger }
         */
        public BigInteger getLinkLag() {
          return linkLag;
        }
        
        /**
         * 設定 linkLag 特性的值.
         *
         * @param value allowed object is {@link BigInteger }
         */
        public void setLinkLag(BigInteger value) {
          this.linkLag = value;
        }
        
        /**
         * 取得 lagFormat 特性的值.
         *
         * @return possible object is {@link BigInteger }
         */
        public BigInteger getLagFormat() {
          return lagFormat;
        }
        
        /**
         * 設定 lagFormat 特性的值.
         *
         * @param value allowed object is {@link BigInteger }
         */
        public void setLagFormat(BigInteger value) {
          this.lagFormat = value;
        }
      }
    }
  }
  
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(
    name = "",
    propOrder = {"verifyUniqueCodes", "generateCodes", "prefix", "wbsMask"})
  public static class WBSMasks {
    
    @XmlElement(name = "VerifyUniqueCodes", defaultValue = "false")
    protected Boolean verifyUniqueCodes;
    
    @XmlElement(name = "GenerateCodes", defaultValue = "false")
    protected Boolean generateCodes;
    
    @XmlElement(name = "Prefix")
    protected String prefix;
    
    @XmlElement(name = "WBSMask")
    protected List<WBSMask> wbsMask;
    
    /**
     * 取得 verifyUniqueCodes 特性的值.
     *
     * @return possible object is {@link Boolean }
     */
    public Boolean isVerifyUniqueCodes() {
      return verifyUniqueCodes;
    }
    
    /**
     * 設定 verifyUniqueCodes 特性的值.
     *
     * @param value allowed object is {@link Boolean }
     */
    public void setVerifyUniqueCodes(Boolean value) {
      this.verifyUniqueCodes = value;
    }
    
    /**
     * 取得 generateCodes 特性的值.
     *
     * @return possible object is {@link Boolean }
     */
    public Boolean isGenerateCodes() {
      return generateCodes;
    }
    
    /**
     * 設定 generateCodes 特性的值.
     *
     * @param value allowed object is {@link Boolean }
     */
    public void setGenerateCodes(Boolean value) {
      this.generateCodes = value;
    }
    
    /**
     * 取得 prefix 特性的值.
     *
     * @return possible object is {@link String }
     */
    public String getPrefix() {
      return prefix;
    }
    
    /**
     * 設定 prefix 特性的值.
     *
     * @param value allowed object is {@link String }
     */
    public void setPrefix(String value) {
      this.prefix = value;
    }
    
    /**
     * Gets the value of the wbsMask property.
     *
     * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the wbsMask property.
     *
     * <p>For example, to add a new item, do as follows:
     *
     * <pre>
     *    getWBSMask().add(newItem);
     * </pre>
     *
     * <p>Objects of the following type(s) are allowed in the list {@link WBSMask }
     */
    public List<WBSMask> getWBSMask() {
      if (wbsMask == null) {
        wbsMask = new ArrayList<WBSMask>();
      }
      return this.wbsMask;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(
      name = "",
      propOrder = {"level", "type", "length", "separator"})
    public static class WBSMask {
      
      @XmlElement(name = "Level", required = true)
      protected BigInteger level;
      
      @XmlElement(name = "Type", required = true)
      protected BigInteger type;
      
      @XmlElement(name = "Length", required = true)
      protected String length;
      
      @XmlElement(name = "Separator", required = true)
      protected String separator;
      
      /**
       * 取得 level 特性的值.
       *
       * @return possible object is {@link BigInteger }
       */
      public BigInteger getLevel() {
        return level;
      }
      
      /**
       * 設定 level 特性的值.
       *
       * @param value allowed object is {@link BigInteger }
       */
      public void setLevel(BigInteger value) {
        this.level = value;
      }
      
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
       * 取得 length 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getLength() {
        return length;
      }
      
      /**
       * 設定 length 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setLength(String value) {
        this.length = value;
      }
      
      /**
       * 取得 separator 特性的值.
       *
       * @return possible object is {@link String }
       */
      public String getSeparator() {
        return separator;
      }
      
      /**
       * 設定 separator 特性的值.
       *
       * @param value allowed object is {@link String }
       */
      public void setSeparator(String value) {
        this.separator = value;
      }
    }
  }
}
