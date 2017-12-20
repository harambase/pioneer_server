package com.harambase.pioneer.dao.repository.view;

import com.harambase.pioneer.pojo.view.CourseView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseViewRepository extends JpaRepository<CourseView, Integer> {

    CourseView findByCrn(String crn);

}
