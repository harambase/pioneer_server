package com.harambase.pioneer.dao;

import com.harambase.pioneer.pojo.Course;
import com.harambase.pioneer.pojo.Person;

import java.util.List;
import java.util.Map;

public interface CourseMapper {
    int deleteByPrimaryKey(String crn);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(String crn);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    List<Course> getCourseBySearch(Map<String, Object> param);

    long getCourseCountByMapPageSearchOrdered(Map<String, Object> param);

    List<Person> getCourseByMapPageSearchOrdered(Map<String, Object> param);

    int getRemain(String crn);

    int facultyTime(Map<String, String> param);
}