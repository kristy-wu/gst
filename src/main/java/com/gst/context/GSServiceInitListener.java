package com.gst.context;

import com.dcsplab.i18n.DLi18nProvider;
import com.gst.broadcast.GSBulletinBroadcaster;
import com.gst.mes.pmc.GSPMCProductLineControl;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GSServiceInitListener implements VaadinServiceInitListener {
  private static final Logger logger = LoggerFactory.getLogger(GSServiceInitListener.class);
  private static final long serialVersionUID = -955881795547738998L;
  
  @Override
  public void serviceInit(ServiceInitEvent event) {
    System.setProperty("vaadin.i18n.provider", DLi18nProvider.class.getName());
    
    System.out.println("**************** Starting GST-MES Service *****************");
    
    logger.info("init PMC Production Line Control...");
    GSPMCProductLineControl.init();
    
    VaadinService service = event.getSource();
    service.addServiceDestroyListener(
      e -> {
        GSBulletinBroadcaster.stopPush();
        System.out.println("**************** GST-MES service is terminated. *****************");
      });
    
    System.out.println("**************** GST-MES Service Started *****************");
  }
}
