package com.gst.persistence.key;

public class GSMOCTA_ID extends GSDomainKeyBase {
  
  private static final long serialVersionUID = -8867905997665531887L;
  
  private String company;
  
  private String TA001;
  
  private String TA002;
  
  public GSMOCTA_ID() {
  }
  
  public String getCompany() {
    return company;
  }
  
  public void setCompany(String company) {
    this.company = company;
  }
  
  public String getTA001() {
    return TA001;
  }
  
  public void setTA001(String sTA001) {
    TA001 = sTA001;
  }
  
  public String getTA002() {
    return TA002;
  }
  
  public void setTA002(String sTA002) {
    TA002 = sTA002;
  }
  
  @Override
  public int hashCode() {
    int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
    result = HASH_PRIME * result + ((TA001 == null) ? 0 : TA001.hashCode());
    result = HASH_PRIME * result + ((TA002 == null) ? 0 : TA002.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (obj == null) {
      return false;
    }
    
    if (getClass() != obj.getClass()) {
      return false;
    }
    
    GSMOCTA_ID other = (GSMOCTA_ID) obj;
    if (company == null) {
      if (other.company != null) {
        return false;
      }
      return matchAttributes(other);
    } else {
      if (!matchAttributes(other)) {
        return false;
      }
      return company.equals(other.company);
    }
  }
  
  private boolean matchAttributes(GSMOCTA_ID other) {
    if (TA001 == null) {
      if (other.TA001 != null) {
        return false;
      }
    } else if (!TA001.equals(other.TA001)) {
      return false;
    }
    
    if (TA002 == null) {
      return other.TA002 == null;
    } else {
      return TA002.equals(other.TA002);
    }
  }
}
