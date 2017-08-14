package com.harambase.pioneer.pojo;

import java.io.Serializable;

public class TranscriptView implements Serializable {

    private Integer id;

    private String studentid;

    private String crn;

    private String grade;

    private String complete;

    private String coursename;

    private String facultyid;

    private String sfirst;

    private String slast;

    private String ffirst;

    private String flast;

    private String assigntime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getFacultyid() {
        return facultyid;
    }

    public void setFacultyid(String facultyid) {
        this.facultyid = facultyid;
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

    public String getAssigntime() {
        return assigntime;
    }

    public void setAssigntime(String assigntime) {
        this.assigntime = assigntime;
    }
}
