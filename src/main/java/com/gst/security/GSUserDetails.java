package com.gst.security;

import com.gst.domain.GSMember;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class GSUserDetails implements UserDetails {
  private static final long serialVersionUID = -169804940237854423L;
  private final GSMember member;
  
  public GSUserDetails(GSMember data) {
    member = data;
  }
  
  public GSMember getMember() {
    return member;
  }
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public String getPassword() {
    return member.getPassword();
  }
  
  @Override
  public String getUsername() {
    return member.getName();
  }
  
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  
  @Override
  public boolean isEnabled() {
    return true;
  }
}
