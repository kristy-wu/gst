package com.dcsplab.vdui.navigation.drawer;

import com.dcsplab.vdui.util.UIUtils;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

import java.util.ArrayList;
import java.util.List;

@CssImport("./styles/components/navi-item.css")
public class VDNavigationItem extends Div {
  
  private static final long serialVersionUID = 1127174290374674682L;
  
  private final String CLASS_NAME = "navi-item";
  
  private final Component link;
  
  private final Class<? extends Component> navigationTarget;
  
  private final String text;
  
  private final List<VDNavigationItem> subItems;
  protected Button expandCollapse;
  private int level = 0;
  private boolean subItemsVisible;
  
  public VDNavigationItem(
    VaadinIcon icon, String text, Class<? extends Component> navigationTarget) {
    this(text, navigationTarget);
    link.getElement().insertChild(0, new Icon(icon).getElement());
  }
  
  public VDNavigationItem(Image image, String text, Class<? extends Component> navigationTarget) {
    this(text, navigationTarget);
    link.getElement().insertChild(0, image.getElement());
  }
  
  public VDNavigationItem(
    VDNavigationItem item,
    VaadinIcon icon,
    String text,
    Class<? extends Component> navigationTarget) {
    this(text, navigationTarget);
    link.getElement().insertChild(0, new Icon(icon).getElement());
  }
  
  public VDNavigationItem(String text, Class<? extends Component> navigationTarget) {
    setClassName(CLASS_NAME);
    setLevel(0);
    
    this.text = text;
    this.navigationTarget = navigationTarget;
    
    if (navigationTarget != null) {
      RouterLink routerLink = new RouterLink(null, navigationTarget);
      routerLink.add(new Span(text));
      routerLink.setClassName(CLASS_NAME + "__link");
      routerLink.setHighlightCondition(HighlightConditions.sameLocation());
      this.link = routerLink;
      
    } else {
      Div div = new Div(new Span(text));
      div.addClickListener(e -> expandCollapse.click());
      div.setClassName(CLASS_NAME + "__link");
      this.link = div;
    }
    
    expandCollapse =
      UIUtils.createButton(
        VaadinIcon.MINUS_SQUARE_O, ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
    expandCollapse.addClickListener(event -> setSubItemsVisible(!subItemsVisible));
    expandCollapse.setVisible(false);
    
    subItems = new ArrayList<>();
    subItemsVisible = true;
    updateAriaLabel();
    
    add(expandCollapse, link);
  }
  
  private void updateAriaLabel() {
    String action = (subItemsVisible ? "Collapse " : "Expand ") + text;
    UIUtils.setAriaLabel(action, expandCollapse);
  }
  
  public boolean isHighlighted(AfterNavigationEvent e) {
    return link instanceof RouterLink
      && ((RouterLink) link).getHighlightCondition().shouldHighlight((RouterLink) link, e);
  }
  
  public int getLevel() {
    return level;
  }
  
  public void setLevel(int level) {
    this.level = level;
    if (level > 0) {
      getElement().setAttribute("level", Integer.toString(level));
    }
  }
  
  public Class<? extends Component> getNavigationTarget() {
    return navigationTarget;
  }
  
  public void addSubItem(VDNavigationItem item) {
    if (!expandCollapse.isVisible()) {
      expandCollapse.setVisible(true);
    }
    item.setLevel(getLevel() + 1);
    subItems.add(item);
  }
  
  private void setSubItemsVisible(boolean visible) {
    if (level == 0) {
      // expandCollapse.setIcon(new Icon(visible ? VaadinIcon.CARET_UP : VaadinIcon.CARET_DOWN));
      expandCollapse.setIcon(
        new Icon(visible ? VaadinIcon.MINUS_SQUARE_O : VaadinIcon.PLUS_SQUARE_O));
    }
    subItems.forEach(item -> item.setVisible(visible));
    subItemsVisible = visible;
    updateAriaLabel();
  }
  
  public String getText() {
    return text;
  }
  
  @Override
  public void setVisible(boolean visible) {
    super.setVisible(visible);
    
    // If true, we only update the icon. If false, we hide all the sub items
    if (visible) {
      if (level == 0) {
        // expandCollapse.setIcon(new Icon(VaadinIcon.CARET_DOWN));
        expandCollapse.setIcon(new Icon(VaadinIcon.PLUS_SQUARE_O));
      }
    } else {
      setSubItemsVisible(false);
    }
  }
}
