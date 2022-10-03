package com.gst.mes.pmc;

import com.gst.broadcast.GSBroadcastListener;
import com.gst.broadcast.GSBroadcastMessage;
import com.gst.framework.components.GSCommonViewFrame;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.framework.layout.GSMainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;

/*
 * 生管製令開立作業 PMCD08
 */
@Route(value = "pmc/workorder", layout = GSMainLayout.class)
public class GSPMCWorkOrder extends GSCommonViewFrame
  implements GSFormChangeHandler, GSBroadcastListener {
  
  private static final long serialVersionUID = -4179673586191429499L;
  
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
