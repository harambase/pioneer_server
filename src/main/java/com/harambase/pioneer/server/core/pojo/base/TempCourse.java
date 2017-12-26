package com.harambase.pioneer.server.core.pojo.base;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tempcourse")
public class TempCourse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String crn;
    private String status;
    private String createtime;
    private String updatetime;
    private String operator;
    private String courseJson;

    public Integer getId() {
        return id;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn == null ? null : crn.trim();
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

    public String getCourseJson() {
        return courseJson;
    }

    public void setCourseJson(String courseJson) {
        this.courseJson = courseJson == null ? null : courseJson.trim();
    }

}