package com.harambase.pioneer.server.service;

import com.harambase.pioneer.common.ResultMap;
import com.harambase.pioneer.server.pojo.base.Advise;

public interface AdviseService {

    ResultMap advisingList(String s, String s1, String search, String order, String orderCol, String studentid, String facultyid);

    ResultMap updateAdvise(Integer id, String studentId, String facultyId);

    ResultMap assignMentor(Advise advise);

    ResultMap removeMentor(Integer id);

    ResultMap getMentor(Integer id);

    ResultMap getAdviseByStudentId(String studentId);
}
