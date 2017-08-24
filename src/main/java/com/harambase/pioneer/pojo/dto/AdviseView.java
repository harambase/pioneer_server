package com.harambase.pioneer.pojo.dto;

import java.io.Serializable;

public class AdviseView implements Serializable {
    private int id;
    private String studentid;
    private String sfirst;
    private String slast;
    private String facultyid;
    private String ffirst;
    private String flast;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
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

    public String getFacultyid() {
        return facultyid;
    }

    public void setFacultyid(String facultyid) {
        this.facultyid = facultyid;
    }

    public String getFfirst() {
        return ffirst;
    }

    public void setFfirst(String ffirst) {
        this.ffirst = ffirst;
    }

    public String getFlast() {
        return flast;
    }

    public void setFlast(String flast) {
        this.flast = flast;
    }
}
