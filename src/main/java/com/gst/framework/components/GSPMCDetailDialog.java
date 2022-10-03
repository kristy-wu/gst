package com.gst.framework.components;

import com.dcsplab.vdui.UIConstants;
import com.dcsplab.vdui.components.VDButton;
import com.dcsplab.vdui.components.VDConfirmDialog;
import com.dcsplab.vdui.components.VDConfirmDialog.Option;
import com.dcsplab.vdui.components.VDLabel;
import com.dcsplab.vdui.util.BoxShadowBorders;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.GSDetailFormBase.ViewMode;
import com.gst.mes.pmc.GSPMCManufactureOrderActivityReviewDetail;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class GSPMCDetailDialog extends Dialog implements VDConfirmDialog.ConfirmHandler {
  
  private static final String REVIEW_CONFIRMED = "審核";
  
  private static final String REVIEW_CLOSED = "結案";
  
  private static final String REVIEW_OBSOLETE = "作廢";
  
  private static final String REVIEW_RESUME = "取消結案";
  
  private static final String DIALOG_SAVE = "儲存";
  
  private static final String DIALOG_CANCEL = "取消";
  
  private static final String DIALOG_DELETE = "刪除";
  
  private static final long serialVersionUID = -5980836712033050294L;
  
  private final GSDetailRepresentation component;
  
  private final VerticalLayout panel;
  
  private VDLabel title;
  
  private VerticalLayout content;
  
  private boolean isObsolete = false;
  
  public GSPMCDetailDialog(GSDetailRepresentation component) {
    
    this.component = component;
    
    panel = new VerticalLayout();
    panel.setSizeFull();
    panel.getStyle().set("padding", "0px");
    
    createHeader();
    createContent();
    createFooter();
    
    setWidth("800px");
    setHeight("500px");
    
    add(panel);
  }
  
  private void createHeader() {
    title = new VDLabel();
    title.setText(component.getDetailTitle());
    
    Icon closeIcon = VaadinIcon.CLOSE.create();
    closeIcon.setSize("14px");
    closeIcon.setColor(UIConstants.DEFAULT_RED_COLOR);
    closeIcon.addClickListener(e -> closeDialog());
    closeIcon.getStyle().set("cursor", "pointer");
    
    Icon titleIcon = component.getDetailHeaderIcon();
    titleIcon.setColor(UIConstants.DEFAULT_ICON_ENABLED_COLOR);
    titleIcon.setSize("14px");
    
    HorizontalLayout titleLayout = new HorizontalLayout();
    titleLayout.add(titleIcon, title);
    titleLayout.setVerticalComponentAlignment(Alignment.CENTER, titleIcon, title);
    
    HorizontalLayout header = new HorizontalLayout();
    header.setHeight("32px");
    header.setWidthFull();
    // header.getStyle().set("margin-top", "16px");
    header.addClassName(BoxShadowBorders.BOTTOM);
    header.setFlexGrow(1, titleLayout);
    header.setVerticalComponentAlignment(Alignment.CENTER, titleLayout, closeIcon);
    header.add(titleLayout, closeIcon);

    /*
    header.setHeight("40px");
    header.getStyle().set("margin-top", "-22px");
    header.getStyle().set("margin-left", "-22px");
    header.getStyle().set("width", "calc(100% + 40px)");
    header.addClassName(BoxShadowBorders.BOTTOM);
    header.setFlexGrow(1, titleLayout);
    header.setVerticalComponentAlignment(Alignment.CENTER, titleLayout, closeIcon);
    header.add(titleLayout, closeIcon);*/
    
    panel.add(header);
  }
  
  private void createContent() {
    GSDetailFormBase detailForm = (GSDetailFormBase) component;
    detailForm.setSizeFull();
    
    content = new VerticalLayout();
    content.setSizeFull();
    content.getStyle().set("overflow", "auto");
    content.setHorizontalComponentAlignment(Alignment.CENTER, detailForm);
    content.addClassName(BoxShadowBorders.BOTTOM);
    content.add(detailForm);

    /*
    content.setHeight("460px");
    content.getStyle().set("margin-left", "-22px");
    content.getStyle().set("width", "calc(100% + 40px)");
    content.getStyle().set("overflow", "auto");
    content.setPadding(false);*/
    
    panel.add(content);
  }
  
  private void createFooter() {
    GSDetailFormBase detailForm = (GSDetailFormBase) component;
    ViewMode mode = detailForm.getViewMode();
    
    VDButton save = new VDButton(DIALOG_SAVE);
    save.setIcon(VaadinIcon.CHECK_CIRCLE_O.create());
    save.addClickListener(e -> saveDetail());
    
    VDButton cancel = new VDButton(DIALOG_CANCEL);
    cancel.setIcon(VaadinIcon.CLOSE_CIRCLE_O.create());
    cancel.addClickListener(e -> cancel());
    
    VDButton delete = new VDButton(DIALOG_DELETE);
    delete.setIcon(VaadinIcon.TRASH.create());
    delete.addClickListener(e -> delete());
    delete.getStyle().set("color", "#D93651");
    
    HorizontalLayout buttonLayout = new HorizontalLayout();
    buttonLayout.setWidthFull();
    buttonLayout.setHeight("42px");
    buttonLayout.getStyle().set("margin-top", "5px");
    buttonLayout.setJustifyContentMode(JustifyContentMode.END);
    /*
    buttonLayout.setHeight("40px");
    // buttonLayout.getStyle().set("margin-bottom", "-24px");
    buttonLayout.getStyle().set("bottom", "0px");
    buttonLayout.getStyle().set("margin-left", "-22px");
    buttonLayout.getStyle().set("width", "calc(100% + 40px)");
    // buttonLayout.getStyle().set("background-color", "grey");
    buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);*/
    
    if (mode == ViewMode.View) {
      save.setText("關閉");
      buttonLayout.add(save);
    } else if (mode == ViewMode.Modify) {
      buttonLayout.add(save, cancel);
    } else if (mode == ViewMode.Delete) {
      buttonLayout.add(save, cancel, delete);
    } else if (mode == ViewMode.ConfirmClose || mode == ViewMode.New) {
      save.setText("確定");
      cancel.setText(DIALOG_CANCEL);
      buttonLayout.add(save, cancel);
    } else if (mode == ViewMode.PMCReview) {
      VDButton reviewConfirm = new VDButton(REVIEW_CONFIRMED);
      reviewConfirm.addClickListener(e -> pmcReviewConfirmed());
      
      VDButton reviewClose = new VDButton(REVIEW_CLOSED);
      reviewClose.addClickListener(e -> pmcReviewClosed());
      
      VDButton PMCResume = new VDButton(REVIEW_RESUME);
      PMCResume.addClickListener(e -> pmcResume());
      PMCResume.setEnabled(false);
      
      VDButton reviewObsolete = new VDButton(REVIEW_OBSOLETE);
      reviewObsolete.addClickListener(e -> pmcReviewObsolete());
      
      GSPMCManufactureOrderActivityReviewDetail reviewForm = ((GSPMCManufactureOrderActivityReviewDetail) component);

      /*
      if (reviewForm.isReviewed()) {
      	//reviewConfirm.setEnabled(false);
      	//reviewObsolete.setEnabled(false);
      }

      if (reviewForm.isPMCOrderClosed()) {
      	PMCResume.setEnabled(true);
      }
      */
      
      if (reviewForm.isReviewClosed()) { // 此審核單設定結案
        if (reviewForm.isManufactureOrderCompleted()) { // 此製令設定結案
          PMCResume.setEnabled(true);
          reviewConfirm.setEnabled(false);
          reviewClose.setEnabled(false);
          reviewObsolete.setEnabled(false);
        } else {
          PMCResume.setEnabled(false);
          reviewConfirm.setEnabled(false);
          reviewClose.setEnabled(false);
          reviewObsolete.setEnabled(false);
        }
      } else if (reviewForm.isObsolete()) { // 此審核單已作廢
        PMCResume.setEnabled(false);
        reviewClose.setEnabled(false);
        reviewObsolete.setEnabled(false);
        cancel.setText("關閉");
      } else {
        if (reviewForm.isReviewed()) {
          reviewConfirm.setEnabled(false);
          reviewObsolete.setEnabled(false);
        }
        if (reviewForm.isManufactureOrderCompleted()) {
          reviewClose.setEnabled(false);
        }
      }
      
      buttonLayout.add(reviewConfirm, reviewClose, PMCResume, reviewObsolete, cancel);
    }
    
    panel.add(buttonLayout);
  }
  
  public void setTitle(String str) {
    title.setText(str);
  }
  
  public void closeDialog() {
    if (component.isValueChanged()) {
      String title = "資料修改提醒";
      String message = "資料內容已修改，是否要放棄？";
      VDConfirmDialog d = new VDConfirmDialog(this, title, message, Option.OK_CANCEL);
      d.show();
    } else {
      close();
    }
  }
  
  public void saveDetail() {
    if (component.validate()) {
      component.onSave();
      close();
    }
  }
  
  public void cancel() {
    if (component.isValueChanged()) {
      String title = "資料修改提醒";
      String message = "資料內容已修改，是否要放棄？";
      VDConfirmDialog d = new VDConfirmDialog(this, title, message, Option.OK_CANCEL);
      d.show();
    } else {
      close();
    }
  }
  
  public void delete() {
    String title = "資料刪除警示";
    String message = "確定要刪除此資料？";
    VDConfirmDialog d = new VDConfirmDialog(this, title, message, Option.DELETE_CANCEL);
    d.show();
  }
  
  public void pmcReviewConfirmed() {
    if (component.validate()) {
      ((GSPMCManufactureOrderActivityReviewDetail) component).reviewConfirm();
    }
    super.close();
  }
  
  public void pmcReviewClosed() {
    if (component.validate()) {
      ((GSPMCManufactureOrderActivityReviewDetail) component).reviewClose();
    }
    super.close();
  }
  
  public void pmcResume() {
    if (component.validate()) {
      ((GSPMCManufactureOrderActivityReviewDetail) component).resumePMCLine();
    }
    super.close();
  }
  
  public void pmcReviewObsolete() {
    isObsolete = true;
    
    String title = "資料修改提醒";
    String message = "確定要作廢本次報工內容?";
    VDConfirmDialog d = new VDConfirmDialog(this, title, message, Option.OK_CANCEL);
    d.show();
  }
  
  public void close() {
    content.remove((GSDetailFormBase) component);
    super.close();
  }
  
  @Override
  public void doConfirm() {
    if (isObsolete) {
      ((GSPMCManufactureOrderActivityReviewDetail) component).reviewObsolete();
    }
    close();
  }
  
  @Override
  public void doReject() {
  }
  
  @Override
  public void doConfirmDelete() {
    component.onDelete();
    close();
  }
}
