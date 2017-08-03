package com.harambase.pioneer.pojo;

public class Transcript {
    private Integer id;

    private Integer studentid;

    private Integer crn;

    private Integer facultyid;

    private String grade;

    private String complete;

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

    public Integer getCrn() {
        return crn;
    }

    public void setCrn(Integer crn) {
        this.crn = crn;
    }

    public Integer getFacultyid() {
        return facultyid;
    }

    public void setFacultyid(Integer facultyid) {
        this.facultyid = facultyid;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete == null ? null : complete.trim();
    }
}