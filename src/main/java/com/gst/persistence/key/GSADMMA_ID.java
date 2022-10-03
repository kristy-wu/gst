package com.gst.persistence.key;

public class GSADMMA_ID extends GSDomainKeyBase {
  
  private static final long serialVersionUID = 6002930908685660244L;
  
  private String company;
  
  private String MA001;
  
  private String MA002;
  
  public GSADMMA_ID() {
  }
  
  public String getCompany() {
    return company;
  }
  
  public void setCompany(String company) {
    this.company = company;
  }
  
  public String getMA001() {
    return MA001;
  }
  
  public void setMA001(String mA001) {
    MA001 = mA001;
  }
  
  public String getMA002() {
    return MA002;
  }
  
  public void setMA002(String mA002) {
    MA002 = mA002;
  }
  
  @Override
  public int hashCode() {
    int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
    result = HASH_PRIME * result + ((MA001 == null) ? 0 : MA001.hashCode());
    result = HASH_PRIME * result + ((MA002 == null) ? 0 : MA002.hashCode());
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
    
    GSADMMA_ID other = (GSADMMA_ID) obj;
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
  
  private boolean matchAttributes(GSADMMA_ID other) {
    if (MA001 == null) {
      if (other.MA001 != null) {
        return false;
      }
    } else if (!MA001.equals(other.MA001)) {
      return false;
    }
    
    if (MA002 == null) {
      return other.MA002 == null;
    } else {
      return MA002.equals(other.MA002);
    }
  }
}
