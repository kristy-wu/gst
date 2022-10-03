package com.gst.persistence.key;

public class GSCMSEA_ID extends GSDomainKeyBase {
  
  private static final long serialVersionUID = 8888663530904011653L;
  
  private String company;
  
  private String EA001;
  
  private String EA002;
  
  public GSCMSEA_ID() {
  }
  
  public String getCompany() {
    return company;
  }
  
  public void setCompany(String company) {
    this.company = company;
  }
  
  public String getEA001() {
    return EA001;
  }
  
  public void setEA001(String eA001) {
    EA001 = eA001;
  }
  
  public String getEA002() {
    return EA002;
  }
  
  public void setEA002(String eA002) {
    EA002 = eA002;
  }
  
  @Override
  public int hashCode() {
    int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
    result = HASH_PRIME * result + ((EA001 == null) ? 0 : EA001.hashCode());
    result = HASH_PRIME * result + ((EA002 == null) ? 0 : EA002.hashCode());
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
    
    GSCMSEA_ID other = (GSCMSEA_ID) obj;
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
  
  private boolean matchAttributes(GSCMSEA_ID other) {
    if (EA001 == null) {
      if (other.EA001 != null) {
        return false;
      }
    } else if (!EA001.equals(other.EA001)) {
      return false;
    }
    
    if (EA002 == null) {
      return other.EA002 == null;
    } else {
      return EA002.equals(other.EA002);
    }
  }
}
