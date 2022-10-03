package com.gst.cron;

import com.gst.service.GSERPDataService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import static com.gst.cron.GSTScheduler.JOB_DETAIL_KEY_ERPSERVICE;
import static com.gst.cron.GSTScheduler.JOB_DETAIL_KEY_TARGET;

public class GSTVirtualJob implements Job {
  public static final String ERP_CMSMR_IMPORT = "ERP_CMSMR_IMPORT";
  public static final String ERP_INVMB_IMPORT = "ERP_INVMB_IMPORT";
  private static final Logger logger = LoggerFactory.getLogger(GSTVirtualJob.class);

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    JobKey key = context.getJobDetail().getKey();
    JobDataMap dataMap = context.getJobDetail().getJobDataMap();

    GSERPDataService erpService = (GSERPDataService) dataMap.get(JOB_DETAIL_KEY_ERPSERVICE);
    String target = (String) dataMap.get(JOB_DETAIL_KEY_TARGET);

    if (ERP_CMSMR_IMPORT.equals(target)) {
      importCMSMR(erpService);
    } else if (ERP_INVMB_IMPORT.equals(target)) {
      importINVMB(erpService);
    }

  }

  private void importCMSMR(GSERPDataService service) {
    try {
      service.transferCMSMR();
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }
    //System.out.println("executing job: importCMSMR...");
  }

  private void importINVMB(GSERPDataService service) {
    try {
      service.transferINVMB();
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    }
    //System.out.println("executing job: importINVMB...");
  }
}
