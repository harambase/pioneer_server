package com.harambase.pioneer.dao.repository.view;

import com.harambase.pioneer.pojo.view.StudentView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentViewRepository extends JpaRepository<StudentView, String> {

}
