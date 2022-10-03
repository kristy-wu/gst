package com.gst.bulletin;

import com.dcsplab.vdui.UIConstants;
import com.dcsplab.vdui.components.VDButton;
import com.dcsplab.vdui.components.VDConfirmDialog;
import com.dcsplab.vdui.components.VDGrid;
import com.dcsplab.vdui.components.VDLabel;
import com.dcsplab.vdui.layout.VDHorizontalLayout;
import com.dcsplab.vdui.layout.VDLayoutContainer;
import com.dcsplab.vdui.layout.VDVerticalLayout;
import com.gst.broadcast.GSBroadcastListener;
import com.gst.broadcast.GSBroadcastMessage;
import com.gst.context.GSContext;
import com.gst.domain.GSAuthorization.Action;
import com.gst.domain.GSBulletinClass;
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
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

/*
 * 公佈欄類別維護 CMSI05
 */
@Route(value = "adm/bulletinclass", layout = GSMainLayout.class)
public class GSBulletinClassification extends GSCommonViewFrame
  implements GSFormChangeHandler, VDConfirmDialog.ConfirmHandler, GSBroadcastListener {
  
  public static final String TITLE = "公佈欄類別維護";
  
  public static final String HANDLER_ID = "CMSI05";
  
  private static final long serialVersionUID = 609237856250132004L;
  
  private static final Logger logger = LoggerFactory.getLogger(GSBulletinClassification.class);
  
  private final GSMember member;
  
  private Registration resizeListener;
  
  private boolean hasAccess = false;
  
  @Autowired
  private GSBulletinService bulletinService;
  
  private VDButton removeButton;
  
  private VDGrid<GSBulletinClass> grid;
  
  public GSBulletinClassification() {
    setTitleKey(TITLE);
    setId("gst-bulletin-class");
    member = GSContext.getCurrentMember();
  }
  
  @PostConstruct
  private void init() {
    hasAccess = member.hasAccess(HANDLER_ID, Action.Search);
    if (hasAccess) {
      setViewContent(createContent());
    } else {
      GSAccessDeniedScreen deniedScreen = new GSAccessDeniedScreen();
      setViewContent(deniedScreen);
    }
  }
  
  private Component createContent() {
    createGrid();
    refreshGrid();
    
    Icon createIcon = VaadinIcon.FILE_ADD.create();
    VDButton addButton = new VDButton("新增類別");
    addButton.setIcon(createIcon);
    addButton.addClickListener(
      e -> {
        try {
          GSBulletinClass clazz = bulletinService.createBulletinClass(member);
          showDetailFrame(clazz, GSDetailFormBase.ViewMode.New);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      });
    
    Icon removeIcon = VaadinIcon.FILE_REMOVE.create();
    removeIcon.setColor(UIConstants.DEFAULT_ICON_DISABLED_COLOR);
    removeButton = new VDButton("刪除類別");
    removeButton.setIcon(removeIcon);
    removeButton.setEnabled(false);
    removeButton.addClickListener(
      e -> {
        VDConfirmDialog dialog =
          new VDConfirmDialog(this, "刪除公佈欄", "確認刪除公佈欄?", VDConfirmDialog.Option.DELETE_CANCEL);
        dialog.open();
      });
    
    HorizontalLayout actionLayout = new VDHorizontalLayout();
    actionLayout.add(addButton, removeButton);
    actionLayout.setVerticalComponentAlignment(Alignment.CENTER, addButton, removeButton);
    
    VDVerticalLayout gridLayout = new VDVerticalLayout();
    gridLayout.setSizeFull();
    gridLayout.add(grid);
    
    return new VDLayoutContainer(actionLayout, gridLayout);
  }
  
  private void showDetailFrame(GSBulletinClass board, GSDetailFormBase.ViewMode mode) {
    GSBulletinClassDetailForm detailForm = new GSBulletinClassDetailForm();
    detailForm.setViewMode(mode);
    detailForm.setChangeHandler(this);
    detailForm.setDetail(board);
    
    GSCommonDetailDialog dialog = new GSCommonDetailDialog(detailForm);
    dialog.setWidth("600px");
    dialog.setHeight("300px");
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
  }
  
  private void createGrid() {
    grid = new VDGrid<>();
    grid.setSelectionMode(SelectionMode.SINGLE);
    grid.addSelectionListener(
      e -> {
        Set<GSBulletinClass> items = e.getSource().getSelectedItems();
        GSBulletinClass defaultClass = bulletinService.getDefaultBoard(member.getCompanyId());
        for (GSBulletinClass item : items) {
          if (item.getId().equals(defaultClass.getId())) {
            removeButton.setEnabled(false);
            ((Icon) removeButton.getIcon()).setColor(UIConstants.DEFAULT_ICON_DISABLED_COLOR);
            return;
          }
        }
        
        boolean enabled = items.size() > 0;
        removeButton.setEnabled(enabled);
        String iconColor =
          enabled ? UIConstants.DEFAULT_RED_COLOR : UIConstants.DEFAULT_ICON_DISABLED_COLOR;
        ((Icon) removeButton.getIcon()).setColor(iconColor);
      });
    
    Grid.Column<GSBulletinClass> editColumn =
      grid.addComponentColumn(this::createEditLink)
        .setResizable(false)
        .setWidth("70px")
        .setHeader("編輯")
        .setFlexGrow(0)
        .setSortable(false);
    
    Grid.Column<GSBulletinClass> nameColumn =
      grid.addComponentColumn(this::createClassNameDisplay)
        .setResizable(true)
        .setWidth("350px")
        .setFlexGrow(0)
        .setHeader("類別名稱")
        .setSortable(true);
    
    VDLabel boardListTitle = new VDLabel("公佈欄類別");
    HorizontalLayout gridTitleLayout = new HorizontalLayout();
    gridTitleLayout.setVerticalComponentAlignment(FlexComponent.Alignment.BASELINE, boardListTitle);
    gridTitleLayout.add(boardListTitle);
    
    HeaderRow topRow = grid.prependHeaderRow();
    HeaderRow.HeaderCell titleActions = topRow.join(editColumn, nameColumn);
    titleActions.setComponent(gridTitleLayout);
  }
  
  private Component createEditLink(GSBulletinClass data) {
    Icon icon = new Icon(VaadinIcon.CLIPBOARD_USER);
    icon.setSize("14px");
    
    icon.setColor(UIConstants.DEFAULT_ICON_ENABLED_COLOR);
    icon.getStyle().set("cursor", "pointer");
    
    icon.addClickListener(e -> showDetailFrame(data, GSDetailFormBase.ViewMode.Modify));

    /*
    if (hasConfirmAccess) {
      icon.setColor(UIConstants.DEFAULT_ICON_ENABLED_COLOR);
      icon.getStyle().set("cursor", "pointer");
      icon.addClickListener(e -> showDetailFrame(data, GSDetailFormBase.ViewMode.Modify));
    } else {
      icon.setColor(UIConstants.DEFAULT_ICON_DISABLED_COLOR);
    }*/
    
    return icon;
  }
  
  private Component createClassIDDisplay(GSBulletinClass board) {
    Div display = new Div();
    display.setText(board.getId());
    return display;
  }
  
  private Component createClassNameDisplay(GSBulletinClass board) {
    Div display = new Div();
    display.setText(board.getName());
    return display;
  }
  
  private List<GSBulletinClass> getDataList() {
    return bulletinService.listBulletinBoards(GSContext.getCurrentMember().getCompanyId());
  }
  
  private void refreshGrid() {
    grid.setItems(getDataList());
  }
  
  @Override
  protected void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);
    getUI()
      .ifPresent(
        ui -> {
          if (hasAccess) {
            Page page = ui.getPage();
            resizeListener =
              page.addBrowserWindowResizeListener(
                event -> updateVisibleColumns(event.getWidth()));
            page.retrieveExtendedClientDetails(
              details -> updateVisibleColumns(details.getBodyClientWidth()));
          }
        });
    
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
    Set<GSBulletinClass> classes = grid.getSelectedItems();
    for (GSBulletinClass item : classes) {
      if (item.getId().equals("company")) {
        continue;
      }
      bulletinService.deleteBulletinClass(item);
    }
    
    refreshGrid();
  }
}
