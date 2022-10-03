package com.gst.security;

import com.dcsplab.security.DLSecurityUtils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;

@Component
public class GSConfigureUIServiceInitListener implements VaadinServiceInitListener {
  
  private static final long serialVersionUID = 1049380850454604514L;
  
  @Override
  public void serviceInit(ServiceInitEvent event) {
    event
      .getSource()
      .addUIInitListener(
        uiEvent -> {
          final UI ui = uiEvent.getUI();
          ui.addBeforeEnterListener(this::authenticateNavigation);
        });
  }
  
  private void authenticateNavigation(BeforeEnterEvent event) {
    if (!GSLoginView.class.equals(event.getNavigationTarget())
      && !DLSecurityUtils.isUserLoggedIn()) {
      event.rerouteTo(GSLoginView.class);
    }
  }
}
