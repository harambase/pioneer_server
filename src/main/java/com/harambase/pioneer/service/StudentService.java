package com.harambase.pioneer.service;

import com.harambase.common.HaramMessage;
import com.harambase.pioneer.pojo.Student;
import com.harambase.pioneer.pojo.dto.Option; /**
 * Created by linsh on 7/12/2017.
 */
public interface StudentService {

    HaramMessage transcriptDetail(String studentid);

    HaramMessage update(Student student);

    HaramMessage studentList(String s, String s1, String search, String order, String orderCol, String type, String status);

    HaramMessage getAvaliableCredit(String studentid, String info);
}
