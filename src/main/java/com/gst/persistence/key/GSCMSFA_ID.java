package com.gst.persistence.key;

public class GSCMSFA_ID extends GSDomainKeyBase {
  
  private static final long serialVersionUID = 8587610297089778833L;
  
  private String company;
  
  private String FA001;
  
  public GSCMSFA_ID() {
  }
  
  public String getCompany() {
    return company;
  }
  
  public void setCompany(String company) {
    this.company = company;
  }
  
  public String getFA001() {
    return FA001;
  }
  
  public void setFA001(String fA001) {
    FA001 = fA001;
  }
  
  @Override
  public int hashCode() {
    int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
    result = HASH_PRIME * result + ((FA001 == null) ? 0 : FA001.hashCode());
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
    
    GSCMSFA_ID other = (GSCMSFA_ID) obj;
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
  
  private boolean matchAttributes(GSCMSFA_ID other) {
    if (FA001 == null) {
      return other.FA001 == null;
    } else {
      return FA001.equals(other.FA001);
    }
  }
}
