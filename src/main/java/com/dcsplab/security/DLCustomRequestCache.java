package com.dcsplab.security;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class DLCustomRequestCache extends HttpSessionRequestCache {
  
  @Override
  public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
    if (!DLSecurityUtils.isFrameworkInternalRequest(request)) {
      super.saveRequest(request, response);
    }
  }
}
