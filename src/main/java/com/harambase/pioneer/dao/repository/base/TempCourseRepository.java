package com.harambase.pioneer.dao.repository.base;

import com.harambase.pioneer.pojo.TempCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempCourseRepository extends JpaRepository<TempCourse, Integer>{
}
