package com.harambase.pioneer.server.core.service;

import com.alibaba.fastjson.JSONObject;
import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.server.core.pojo.base.TempCourse;

public interface TempCourseService {

    HaramMessage register(String facultyId, JSONObject jsonObject);

    HaramMessage deleteTempCourseById(Integer id);

    HaramMessage updateTempCourse(Integer id, TempCourse tempCourse);

    HaramMessage tempCourseList(String s, String s1, String search, String order, String orderCol, String status, String facultyId);

    HaramMessage get(Integer id);

}
