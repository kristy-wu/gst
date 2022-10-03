package com.gst.framework;

import com.dcsplab.common.DLDateUtils;
import com.dcsplab.vdui.components.VDTextField;
import com.dcsplab.vdui.layout.VDFlexBoxLayout;
import com.gst.context.GSAbstractObject;
import com.gst.context.GSContext;
import com.gst.domain.GSCompany;
import com.gst.framework.components.GSDetailRepresentation;
import com.gst.service.GSOrganizationService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

@CssImport(value = "./styles/dcsplab/detail-form-base.css", themeFor = "vaadin-form-layout")
public abstract class GSDetailFormBase extends VDFlexBoxLayout implements GSDetailRepresentation {
  private static final long serialVersionUID = 5656252858117592925L;
  private static final Logger logger = LoggerFactory.getLogger(GSDetailFormBase.class);
  
  public static final String COMPANY = "公司別";
  public static final String CREATOR = "建立人";
  public static final String CREATE_DATE = "建立日期";
  public static final String MODIFY_DATE = "前次修改日期";
  
  private GSAbstractObject detail;
  
  private String header;
  
  private final VDTextField vdCompanyDisplay = new VDTextField();
  
  private final VDTextField vdCompanyId = new VDTextField();
  
  private final VDTextField vdCreator = new VDTextField();
  
  private final VDTextField vdCreateDate = new VDTextField();
  
  private final VDTextField vdModifyDate = new VDTextField();
  
  private FormLayout basicInfoLayout;
  
  private ViewMode viewMode = ViewMode.View;
  
  public GSDetailFormBase() {
    setFlexDirection(FlexDirection.COLUMN);
    setSizeFull();
    addBasicInfoArea();
  }
  
  private void addBasicInfoArea() {
    vdCompanyDisplay.setReadOnly(true);
    vdCompanyDisplay.setWidthFull();
    vdCreator.setReadOnly(true);
    vdCreator.setWidthFull();
    vdCreateDate.setReadOnly(true);
    vdCreateDate.setWidthFull();
    vdModifyDate.setReadOnly(true);
    vdModifyDate.setWidthFull();
    
    basicInfoLayout = new FormLayout();
    
    basicInfoLayout.addFormItem(vdCompanyDisplay, COMPANY);
    basicInfoLayout.addFormItem(vdCreator, CREATOR);
    basicInfoLayout.addFormItem(vdCreateDate, CREATE_DATE);
    basicInfoLayout.addFormItem(vdModifyDate, MODIFY_DATE);
    
    basicInfoLayout.setResponsiveSteps(
      new FormLayout.ResponsiveStep("0", 1), new FormLayout.ResponsiveStep("400px", 2));
    
    add(basicInfoLayout);
  }
  
  private String getDateDisplay(String value) {
    if (value == null || value.trim().isEmpty()) {
      return "";
    }
    
    String display = value;
    try {
      Date date = DLDateUtils.parse(value, DLDateUtils.Resolution.SimpleDate);
      display = DLDateUtils.format(date, DLDateUtils.Resolution.NormalDate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return display;
  }
  
  public abstract void populateDetail();
  
  private void refreshData() {
    String companyId = detail.getCompanyId();
    GSOrganizationService orgService = GSContext.getApplication().getOrganizationService();
    GSCompany company = orgService.getCompany(companyId);
    
    vdCompanyId.setValue(companyId);
    vdCompanyDisplay.setValue(company.getName());
    
    String strCreator = detail.getCreator() == null ? "" : detail.getCreator().trim();
    vdCreator.setValue(strCreator);
    
    vdCreateDate.setValue(getDateDisplay(detail.getCreateDate()));
    vdModifyDate.setValue(getDateDisplay(detail.getModifyDate()));
    
    populateDetail();
  }
  
  @Override
  public Icon getDetailHeaderIcon() {
    return VaadinIcon.FILE_TEXT_O.create();
  }
  
  public void setHideBasicInfo(boolean hide) {
    basicInfoLayout.setVisible(!hide);
  }
  
  public String getHeader() {
    return header;
  }
  
  public void setHeader(String header) {
    this.header = header;
  }
  
  public GSAbstractObject getDetail() {
    return detail;
  }
  
  public void setDetail(GSAbstractObject abstractObject) {
    this.detail = abstractObject;
    refreshData();
  }
  
  public ViewMode getViewMode() {
    return viewMode;
  }
  
  public void setViewMode(ViewMode viewMode) {
    this.viewMode = viewMode;
  }
  
  public enum ViewMode {
    View,
    Modify,
    Delete,
    New,
    PMCReview,
    ConfirmClose
  }
}
