package com.gst.security;

import com.dcsplab.vdui.layout.VDVerticalLayout;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.ReconnectDialogConfiguration;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.BootstrapListener;
import com.vaadin.flow.server.BootstrapPageResponse;
import org.jsoup.nodes.Element;

import static com.gst.GSTApplicationServlet.SERVER_RECONNECT_ALERT;

@CssImport(
  value = "./styles/dcsplab-login-styles.css",
  themeFor = "vaadin-login-*-wrapper vaadin-text-field vaadin-button")
@CssImport(value = "./styles/dcsplab-notification-card.css", themeFor = "vaadin-notification-card")
@Route("login")
@PageTitle("Login | GST-MES")
public class GSLoginView extends VDVerticalLayout implements BeforeEnterObserver, BootstrapListener {
  
  private static final long serialVersionUID = -2798868315657073315L;
  
  private final LoginOverlay login;
  
  public GSLoginView() {
    ReconnectDialogConfiguration configuration = UI.getCurrent().getReconnectDialogConfiguration();
    configuration.setDialogText(SERVER_RECONNECT_ALERT);
    
    H1 title = new H1();
    
    Image img = new Image("images/logos/27.png", "GST");
    img.setWidth("34px");
    img.getStyle().set("margin-left", "-5px");
    title.add(img);
    
    title.getStyle().set("color", "var(--lumo-base-color)");
    // title.getStyle().set("font-family", "Microsoft JhengHei");
    
    Text text = new Text("格尚資訊");
    Html h3 = new Html("<h3 style=\"color:white;\">MES-製造執行系統</h3>");
    VerticalLayout V1 = new VerticalLayout();
    V1.add(img, text, h3);
    title.add(V1);
    
    LoginI18n.Form form = new LoginI18n.Form();
    form.setTitle("登入");
    form.setSubmit("登入");
    form.setUsername("帳號");
    form.setPassword("密碼");
    
    LoginI18n.ErrorMessage em = new LoginI18n.ErrorMessage();
    em.setTitle("帳號或密碼錯誤");
    em.setMessage("請確認帳號密碼正確，並重新登入.");
    
    LoginI18n i18n = LoginI18n.createDefault();
    i18n.setForm(form);
    i18n.setErrorMessage(em);
    
    login = new LoginOverlay(i18n);
    
    login.setTitle(title);
    login.setDescription("GST-MES");
    
    login.setAction("login");
    login.setForgotPasswordButtonVisible(false);
    login.setOpened(true);
    add(login);
  }
  
  @Override
  public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
    // inform the user about an authentication error
    if (beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("error")) {
      login.setError(true);
    }
  }
  
  @Override
  public void modifyBootstrapPage(BootstrapPageResponse response) {
    final Element head = response.getDocument().head();
    head.append("<link rel=\"shortcut icon\" href=\"images/favicons/favicon-Small.ico\">");
  }
}
