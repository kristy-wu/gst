package com.gst.domain;

import com.gst.context.GSAbstractObject;

import java.util.HashMap;

public abstract class GSAuthorization extends GSAbstractObject {
  
  private static final long serialVersionUID = 8355989342049428957L;
  private final HashMap<Action, Boolean> accMap = new HashMap<>();
  
  private boolean hasCreateAccess = false;
  
  private boolean hasSearchAccess = false;
  
  private boolean hasModifyAccess = false;
  
  private boolean hasDeleteAccess = false;
  
  private boolean hasConfirmAccess = false;
  
  private boolean hasCancelAccess = false;
  
  private boolean hasObsoleteAccess = false;
  
  private String acc;
  
  public void populateAccess(String accString) {
    if (accString == null) {
      accString = "NNNNNNN";
    }
    this.acc = accString;
    
    hasCreateAccess = 'Y' == accString.charAt(0);
    accMap.put(Action.Create, hasCreateAccess);
    
    hasSearchAccess = 'Y' == accString.charAt(1);
    accMap.put(Action.Search, hasSearchAccess);
    
    hasModifyAccess = 'Y' == accString.charAt(2);
    accMap.put(Action.Modify, hasModifyAccess);
    
    hasDeleteAccess = 'Y' == accString.charAt(3);
    accMap.put(Action.Delete, hasDeleteAccess);
    
    hasConfirmAccess = 'Y' == accString.charAt(4);
    accMap.put(Action.Confirm, hasConfirmAccess);
    
    hasCancelAccess = 'Y' == accString.charAt(5);
    accMap.put(Action.Cancel, hasCancelAccess);
    
    hasObsoleteAccess = 'Y' == accString.charAt(6);
    accMap.put(Action.Obsolete, hasObsoleteAccess);
  }
  
  public String buildAccString() {
    
    this.acc =
      String.valueOf(hasCreateAccess ? 'Y' : 'N')
        + (hasSearchAccess ? 'Y' : 'N')
        + (hasModifyAccess ? 'Y' : 'N')
        + (hasDeleteAccess ? 'Y' : 'N')
        + (hasConfirmAccess ? 'Y' : 'N')
        + (hasCancelAccess ? 'Y' : 'N')
        + (hasObsoleteAccess ? 'Y' : 'N');
    
    return this.acc;
  }
  
  public boolean hasAccess(Action action) {
    return accMap.get(action);
  }
  
  public boolean hasCreateAccess() {
    return hasCreateAccess;
  }
  
  public void setCreateAccess(boolean acc) {
    hasCreateAccess = acc;
  }
  
  public boolean hasSearchAccess() {
    return hasSearchAccess;
  }
  
  public void setSearchAccess(boolean acc) {
    hasSearchAccess = acc;
  }
  
  public boolean hasModifyAccess() {
    return hasModifyAccess;
  }
  
  public void setModifyAccess(boolean acc) {
    hasModifyAccess = acc;
  }
  
  public boolean hasDeleteAccess() {
    return hasDeleteAccess;
  }
  
  public void setDeleteAccess(boolean acc) {
    hasDeleteAccess = acc;
  }
  
  public boolean hasConfirmAccess() {
    return hasConfirmAccess;
  }
  
  public void setConfirmAccess(boolean acc) {
    hasConfirmAccess = acc;
  }
  
  public boolean hasCancelAccess() {
    return hasCancelAccess;
  }
  
  public void setCancelAccess(boolean acc) {
    hasCancelAccess = acc;
  }
  
  public boolean hasObsoleteAccess() {
    return hasObsoleteAccess;
  }
  
  public void setObsoleteAccess(boolean acc) {
    hasObsoleteAccess = acc;
  }
  
  public enum Action {
    Create,
    Search,
    Modify,
    Delete,
    Confirm,
    Cancel,
    Obsolete
  }
}
