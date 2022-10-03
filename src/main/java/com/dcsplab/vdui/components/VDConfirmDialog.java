package com.dcsplab.vdui.components;

import com.dcsplab.vdui.UIConstants;
import com.dcsplab.vdui.layout.VDVerticalLayout;
import com.dcsplab.vdui.util.BoxShadowBorders;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * The overlay elements in v10 are a bit special, including Dialog, Notification, and the dropdown
 * elements of ComboBox, DatePicker, ContextMenu, and Select.
 *
 * <p>With those components, the main element (f.e. <vaadin-dialog>) is not the one which is
 * visually shown. Instead, another element (f.e. <vaadin-dialog-overlay>) is created directly under
 * the <body> element, to escape any parent stacking contexts.
 *
 * <p>The 'class' attribute is not copied from the main element to the actual overlay element. In
 * the latest versions of the overlay elements (currently in beta), the 'theme' attribute is copied
 * from the main element to the overlay elements, allowing you to style individual overlay elements.
 */
@CssImport(
  value = "./styles/dcsplab-confirm-dialog.css",
  themeFor = "vaadin-dialog-overlay vaadin-horizontal-layout vaadin-button")
public class VDConfirmDialog extends Dialog implements HasStyle {
  
  private static final long serialVersionUID = 1783421745823985197L;
  
  private static final String DIALOG_THEME = "dcsplab-confirm-dialog";
  
  private final Option option;
  
  private final String title;
  
  private final String message;
  
  private final ConfirmHandler handler;
  
  private HorizontalLayout header;
  
  private VerticalLayout content;
  
  private VerticalLayout footer;
  
  public VDConfirmDialog(ConfirmHandler h, String message) {
    this(h, "", "", Option.OK);
  }
  
  public VDConfirmDialog(ConfirmHandler h, String title, String message, Option option) {

    /*
    the 'theme' attribute is copied from the main element to the overlay elements
     */
    getElement().setAttribute("theme", DIALOG_THEME);
    
    this.setDraggable(true);
    
    this.handler = h;
    this.title = title;
    if (message.trim().isEmpty()) {
      message = " ";
    }
    this.message = message;
    this.option = option;
    
    this.setCloseOnEsc(false);
    this.setCloseOnOutsideClick(false);
    
    createHeader();
    createContent();
    createFooter();
    
    createPanel();
  }
  
  private void createPanel() {
    VerticalLayout panel = new VerticalLayout();
    panel.setSizeFull();
    panel.getStyle().set("padding", "0px");
    panel.add(header, content, footer);
    add(panel);
  }
  
  private void createHeader() {
    VDLabel titleLabel = new VDLabel();
    titleLabel.setText(this.title);
    titleLabel.setClassName("confirm-dialog-title");
    
    Icon closeIcon = VaadinIcon.CLOSE.create();
    closeIcon.setSize("14px");
    closeIcon.setColor(UIConstants.DEFAULT_RED_COLOR);
    closeIcon.addClickListener(
      e -> {
        this.handler.doReject();
        this.close();
      });
    
    Icon infoIcon = VaadinIcon.INFO_CIRCLE.create();
    infoIcon.setColor(UIConstants.DEFAULT_ICON_ENABLED_COLOR);
    infoIcon.setSize("14px");
    
    HorizontalLayout titlePart = new HorizontalLayout();
    titlePart.add(infoIcon, titleLabel);
    titlePart.setVerticalComponentAlignment(Alignment.CENTER, infoIcon, titleLabel);
    
    header = new HorizontalLayout();
    header.setHeight("50px");
    header.getStyle().set("margin-top", "4px");
    header.setWidthFull();
    header.addClassName(BoxShadowBorders.BOTTOM);
    header.setFlexGrow(1, titlePart);
    header.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, titlePart, closeIcon);
    header.add(titlePart, closeIcon);
  }
  
  private void createContent() {
    Icon alertIcon;
    if (this.option == Option.DELETE_CANCEL) {
      alertIcon = VaadinIcon.FILE_REMOVE.create();
    } else {
      alertIcon = VaadinIcon.EXCLAMATION_CIRCLE.create();
    }
    alertIcon.setColor("#FF5151");
    alertIcon.setSize("18px");
    
    HorizontalLayout imageLayout = new HorizontalLayout();
    imageLayout.add(alertIcon);
    imageLayout.setWidth("50px");
    imageLayout.setVerticalComponentAlignment(Alignment.START, alertIcon);
    imageLayout.setJustifyContentMode(JustifyContentMode.END);
    
    Div messageArea = new Div();
    messageArea.setClassName("confirm-dialog-message");
    messageArea.setText(this.message);
    
    HorizontalLayout messageLayout = new HorizontalLayout();
    messageLayout.add(messageArea);
    messageLayout.setSizeFull();
    messageLayout.setVerticalComponentAlignment(Alignment.CENTER, messageArea);
    
    HorizontalLayout bodyLayout = new HorizontalLayout();
    bodyLayout.setWidth("90%");
    bodyLayout.setHeightFull();
    bodyLayout.setVerticalComponentAlignment(Alignment.CENTER, imageLayout, messageLayout);
    bodyLayout.add(imageLayout, messageLayout);
    
    content = new VDVerticalLayout();
    content.setSizeFull();
    content.setHorizontalComponentAlignment(Alignment.CENTER, bodyLayout);
    content.add(bodyLayout);
  }
  
  private void createFooter() {
    
    VDButton okButton = new VDButton("確定");
    okButton.setClassName("confirm-ok");
    okButton.addClickListener(
      e -> {
        if (this.option == Option.DELETE_CANCEL) {
          this.handler.doConfirmDelete();
        } else {
          this.handler.doConfirm();
        }
        this.close();
      });
    
    VDButton cancelButton = new VDButton("取消");
    cancelButton.setClassName("confirm-cancel");
    cancelButton.addClickListener(
      e -> {
        this.handler.doReject();
        this.close();
      });
    
    HorizontalLayout buttonLayout = new HorizontalLayout();
    buttonLayout.setSizeFull();
    buttonLayout.getStyle().set("margin-top", "5px");
    buttonLayout.setJustifyContentMode(JustifyContentMode.CENTER);
    
    if (this.option == Option.OK) {
      buttonLayout.add(okButton);
      buttonLayout.setVerticalComponentAlignment(Alignment.CENTER, okButton);
    } else if (this.option == Option.OK_CANCEL) {
      buttonLayout.add(okButton, cancelButton);
      buttonLayout.setVerticalComponentAlignment(Alignment.CENTER, okButton, cancelButton);
    } else if (this.option == Option.DELETE_CANCEL) {
      okButton.setText("確認刪除");
      buttonLayout.add(okButton, cancelButton);
      buttonLayout.setVerticalComponentAlignment(Alignment.CENTER, okButton, cancelButton);
    }
    
    footer = new VerticalLayout();
    footer.setHeight("36px");
    footer.setWidthFull();
    footer.getStyle().set("padding", "2px");
    
    footer.setHorizontalComponentAlignment(Alignment.CENTER, buttonLayout);
    footer.setJustifyContentMode(JustifyContentMode.CENTER);
    footer.add(buttonLayout);
  }
  
  public void show() {
    this.open();
  }
  
  public enum Option {
    OK,
    CANCEL,
    OK_CANCEL,
    DELETE_CANCEL
  }
  
  public interface ConfirmHandler {
    
    void doConfirm();
    
    void doReject();
    
    default void doConfirmDelete() {
    }
  }
}
