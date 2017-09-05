package com.harambase.pioneer.dao.mapper;

import com.harambase.pioneer.pojo.Course;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.pojo.dto.CourseView;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public interface CourseMapper {
    int deleteByPrimaryKey(String crn);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(String crn);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    List<Course> getCourseBySearch(Map<String, Object> param);

    long getCourseCountByMapPageSearchOrdered(Map<String, Object> param);

    List<Course> getCourseByMapPageSearchOrdered(Map<String, Object> param);

    int getRemain(String crn);

    int facultyTime(Map<String, String> param);

    List<CourseView> getAllActiveCourses();

    int countActiveCourse();
    
    List<Course> getAllCoursesWithInfo(String info);
}