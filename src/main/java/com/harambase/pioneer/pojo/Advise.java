package com.harambase.pioneer.pojo;

import java.io.Serializable;

public class Advise implements Serializable {
    private Integer id;

    private Integer studentid;

    private Integer facultyid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public Integer getFacultyid() {
        return facultyid;
    }

    public void setFacultyid(Integer facultyid) {
        this.facultyid = facultyid;
    }
}