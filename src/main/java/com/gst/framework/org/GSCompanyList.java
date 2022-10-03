package com.gst.framework.org;

import com.dcsplab.vdui.layout.VDFlexBoxLayout;
import com.dcsplab.vdui.layout.size.Horizontal;
import com.dcsplab.vdui.layout.size.Vertical;
import com.dcsplab.vdui.util.css.BoxSizing;
import com.gst.broadcast.GSBroadcastListener;
import com.gst.broadcast.GSBroadcastMessage;
import com.gst.context.GSContext;
import com.gst.domain.GSAuthorization.Action;
import com.gst.domain.GSMember;
import com.gst.framework.components.GSAccessDeniedScreen;
import com.gst.framework.components.GSCommonViewFrame;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.framework.components.GSUnderConstruction;
import com.gst.framework.layout.GSMainLayout;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/*
 * 公司基本資料維護 CMSI01
 */
@Route(value = "org/company", layout = GSMainLayout.class)
public class GSCompanyList extends GSCommonViewFrame
  implements GSFormChangeHandler, GSBroadcastListener {
  
  public static final String HANDLER_ID = "CMSI01";
  private static final long serialVersionUID = -5485783780572634995L;
  private static final Logger logger = LoggerFactory.getLogger(GSCompanyList.class);
  private Registration resizeListener;
  
  private boolean hasAccess = false;
  
  public GSCompanyList() {
    setTitleKey("公司基本資料維護");
    setId("gst-company-view");
  }
  
  @PostConstruct
  private void init() {
    GSMember member = GSContext.getCurrentMember();
    hasAccess = member.hasAccess(HANDLER_ID, Action.Search);
    if (hasAccess) {
      setViewContent(createContent());
    } else {
      GSAccessDeniedScreen deniedScreen = new GSAccessDeniedScreen();
      setViewContent(deniedScreen);
    }
  }
  
  private Component createContent() {
    GSUnderConstruction uc = new GSUnderConstruction();
    
    VDFlexBoxLayout content = new VDFlexBoxLayout(uc);
    content.setFlexDirection(FlexDirection.COLUMN);
    content.setBoxSizing(BoxSizing.BORDER_BOX);
    content.setHeight("98%");
    content.setMargin(Horizontal.S, Vertical.S, Horizontal.S, Horizontal.S);
    return content;
  }
  
  @Override
  protected void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);
    getUI()
      .ifPresent(
        ui -> {
          if (hasAccess) {
            Page page = ui.getPage();
            resizeListener =
              page.addBrowserWindowResizeListener(
                event -> updateVisibleColumns(event.getWidth()));
            page.retrieveExtendedClientDetails(
              details -> updateVisibleColumns(details.getBodyClientWidth()));
          }
        });
  }
  
  @Override
  protected void onDetach(DetachEvent detachEvent) {
    if (hasAccess) {
      resizeListener.remove();
      resizeListener = null;
    }
    
    super.onDetach(detachEvent);
  }
  
  @Override
  public void receiveBroadcast(UI ui, GSBroadcastMessage message) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void onChange() {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public String getHandlerID() {
    return HANDLER_ID;
  }
}
