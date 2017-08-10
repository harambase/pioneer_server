package com.harambase.pioneer.service;

import com.harambase.common.HaramMessage;
import com.harambase.pioneer.pojo.Course;

/**
 * Created by linsh on 7/12/2017.
 */
public interface CourseService {
    HaramMessage add(Course course);

    HaramMessage remove(Course course);

    HaramMessage get(String courseid);

    HaramMessage updateCourse(Course course);

    HaramMessage assignFac2Cou(String facultyid, String courseid);

    HaramMessage addStu2Cou(String studentid, String courseid);

    HaramMessage removeFacFromCou(String facultyid, String courseid);

    HaramMessage removeStuFromCou(String studentid, String courseid);

    HaramMessage countStudent(String courseid);

    HaramMessage updateGrade(String courseid, String studentid, String grade);

    HaramMessage listBySearch(String search);

    HaramMessage courseList(String s, String s1, String search, String order, String orderCol);
}
