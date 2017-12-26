package com.harambase.pioneer.server.core.dao.repository;

import com.harambase.pioneer.server.core.pojo.base.TempCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempCourseRepository extends JpaRepository<TempCourse, Integer> {
}
