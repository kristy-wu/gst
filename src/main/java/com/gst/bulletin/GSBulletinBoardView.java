package com.gst.bulletin;

import com.dcsplab.vdui.components.VDComboBox;
import com.dcsplab.vdui.components.VDGrid;
import com.dcsplab.vdui.components.VDLabel;
import com.dcsplab.vdui.layout.VDHorizontalLayout;
import com.dcsplab.vdui.layout.VDLayoutContainer;
import com.dcsplab.vdui.layout.VDVerticalLayout;
import com.gst.broadcast.GSBroadcastListener;
import com.gst.broadcast.GSBroadcastMessage;
import com.gst.broadcast.GSBulletinBroadcaster;
import com.gst.context.GSContext;
import com.gst.domain.GSBulletinClass;
import com.gst.domain.GSBulletinItem;
import com.gst.domain.GSMember;
import com.gst.framework.components.GSCommonViewFrame;
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
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * 系統公佈欄
 */
@Route(value = "bulletinboard", layout = GSMainLayout.class)
@RouteAlias(value = "", layout = GSMainLayout.class)
public class GSBulletinBoardView extends GSCommonViewFrame implements GSBroadcastListener {
  private static final Logger logger = LoggerFactory.getLogger(GSBulletinBoardView.class);
  private static final long serialVersionUID = 3339284089353813435L;
  
  public static final String TITLE = "gst.app.Navigator.BulletinBoard.Overview";
  public static final String HANDLER_ID = "CMSI07";
  
  private final GSMember contextUser;
  
  private VDGrid<GSBulletinItem> grid;
  
  private VDComboBox<GSBulletinClass> classFilter;
  
  @Autowired
  private GSBulletinService bulletinService;
  
  private Registration resizeListener;
  
  public GSBulletinBoardView() {
    setTitleKey(TITLE);
    setId("gst-bulletin-board");
    
    contextUser = GSContext.getCurrentMember();
  }
  
  @PostConstruct
  private void init() {
    setViewContent(createContent());
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
    
    HorizontalLayout actionLayout = new VDHorizontalLayout();
    actionLayout.add(classFilter);
    actionLayout.setVerticalComponentAlignment(Alignment.CENTER, classFilter);
    
    VDVerticalLayout gridLayout = new VDVerticalLayout();
    gridLayout.setSizeFull();
    gridLayout.add(grid);
    
    refreshGrid();
    
    return new VDLayoutContainer(actionLayout, gridLayout);
  }
  
  private void createGrid() {
    grid = new VDGrid<>();
    grid.setSelectionMode(SelectionMode.SINGLE);
    
    Grid.Column<GSBulletinItem> idColumn =
      grid.addComponentColumn(this::createItemIDDisplay)
        .setResizable(true)
        .setWidth("150px")
        .setFlexGrow(0)
        .setHeader("編號")
        .setSortable(true);
    
    Grid.Column<GSBulletinItem> nameColumn =
      grid.addComponentColumn(this::createContentDisplay)
        .setResizable(true)
        .setWidth("400px")
        .setFlexGrow(0)
        .setHeader("內容")
        .setSortable(true);
    
    VDLabel itemListTitle = new VDLabel("公佈欄項目");
    HorizontalLayout gridTitleLayout = new HorizontalLayout();
    gridTitleLayout.setVerticalComponentAlignment(FlexComponent.Alignment.BASELINE, itemListTitle);
    gridTitleLayout.add(itemListTitle);
    
    HeaderRow topRow = grid.prependHeaderRow();
    HeaderRow.HeaderCell titleActions = topRow.join(idColumn, nameColumn);
    titleActions.setComponent(gridTitleLayout);
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
    Page page = ui.getPage();
    resizeListener =
      page.addBrowserWindowResizeListener(event -> updateVisibleColumns(event.getWidth()));
    page.retrieveExtendedClientDetails(
      details -> updateVisibleColumns(details.getBodyClientWidth()));
    
    UUID uuid = UUID.randomUUID();
    String UIID = "gst-bulletin-board-" + uuid;
    ui.setId(UIID);
    
    GSBulletinBroadcaster.register(ui, this);
    
    GSMainLayout ml = GSMainLayout.get();
    ml.getAppBar().setTitle(getTranslation(TITLE));
  }
  
  @Override
  protected void onDetach(DetachEvent detachEvent) {
    // if (hasAccess) {
    resizeListener.remove();
    resizeListener = null;
    // }
    
    GSBulletinBroadcaster.unregister(detachEvent.getUI());
    super.onDetach(detachEvent);
  }
  
  @Override
  public void receiveBroadcast(UI ui, GSBroadcastMessage message) {
    try {
      if (ui == null || ui.isClosing() || !ui.isVisible()) {
        logger.info("UI is not available. Skip processing message broadcasting.");
        return;
      }
      
      VaadinSession session = ui.getSession();
      if (session == null) {
        logger.info(
          "UI["
            + ui.getId()
            + "] does not connect to any session. Skip processing message broadcasting.");
        return;
      }
      
      ui.access(this::refreshGrid);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
  }
}
