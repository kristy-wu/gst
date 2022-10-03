package com.dcsplab.vdui.navigation.drawer;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;

import java.util.List;
import java.util.stream.Collectors;

@CssImport("./styles/components/navi-menu.css")
public class VDNavigationMenu extends Div {
  
  private static final long serialVersionUID = 2513297371129509832L;
  
  private final String CLASS_NAME = "navi-menu";
  
  public VDNavigationMenu() {
    setClassName(CLASS_NAME);
  }
  
  protected void addNaviItem(VDNavigationItem item) {
    add(item);
  }
  
  protected void addNaviItem(VDNavigationItem parent, VDNavigationItem item) {
    parent.addSubItem(item);
    addNaviItem(item);
  }
  
  public void filter(String filter) {
    getNaviItems()
      .forEach(
        naviItem -> {
          boolean matches = naviItem.getText().toLowerCase().contains(filter.toLowerCase());
          naviItem.setVisible(matches);
        });
  }
  
  public VDNavigationItem addNaviItem(String text, Class<? extends Component> navigationTarget) {
    VDNavigationItem item = new VDNavigationItem(text, navigationTarget);
    addNaviItem(item);
    return item;
  }
  
  public VDNavigationItem addNaviItem(
    VaadinIcon icon, String text, Class<? extends Component> navigationTarget) {
    VDNavigationItem item = new VDNavigationItem(icon, text, navigationTarget);
    addNaviItem(item);
    return item;
  }
  
  public VDNavigationItem addNaviItem(
    Image image, String text, Class<? extends Component> navigationTarget) {
    VDNavigationItem item = new VDNavigationItem(image, text, navigationTarget);
    addNaviItem(item);
    return item;
  }
  
  public VDNavigationItem addNaviItem(
    VDNavigationItem parent,
    VaadinIcon icon,
    String text,
    Class<? extends Component> navigationTarget) {
    VDNavigationItem item = new VDNavigationItem(parent, icon, text, navigationTarget);
    addNaviItem(parent, item);
    return item;
  }
  
  public VDNavigationItem addNaviItem(
    VDNavigationItem parent, String text, Class<? extends Component> navigationTarget) {
    VDNavigationItem item = new VDNavigationItem(text, navigationTarget);
    addNaviItem(parent, item);
    return item;
  }
  
  public List<VDNavigationItem> getNaviItems() {
    return (List<VDNavigationItem>) (List) getChildren().collect(Collectors.toList());
  }
}
