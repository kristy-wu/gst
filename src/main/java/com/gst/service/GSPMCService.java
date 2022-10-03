package com.gst.service;

import com.dcsplab.common.DLDateUtils;
import com.gst.domain.*;
import com.gst.persistence.entity.GSCMSFA;
import com.gst.persistence.entity.GSMESTA;
import com.gst.persistence.entity.GSMOCTA;
import com.gst.persistence.key.GSCMSFA_ID;
import com.gst.persistence.key.GSMESTA_ID;
import com.gst.persistence.key.GSMOCTA_ID;
import com.gst.persistence.repo.GSCMSFARepository;
import com.gst.persistence.repo.GSMESTARepository;
import com.gst.persistence.repo.GSMOCTARepository;
import com.vaadin.flow.component.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class GSPMCService {
  private static final Logger logger = LoggerFactory.getLogger(GSPMCService.class);
  
  @Autowired
  private GSMOCTARepository manufactureOrderRepository;
  
  @Autowired
  private GSMESTARepository manufactureOrderActivityRepository;
  
  @Autowired
  private GSOrganizationService orgService;
  
  @Autowired
  private GSCMSFARepository workTypeRepository;
  
  @Value("${gst.mes.pmc.dashboard.product_line.count}")
  private String defaultDashboardItems;
  
  @Value("${gst.default.company}")
  private String defaultCompany;
  
  public String getDefaultCompany() {
    return defaultCompany;
  }
  
  public List<GSPMCManufactureOrder> getActiveManufactureOrders(String companyId) {
    List<GSPMCManufactureOrder> orderList = new ArrayList<>();
    
    String topN = "5"; // 預設顯示前五筆資料
    if (defaultDashboardItems != null) {
      try {
        topN = String.valueOf(Integer.parseInt(defaultDashboardItems));
      } catch (Exception e) {
        logger.warn(
          "properites:'gst.mes.pmc.dashboard.product_line.count' not properly set. use default value '5'");
      }
    }
    
    // List<GSMOCTA> gsmoctaList = manufactureOrderRepository.findAllOnlineOrder(companyId);
    List<GSMOCTA> gsmoctaList = manufactureOrderRepository.findAllOnlineMOCTATopN(companyId, topN);
    
    for (GSMOCTA gsmocta : gsmoctaList) {
      GSPMCManufactureOrder order = populateManufactureOrder(gsmocta);
      orderList.add(order);
    }
    
    return orderList;
  }
  
  public List<GSPMCManufactureOrder> getActiveManufactureOrders(GSDepartment productLine) {
    String companyId = productLine.getCompanyId();
    String lineId = productLine.getId();
    return getActiveManufactureOrders(companyId, lineId);
  }
  
  public List<GSPMCManufactureOrder> getActiveManufactureOrders(
    String companyId, String productLineId) {
    List<GSPMCManufactureOrder> orderList = new ArrayList<>();
    
    List<GSMOCTA> gsmoctaList =
      manufactureOrderRepository.findOnlineMOCTAByDept(companyId, productLineId);
    for (GSMOCTA gsmocta : gsmoctaList) {
      GSPMCManufactureOrder order = populateManufactureOrder(gsmocta);
      orderList.add(order);
    }
    
    return orderList;
  }
  
  public GSPMCManufactureOrder getManufactureOrder(
    String companyId, String typeId, String orderId) {
    GSMOCTA_ID id = new GSMOCTA_ID();
    id.setCompany(companyId);
    id.setTA001(typeId);
    id.setTA002(orderId);
    
    GSMOCTA mocta = null;
    if (manufactureOrderRepository.findById(id).isPresent()) {
      mocta = manufactureOrderRepository.findById(id).get();
    }
    
    return populateManufactureOrder(mocta);
  }
  
  public void updateManufactureOrder(GSMember member, GSPMCManufactureOrder m_order) {
    GSMOCTA gsmocta = persistManufactureOrder(m_order);
    gsmocta.setModifiedBy(member);
    manufactureOrderRepository.save(gsmocta);
  }
  
  public void kickoffManufactureOrder(GSMember member, GSPMCManufactureOrder m_order) {
    String productLineId = m_order.getProductLine().trim();
    String m_orderId =
      m_order.getManufactureOrderTypeId().trim() + "-" + m_order.getManufactureOrderId().trim();
    logger.info("[" + productLineId + ": " + m_orderId + "] 準備開工...");
    
    try {
      Calendar clndr = Calendar.getInstance();
      m_order.setStartTime(clndr.getTime());
      
      /* 2021/06/10  若實際開工日TA012空白，則寫入目前日期 */
      String actualStartDate = m_order.getActualStartDate();
      if (actualStartDate == null || actualStartDate.trim().isEmpty()) {
        String today = DLDateUtils.format(clndr.getTime(), DLDateUtils.Resolution.SimpleDate);
        m_order.setActualStartDate(today);
      }
      this.updateManufactureOrder(member, m_order);
      
      logger.info("[" + productLineId + ": " + m_orderId + "] 開工程序成功完成.");
      String msg = m_orderId + " 已開工.";
      Notification.show(msg, 2000, Notification.Position.MIDDLE);
    } catch (Exception e) {
      logger.error("GSMOCTA persisted object does not exist!", e);
      Notification.show(
        "GSMOCTA persisted object does not exist!", 3000, Notification.Position.MIDDLE);
    }
  }
  
  public double getManufactureOrderUnreviewedAmount(GSPMCManufactureOrder m_order) {
    return manufactureOrderActivityRepository.getUnReviewedAmount(
      m_order.getManufactureOrderTypeId().trim(), m_order.getManufactureOrderId().trim());
  }
  
  public List<GSPMCManufactureOrderActivity> getMOActivityReportsByProductLine(
    GSDepartment dept, String m_orderFilter) {
    List<GSPMCManufactureOrderActivity> reportList = new ArrayList<>();
    
    List<GSMESTA> gsmestaList =
      manufactureOrderActivityRepository.findReportByLineID(dept.getId(), m_orderFilter);
    for (GSMESTA gsmesta : gsmestaList) {
      GSPMCManufactureOrderActivity report = populateManufactureOrderActivity(gsmesta);
      reportList.add(report);
    }
    
    return reportList;
  }
  
  public List<GSPMCManufactureOrderActivity> getMOActivityReportsByProductLine(GSDepartment dept) {
    List<GSPMCManufactureOrderActivity> reportList = new ArrayList<>();
    
    List<GSMESTA> gsmestaList =
      manufactureOrderActivityRepository.findByTA001OrderByTA006Asc(dept.getId());
    for (GSMESTA gsmesta : gsmestaList) {
      GSPMCManufactureOrderActivity report = populateManufactureOrderActivity(gsmesta);
      reportList.add(report);
    }
    
    return reportList;
  }
  
  public List<GSPMCManufactureOrderActivity> getMOActivityReportsByReviewStatus(
    GSDepartment dept, String status) {
    List<GSPMCManufactureOrderActivity> reportList = new ArrayList<>();
    
    List<GSMESTA> gsmestaList =
      manufactureOrderActivityRepository.findByTA001AndTA009OrderByTA006Asc(dept.getId(), status);
    for (GSMESTA gsmesta : gsmestaList) {
      GSPMCManufactureOrderActivity report = populateManufactureOrderActivity(gsmesta);
      reportList.add(report);
    }
    
    return reportList;
  }
  
  public GSPMCManufactureOrderActivity getManufactureOrderActivityReport(
    String companyId, String deptId, String memberId, Date startTime) {
    GSMESTA_ID _id = new GSMESTA_ID();
    _id.setCompany(companyId);
    _id.setTA001(deptId);
    _id.setTA002(memberId);
    _id.setTA005(startTime);
    
    GSMESTA gsmesta = manufactureOrderActivityRepository.findById(_id).orElse(null);
    
    return populateManufactureOrderActivity(gsmesta);
  }
  
  public void updateManufactureOrderActivity(
    GSMember member, GSPMCManufactureOrderActivity activity) {
    GSMESTA gsmesta = persistManufactureOrderActivity(activity);
    gsmesta.setModifiedBy(member);
    manufactureOrderActivityRepository.save(gsmesta);
  }
  
  public void deleteManufactureOrderActivity(GSPMCManufactureOrderActivity activity) {
    GSMESTA_ID id = new GSMESTA_ID();
    id.setCompany(activity.getCompanyId());
    id.setTA001(activity.getDeptId());
    id.setTA002(activity.getMemberId());
    id.setTA005(activity.getStartTime());
    
    manufactureOrderActivityRepository.deleteById(id);
  }
  
  public GSPMCManufactureOrderActivity createManufactureOrderActivity(
    GSMember executor, GSPMCManufactureOrder m_order) {
    GSDepartment productLine =
      orgService.getDepartment(m_order.getCompanyId(), m_order.getProductLine());
    Date reportTime = productLine.getLastFinishTime();
    Date now = Calendar.getInstance().getTime();
    if (reportTime == null) {
      reportTime = Calendar.getInstance().getTime();
    }
    long diff = now.getTime() - reportTime.getTime();
    double voidTime = diff * 1.0;
    voidTime = Math.ceil(voidTime / 1000 / 60.0d);
    
    GSMESTA stock = new GSMESTA(executor);
    stock.setModifiedBy(executor);
    stock.setTA001(executor.getDeptId());
    stock.setTA002(executor.getId());
    stock.setTA003(m_order.getManufactureOrderTypeId().trim());
    stock.setTA004(m_order.getManufactureOrderId().trim());
    stock.setTA005(reportTime);
    stock.setTA006(now);
    stock.setTA007(voidTime);
    stock.setTA008(0.0);
    stock.setTA009("");
    stock.setTA010(0.0);
    stock.setTA011(0.0);
    stock.setTA503("9");
    stock.setTA504("004");
    
    // productionReportRepository.save(stock);
    
    return populateManufactureOrderActivity(stock);
  }
  
  public List<GSWorkType> getWorkTypeList(String companyId) {
    List<GSWorkType> list = new ArrayList<>();
    
    List<GSCMSFA> gscmsfaList = workTypeRepository.findByCompany(companyId);
    for (GSCMSFA gscmsfa : gscmsfaList) {
      GSWorkType workType = populateWorkType(gscmsfa);
      list.add(workType);
    }
    return list;
  }
  
  public GSWorkType getWorkType(String companyId, String typeId) {
    GSCMSFA_ID id = new GSCMSFA_ID();
    id.setCompany(companyId);
    id.setFA001(typeId);
    if (workTypeRepository.findById(id).isPresent()) {
      GSCMSFA cmsfa = workTypeRepository.findById(id).get();
      return populateWorkType(cmsfa);
    }
    return null;
  }
  
  public GSWorkType createWorkType(GSMember member) {
    GSCMSFA gscmsfa = new GSCMSFA(member);
    gscmsfa.setFA001("");
    gscmsfa.setFA002("");
    gscmsfa.setFA003("");
    return populateWorkType(gscmsfa);
  }
  
  public void updateWorkType(GSMember member, GSWorkType workType) {
    GSCMSFA gscmsfa = persistWorkType(workType);
    gscmsfa.setModifiedBy(member);
    gscmsfa.setFA001(workType.getId());
    gscmsfa.setFA002(workType.getName());
    gscmsfa.setFA003(workType.getTypeNameEng());
    
    workTypeRepository.save(gscmsfa);
  }
  
  public void deleteWorkType(GSWorkType workType) {
    GSCMSFA_ID id = new GSCMSFA_ID();
    id.setCompany(workType.getCompanyId());
    id.setFA001(workType.getId());
    workTypeRepository.deleteById(id);
  }
  
  private GSWorkType populateWorkType(GSCMSFA gscmsfa) {
    GSWorkType workType = new GSWorkType(this);
    workType.setBaseAttribute(gscmsfa);
    workType.setId(gscmsfa.getFA001());
    workType.setName(gscmsfa.getFA002());
    workType.setTypeNameEng(gscmsfa.getFA003());
    
    return workType;
  }
  
  private GSCMSFA persistWorkType(GSWorkType workType) {
    GSCMSFA gscmsfa = new GSCMSFA();
    gscmsfa.setBaseAttribute(workType);
    gscmsfa.setFA001(workType.getId());
    gscmsfa.setFA002(workType.getName());
    gscmsfa.setFA003(workType.getTypeNameEng());
    
    return gscmsfa;
  }
  
  private GSPMCManufactureOrderActivity populateManufactureOrderActivity(GSMESTA gsmesta) {
    if (gsmesta == null) {
      return null;
    }
    
    GSPMCManufactureOrderActivity report = new GSPMCManufactureOrderActivity(this);
    report.setBaseAttribute(gsmesta);
    report.setDeptId(gsmesta.getTA001());
    report.setMemberId(gsmesta.getTA002());
    report.setManufactureOrderTypeId(gsmesta.getTA003());
    report.setManufactureOrderId(gsmesta.getTA004());
    report.setStartTime(gsmesta.getTA005());
    report.setFinishTime(gsmesta.getTA006());
    report.setVoidTime(gsmesta.getTA007());
    report.setWorkingAmount(gsmesta.getTA008());
    report.setReviewStatus(gsmesta.getTA009());
    report.setWorkingHeadCount(gsmesta.getTA010());
    report.setPackAmount(gsmesta.getTA011());
    report.setDefaultLotNumber(gsmesta.getTA012());
    report.setTA501(gsmesta.getTA501());
    report.setTA502(gsmesta.getTA502());
    report.setTA503(gsmesta.getTA503());
    
    GSWorkType workType = getWorkType(gsmesta.getCompany(), gsmesta.getTA504());
    report.setWorkType(workType);
    
    report.setRelatedManufactureOrder(gsmesta.getTA490());
    
    return report;
  }
  
  private GSMESTA persistManufactureOrderActivity(GSPMCManufactureOrderActivity report) {
    GSMESTA gsmesta = new GSMESTA();
    gsmesta.setBaseAttribute(report);
    gsmesta.setFlag(report.getFlag());
    gsmesta.setTA001(report.getDeptId());
    gsmesta.setTA002(report.getMemberId());
    gsmesta.setTA003(report.getManufactureOrderTypeId());
    gsmesta.setTA004(report.getManufactureOrderId());
    gsmesta.setTA005(report.getStartTime());
    gsmesta.setTA006(report.getFinishTime());
    gsmesta.setTA007(report.getVoidTime());
    gsmesta.setTA008(report.getWorkingAmount());
    gsmesta.setTA009(report.getReviewStatus());
    gsmesta.setTA010(report.getWorkingHeadCount());
    gsmesta.setTA011(report.getPackAmount());
    gsmesta.setTA012(report.getDefaultLotNumber());
    gsmesta.setTA501(report.getTA501());
    gsmesta.setTA502(report.getTA502());
    gsmesta.setTA503(report.getTA503());
    gsmesta.setTA490(report.getRelatedManufactureOrder());
    
    GSWorkType workType = report.getWorkType();
    String wid = "";
    if (workType != null) {
      wid = workType.getId();
    }
    gsmesta.setTA504(wid);
    
    return gsmesta;
  }
  
  private GSPMCManufactureOrder populateManufactureOrder(GSMOCTA gsmocta) {
    if (gsmocta == null) {
      return null;
    }
    GSPMCManufactureOrder manufactureOrder = new GSPMCManufactureOrder(this);
    manufactureOrder.setBaseAttribute(gsmocta);
    manufactureOrder.setManufactureOrderTypeId(gsmocta.getTA001());
    manufactureOrder.setManufactureOrderId(gsmocta.getTA002());
    manufactureOrder.setOrderCreateDate(gsmocta.getTA003());
    manufactureOrder.setPartNumber(gsmocta.getTA006());
    manufactureOrder.setMaterialUnit(gsmocta.getTA007());
    manufactureOrder.setEstimateStartDate(gsmocta.getTA009());
    manufactureOrder.setEstimateFinishDate(gsmocta.getTA010());
    manufactureOrder.setStatusCode(gsmocta.getTA011());
    manufactureOrder.setActualStartDate(gsmocta.getTA012());
    manufactureOrder.setCheckCode(gsmocta.getTA013());
    manufactureOrder.setEstimateAmount(gsmocta.getTA015());
    manufactureOrder.setPickAmount(gsmocta.getTA016());
    manufactureOrder.setFinishedAmount(gsmocta.getTA017());
    manufactureOrder.setObsoleteAmount(gsmocta.getTA018());
    manufactureOrder.setInventoryLocation(gsmocta.getTA020());
    manufactureOrder.setProductLine(gsmocta.getTA021());
    manufactureOrder.setSalesOrderType(gsmocta.getTA026());
    manufactureOrder.setSalesOrderId(gsmocta.getTA027());
    manufactureOrder.setSalesOrderSeries(gsmocta.getTA028());
    manufactureOrder.setRemark(gsmocta.getTA029());
    manufactureOrder.setOutsourcingVendor(gsmocta.getTA032());
    manufactureOrder.setProductName(gsmocta.getTA034());
    manufactureOrder.setProductSpec(gsmocta.getTA035());
    manufactureOrder.setTA038(gsmocta.getTA038());
    manufactureOrder.setTA039(gsmocta.getTA039());
    manufactureOrder.setReviewDate(gsmocta.getTA040());
    manufactureOrder.setUrgent(gsmocta.isUrgent());
    manufactureOrder.setEstimatePackAmount(gsmocta.getTA045());
    manufactureOrder.setFinishedPackAmount(gsmocta.getTA046());
    manufactureOrder.setObsoletePackAmount(gsmocta.getTA047());
    manufactureOrder.setPackingUnit(gsmocta.getTA048());
    manufactureOrder.setCustomerPartNumber(gsmocta.getTA051());
    manufactureOrder.setTA063(gsmocta.getTA063());
    manufactureOrder.setCurrentStatus(gsmocta.getTA092());
    manufactureOrder.setWorkingSequence(gsmocta.getTA501());
    manufactureOrder.setLastStartTime(gsmocta.getTA502());
    manufactureOrder.setStartTime(gsmocta.getTA503());
    manufactureOrder.setTA504(gsmocta.getTA504());
    
    return manufactureOrder;
  }
  
  private GSMOCTA persistManufactureOrder(GSPMCManufactureOrder m_order) {
    GSMOCTA gsmocta = new GSMOCTA();
    gsmocta.setBaseAttribute(m_order);
    gsmocta.setTA001(m_order.getManufactureOrderTypeId());
    gsmocta.setTA002(m_order.getManufactureOrderId());
    gsmocta.setTA003(m_order.getOrderCreateDate());
    gsmocta.setTA006(m_order.getPartNumber());
    gsmocta.setTA007(m_order.getMaterialUnit());
    gsmocta.setTA009(m_order.getEstimateStartDate());
    gsmocta.setTA010(m_order.getEstimateFinishDate());
    gsmocta.setTA011(m_order.getStatusCode());
    gsmocta.setTA012(m_order.getActualStartDate());
    gsmocta.setTA013(m_order.getCheckCode());
    gsmocta.setTA014(m_order.getTA014());
    gsmocta.setTA015(m_order.getEstimateAmount());
    gsmocta.setTA016(m_order.getPickAmount());
    gsmocta.setTA017(m_order.getFinishedAmount());
    gsmocta.setTA018(m_order.getObsoleteAmount());
    gsmocta.setTA020(m_order.getInventoryLocation());
    gsmocta.setTA021(m_order.getProductLine());
    gsmocta.setTA026(m_order.getSalesOrderType());
    gsmocta.setTA027(m_order.getSalesOrderId());
    gsmocta.setTA028(m_order.getSalesOrderSeries());
    gsmocta.setTA029(m_order.getRemark());
    gsmocta.setTA032(m_order.getOutsourcingVendor());
    gsmocta.setTA034(m_order.getProductName());
    gsmocta.setTA035(m_order.getProductSpec());
    gsmocta.setTA038(m_order.getTA038());
    gsmocta.setTA039(m_order.getTA039());
    gsmocta.setTA040(m_order.getReviewDate());
    gsmocta.setTA044(m_order.isUrgent() ? "Y" : "N");
    gsmocta.setTA045(m_order.getEstimatePackAmount());
    gsmocta.setTA046(m_order.getFinishedPackAmount());
    gsmocta.setTA047(m_order.getObsoletePackAmount());
    gsmocta.setTA048(m_order.getPackingUnit());
    gsmocta.setTA051(m_order.getCustomerPartNumber());
    gsmocta.setTA063(m_order.getTA063());
    gsmocta.setTA092(m_order.getCurrentStatus());
    gsmocta.setTA501(m_order.getWorkingSequence());
    gsmocta.setTA502(m_order.getLastStartTime());
    gsmocta.setTA503(m_order.getStartTime());
    gsmocta.setTA504(m_order.getTA504());
    
    return gsmocta;
  }
}
