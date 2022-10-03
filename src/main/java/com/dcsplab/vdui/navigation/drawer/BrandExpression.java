package com.dcsplab.vdui.navigation.drawer;

import com.dcsplab.vdui.util.UIUtils;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;

@CssImport("./styles/components/brand-expression.css")
public class BrandExpression extends Div {
  
  private static final long serialVersionUID = -2292728949256966425L;
  
  private static final String CLASS_NAME = "brand-expression";
  
  public BrandExpression(String text) {
    setClassName(CLASS_NAME);
    
    Image logo = new Image(UIUtils.IMG_PATH + "logos/18.png", "");
    logo.setAlt(text + " logo");
    logo.setClassName(CLASS_NAME + "__logo");
    
    // VerticalLayout brandLayout = new VerticalLayout();
    Label title = UIUtils.createH3Label(text);
    title.addClassName(CLASS_NAME + "__title");

    /*
    subtitle = UIUtils.createLabel(FontSize.S, "Administrator");
    brandLayout.add(title, subtitle);
    */
    
    add(logo, title);
  }
}
