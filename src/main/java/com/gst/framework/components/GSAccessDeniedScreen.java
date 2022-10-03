package com.gst.framework.components;

import com.dcsplab.vdui.components.VDLabel;
import com.dcsplab.vdui.layout.VDFlexBoxLayout;
import com.dcsplab.vdui.layout.size.Horizontal;
import com.dcsplab.vdui.util.css.BoxSizing;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class GSAccessDeniedScreen extends VDFlexBoxLayout {
  
  private static final long serialVersionUID = -6995591143315245402L;
  
  public GSAccessDeniedScreen() {
    showMessage();
  }
  
  private void showMessage() {
    Div div = new Div();
    VDLabel message = new VDLabel("無存取權限!");
    message.getStyle().set("font-size", "18px");
    // Html message = new Html("<p>無存取權限!</p>");
    div.add(message);
    
    VerticalLayout V1 = new VerticalLayout();
    V1.add(div);
    V1.setWidthFull();
    V1.setHorizontalComponentAlignment(Alignment.CENTER, div);
    
    HorizontalLayout H1 = new HorizontalLayout();
    H1.add(V1);
    H1.setHeightFull();
    H1.setVerticalComponentAlignment(Alignment.CENTER, V1);
    
    VDFlexBoxLayout content = new VDFlexBoxLayout(H1);
    content.setFlexDirection(FlexDirection.COLUMN);
    content.setBoxSizing(BoxSizing.BORDER_BOX);
    content.setSizeFull();
    content.setMargin(Horizontal.XS);
    
    add(content);
  }
}
