package com.gst.persistence.key;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GSMESTA_ID extends GSDomainKeyBase {
  
  private static final long serialVersionUID = -3301735044278418970L;
  
  private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
  
  private String company;
  
  private String TA001;
  
  private String TA002;
  
  private Date TA005;
  
  public GSMESTA_ID() {
  }
  
  public String getCompany() {
    return company;
  }
  
  public void setCompany(String company) {
    this.company = company;
  }
  
  public String getTA001() {
    return TA001;
  }
  
  public void setTA001(String TA001) {
    this.TA001 = TA001;
  }
  
  public String getTA002() {
    return TA002;
  }
  
  public void setTA002(String TA002) {
    this.TA002 = TA002;
  }
  
  public Date getTA005() {
    return TA005;
  }
  
  public void setTA005(Date TA005) {
    this.TA005 = TA005;
  }
  
  @Override
  public int hashCode() {
    int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
    result = HASH_PRIME * result + ((TA001 == null) ? 0 : TA001.hashCode());
    result = HASH_PRIME * result + ((TA002 == null) ? 0 : TA002.hashCode());
    result = HASH_PRIME * result + ((TA005 == null) ? 0 : TA005.hashCode());

    /*if (TA005 != null) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
      String st = sdf.format(TA005);
      result = HASH_PRIME * result + st.hashCode();
    }*/
    
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
    
    GSMESTA_ID other = (GSMESTA_ID) obj;
    if (company == null) {
      if (other.TA001 != null || other.TA002 != null || other.TA005 != null) {
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
  
  private boolean matchAttributes(GSMESTA_ID other) {
    
    if (TA005 == null) {
      if (other.TA005 != null) {
        return false;
      }
    } else if (!TA005.equals(other.TA005)) {
      String thisTA005 = sdf.format(TA005);
      String otherTA005 = sdf.format(other.TA005);
      if (!thisTA005.equals(otherTA005)) {
        return false;
      }
    }
    
    if (TA001 == null) {
      if (other.TA001 != null) {
        return false;
      }
    } else if (!TA001.equals(other.TA001)) {
      return false;
    }
    
    if (TA002 == null) {
      return other.TA002 == null;
    } else {
      return TA002.equals(other.TA002);
    }
  }
}
