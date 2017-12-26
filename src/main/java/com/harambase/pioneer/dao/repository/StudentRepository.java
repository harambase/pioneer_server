package com.harambase.pioneer.dao.repository;

import com.harambase.pioneer.pojo.base.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

}