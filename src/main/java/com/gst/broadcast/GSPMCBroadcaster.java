package com.gst.broadcast;

import com.vaadin.flow.component.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GSPMCBroadcaster {
  
  private static final Map<UI, GSBroadcastListener> listeners = new HashMap<>();
  
  private static final Logger logger = LoggerFactory.getLogger(GSPMCBroadcaster.class);
  
  static ExecutorService executorService = Executors.newSingleThreadExecutor();
  
  public static synchronized void register(UI ui, GSBroadcastListener listener) {
    listeners.put(ui, listener);
    
    String uiid = "undefined";
    if (ui.getId().isPresent()) {
      uiid = ui.getId().get();
    }
    
    logger.info("Registered UI[" + uiid + "] broadcast. Current listeners: " + listeners.size());
  }
  
  public static synchronized void unregister(UI ui) {
    if (ui == null) {
      return;
    }
    
    String uiid = "undefined";
    if (ui.getId().isPresent()) {
      uiid = ui.getId().get();
    }
    
    listeners.remove(ui);
    logger.info("Unregistered UI[" + uiid + "] broadcast. Current listeners: " + listeners.size());
  }
  
  public static synchronized void broadcast(UI source, final GSBroadcastMessage message) {
    for (final Map.Entry<UI, GSBroadcastListener> entry : listeners.entrySet()) {
      executorService.execute(
        () -> {
          UI key = entry.getKey();
          if (key == source || key.isClosing() || !key.isEnabled()) {
            return;
          }
          entry.getValue().receiveBroadcast(entry.getKey(), message);
        });
    }
  }
}
