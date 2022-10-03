package com.gst.framework.admin;

import com.dcsplab.security.DLPasswordEncoder;
import com.dcsplab.vdui.components.VDPasswordField;
import com.dcsplab.vdui.layout.VDFormLayout;
import com.gst.context.GSContext;
import com.gst.domain.GSMember;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.service.GSOrganizationService;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

public class GSMemberChangePassword extends GSDetailFormBase {
  private static final long serialVersionUID = 8492199661117342484L;
  
  private final GSMember contextUser;
  
  private VDPasswordField currentPassword;
  
  private VDPasswordField newPassword;
  
  private VDPasswordField confirmPassword;
  
  public GSMemberChangePassword() {
    setHideBasicInfo(true);
    initFields();
    
    contextUser = GSContext.getCurrentMember();
    setDetail(contextUser);
  }
  
  private void initFields() {
    currentPassword = new VDPasswordField();
    currentPassword.setWidthFull();
    
    newPassword = new VDPasswordField();
    newPassword.setWidthFull();
    
    confirmPassword = new VDPasswordField();
    confirmPassword.setWidthFull();
    
    FormLayout formLayout = new VDFormLayout();
    formLayout.getStyle().set("margin-top", "3px");
    formLayout.addFormItem(currentPassword, "目前密碼");
    formLayout.addFormItem(newPassword, "新密碼");
    formLayout.addFormItem(confirmPassword, "確認新密碼");
    formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
    
    add(formLayout);
  }
  
  public boolean validate() {
    String input = currentPassword.getValue().trim();
    String origPwd = contextUser.getPassword().trim();
    DLPasswordEncoder encoder = new DLPasswordEncoder();
    boolean pass = encoder.matches(input, origPwd);
    
    if (!pass) {
      currentPassword.setErrorMessage("目前密碼錯誤");
      currentPassword.setInvalid(true);
    }
    
    String strNewPassword = newPassword.getValue().trim();
    if (pass && strNewPassword.isEmpty()) {
      newPassword.setErrorMessage("請輸入新密碼");
      newPassword.setInvalid(true);
      pass = false;
    }
    
    String strConfirmPassword = confirmPassword.getValue().trim();
    if (pass && !strNewPassword.equals(strConfirmPassword)) {
      confirmPassword.setErrorMessage("新密碼確認錯誤");
      confirmPassword.setInvalid(true);
      pass = false;
    }
    
    return pass;
  }
  
  @Override
  public void populateDetail() {
  }
  
  @Override
  public void onSave() {
    String password = newPassword.getValue().trim();
    DLPasswordEncoder encoder = new DLPasswordEncoder();
    String encodedVal = encoder.encode(password);
    
    GSMember member = (GSMember) getDetail();
    member.setPassword(encodedVal);
    
    GSOrganizationService orgService = GSContext.getApplication().getOrganizationService();
    orgService.updateMember(contextUser, member);
  }
  
  @Override
  public String getDetailTitle() {
    return "變更密碼";
  }
  
  @Override
  public Icon getDetailHeaderIcon() {
    Icon keyIcon = VaadinIcon.KEY.create();
    keyIcon.setSize("13px");
    keyIcon.setColor("#965323");
    
    return keyIcon;
  }
  
  @Override
  public void setChangeHandler(GSFormChangeHandler handler) {
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
    return false;
  }
}
