package com.gst.framework.components;

import com.vaadin.flow.component.icon.Icon;

public interface GSDetailRepresentation {
  
  String getDetailTitle();
  
  Icon getDetailHeaderIcon();
  
  void setChangeHandler(GSFormChangeHandler handler);
  
  void onSave();
  
  void onCancel();
  
  void onClose();
  
  void onDelete();
  
  default boolean validate() {
    return true;
  }
  
  boolean isValueChanged();
}
