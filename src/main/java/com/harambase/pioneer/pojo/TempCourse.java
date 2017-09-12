package com.harambase.pioneer.pojo;

import java.io.Serializable;

public class TempCourse implements Serializable {
    private Integer id;

    private String crn;

    private String json;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn == null ? null : crn.trim();
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json == null ? null : json.trim();
    }
}