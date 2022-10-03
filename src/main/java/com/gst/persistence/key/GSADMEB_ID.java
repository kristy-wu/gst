package com.gst.persistence.key;

public class GSADMEB_ID extends GSDomainKeyBase {
  
  private static final long serialVersionUID = -5087288782802708688L;
  
  private String company;
  
  private String EB001;
  
  private String EB002;
  
  public GSADMEB_ID() {
  }
  
  public String getCompany() {
    return company;
  }
  
  public void setCompany(String company) {
    this.company = company;
  }
  
  public String getEB001() {
    return EB001;
  }
  
  public void setEB001(String eB001) {
    EB001 = eB001;
  }
  
  public String getEB002() {
    return EB002;
  }
  
  public void setEB002(String eB002) {
    EB002 = eB002;
  }
  
  @Override
  public int hashCode() {
    int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
    result = HASH_PRIME * result + ((EB001 == null) ? 0 : EB001.hashCode());
    result = HASH_PRIME * result + ((EB002 == null) ? 0 : EB002.hashCode());
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
    
    GSADMEB_ID other = (GSADMEB_ID) obj;
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
  
  private boolean matchAttributes(GSADMEB_ID other) {
    if (EB001 == null) {
      if (other.EB001 != null) {
        return false;
      }
    } else if (!EB001.equals(other.EB001)) {
      return false;
    }
    
    if (EB002 == null) {
      return other.EB002 == null;
    } else {
      return EB002.equals(other.EB002);
    }
  }
}
