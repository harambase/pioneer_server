package com.harambase.pioneer.pojo.dto;

import com.harambase.pioneer.pojo.Transcript;

import java.io.Serializable;

public class TranscriptView extends Transcript implements Serializable {
    
    private String coursename;

    private String facultyid;

    private String sfirst;

    private String slast;

    private String ffirst;

    private String flast;

    private Integer credits;
    
    private String day;

    private String time;

    private String date;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
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
}
