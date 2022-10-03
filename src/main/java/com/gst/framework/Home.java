package com.gst.framework;

import com.dcsplab.vdui.layout.VDFlexBoxLayout;
import com.dcsplab.vdui.layout.size.Horizontal;
import com.dcsplab.vdui.layout.size.Right;
import com.dcsplab.vdui.layout.size.Uniform;
import com.dcsplab.vdui.util.UIUtils;
import com.gst.framework.layout.GSMainLayout;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexWrap;
import com.vaadin.flow.router.Route;

// @PageTitle("Welcome")
@Route(value = "notused", layout = GSMainLayout.class)
public class Home extends GSAbstractViewFrame {
  
  private static final long serialVersionUID = 5943558401418617486L;
  
  public Home() {
    setTitleKey("gst.app.Navigator.Home.Title");
    setId("home");
    setViewContent(createContent());
  }
  
  private Component createContent() {
    Html intro =
      new Html(
        "<p>A responsive application template with some dummy data. Loosely based on "
          + "the <b>responsive layout grid</b> guidelines set by "
          + "<a href=\"https://material.io/design/layout/responsive-layout-grid.html\">Material Design</a>. "
          + "Utilises the <a href=\"https://vaadin.com/themes/lumo\">Lumo</a> theme.</p>");
    
    Html productivity =
      new Html(
        "<p>The starter gives you a productivity boost and a head start. "
          + "You get an app shell with a typical hierarchical left-hand menu. The shell, the views and the "
          + "components are all responsive and touch friendly, which makes them great for desktop and mobile"
          + "use. The views are built with Java, which enhances Java developers' productivity by allowing them to"
          + "do all in one language.</p>");
    
    Html features =
      new Html(
        "<p>The app comes with multiple list views to edit master-detail data. "
          + "Views can be divided horizontally or vertically to open up the details, and the details can "
          + "also be split into multiple tabs for extra space. The details can also be opened fullscreen "
          + "to maximize the use of space. Additionally there is an opt-in option for opening multiple "
          + "application views in tabs within the app, for quick comparison or navigation between data. "
          + "You enable this feature by setting <code>MainLayout.navigationTabs</code> to true.</p>");
    
    Anchor documentation =
      new Anchor(
        "https://vaadin.com/docs/business-app/overview.html",
        UIUtils.createButton("Read the documentation", VaadinIcon.EXTERNAL_LINK));
    Anchor starter =
      new Anchor(
        "https://vaadin.com/start/latest/business-app",
        UIUtils.createButton(
          "Start a new project with Business App", VaadinIcon.EXTERNAL_LINK));
    
    VDFlexBoxLayout links = new VDFlexBoxLayout(documentation, starter);
    links.setFlexWrap(FlexWrap.WRAP);
    links.setSpacing(Right.S);
    
    VDFlexBoxLayout content = new VDFlexBoxLayout(intro, productivity, features, links);
    content.setFlexDirection(FlexDirection.COLUMN);
    content.setMargin(Horizontal.AUTO);
    content.setMaxWidth("840px");
    content.setPadding(Uniform.RESPONSIVE_L);
    return content;
  }
  
  @Override
  protected void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);
    GSMainLayout.get().getAppBar().reset();
  }
  
  @Override
  public void updateVisibleColumns(int width) {
    // TODO Auto-generated method stub
    
  }
}
