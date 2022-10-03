package com.gst.persistence.key;

public class GSADMMB_ID extends GSDomainKeyBase {
  
  private static final long serialVersionUID = -3328366060261810782L;
  
  private String company;
  
  private String MB001;
  
  private String MB002;
  
  public GSADMMB_ID() {
  }
  
  public String getCompany() {
    return company;
  }
  
  public void setCompany(String company) {
    this.company = company;
  }
  
  public String getMB001() {
    return MB001;
  }
  
  public void setMB001(String mB001) {
    MB001 = mB001;
  }
  
  public String getMB002() {
    return MB002;
  }
  
  public void setMB002(String mB002) {
    MB002 = mB002;
  }
  
  @Override
  public int hashCode() {
    int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
    result = HASH_PRIME * result + ((MB001 == null) ? 0 : MB001.hashCode());
    result = HASH_PRIME * result + ((MB001 == null) ? 0 : MB001.hashCode());
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
    
    GSADMMB_ID other = (GSADMMB_ID) obj;
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
  
  private boolean matchAttributes(GSADMMB_ID other) {
    if (MB001 == null) {
      if (other.MB001 != null) {
        return false;
      }
    } else if (!MB001.equals(other.MB001)) {
      return false;
    }
    
    if (MB002 == null) {
      return other.MB002 == null;
    } else {
      return MB002.equals(other.MB002);
    }
  }
}
