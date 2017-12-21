package com.harambase.pioneer.pojo.view;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "studentview")
public class StudentView implements Serializable{
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    private Integer id;
    private String studentid;
    private int max_credits;
    private int complete;
    private int progress;
    private int incomplete;

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
