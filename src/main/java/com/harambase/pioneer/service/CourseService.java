package com.harambase.pioneer.service;

import com.harambase.common.HaramMessage;
import com.harambase.pioneer.pojo.base.CourseBase;
import com.harambase.pioneer.pojo.dto.Option;

/**
 * Created by linsh on 7/12/2017.
 */
public interface CourseService {
    HaramMessage create(CourseBase course);

    HaramMessage delete(String crn);

    HaramMessage update(CourseBase course);

    HaramMessage assignFac2Cou(String crn, String facultyId);

    HaramMessage addStu2Cou(String crn, String studentId, Option option);

    HaramMessage removeStuFromCou(String crn, String studentId);

    HaramMessage getCourseBySearch(String search, String status);

    HaramMessage courseList(String s, String s1, String search, String order, String orderCol, String facultyid, String info);

    HaramMessage countActiveCourse();

    HaramMessage preCourseList(String crn);

    HaramMessage reg2Course(String userid, String[] choices);
    
    HaramMessage getCourseByCrn(String crn);

    HaramMessage courseTreeList(String facultyId, String info);
}
