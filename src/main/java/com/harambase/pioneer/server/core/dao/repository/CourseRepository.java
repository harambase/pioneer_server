package com.harambase.pioneer.server.core.dao.repository;

import com.harambase.pioneer.server.core.pojo.base.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findCourseByFacultyId(String facultyId);

    int countCourseByCrn(String crn);

    Course findByCrn(String crn);

}