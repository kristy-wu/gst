package com.gst.framework.org;

import com.dcsplab.vdui.components.VDTextField;
import com.dcsplab.vdui.layout.VDFormLayout;
import com.gst.context.GSContext;
import com.gst.domain.GSDepartment;
import com.gst.domain.GSMember;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.service.GSOrganizationService;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GSDepartmentDetailForm extends GSDetailFormBase implements GSFormChangeHandler {
  
  private static final long serialVersionUID = -6589479318166751020L;
  
  private static final Logger logger = LoggerFactory.getLogger(GSDepartmentDetailForm.class);
  
  private final GSOrganizationService orgService;
  
  private final Binder<GSDepartment> binder = new Binder<>(GSDepartment.class);
  
  private GSFormChangeHandler handler;
  
  @PropertyId("id")
  private VDTextField vdDeptId;
  
  @PropertyId("name")
  private VDTextField vdDeptName;
  
  private final GSMember contextUser;
  
  public GSDepartmentDetailForm() {
    contextUser = GSContext.getCurrentMember();
    orgService = GSContext.getApplication().getOrganizationService();
    initFields();
  }
  
  private void initFields() {
    vdDeptId = new VDTextField();
    vdDeptId.setWidthFull();
    
    vdDeptName = new VDTextField();
    vdDeptName.setWidthFull();
    
    FormLayout formLayout = new VDFormLayout();
    formLayout.getStyle().set("margin-top", "3px");
    formLayout.addFormItem(vdDeptId, "部門編號");
    formLayout.addFormItem(vdDeptName, "部門名稱");
    formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
    
    add(formLayout);
  }
  
  @Override
  public void populateDetail() {
    GSDepartment data = (GSDepartment) getDetail();
    
    if (getViewMode().equals(ViewMode.New)) {
      setHeader("建立部門:");
      binder
        .forField(vdDeptId)
        .withValidator(deptId -> deptId.trim().length() > 0, "必須輸入部門編號")
        .withValidator(
          deptId -> orgService.getDepartment(contextUser.getCompanyId(), deptId.trim()) == null,
          "部門編號已存在")
        .bind(GSDepartment::getId, GSDepartment::setId);
    } else {
      vdDeptId.setReadOnly(true);
      setHeader("編輯部門資料: [" + data.getId().trim() + "]-[" + data.getName().trim() + "]");
    }
    
    binder
      .forField(vdDeptName)
      .withValidator(deptName -> deptName.length() > 0, "必須輸入部門名稱")
      .bind(GSDepartment::getName, GSDepartment::setName);
    
    binder.bindInstanceFields(this);
    binder.readBean(data);
  }
  
  @Override
  public Icon getDetailHeaderIcon() {
    return VaadinIcon.WORKPLACE.create();
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
    try {
      GSDepartment data = (GSDepartment) getDetail();
      binder.writeBean(data);
      orgService.updateDepartment(contextUser, data);
    } catch (Exception e) {
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
    try {
      GSDepartment data = (GSDepartment) getDetail();
      orgService.deleteDepartment(data);
    } catch (Exception e) {
      e.printStackTrace();
    }
    handler.onChange();
  }
  
  @Override
  public boolean isValueChanged() {
    return binder.hasChanges();
  }
  
  @Override
  public void onChange() {
  }
  
  @Override
  public String getHandlerID() {
    return null;
  }
}
