package com.harambase.pioneer.dao.repository.base;

import com.harambase.pioneer.pojo.base.Advise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdviseRepository extends JpaRepository<Advise, Integer> {

    void deleteByStudentIdOrFacultyId(String studentId, String facultyId);

    Advise findOneByStudentId(String studentId);

    int countByFacultyIdAndStudentId(String facultyId, String studentId);

    int countById(Integer id);
}