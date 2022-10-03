package com.gst.persistence.key;

public class GSCMSCB_ID extends GSDomainKeyBase {
  
  private static final long serialVersionUID = -8239549332757685042L;
  
  private String company;
  
  private String CB001;
  
  private String CB002;
  
  public GSCMSCB_ID() {
  }
  
  public String getCompany() {
    return company;
  }
  
  public void setCompany(String company) {
    this.company = company;
  }
  
  public String getCB001() {
    return CB001;
  }
  
  public void setCB001(String cB001) {
    CB001 = cB001;
  }
  
  public String getCB002() {
    return CB002;
  }
  
  public void setCB002(String cB002) {
    CB002 = cB002;
  }
  
  @Override
  public int hashCode() {
    int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
    result = HASH_PRIME * result + ((CB001 == null) ? 0 : CB001.hashCode());
    result = HASH_PRIME * result + ((CB002 == null) ? 0 : CB002.hashCode());
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
    
    GSCMSCB_ID other = (GSCMSCB_ID) obj;
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
  
  private boolean matchAttributes(GSCMSCB_ID other) {
    if (CB001 == null) {
      if (other.CB001 != null) {
        return false;
      }
    } else if (!CB001.equals(other.CB001)) {
      return false;
    }
    
    if (CB002 == null) {
      return other.CB002 == null;
    } else {
      return CB002.equals(other.CB002);
    }
  }
}
