package com.gst;

import com.gst.persistence.datasource.GSDataSource;
import com.vaadin.flow.server.*;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URL;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

@VaadinServletConfiguration(heartbeatInterval = 1, productionMode = true, closeIdleSessions = true)
@WebServlet
@WebListener
public class GSTApplicationServlet extends VaadinServlet
  implements ServletContextListener, SessionInitListener, SessionDestroyListener {
  private static final long serialVersionUID = 8814756020844010715L;
  private static final Logger logger = LoggerFactory.getLogger(GSTApplicationServlet.class);
  
  public static final String SERVER_RECONNECT_ALERT = "伺服器連線中斷, 嘗試重新連線...";
  
  private static final String DATASOURCE_CONFIG = "dbconfig.xml";
  private static final byte[] lock = new byte[0];
  
  private static HashMap<String, WrappedSession> userSessions;
  private final ArrayList<GSDataSource> datasources = new ArrayList<>();
  
  public static Object getUserSession(String userId) {
    return userSessions.get(userId);
  }
  
  public static void setUserSession(String userId) {
    synchronized (lock) {
      if (userSessions == null) {
        userSessions = new HashMap<>();
      }
      
      VaadinSession vaadinSession = VaadinSession.getCurrent();
      WrappedSession session = vaadinSession.getSession();

      /*
      WrappedSession session = VaadinService.getCurrentRequest()
      		.getWrappedSession();*/
      userSessions.put(userId, session);
    }
  }
  
  public static void invalidateUser(String userId) {
    synchronized (lock) {
      if (userSessions == null) {
        return;
      }
      
      WrappedSession session = userSessions.remove(userId);
      if (session != null) {
        session.invalidate();
      }
    }
  }
  
  private Document loadConfig() throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();
    URL resource = classLoader.getResource(DATASOURCE_CONFIG);
    
    File settings = new File(resource.getFile());
    if (!settings.exists() || !settings.isFile()) {
      throw new Exception("config file '" + DATASOURCE_CONFIG + "' does not exist!");
    }
    
    DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    Document doc = dBuilder.parse(settings);
    doc.getDocumentElement().normalize();
    
    return doc;
  }
  
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContextListener.super.contextInitialized(sce);
    
    logger.info("preparing datasource...");
    
    try {
      Document config = loadConfig();
      NodeList nodes = config.getElementsByTagName("DataSource");
      int count = nodes.getLength();
      for (int i = 0; i < count; i++) {
        Node dsNode = nodes.item(i);
        Element dsElement = (Element) dsNode;
        String id = dsElement.getAttribute("id");
        String name = dsElement.getAttribute("name");
        
        String open = dsElement.getElementsByTagName("Open").item(0).getTextContent();
        if (!"true".equalsIgnoreCase(open)) {
          continue;
        }
        
        String url = dsElement.getElementsByTagName("URL").item(0).getTextContent();
        String user = dsElement.getElementsByTagName("User").item(0).getTextContent();
        String pwd = dsElement.getElementsByTagName("Password").item(0).getTextContent();
        
        HashMap<String, String> settings = new HashMap<>();
        settings.put(GSDataSource.ID, id);
        settings.put(GSDataSource.NAME, name);
        settings.put(GSDataSource.URL, url);
        settings.put(GSDataSource.USER, user);
        settings.put(GSDataSource.PASSWORD, pwd);
        
        GSDataSource ds = GSDataSource.create(settings);
        datasources.add(ds);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    logger.info("preparing datasource finished...");
  }
  
  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    logger.info("Shutting down context...");

    logger.info("Shutting down scheduled jobs...");
    GSTBootApplication app = GSTBootApplication.get();
    try {
      app.shutdownScheduler();
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
    
    if (datasources.size() > 0) {
      logger.info("Shutting down GST App...");
      for (GSDataSource ds : datasources) {
        ds.close();
        logger.info("datasource [" + ds.getName() + "] closed.");
      }
    }
    
    // deregister the jdbc drivers
    // This manually deregisters JDBC driver,
    // which prevents Tomcat 7 from complaining about memory leaks wrto this class
    Enumeration<Driver> drivers = DriverManager.getDrivers();
    while (drivers.hasMoreElements()) {
      Driver driver = drivers.nextElement();
      try {
        DriverManager.deregisterDriver(driver);
        logger.info(String.format("deregistering jdbc driver: %s", driver));
      } catch (SQLException e) {
        logger.error(String.format("Error deregistering driver %s", driver), e);
      }
    }
    logger.info("Context shutdown completed.");

    ServletContextListener.super.contextDestroyed(sce);
  }
  
  @Override
  protected void servletInitialized() throws ServletException {
    super.servletInitialized();
    logger.info("initializing VaadinServlet...");
    getService().addSessionInitListener(this);
    getService().addSessionDestroyListener(this);
  }
  
  @Override
  public void sessionDestroy(SessionDestroyEvent event) {
    logger.info("session destroyed...");
  }
  
  @Override
  public void sessionInit(SessionInitEvent event) throws ServiceException {
    event.getSession().getSession().setMaxInactiveInterval(60);
    logger.info("session initialized...");
  }
}
