package com.gst.framework.admin;

import com.dcsplab.vdui.components.VDButton;
import com.dcsplab.vdui.components.VDTextField;
import com.dcsplab.vdui.layout.VDFormLayout;
import com.dcsplab.vdui.layout.VDVerticalLayout;
import com.gst.context.GSContext;
import com.gst.domain.GSDepartment;
import com.gst.domain.GSMember;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSCommonDetailDialog;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.service.GSOrganizationService;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GSMemberProfile extends GSDetailFormBase {
  
  private static final long serialVersionUID = -6156737248382484170L;
  private static final Logger logger = LoggerFactory.getLogger(GSMemberProfile.class);
  
  private final GSMember contextUser;
  
  private final Binder<GSMember> binder = new Binder<>(GSMember.class);
  
  // private GSFormChangeHandler handler;
  
  @PropertyId("id")
  private VDTextField vdMemberId;
  
  @PropertyId("name")
  private VDTextField vdMemberName;
  
  @PropertyId("deptId")
  private VDTextField vdDepartmentId;
  
  private VDTextField vdDepartmentName;
  
  @PropertyId("password")
  private VDTextField vdPassword;
  
  private final GSOrganizationService orgService;
  
  public GSMemberProfile() {
    contextUser = GSContext.getCurrentMember();
    orgService = GSContext.getApplication().getOrganizationService();
    
    initFields();
    setDetail(contextUser);
  }
  
  private void initFields() {
    vdMemberId = new VDTextField();
    vdMemberId.setWidthFull();
    vdMemberId.setReadOnly(true);
    
    vdMemberName = new VDTextField();
    vdMemberName.setWidthFull();
    vdMemberName.setReadOnly(true);
    
    vdDepartmentId = new VDTextField();
    vdDepartmentId.setWidthFull();
    vdDepartmentId.setReadOnly(true);
    
    vdDepartmentName = new VDTextField();
    vdDepartmentName.setWidthFull();
    vdDepartmentName.setReadOnly(true);
    
    vdPassword = new VDTextField("密碼");
    
    VDButton button = new VDButton("變更密碼");
    button.addClickListener(
      e -> {
        GSMemberChangePassword pwdForm = new GSMemberChangePassword();
        pwdForm.setViewMode(ViewMode.Modify);
        
        GSCommonDetailDialog dialog = new GSCommonDetailDialog(pwdForm);
        dialog.setWidth("300px");
        dialog.setHeight("350px");
        dialog.setCloseOnOutsideClick(false);
        dialog.setCloseOnEsc(false);
        dialog.open();
      });
    
    VDVerticalLayout V1 = new VDVerticalLayout();
    V1.setHorizontalComponentAlignment(Alignment.END, button);
    V1.add(button);
    V1.setHeight("40px");
    
    FormLayout formLayout = new VDFormLayout();
    formLayout.getStyle().set("margin-top", "3px");
    formLayout.addFormItem(vdDepartmentId, "部門編號");
    formLayout.addFormItem(vdDepartmentName, "部門名稱");
    formLayout.addFormItem(vdMemberId, "員工編號");
    formLayout.addFormItem(vdMemberName, "員工姓名");
    formLayout.add();
    formLayout.setResponsiveSteps(
      new FormLayout.ResponsiveStep("0", 1),
      new FormLayout.ResponsiveStep("400px", 2, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));
    
    add(formLayout, V1);
  }
  
  @Override
  public String getDetailTitle() {
    GSMember member = (GSMember) getDetail();
    return member.getId() + " - " + member.getName();
  }
  
  @Override
  public void setChangeHandler(GSFormChangeHandler handler) {
    // this.handler = handler;
  }
  
  public boolean check() {
    return binder.validate().isOk();
  }
  
  @Override
  public void populateDetail() {
    GSMember member = (GSMember) getDetail();
    member.setPassword("");
    
    String deptId = member.getDeptId();
    GSDepartment dept = orgService.getDepartment(member.getCompanyId(), deptId);
    
    vdDepartmentName.setValue(dept.getName().trim());
    
    binder.bindInstanceFields(this);
    binder.readBean(member);
  }
  
  @Override
  public void onSave() {
    /*GSMember member = (GSMember) getDetail();
    String pwd = vdPassword.getValue().trim();
    if (!pwd.isEmpty()) {
      DLPasswordEncoder encoder = new DLPasswordEncoder();
      pwd = encoder.encode(pwd);
      member.setPassword(pwd);
    }
    member.setName(vdMemberName.getValue().trim());

    GSOrganizationService orgService = GSContext.getApplication().getOrganizationService();
    orgService.updateMember(contextUser, member);*/
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
