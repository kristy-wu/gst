package com.gst.framework.org;

import com.dcsplab.security.DLPasswordEncoder;
import com.dcsplab.vdui.components.*;
import com.dcsplab.vdui.layout.VDFormLayout;
import com.gst.context.GSContext;
import com.gst.domain.GSDepartment;
import com.gst.domain.GSMember;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSCommonDetailDialog;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.framework.util.GSLocalDateInputConverter;
import com.gst.service.GSOrganizationService;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.data.binder.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.*;

public class GSPersonnelDetailForm extends GSDetailFormBase implements GSFormChangeHandler {
  
  private static final long serialVersionUID = -8369147972938918329L;
  
  private static final Logger logger = LoggerFactory.getLogger(GSPersonnelDetailForm.class);
  
  private final Binder<GSMember> binder = new Binder<>(GSMember.class);
  
  @PropertyId("id")
  private VDTextField vdMemberId;
  
  @PropertyId("name")
  private VDTextField vdMemberName;
  
  @PropertyId("deptId")
  private VDTextField vdDeptId;
  
  private VDComboBox<GSDepartment> vdSelectDept;
  
  @PropertyId("password")
  private VDPasswordField vdPassword;
  
  @PropertyId("activeDate")
  private VDDatePicker vdActiveDate;
  
  @PropertyId("obsoleteDate")
  private VDDatePicker vdObsoleteDate;
  
  private GSFormChangeHandler handler;
  
  private final GSOrganizationService orgService;
  
  private final GSMember contextUser;
  
  public GSPersonnelDetailForm() {
    contextUser = GSContext.getCurrentMember();
    orgService = GSContext.getApplication().getOrganizationService();
    
    initFields();
  }
  
  private void initFields() {
    vdMemberId = new VDTextField();
    vdDeptId = new VDTextField(); // main department id
    
    vdMemberName = new VDTextField();
    vdMemberName.setWidthFull();
    vdMemberName.setRequired(true);
    vdMemberName.setRequiredIndicatorVisible(true);
    
    vdPassword = new VDPasswordField();
    vdPassword.setRevealButtonVisible(false);
    
    vdSelectDept = new VDComboBox<>();
    vdSelectDept.setWidth("300px");
    vdSelectDept.setRequired(true);
    vdSelectDept.setRequiredIndicatorVisible(true);
    vdSelectDept.setItemLabelGenerator(item -> item.getId().trim() + "-[" + item.getName() + "]");
    
    VDButton deptSetting = new VDButton("設定部門");
    deptSetting.setIcon(VaadinIcon.GROUP.create());
    deptSetting.addClickListener(e -> showDepartmentSetting());
    
    HorizontalLayout deptLayout = new HorizontalLayout();
    deptLayout.setWidthFull();
    deptLayout.setVerticalComponentAlignment(Alignment.CENTER, vdSelectDept, deptSetting);
    deptLayout.add(vdSelectDept, deptSetting);
    
    vdActiveDate = new VDDatePicker();
    vdActiveDate.setValue(LocalDate.now());
    vdActiveDate.setLocale(Locale.TAIWAN);
    vdActiveDate.setRequired(true);
    vdActiveDate.setRequiredIndicatorVisible(true);
    
    vdObsoleteDate = new VDDatePicker();
    vdObsoleteDate.setValue(LocalDate.now());
    vdObsoleteDate.setLocale(Locale.TAIWAN);
    vdObsoleteDate.setRequired(true);
    vdObsoleteDate.setRequiredIndicatorVisible(true);
    
    FormLayout formLayout = new VDFormLayout();
    formLayout.getStyle().set("margin-top", "3px");
    FormLayout.FormItem memberIdItem = formLayout.addFormItem(vdMemberId, "員工編號");
    memberIdItem.getElement().setAttribute("colspan", "2");
    
    formLayout.addFormItem(vdMemberName, "員工姓名");
    formLayout.addFormItem(vdPassword, "密碼");
    
    formLayout.addFormItem(vdActiveDate, "生效日");
    formLayout.addFormItem(vdObsoleteDate, "失效日");
    
    FormLayout.FormItem deptItem = formLayout.addFormItem(deptLayout, "主要部門");
    deptItem.getElement().setAttribute("colspan", "2");
    
    formLayout.setResponsiveSteps(
      new FormLayout.ResponsiveStep("0", 1),
      new FormLayout.ResponsiveStep("400px", 2, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));
    
    add(formLayout);
  }
  
  private void showDepartmentSetting() {
    GSMember data = (GSMember) getDetail();
    
    GSPersonnelSelectDept detailForm = new GSPersonnelSelectDept();
    detailForm.setViewMode(ViewMode.ConfirmClose);
    detailForm.setChangeHandler(this);
    detailForm.setDetail(data);
    
    GSCommonDetailDialog dialog = new GSCommonDetailDialog(detailForm);
    dialog.setWidth("680px");
    dialog.setHeight("450px");
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
  }
  
  public void updateDepartmentList(Collection<GSDepartment> selectedList) {
    GSDepartment selectedDept = vdSelectDept.getValue();
    ArrayList<GSDepartment> deptList = new ArrayList<>();
    
    GSDepartment mainDepartment = null;
    for (GSDepartment dept : selectedList) {
      if (selectedDept != null && dept.getId().equals(selectedDept.getId())) {
        mainDepartment = dept;
      }
      deptList.add(dept);
    }
    
    vdSelectDept.setItems(deptList);
    if (mainDepartment == null) {
      mainDepartment = deptList.get(0);
    }
    
    vdSelectDept.setValue(mainDepartment);
  }
  
  public Collection<GSDepartment> getAssignedDepartments() {
    return vdSelectDept.getItems();
  }
  
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
    GSMember member = (GSMember) getDetail();
    try {
      binder.writeBean(member);
    } catch (ValidationException e) {
      e.printStackTrace();
    }
    
    GSDepartment mainDept = vdSelectDept.getValue();
    member.setDeptId(mainDept.getId());
    member.setModifyPRID(handler.getHandlerID());
    
    String pwd = vdPassword.getValue().trim();
    if (getViewMode().equals(ViewMode.New) || !pwd.isEmpty()) {
      DLPasswordEncoder encoder = new DLPasswordEncoder();
      pwd = encoder.encode(pwd);
      member.setPassword(pwd);
    }
    
    Collection<GSDepartment> depts = vdSelectDept.getItems();
    member.setRelatedDepartments(new ArrayList<>(depts));
    orgService.updateMember(contextUser, member);
    
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
    GSMember member = (GSMember) getDetail();
    orgService.deleteMember(member);
    handler.onChange();
  }
  
  @Override
  public boolean isValueChanged() {
    return binder.hasChanges();
  }
  
  @Override
  public void populateDetail() {
    GSMember member = (GSMember) getDetail();
    if (getViewMode().equals(ViewMode.New)) {
      vdMemberId.setRequiredIndicatorVisible(true);
      vdMemberId.setRequired(true);
      
      binder
        .forField(vdMemberId)
        .withValidator(memberId -> memberId.trim().length() > 0, "必須輸入員工編號")
        .withValidator(
          memberId -> orgService.getMember(contextUser.getCompanyId(), memberId.trim()) == null,
          "員工編號已存在")
        .bind(GSMember::getId, GSMember::setId);
      
      binder
        .forField(vdPassword)
        .withValidator(pwd -> pwd.trim().length() > 0, "必須輸入密碼")
        .bind(GSMember::getId, GSMember::setId);
      
      setHeader("建立員工資料");
    } else {
      vdMemberId.setReadOnly(true);
      
      List<GSDepartment> depts = member.getRelatedDepartments();
      vdSelectDept.setItems(depts);
      
      String mainDeptId = member.getDeptId();
      GSDepartment mainDept = orgService.getDepartment(member.getCompanyId(), mainDeptId);
      vdSelectDept.setValue(mainDept);
      
      setHeader("編輯員工資料: " + member.getId() + " - " + member.getName());
    }
    
    vdMemberName.setRequiredIndicatorVisible(true);
    vdMemberName.focus();
    
    binder
      .forField(vdMemberName)
      .withValidator(memberId -> memberId.trim().length() > 0, "必須輸入員工姓名")
      .bind(GSMember::getName, GSMember::setName);
    
    binder
      .forField(vdSelectDept)
      .withValidator(Objects::nonNull, "必須設定主要部門")
      .bind(GSMember::getMainDepartment, GSMember::setMainDepartment);
    
    binder
      .forField(vdActiveDate)
      .withConverter(new GSLocalDateInputConverter())
      .withValidator(Objects::nonNull, "必須設定生效日")
      .bind(GSMember::getActiveDate, GSMember::setActiveDate);
    
    binder
      .forField(vdObsoleteDate)
      .withConverter(new GSLocalDateInputConverter())
      .withValidator(Objects::nonNull, "必須設定失效日")
      .bind(GSMember::getObsoleteDate, GSMember::setObsoleteDate);
    
    binder.bindInstanceFields(this);
    
    member.setPassword("");
    binder.readBean(member);
  }
  
  @Override
  public Icon getDetailHeaderIcon() {
    return VaadinIcon.USER.create();
  }
  
  @Override
  public void onChange() {
  }
  
  @Override
  public String getHandlerID() {
    return null;
  }
}
