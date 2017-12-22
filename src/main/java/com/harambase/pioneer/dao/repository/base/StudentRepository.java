package com.harambase.pioneer.dao.repository.base;

import com.harambase.pioneer.pojo.base.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

}