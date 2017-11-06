package com.harambase.pioneer.pojo.dto;

import com.harambase.pioneer.pojo.Advise;

import java.io.Serializable;

public class AdviseView extends Advise implements Serializable {
    private String sname;
    private String fname;
    private String oname;

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
