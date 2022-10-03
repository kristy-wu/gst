package com.gst.framework.admin;

import com.dcsplab.vdui.UIConstants;
import com.dcsplab.vdui.components.VDButton;
import com.dcsplab.vdui.components.VDCheckboxGroup;
import com.dcsplab.vdui.components.VDGrid;
import com.gst.context.GSContext;
import com.gst.domain.GSRole;
import com.gst.domain.GSRoleAccess;
import com.gst.domain.GSSystemFunction;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSCommonDetailDialog;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.service.GSAuthorizationService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class GSRoleAccessSetting extends GSDetailFormBase implements GSFormChangeHandler {
  
  private static final long serialVersionUID = 844702616850933944L;
  private static final Logger logger = LoggerFactory.getLogger(GSRoleAccessSetting.class);
  
  private final ArrayList<GSRoleAccess> removedFunction;
  
  private VDGrid<GSRoleAccess> grid;
  
  private boolean valueChanged = false;
  
  private final GSAuthorizationService authService;
  
  public GSRoleAccessSetting() {
    setHideBasicInfo(true);
    removedFunction = new ArrayList<>();
    authService = GSContext.getApplication().getAuthorizationService();
    init();
  }
  
  private void init() {
    HorizontalLayout action = new HorizontalLayout();
    VDButton newAccess = new VDButton("新增程式");
    newAccess.addClickListener(e -> showFunctionSelections());
    action.add(newAccess);
    add(action);
    
    createGrid();
  }
  
  private void showFunctionSelections() {
    GSMemberAccessAssignment selectForm =
      new GSMemberAccessAssignment(GSMemberAccessAssignment.Filter.ForRole);
    selectForm.setViewMode(ViewMode.ConfirmClose);
    selectForm.setChangeHandler(this);
    selectForm.setDetail(this.getDetail());
    
    GSCommonDetailDialog dialog = new GSCommonDetailDialog(selectForm);
    dialog.setWidth("300px");
    dialog.setHeight("450px");
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
  }
  
  private void createGrid() {
    grid = new VDGrid<>();
    grid.setId("gst-admin-roleaccess-grid");
    
    grid.addComponentColumn(this::createFunctionLabel)
      .setHeader("程式")
      .setWidth("200px")
      .setResizable(true)
      .setFlexGrow(0)
      .setSortable(false);
    
    grid.addComponentColumn(this::createAccessSettings)
      .setHeader("權限設定")
      .setResizable(true)
      .setWidth("300px")
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setSortable(false);
    
    grid.addComponentColumn(this::createDeleteIcon)
      .setHeader("")
      .setAutoWidth(false)
      .setFlexGrow(0)
      .setSortable(false);
    
    add(grid);
  }
  
  private Component createFunctionLabel(GSRoleAccess data) {
    
    String companyId = data.getCompanyId();
    String functionId = data.getFunctionId();
    GSSystemFunction function = authService.getSystemFunction(companyId, functionId);
    
    Div display = new Div();
    String html = function.getName() + "<br/>" + function.getId();
    display.getElement().setProperty("innerHTML", html);
    
    return display;
  }
  
  private HorizontalLayout createAccessSettings(GSRoleAccess data) {
    Vector<String> accessList = new Vector<>();
    String accessValue = data.getAccessString();
    for (int i = 0; i < UIConstants.ACCESS_LIST.length; i++) {
      char accessCh = accessValue.charAt(i);
      if ('Y' == accessCh) {
        accessList.add(UIConstants.ACCESS_LIST[i]);
      }
    }
    
    Object[] objarray = accessList.toArray();
    String[] strAccessList = Arrays.copyOf(objarray, objarray.length, String[].class);
    
    VDCheckboxGroup<String> checkboxGroup = new VDCheckboxGroup<>();
    checkboxGroup.getStyle().set("font-size", "11px");
    Set<String> items = new LinkedHashSet<>(Arrays.asList(UIConstants.ACCESS_LIST));
    checkboxGroup.setItems(items);
    
    if (strAccessList.length > 0) {
      Set<String> values = new LinkedHashSet<>(Arrays.asList(strAccessList));
      checkboxGroup.setValue(values);
    }
    
    checkboxGroup.addValueChangeListener(
      event -> {
        String function = data.getFunctionId().trim();
        System.out.println("[" + function + "] access changed...");
        
        StringBuilder accessDisplay = new StringBuilder();
        StringBuilder access = new StringBuilder();
        Set<String> eValues = event.getValue();
        
        for (int i = 0; i < UIConstants.ACCESS_LIST.length; i++) {
          String key = UIConstants.ACCESS_LIST[i];
          
          boolean matched = false;
          for (String val : eValues) {
            if (val.equals(key)) {
              matched = true;
              accessDisplay.append(val).append(",");
              break;
            }
          }
          
          if (matched) {
            access.append("Y");
          } else {
            access.append("N");
          }
        }
        
        logger.debug("  checked value: " + accessDisplay + ", access:" + access);
        
        data.setAccessString(access.toString());
        
        valueChanged = true;
      });
    
    HorizontalLayout column = new HorizontalLayout();
    column.setWidthFull();
    column.setVerticalComponentAlignment(Alignment.CENTER, checkboxGroup);
    column.add(checkboxGroup);
    
    return column;
  }
  
  private Icon createDeleteIcon(GSRoleAccess data) {
    Icon icon = new Icon(VaadinIcon.CLOSE);
    icon.setColor("#f51f1f");
    icon.getStyle().set("cursor", "pointer");
    icon.setSize("14px");
    icon.addClickListener(e -> removeAccess(data));
    return icon;
  }
  
  private void removeAccess(GSRoleAccess data) {
    removedFunction.add(data);
    grid.remove(data);
  }
  
  private Collection<GSRoleAccess> getDataList() {
    GSRole data = (GSRole) getDetail();
    return authService.getRoleAccessList(data);
  }
  
  private void refreshGrid(boolean reload) {
    if (reload) {
      grid.setItems(getDataList());
    } else {
      grid.refresh();
    }
  }
  
  public Collection<GSRoleAccess> getCurrentAccessList() {
    return grid.getItems();
  }
  
  public String getDetailTitle() {
    return getHeader();
  }
  
  @Override
  public void setChangeHandler(GSFormChangeHandler handler) {
  }
  
  @Override
  public void onSave() {
    Collection<GSRoleAccess> accessList = getCurrentAccessList();
    GSRole data = (GSRole) getDetail();
    data.setAccessList(new ArrayList<>(accessList));
    data.updateAccess();
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
  
  @Override
  public boolean isValueChanged() {
    return valueChanged;
  }
  
  public void addAccess(ArrayList<GSRoleAccess> newAccess) {
    grid.addItems(newAccess);
  }
  
  @Override
  public void populateDetail() {
    GSRole role = (GSRole) getDetail();
    setHeader("權限設定: " + role.getId() + "-" + role.getName());
    refreshGrid(true);
  }
  
  public Icon getDetailHeaderIcon() {
    return VaadinIcon.PACKAGE.create();
  }
  
  @Override
  public void onChange() {
    refreshGrid(false);
  }
  
  @Override
  public String getHandlerID() {
    return null;
  }
}
