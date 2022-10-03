package com.gst.persistence.key;

public class GSCMSMR_ID extends GSDomainKeyBase {

  private static final long serialVersionUID = 6304155884246670171L;
  private String company;

  private String MR001;

  private String MR002;

  public GSCMSMR_ID() {

  }

  @Override
  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getMR001() {
    return MR001;
  }

  public void setMR001(String MR001) {
    this.MR001 = MR001;
  }

  public String getMR002() {
    return MR002;
  }

  public void setMR002(String MR002) {
    this.MR002 = MR002;
  }

  @Override
  public int hashCode() {
    int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
    result = HASH_PRIME * result + ((MR001 == null) ? 0 : MR001.hashCode());
    result = HASH_PRIME * result + ((MR002 == null) ? 0 : MR002.hashCode());
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

    GSCMSMR_ID other = (GSCMSMR_ID) obj;
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

  private boolean matchAttributes(GSCMSMR_ID other) {
    if (MR001 == null) {
      if (other.MR001 != null) {
        return false;
      }
    } else if (!MR001.equals(other.MR001)) {
      return false;
    }

    if (MR002 == null) {
      return other.MR002 == null;
    } else {
      return MR002.equals(other.MR002);
    }
  }
}
