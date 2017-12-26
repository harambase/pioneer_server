package com.harambase.pioneer.dao.repository;

import com.harambase.pioneer.pojo.base.TempCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempCourseRepository extends JpaRepository<TempCourse, Integer> {
}
