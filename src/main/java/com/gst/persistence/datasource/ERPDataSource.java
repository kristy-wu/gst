package com.gst.persistence.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ERPDataSource extends HikariDataSource {
  private static final Logger logger = LoggerFactory.getLogger(ERPDataSource.class);

  public ERPDataSource(HikariConfig config) {
    super(config);
  }
}
