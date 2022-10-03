package com.gst.framework.components;

import com.gst.framework.GSAbstractViewFrame;
import com.gst.framework.layout.GSMainLayout;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.dependency.CssImport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A view frame that establishes app design guidelines. It consists of three parts:
 *
 * <ul>
 *   <li>Topmost {@link #setViewHeader(Component...) header}
 *   <li>Center {@link #setViewContent(Component...) content}
 *   <li>Bottom {@link #setViewFooter(Component...) footer}
 * </ul>
 */
@CssImport("./styles/components/view-frame.css")
public class GSCommonViewFrame extends GSAbstractViewFrame {
  
  private static final long serialVersionUID = 451276050280614925L;
  
  static Logger logger = LoggerFactory.getLogger(GSCommonViewFrame.class);
  
  public GSCommonViewFrame() {
  }
  
  @Override
  protected void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);
    GSMainLayout.get().getAppBar().reset();
  }
  
  @Override
  public void updateVisibleColumns(int width) {
    /*
    //boolean mobile = width < MOBILE_BREAKPOINT;
    List<Grid.Column<GSMOCTA>> columns = grid.getColumns();

    // "Mobile" column
    //columns.get(0).setVisible(mobile);

    // "Desktop" columns
    for (int i = 1; i < columns.size(); i++) {
    	columns.get(i).setVisible(true);
    }
    */
  
    // default do nothing;
  }
  
  @Override
  protected void onDetach(DetachEvent detachEvent) {
    super.onDetach(detachEvent);
  }
}
