package com.harambase.pioneer.pojo.dto;

import java.io.Serializable;

public class StudentView implements Serializable{
    private int id;
    private String studentid;
    private int max_credits;
    private String lastname;
    private String firstname;
    private int complete;
    private int progress;
    private int incomplete;

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

    public int getMax_credits() {
        return max_credits;
    }

    public void setMax_credits(int max_credits) {
        this.max_credits = max_credits;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getIncomplete() {
        return incomplete;
    }

    public void setIncomplete(int incomplete) {
        this.incomplete = incomplete;
    }
}
