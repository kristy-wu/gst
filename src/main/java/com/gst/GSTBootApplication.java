package com.gst;

import com.gst.cron.GSTScheduler;
import com.gst.service.GSAuthorizationService;
import com.gst.service.GSBulletinService;
import com.gst.service.GSOrganizationService;
import com.gst.service.GSPMCService;
import com.vaadin.flow.server.VaadinServlet;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.Objects;

/*
 * Traditional Deployment instead of running as an application
 * has to provide a SpringBootServletInitializer subclass
 * and override its configure method
 *
 * refer to: https://docs.spring.io/spring-boot/docs/current/reference \
 *             /html/howto.html#howto-traditional-deployment
 *
 */

@EnableTransactionManagement
@SpringBootApplication
@ComponentScan({"com.dcsplab", "com.gst"})
@PropertySource(value = {"classpath:gstSystem.properties"}, ignoreResourceNotFound = true, encoding = "UTF-8")
//@EnableAutoConfiguration(exclude = RedisAutoConfiguration.class)
public class GSTBootApplication extends SpringBootServletInitializer {
  private static final Logger logger = LoggerFactory.getLogger(GSTBootApplication.class);

  @Autowired
  private GSOrganizationService orgService;

  @Autowired
  private GSAuthorizationService authService;

  @Autowired
  private GSBulletinService bulletinService;

  @Autowired
  private GSPMCService PMCService;

  @Autowired
  private GSTScheduler scheduler;

  public static void main(String[] args) {
    System.setProperty("server.servlet.context-path", "/gst");
    SpringApplication.run(GSTBootApplication.class, args);
  }

  public static GSTBootApplication get() {
    return Objects.requireNonNull(WebApplicationContextUtils.getWebApplicationContext(
            VaadinServlet.getCurrent().getServletContext()))
        .getBean(GSTBootApplication.class);
  }

  public GSBulletinService getBulletinService() {
    return bulletinService;
  }

  public GSOrganizationService getOrganizationService() {
    return orgService;
  }

  public GSAuthorizationService getAuthorizationService() {
    return authService;
  }

  public GSPMCService getPCMService() {
    return PMCService;
  }

  @Bean
  public CommandLineRunner initScheduler() throws Exception {
    return (args) -> {
      logger.info("Initializing GST scheduler...");
      scheduler.buildJobs();
      scheduler.start();
      logger.info("GST scheduler initialized.");
    };
  }

  public void shutdownScheduler() throws SchedulerException {
    scheduler.stop();
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(GSTBootApplication.class);
  }

  @Bean
  public HttpSessionEventPublisher httpSessionEventPublisher() {
    return new HttpSessionEventPublisher();
  }
}
