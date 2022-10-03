package com.dcsplab.security;

import com.gst.security.GSUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@Configuration
public class DLSecurityConfiguration extends WebSecurityConfigurerAdapter {
  private static final Logger logger = LoggerFactory.getLogger(DLSecurityConfiguration.class);
  
  private static final String LOGIN_PROCESSING_URL = "/login";
  
  private static final String LOGIN_FAILURE_URL = "/login?error";
  
  private static final String LOGIN_URL = "/login";
  
  private static final String LOGOUT_SUCCESS_URL = "/login";
  
  @Autowired
  GSUserDetailsService userService;
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(new DLPasswordEncoder());
  }
  
  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    return userService;
  }
  
  @Override
  public void configure(WebSecurity web) {
    web.ignoring()
      .antMatchers(
        "/VAADIN/**",
        "/favicon.ico",
        "/robots.txt",
        "/manifest.webmanifest",
        "/sw.js",
        "/offline.html",
        "/icons/**",
        "/images/**",
        "/styles/**",
        "/h2-console/**");
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().maximumSessions(1);
    /*
     *  use this to enable the spring security
     */
    http.csrf()
      .disable()
      .requestCache()
      .requestCache(new DLCustomRequestCache())
      .and()
      .authorizeRequests()
      .requestMatchers(DLSecurityUtils::isFrameworkInternalRequest)
      .permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .formLogin()
      .loginPage(LOGIN_URL)
      .permitAll()
      .loginProcessingUrl(LOGIN_PROCESSING_URL)
      .failureUrl(LOGIN_FAILURE_URL)
      .and()
      .logout()
      .logoutSuccessUrl(LOGOUT_SUCCESS_URL);
    
    /*
     *  use this to disable the spring security
     */
    /*
    http.csrf().disable().requestCache()
    		.requestCache(new CustomRequestCache()).and().authorizeRequests()
    		.requestMatchers(SecurityUtils::isFrameworkInternalRequest)
    		.permitAll();
    */
  }
}
