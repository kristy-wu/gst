package com.gst.security;

import com.gst.domain.GSMember;
import com.gst.service.GSAuthorizationService;
import com.gst.service.GSOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GSUserDetailsService implements UserDetailsService {
  
  private static final Logger logger = LoggerFactory.getLogger(GSUserDetailsService.class);
  
  @Autowired
  private GSOrganizationService orgService;
  
  @Autowired
  private GSAuthorizationService authService;
  
  @Value("${gst.default.company}")
  private String defaultCompany;
  
  public GSUserDetailsService() {
    super();
  }
  
  @Override
  public UserDetails loadUserByUsername(String memId) throws UsernameNotFoundException {
    logger.info("User[" + memId + "] is trying to login. ");
    
    //GSMember member = orgService.getMember(GSTBootApplication.COMPANY, memId);
    GSMember member = orgService.getMember(defaultCompany, memId);
    
    boolean hasManagerAccess = authService.hasAdministratorAccess(member);
    member.setManagerAccess(hasManagerAccess);
    return new GSUserDetails(member);
  }
}
