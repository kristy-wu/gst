package com.gst.broadcast;

import com.vaadin.flow.component.UI;

public interface GSBroadcastListener {
  
  void receiveBroadcast(UI ui, GSBroadcastMessage message);
}
