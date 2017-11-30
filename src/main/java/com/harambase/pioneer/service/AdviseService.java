package com.harambase.pioneer.service;

import com.harambase.common.HaramMessage;
import com.harambase.pioneer.pojo.Advise;

public interface AdviseService {

    HaramMessage advisingList(String s, String s1, String search, String order, String orderCol, String studentid, String facultyid);

    HaramMessage updateAdvise(Advise advise);

    HaramMessage assignMentor(Advise advise);

    HaramMessage removeMentor(Integer id);

    HaramMessage getMentor(Integer id);
}
