package com.gst.domain;

import com.gst.context.GSAbstractObject;
import com.gst.service.GSBulletinService;

import java.util.List;

public class GSBulletinClass extends GSAbstractObject {
  
  private static final long serialVersionUID = -7065386035105042765L;
  private final GSBulletinService manager;
  
  public GSBulletinClass(GSBulletinService manager) {
    this.manager = manager;
  }
  
  public List<GSBulletinItem> getItems() {
    return this.manager.listBulletinItems(getCompanyId(), getId().trim());
  }
}
