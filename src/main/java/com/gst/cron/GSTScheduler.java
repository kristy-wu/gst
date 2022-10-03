package com.gst.cron;

import com.gst.service.GSERPDataService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Vector;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Component
public class GSTScheduler {
  public static final String GST_JOB_GROUP = "GST_JOB_GROUP";
  public static final String JOB_DETAIL_KEY_TARGET = "TARGET";

  public static final String JOB_DETAIL_KEY_ERPSERVICE = "DataService";

  private static final Logger logger = LoggerFactory.getLogger(GSTScheduler.class);

  private final Scheduler scheduler;

  private final Vector<JobDetail> jobs;

  private final Vector<Trigger> triggers;

  @Autowired
  private GSERPDataService erpService;

  public GSTScheduler() throws SchedulerException {
    SchedulerFactory schedFactory = new StdSchedulerFactory();
    scheduler = schedFactory.getScheduler();

    jobs = new Vector<>();
    triggers = new Vector<>();
  }

  public void start() throws SchedulerException {
    for (int i = 0; i < jobs.size(); i++) {
      scheduler.scheduleJob(jobs.get(i), triggers.get(i));
    }

    scheduler.start();
  }

  public void stop() throws SchedulerException {
    scheduler.shutdown(true);
  }

  public void buildJobs() {
    createCMSMRJob();
    createINVMBJob();
  }

  private void createCMSMRJob() {
    if(!erpService.isERPActivated()) {
      logger.info("ERP Connection is not activated, abort CMSMR schedule job creation.");
      return;
    }

    JobDetail job =
        newJob(GSTVirtualJob.class)
            .withIdentity("VirtualJob_" + GSTVirtualJob.ERP_CMSMR_IMPORT, GST_JOB_GROUP)
            .build();
    JobDataMap jobData = job.getJobDataMap();
    jobData.put(JOB_DETAIL_KEY_TARGET, GSTVirtualJob.ERP_CMSMR_IMPORT);
    jobData.put(JOB_DETAIL_KEY_ERPSERVICE, erpService);
    jobs.add(job);

    Trigger trigger =
        newTrigger()
            .withIdentity(
                "VirtualJobTrigger_" + GSTVirtualJob.ERP_CMSMR_IMPORT, GST_JOB_GROUP)
            .withSchedule(cronSchedule("3/5 * * * * ?"))
            .forJob("VirtualJob_" + GSTVirtualJob.ERP_CMSMR_IMPORT, GST_JOB_GROUP)
            .build();
    triggers.add(trigger);

    logger.info(
        "Job: VirtualJob_"
            + GSTVirtualJob.ERP_CMSMR_IMPORT
            + " with period '5 sec' created.");
  }

  private void createINVMBJob() {
    if(!erpService.isERPActivated()) {
      logger.info("ERP Connection is not activated, abort INVMB schedule job creation.");
      return;
    }

    JobDetail job =
        newJob(GSTVirtualJob.class)
            .withIdentity("VirtualJob_" + GSTVirtualJob.ERP_INVMB_IMPORT, GST_JOB_GROUP)
            .build();
    JobDataMap jobData = job.getJobDataMap();
    jobData.put(JOB_DETAIL_KEY_TARGET, GSTVirtualJob.ERP_INVMB_IMPORT);
    jobData.put(JOB_DETAIL_KEY_ERPSERVICE, erpService);
    jobs.add(job);

    Trigger trigger =
        newTrigger()
            .withIdentity(
                "VirtualJobTrigger_" + GSTVirtualJob.ERP_INVMB_IMPORT, GST_JOB_GROUP)
            .withSchedule(cronSchedule("17/5 * * * * ?"))
            .forJob("VirtualJob_" + GSTVirtualJob.ERP_INVMB_IMPORT, GST_JOB_GROUP)
            .build();
    triggers.add(trigger);

    logger.info(
        "Job: VirtualJob_"
            + GSTVirtualJob.ERP_INVMB_IMPORT
            + " with period '5 sec' created.");
  }
}
