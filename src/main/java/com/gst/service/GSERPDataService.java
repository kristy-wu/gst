package com.gst.service;

import com.gst.persistence.datasource.ERPDataSource;
import com.gst.persistence.entity.GSCMSMR;
import com.gst.persistence.entity.GSINVMB;
import com.gst.persistence.repo.GSADMSYRepository;
import com.gst.persistence.repo.GSCMSMRRepository;
import com.gst.persistence.repo.GSINVMBRepository;
import com.zaxxer.hikari.HikariConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class GSERPDataService {
  private static final Logger logger = LoggerFactory.getLogger(GSERPDataService.class);
  @Autowired
  GSCMSMRRepository cmsmrRepo;

  @Autowired
  GSINVMBRepository invmbRepo;

  @Autowired
  private GSADMSYRepository settings;
  private ERPDataSource dataSource;

  private boolean ERPActivated = false;

  public GSERPDataService() throws ClassNotFoundException {
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
  }

  @PostConstruct
  private void init() {
    ERPActivated = "true".equals(settings.findBySY001("SET00006").getSY002());

    if (ERPActivated) {
      createDataSource();
    }
  }

  public boolean isERPActivated() {
    return ERPActivated;
  }

  private void createDataSource() {
    dataSource = new ERPDataSource(createConfig());
  }

  private HikariConfig createConfig() {
    String host = settings.findBySY001("SET00001").getSY002().trim();
    String port = settings.findBySY001("SET00002").getSY002().trim();
    String user = settings.findBySY001("SET00003").getSY002().trim();
    String pwd = settings.findBySY001("SET00004").getSY002().trim();
    String category = settings.findBySY001("SET00005").getSY002().trim();

    String url = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + category;

    HikariConfig dataConfig = new HikariConfig();
    dataConfig.setJdbcUrl(url);
    dataConfig.setUsername(user);
    dataConfig.setPassword(pwd);

    dataConfig.setMaximumPoolSize(50);
    dataConfig.setConnectionTimeout(5000L);

    return dataConfig;
  }

  public void transferCMSMR() throws SQLException {
    try (Connection conn = dataSource.getConnection()) {
      transferCMSMRByConn(conn);
    }
    logger.info("CMSMR data transfered.");
  }

  private void transferCMSMRByConn(Connection conn) {
    Statement stmt = null;
    ResultSet rs = null;

    try {
      String sql = "SELECT TOP 100 FLAG,MR001,MR002,MR003,MR004,MR005 " +
          "FROM CMSMR WHERE UDF01 <> 'Y' ";

      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        int flag = rs.getInt("FLAG");
        String MR001 = rs.getString("MR001").trim();
        String MR002 = rs.getString("MR002").trim();
        String MR003 = rs.getString("MR003") == null ? "" : rs.getString("MR003").trim();
        String MR004 = rs.getString("MR004") == null ? "" : rs.getString("MR004").trim();
        String MR005 = rs.getString("MR005") == null ? "" : rs.getString("MR005").trim();

        GSCMSMR cmsmr = new GSCMSMR();
        cmsmr.setCompany("MME001");
        cmsmr.setFlag(flag);
        cmsmr.setMR001(MR001);
        cmsmr.setMR002(MR002);
        cmsmr.setMR003(MR003);
        cmsmr.setMR004(MR004);
        cmsmr.setMR005(MR005);

        cmsmrRepo.save(cmsmr);

        sql = "UPDATE CMSMR SET UDF01 = 'Y' WHERE MR001='" + MR001 + "' AND MR002 = '" + MR002 + "'";
        Statement updateStmt = conn.createStatement();
        int ret = updateStmt.executeUpdate(sql);
        if (ret != 1) {
          logger.error("ERP: error update CMSMR for MR001-MR002: " + MR001 + "-" + MR002);
        }
        updateStmt.close();
      }
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException se) {
        logger.error(se.getMessage(), se);
      }
    }
  }

  public void transferINVMB() throws SQLException {
    long start = System.currentTimeMillis();
    try (Connection conn = dataSource.getConnection()) {
      transferINVMBByConn(conn);
    }
    long end = System.currentTimeMillis();

    long elapsed = end - start;
    logger.info("100 rows of INVMB data transfered in " + elapsed + " ms.");
  }

  private void transferINVMBByConn(Connection conn) {
    Statement stmt = null;
    ResultSet rs = null;
    try {
      String sql = "SELECT TOP 100 FLAG,MB001,MB002,MB003,MB004,MB005," +
          "MB006,MB007,MB008,MB009,MB030,MB031,MB032,MB048,MB049,MB050 " +
          "FROM INVMB WHERE UDF01 <> 'Y' ";

      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        int flag = rs.getInt("FLAG");
        String MB001 = rs.getString("MB001").trim();
        String MB002 = rs.getString("MB002").trim();
        String MB003 = rs.getString("MB003") == null ? "" : rs.getString("MB003").trim();
        String MB004 = rs.getString("MB004") == null ? "" : rs.getString("MB004").trim();
        String MB005 = rs.getString("MB005") == null ? "" : rs.getString("MB005").trim();
        String MB006 = rs.getString("MB006") == null ? "" : rs.getString("MB006").trim();
        String MB007 = rs.getString("MB007") == null ? "" : rs.getString("MB007").trim();
        String MB008 = rs.getString("MB008") == null ? "" : rs.getString("MB008").trim();
        String MB009 = rs.getString("MB009") == null ? "" : rs.getString("MB009").trim();
        String MB030 = rs.getString("MB030") == null ? "" : rs.getString("MB030").trim();
        String MB031 = rs.getString("MB031") == null ? "" : rs.getString("MB031").trim();
        String MB032 = rs.getString("MB032") == null ? "" : rs.getString("MB032").trim();
        String MB048 = rs.getString("MB048") == null ? "" : rs.getString("MB048").trim();
        String MB049 = rs.getString("MB049") == null ? "" : rs.getString("MB049").trim();
        String MB050 = rs.getString("MB050") == null ? "" : rs.getString("MB050").trim();

        GSINVMB invmb = new GSINVMB();
        invmb.setCompany("MME001");
        invmb.setFlag(flag);
        invmb.setMB001(MB001);
        invmb.setMB002(MB002);
        invmb.setMB003(MB003);
        invmb.setMB004(MB004);
        invmb.setMB005(MB005);
        invmb.setMB006(MB006);
        invmb.setMB007(MB007);
        invmb.setMB008(MB008);
        invmb.setMB009(MB009);
        invmb.setMB030(MB030);
        invmb.setMB031(MB031);
        invmb.setMB032(MB032);
        invmb.setMB048(MB048);
        invmb.setMB049(MB049);
        invmb.setMB050(MB050);

        invmbRepo.save(invmb);

        sql = "UPDATE INVMB SET UDF01 = 'Y' WHERE MB001='" + MB001 + "'";

        Statement updateStmt = conn.createStatement();
        int ret = updateStmt.executeUpdate(sql);
        if (ret != 1) {
          logger.error("ERP: error update INVMB for MB001: " + MB001);
        }
        updateStmt.close();
      }
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException se) {
        logger.error(se.getMessage(), se);
      }
    }
  }
}
