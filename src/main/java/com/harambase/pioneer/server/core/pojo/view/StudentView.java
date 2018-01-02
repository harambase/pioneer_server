package com.harambase.pioneer.server.core.pojo.view;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "studentview")
public class StudentView implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "sname")
    private String sname;

    @Column(name = "status")
    private String status;

    @Column(name = "max_credits")
    private Integer maxCredits;

    @Column(name = "complete")
    private Integer complete;

    @Column(name = "progress")
    private Integer progress;

    @Column(name = "incomplete")
    private Integer incomplete;

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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getIncomplete() {
        return incomplete;
    }

    public void setIncomplete(Integer incomplete) {
        this.incomplete = incomplete;
    }

    public Integer getMaxCredits() {
        return maxCredits;
    }

    public void setMaxCredits(Integer maxCredits) {
        this.maxCredits = maxCredits;
    }


}
