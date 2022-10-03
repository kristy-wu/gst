package com.gst.persistence.key;

public class GSCMSBA_ID extends GSDomainKeyBase {
  
  private static final long serialVersionUID = -762891937617039685L;
  
  private String company;
  
  private String BA001;
  
  public GSCMSBA_ID() {
  }
  
  public String getCompany() {
    return company;
  }
  
  public void setCompany(String company) {
    this.company = company;
  }
  
  public String getBA001() {
    return BA001;
  }
  
  public void setBA001(String bA001) {
    BA001 = bA001;
  }
  
  @Override
  public int hashCode() {
    int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
    result = HASH_PRIME * result + ((BA001 == null) ? 0 : BA001.hashCode());
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
    
    GSCMSBA_ID other = (GSCMSBA_ID) obj;
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
  
  private boolean matchAttributes(GSCMSBA_ID other) {
    if (BA001 == null) {
      return other.BA001 == null;
    } else {
      return BA001.equals(other.BA001);
    }
  }
}
