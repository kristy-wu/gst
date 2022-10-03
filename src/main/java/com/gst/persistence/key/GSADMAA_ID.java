package com.gst.persistence.key;

public class GSADMAA_ID extends GSDomainKeyBase {
  
  private static final long serialVersionUID = 1275132765461492620L;
  
  private String company;
  
  private String AA001;
  
  public GSADMAA_ID() {
  }
  
  public String getCompany() {
    return company;
  }
  
  public void setCompany(String company) {
    this.company = company;
  }
  
  @Override
  public int hashCode() {
    int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
    result = HASH_PRIME * result + ((AA001 == null) ? 0 : AA001.hashCode());
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
    
    GSADMAA_ID other = (GSADMAA_ID) obj;
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
  
  private boolean matchAttributes(GSADMAA_ID other) {
    if (AA001 == null) {
      return other.AA001 == null;
    } else {
      return AA001.equals(other.AA001);
    }
  }
  
  public String getAA001() {
    return AA001;
  }
  
  public void setAA001(String aA001) {
    AA001 = aA001;
  }
}
