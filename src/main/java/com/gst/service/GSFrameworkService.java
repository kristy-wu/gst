package com.gst.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GSFrameworkService {
  private static final Logger logger = LoggerFactory.getLogger(GSFrameworkService.class);

  @Value("${gst.default.company}")
  private String defaultCompany;
}
