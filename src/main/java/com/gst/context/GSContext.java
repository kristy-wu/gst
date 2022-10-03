package com.gst.context;

import com.gst.GSTBootApplication;
import com.gst.domain.GSMember;
import com.gst.security.GSUserDetails;
import com.vaadin.flow.server.VaadinServlet;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.Objects;

public class GSContext {
  
  public static GSTBootApplication getApplication() {
    return Objects.requireNonNull(
        WebApplicationContextUtils.getWebApplicationContext(
          VaadinServlet.getCurrent().getServletContext()))
      .getBean(GSTBootApplication.class);
  }
  
  public static GSMember getCurrentMember() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    GSUserDetails userDetails = (GSUserDetails) authentication.getPrincipal();
    return userDetails.getMember();

    /*if (authentication instanceof AnonymousAuthenticationToken) {
      return null;
    }

    Object details = authentication.getPrincipal();
    if (details instanceof GSUserDetails) {
      GSUserDetails userDetails = (GSUserDetails) details;
      return userDetails.getMember();
    }

    return null;*/
  }
}
