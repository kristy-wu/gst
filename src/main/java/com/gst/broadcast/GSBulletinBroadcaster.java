package com.gst.broadcast;

import com.gst.domain.GSBulletinItem;
import com.gst.service.GSBulletinService;
import com.vaadin.flow.component.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GSBulletinBroadcaster {
  
  private static final Map<UI, GSBroadcastListener> listeners = new HashMap<>();
  
  private static final Logger logger = LoggerFactory.getLogger(GSBulletinBroadcaster.class);
  static ExecutorService executorService = Executors.newSingleThreadExecutor();
  private static boolean stopPush = false;
  private static boolean pushStarted = false;
  
  public static synchronized void register(UI ui, GSBroadcastListener listener) {
    listeners.put(ui, listener);
    /*
    logger.info(
            "Registered UI["
                    + framework.getId().get()
                    + "] broadcast. Current listeners: "
                    + listeners.size());*/
  }
  
  public static synchronized void unregister(UI ui) {
    listeners.remove(ui);
    /*
    logger.info(
            "Unregistered UI["
                    + framework.getId().get()
                    + "] broadcast. Current listeners: "
                    + listeners.size());*/
  }
  
  public static synchronized void broadcast(UI source, final GSBroadcastMessage message) {
    Iterator<Map.Entry<UI, GSBroadcastListener>> entryIter = listeners.entrySet().iterator();
    while (entryIter.hasNext()) {
      Map.Entry<UI, GSBroadcastListener> entry = entryIter.next();
      UI key = entry.getKey();
      GSBroadcastListener listener = entry.getValue();
      if (key == null || listener == null || key.getSession() == null) {
        entryIter.remove();
      }
    }
    
    for (final Map.Entry<UI, GSBroadcastListener> entry : listeners.entrySet()) {
      executorService.execute(
        () -> {
          UI key = entry.getKey();
          if (key == null) {
            return;
          }
          if (key == source || key.isClosing() || !key.isEnabled()) {
            return;
          }
          entry.getValue().receiveBroadcast(entry.getKey(), message);
        });
    }
  }
  
  public static synchronized void stopPush() {
    stopPush = true;
    pushStarted = false;
  }
  
  public static synchronized void pushBulletin(GSBulletinService manager) {
    if (pushStarted) {
      return;
    }
    pushStarted = true;
    stopPush = false;
    
    ExecutorService pushService = Executors.newSingleThreadExecutor();
    pushService.execute(
      () -> {
        while (!stopPush) {
            /*if(manager==null) {
              logger.warn("*********************** bulletin service is null. ************************");
              return;
            }*/
          List<GSBulletinItem> items = manager.getLatestItems(manager.getDefaultCompany(), 5);
          if (items.size() < 1) {
            try {
              Thread.sleep(60000);
            } catch (Exception ignore) {
            
            }
          }
          
          for (GSBulletinItem item : items) {
            GSBroadcastMessage message = new GSBroadcastMessage();
            message.setMessage(item.getContent());
            broadcast(null, message);
            
            try {
              Thread.sleep(15000);
            } catch (Exception ignore) {
            
            }
          }
        }
        
        System.out.println("bulletin push stoped.");
      });
  }
}
