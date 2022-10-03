package com.gst.framework.admin;

import com.dcsplab.vdui.components.VDTextArea;
import com.dcsplab.vdui.components.VDTextField;
import com.dcsplab.vdui.layout.VDFormLayout;
import com.gst.context.GSContext;
import com.gst.domain.GSMember;
import com.gst.domain.GSRole;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.service.GSAuthorizationService;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.data.binder.ValidationException;

public class GSRoleDetailForm extends GSDetailFormBase {
  private static final long serialVersionUID = -1571395661408565248L;
  
  public static final String LABEL_ROLEID = "角色代號";
  public static final String LABEL_ROLENAME = "角色名稱";
  public static final String LABEL_INPUT_ROLEID = "輸入角色編號";
  public static final String LABEL_INPUT_ROLENAME = "輸入角色名稱";
  public static final String LABEL_COMMENT = "備註";
  
  private final Binder<GSRole> binder = new Binder<>(GSRole.class);
  
  private GSRole detail;
  
  private GSFormChangeHandler handler;
  
  @PropertyId("id")
  private VDTextField vdRoleId;
  
  @PropertyId("name")
  private VDTextField vdRoleName;
  
  @PropertyId("remark")
  private VDTextArea vdRemark;
  
  private final GSMember contextUser;
  
  private final GSAuthorizationService authService;
  
  public GSRoleDetailForm() {
    contextUser = GSContext.getCurrentMember();
    authService = GSContext.getApplication().getAuthorizationService();
    initFields();
  }
  
  private void initFields() {
    vdRoleId = new VDTextField();
    vdRoleId.setWidthFull();
    vdRoleId.setPlaceholder(LABEL_INPUT_ROLEID);
    vdRoleId.setRequired(true);
    vdRoleId.setRequiredIndicatorVisible(true);
    
    vdRoleName = new VDTextField();
    vdRoleName.setWidthFull();
    vdRoleName.setPlaceholder(LABEL_INPUT_ROLENAME);
    vdRoleName.setRequired(true);
    vdRoleName.setRequiredIndicatorVisible(true);
    
    vdRemark = new VDTextArea();
    vdRemark.setWidthFull();
    vdRemark.setHeight("100px");
    
    FormLayout formLayout = new VDFormLayout();
    formLayout.getStyle().set("margin-top", "3px");
    formLayout.addFormItem(vdRoleId, "角色代號");
    formLayout.addFormItem(vdRoleName, "角色名稱");
    FormLayout.FormItem remarkItem = formLayout.addFormItem(vdRemark, "備註");
    remarkItem.getElement().setAttribute("colspan", "2");
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
  public void setChangeHandler(GSFormChangeHandler handler) {
    this.handler = handler;
  }
  
  public boolean validate() {
    return binder.validate().isOk();
  }
  
  @Override
  public void onSave() {
    if (binder.validate().isOk()) {
      try {
        binder.writeBean(detail);
        
        detail.setModifier(contextUser.getId());
        detail.persist();
      } catch (ValidationException e) {
        e.printStackTrace();
      }
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
    authService.deleteRole(detail);
    handler.onChange();
  }
  
  @Override
  public boolean isValueChanged() {
    return binder.hasChanges();
  }
  
  @Override
  public void populateDetail() {
    this.detail = (GSRole) getDetail();
    ViewMode mode = getViewMode();
    
    String header;
    if (mode == ViewMode.New) {
      header = "建立新角色";
    } else {
      vdRoleId.setReadOnly(true);
      header = "角色基本資訊: " + detail.getId() + " - " + detail.getName();
    }
    
    setHeader(header);
    
    binder
      .forField(vdRoleId)
      .withValidator(roleId -> roleId.trim().length() > 0, "必須輸入角色編號")
      .bind(GSRole::getId, GSRole::setId);
    
    binder
      .forField(vdRoleName)
      .withValidator(roleName -> roleName.length() > 0, "必須輸入角色名稱")
      .bind(GSRole::getName, GSRole::setName);
    
    binder.bindInstanceFields(this);
    binder.readBean(detail);
  }
  
  @Override
  public Icon getDetailHeaderIcon() {
    return VaadinIcon.PACKAGE.create();
  }
}
