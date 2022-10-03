package com.dcsplab.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
 * BCrypt, it's usually the best solution available.
 * Most of the other mechanisms, such as the MD5PasswordEncoder and
 * ShaPasswordEncoder use weaker algorithms and are now deprecated.
 */
public class DLPasswordEncoder extends BCryptPasswordEncoder {
  
  static Logger logger = LoggerFactory.getLogger(DLPasswordEncoder.class);

  /*
  @Override
  public String encode(CharSequence rawPassword) {
  	return rawPassword.toString();
  }
  */
  
  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    logger.info("identifying password...");
    return super.matches(rawPassword, encodedPassword);
    
    // return rawPassword.toString().equals(encodedPassword);
  }
}
