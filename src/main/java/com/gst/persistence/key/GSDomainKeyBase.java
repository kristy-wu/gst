package com.gst.persistence.key;

import java.io.Serializable;

public abstract class GSDomainKeyBase implements Serializable {
  
  public static final int HASH_START = 7;
  
  public static final int HASH_PRIME = 31;
  
  private static final long serialVersionUID = -1013301066050898227L;
  
  public GSDomainKeyBase() {
  }
  
  public abstract String getCompany();
}
