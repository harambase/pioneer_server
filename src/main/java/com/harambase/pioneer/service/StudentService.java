package com.harambase.pioneer.service;

import com.harambase.common.HaramMessage;
import com.harambase.pioneer.pojo.Student;
import com.harambase.pioneer.pojo.dto.Option; /**
 * Created by linsh on 7/12/2017.
 */
public interface StudentService {
    void assignMentor(String studentid, String facultyid);

    void removeMentor(String studentid, String facultyid);

    void updateMentor(String studentid, String facultyid);

    HaramMessage transcriptDetail(String studentid);

    HaramMessage update(Student student);
}
