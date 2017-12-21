package com.harambase.pioneer.pojo.base;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Id
    private Integer id;

    private String studentid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer maxCredits;

    @OneToOne(optional=false, mappedBy="mapStudent")
    @PrimaryKeyJoinColumn
    private Person person;

    @OneToMany(mappedBy="student", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
//    @JoinColumn(foreignKey = @ForeignKey(name = "studentid"))
    private List<Transcript> transcriptList;

    public List<Transcript> getTranscriptList() {
        return transcriptList;
    }

    public void setTranscriptList(List<Transcript> transcriptList) {
        this.transcriptList = transcriptList;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid == null ? null : studentid.trim();
    }

    public Integer getMaxCredits() {
        return maxCredits;
    }

    public void setMaxCredits(Integer maxCredits) {
        this.maxCredits = maxCredits;
    }

}