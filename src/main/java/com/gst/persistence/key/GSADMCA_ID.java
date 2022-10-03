package com.gst.persistence.key;

public class GSADMCA_ID extends GSDomainKeyBase {
  
  private static final long serialVersionUID = -9000797200680659017L;
  
  private String company;
  
  private String CA001;
  
  public GSADMCA_ID() {
  }
  
  public String getCompany() {
    return company;
  }
  
  public void setCompany(String company) {
    this.company = company;
  }
  
  public String getCA001() {
    return CA001;
  }
  
  public void setCA001(String cA001) {
    CA001 = cA001;
  }
  
  @Override
  public int hashCode() {
    int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
    result = HASH_PRIME * result + ((CA001 == null) ? 0 : CA001.hashCode());
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
    
    GSADMCA_ID other = (GSADMCA_ID) obj;
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
  
  private boolean matchAttributes(GSADMCA_ID other) {
    if (CA001 == null) {
      return other.CA001 == null;
    } else {
      return CA001.equals(other.CA001);
    }
  }
}
