package com.harambase.pioneer.server.core.service;

import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.server.core.pojo.base.Advise;

public interface AdviseService {

    HaramMessage advisingList(String s, String s1, String search, String order, String orderCol, String studentid, String facultyid);

    HaramMessage updateAdvise(Integer id, String studentId, String facultyId);

    HaramMessage assignMentor(Advise advise);

    HaramMessage removeMentor(Integer id);

    HaramMessage getMentor(Integer id);
}
