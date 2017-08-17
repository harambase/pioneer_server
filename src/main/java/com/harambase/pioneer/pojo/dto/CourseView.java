package com.harambase.pioneer.pojo.dto;

import java.io.Serializable;

public class CourseView implements Serializable {

    private Integer id;
    private String crn;
    private String name;
    private Integer credits;
    private String couLev;
    private String couSec;
    private Integer capa;
    private Integer remain;
    private String faculty;
    private String time;
    private String date;
    private String status;
    private String day;
    private String updatetime;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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
        this.crn = crn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getCouLev() {
        return couLev;
    }

    public void setCouLev(String couLev) {
        this.couLev = couLev;
    }

    public String getCouSec() {
        return couSec;
    }

    public void setCouSec(String couSec) {
        this.couSec = couSec;
    }

    public Integer getCapa() {
        return capa;
    }

    public void setCapa(Integer capa) {
        this.capa = capa;
    }

    public Integer getRemain() {
        return remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
