package com.harambase.pioneer.pojo.dto;

import com.harambase.pioneer.pojo.MessageWithBLOBs;

import java.io.Serializable;

public class MessageView extends MessageWithBLOBs implements Serializable {

    private String semail;
    private String sfirst;
    private String slast;
    private String pemail;
    private String pfirst;
    private String plast;
    private String role_name;

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public String getSfirst() {
        return sfirst;
    }

    public void setSfirst(String sfirst) {
        this.sfirst = sfirst;
    }

    public String getSlast() {
        return slast;
    }

    public void setSlast(String slast) {
        this.slast = slast;
    }

    public String getPemail() {
        return pemail;
    }

    public void setPemail(String pemail) {
        this.pemail = pemail;
    }

    public String getPfirst() {
        return pfirst;
    }

    public void setPfirst(String pfirst) {
        this.pfirst = pfirst;
    }

    public String getPlast() {
        return plast;
    }

    public void setPlast(String plast) {
        this.plast = plast;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
