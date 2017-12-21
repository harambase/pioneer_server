package com.harambase.pioneer.pojo.base;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tempuser")
public class TempUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Integer getId() {
        return id;
    }

    private String userid;

    private String status;

    private String createtime;

    private String updatetime;

    private String operator;

    private String userJson;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getUserJson() {
        return userJson;
    }

    public void setUserJson(String userJson) {
        this.userJson = userJson == null ? null : userJson.trim();
    }

}