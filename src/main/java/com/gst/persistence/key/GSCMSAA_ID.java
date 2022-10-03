package com.gst.persistence.key;

public class GSCMSAA_ID extends GSDomainKeyBase {
  
  private static final long serialVersionUID = 2406159624624200418L;
  
  private String company;
  
  private String AA001;
  
  private String AA004;
  
  public GSCMSAA_ID() {
  }
  
  public String getCompany() {
    return company;
  }
  
  public void setCompany(String company) {
    this.company = company;
  }
  
  public String getAA001() {
    return AA001;
  }
  
  public void setAA001(String aA001) {
    AA001 = aA001;
  }
  
  public String getAA004() {
    return AA004;
  }
  
  public void setAA004(String aA004) {
    AA004 = aA004;
  }
  
  @Override
  public int hashCode() {
    int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
    result = HASH_PRIME * result + ((AA001 == null) ? 0 : AA001.hashCode());
    result = HASH_PRIME * result + ((AA004 == null) ? 0 : AA004.hashCode());
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
    
    GSCMSAA_ID other = (GSCMSAA_ID) obj;
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
  
  private boolean matchAttributes(GSCMSAA_ID other) {
    if (AA001 == null) {
      if (other.AA001 != null) {
        return false;
      }
    } else if (!AA001.equals(other.AA001)) {
      return false;
    }
    
    if (AA004 == null) {
      return other.AA004 == null;
    } else {
      return AA004.equals(other.AA004);
    }
  }
}
