package com.gst.mes.pmc;

import com.dcsplab.common.DLDateUtils;
import com.dcsplab.vdui.components.VDTextField;
import com.dcsplab.vdui.layout.VDFormLayout;
import com.gst.context.GSContext;
import com.gst.domain.GSDepartment;
import com.gst.domain.GSMember;
import com.gst.domain.GSPMCManufactureOrder;
import com.gst.domain.GSPMCManufactureOrderActivity;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.service.GSOrganizationService;
import com.gst.service.GSPMCService;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.data.binder.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 報工畫面
 */
public class GSPMCManufactureOrderActivityReportDetail extends GSDetailFormBase {
  
  private static final long serialVersionUID = 7649424498839570079L;
  private static final Logger logger =
    LoggerFactory.getLogger(GSPMCManufactureOrderActivityReportDetail.class);
  
  private final Binder<GSPMCManufactureOrderActivity> binder =
    new Binder<>(GSPMCManufactureOrderActivity.class);
  
  private final GSMember contextUser;
  
  private double unitValue = 0;
  
  private GSPMCManufactureOrder m_order;
  
  private GSPMCManufactureOrderActivity m_activity;
  
  // 報工部門
  @PropertyId("deptId")
  private VDTextField vdReportingDeptId;
  
  private VDTextField vdReportingDeptDisplay;
  
  // 報工人員
  @PropertyId("memberId")
  private VDTextField vdMemberId;
  
  private VDTextField vdMemberDisplay;
  
  // 開工時間
  @PropertyId("startTime")
  private VDTextField vdStartTime;
  
  // 完工時間
  @PropertyId("finishTime")
  private VDTextField vdFinishTime;
  
  // 報工數量
  @PropertyId("workingAmount")
  private VDTextField vdReportAmount;
  
  // 生產人數
  @PropertyId("workingHeadCount")
  private VDTextField vdWorkingHeadcount;
  
  // 報工包裝數量
  @PropertyId("packAmount")
  private VDTextField vdPackAmount;
  
  // 製令-預計生產數量
  private VDTextField vdEstimateAmount;
  
  // 製令-包裝單位數量
  private VDTextField vdEstimatePackAmount;
  
  // 製令-數量單位
  private VDTextField vdMaterialUnit;
  
  // 包裝單位
  private VDTextField vdPackingUnit;
  
  private GSFormChangeHandler handler;
  
  private boolean valueChanged;
  
  private final GSPMCService PMCService;
  
  public GSPMCManufactureOrderActivityReportDetail() {
    contextUser = GSContext.getCurrentMember();
    PMCService = GSContext.getApplication().getPCMService();
    initFields();
  }
  
  private void initFields() {
    vdReportingDeptId = new VDTextField();
    vdReportingDeptId.setReadOnly(true);
    
    vdReportingDeptDisplay = new VDTextField(); // "報工部門"
    vdReportingDeptDisplay.setWidthFull();
    vdReportingDeptDisplay.setReadOnly(true);
    
    vdMemberId = new VDTextField();
    vdMemberId.setVisible(false);
    
    vdMemberDisplay = new VDTextField(); // "報工人員"
    vdMemberDisplay.setWidthFull();
    vdMemberDisplay.setReadOnly(true);
    
    vdStartTime = new VDTextField(); // "開工時間"
    vdStartTime.setWidthFull();
    vdStartTime.setReadOnly(true);
    
    vdFinishTime = new VDTextField(); // "報工時間"
    vdFinishTime.setWidthFull();
    vdFinishTime.setReadOnly(true);
    
    vdEstimateAmount = new VDTextField(); // "製令數量"
    vdEstimateAmount.setWidthFull();
    vdEstimateAmount.setReadOnly(true);
    
    vdMaterialUnit = new VDTextField(); // "單位"
    vdMaterialUnit.setWidthFull();
    vdMaterialUnit.setReadOnly(true);
    
    vdReportAmount = new VDTextField(); // "報工數量" vdReportAmount
    vdReportAmount.setWidthFull();
    vdReportAmount.addValueChangeListener(
      e -> {
        if (e.isFromClient()) {
          String val = e.getOldValue();
          String newVal = e.getValue();
          try {
            double nv = Double.parseDouble(newVal);
            
            if (unitValue > 0) {
              String fmt = String.format("%.4f", (nv / unitValue));
              vdPackAmount.setValue(fmt);
            }
          } catch (Exception ex) {
            if (val == null) {
              val = "";
            }
            e.getSource().setValue(val);
          }
        }
      });
    
    vdWorkingHeadcount = new VDTextField(); // "生產人數"
    vdWorkingHeadcount.setWidthFull();
    vdWorkingHeadcount.addValueChangeListener(
      e -> {
        if (e.isFromClient()) {
          String val = e.getOldValue();
          String newVal = e.getValue();
          try {
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(2);
            nf.setRoundingMode(RoundingMode.FLOOR);
            
            double dv = Double.parseDouble(newVal);
            newVal = nf.format(dv);
            e.getSource().setValue(newVal);
          } catch (Exception ex) {
            if (val == null) {
              val = "";
            }
            e.getSource().setValue(val);
          }
        }
      });
    
    FormLayout formLayout_1 = new VDFormLayout();
    formLayout_1.getStyle().set("margin-top", "3px");
    formLayout_1.addFormItem(vdReportingDeptDisplay, "報工部門");
    formLayout_1.addFormItem(vdMemberDisplay, "報工人員");
    formLayout_1.addFormItem(vdStartTime, "開工時間");
    formLayout_1.addFormItem(vdFinishTime, "報工時間");
    formLayout_1.addFormItem(vdEstimateAmount, "製令數量");
    formLayout_1.addFormItem(vdMaterialUnit, "單位");
    formLayout_1.addFormItem(vdReportAmount, "報工數量");
    formLayout_1.addFormItem(vdWorkingHeadcount, "生產人數");
    formLayout_1.setResponsiveSteps(
      new FormLayout.ResponsiveStep("0", 1),
      new FormLayout.ResponsiveStep("400px", 2, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));
    
    vdEstimatePackAmount = new VDTextField(); // "包裝數量"
    vdEstimatePackAmount.setWidthFull();
    vdEstimatePackAmount.setReadOnly(true);
    
    vdPackingUnit = new VDTextField(); // "包裝單位"
    vdPackingUnit.setWidthFull();
    vdPackingUnit.setReadOnly(true);
    
    // 報工包裝數量
    vdPackAmount = new VDTextField(); // "報工包裝數量"
    vdPackAmount.setWidthFull();
    vdPackAmount.addValueChangeListener(
      e -> {
        if (e.isFromClient()) {
          String val = e.getOldValue();
          String newVal = e.getValue();
          try {
            double nv = Double.parseDouble(newVal);
            if (unitValue > 0) {
              vdReportAmount.setValue("" + (nv * unitValue));
            }
          } catch (Exception ex) {
            if (val == null) {
              val = "";
            }
            e.getSource().setValue(val);
          }
        }
      });
    
    FormLayout formLayout_2 = new VDFormLayout();
    formLayout_2.getStyle().set("margin-top", "3px");
    formLayout_2.addFormItem(vdEstimatePackAmount, "包裝數量");
    formLayout_2.addFormItem(vdPackingUnit, "包裝單位");
    formLayout_2.addFormItem(vdPackAmount, "報工包裝數量");
    formLayout_2.setResponsiveSteps(
      new FormLayout.ResponsiveStep("0", 1),
      new FormLayout.ResponsiveStep("400px", 3, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));
    
    add(formLayout_1, formLayout_2);
  }
  
  @Override
  public void populateDetail() {
    m_order = (GSPMCManufactureOrder) getDetail();
    setHeader(
      "生產入庫報工作業: " + m_order.getManufactureOrderTypeId() + "-" + m_order.getManufactureOrderId());
    
    m_activity = PMCService.createManufactureOrderActivity(contextUser, m_order);
    m_activity.setWorkingAmount(0d);
    
    Date startTime = m_order.getStartTime();
    if (startTime != null) {
      m_activity.setStartTime(startTime);
    }
    
    Date finishTime = Calendar.getInstance().getTime();
    m_activity.setFinishTime(finishTime);
    
    binder
      .forField(vdStartTime)
      .withConverter(new DLDateUtils.DateConverter())
      .bind(
        GSPMCManufactureOrderActivity::getStartTime,
        GSPMCManufactureOrderActivity::setStartTime);
    
    binder
      .forField(vdFinishTime)
      .withConverter(new DLDateUtils.DateConverter())
      .bind(
        GSPMCManufactureOrderActivity::getFinishTime,
        GSPMCManufactureOrderActivity::setFinishTime);
    
    binder
      .forField(vdReportAmount)
      .withValidator(val -> val.trim().length() > 0, "必須輸入報工數量")
      .withValidator(
        val -> {
          try {
            double dVal = Double.parseDouble(val);
            return dVal > 0;
          } catch (Exception e) {
            return false;
          }
        },
        "報工數量必須大於0")
      .withConverter(Double::valueOf, String::valueOf)
      .bind(
        GSPMCManufactureOrderActivity::getWorkingAmount,
        GSPMCManufactureOrderActivity::setWorkingAmount);
    
    binder
      .forField(vdWorkingHeadcount)
      .withValidator(val -> val.trim().length() > 0, "必須輸入生產人數")
      .withValidator(
        val -> {
          try {
            double dVal = Double.parseDouble(val);
            return dVal > 0;
          } catch (Exception e) {
            return false;
          }
        },
        "生產人數必須大於0")
      .withConverter(Double::valueOf, String::valueOf)
      .bind(
        GSPMCManufactureOrderActivity::getWorkingHeadCount,
        GSPMCManufactureOrderActivity::setWorkingHeadCount);
    
    binder
      .forField(vdPackAmount)
      /*.withValidator(val -> val.trim().length() > 0, "必須輸入包裝數量")
      .withValidator(
          val -> {
            try {
              double dVal = Double.parseDouble(val);
              return dVal > 0;
            } catch (Exception e) {
              return false;
            }
          },
          "包裝數量必須大於0")*/
      .withConverter(Double::valueOf, String::valueOf)
      .bind(
        GSPMCManufactureOrderActivity::getPackAmount,
        GSPMCManufactureOrderActivity::setPackAmount);
    
    binder.bindInstanceFields(this);
    binder.readBean(m_activity);
    
    vdMaterialUnit.setValue(m_order.getMaterialUnit());
    vdEstimateAmount.setValue("" + m_order.getEstimateAmount());
    
    int ta045 = (int) m_order.getEstimatePackAmount().doubleValue();
    vdEstimatePackAmount.setValue("" + ta045);
    
    String unit = "0";
    if (ta045 == 0) {
      vdPackAmount.setReadOnly(true);
    } else {
      NumberFormat nf = NumberFormat.getInstance();
      nf.setMaximumFractionDigits(2);
      nf.setRoundingMode(RoundingMode.FLOOR);
      
      unitValue = m_order.getEstimateAmount() / m_order.getEstimatePackAmount();
      unit = nf.format(unitValue);
    }
    
    String materialUnit = m_order.getMaterialUnit();
    String packingUnit = m_order.getPackingUnit();
    vdPackingUnit.setValue(unit + " " + materialUnit + "/" + packingUnit);
    
    vdMemberDisplay.setValue(contextUser.getId().trim() + "(" + contextUser.getName().trim() + ")");
    
    GSOrganizationService orgService = GSContext.getApplication().getOrganizationService();
    String deptId = contextUser.getDeptId().trim();
    GSDepartment dept = orgService.getDepartment(contextUser.getCompanyId(), deptId);
    vdReportingDeptDisplay.setValue(deptId + "(" + dept.getName() + ")");
    
    vdReportAmount.focus();
  }
  
  @Override
  public void onSave() {
    String m_orderId = m_order.getManufactureOrderTypeId() + "-" + m_order.getManufactureOrderId();
    logger.info("[" + m_orderId + "] 準備報工程序...");
    
    try {
      binder.writeBean(m_activity);
      
      Date startTime = m_activity.getStartTime();
      Date finishTime = m_activity.getFinishTime();
      long workingTimeInMinute = (finishTime.getTime() - startTime.getTime()) / 1000 / 60;
      m_activity.setVoidTime(workingTimeInMinute * 1.0);
      
      m_activity.setReviewStatus("N");
      m_activity.setTA503("1");
      
      PMCService.updateManufactureOrderActivity(contextUser, m_activity);
      logger.info("[" + m_orderId + "] 已建立報工單據...");
      
      m_order.setWorkingSequence(GSPMCProductLineControl.PMC_REPORTED_SEQUENCE);
      m_order.setLastStartTime(startTime);
      m_order.setStartTime(null);
      PMCService.updateManufactureOrder(contextUser, m_order);
      
      GSOrganizationService orgService = GSContext.getApplication().getOrganizationService();
      GSDepartment dept =
        orgService.getDepartment(m_order.getCompanyId(), m_order.getProductLine());
      dept.setLastFinishTime(finishTime);
      try {
        orgService.updateDepartment(contextUser, dept);
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      GSPMCProductLineControl.releaseLock(m_order.getProductLine());
      GSPMCProductLineControl.resetSequence(m_order.getProductLine());
      logger.info("[" + m_orderId + "] 已解鎖製令...");
      
      GSPMCManufactureOrderActivityOverview PMCTable =
        (GSPMCManufactureOrderActivityOverview) handler;
      PMCTable.orderReported(m_order);
      logger.info("[" + m_orderId + "] 已更新製令狀態...");
      logger.info("[" + m_orderId + "] 報工程序成功完成");
      
    } catch (ValidationException e) {
      throw new RuntimeException(e);
    }
  }

  /*public void __onSave() {
    String m_orderId = m_order.getManufactureOrderTypeId() + "-" + m_order.getManufactureOrderId();
    logger.info("[" + m_orderId + "] 準備報工程序...");

    GSPMCManufactureOrderActivity report = new GSPMCManufactureOrderActivity(PMCService);
    report.setCompanyId(m_order.getCompanyId());
    report.setCreator(contextUser.getId());
    report.setModifier(contextUser.getId());
    report.setDeptId(contextUser.getMainDepartment().getId());
    report.setMemberId(contextUser.getId());
    report.setManufactureOrderTypeId(m_order.getManufactureOrderTypeId());
    report.setManufactureOrderId(m_order.getManufactureOrderId());

    String strStartTime = vdStartTime.getValue().trim();
    Date startTime;
    try {
      startTime = DLDateUtils.parse(strStartTime, Resolution.Full);
    } catch (Exception e) {
      logger.error("[" + m_orderId + "] 開工日期錯誤: " + e.getMessage(), e);
      startTime = Calendar.getInstance().getTime();
    }
    report.setStartTime(startTime);

    String strFinishTime = vdFinishTime.getValue().trim();
    Date finishTime;
    try {
      finishTime = DLDateUtils.parse(strFinishTime, Resolution.Full);
    } catch (Exception e) {
      logger.error("[" + m_orderId + "] 報工日期錯誤: " + e.getMessage(), e);
      finishTime = Calendar.getInstance().getTime();
    }
    report.setFinishTime(finishTime);

    long workingTimeInMinute = (finishTime.getTime() - startTime.getTime()) / 1000 / 60;
    report.setVoidTime(workingTimeInMinute * 1.0);

    report.setWorkingAmount(Double.parseDouble(vdReportAmount.getValue()));
    report.setReviewStatus("N");
    report.setWorkingHeadCount(Double.parseDouble(vdWorkingHeadcount.getValue()));

    String strPackAmount = vdPackAmount.getValue().trim();
    double packAmount = strPackAmount.isEmpty() ? 0d : Double.parseDouble(strPackAmount);
    report.setPackAmount(packAmount);

    report.setTA503("1");
    PMCService.updateManufactureOrderActivity(contextUser, report);
    logger.info("[" + m_orderId + "] 已建立報工單據...");

    m_order.setWorkingSequence(GSPMCProductLineControl.PMC_REPORTED_SEQUENCE);
    m_order.setLastStartTime(startTime);
    m_order.setStartTime(null);
    PMCService.updateManufactureOrder(contextUser, m_order);

    GSOrganizationService orgService = GSContext.getApplication().getOrganizationService();
    GSDepartment dept = orgService.getDepartment(m_order.getCompanyId(), m_order.getProductLine());
    dept.setLastFinishTime(finishTime);
    try {
      orgService.updateDepartment(contextUser, dept);
    } catch (Exception e) {
      e.printStackTrace();
    }

    GSPMCProductLineControl.releaseLock(m_order.getProductLine());
    GSPMCProductLineControl.resetSequence(m_order.getProductLine());
    logger.info("[" + m_orderId + "] 已解鎖製令...");

    GSPMCManufactureOrderActivityOverview PMCTable =
        (GSPMCManufactureOrderActivityOverview) handler;
    PMCTable.orderReported(m_order);
    logger.info("[" + m_orderId + "] 已更新製令狀態...");
    logger.info("[" + m_orderId + "] 報工程序成功完成");
  }*/
  
  @Override
  public String getDetailTitle() {
    return getHeader();
  }
  
  @Override
  public void setChangeHandler(GSFormChangeHandler handler) {
    this.handler = handler;
  }
  
  @Override
  public void onCancel() {
  }
  
  @Override
  public void onClose() {
  }
  
  @Override
  public void onDelete() {
  }
  
  @Override
  public boolean validate() {
    return binder.validate().isOk();
  }
  
  @Override
  public boolean isValueChanged() {
    return binder.hasChanges();
  }
  
  @Override
  public Icon getDetailHeaderIcon() {
    return VaadinIcon.PACKAGE.create();
  }
}
