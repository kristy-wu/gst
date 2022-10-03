package com.dcsplab.vdui.layout;

import com.dcsplab.vdui.util.css.BoxSizing;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexLayout;

public class VDLayoutContainer extends VDFlexBoxLayout {
  private static final long serialVersionUID = -3216295584971277182L;
  
  public VDLayoutContainer() {
    super();
    setFlexDirection(FlexLayout.FlexDirection.COLUMN);
    setBoxSizing(BoxSizing.BORDER_BOX);
    setSizeFull();
  }
  
  public VDLayoutContainer(Component... components) {
    super(components);
    setFlexDirection(FlexLayout.FlexDirection.COLUMN);
    setBoxSizing(BoxSizing.BORDER_BOX);
    setSizeFull();
  }
}
