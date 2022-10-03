package com.gst.mes.pmc;

import com.gst.context.GSContext;
import com.gst.domain.GSMember;
import com.gst.domain.GSPMCManufactureOrder;
import com.gst.service.GSPMCService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public final class GSPMCProductLineControl {
  
  public static final Double PMC_REPORTED_SEQUENCE = 999d; // 製令報工後順序
  
  public static final Double PMC_INCOMING_SEQUENCE = 888d; // 新製令順序
  
  // public static final String company = "MME001";
  
  private static final byte[] _lock_ = new byte[0];
  
  private static final ConcurrentHashMap<String, Boolean> lineStatusMap = new ConcurrentHashMap<>();
  
  private static final Logger logger = LoggerFactory.getLogger(GSPMCProductLineControl.class);
  
  private static GSPMCProductLineControl _instance_ = null;
  
  private final GSPMCService PMCService;
  
  private boolean intialized = false;
  
  private GSPMCProductLineControl() {
    PMCService = GSContext.getApplication().getPCMService();
  }
  
  private static GSPMCProductLineControl getInstance() {
    return _instance_;
  }
  
  public static void refresh() {
    GSPMCProductLineControl ins = getInstance();
    ins.createProductLineStatusMap();
  }
  
  public static void resetProductLine(String lineId) {
    GSPMCProductLineControl ins = getInstance();
    ins.resetSingleLine(lineId);
  }
  
  public static void resetSequence(String lineId) {
    GSPMCProductLineControl ins = getInstance();
    ins._resetSequence(lineId);
  }
  
  public static void init() {
    if (_instance_ == null) {
      _instance_ = new GSPMCProductLineControl();
      _instance_.createProductLineStatusMap();
    }
  }
  
  public static PMCLineStatus addLock(String lineId) {
    return _instance_.lock(lineId);
  }
  
  public static void releaseLock(String lineId) {
    _instance_.unlock(lineId);
  }
  
  private void createProductLineStatusMap() {
    synchronized (_lock_) {
      String companyId = PMCService.getDefaultCompany();
      logger.info("Refresh [" + companyId + "] PMC product line status cache...");
      lineStatusMap.clear();
      
      List<GSPMCManufactureOrder> activeOrders = PMCService.getActiveManufactureOrders(companyId);
      for (GSPMCManufactureOrder order : activeOrders) {
        Date lineStart = order.getStartTime();
        String productLine = order.getProductLine().trim();
        
        Boolean available = Boolean.TRUE;
        if (lineStart != null) {
          available = Boolean.FALSE;
        }
        lineStatusMap.put(productLine, available);
      }
      
      intialized = true;
    }
  }
  
  private void resetSingleLine(String productLine) {
    synchronized (_lock_) {
      logger.info("Refresh PMC product line[" + productLine + "] cache...");
      
      try {
        List<GSPMCManufactureOrder> activeOrders =
          PMCService.getActiveManufactureOrders(
            PMCService.getDefaultCompany(), productLine.trim());
        boolean available = Boolean.TRUE;
        for (GSPMCManufactureOrder order : activeOrders) {
          Date lineStart = order.getStartTime();
          if (lineStart != null) {
            available = Boolean.FALSE;
            break;
          }
        }
        lineStatusMap.put(productLine.trim(), available);
      } catch (Exception e) {
        logger.info("Refresh PMC product line[" + productLine + "] cache failed.", e);
      }
    }
  }
  
  private void _resetSequence(String productLine) {
    try {
      GSMember contextUser = GSContext.getCurrentMember();
      List<GSPMCManufactureOrder> activeOrders =
        PMCService.getActiveManufactureOrders(PMCService.getDefaultCompany(), productLine.trim());
      
      Iterator<GSPMCManufactureOrder> iter = activeOrders.iterator();
      double d = 1;
      while (iter.hasNext()) {
        GSPMCManufactureOrder order = iter.next();
        Double sequence = order.getWorkingSequence();
        if (sequence.intValue() >= PMC_INCOMING_SEQUENCE) {
          break;
        }
        order.setWorkingSequence(d++);
        PMCService.updateManufactureOrder(contextUser, order);
      }
    } catch (Exception e) {
      logger.info("Refresh PMC product line[" + productLine + "] sequence failed.", e);
    }
  }
  
  private PMCLineStatus lock(String lineId) {
    if (!intialized) {
      logger.info("PMC product line controller is not properly initialized!");
      return PMCLineStatus.HasLock;
    }
    
    synchronized (_lock_) {
      Boolean status = lineStatusMap.get(lineId);
      logger.info("PMC product line '" + lineId + "' status: " + status);
      if (status == Boolean.FALSE) {
        return PMCLineStatus.HasLock;
      } else {
        lineStatusMap.put(lineId, Boolean.FALSE);
        return PMCLineStatus.Locked;
      }
    }
  }
  
  private void unlock(String lineId) {
    if (!intialized) {
      return;
    }
    
    synchronized (_lock_) {
      lineStatusMap.put(lineId, Boolean.TRUE);
    }
  }
  
  public enum PMCLineStatus {
    HasLock,
    Locked,
    Free
  }
}
