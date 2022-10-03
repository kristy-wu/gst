package com.gst.framework.admin;

import com.dcsplab.vdui.UIConstants;
import com.dcsplab.vdui.components.VDCheckboxGroup;
import com.dcsplab.vdui.components.VDGrid;
import com.dcsplab.vdui.components.VDLabel;
import com.dcsplab.vdui.components.VDTextField;
import com.gst.context.GSContext;
import com.gst.domain.GSModule;
import com.gst.domain.GSRole;
import com.gst.domain.GSRoleAccess;
import com.gst.domain.GSSystemFunction;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.service.GSAuthorizationService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class GSModuleSelectRole extends GSDetailFormBase {
  
  private static final long serialVersionUID = 8285081353781830583L;
  
  private static final Logger logger = LoggerFactory.getLogger(GSModuleSelectRole.class);
  
  VDCheckboxGroup<String> checkboxGroup;
  private VDGrid<GSRole> grid;
  private VDTextField hiddenAccessString;
  
  public GSModuleSelectRole() {
    setHideBasicInfo(true);
    init();
  }
  
  private void init() {
    hiddenAccessString = new VDTextField();
    
    VDLabel textLabel = new VDLabel("權限: ");
    
    checkboxGroup = new VDCheckboxGroup<>();
    checkboxGroup.getStyle().set("font-size", "12px");
    Set<String> items = new LinkedHashSet<>(Arrays.asList(UIConstants.ACCESS_LIST));
    checkboxGroup.setItems(items);
    checkboxGroup.addValueChangeListener(
      event -> {
        Set<String> eValues = event.getValue();
        StringBuilder access = new StringBuilder();
        for (int i = 0; i < UIConstants.ACCESS_LIST.length; i++) {
          String key = UIConstants.ACCESS_LIST[i];
          
          boolean matched = false;
          for (String val : eValues) {
            if (val.equals(key)) {
              matched = true;
              break;
            }
          }
          
          if (matched) {
            access.append('Y');
          } else {
            access.append('N');
          }
        }
        hiddenAccessString.setValue(access.toString());
      });
    
    HorizontalLayout accessLayout = new HorizontalLayout();
    accessLayout.add(textLabel, checkboxGroup);
    // accessLayout.add(textLabel, checkboxGroup, hiddenAccessString);
    add(accessLayout);
    createGrid();
  }
  
  private void createGrid() {
    grid = new VDGrid<>();
    grid.setId("gst-admin-roleaccess-grid");
    grid.setSelectionMode(SelectionMode.MULTI);
    
    grid.addComponentColumn(this::createRoleDisplay)
      .setHeader("角色")
      .setWidth("200px")
      .setFlexGrow(0)
      .setSortable(false);
    
    add(grid);
  }
  
  private Collection<GSRole> getDataList() {
    GSAuthorizationService authService = GSContext.getApplication().getAuthorizationService();
    return authService.getRoleList(GSContext.getCurrentMember().getCompanyId());
  }
  
  private void refreshGrid(boolean reload) {
    if (reload) {
      grid.setItems(getDataList());
    } else {
      grid.refresh();
    }
  }
  
  private Component createRoleDisplay(GSRole role) {
    
    VDLabel roleLabel = new VDLabel(role.getName());
    roleLabel.getStyle().set("font-size", "13px");
    return roleLabel;
  }
  
  @Override
  public String getDetailTitle() {
    return getHeader();
  }
  
  @Override
  public void setChangeHandler(GSFormChangeHandler h) {
  }
  
  @Override
  public void onSave() {
    Set<GSRole> roles = grid.getSelectedItems();
    String accessSetting = this.hiddenAccessString.getValue();
    
    GSModule module = (GSModule) getDetail();
    List<GSSystemFunction> functions = module.getFunctionList();
    
    for (GSRole role : roles) {
      logger.info("賦予模組[" + module.getName() + "]程式權限至角色[" + role.getName() + "]");
      
      List<GSRoleAccess> newAccessList = new ArrayList<>();
      for (GSSystemFunction func : functions) {
        GSRoleAccess roleAccess = null;
        try {
          roleAccess = GSRoleAccess.transition(role, func);
          roleAccess.populateAccess(accessSetting);
          newAccessList.add(roleAccess);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      role.addAccess(newAccessList);
    }
  }
  
  @Override
  public void onCancel() {
  }
  
  @Override
  public void onClose() {
  }
  
  @Override
  public void onDelete() {
  }
  
  public boolean validate() {
    boolean pass = true;
    
    Set<String> items = checkboxGroup.getSelectedItems();
    Set<GSRole> roles = grid.getSelectedItems();
    
    if (items.size() < 1) {
      pass = false;
      Notification.show("請設定模組權限!", 3000, Position.MIDDLE);
    } else if (roles.size() < 1) {
      pass = false;
      Notification.show("請選擇角色!", 3000, Position.MIDDLE);
    }
    
    return pass;
  }
  
  @Override
  public boolean isValueChanged() {
    return false;
  }
  
  @Override
  public void populateDetail() {
    GSModule detail = (GSModule) getDetail();
    String header = "指派角色權限: " + detail.getId() + " - " + detail.getName();
    setHeader(header);
    
    refreshGrid(true);
  }
}
