package com.gst.mes.pmc;

import com.gst.broadcast.GSBroadcastListener;
import com.gst.broadcast.GSBroadcastMessage;
import com.gst.framework.components.GSCommonViewFrame;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.framework.layout.GSMainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;

/*
 * 工時統計報表 PMCD07
 */
@Route(value = "pmc/workreport", layout = GSMainLayout.class)
public class GSPMCWorkHourReport extends GSCommonViewFrame
  implements GSFormChangeHandler, GSBroadcastListener {
  
  private static final long serialVersionUID = -6689436430915087011L;
  
  @Override
  public void receiveBroadcast(UI ui, GSBroadcastMessage message) {
    
  }
  
  @Override
  public void onChange() {
    
  }
  
  @Override
  public String getHandlerID() {
    return null;
  }
}
