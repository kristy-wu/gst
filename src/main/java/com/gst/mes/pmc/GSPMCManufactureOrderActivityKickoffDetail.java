package com.gst.mes.pmc;

import com.dcsplab.common.DLDateUtils;
import com.dcsplab.vdui.components.VDComboBox;
import com.dcsplab.vdui.components.VDTextField;
import com.dcsplab.vdui.layout.VDFormLayout;
import com.gst.context.GSContext;
import com.gst.domain.*;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.service.GSOrganizationService;
import com.gst.service.GSPMCService;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * 開工 1. 開啟開工畫面: 顯示前次報工時間(CMSBA.BA005), 顯示開工時間(目前時間), 無效工時類別(CMSFA.FA001 清單) 2. 畫面內容確定後 -
 * 檢查開工線別是否存在已開工製令，若存在則終止開工程序並 alert 3. 若可開工則 - 鎖定此線別 - 將目前時間寫入 MOCTA.TA503 (開工時間) - 將目前日期寫入
 * MOCTA.TA012 (實際開工時間)
 *
 * <p>建立MESTA報工資料 - 寫入開工線別(TA001)，人員(TA002)，製令(TA003,TA004)，開工時間(TA005),完工時間(TA006),花費工時(TA007),
 * ...... - MESTA.TA005 寫入 CMSBA.BA905(now if null) - MESTA.TA006 寫入 now - MESTA.TA007 回寫 開工時間 -
 * 前次報工時間(now-CMSBA.BA905) - MESTA.TA503 回寫 9 - MESTA.TA504 回寫無效工時類別代號(CMSFA.FA001)
 */
public class GSPMCManufactureOrderActivityKickoffDetail extends GSDetailFormBase {
  
  private static final long serialVersionUID = 7649424498839570079L;
  private static final Logger logger =
    LoggerFactory.getLogger(GSPMCManufactureOrderActivityKickoffDetail.class);
  
  private final Binder<GSPMCManufactureOrderActivity> binder =
    new Binder<>(GSPMCManufactureOrderActivity.class);
  
  private final GSMember contextUser;
  
  private GSPMCManufactureOrder m_order;
  
  private GSPMCManufactureOrderActivity m_activity;
  
  // 報工部門
  @PropertyId("deptId")
  private VDTextField vdReportingDeptId;
  
  private VDTextField vdReportintDeptDisplay;
  
  // 報工人員
  @PropertyId("memberId")
  private VDTextField vdMemberId;
  
  private VDTextField vdReportingMemberDisplay;
  
  // 開工時間
  @PropertyId("startTime")
  private VDTextField vdStartTime;
  
  // 無效工時類別 TA504
  @PropertyId("workType")
  private VDComboBox<GSWorkType> vdWorkType;
  
  // 前次報工時間
  private VDTextField vdLastReportingTime;
  
  private GSFormChangeHandler handler;
  
  private final GSPMCService PMCService;
  
  private final GSOrganizationService orgService;
  
  public GSPMCManufactureOrderActivityKickoffDetail() {
    contextUser = GSContext.getCurrentMember();
    PMCService = GSContext.getApplication().getPCMService();
    orgService = GSContext.getApplication().getOrganizationService();
    initFields();
  }
  
  private void initFields() {
    
    vdReportintDeptDisplay = new VDTextField();
    vdReportintDeptDisplay.setWidthFull();
    vdReportintDeptDisplay.setReadOnly(true);
    
    vdMemberId = new VDTextField();
    vdMemberId.setVisible(false);
    
    vdReportingMemberDisplay = new VDTextField();
    vdReportingMemberDisplay.setWidthFull();
    vdReportingMemberDisplay.setReadOnly(true);
    
    vdLastReportingTime = new VDTextField();
    vdLastReportingTime.setWidthFull();
    vdLastReportingTime.setReadOnly(true);
    
    vdStartTime = new VDTextField();
    vdStartTime.setWidthFull();
    vdStartTime.setReadOnly(true);
    
    vdWorkType = new VDComboBox<>();
    vdWorkType.setWidth("350px");
    vdWorkType.setRequired(true);
    vdWorkType.setRequiredIndicatorVisible(true);
    vdWorkType.setRenderer(
      TemplateRenderer.<GSWorkType>of("<div>[[item.cname]](<small>[[item.ename]]</small>)</div>")
        .withProperty("cname", GSWorkType::getName)
        .withProperty("ename", GSWorkType::getTypeNameEng));
    vdWorkType.setItemLabelGenerator(GSWorkType::getNameDisplay);
    vdWorkType.setClearButtonVisible(true);
    
    FormLayout formLayout = new VDFormLayout();
    formLayout.getStyle().set("margin-top", "3px");
    formLayout.addFormItem(vdReportintDeptDisplay, "報工部門");
    formLayout.addFormItem(vdReportingMemberDisplay, "報工人員");
    formLayout.addFormItem(vdLastReportingTime, "前次報工時間");
    formLayout.addFormItem(vdStartTime, "開工時間");
    FormLayout.FormItem typeItem = formLayout.addFormItem(vdWorkType, "無效工時類別");
    typeItem.getElement().setAttribute("colspan", "2");
    formLayout.setResponsiveSteps(
      new FormLayout.ResponsiveStep("0", 1),
      new FormLayout.ResponsiveStep("400px", 2, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));
    
    add(formLayout);
  }
  
  @Override
  public String getDetailTitle() {
    return getHeader();
  }
  
  @Override
  public void setChangeHandler(GSFormChangeHandler h) {
    handler = h;
  }
  
  @Override
  public void populateDetail() {
    m_order = (GSPMCManufactureOrder) getDetail();
    setHeader(
      "生產開工作業: " + m_order.getManufactureOrderTypeId() + "-" + m_order.getManufactureOrderId());
    
    Date now = Calendar.getInstance().getTime();
    
    m_activity = PMCService.createManufactureOrderActivity(contextUser, m_order);
    m_activity.setStartTime(now);
    
    String memDisplay = contextUser.getId().trim() + "(" + contextUser.getName().trim() + ")";
    vdReportingMemberDisplay.setValue(memDisplay);
    
    GSDepartment reportingDept =
      orgService.getDepartment(contextUser.getCompanyId(), contextUser.getDeptId().trim());
    String deptDisplay = reportingDept.getId().trim() + "(" + reportingDept.getName().trim() + ")";
    vdReportintDeptDisplay.setValue(deptDisplay);
    
    // 前次報工時間
    String deptId = m_order.getProductLine().trim();
    GSDepartment lastFinishDept = orgService.getDepartment(m_order.getCompanyId(), deptId);
    Date lastFinishTime = lastFinishDept.getLastFinishTime();
    if (lastFinishTime == null) {
      lastFinishTime = now;
    }
    vdLastReportingTime.setValue(DLDateUtils.format(lastFinishTime, DLDateUtils.Resolution.Full));
    
    vdWorkType.setItems(PMCService.getWorkTypeList(m_order.getCompanyId()));
    
    binder
      .forField(vdStartTime)
      .withConverter(new DLDateUtils.DateConverter())
      .bind(
        GSPMCManufactureOrderActivity::getStartTime,
        GSPMCManufactureOrderActivity::setStartTime);
    
    binder
      .forField(vdWorkType)
      .withValidator(Objects::nonNull, "必須選擇無效工時類別")
      .bind(
        GSPMCManufactureOrderActivity::getWorkType, GSPMCManufactureOrderActivity::setWorkType);
    binder.bindInstanceFields(this);
    binder.readBean(m_activity);
  }
  
  @Override
  public void onSave() {
    String m_orderId =
      m_order.getManufactureOrderTypeId().trim() + "-" + m_order.getManufactureOrderId().trim();
    PMCService.kickoffManufactureOrder(contextUser, m_order);
    
    Date now = Calendar.getInstance().getTime();
    // 前次報工時間
    GSOrganizationService orgService = GSContext.getApplication().getOrganizationService();
    GSDepartment dept = orgService.getDepartment(m_order.getCompanyId(), m_order.getProductLine());
    Date lastFinishTime = dept.getLastFinishTime();
    if (lastFinishTime == null) {
      lastFinishTime = now;
    }
    m_activity.setStartTime(lastFinishTime);
    m_activity.setFinishTime(now);
    
    // 無效工時(min) TA007 開工時間 - 前次報工時間
    long voidTimeInMinute = (now.getTime() - lastFinishTime.getTime()) / 1000 / 60;
    m_activity.setVoidTime(voidTimeInMinute * 1.0);
    
    m_activity.setWorkingAmount(0.0);
    m_activity.setReviewStatus("");
    m_activity.setWorkingHeadCount(0.0);
    m_activity.setPackAmount(0.0);
    
    m_activity.setTA503("9");
    m_activity.setWorkType(vdWorkType.getValue());
    
    PMCService.updateManufactureOrderActivity(contextUser, m_activity);
    logger.info("product line:[" + m_orderId + "] 開工程序完成.");
    
    handler.onChange();
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
    if (!binder.validate().isOk()) {
      return false;
    }
    
    GSPMCManufactureOrderActivityOverview activityOverview =
      (GSPMCManufactureOrderActivityOverview) handler;
    return activityOverview.kickoffCheck((GSPMCManufactureOrder) getDetail());
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
