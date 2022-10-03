package com.dcsplab.vdui.components;

import com.vaadin.flow.component.timepicker.TimePicker;

public class VDTimePicker extends TimePicker {
  
  private static final long serialVersionUID = 7617841572711796738L;
  
  public VDTimePicker() {
    this("");
  }
  
  public VDTimePicker(String text) {
    super(text);
  }
}
