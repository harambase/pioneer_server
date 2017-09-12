package com.harambase.pioneer.pojo;

import java.io.Serializable;

public class TempUser implements Serializable {
    private Integer id;

    private String userid;

    private String userJson;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUserJson() {
        return userJson;
    }

    public void setUserJson(String userJson) {
        this.userJson = userJson == null ? null : userJson.trim();
    }
}