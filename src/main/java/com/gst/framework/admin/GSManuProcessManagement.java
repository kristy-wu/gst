package com.gst.framework.admin;

/*
  製程資料建立作業
 */

import com.gst.broadcast.GSBroadcastListener;
import com.gst.broadcast.GSBroadcastMessage;
import com.gst.framework.components.GSCommonViewFrame;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.framework.layout.GSMainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route(value = "pas/procm", layout = GSMainLayout.class)
public class GSManuProcessManagement extends GSCommonViewFrame
    implements GSFormChangeHandler, GSBroadcastListener {
  private static final long serialVersionUID = -7825670211296142410L;

  private static final Logger logger = LoggerFactory.getLogger(GSManuProcessManagement.class);

  public static final String TITLE = "製程資料建立作業";

  public GSManuProcessManagement() {
    setTitleKey(TITLE);
    setId("gst-pas-processmanagement");
  }

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
