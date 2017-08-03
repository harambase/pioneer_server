package com.harambase.pioneer.pojo;

public class Student {
    private Integer id;

    private String type;

    private String mentor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor == null ? null : mentor.trim();
    }
}