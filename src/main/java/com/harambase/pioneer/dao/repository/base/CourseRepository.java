package com.harambase.pioneer.dao.repository.base;

import com.harambase.pioneer.pojo.base.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    int countCourseByCrn(String crn);

    Course findByCrn(String crn);
}