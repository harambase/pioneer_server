package com.harambase.pioneer.pojo;

import java.io.Serializable;

public class Course implements Serializable{
    private Integer id;

    private String crn;

    private String name;

    private Integer credits;

    private Integer precrn;

    private String coulev;

    private String cousec;

    private String startdate;

    private String enddate;

    private String day;

    private String starttime;

    private String endtime;

    private Integer capa;

    private String status;

    private String facultyid;

    private String info;

    private String createtime;

    private String updatetime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getPrecrn() {
        return precrn;
    }

    public void setPrecrn(Integer precrn) {
        this.precrn = precrn;
    }

    public String getCoulev() {
        return coulev;
    }

    public void setCoulev(String coulev) {
        this.coulev = coulev == null ? null : coulev.trim();
    }

    public String getCousec() {
        return cousec;
    }

    public void setCousec(String cousec) {
        this.cousec = cousec == null ? null : cousec.trim();
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate == null ? null : startdate.trim();
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate == null ? null : enddate.trim();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime == null ? null : starttime.trim();
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime == null ? null : endtime.trim();
    }

    public Integer getCapa() {
        return capa;
    }

    public void setCapa(Integer capa) {
        this.capa = capa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getFacultyid() {
        return facultyid;
    }

    public void setFacultyid(String facultyid) {
        this.facultyid = facultyid == null ? null : facultyid.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
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
}