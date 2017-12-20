package com.harambase.pioneer.dao.repository;

import com.harambase.pioneer.pojo.Advise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdviseRepository extends JpaRepository<Advise, Integer>{

    int countByFacultyidAndStudentid(String facultyId, String studentId);


}