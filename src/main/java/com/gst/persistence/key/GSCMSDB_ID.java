package com.gst.persistence.key;

public class GSCMSDB_ID extends GSDomainKeyBase {
  
  private static final long serialVersionUID = -8587733057644776467L;
  
  private String company;
  
  private String DB001;
  
  private String DB002;
  
  public GSCMSDB_ID() {
  }
  
  public String getCompany() {
    return company;
  }
  
  public void setCompany(String company) {
    this.company = company;
  }
  
  public String getDB001() {
    return DB001;
  }
  
  public void setDB001(String dB001) {
    DB001 = dB001;
  }
  
  public String getDB002() {
    return DB002;
  }
  
  public void setDB002(String dB002) {
    DB002 = dB002;
  }
  
  @Override
  public int hashCode() {
    int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
    result = HASH_PRIME * result + ((DB001 == null) ? 0 : DB001.hashCode());
    result = HASH_PRIME * result + ((DB002 == null) ? 0 : DB002.hashCode());
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
    
    GSCMSDB_ID other = (GSCMSDB_ID) obj;
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
  
  private boolean matchAttributes(GSCMSDB_ID other) {
    if (DB001 == null) {
      if (other.DB001 != null) {
        return false;
      }
    } else if (!DB001.equals(other.DB001)) {
      return false;
    }
    
    if (DB002 == null) {
      return other.DB002 == null;
    } else {
      return DB002.equals(other.DB002);
    }
  }
}
