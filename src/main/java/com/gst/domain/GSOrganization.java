package com.gst.domain;

import com.gst.context.GSAbstractObject;

import java.util.List;

public abstract class GSOrganization extends GSAbstractObject {
  
  private static final long serialVersionUID = -6876868921211363807L;
  
  public GSOrganization() {
  }
  
  public abstract long getChildCount();
  
  public abstract boolean hasChildren();
  
  public abstract List<GSOrganization> getChildren();
}
