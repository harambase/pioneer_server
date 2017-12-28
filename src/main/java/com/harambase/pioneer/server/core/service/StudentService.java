package com.harambase.pioneer.server.core.service;

import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.server.core.pojo.base.Student;

/**
 * Created by linsh on 7/12/2017.
 */
public interface StudentService {

    HaramMessage transcriptDetail(String studentid);

    HaramMessage update(String studentId, Student student);

    HaramMessage studentList(String s, String s1, String search, String order, String orderCol, String status);

    HaramMessage getAvailableCredit(String studentid, String info);
}
