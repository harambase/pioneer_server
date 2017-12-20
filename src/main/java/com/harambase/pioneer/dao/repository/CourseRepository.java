package com.harambase.pioneer.dao.repository;

import com.harambase.pioneer.pojo.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}