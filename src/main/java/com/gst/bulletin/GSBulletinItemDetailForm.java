package com.gst.bulletin;

import com.dcsplab.vdui.components.VDDatePicker;
import com.dcsplab.vdui.components.VDTextField;
import com.dcsplab.vdui.layout.VDFormLayout;
import com.gst.broadcast.GSBroadcastMessage;
import com.gst.broadcast.GSBulletinBroadcaster;
import com.gst.context.GSContext;
import com.gst.domain.GSBulletinItem;
import com.gst.domain.GSMember;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.service.GSBulletinService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class GSBulletinItemDetailForm extends GSDetailFormBase {
  
  private static final Logger logger = LoggerFactory.getLogger(GSBulletinItemDetailForm.class);
  
  private static final long serialVersionUID = 5962621604475358114L;
  
  private final GSBulletinService bulletinService;
  
  private final Binder<GSBulletinItem> binder = new Binder<>(GSBulletinItem.class);
  
  private GSFormChangeHandler handler;
  
  private final GSMember contextUser;
  
  @PropertyId("id")
  private VDTextField vdItemId;
  
  @PropertyId("classId")
  private VDTextField vdClassId;
  
  @PropertyId("content")
  private VDTextField vdContent;
  
  @PropertyId("startDate")
  private VDDatePicker vdStartDate;
  
  @PropertyId("endDate")
  private VDDatePicker vdEndDate;
  
  public GSBulletinItemDetailForm() {
    contextUser = GSContext.getCurrentMember();
    bulletinService = GSContext.getApplication().getBulletinService();
    initFields();
  }
  
  private void initFields() {
    vdItemId = new VDTextField(); // 項目編號
    vdClassId = new VDTextField(); // 類別編號
    
    vdStartDate = new VDDatePicker();
    vdStartDate.setClearButtonVisible(true);
    
    vdEndDate = new VDDatePicker();
    vdEndDate.setClearButtonVisible(true);
    
    vdContent = new VDTextField();
    vdContent.setPlaceholder("輸入項目內容(最多20字)");
    vdContent.setWidthFull();
    vdContent.setMaxLength(20);
    vdContent.setRequiredIndicatorVisible(true);
    
    FormLayout formLayout = new VDFormLayout();
    formLayout.getStyle().set("margin-top", "3px");
    formLayout.addFormItem(vdStartDate, "生效日");
    formLayout.addFormItem(vdEndDate, "失效日");
    FormLayout.FormItem contentItem = formLayout.addFormItem(vdContent, "項目內容");
    contentItem.getElement().setAttribute("colspan", "2");
    formLayout.setResponsiveSteps(
      new FormLayout.ResponsiveStep("0", 1),
      new FormLayout.ResponsiveStep("400px", 2, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));
    
    add(formLayout);
  }
  
  private void bindFields(GSBulletinItem data) {
    binder
      .forField(vdStartDate)
      .asRequired("必須設定生效日!")
      .withValidator(
        localDate -> {
          LocalDate end = vdEndDate.getValue();
          if (end == null) {
            return false;
          }
          return end.isAfter(localDate);
        },
        "生效日不可大於失效日!")
      .bind(GSBulletinItem::getStartDateLocal, GSBulletinItem::setStartDateLocal);
    
    binder
      .forField(vdEndDate)
      .asRequired("必須設定失效日!")
      .withValidator(
        localDate -> {
          LocalDate start = vdStartDate.getValue();
          if (start == null) {
            return false;
          }
          return localDate.isAfter(start);
        },
        "失效日必須大於生效日!")
      .bind(GSBulletinItem::getEndDateLocal, GSBulletinItem::setEndDateLocal);
    
    binder
      .forField(vdContent)
      .asRequired("必須填寫項目內容")
      .bind(GSBulletinItem::getContent, GSBulletinItem::setContent);
    
    binder.bindInstanceFields(this);
    binder.readBean(data);
  }
  
  @Override
  public void populateDetail() {
    GSBulletinItem item = (GSBulletinItem) getDetail();
    if (getViewMode().equals(ViewMode.New)) {
      setHeader("建立公佈欄項目:");
    } else {
      setHeader("編輯項目內容: [" + item.getId().trim() + "]");
    }
    
    bindFields(item);
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
      GSBulletinItem item = (GSBulletinItem) getDetail();
      binder.writeBean(item);
      bulletinService.updateBulletinItem(contextUser, item);
      
      GSBroadcastMessage message = new GSBroadcastMessage();
      message.setContent(item);
      GSBulletinBroadcaster.broadcast(UI.getCurrent(), message);
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
    GSBulletinBroadcaster.broadcast(UI.getCurrent(), null);
  }
  
  @Override
  public boolean isValueChanged() {
    return binder.hasChanges();
  }
}
