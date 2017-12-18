package com.harambase.pioneer.dao.mapper;

import com.harambase.pioneer.pojo.Course;
import com.harambase.pioneer.pojo.base.CourseBase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface CourseMapper {
    int deleteByPrimaryKey(String crn);

    int insert(CourseBase record);

    int insertSelective(CourseBase record);

    CourseBase selectByPrimaryKey(String crn);

    int updateByPrimaryKeySelective(CourseBase record);

    int updateByPrimaryKey(CourseBase record);

    List<Course> getCourseBySearch(Map<String, Object> param);

    long getCourseCountByMapPageSearchOrdered(Map<String, Object> param);

    List<CourseBase> getCourseByMapPageSearchOrdered(Map<String, Object> param);

    int getRemain(String crn);

    List<CourseBase> facultyCourse(String facultyid);

    List<Course> getAllActiveCourses();

    int countActiveCourse();
    
    List<CourseBase> getAllCoursesWithInfo(String info);

    String getStatus(String crn);
}