package com.harambase.pioneer.dao.repository.base;

import com.harambase.pioneer.pojo.base.Advise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdviseRepository extends JpaRepository<Advise, Integer> {

    void deleteByStudentidOrFacultyid(String studentId, String facultyId);

    Advise findOneByStudentid(String studentId);
}