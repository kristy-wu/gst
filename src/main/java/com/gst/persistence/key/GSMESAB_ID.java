package com.gst.persistence.key;

public class GSMESAB_ID extends GSDomainKeyBase {

    private static final long serialVersionUID = -8026882098344702654L;

    private String company;

    private String AB001;

    private String AB002;

    public GSMESAB_ID() {
    }

    @Override
    public String getCompany() {
        return null;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAB001() {
        return AB001;
    }

    public void setAB001(String AB001) {
        this.AB001 = AB001;
    }

    public String getAB002() {
        return AB002;
    }

    public void setAB002(String AB002) {
        this.AB002 = AB002;
    }
}
