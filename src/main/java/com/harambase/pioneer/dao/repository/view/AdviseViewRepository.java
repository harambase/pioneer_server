package com.harambase.pioneer.dao.repository.view;

import com.harambase.pioneer.pojo.view.AdviseView;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdviseViewRepository extends JpaRepository<AdviseView, Integer> {

    @Query("select a from AdviseView a where (student_id like concat('%', ?1, '%') or faculty_id like concat('%', ?2, '%') or sname like concat('%', ?3, '%') or fname like concat('%', ?4, '%'))")
    Page<AdviseView> findSearchOnly(String var1, String var2, String var3, String var4, Pageable pageable);

    @Query("select a from AdviseView a where student_id = ?5 and (student_id like concat('%', ?1, '%') or faculty_id like concat('%', ?2, '%') or sname like concat('%', ?3, '%') or fname like concat('%', ?4, '%'))")
    Page<AdviseView> findWithStudentId(String var1, String var2, String var3, String var4, String studentId, Pageable pageable);

    @Query("select a from AdviseView a where faculty_id = ?5 and (student_id like concat('%', ?1, '%') or faculty_id like concat('%', ?2, '%') or sname like concat('%', ?3, '%') or fname like concat('%', ?4, '%'))")
    Page<AdviseView> findWithFacultyId(String var1, String var2, String var3, String var4, String facultyId, Pageable pageable);

}