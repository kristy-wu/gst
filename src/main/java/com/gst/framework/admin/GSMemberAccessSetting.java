package com.gst.framework.admin;

import com.dcsplab.vdui.UIConstants;
import com.dcsplab.vdui.components.VDButton;
import com.dcsplab.vdui.components.VDCheckbox;
import com.dcsplab.vdui.components.VDCheckboxGroup;
import com.dcsplab.vdui.components.VDGrid;
import com.gst.context.GSContext;
import com.gst.domain.GSMember;
import com.gst.domain.GSMemberAccess;
import com.gst.domain.GSSystemFunction;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSCommonDetailDialog;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.service.GSAuthorizationService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class GSMemberAccessSetting extends GSDetailFormBase implements GSFormChangeHandler {
  
  private static final long serialVersionUID = 2767564938110303818L;
  private static final Logger logger = LoggerFactory.getLogger(GSMemberAccessSetting.class);
  
  private GSFormChangeHandler handler;
  
  private final GSMember contextUser;
  
  private VDGrid<GSMemberAccess> grid;
  
  private boolean valueChanged = false;
  
  private VDCheckbox vdManagerAccess;
  
  private final GSAuthorizationService authService;
  
  public GSMemberAccessSetting() {
    contextUser = GSContext.getCurrentMember();
    authService = GSContext.getApplication().getAuthorizationService();
    
    setHideBasicInfo(true);
    init();
  }
  
  private void init() {
    VDButton newAccess = new VDButton("新增程式");
    newAccess.setIcon(VaadinIcon.FILE_ADD.create());
    newAccess.addClickListener(e -> showFunctionSelections());
    
    HorizontalLayout addFunc = new HorizontalLayout();
    addFunc.add(newAccess);
    
    vdManagerAccess = new VDCheckbox("管理權限");
    HorizontalLayout actions = new HorizontalLayout();
    actions.setAlignItems(FlexComponent.Alignment.CENTER);
    actions.setFlexGrow(1, addFunc);
    actions.add(addFunc, vdManagerAccess);
    add(actions);
    
    createGrid();
  }
  
  private void createGrid() {
    grid = new VDGrid<>();
    grid.setId("gst-admin-useraccess-grid");
    
    grid.addComponentColumn(this::createFunctionLabel)
      .setHeader("程式")
      .setAutoWidth(true)
      .setFlexGrow(0)
      .setSortable(false);
    
    grid.addComponentColumn(this::createAccessSettings)
      .setHeader("權限設定")
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
  
  private Collection<GSMemberAccess> getDataList() {
    GSMember user = (GSMember) getDetail();
    return authService.getMemberAccessList(user);
  }
  
  private Component createFunctionLabel(GSMemberAccess data) {
    GSSystemFunction function =
      authService.getSystemFunction(data.getCompanyId(), data.getFunctionId());
    
    Div vdFunction = new Div();
    String html = function.getName() + "<br/>" + function.getId();
    vdFunction.getElement().setProperty("innerHTML", html);
    
    return vdFunction;
  }
  
  private Component createAccessSettings(GSMemberAccess data) {
    
    Vector<String> accessList = new Vector<>();
    String accessValue = data.getAccessString().trim();
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
        GSSystemFunction function =
          authService.getSystemFunction(data.getCompanyId(), data.getFunctionId());
        System.out.println("[" + function.getName() + "] access changed...");
        
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
    column.add(checkboxGroup);
    return column;
  }
  
  private Component createDeleteIcon(GSMemberAccess data) {
    Icon icon = new Icon(VaadinIcon.CLOSE);
    icon.setColor("#f51f1f");
    icon.getStyle().set("cursor", "pointer");
    icon.setSize("16px");
    icon.addClickListener(e -> removeAccess(data));
    return icon;
  }
  
  private void removeAccess(GSMemberAccess data) {
    grid.remove(data);
  }
  
  private void showFunctionSelections() {
    GSMemberAccessAssignment selectForm =
      new GSMemberAccessAssignment(GSMemberAccessAssignment.Filter.ForUser);
    selectForm.setChangeHandler(this);
    selectForm.setDetail(this.getDetail());
    selectForm.setViewMode(ViewMode.ConfirmClose);
    
    GSCommonDetailDialog dialog = new GSCommonDetailDialog(selectForm);
    dialog.setWidth("300px");
    dialog.setHeight("450px");
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
  }
  
  public void addAccess(ArrayList<GSMemberAccess> newAccess) {
    grid.addItems(newAccess);
  }
  
  public Collection<GSMemberAccess> getCurrentAccessList() {
    Collection<GSMemberAccess> items = grid.getItems();
    logger.info("items: " + items.size());
    return items;
  }
  
  @Override
  public String getDetailTitle() {
    return getHeader();
  }
  
  @Override
  public void setChangeHandler(GSFormChangeHandler handler) {
    this.handler = handler;
  }
  
  @Override
  public void onSave() {
    
    GSMember user = (GSMember) getDetail();
    
    Collection<GSMemberAccess> items = grid.getItems();
    
    try {
      user.addAccess(new ArrayList<>(items));
      user.updateAccess(contextUser);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    boolean hasMgrAccess = vdManagerAccess.getValue();
    user.setManagerAccess(hasMgrAccess);
    try {
      authService.updateMemberAccess(contextUser, user);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    handler.onChange();
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
  
  private void refreshGrid(boolean reload) {
    if (reload) {
      grid.setItems(getDataList());
    } else {
      grid.refresh();
    }
  }
  
  @Override
  public void populateDetail() {
    GSMember data = (GSMember) getDetail();
    vdManagerAccess.setValue(data.hasManagerAccess());
    setHeader("權限設定: " + data.getId() + "-" + data.getName());
    refreshGrid(true);
  }
  
  @Override
  public void onChange() {
    refreshGrid(false);
  }
  
  @Override
  public String getHandlerID() {
    return "";
  }
}
