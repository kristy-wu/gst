package com.gst.persistence.key;

public class GSADMEA_ID extends GSDomainKeyBase {
  
  private static final long serialVersionUID = -311262241279565257L;
  
  private String company;
  
  private String EA001;
  
  public GSADMEA_ID() {
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
  
  @Override
  public int hashCode() {
    /*
    int result = HASH_PRIME * HASH_START + ((getCompany() == null) ? 0 : getCompany().hashCode());
    result = HASH_PRIME * result + ((EA001 == null) ? 0 : EA001.hashCode());*/
    
    int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
    result = HASH_PRIME * result + ((EA001 == null) ? 0 : EA001.hashCode());
    
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
    
    GSADMEA_ID other = (GSADMEA_ID) obj;
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
  
  private boolean matchAttributes(GSADMEA_ID other) {
    if (EA001 == null) {
      return other.EA001 == null;
    } else {
      return EA001.equals(other.EA001);
    }
  }
}
