package com.gst.persistence.key;

public class GSADMSY_ID extends GSDomainKeyBase {

  private static final long serialVersionUID = 537583419107799842L;

  private String company;

  private String SY001;

  public GSADMSY_ID() {

  }

  @Override
  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getSY001() {
    return SY001;
  }

  public void setSY001(String SY001) {
    this.SY001 = SY001;
  }

  @Override
  public int hashCode() {
    int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
    result = HASH_PRIME * result + ((SY001 == null) ? 0 : SY001.hashCode());

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

    GSADMSY_ID other = (GSADMSY_ID) obj;
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

  private boolean matchAttributes(GSADMSY_ID other) {
    if (SY001 == null) {
      return other.SY001 == null;
    } else {
      return SY001.equals(other.SY001);
    }
  }
}
