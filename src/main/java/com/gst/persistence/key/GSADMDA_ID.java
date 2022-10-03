package com.gst.persistence.key;

public class GSADMDA_ID extends GSDomainKeyBase {
  
  private static final long serialVersionUID = -5942802798156255051L;
  
  private String company;
  
  private String DA001;
  
  private String DA002;
  
  public GSADMDA_ID() {
  }
  
  public String getCompany() {
    return company;
  }
  
  public void setCompany(String company) {
    this.company = company;
  }
  
  public String getDA001() {
    return DA001;
  }
  
  public void setDA001(String dA001) {
    DA001 = dA001;
  }
  
  public String getDA002() {
    return DA002;
  }
  
  public void setDA002(String dA002) {
    DA002 = dA002;
  }
  
  @Override
  public int hashCode() {
    int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
    result = HASH_PRIME * result + ((DA001 == null) ? 0 : DA001.hashCode());
    result = HASH_PRIME * result + ((DA002 == null) ? 0 : DA002.hashCode());
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
    
    GSADMDA_ID other = (GSADMDA_ID) obj;
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
  
  private boolean matchAttributes(GSADMDA_ID other) {
    if (DA001 == null) {
      if (other.DA001 != null) {
        return false;
      }
    } else if (!DA001.equals(other.DA001)) {
      return false;
    }
    
    if (DA002 == null) {
      return other.DA002 == null;
    } else {
      return DA002.equals(other.DA002);
    }
  }
}
