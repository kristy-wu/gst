package com.gst.bulletin;

import com.dcsplab.common.DLDateUtils;
import com.dcsplab.common.DLDateUtils.Resolution;
import com.dcsplab.vdui.UIConstants;
import com.dcsplab.vdui.components.*;
import com.dcsplab.vdui.layout.VDHorizontalLayout;
import com.dcsplab.vdui.layout.VDLayoutContainer;
import com.dcsplab.vdui.layout.VDVerticalLayout;
import com.gst.broadcast.GSBroadcastListener;
import com.gst.broadcast.GSBroadcastMessage;
import com.gst.context.GSContext;
import com.gst.domain.GSAuthorization.Action;
import com.gst.domain.GSBulletinClass;
import com.gst.domain.GSBulletinItem;
import com.gst.domain.GSMember;
import com.gst.framework.GSDetailFormBase;
import com.gst.framework.components.GSAccessDeniedScreen;
import com.gst.framework.components.GSCommonDetailDialog;
import com.gst.framework.components.GSCommonViewFrame;
import com.gst.framework.components.GSFormChangeHandler;
import com.gst.framework.layout.GSMainLayout;
import com.gst.service.GSBulletinService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/*
 * 公佈欄資料維護 CMSI06
 */
@Route(value = "adm/bulletinitems", layout = GSMainLayout.class)
public class GSBulletinItemList extends GSCommonViewFrame
  implements GSFormChangeHandler, VDConfirmDialog.ConfirmHandler, GSBroadcastListener {
  
  private static final long serialVersionUID = -6371352695603060748L;
  private static final Logger logger = LoggerFactory.getLogger(GSBulletinItemList.class);
  
  public static final String TITLE = "公佈欄資料維護";
  
  public static final String HANDLER_ID = "CMSI06";
  
  private final GSMember contextUser;
  
  private Registration resizeListener;
  
  private boolean hasAccess = false;
  
  private VDButton removeButton;
  
  private VDGrid<GSBulletinItem> grid;
  
  private VDComboBox<GSBulletinClass> classFilter;
  
  @Autowired
  private GSBulletinService bulletinService;
  
  public GSBulletinItemList() {
    setTitleKey(TITLE);
    setId("gst-bulletin-item");
    
    contextUser = GSContext.getCurrentMember();
  }
  
  @PostConstruct
  private void init() {
    hasAccess = contextUser.hasAccess(HANDLER_ID, Action.Search);
    if (hasAccess) {
      setViewContent(createContent());
    } else {
      GSAccessDeniedScreen deniedScreen = new GSAccessDeniedScreen();
      setViewContent(deniedScreen);
    }
  }
  
  private Component createContent() {
    createGrid();
    
    List<GSBulletinClass> classes = bulletinService.listBulletinBoards(contextUser.getCompanyId());
    classFilter = new VDComboBox<>();
    classFilter.setItems(classes);
    classFilter.setRenderer(
      TemplateRenderer.<GSBulletinClass>of("<div>[[item.name]]</div>")
        .withProperty("name", GSBulletinClass::getName));
    classFilter.setItemLabelGenerator(GSBulletinClass::getName);
    classFilter.setClearButtonVisible(true);
    classFilter.addValueChangeListener(e -> refreshGrid());
    classFilter.setValue(bulletinService.getDefaultBoard(contextUser.getCompanyId()));
    
    Icon createIcon = VaadinIcon.FILE_ADD.create();
    VDButton addButton = new VDButton("新增項目");
    addButton.setIcon(createIcon);
    addButton.addClickListener(
      e -> {
        GSBulletinItem item =
          bulletinService.createBulletinItem(contextUser, classFilter.getValue());
        showDetailFrame(item, GSDetailFormBase.ViewMode.New);
      });
    
    Icon removeIcon = VaadinIcon.FILE_REMOVE.create();
    removeIcon.setColor(UIConstants.DEFAULT_ICON_DISABLED_COLOR);
    removeButton = new VDButton("刪除項目");
    removeButton.setIcon(removeIcon);
    removeButton.setEnabled(false);
    removeButton.addClickListener(e -> removeItem());
    
    HorizontalLayout actionLayout = new VDHorizontalLayout();
    actionLayout.add(classFilter, addButton, removeButton);
    
    VDVerticalLayout gridLayout = new VDVerticalLayout();
    gridLayout.setSizeFull();
    gridLayout.add(grid);
    
    refreshGrid();
    
    return new VDLayoutContainer(actionLayout, gridLayout);
  }
  
  private void showDetailFrame(GSBulletinItem item, GSDetailFormBase.ViewMode mode) {
    GSBulletinItemDetailForm detailForm = new GSBulletinItemDetailForm();
    detailForm.setViewMode(mode);
    detailForm.setChangeHandler(this);
    detailForm.setDetail(item);
    
    GSCommonDetailDialog dialog = new GSCommonDetailDialog(detailForm);
    dialog.setWidth("600px");
    dialog.setHeight("350px");
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
  }
  
  private void removeItem() {
    VDConfirmDialog dialog =
      new VDConfirmDialog(this, "刪除公佈欄項目", "確定要刪除此項目?", VDConfirmDialog.Option.DELETE_CANCEL);
    dialog.open();
  }
  
  private void createGrid() {
    grid = new VDGrid<>();
    grid.setSelectionMode(SelectionMode.SINGLE);
    grid.addSelectionListener(
      e -> {
        Set<GSBulletinItem> items = e.getSource().getSelectedItems();
        boolean enabled = items.size() > 0;
        removeButton.setEnabled(enabled);
        String iconColor =
          enabled ? UIConstants.DEFAULT_RED_COLOR : UIConstants.DEFAULT_ICON_DISABLED_COLOR;
        ((Icon) removeButton.getIcon()).setColor(iconColor);
      });
    
    Grid.Column<GSBulletinItem> editColumn =
      grid.addComponentColumn(this::createEditLink)
        .setResizable(false)
        .setWidth("70px")
        .setHeader("編輯")
        .setFlexGrow(0)
        .setSortable(false);
    
    Grid.Column<GSBulletinItem> idColumn =
      grid.addComponentColumn(this::createItemIDDisplay)
        .setResizable(true)
        .setWidth("100px")
        .setFlexGrow(0)
        .setHeader("編號")
        .setSortable(true);
    
    Grid.Column<GSBulletinItem> contentColumn =
      grid.addComponentColumn(this::createContentDisplay)
        .setResizable(true)
        .setWidth("350px")
        .setFlexGrow(0)
        .setHeader("內容")
        .setSortable(true);
    
    Grid.Column<GSBulletinItem> startDateColumn =
      grid.addComponentColumn(this::createStartDateDisplay)
        .setResizable(true)
        .setWidth("100px")
        .setFlexGrow(0)
        .setHeader("生效日")
        .setSortable(true);
    
    Grid.Column<GSBulletinItem> endDateColumn =
      grid.addComponentColumn(this::createEndDateDisplay)
        .setResizable(true)
        .setWidth("100px")
        .setFlexGrow(0)
        .setHeader("失效日")
        .setSortable(true);
    
    VDLabel itemListTitle = new VDLabel("公佈欄項目");
    HorizontalLayout gridTitleLayout = new HorizontalLayout();
    gridTitleLayout.setVerticalComponentAlignment(FlexComponent.Alignment.BASELINE, itemListTitle);
    gridTitleLayout.add(itemListTitle);
    
    HeaderRow topRow = grid.prependHeaderRow();
    HeaderRow.HeaderCell titleActions =
      topRow.join(editColumn, idColumn, contentColumn, startDateColumn, endDateColumn);
    titleActions.setComponent(gridTitleLayout);
  }
  
  private Component createEditLink(GSBulletinItem item) {
    Icon icon = new Icon(VaadinIcon.EDIT);
    icon.setSize("14px");
    icon.setColor(UIConstants.DEFAULT_ICON_ENABLED_COLOR);
    icon.getStyle().set("cursor", "pointer");
    
    icon.addClickListener(e -> showDetailFrame(item, GSDetailFormBase.ViewMode.Modify));
    return icon;
  }
  
  private Component createItemIDDisplay(GSBulletinItem item) {
    Div display = new Div();
    display.setText(item.getId());
    return display;
  }
  
  private Component createContentDisplay(GSBulletinItem item) {
    Div display = new Div();
    display.setText(item.getContent());
    return display;
  }
  
  private Component createStartDateDisplay(GSBulletinItem item) {
    Div display = new Div();
    Date start = item.getStartDate();
    String strdate = DLDateUtils.format(start, Resolution.SimpleDate);
    display.setText(strdate);
    return display;
  }
  
  private Component createEndDateDisplay(GSBulletinItem item) {
    Div display = new Div();
    Date end = item.getEndDate();
    String strdate = DLDateUtils.format(end, Resolution.SimpleDate);
    display.setText(strdate);
    return display;
  }
  
  private List<GSBulletinItem> getDataList() {
    if (classFilter.getValue() == null) {
      return new ArrayList<>();
    }
    
    return this.bulletinService.listBulletinItems(
      contextUser.getCompanyId(), classFilter.getValue().getId().trim());
  }
  
  private void refreshGrid() {
    grid.setItems(getDataList());
  }
  
  @Override
  protected void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);
    
    UI ui = attachEvent.getUI();
    if (hasAccess) {
      Page page = ui.getPage();
      resizeListener =
        page.addBrowserWindowResizeListener(event -> updateVisibleColumns(event.getWidth()));
      page.retrieveExtendedClientDetails(
        details -> updateVisibleColumns(details.getBodyClientWidth()));
    }
    
    GSMainLayout ml = GSMainLayout.get();
    ml.getAppBar().setTitle(getTranslation(TITLE));
  }
  
  @Override
  protected void onDetach(DetachEvent detachEvent) {
    if (hasAccess) {
      resizeListener.remove();
      resizeListener = null;
    }
    
    super.onDetach(detachEvent);
  }
  
  @Override
  public void receiveBroadcast(UI ui, GSBroadcastMessage message) {
    refreshGrid();
  }
  
  @Override
  public void onChange() {
    refreshGrid();
  }
  
  @Override
  public String getHandlerID() {
    return HANDLER_ID;
  }
  
  @Override
  public void doConfirm() {
  }
  
  @Override
  public void doReject() {
  }
  
  @Override
  public void doConfirmDelete() {
    Set<GSBulletinItem> items = grid.getSelectedItems();
    for (GSBulletinItem item : items) {
      bulletinService.deleteBulletinItem(item);
    }
    
    refreshGrid();
  }
}
