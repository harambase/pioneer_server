package com.harambase.pioneer.server.core.pojo.view;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "studentview")
public class StudentView implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String studentid;

    @Column(name = "sname")
    private String sname;

    @Column(name = "status")
    private String status;

    @Column(name = "max_credits")
    private int maxCredits;

    @Column(name = "complete")
    private int complete;

    @Column(name = "progress")
    private int progress;

    @Column(name = "incomplete")
    private int incomplete;

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
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

    public int getMaxCredits() {
        return maxCredits;
    }

    public void setMaxCredits(int maxCredits) {
        this.maxCredits = maxCredits;
    }


}
