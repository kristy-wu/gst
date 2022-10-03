package com.gst.mes.pmc;

import com.gst.broadcast.GSBroadcastListener;
import com.gst.broadcast.GSBroadcastMessage;
import com.gst.framework.components.GSCommonViewFrame;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.framework.layout.GSMainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;

/*
 * 製令維護作業 PMCD09
 */
@Route(value = "pmc/linemod", layout = GSMainLayout.class)
public class GSPMCProductLineManagement extends GSCommonViewFrame
  implements GSFormChangeHandler, GSBroadcastListener {
  
  private static final long serialVersionUID = 8223391467925740923L;
  
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
