package com.harambase.pioneer.service;

import com.harambase.pioneer.pojo.Course;

/**
 * Created by linsh on 7/12/2017.
 */
public interface CourseService {
    void add(Course course);

    void remove(Course course);

    void get(String courseid);

    void updateCourse(Course course);

    void assignFac2Cou(String facultyid, String courseid);

    void addStu2Cou(String studentid, String courseid);

    void removeFacFromCou(String facultyid, String courseid);

    void removeStuFromCou(String studentid, String courseid);

    void countStudent(String courseid);

    void updateGrade(String courseid, String studentid, String grade);
}
