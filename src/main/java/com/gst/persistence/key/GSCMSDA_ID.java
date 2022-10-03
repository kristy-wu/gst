package com.gst.persistence.key;

public class GSCMSDA_ID extends GSDomainKeyBase {
  
  private static final long serialVersionUID = 2458255973240970809L;
  
  private String company;
  
  private String DA001;
  
  public GSCMSDA_ID() {
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
  
  @Override
  public int hashCode() {
    int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
    result = HASH_PRIME * result + ((DA001 == null) ? 0 : DA001.hashCode());
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
    
    GSCMSDA_ID other = (GSCMSDA_ID) obj;
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
  
  private boolean matchAttributes(GSCMSDA_ID other) {
    if (DA001 == null) {
      return other.DA001 == null;
    } else {
      return DA001.equals(other.DA001);
    }
  }
}
