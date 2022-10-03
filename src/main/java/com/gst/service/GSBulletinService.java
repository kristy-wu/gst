package com.gst.service;

import com.gst.domain.GSBulletinClass;
import com.gst.domain.GSBulletinItem;
import com.gst.domain.GSMember;
import com.gst.persistence.entity.GSCMSDA;
import com.gst.persistence.entity.GSCMSEA;
import com.gst.persistence.repo.GSCMSDARepository;
import com.gst.persistence.repo.GSCMSEARepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

// @Component
@Service
public class GSBulletinService {
  private static final Logger logger = LoggerFactory.getLogger(GSBulletinService.class);
  
  @Autowired
  private GSCMSDARepository bulletinClassRepository;
  
  @Autowired
  private GSCMSEARepository bulletinItemRepository;
  
  @Value("${gst.default.company}")
  private String defaultCompany;
  
  public String getDefaultCompany() {
    return defaultCompany;
  }
  
  public List<GSBulletinClass> listBulletinBoards(String companyId) {
    List<GSBulletinClass> list = new ArrayList<>();
    
    List<GSCMSDA> cmsdaList = bulletinClassRepository.findByCompanyOrderByDA001(companyId);
    for (GSCMSDA cmsda : cmsdaList) {
      GSBulletinClass board = populateBulletinBoard(cmsda);
      list.add(board);
    }
    
    return list;
  }
  
  public GSBulletinClass getDefaultBoard(String companyId) {
    GSCMSDA cmsda = bulletinClassRepository.findDefaultBoard(companyId);
    return populateBulletinBoard(cmsda);
  }
  
  public GSBulletinClass createBulletinClass(GSMember member) {
    GSCMSDA data = new GSCMSDA(member);
    data.setDA001(null);
    data.setDA002("新建公佈欄");
    
    return populateBulletinBoard(data);
  }
  
  public void updateBulletinClass(GSMember member, GSBulletinClass bulletinClass) {
    GSCMSDA data = persistBulletinClass(bulletinClass);
    data.setModifiedBy(member);
    bulletinClassRepository.save(data);
  }
  
  private GSCMSDA persistBulletinClass(GSBulletinClass clazz) {
    GSCMSDA gscmsda = new GSCMSDA();
    gscmsda.setBaseAttribute(clazz);
    gscmsda.setDA001(clazz.getId());
    gscmsda.setDA002(clazz.getName());
    return gscmsda;
  }
  
  public boolean existsBulletinClassName(String name) {
    GSCMSDA data = bulletinClassRepository.findByDA002(name);
    return data != null;
  }
  
  public void deleteBulletinClass(GSBulletinClass bulletinClass) {
    GSCMSDA data = bulletinClassRepository.findByDA001(bulletinClass.getId());
    bulletinClassRepository.delete(data);
  }
  
  private GSBulletinClass populateBulletinBoard(GSCMSDA cmsda) {
    GSBulletinClass board = new GSBulletinClass(this);
    board.setBaseAttribute(cmsda);
    board.setId(cmsda.getDA001());
    board.setName(cmsda.getDA002());
    
    return board;
  }
  
  @Cacheable(value = "gs_bulletin_item", keyGenerator = "gsBulletinItemListKeyGenerator")
  public List<GSBulletinItem> listBulletinItems(String company, String classId) {
    List<GSBulletinItem> itemList = new ArrayList<>();
    
    List<GSCMSEA> list =
      bulletinItemRepository.findByCompanyAndEA001OrderByEA002Desc(company, classId);
    System.out.println("******************* list bulletin items from db...");
    for (GSCMSEA cmsea : list) {
      GSBulletinItem item = populateBulletinItem(cmsea);
      itemList.add(item);
    }
    return itemList;
  }
  
  @Cacheable(value = "gs_bulletin_item_latest", keyGenerator = "gsBulletinItemListKeyGenerator")
  public List<GSBulletinItem> getLatestItems(String companyId, int count) {
    if (count < 5) {
      count = 5;
    }
    if (count > 10) {
      count = 10;
    }
    Date today = Calendar.getInstance().getTime();
    List<GSCMSEA> items = bulletinItemRepository.findLatest(companyId, count, today);
    
    List<GSBulletinItem> list = new ArrayList<>();
    for (GSCMSEA cmsea : items) {
      GSBulletinItem item = populateBulletinItem(cmsea);
      list.add(item);
    }
    return list;
  }
  
  public GSBulletinItem createBulletinItem(GSMember member, GSBulletinClass clazz) {
    GSCMSEA cmsea = new GSCMSEA(member);
    cmsea.setEA001(clazz.getId());
    cmsea.setEA002("");
    cmsea.setEA003(Calendar.getInstance().getTime());
    cmsea.setEA004(Calendar.getInstance().getTime());
    cmsea.setEA005("");
    
    return populateBulletinItem(cmsea);
  }
  
  @CacheEvict(
    value = {"gs_bulletin_item", "gs_bulletin_item_latest"},
    allEntries = true)
  public void updateBulletinItem(GSMember member, GSBulletinItem item) {
    GSCMSEA cmsea = persistBulletinItem(item);
    cmsea.setModifiedBy(member);
    bulletinItemRepository.save(cmsea);
  }
  
  public void deleteBulletinItem(GSBulletinItem item) {
    GSCMSEA persist = persistBulletinItem(item);
    bulletinItemRepository.delete(persist);
  }
  
  private GSBulletinItem populateBulletinItem(GSCMSEA data) {
    GSBulletinItem item = new GSBulletinItem(this);
    item.setBaseAttribute(data);
    item.setClassId(data.getEA001());
    item.setId(data.getEA002());
    item.setName(data.getEA002());
    item.setStartDate(data.getEA003());
    item.setEndDate(data.getEA004());
    item.setContent(data.getEA005());
    
    return item;
  }
  
  private GSCMSEA persistBulletinItem(GSBulletinItem item) {
    GSCMSEA cmsea = new GSCMSEA();
    cmsea.setBaseAttribute(item);
    cmsea.setEA001(item.getClassId());
    cmsea.setEA002(item.getId());
    cmsea.setEA003(item.getStartDate());
    cmsea.setEA004(item.getEndDate());
    cmsea.setEA005(item.getContent());
    
    return cmsea;
  }
}
