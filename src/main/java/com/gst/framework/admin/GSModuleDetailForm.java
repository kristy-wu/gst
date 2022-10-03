package com.gst.framework.admin;

import com.dcsplab.vdui.components.VDTextArea;
import com.dcsplab.vdui.components.VDTextField;
import com.dcsplab.vdui.layout.VDFormLayout;
import com.gst.context.GSContext;
import com.gst.domain.GSMember;
import com.gst.domain.GSModule;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.service.GSAuthorizationService;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.data.binder.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GSModuleDetailForm extends GSDetailFormBase {
  
  private static final Logger logger = LoggerFactory.getLogger(GSModuleDetailForm.class);
  
  private static final long serialVersionUID = 7649424498839570079L;
  
  private final Binder<GSModule> binder = new Binder<>(GSModule.class);
  
  private GSFormChangeHandler handler;
  
  private final GSMember contextUser;
  
  @PropertyId("id")
  private VDTextField vdModuleId;
  
  @PropertyId("name")
  private VDTextField vdModuleName;
  
  @PropertyId("remark")
  private VDTextArea vdRemark;
  
  private final GSAuthorizationService authService;
  
  public GSModuleDetailForm() {
    contextUser = GSContext.getCurrentMember();
    authService = GSContext.getApplication().getAuthorizationService();
    initFields();
  }
  
  private void initFields() {
    
    vdModuleId = new VDTextField();
    vdModuleId.setWidthFull();
    
    vdModuleName = new VDTextField();
    vdModuleName.setWidthFull();
    vdModuleName.setPlaceholder("輸入模組名稱");
    
    vdRemark = new VDTextArea();
    vdRemark.setWidthFull();
    vdRemark.setHeight("100px");
    
    FormLayout formLayout = new VDFormLayout();
    formLayout.getStyle().set("margin-top", "3px");
    formLayout.addFormItem(vdModuleId, "模組代號");
    formLayout.addFormItem(vdModuleName, "模組名稱");
    FormLayout.FormItem remarkItem = formLayout.addFormItem(vdRemark, "備註");
    remarkItem.getElement().setAttribute("colspan", "2");
    formLayout.setResponsiveSteps(
      new FormLayout.ResponsiveStep("0", 1),
      new FormLayout.ResponsiveStep("400px", 2, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));
    
    add(formLayout);
  }
  
  @Override
  public void populateDetail() {
    GSModule detail = (GSModule) getDetail();
    
    vdModuleId.setReadOnly(true);
    vdModuleName.setReadOnly(true);
    
    binder.bindInstanceFields(this);
    binder.readBean(detail);
    
    setHeader(detail.getId() + " - " + detail.getName());
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
    GSModule detail = (GSModule) getDetail();
    try {
      binder.writeBean(detail);
      authService.updateModule(contextUser, detail);
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
    handler.onChange();
  }
  
  @Override
  public boolean isValueChanged() {
    return binder.hasChanges();
  }
}
