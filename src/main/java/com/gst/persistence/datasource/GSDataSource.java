package com.gst.persistence.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class GSDataSource {
  
  public static final String ID = "id";
  public static final String NAME = "name";
  public static final String URL = "URL";
  public static final String USER = "user";
  public static final String PASSWORD = "password";
  public static final String MAX_POOL_SIZE = "maxsize";
  public static final String MIN_POOL_SIZE = "minsize";
  private static final Logger logger = LoggerFactory.getLogger(GSDataSource.class);
  private String id;
  
  private String name;
  
  private HikariConfig hikariConfig;
  
  private HikariDataSource ds;
  
  private GSDataSource(HashMap<String, String> settings) {
    prepare(settings);
    prepareDataSource();
  }
  
  public static GSDataSource create(HashMap<String, String> settings) {
    return new GSDataSource(settings);
  }
  
  private void prepareDataSource() {
    ds = new HikariDataSource(hikariConfig);
  }
  
  private void prepare(HashMap<String, String> settings) {
    hikariConfig = new HikariConfig();
    
    this.id = settings.get(ID);
    this.name = settings.get(NAME);
    logger.info("creating datasource: [" + id + "] [" + name + "]");
    
    hikariConfig.setJdbcUrl(settings.get(URL));
    logger.info("  " + name + " url: " + settings.get(URL));
    
    hikariConfig.setUsername(settings.get(USER));
    logger.info("  " + name + " user: " + settings.get(USER));
    hikariConfig.setPassword(settings.get(PASSWORD));
    
    hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
    hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
    hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
  }
  
  public void close() {
    ds.close();
  }
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
}
