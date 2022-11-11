package com.gst.persistence.key;

public class GSCMSGA_ID extends GSDomainKeyBase {

    private static final long serialVersionUID = -4441603768177009579L;

    private String company;

    private String GA001;

    public GSCMSGA_ID() {
    }

    @Override
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGA001() {
        return GA001;
    }

    public void setGA001(String GA001) {
        this.GA001 = GA001;
    }

    @Override
    public int hashCode() {
        int result = HASH_PRIME * HASH_START + ((company == null) ? 0 : company.hashCode());
        result = HASH_PRIME * result + ((GA001 == null) ? 0 : GA001.hashCode());

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

        GSCMSGA_ID other = (GSCMSGA_ID) obj;
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

    private boolean matchAttributes(GSCMSGA_ID other) {
        if (GA001 == null) {
            return other.GA001 == null;
        } else {
            return GA001.equals(other.GA001);
        }
    }
}
