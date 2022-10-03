package com.gst.mes.pmc;

import com.gst.broadcast.GSBroadcastListener;
import com.gst.broadcast.GSBroadcastMessage;
import com.gst.framework.components.GSCommonViewFrame;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.framework.layout.GSMainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;

/*
 * 品保生產入庫報表 PMCD06
 */
@Route(value = "pmc/qareport", layout = GSMainLayout.class)
public class GSPMCQAReport extends GSCommonViewFrame
  implements GSFormChangeHandler, GSBroadcastListener {
  
  private static final long serialVersionUID = -1585249447422483333L;
  
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
