package com.dcsplab.vdui.components;

import com.vaadin.flow.component.html.Label;

public class VDLabel extends Label {
  
  private static final long serialVersionUID = -3582411936642103531L;
  
  public VDLabel() {
    super();
  }
  
  public VDLabel(String label) {
    setText(label);
  }
  
  public void setFontSize(int sz) {
    getStyle().set("font-size", sz + "px");
  }
}
