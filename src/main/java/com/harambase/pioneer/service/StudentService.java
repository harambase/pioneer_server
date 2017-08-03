package com.harambase.pioneer.service;

/**
 * Created by linsh on 7/12/2017.
 */
public interface StudentService {
    void assignMentor(String studentid, String facultyid);

    void removeMentor(String studentid, String facultyid);

    void updateMentor(String studentid, String facultyid);
}
