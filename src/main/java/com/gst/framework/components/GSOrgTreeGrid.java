package com.gst.framework.components;

import com.dcsplab.vdui.UIConstants;
import com.dcsplab.vdui.components.VDLabel;
import com.dcsplab.vdui.components.VDTreeGrid;
import com.gst.context.GSContext;
import com.gst.domain.GSAuthorization.Action;
import com.gst.domain.*;
import com.gst.framework.GSDetailFormBase.ViewMode;
import com.gst.framework.org.GSDepartmentDetailForm;
import com.gst.service.GSOrganizationService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.hierarchy.AbstractBackEndHierarchicalDataProvider;
import com.vaadin.flow.data.provider.hierarchy.HierarchicalDataProvider;
import com.vaadin.flow.data.provider.hierarchy.HierarchicalQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class GSOrgTreeGrid extends VDTreeGrid<GSOrganization> implements GSFormChangeHandler {
  
  private static final long serialVersionUID = -501957735570129916L;
  
  private static final Logger logger = LoggerFactory.getLogger(GSOrgTreeGrid.class);
  
  private final GSMember contextUser;
  
  private final TreeType nodeType;
  
  boolean hasModifyAccess = false;
  
  boolean hasDeleteAccess = false;
  
  private HierarchicalDataProvider<GSOrganization, Void> dataProvider;
  
  private final GSOrganizationService orgService;
  
  public GSOrgTreeGrid(TreeType type) {
    this(type, null);
  }
  
  public GSOrgTreeGrid(TreeType type, GSSystemFunction func) {
    super();
    addThemeVariants(GridVariant.LUMO_COMPACT);
    addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
    
    contextUser = GSContext.getCurrentMember();
    this.nodeType = type;
    
    orgService = GSContext.getApplication().getOrganizationService();
    
    if (func != null) {
      hasModifyAccess = contextUser.hasAccess(func, Action.Modify);
      hasDeleteAccess = contextUser.hasAccess(func, Action.Delete);
    }
    
    List<Grid.Column<GSOrganization>> columns = new ArrayList<>();
    String strTitle = "公司部門設定";
    
    if (type == TreeType.DeptList) {
      Grid.Column<GSOrganization> idColumn =
        addHierarchyColumn(GSOrganization::getId)
          .setHeader("編號")
          .setResizable(true)
          .setWidth("150px")
          .setFlexGrow(0)
          .setSortable(false);
      columns.add(idColumn);
      
      if (hasModifyAccess || hasDeleteAccess) {
        Grid.Column<GSOrganization> editColumn =
          addComponentColumn(this::createEditLink).setFlexGrow(0).setWidth("60px");
        columns.add(editColumn);
      }
      
      Grid.Column<GSOrganization> nameColumn =
        addColumn(GSOrganization::getName)
          .setHeader("名稱")
          .setResizable(true)
          .setFlexGrow(0)
          .setResizable(true)
          .setWidth("200px");
      columns.add(nameColumn);
      
      Grid.Column<GSOrganization> deptColumn =
        addComponentColumn(this::createReleatedDeptDisplay)
          .setFlexGrow(0)
          .setWidth("350px")
          .setResizable(true)
          .setHeader("關聯部門");
      columns.add(deptColumn);
      
    } else if (type == TreeType.DeptOnly) {
      Grid.Column<GSOrganization> deptColumn =
        addHierarchyColumn(GSOrganization::getName).setHeader("部門").setWidth("150px");
      columns.add(deptColumn);
      strTitle = "部門清單";
    } else if (type == TreeType.PersonSelection) {
      Grid.Column<GSOrganization> personColumn =
        addHierarchyColumn(GSOrganization::getName).setHeader("人員").setWidth("150px");
      columns.add(personColumn);
      strTitle = "人員清單";
    }
    
    VDLabel gridTitle = new VDLabel(strTitle);
    HorizontalLayout gridTitleLayout = new HorizontalLayout();
    gridTitleLayout.setVerticalComponentAlignment(FlexComponent.Alignment.BASELINE, gridTitle);
    gridTitleLayout.add(gridTitle);
    
    if (columns.size() > 1) {
      Column[] colArray = new Column[columns.size()];
      for (int i = 0; i < columns.size(); i++) {
        colArray[i] = columns.get(i);
      }
      
      HeaderRow topRow = prependHeaderRow();
      HeaderRow.HeaderCell titleActions = topRow.join(colArray);
      titleActions.setComponent(gridTitleLayout);
    }
    
    initDataProvider();
    
    GSOrganizationService orgService = GSContext.getApplication().getOrganizationService();
    GSCompany company = orgService.getCompany(contextUser.getCompanyId());
    
    ArrayList<GSOrganization> emptyList = new ArrayList<>();
    if (nodeType == TreeType.DeptOnly) {
      setItems(company.getChildren(), e -> emptyList);
    } else {
      setItems(company.getChildren(), GSOrganization::getChildren);
    }
  }
  
  private Component createEditLink(GSOrganization org) {
    Icon editIcon;
    if (org instanceof GSMember) {
      editIcon = new Icon(VaadinIcon.USER);
      editIcon.setSize("13px");
      editIcon.setColor(UIConstants.DEFAULT_ICON_DISABLED_COLOR);
    } else {
      editIcon = new Icon(VaadinIcon.EDIT);
      editIcon.setColor(UIConstants.DEFAULT_ICON_ENABLED_COLOR);
      editIcon.getStyle().set("cursor", "pointer");
      editIcon.setSize("14px");
      editIcon.addClickListener(
        e -> {
          // entityOnEdit = ((GSDepartment) org).getEntity();
          GSDepartment dept = (GSDepartment) org;
          if (hasDeleteAccess) {
            showDetailFrame(dept, ViewMode.Delete);
          } else {
            showDetailFrame(dept, ViewMode.Modify);
          }
        });
    }
    return editIcon;
  }
  
  private void showDetailFrame(GSDepartment dept, ViewMode mode) {
    GSDepartmentDetailForm detailForm = new GSDepartmentDetailForm();
    detailForm.setViewMode(mode);
    detailForm.setChangeHandler(this);
    detailForm.setDetail(dept);
    
    GSCommonDetailDialog dialog = new GSCommonDetailDialog(detailForm);
    dialog.setWidth("600px");
    dialog.setHeight("300px");
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
  }
  
  private Component createReleatedDeptDisplay(GSOrganization org) {
    StringBuilder text = new StringBuilder();
    if (org instanceof GSMember) {
      GSMember person = (GSMember) org;
      List<GSDepartment> deptList = person.getAssignments();
      Iterator<GSDepartment> iter = deptList.iterator();
      while (iter.hasNext()) {
        GSDepartment dept = iter.next();
        text.append(dept.getId().trim());
        if (iter.hasNext()) {
          text.append(",");
        }
      }
    }
    
    Div display = new Div();
    display.setText(text.toString());
    return display;
  }
  
  public void refresh() {
    this.dataProvider.refreshAll();
  }
  
  private void initDataProvider() {
    
    dataProvider =
      new AbstractBackEndHierarchicalDataProvider<>() {
        
        private static final long serialVersionUID = 3684874419371892822L;
        
        @Override
        public int getChildCount(HierarchicalQuery<GSOrganization, Void> query) {
          GSOrganization item = query.getParent();
          if (item == null) {
            return 0;
          }
          
          logger.info("getChildCount() parent :" + item.getName() + ", " + nodeType);
          
          if (item instanceof GSCompany) {
            GSCompany comp = (GSCompany) item;
            return (int) comp.getChildCount();
          } else if (item instanceof GSDepartment) {
            if (nodeType == TreeType.DeptList) {
              GSDepartment dept = (GSDepartment) item;
              return dept.getMemberCount();
            }
          }
          
          return 0;
        }
        
        @Override
        public boolean hasChildren(GSOrganization item) {
          logger.info("hasChildren() :" + item.getName() + ", " + nodeType);
          
          if (item instanceof GSCompany) {
            GSCompany comp = (GSCompany) item;
            return comp.getChildCount() > 0;
          } else if (item instanceof GSDepartment) {
            if (nodeType == TreeType.DeptList) {
              GSDepartment dept = (GSDepartment) item;
              return dept.getMemberCount() > 0;
            }
          }
          
          return false;
        }
        
        @Override
        protected Stream<GSOrganization> fetchChildrenFromBackEnd(
          HierarchicalQuery<GSOrganization, Void> query) {
          // return accountService.fetchChildren(query.getParent()).stream();
          
          GSOrganization item = query.getParent();
          
          if (item == null) {
            logger.info("fetchChildrenFromBackEnd parent : null" + ", " + nodeType);
            
            GSCompany comp = orgService.getCompany(contextUser.getCompanyId());
            List<GSDepartment> depts = comp.getDepartmentList();
            ArrayList<GSOrganization> ary = new ArrayList<>(depts);
            return ary.stream();
          } else if (item instanceof GSCompany) {
            logger.info("fetchChildrenFromBackEnd parent :" + item.getName() + ", " + nodeType);
            
            GSCompany comp = (GSCompany) item;
            List<GSDepartment> depts = comp.getDepartmentList();
            ArrayList<GSOrganization> ary = new ArrayList<>(depts);
            return ary.stream();
          } else if (item instanceof GSDepartment) {
            logger.info("fetchChildrenFromBackEnd parent :" + item.getName() + ", " + nodeType);
            
            if (nodeType == TreeType.DeptList || nodeType == TreeType.PersonSelection) {
              GSDepartment dept = (GSDepartment) item;
              List<GSMember> persons = dept.getMemberList();
              ArrayList<GSOrganization> ary = new ArrayList<>(persons);
              return ary.stream();
            }
          }
          
          return null;
        }
      };
    
    this.setDataProvider(dataProvider);
  }
  
  @Override
  public void onChange() {
    GSCompany comp = orgService.getCompany(contextUser.getCompanyId());
    setItems(comp.getChildren(), GSOrganization::getChildren);
    refresh();
  }
  
  @Override
  public String getHandlerID() {
    return null;
  }
  
  public enum TreeType {
    DeptList,
    PersonSelection,
    DeptOnly,
    PersonOnly
  }
}
