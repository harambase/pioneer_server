package com.harambase.pioneer.service.Impl;

import com.harambase.pioneer.pojo.Course;
import com.harambase.pioneer.service.CourseService;
import org.springframework.stereotype.Service;

/**
 * Created by linsh on 7/12/2017.
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Override
    public void add(Course course) {

    }

    @Override
    public void remove(Course course) {

    }

    @Override
    public void get(String courseid) {

    }

    @Override
    public void updateCourse(Course course) {

    }

    @Override
    public void assignFac2Cou(String facultyid, String courseid) {

    }

    @Override
    public void addStu2Cou(String studentid, String courseid) {

    }

    @Override
    public void removeFacFromCou(String facultyid, String courseid) {

    }

    @Override
    public void removeStuFromCou(String studentid, String courseid) {

    }

    @Override
    public void countStudent(String courseid) {

    }

    @Override
    public void updateGrade(String courseid, String studentid, String grade) {

    }
}

