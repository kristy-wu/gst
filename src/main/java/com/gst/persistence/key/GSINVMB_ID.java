package com.gst.persistence.key;

public class GSINVMB_ID extends GSDomainKeyBase {

  private static final long serialVersionUID = -2892171115957718482L;
  private String company;

  private String MB001;

  public GSINVMB_ID() {

  }

  @Override
  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getMB001() {
    return MB001;
  }

  public void setMB001(String MB001) {
    this.MB001 = MB001;
  }

  @Override
  public int hashCode() {
    int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
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

    GSINVMB_ID other = (GSINVMB_ID) obj;
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

  private boolean matchAttributes(GSINVMB_ID other) {
    if (MB001 == null) {
      return other.MB001 == null;
    } else {
      return MB001.equals(other.MB001);
    }
  }
}
