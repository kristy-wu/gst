package com.gst.project;

import com.gst.context.GSDomainObject;

import java.util.List;

public abstract class GSProjectWBS implements GSDomainObject {
  
  public abstract long getChildCount();
  
  public abstract boolean hasChildren();
  
  public abstract List<GSProjectWBS> getChildren();
  
  public abstract String getSequence();
  
  public abstract String getStartDisplay();
  
  public abstract String getFinishDisplay();
  
  public abstract String getActualStartDisplay();
  
  public abstract String getActualFinishDisplay();
}
