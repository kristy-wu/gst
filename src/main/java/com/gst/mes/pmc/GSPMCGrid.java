package com.gst.mes.pmc;

import com.dcsplab.vdui.components.VDGrid;
import com.gst.domain.GSPMCManufactureOrder;
import com.vaadin.flow.component.Component;

public class GSPMCGrid extends VDGrid<GSPMCManufactureOrder> {
  
  private static final long serialVersionUID = -4078276907420944499L;
  
  private final PMCGridType gridType;
  
  public GSPMCGrid() {
    this(PMCGridType.Dashboard);
  }
  
  public GSPMCGrid(PMCGridType type) {
    super();
    gridType = type;
    
    initFields();
  }
  
  private void initFields() {
    
    addComponentColumn(this::createTA001Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("單別")
      .setSortable(true)
      .setFrozen(true);
    
    addComponentColumn(this::createTA002Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("單號")
      .setSortable(true)
      .setFrozen(true);
    
    addComponentColumn(this::createTA021Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("生產單位")
      .setSortable(true)
      .setFrozen(true);
    
    addComponentColumn(this::createTA006Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("產品品號")
      .setSortable(true)
      .setFrozen(true);
    
    addComponentColumn(this::createTA034Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("品名")
      .setSortable(true)
      .setFrozen(true);
    
    addComponentColumn(this::createTA035Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("規格")
      .setSortable(true)
      .setFrozen(true);
    
    // 顯示預計產量及單位
    addComponentColumn(this::createTA015Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("預計產量")
      .setSortable(true);
    
    // 顯示包裝數量及單位
    addComponentColumn(this::createTA045Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("包裝數量")
      .setSortable(true);
    
    // 顯示已生產數量及單位
    addComponentColumn(this::createTA017Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("已生產數量")
      .setSortable(true);
    
    addComponentColumn(this::createTA009Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("預計開工日")
      .setSortable(true);
    
    addComponentColumn(this::createTA010Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("預計完工日")
      .setSortable(true);
    
    addComponentColumn(this::createTA012Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("實際開工日")
      .setSortable(true);
    
    addComponentColumn(this::createTA501Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("生產順序")
      .setSortable(true);
    
    addComponentColumn(this::createTA502Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("前次加工時間")
      .setSortable(true);
    
    addComponentColumn(this::createTA503Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("目前開工時間")
      .setSortable(true);
    
    addComponentColumn(this::createTA092Display)
      .setResizable(true)
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setHeader("目前生產狀態")
      .setSortable(true);
  }
  
  private Component createTA001Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA001Display(data);
  }
  
  private Component createTA002Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA002Display(data);
  }
  
  private Component createTA021Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA021Display(data);
  }
  
  private Component createTA006Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA006Display(data);
  }
  
  private Component createTA034Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA034Display(data);
  }
  
  private Component createTA035Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA035Display(data);
  }
  
  private Component createTA009Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA009Display(data);
  }
  
  private Component createTA010Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA010Display(data);
  }
  
  private Component createTA012Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA012Display(data);
  }
  
  private Component createTA015Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA015Display(data);
  }
  
  private Component createTA017Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA017Display(data);
  }
  
  private Component createTA045Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA045Display(data);
  }
  
  private Component createTA501Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA501Display(data);
  }
  
  private Component createTA502Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA502Display(data);
  }
  
  private Component createTA503Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA503Display(data);
  }
  
  private Component createTA092Display(GSPMCManufactureOrder data) {
    return GSPMCGridHelper.getTA092Display(data);
  }
  
  public enum PMCGridType {
    Dashboard,
    Inbound,
    Review,
    Scheduler
  }
}
