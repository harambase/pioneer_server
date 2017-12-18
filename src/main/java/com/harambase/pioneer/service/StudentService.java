package com.harambase.pioneer.service;

import com.harambase.common.HaramMessage;
import com.harambase.pioneer.pojo.base.StudentBase;

/**
 * Created by linsh on 7/12/2017.
 */
public interface StudentService {

    HaramMessage transcriptDetail(String studentid);

    HaramMessage update(StudentBase student);

    HaramMessage studentList(String s, String s1, String search, String order, String orderCol, String type, String status);

    HaramMessage getAvailableCredit(String studentid, String info);
}
