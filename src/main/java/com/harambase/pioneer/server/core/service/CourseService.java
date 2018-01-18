package com.harambase.pioneer.server.core.service;

import com.alibaba.fastjson.JSONObject;
import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.server.core.pojo.base.Course;
import com.harambase.pioneer.server.core.pojo.dto.Option;

/**
 * Created by linsh on 7/12/2017.
 */
public interface CourseService {

    HaramMessage addCourse(Course course);

    HaramMessage delete(String crn);

    HaramMessage update(String crn, Course course);

    HaramMessage assignFac2Cou(String crn, String facultyId);

    HaramMessage addStu2Cou(String crn, String studentId, Option option);

    HaramMessage removeStuFromCou(String crn, String studentId);

    HaramMessage getCourseBySearch(String search, String status);

    HaramMessage courseList(String s, String s1, String search, String order, String orderCol, String facultyid, String info);

    HaramMessage preCourseList(String crn);

    HaramMessage reg2Course(String studentId, JSONObject choiceList);

    HaramMessage getCourseByCrn(String crn);

    HaramMessage courseTreeList(String facultyId, String info);

    HaramMessage courseListInfo(String search);

    HaramMessage studentList(String crn, String search);
}
