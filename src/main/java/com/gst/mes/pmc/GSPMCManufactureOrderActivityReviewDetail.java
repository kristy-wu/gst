package com.gst.mes.pmc;

import com.dcsplab.common.DLDateUtils;
import com.dcsplab.common.DLDateUtils.Resolution;
import com.dcsplab.vdui.components.VDDatePicker;
import com.dcsplab.vdui.components.VDTextField;
import com.dcsplab.vdui.components.VDTimePicker;
import com.dcsplab.vdui.layout.VDFormLayout;
import com.gst.broadcast.GSBroadcastMessage;
import com.gst.broadcast.GSPMCBroadcaster;
import com.gst.context.GSContext;
import com.gst.domain.GSDepartment;
import com.gst.domain.GSMember;
import com.gst.domain.GSPMCManufactureOrder;
import com.gst.domain.GSPMCManufactureOrderActivity;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.framework.util.GSDateStringConverter;
import com.gst.service.GSOrganizationService;
import com.gst.service.GSPMCService;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GSPMCManufactureOrderActivityReviewDetail extends GSDetailFormBase {
  
  private static final long serialVersionUID = 7649424498839570079L;
  private static final Logger logger =
    LoggerFactory.getLogger(GSPMCManufactureOrderActivityReviewDetail.class);
  
  private double unitValue = 0;
  
  private GSPMCManufactureOrderActivity data;
  
  private final Binder<GSPMCManufactureOrderActivity> binder =
    new Binder<>(GSPMCManufactureOrderActivity.class);
  
  private final DateTimeFormatter formatter =
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withLocale(Locale.TAIWAN);
  
  private final GSDateStringConverter dateConverter = new GSDateStringConverter();
  
  // 報工部門
  @PropertyId("deptId")
  private VDTextField vdReportingDeptId;
  
  private VDTextField vdReportingDeptDisplay;
  
  // 報工人員
  @PropertyId("memberId")
  private VDTextField vdReportingMemberId;
  
  private VDTextField vdReportingMemberDisplay;
  
  // 製令單別
  @PropertyId("manufactureOrderTypeId")
  private VDTextField vdReportingManufactureOrderTypeId;
  
  // 製令單號
  @PropertyId("manufactureOrderId")
  private VDTextField vdReportingManufactureOrderId;
  
  // 開工時間
  private VDTextField vdReportingStartTime;
  private VDDatePicker vdReportingDatePickerStartTime;
  private VDTimePicker vdReportingTimePickerStartTime;
  
  // 完工時間
  private VDTextField vdReportingFinishTime;
  private VDDatePicker vdReportingDatePickerFinishTime;
  private VDTimePicker vdReportingTimePickerFinishTime;
  
  // 無效工時(min) TA007  20201/04/21 --> 開工時間 - 前次報工時間
  @PropertyId("voidTime")
  private VDTextField vdReportingVoidTime;
  
  // 報工數量
  @PropertyId("workingAmount")
  private VDTextField vdReportingAmount;
  
  // 審核狀態
  @PropertyId("reviewStatus")
  private VDTextField vdReportingReviewStatus;
  
  // 報工包裝數量
  @PropertyId("packAmount")
  private VDTextField vdReportingPackAmount;
  
  // 預設批號
  @PropertyId("defaultLotNumber")
  private VDTextField vdReportingDefaultLotNumber;
  
  // 製令線別
  private VDTextField vdMOProductLine;
  
  // 產品品號
  private VDTextField vdMOPartNumber;
  
  // 製令包裝數量
  private VDTextField vdMOPackAmount;
  
  // 製令規格
  private VDTextField vdMOProductSpec;
  
  // 預計開工日
  private VDTextField vdMOEstimateStartDate;
  
  // 實際開工日
  private VDTextField vdMOActualStartDate;
  
  // 製令數量
  private VDTextField vdMOAmount;
  
  private GSFormChangeHandler handler;
  
  private final GSMember contextUser;
  
  private final GSPMCService PMCService;
  
  private GSPMCManufactureOrder m_order;
  
  public GSPMCManufactureOrderActivityReviewDetail() {
    contextUser = GSContext.getCurrentMember();
    PMCService = GSContext.getApplication().getPCMService();
    
    this.setHideBasicInfo(true);
    initFields();
  }
  
  private void initFields() {
    vdReportingReviewStatus = new VDTextField();
    vdReportingManufactureOrderTypeId = new VDTextField();
    vdReportingManufactureOrderId = new VDTextField();
    
    vdMOProductLine = new VDTextField(); // 線別
    vdMOProductLine.setReadOnly(true);
    vdMOProductLine.setWidthFull();
    
    vdMOPartNumber = new VDTextField(); // 產品品號
    vdMOPartNumber.setWidthFull();
    vdMOPartNumber.setReadOnly(true);
    
    vdMOProductSpec = new VDTextField(); // 規格
    vdMOProductSpec.setReadOnly(true);
    vdMOProductSpec.setWidthFull();
    
    FormLayout formLayout_1 = new VDFormLayout();
    formLayout_1.getStyle().set("margin-top", "3px");
    formLayout_1.addFormItem(vdMOProductLine, "線別");
    formLayout_1.addFormItem(vdMOPartNumber, "產品品號");
    FormLayout.FormItem specItem = formLayout_1.addFormItem(vdMOProductSpec, "規格");
    specItem.getElement().setAttribute("colspan", "2");
    formLayout_1.setResponsiveSteps(
      new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.ASIDE),
      new FormLayout.ResponsiveStep("400px", 2, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));
    
    vdReportingDeptId = new VDTextField();
    vdReportingDeptDisplay = new VDTextField(); // 報工部門
    vdReportingDeptDisplay.setReadOnly(true);
    vdReportingDeptDisplay.setWidthFull();
    
    vdReportingMemberId = new VDTextField(); // 報工人員
    vdReportingMemberDisplay = new VDTextField();
    vdReportingMemberDisplay.setReadOnly(true);
    vdReportingMemberDisplay.setWidthFull();
    
    vdMOAmount = new VDTextField(); // 製令數量
    vdMOAmount.setReadOnly(true);
    vdMOAmount.setWidthFull();
    
    vdReportingAmount = new VDTextField(); // 報工數量
    vdReportingAmount.setWidthFull();
    vdReportingAmount.setRequired(true);
    vdReportingAmount.setRequiredIndicatorVisible(true);
    vdReportingAmount.addValueChangeListener(
      e -> {
        if (e.isFromClient()) {
          String val = e.getOldValue();
          String newVal = e.getValue();
          try {
            double nv = Double.parseDouble(newVal);
            if (unitValue > 0) {
              String fmt = String.format("%.4f", (nv / unitValue));
              vdReportingPackAmount.setValue(fmt);
            }
          } catch (Exception ex) {
            if (val == null) {
              val = "";
            }
            e.getSource().setValue(val);
          }
        }
      });
    
    vdMOPackAmount = new VDTextField(); // 製令包裝數量
    vdMOPackAmount.setReadOnly(true);
    
    vdReportingPackAmount = new VDTextField(); // 報工包裝數量
    vdReportingPackAmount.setRequired(true);
    vdReportingPackAmount.setRequiredIndicatorVisible(true);
    vdReportingPackAmount.addValueChangeListener(
      e -> {
        if (e.isFromClient()) {
          String val = e.getOldValue();
          String newVal = e.getValue();
          try {
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(2);
            nf.setRoundingMode(RoundingMode.FLOOR);
            
            double nv = Double.parseDouble(newVal);
            vdReportingAmount.setValue("" + (nv * unitValue));
          } catch (Exception ex) {
            if (val == null) {
              val = "";
            }
            e.getSource().setValue(val);
          }
        }
      });
    
    vdReportingStartTime = new VDTextField(); // 開工時間
    vdReportingStartTime.setReadOnly(true);
    vdReportingDatePickerStartTime = new VDDatePicker(); // 開工日期
    vdReportingDatePickerStartTime.setRequired(true);
    vdReportingDatePickerStartTime.setRequiredIndicatorVisible(true);
    vdReportingDatePickerStartTime.addValueChangeListener(
      e -> {
        if (e.isFromClient()) {
          resetElapsedTime();
        }
      });
    
    vdReportingTimePickerStartTime = new VDTimePicker(); // 時間
    vdReportingTimePickerStartTime.setRequired(true);
    vdReportingTimePickerStartTime.setRequiredIndicatorVisible(true);
    vdReportingTimePickerStartTime.setStep(Duration.ofMinutes(1L));
    vdReportingTimePickerStartTime.addValueChangeListener(
      e -> {
        if (e.isFromClient()) {
          resetElapsedTime();
        }
      });
    
    vdMOEstimateStartDate = new VDTextField(); // 預計開工日
    vdMOActualStartDate = new VDTextField(); // 實際開工日
    
    vdReportingFinishTime = new VDTextField(); // 報工時間
    vdReportingFinishTime.setReadOnly(true);
    vdReportingDatePickerFinishTime = new VDDatePicker(); // 報工日期
    vdReportingDatePickerFinishTime.setRequired(true);
    vdReportingDatePickerFinishTime.setRequiredIndicatorVisible(true);
    vdReportingDatePickerFinishTime.addValueChangeListener(
      e -> {
        if (e.isFromClient()) {
          resetElapsedTime();
        }
      });
    
    vdReportingTimePickerFinishTime = new VDTimePicker(); // 報工時間
    vdReportingTimePickerFinishTime.setRequired(true);
    vdReportingTimePickerFinishTime.setRequiredIndicatorVisible(true);
    vdReportingTimePickerFinishTime.setStep(Duration.ofMinutes(1L));
    vdReportingTimePickerFinishTime.addValueChangeListener(
      e -> {
        if (e.isFromClient()) {
          resetElapsedTime();
        }
      });
    
    vdReportingVoidTime = new VDTextField(); // 花費工時(min)
    vdReportingVoidTime.setReadOnly(true);
    
    vdReportingDefaultLotNumber = new VDTextField(); // 預設批號
    vdReportingDefaultLotNumber.setRequired(true);
    vdReportingDefaultLotNumber.setRequiredIndicatorVisible(true);
    
    FormLayout formLayout_2 = new VDFormLayout();
    formLayout_2.getStyle().set("margin-top", "3px");
    formLayout_2.addFormItem(vdReportingDeptDisplay, "報工部門");
    formLayout_2.addFormItem(vdReportingMemberDisplay, "報工人員");
    formLayout_2.addFormItem(vdMOAmount, "製令數量");
    formLayout_2.addFormItem(vdReportingAmount, "報工數量");
    formLayout_2.addFormItem(vdMOPackAmount, "製令包裝數量");
    formLayout_2.addFormItem(vdReportingPackAmount, "報工包裝數量");
    formLayout_2.addFormItem(vdReportingDatePickerStartTime, "開工日期");
    formLayout_2.addFormItem(vdReportingTimePickerStartTime, "時間");
    formLayout_2.addFormItem(vdMOEstimateStartDate, "預計開工日");
    formLayout_2.addFormItem(vdMOActualStartDate, "實際開工日");
    formLayout_2.addFormItem(vdReportingDatePickerFinishTime, "報工日期");
    formLayout_2.addFormItem(vdReportingTimePickerFinishTime, "報工時間");
    formLayout_2.addFormItem(vdReportingVoidTime, "花費工時(min)");
    formLayout_2.addFormItem(vdReportingDefaultLotNumber, "預設批號");
    
    formLayout_2.setResponsiveSteps(
      new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.ASIDE),
      new FormLayout.ResponsiveStep("400px", 2, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));
    
    add(formLayout_1, formLayout_2);
  }
  
  private void resetElapsedTime() {
    try {
      String startDate = vdReportingDatePickerStartTime.getValue().toString();
      String startTime = vdReportingTimePickerStartTime.getValue().toString();
      String a = startDate + " " + startTime;
      // logger.info("開工:" + a);
      
      String endDate = vdReportingDatePickerFinishTime.getValue().toString();
      String endTime = vdReportingTimePickerFinishTime.getValue().toString();
      String b = endDate + " " + endTime;
      // logger.info("報工:" + b);
      
      LocalDateTime date1 = LocalDateTime.parse(a, formatter);
      LocalDateTime date2 = LocalDateTime.parse(b, formatter);
      Duration duration = Duration.between(date1, date2);
      long minute = duration.getSeconds() / 60L;
      vdReportingVoidTime.setValue("" + minute);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
  }
  
  public boolean isObsolete() {
    String value = data.getReviewStatus().trim();
    return GSPMCManufactureOrderActivityReviewList.TYPE_OBSOLETE.equals(value);
  }
  
  public boolean isReviewed() {
    String value = data.getReviewStatus().trim();
    return GSPMCManufactureOrderActivityReviewList.TYPE_REVIEWED.equals(value);
  }
  
  public boolean isReviewClosed() {
    String value = data.getReviewStatus().trim();
    
    return GSPMCManufactureOrderActivityReviewList.TYPE_CLOSED.equals(value)
      || GSPMCManufactureOrderActivityReviewList.TYPE_CLOSED_BY_SYSTEM.equals(value);
  }
  
  public boolean isManufactureOrderCompleted() {
    return "Y".equals(m_order.getStatusCode());
  }
  
  public void reviewConfirm() {
    // 開工時間
    String strStartDate = vdReportingDatePickerStartTime.getValue().toString();
    String strStartTime = vdReportingTimePickerStartTime.getValue().toString();
    if (strStartTime.length() < 6) {
      strStartTime += ":00";
    }
    String lineOpenTime = strStartDate + " " + strStartTime;
    logger.info("lineOpenTime: " + lineOpenTime);
    
    // 報工時間
    String strFinishDate = vdReportingDatePickerFinishTime.getValue().toString();
    String strFinishTime = vdReportingTimePickerFinishTime.getValue().toString();
    if (strFinishTime.length() < 6) {
      strFinishTime += ":00";
    }
    String lineCloseTime = strFinishDate + " " + strFinishTime;
    logger.info("lineCloseTime: " + lineCloseTime);
    
    String TA001 = data.getDeptId().trim();
    String TA002 = data.getMemberId().trim();
    String TA003 = data.getManufactureOrderTypeId().trim();
    String TA004 = data.getManufactureOrderId().trim();
    
    Date startTime = data.getStartTime();
    
    // 若 TA005(開工時間) 有變更, 則刪除原始資料 origData
    GSPMCManufactureOrderActivity origReport =
      PMCService.getManufactureOrderActivityReport(
        data.getCompanyId(), data.getDeptId(), data.getMemberId(), data.getStartTime());
    if (origReport == null) {
      logger.error("PMC Review[" + TA003 + "-" + TA004 + "-" + startTime + "] data not found!");
      Notification.show("報工資料錯誤，請聯絡系統管理員", 2000, Position.MIDDLE);
      return;
    }
    
    boolean isStartTimeChanged = false;
    try {
      Date newStartTime = DLDateUtils.parse(lineOpenTime, Resolution.Full);
      isStartTimeChanged = startTime.getTime() != newStartTime.getTime();
      if (isStartTimeChanged) {
        logger.info("PMC Review[" + TA001 + "-" + TA002 + "] start time changed...");
        data.setStartTime(newStartTime);
      }
      
      Date newFinishTime = DLDateUtils.parse(lineCloseTime, Resolution.Full);
      data.setFinishTime(newFinishTime);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    Date now = Calendar.getInstance().getTime();
    String modidate = DLDateUtils.format(now, Resolution.SimpleDate);
    String moditime = DLDateUtils.format(now, Resolution.Time);
    
    data.setModifier(contextUser.getId());
    data.setModifyDate(modidate);
    data.setModifyTime(moditime);
    data.setFlag(data.getFlag() + 1);
    
    GSPMCManufactureOrder m_order = getManufactureOrder();
    String productLine = m_order.getProductLine();
    Double estimateAmount = m_order.getEstimateAmount();
    Double finishedAmount = m_order.getFinishedAmount();
    
    double reportinAmount = Double.parseDouble(vdReportingAmount.getValue());
    finishedAmount += reportinAmount;
    if (finishedAmount >= estimateAmount) {
      m_order.setFinishedAmount(finishedAmount);
      m_order.setStatusCode("Y");
      PMCService.updateManufactureOrder(contextUser, m_order);
      
      GSPMCProductLineControl.resetProductLine(productLine);
      GSPMCProductLineControl.resetSequence(productLine);
      
      data.setWorkingAmount(finishedAmount);
      data.setReviewStatus(GSPMCManufactureOrderActivityReviewList.TYPE_CLOSED_BY_SYSTEM);
      data.setDefaultLotNumber(vdReportingDefaultLotNumber.getValue());
      PMCService.updateManufactureOrderActivity(contextUser, data);
      ((GSPMCManufactureOrderActivityReviewList) handler).updateRow(data);
      
      if (isStartTimeChanged) {
        logger.info(
          "["
            + m_order.getManufactureOrderTypeId()
            + "-"
            + m_order.getManufactureOrderId()
            + "] 審核單, 開工日期已變更, 刪除原始資料...");
        PMCService.deleteManufactureOrderActivity(origReport);
      }
      
      Notification.show("已達成預計生產數量，系統自動結案.", 2000, Position.MIDDLE);
      logger.info(
        "["
          + m_order.getManufactureOrderTypeId()
          + "-"
          + m_order.getManufactureOrderId()
          + "] 已達成預計生產數量，系統自動結案");
      
      getUI()
        .ifPresent(
          ui -> {
            GSBroadcastMessage message = new GSBroadcastMessage();
            message.setContent(m_order);
            GSPMCBroadcaster.broadcast(ui, message);
          });
      
      return;
    }
    
    m_order.setFinishedAmount(finishedAmount);
    PMCService.updateManufactureOrder(contextUser, m_order);
    
    data.setWorkingAmount(finishedAmount);
    data.setReviewStatus(GSPMCManufactureOrderActivityReviewList.TYPE_REVIEWED);
    data.setDefaultLotNumber(vdReportingDefaultLotNumber.getValue());
    PMCService.updateManufactureOrderActivity(contextUser, data);
    ((GSPMCManufactureOrderActivityReviewList) handler).updateRow(data);
    
    if (isStartTimeChanged) {
      logger.info(
        "["
          + m_order.getManufactureOrderTypeId()
          + "-"
          + m_order.getManufactureOrderId()
          + "] 審核單, 開工日期已變更, 刪除原始資料...");
      PMCService.deleteManufactureOrderActivity(origReport);
    }
    
    GSPMCProductLineControl.resetProductLine(productLine);
    
    getUI()
      .ifPresent(
        ui -> {
          GSBroadcastMessage message = new GSBroadcastMessage();
          message.setContent(m_order);
          GSPMCBroadcaster.broadcast(ui, message);
        });
    
    logger.info(
      "["
        + m_order.getManufactureOrderTypeId()
        + "-"
        + m_order.getManufactureOrderId()
        + "] 已審核完畢");
  }
  
  public void reviewClose() {
    GSPMCManufactureOrder m_order = getManufactureOrder();
    m_order.setStatusCode("Y");
    PMCService.updateManufactureOrder(contextUser, m_order);
    
    String productLine = m_order.getProductLine();
    GSPMCProductLineControl.resetProductLine(productLine);
    GSPMCProductLineControl.resetSequence(productLine);
    
    Date now = Calendar.getInstance().getTime();
    String modidate = DLDateUtils.format(now, Resolution.SimpleDate);
    String moditime = DLDateUtils.format(now, Resolution.Time);
    
    data.setModifier(contextUser.getId());
    data.setModifyDate(modidate);
    data.setModifyTime(moditime);
    data.setFlag(data.getFlag() + 1);
    
    data.setReviewStatus(GSPMCManufactureOrderActivityReviewList.TYPE_CLOSED); // 結案
    data.setDefaultLotNumber(vdReportingDefaultLotNumber.getValue());
    PMCService.updateManufactureOrderActivity(contextUser, data);
    ((GSPMCManufactureOrderActivityReviewList) handler).updateRow(data);
    
    getUI()
      .ifPresent(
        ui -> {
          GSBroadcastMessage message = new GSBroadcastMessage();
          message.setContent(m_order);
          GSPMCBroadcaster.broadcast(ui, message);
        });
    
    logger.info(
      "["
        + m_order.getManufactureOrderTypeId()
        + "-"
        + m_order.getManufactureOrderId()
        + "] 已手動審核結案");
  }
  
  public void resumePMCLine() {
    GSPMCManufactureOrder m_order = getManufactureOrder();
    if (m_order == null) {
      return;
    }
    
    m_order.setStatusCode("N");
    PMCService.updateManufactureOrder(contextUser, m_order);
    
    data.setReviewStatus(GSPMCManufactureOrderActivityReviewList.TYPE_REVIEWED);
    data.setDefaultLotNumber(vdReportingDefaultLotNumber.getValue());
    PMCService.updateManufactureOrderActivity(contextUser, data);
    ((GSPMCManufactureOrderActivityReviewList) handler).updateRow(data);
    
    getUI()
      .ifPresent(
        ui -> {
          GSBroadcastMessage message = new GSBroadcastMessage();
          message.setContent(m_order);
          GSPMCBroadcaster.broadcast(ui, message);
        });
    
    logger.info(
      "["
        + m_order.getManufactureOrderTypeId()
        + "-"
        + m_order.getManufactureOrderId()
        + "] 執行[取消結案]動作，審核單狀態恢復為[已審核]");
  }
  
  public void reviewObsolete() {
    GSPMCManufactureOrder m_order = getManufactureOrder();
    if (m_order == null) {
      return;
    }
    String productLine = m_order.getProductLine().trim();
    GSPMCProductLineControl.resetProductLine(productLine);
    
    data.setReviewStatus(GSPMCManufactureOrderActivityReviewList.TYPE_OBSOLETE);
    data.setDefaultLotNumber(vdReportingDefaultLotNumber.getValue());
    PMCService.updateManufactureOrderActivity(contextUser, data);
    ((GSPMCManufactureOrderActivityReviewList) handler).updateRow(data);
    
    logger.info(
      "["
        + m_order.getManufactureOrderTypeId()
        + "-"
        + m_order.getManufactureOrderId()
        + "] 審核單已作廢");
  }
  
  private GSPMCManufactureOrder getManufactureOrder() {
    String typeId = data.getManufactureOrderTypeId();
    String orderId = data.getManufactureOrderId();
    
    GSPMCManufactureOrder m_order =
      PMCService.getManufactureOrder(data.getCompanyId(), typeId, orderId);
    
    if (m_order == null) {
      Notification.show("[" + typeId + "-" + orderId + "] 製令不存在！", 2000, Position.MIDDLE);
      logger.error("[" + typeId + "-" + orderId + "] 製令不存在！");
    }
    
    return m_order;
  }
  
  @Override
  public String getDetailTitle() {
    return getHeader();
  }
  
  @Override
  public void setChangeHandler(GSFormChangeHandler handler) {
    this.handler = handler;
  }
  
  @Override
  public void onSave() {
  }
  
  @Override
  public void onCancel() {
  }
  
  @Override
  public void onClose() {
    ((GSPMCManufactureOrderActivityReviewList) handler).updateRow(data);
  }
  
  @Override
  public void onDelete() {
  }
  
  public boolean validate() {
    return binder.validate().isOk();
  }
  
  @Override
  public boolean isValueChanged() {
    return binder.hasChanges();
  }
  
  @Override
  public void populateDetail() {
    data = (GSPMCManufactureOrderActivity) getDetail();
    String typeId = data.getManufactureOrderTypeId().trim();
    String orderId = data.getManufactureOrderId().trim();
    String m_orderId = typeId + "-" + orderId;
    
    m_order = getManufactureOrder();
    if (m_order == null) {
      logger.error("Manufacture Order[" + m_orderId + "] does not exists.");
      Notification.show("製令[" + m_orderId + "]不存在.", 3000, Position.MIDDLE);
      return;
    }
    
    GSOrganizationService orgService = GSContext.getApplication().getOrganizationService();
    GSMember reportingMember = orgService.getMember(data.getCompanyId(), data.getMemberId());
    if (reportingMember == null) {
      logger.error("報工人員[" + data.getMemberId().trim() + "] 不存在.");
      Notification.show(
        "製令[" + m_orderId + "]\n報工人員[" + data.getMemberId().trim() + "] 不存在.",
        3000,
        Position.MIDDLE);
      return;
    }
    
    vdReportingMemberDisplay.setValue(
      reportingMember.getId().trim() + "(" + reportingMember.getName().trim() + ")");
    
    GSDepartment dept = orgService.getDepartment(data.getCompanyId(), data.getDeptId());
    vdReportingDeptDisplay.setValue(dept.getId().trim() + "(" + dept.getName().trim() + ")");
    
    String strStartTime = DLDateUtils.format(data.getStartTime(), Resolution.Full);
    vdReportingStartTime.setValue(strStartTime);
    vdReportingDatePickerStartTime.setValue(LocalDate.parse(strStartTime.split(" ")[0]));
    vdReportingTimePickerStartTime.setValue(LocalTime.parse(strStartTime.split(" ")[1]));
    if (isManufactureOrderCompleted()) {
      vdReportingDatePickerStartTime.setReadOnly(true);
      vdReportingTimePickerStartTime.setReadOnly(true);
    }
    
    String strFinishTime = DLDateUtils.format(data.getFinishTime(), Resolution.Full);
    vdReportingFinishTime.setValue(strFinishTime);
    vdReportingDatePickerFinishTime.setValue(LocalDate.parse(strFinishTime.split(" ")[0]));
    vdReportingTimePickerFinishTime.setValue(LocalTime.parse(strFinishTime.split(" ")[1]));
    if (isManufactureOrderCompleted()) {
      vdReportingDatePickerFinishTime.setReadOnly(true);
      vdReportingTimePickerFinishTime.setReadOnly(true);
    }

    /*vdReportingVoidTime.setValue("" + data.getVoidTime());
    vdReportingAmount.setValue("" + data.getWorkingAmount());
    vdReportingPackAmount.setValue("" + data.getPackAmount());*/
    
    String statusDisplay;
    String reviewStatus = data.getReviewStatus();
    switch (reviewStatus) {
      case GSPMCManufactureOrderActivityReviewList.TYPE_REVIEWED:
        statusDisplay = " [已審核]";
        break;
      case GSPMCManufactureOrderActivityReviewList.TYPE_OBSOLETE:
        statusDisplay = " [作廢]";
        break;
      case GSPMCManufactureOrderActivityReviewList.TYPE_CLOSED:
        statusDisplay = " [結案(人工)]";
        break;
      case GSPMCManufactureOrderActivityReviewList.TYPE_CLOSED_BY_SYSTEM:
        statusDisplay = " [結案(系統)]";
        break;
      default:
        statusDisplay = " [未審核]";
    }
    
    setHeader("生產入庫審核作業: " + m_orderId + statusDisplay);
    
    if (isManufactureOrderCompleted()) {
      vdReportingAmount.setReadOnly(true);
      vdReportingPackAmount.setReadOnly(true);
    }
    
    binder
      .forField(vdReportingAmount)
      .withConverter(Double::parseDouble, String::valueOf)
      .bind(
        GSPMCManufactureOrderActivity::getWorkingAmount,
        GSPMCManufactureOrderActivity::setWorkingAmount);
    
    binder
      .forField(vdReportingVoidTime)
      .withConverter(Double::parseDouble, String::valueOf)
      .bind(
        GSPMCManufactureOrderActivity::getVoidTime, GSPMCManufactureOrderActivity::setVoidTime);
    
    binder
      .forField(vdReportingPackAmount)
      .withConverter(Double::parseDouble, String::valueOf)
      .bind(
        GSPMCManufactureOrderActivity::getPackAmount,
        GSPMCManufactureOrderActivity::setPackAmount);
    
    // 2021/06/10 提供預設批號  ex:  210610,  審核者可修改
    String defaultLotNumber = data.getDefaultLotNumber();
    if (defaultLotNumber == null || defaultLotNumber.trim().isEmpty()) {
      Date now = Calendar.getInstance().getTime();
      defaultLotNumber = String.format("%ty%tm%td", now, now, now);
      
      data.setDefaultLotNumber(defaultLotNumber);
    }
    
    binder.bindInstanceFields(this);
    binder.readBean(data);
    
    String materialUnit = m_order.getMaterialUnit();
    String packingUnit = m_order.getPackingUnit();
    
    vdMOPartNumber.setValue(m_order.getPartNumber().trim());
    
    vdMOProductLine.setValue(m_order.getProductLine());
    vdMOProductSpec.setValue(m_order.getProductSpec());
    
    String strEmstimateStartDate =
      dateConverter.convertToPresentation(m_order.getEstimateStartDate(), null);
    vdMOEstimateStartDate.setValue(strEmstimateStartDate);
    vdMOEstimateStartDate.setReadOnly(true);
    
    String strActualStartDate =
      dateConverter.convertToPresentation(m_order.getActualStartDate(), null);
    vdMOActualStartDate.setValue(strActualStartDate);
    vdMOActualStartDate.setReadOnly(true);
    
    vdMOAmount.setValue("" + m_order.getEstimateAmount() + "(" + materialUnit + ")");
    
    if (m_order.getEstimatePackAmount() == 0) {
      vdReportingPackAmount.setReadOnly(true);
    } else {
      NumberFormat nf = NumberFormat.getInstance();
      nf.setMaximumFractionDigits(2);
      nf.setRoundingMode(RoundingMode.FLOOR);
      
      unitValue = m_order.getEstimateAmount() / m_order.getEstimatePackAmount();
      vdMOPackAmount.setValue("" + m_order.getEstimatePackAmount() + "(" + packingUnit + ")");
    }
  }
  
  public void __populateDetail() {
    data = (GSPMCManufactureOrderActivity) getDetail();
    
    String typeId = data.getManufactureOrderTypeId();
    String orderId = data.getManufactureOrderId();
    String m_orderId = typeId + "-" + orderId;
    
    m_order = getManufactureOrder();
    if (m_order == null) {
      logger.error("Manufacture Order[" + m_orderId + "] does not exists.");
      return;
    }
    
    String statusDisplay;
    String reviewStatus = data.getReviewStatus();
    switch (reviewStatus) {
      case GSPMCManufactureOrderActivityReviewList.TYPE_REVIEWED:
        statusDisplay = " [已審核]";
        break;
      case GSPMCManufactureOrderActivityReviewList.TYPE_OBSOLETE:
        statusDisplay = " [作廢]";
        break;
      case GSPMCManufactureOrderActivityReviewList.TYPE_CLOSED:
        statusDisplay = " [結案(人工)]";
        break;
      case GSPMCManufactureOrderActivityReviewList.TYPE_CLOSED_BY_SYSTEM:
        statusDisplay = " [結案(系統)]";
        break;
      default:
        statusDisplay = " [未審核]";
    }
    
    setHeader("生產入庫審核作業: " + m_orderId + statusDisplay);
    
    vdReportingDeptDisplay.setValue(data.getDeptId());
    
    GSOrganizationService orgService = GSContext.getApplication().getOrganizationService();
    GSMember reportingMember = orgService.getMember(data.getCompanyId(), data.getMemberId());
    if (reportingMember == null) {
      logger.error("報工人員[" + data.getMemberId() + "] 不存在.");
      return;
    }
    vdReportingMemberId.setValue(
      reportingMember.getId() + "(" + reportingMember.getName().trim() + ")");
    
    String strStartTime = DLDateUtils.format(data.getStartTime(), Resolution.Full);
    vdReportingStartTime.setValue(strStartTime);
    vdReportingDatePickerStartTime.setValue(LocalDate.parse(strStartTime.split(" ")[0]));
    vdReportingTimePickerStartTime.setValue(LocalTime.parse(strStartTime.split(" ")[1]));
    if (isManufactureOrderCompleted()) {
      vdReportingDatePickerStartTime.setReadOnly(true);
      vdReportingTimePickerStartTime.setReadOnly(true);
    }
    
    String strFinishTime = DLDateUtils.format(data.getFinishTime(), Resolution.Full);
    vdReportingFinishTime.setValue(strFinishTime);
    vdReportingDatePickerFinishTime.setValue(LocalDate.parse(strFinishTime.split(" ")[0]));
    vdReportingTimePickerFinishTime.setValue(LocalTime.parse(strFinishTime.split(" ")[1]));
    if (isManufactureOrderCompleted()) {
      vdReportingDatePickerFinishTime.setReadOnly(true);
      vdReportingTimePickerFinishTime.setReadOnly(true);
    }
    
    vdReportingVoidTime.setValue("" + data.getVoidTime());
    vdReportingAmount.setValue("" + data.getWorkingAmount());
    vdReportingPackAmount.setValue("" + data.getPackAmount());
    
    if (isManufactureOrderCompleted()) {
      vdReportingAmount.setReadOnly(true);
      vdReportingPackAmount.setReadOnly(true);
    }
    
    String materialUnit = m_order.getMaterialUnit();
    String packingUnit = m_order.getPackingUnit();
    
    vdMOPartNumber.setValue(m_order.getPartNumber().trim());
    
    vdMOProductLine.setValue(m_order.getProductLine());
    vdMOProductSpec.setValue(m_order.getProductSpec());
    
    String strEmstimateStartDate =
      dateConverter.convertToPresentation(m_order.getEstimateStartDate(), null);
    vdMOEstimateStartDate.setValue(strEmstimateStartDate);
    vdMOEstimateStartDate.setReadOnly(true);
    
    String strActualStartDate =
      dateConverter.convertToPresentation(m_order.getActualStartDate(), null);
    vdMOActualStartDate.setValue(strActualStartDate);
    vdMOActualStartDate.setReadOnly(true);
    
    // 2021/06/10 提供預設批號  ex:  210610,  審核者可修改
    String defaultLotNumber = data.getDefaultLotNumber();
    if (defaultLotNumber == null || defaultLotNumber.trim().isEmpty()) {
      Date now = Calendar.getInstance().getTime();
      defaultLotNumber = String.format("%ty%tm%td", now, now, now);
    }
    vdReportingDefaultLotNumber.setValue(defaultLotNumber);
    
    vdMOAmount.setValue("" + m_order.getEstimateAmount() + "(" + materialUnit + ")");
    
    if (m_order.getEstimatePackAmount() == 0) {
      vdReportingPackAmount.setReadOnly(true);
    } else {
      NumberFormat nf = NumberFormat.getInstance();
      nf.setMaximumFractionDigits(2);
      nf.setRoundingMode(RoundingMode.FLOOR);
      
      unitValue = m_order.getEstimateAmount() / m_order.getEstimatePackAmount();
      vdMOPackAmount.setValue("" + m_order.getEstimatePackAmount() + "(" + packingUnit + ")");
    }
    // valueChanged = false;
  }
  
  @Override
  public Icon getDetailHeaderIcon() {
    return VaadinIcon.PACKAGE.create();
  }
}
