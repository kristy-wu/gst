package com.dcsplab.vdui.util;

public enum LineHeight {
  XS("var(--lumo-line-height-xs)"),
  S("var(--lumo-line-height-s)"),
  M("var(--lumo-line-height-m)");
  
  private final String value;
  
  LineHeight(String value) {
    this.value = value;
  }
  
  public String getValue() {
    return value;
  }
}
