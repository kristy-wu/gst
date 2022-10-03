package com.gst.framework;

import com.dcsplab.vdui.layout.VDFlexBoxLayout;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.i18n.I18NProvider;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.server.VaadinService;

import java.util.Locale;

/**
 * A view frame that establishes app design guidelines. It consists of four parts:
 *
 * <ul>
 *   <li>Topmost {@link #setViewHeader(Component...) header}
 *   <li>Center {@link #setViewContent(Component...) content}
 *   <li>Center {@link #setViewDetails(Component...) details}
 *   <li>Bottom {@link #setViewFooter(Component...) footer}
 * </ul>
 */
@CssImport("./styles/components/view-frame.css")
public abstract class GSAbstractViewFrame extends Composite<Div>
  implements HasStyle, HasDynamicTitle {
  
  private static final long serialVersionUID = -6447713848820036091L;
  
  private final Div header;
  
  private final VDFlexBoxLayout wrapper;
  
  private final Div content;
  
  private final Div details;
  
  private final Div footer;
  
  private String title_key;
  
  public GSAbstractViewFrame() {
    String CLASS_NAME = "view-frame";
    setClassName(CLASS_NAME);
    
    header = new Div();
    header.setClassName(CLASS_NAME + "__header");
    
    wrapper = new VDFlexBoxLayout();
    wrapper.setClassName(CLASS_NAME + "__wrapper");
    
    content = new Div();
    content.setClassName(CLASS_NAME + "__content");
    
    details = new Div();
    details.setClassName(CLASS_NAME + "__details");
    
    footer = new Div();
    footer.setClassName(CLASS_NAME + "__footer");
    
    wrapper.add(content, details);
    getContent().add(header, wrapper, footer);
  }
  
  public void setTitleKey(String key) {
    title_key = key;
  }
  
  /**
   * Sets the header slot's components.
   */
  public void setViewHeader(Component... components) {
    header.removeAll();
    header.add(components);
  }
  
  /**
   * Sets the content slot's components.
   */
  public void setViewContent(Component... components) {
    content.removeAll();
    content.add(components);
  }
  
  /**
   * Sets the detail slot's components.
   */
  public void setViewDetails(Component... components) {
    details.removeAll();
    details.add(components);
  }
  
  public void setViewDetailsPosition(Position position) {
    if (position.equals(Position.RIGHT) || position.equals(Position.CENTER)) {
      wrapper.setFlexDirection(FlexDirection.ROW);
      
    } else if (position.equals(Position.BOTTOM)) {
      wrapper.setFlexDirection(FlexDirection.COLUMN);
    }
  }
  
  /**
   * Sets the footer slot's components.
   */
  public void setViewFooter(Component... components) {
    footer.removeAll();
    footer.add(components);
  }
  
  @Override
  protected void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);
  }
  
  @Override
  public String getPageTitle() {
    final I18NProvider i18NProvider =
      VaadinService.getCurrent().getInstantiator().getI18NProvider();
    
    UI current = UI.getCurrent();
    Locale locale = current.getLocale();
    Object[] o = new Object[0];
    return i18NProvider.getTranslation(title_key, locale, o);
  }
  
  public abstract void updateVisibleColumns(int width);
  
  public enum Position {
    RIGHT,
    BOTTOM,
    CENTER
  }
}
