package com.gst.persistence.key;

public class GSMESAA_ID extends GSDomainKeyBase {

    private static final long serialVersionUID = 6334163080147879222L;

    private String company;

    private String AA001;

    private String AA002;

    public GSMESAA_ID() {
    }

    @Override
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAA001() {
        return AA001;
    }

    public void setAA001(String AA001) {
        this.AA001 = AA001;
    }

    public String getAA002() {
        return AA002;
    }

    public void setAA002(String AA002) {
        this.AA002 = AA002;
    }
}