package com.dcsplab.vdui.components;

import com.vaadin.flow.component.datepicker.DatePicker;

import java.time.LocalDate;

public class VDDatePicker extends DatePicker {
  
  private static final long serialVersionUID = -5806425066193462140L;
  
  public VDDatePicker() {
    this("");
  }
  
  public VDDatePicker(String text) {
    super(text);
  }
  
  public VDDatePicker(String text, LocalDate date) {
    super(text, date);
  }
}
