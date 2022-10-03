package com.gst.framework.admin;

import com.dcsplab.vdui.components.VDTextField;
import com.dcsplab.vdui.layout.VDFormLayout;
import com.gst.context.GSContext;
import com.gst.domain.GSMember;
import com.gst.domain.GSWorkType;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.service.GSPMCService;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.data.binder.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GSWorkTypeDetailForm extends GSDetailFormBase {
  
  private static final long serialVersionUID = 7649424498839570079L;
  private static final Logger logger = LoggerFactory.getLogger(GSWorkTypeDetailForm.class);
  
  private final Binder<GSWorkType> binder = new Binder<>(GSWorkType.class);
  
  @PropertyId("id")
  private VDTextField vdTypeId;
  
  @PropertyId("name")
  private VDTextField vdTypeName;
  
  @PropertyId("typeNameEng")
  private VDTextField vdTypeNameEng;
  
  private GSFormChangeHandler handler;
  
  private final GSMember contextUser;
  
  private final GSPMCService PMCService;
  
  public GSWorkTypeDetailForm() {
    contextUser = GSContext.getCurrentMember();
    PMCService = GSContext.getApplication().getPCMService();
    initFields();
  }
  
  private void initFields() {
    vdTypeId = new VDTextField();
    vdTypeId.setWidthFull();
    
    vdTypeName = new VDTextField();
    vdTypeName.setWidthFull();
    
    vdTypeNameEng = new VDTextField();
    vdTypeNameEng.setWidthFull();
    
    FormLayout formLayout = new VDFormLayout();
    formLayout.getStyle().set("margin-top", "3px");
    formLayout.addFormItem(vdTypeId, "工時類別代號");
    formLayout.addFormItem(vdTypeName, "工時類別名稱");
    formLayout.addFormItem(vdTypeNameEng, "工時類別名稱 - 英文");
    formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
    add(formLayout);
  }
  
  @Override
  public String getDetailTitle() {
    return getHeader();
  }
  
  @Override
  public void setChangeHandler(GSFormChangeHandler handler) {
    this.handler = handler;
  }
  
  public boolean validate() {
    return binder.validate().isOk();
  }
  
  @Override
  public void onSave() {
    GSWorkType workType = (GSWorkType) getDetail();
    
    try {
      if (binder.validate().isOk()) {
        binder.writeBean(workType);
      }
    } catch (ValidationException e) {
      e.printStackTrace();
    }
    
    GSPMCService PMCService = GSContext.getApplication().getPCMService();
    PMCService.updateWorkType(contextUser, workType);
    
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
    GSWorkType workType = (GSWorkType) getDetail();
    PMCService.deleteWorkType(workType);
    handler.onChange();
  }
  
  @Override
  public boolean isValueChanged() {
    return binder.hasChanges();
  }
  
  @Override
  public void populateDetail() {
    GSWorkType workType = (GSWorkType) getDetail();
    final boolean persisted = (workType.getCompanyId() != null && workType.getId() != null);
    
    if (persisted) {
      vdTypeId.setReadOnly(true);
      setHeader("工時類別: " + workType.getId() + " - " + workType.getName());
    } else {
      vdTypeId.setRequiredIndicatorVisible(true);
      vdTypeId.setRequired(true);
      
      setHeader("建立工時類別");
    }
    
    vdTypeName.setRequiredIndicatorVisible(true);
    vdTypeName.setRequired(true);
    
    vdTypeNameEng.setRequiredIndicatorVisible(true);
    vdTypeNameEng.setRequired(true);
    
    binder
      .forField(vdTypeId)
      .withValidator(typeId -> typeId.trim().length() > 0, "必須填寫類別編號")
      .withValidator(
        typeId -> PMCService.getWorkType(contextUser.getCompanyId(), typeId.trim()) == null,
        "類別編號已存在")
      .bind(GSWorkType::getId, GSWorkType::setId);
    
    binder
      .forField(vdTypeName)
      .withValidator(typeId -> typeId.trim().length() > 0, "必須填寫類別名稱")
      .bind(GSWorkType::getName, GSWorkType::setName);
    
    binder
      .forField(vdTypeNameEng)
      .withValidator(typeId -> typeId.trim().length() > 0, "必須填寫類別英文名稱")
      .bind(GSWorkType::getTypeNameEng, GSWorkType::setTypeNameEng);
    
    binder.bindInstanceFields(this);
    binder.readBean(workType);
  }
  
  @Override
  public Icon getDetailHeaderIcon() {
    return VaadinIcon.PACKAGE.create();
  }
}
