package com.dcsplab.vdui.navigation.bar;

import com.dcsplab.vdui.components.VDLabel;
import com.dcsplab.vdui.layout.VDFlexBoxLayout;
import com.dcsplab.vdui.navigation.tab.NaviTab;
import com.dcsplab.vdui.navigation.tab.NaviTabs;
import com.dcsplab.vdui.util.LumoStyles;
import com.dcsplab.vdui.util.UIUtils;
import com.gst.framework.GSDetailFormBase.ViewMode;
import com.gst.framework.Home;
import com.gst.framework.admin.GSMemberChangePassword;
import com.gst.framework.admin.GSMemberProfile;
import com.gst.framework.components.GSCommonDetailDialog;
import com.gst.framework.layout.GSMainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.Registration;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;

@CssImport("./styles/components/app-bar.css")
public class AppBar extends VDFlexBoxLayout {
  
  public static final String APP_TYPE_ADM = "ADM";
  
  public static final String APP_TYPE_USER = "USER";
  
  private static final long serialVersionUID = -419542489137506249L;
  
  private final String CLASS_NAME = "app-bar";
  
  private VDFlexBoxLayout container;
  
  private Button menuIcon;
  
  private Button contextIcon;
  
  private H4 title;
  
  private VDFlexBoxLayout actionItems;
  
  // private Image avatar;
  private Icon avatar;
  
  private VDFlexBoxLayout tabContainer;
  
  private NaviTabs tabs;
  
  private ArrayList<Registration> tabSelectionListeners;
  
  private Button addTab;
  
  private TextField search;
  
  private Registration searchRegistration;
  
  private String type;
  
  private VDLabel nameLabel;
  
  private VDLabel bulletinField;
  
  private HorizontalLayout bulletinLayout;
  
  public AppBar(String title, String type, NaviTab... tabs) {
    setClassName(CLASS_NAME);
    setType(type);
    
    initMenuIcon();
    initContextIcon();
    initTitle(title);
    initSearch();
    initAvatar();
    initActionItems();
    initContainer();
    initTabs(tabs);
  }
  
  public void setNaviMode(NaviMode mode) {
    if (mode.equals(NaviMode.MENU)) {
      menuIcon.setVisible(true);
      contextIcon.setVisible(false);
    } else {
      menuIcon.setVisible(false);
      contextIcon.setVisible(true);
    }
  }
  
  private void initMenuIcon() {
    menuIcon = UIUtils.createTertiaryInlineButton(VaadinIcon.MENU);
    menuIcon.addClassName(CLASS_NAME + "__navi-icon");

    /*
    if (APP_TYPE_ADM.equals(getType())) {
      menuIcon.addClickListener(e -> GSAdminLayout.get().getNaviDrawer().toggle());
    } else {
      menuIcon.addClickListener(e -> GSMainLayout.get().getNaviDrawer().toggle());
    }*/
    
    menuIcon.addClickListener(e -> GSMainLayout.get().getNaviDrawer().toggle());
    
    UIUtils.setAriaLabel("Menu", menuIcon);
    UIUtils.setLineHeight("1", menuIcon);
  }
  
  private void initContextIcon() {
    contextIcon = UIUtils.createTertiaryInlineButton(VaadinIcon.ARROW_LEFT);
    contextIcon.addClassNames(CLASS_NAME + "__context-icon");
    contextIcon.setVisible(false);
    UIUtils.setAriaLabel("Back", contextIcon);
    UIUtils.setLineHeight("1", contextIcon);
  }
  
  private void initTitle(String title) {
    this.title = new H4(title);
    this.title.setClassName(CLASS_NAME + "__title");
  }
  
  private void initSearch() {
    search = new TextField();
    search.setPlaceholder("Search");
    search.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
    search.setVisible(false);
  }
  
  private void initAvatar() {
    /*
    avatar = new Image();
    avatar.setClassName(CLASS_NAME + "__avatar");
    //avatar.setSrc(IMG_PATH + "avatar.png");
    avatar.setSrc(IMG_PATH + "chopper.png");
    avatar.setAlt("User menu");
    */
    
    avatar = VaadinIcon.USER_CARD.create();
    avatar.setClassName(CLASS_NAME + "__avatar");
    // avatar.setSize("16px");
    
    ContextMenu contextMenu = new ContextMenu(avatar);
    contextMenu.getStyle().set("font-size", "13px");
    contextMenu.setOpenOnClick(true);
    
    VDLabel profileLabel = new VDLabel("個人資訊");
    MenuItem profile = contextMenu.addItem(profileLabel, e -> showProfile());
    Icon user = VaadinIcon.USER.create();
    user.setSize("13px");
    user.setColor("#00b4f0");
    user.getStyle().set("margin-right", "5px");
    profile.addComponentAsFirst(user);
    
    VDLabel passwordLabel = new VDLabel("變更密碼");
    MenuItem pwdItem = contextMenu.addItem(passwordLabel, e -> changePassword());
    Icon keyIcon = VaadinIcon.KEY.create();
    keyIcon.setSize("13px");
    keyIcon.setColor("#965323");
    keyIcon.getStyle().set("margin-right", "5px");
    pwdItem.addComponentAsFirst(keyIcon);
    
    VDLabel exitLabel = new VDLabel("登出");
    MenuItem logout = contextMenu.addItem(exitLabel, e -> logout());
    Icon exit = VaadinIcon.EXIT.create();
    exit.setSize("13px");
    exit.setColor("#701a3b");
    exit.getStyle().set("margin-right", "5px");
    logout.addComponentAsFirst(exit);
  }
  
  private void showProfile() {
    GSMemberProfile detailForm = new GSMemberProfile();
    detailForm.setViewMode(ViewMode.View);
    
    GSCommonDetailDialog dialog = new GSCommonDetailDialog(detailForm);
    dialog.setWidth("600px");
    dialog.setHeight("300px");
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
  }
  
  private void changePassword() {
    GSMemberChangePassword pwdForm = new GSMemberChangePassword();
    pwdForm.setViewMode(ViewMode.Modify);
    
    GSCommonDetailDialog dialog = new GSCommonDetailDialog(pwdForm);
    dialog.setWidth("300px");
    dialog.setHeight("350px");
    dialog.setCloseOnOutsideClick(false);
    dialog.setCloseOnEsc(false);
    dialog.open();
  }
  
  private void logout() {
    // UI.getCurrent().navigate("logout");
    SecurityContextHolder.clearContext();
    VaadinSession.getCurrent().close();
    UI.getCurrent().getPage().setLocation("login");
  }
  
  private void initActionItems() {
    actionItems = new VDFlexBoxLayout();
    actionItems.addClassName(CLASS_NAME + "__action-items");
    actionItems.setVisible(false);
  }
  
  public void setUserName(String value) {
    nameLabel.setText(value);
  }
  
  public void setBulletinMessage(String message) {
    bulletinLayout.remove(bulletinField);
    bulletinField = new VDLabel(message);
    bulletinField.addClassName("dcsplab-bulletin-note");
    
    bulletinLayout.add(bulletinField);
  }
  
  private void initContainer() {
    nameLabel = new VDLabel();
    
    VDLabel btitle = new VDLabel("公佈欄:");
    btitle.getStyle().set("margin-right", "3px");
    btitle.getStyle().set("margin-left", "12px");
    btitle.getStyle().set("color", "#9393FF");
    
    bulletinField = new VDLabel("正在取得公佈欄資訊 ...");
    bulletinField.addClassName("dcsplab-bulletin-note");
    
    bulletinLayout = new HorizontalLayout();
    bulletinLayout.setVerticalComponentAlignment(Alignment.CENTER, btitle, bulletinField);
    bulletinLayout.add(btitle, bulletinField);
    bulletinLayout.addClassName("dcsplab-bulletin-layout");
    
    container =
      new VDFlexBoxLayout(
        menuIcon,
        contextIcon,
        this.title,
        search,
        actionItems,
        bulletinLayout,
        nameLabel,
        avatar);
    container.addClassName(CLASS_NAME + "__container");
    container.setAlignItems(FlexComponent.Alignment.CENTER);
    container.setFlexGrow(1, search);
    add(container);
  }
  
  private void initTabs(NaviTab... tabs) {
    addTab = UIUtils.createSmallButton(VaadinIcon.PLUS);
    addTab.addClickListener(
      e -> this.tabs.setSelectedTab(addClosableNaviTab("New Tab", Home.class)));
    addTab.setVisible(false);
    
    this.tabs = tabs.length > 0 ? new NaviTabs(tabs) : new NaviTabs();
    this.tabs.setClassName(CLASS_NAME + "__tabs");
    this.tabs.setVisible(false);
    for (NaviTab tab : tabs) {
      configureTab(tab);
    }
    
    this.tabSelectionListeners = new ArrayList<>();
    
    tabContainer = new VDFlexBoxLayout(this.tabs, addTab);
    tabContainer.addClassName(CLASS_NAME + "__tab-container");
    tabContainer.setAlignItems(FlexComponent.Alignment.CENTER);
    add(tabContainer);
  }
  
  public Button getMenuIcon() {
    return menuIcon;
  }
  
  /* === MENU ICON === */
  
  public Button getContextIcon() {
    return contextIcon;
  }
  
  /* === CONTEXT ICON === */
  
  public void setContextIcon(Icon icon) {
    contextIcon.setIcon(icon);
  }
  
  public String getTitle() {
    return this.title.getText();
  }
  
  /* === TITLE === */
  
  public void setTitle(String title) {
    this.title.setText(title);
  }
  
  public Component addActionItem(Component component) {
    actionItems.add(component);
    updateActionItemsVisibility();
    return component;
  }
  
  /* === ACTION ITEMS === */
  
  public Button addActionItem(VaadinIcon icon) {
    Button button =
      UIUtils.createButton(icon, ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
    addActionItem(button);
    return button;
  }
  
  public void removeAllActionItems() {
    actionItems.removeAll();
    updateActionItemsVisibility();
  }
  
  public Icon getAvatar() {
    return avatar;
  }
  
  /* === AVATAR == */
  
  public void centerTabs() {
    tabs.addClassName(LumoStyles.Margin.Horizontal.AUTO);
  }
  
  /* === TABS === */
  
  private void configureTab(Tab tab) {
    tab.addClassName(CLASS_NAME + "__tab");
    updateTabsVisibility();
  }
  
  public Tab addTab(String text) {
    Tab tab = tabs.addTab(text);
    configureTab(tab);
    return tab;
  }
  
  public Tab addTab(String text, Class<? extends Component> navigationTarget) {
    Tab tab = tabs.addTab(text, navigationTarget);
    configureTab(tab);
    return tab;
  }
  
  public Tab addClosableNaviTab(String text, Class<? extends Component> navigationTarget) {
    Tab tab = tabs.addClosableTab(text, navigationTarget);
    configureTab(tab);
    return tab;
  }
  
  public Tab getSelectedTab() {
    return tabs.getSelectedTab();
  }
  
  public void setSelectedTab(Tab selectedTab) {
    tabs.setSelectedTab(selectedTab);
  }
  
  public void updateSelectedTab(String text, Class<? extends Component> navigationTarget) {
    tabs.updateSelectedTab(text, navigationTarget);
  }
  
  public void navigateToSelectedTab() {
    tabs.navigateToSelectedTab();
  }
  
  public void addTabSelectionListener(ComponentEventListener<Tabs.SelectedChangeEvent> listener) {
    Registration registration = tabs.addSelectedChangeListener(listener);
    tabSelectionListeners.add(registration);
  }
  
  public int getTabCount() {
    return tabs.getTabCount();
  }
  
  public void removeAllTabs() {
    tabSelectionListeners.forEach(registration -> registration.remove());
    tabSelectionListeners.clear();
    tabs.removeAll();
    updateTabsVisibility();
  }
  
  public void setAddTabVisible(boolean visible) {
    addTab.setVisible(visible);
  }
  
  /* === ADD TAB BUTTON === */
  
  public void searchModeOn() {
    menuIcon.setVisible(false);
    title.setVisible(false);
    actionItems.setVisible(false);
    tabContainer.setVisible(false);
    
    contextIcon.setIcon(new Icon(VaadinIcon.ARROW_BACKWARD));
    contextIcon.setVisible(true);
    searchRegistration = contextIcon.addClickListener(e -> searchModeOff());
    
    search.setVisible(true);
    search.focus();
  }
  
  /* === SEARCH === */
  
  public void addSearchListener(HasValue.ValueChangeListener listener) {
    search.addValueChangeListener(listener);
  }
  
  public void setSearchPlaceholder(String placeholder) {
    search.setPlaceholder(placeholder);
  }
  
  private void searchModeOff() {
    menuIcon.setVisible(true);
    title.setVisible(true);
    tabContainer.setVisible(true);
    
    updateActionItemsVisibility();
    updateTabsVisibility();
    
    contextIcon.setVisible(false);
    searchRegistration.remove();
    
    search.clear();
    search.setVisible(false);
  }
  
  public void reset() {
    title.setText("");
    setNaviMode(AppBar.NaviMode.MENU);
    removeAllActionItems();
    removeAllTabs();
  }
  
  /* === RESET === */
  
  private void updateActionItemsVisibility() {
    actionItems.setVisible(actionItems.getComponentCount() > 0);
  }
  
  /* === UPDATE VISIBILITY === */
  
  private void updateTabsVisibility() {
    tabs.setVisible(tabs.getComponentCount() > 0);
  }
  
  public String getType() {
    return type;
  }
  
  public void setType(String type) {
    this.type = type;
  }
  
  public enum NaviMode {
    MENU,
    CONTEXTUAL
  }
}
