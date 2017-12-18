package com.harambase.pioneer.pojo.base;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class StudentBase extends BaseDomain{

    private String studentid;

    private Integer maxCredits;

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid == null ? null : studentid.trim();
    }

    public Integer getMaxCredits() {
        return maxCredits;
    }

    public void setMaxCredits(Integer maxCredits) {
        this.maxCredits = maxCredits;
    }

}