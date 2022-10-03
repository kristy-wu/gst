package com.gst.framework.admin;

import com.dcsplab.vdui.components.VDTextArea;
import com.dcsplab.vdui.components.VDTextField;
import com.dcsplab.vdui.layout.VDFormLayout;
import com.gst.context.GSContext;
import com.gst.domain.GSMember;
import com.gst.domain.GSSystemFunction;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.service.GSAuthorizationService;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.data.binder.ValidationException;

public class GSFunctionDetailForm extends GSDetailFormBase {
  
  private static final long serialVersionUID = 7649424498839570079L;
  
  private final Binder<GSSystemFunction> binder = new Binder<>(GSSystemFunction.class);
  
  private GSSystemFunction data;
  
  private GSFormChangeHandler handler;
  
  @PropertyId("id")
  private VDTextField vdFunctionId;
  
  @PropertyId("name")
  private VDTextField vdFunctionName;
  
  @PropertyId("type")
  private VDTextField vdFunctionType;
  
  @PropertyId("module")
  private VDTextField vdModule;
  
  @PropertyId("remark")
  private VDTextArea vdRemark;
  
  private final GSMember contextUser;
  
  private final GSAuthorizationService authService;
  
  public GSFunctionDetailForm() {
    contextUser = GSContext.getCurrentMember();
    authService = GSContext.getApplication().getAuthorizationService();
    initFields();
  }
  
  private void initFields() {
    vdFunctionId = new VDTextField();
    vdFunctionId.setWidthFull();
    vdFunctionName = new VDTextField();
    vdFunctionName.setWidthFull();
    vdFunctionType = new VDTextField();
    vdFunctionType.setWidthFull();
    vdModule = new VDTextField();
    vdModule.setWidthFull();
    
    vdRemark = new VDTextArea();
    vdRemark.setWidthFull();
    vdRemark.setHeight("100px");
    
    FormLayout formLayout = new VDFormLayout();
    formLayout.getStyle().set("margin-top", "3px");
    formLayout.addFormItem(vdFunctionId, "程式代號");
    formLayout.addFormItem(vdFunctionName, "程式名稱");
    formLayout.addFormItem(vdFunctionType, "程式類別");
    formLayout.addFormItem(vdModule, "模組名稱");
    FormLayout.FormItem remarkItem = formLayout.addFormItem(vdRemark, "備註");
    remarkItem.getElement().setAttribute("colspan", "2");
    formLayout.setResponsiveSteps(
      new FormLayout.ResponsiveStep("0", 1),
      new FormLayout.ResponsiveStep("400px", 2, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));
    
    add(formLayout);
  }
  
  @Override
  public void populateDetail() {
    data = (GSSystemFunction) getDetail();
    final boolean persisted = (data.getCompanyId() != null && data.getId() != null);
    
    if (persisted) {
      vdFunctionId.setReadOnly(true);
      vdFunctionName.setReadOnly(true);
      vdFunctionType.setReadOnly(true);
      vdModule.setReadOnly(true);
    } else {
      vdFunctionId.setRequiredIndicatorVisible(true);
      vdFunctionId.setRequired(true);
    }
    setHeader(data.getId() + " - " + data.getName());
    
    binder.bindInstanceFields(this);
    binder.readBean(data);
  }
  
  @Override
  public Icon getDetailHeaderIcon() {
    return VaadinIcon.PACKAGE.create();
  }
  
  @Override
  public String getDetailTitle() {
    return getHeader();
  }
  
  @Override
  public void setChangeHandler(GSFormChangeHandler handler) {
    this.handler = handler;
  }
  
  public boolean check() {
    return binder.validate().isOk();
  }
  
  @Override
  public void onSave() {
    try {
      binder.writeBean(data);
      authService.updateSystemFunction(contextUser, data);
    } catch (ValidationException e) {
      e.printStackTrace();
    }
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
  public boolean isValueChanged() {
    return binder.hasChanges();
  }
}
