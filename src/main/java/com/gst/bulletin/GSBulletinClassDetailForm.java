package com.gst.bulletin;

import com.dcsplab.vdui.components.VDTextField;
import com.dcsplab.vdui.layout.VDFormLayout;
import com.gst.context.GSContext;
import com.gst.domain.GSBulletinClass;
import com.gst.domain.GSMember;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.service.GSBulletinService;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GSBulletinClassDetailForm extends GSDetailFormBase {
  private static final Logger logger = LoggerFactory.getLogger(GSBulletinClassDetailForm.class);
  
  private static final long serialVersionUID = -2317515381203613931L;
  
  private final Binder<GSBulletinClass> binder = new Binder<>(GSBulletinClass.class);
  
  private final GSBulletinService bulletinService;
  
  private final GSMember contextUser;
  
  private GSFormChangeHandler handler;
  
  @PropertyId("id")
  private VDTextField classId;
  
  @PropertyId("name")
  private VDTextField className;
  
  public GSBulletinClassDetailForm() {
    contextUser = GSContext.getCurrentMember();
    bulletinService = GSContext.getApplication().getBulletinService();
    initFields();
  }
  
  private void initFields() {
    classId = new VDTextField();
    className = new VDTextField();
    className.setWidth("300px");
    className.setPlaceholder("輸入公佈欄名稱");
    
    FormLayout formLayout = new VDFormLayout();
    formLayout.getStyle().set("margin-top", "3px");
    formLayout.addFormItem(className, "公佈欄名稱");
    formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
    
    add(formLayout);
  }
  
  @Override
  public void populateDetail() {
    GSBulletinClass data = (GSBulletinClass) getDetail();
    if (getViewMode().equals(ViewMode.New)) {
      setHeader("建立類別:");
    } else {
      setHeader("編輯類別資料: [" + data.getId().trim() + "]-[" + data.getName().trim() + "]");
    }
    
    binder
      .forField(className)
      .withValidator(name -> name.trim().length() > 0, "必須輸入類別名稱")
      .withValidator(
        name -> {
          if (getViewMode().equals(ViewMode.New) || binder.hasChanges()) {
            return !bulletinService.existsBulletinClassName(name);
          }
          return true;
        },
        "類別名稱已存在")
      .bind(GSBulletinClass::getName, GSBulletinClass::setName);
    
    binder.bindInstanceFields(this);
    binder.readBean(data);
  }
  
  @Override
  public String getDetailTitle() {
    return getHeader();
  }
  
  @Override
  public void setChangeHandler(GSFormChangeHandler handler) {
    this.handler = handler;
  }
  
  @Override
  public void onSave() {
    try {
      GSBulletinClass detail = (GSBulletinClass) getDetail();
      binder.writeBean(detail);
      bulletinService.updateBulletinClass(contextUser, detail);
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
  }
  
  public boolean validate() {
    return binder.validate().isOk();
  }
  
  @Override
  public boolean isValueChanged() {
    return binder.hasChanges();
  }
}
