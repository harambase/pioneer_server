package com.harambase.pioneer.dao.repository.view;

import com.harambase.pioneer.pojo.view.CourseView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseViewRepository extends JpaRepository<CourseView, Integer> {

    int countAllByStatus(String status);

    CourseView findByCrn(String crn);

    List<CourseView> findAllCoursesViewByInfo(String info);

    List<CourseView> findCourseViewByFacultyId(String facultyid);

    @Query("select c from CourseView c where status = ?1 and " +
            "(c.crn     like concat('%', ?2, '%') or " +
            " c.name    like concat('%', ?2, '%') or " +
            " c.comment like concat('%', ?2, '%')) " +
            "order by c.id desc")
    List<CourseView> findTop5ByStatusAndSearch(String status, String search);

    @Query("SELECT c FROM CourseView c WHERE c.crn IN (SELECT t.crn FROM Transcript t WHERE t.studentId = ?1)")
    List<CourseView> findCourseViewByStudentId(String studentId);


    @Query("select c from CourseView c where c.facultyId = ?2 and c.info = ?3 and " +
            " (c.crn    like concat('%',?1,'%') or c.name     like concat('%',?1,'%') or c.credits like concat('%',?1,'%') or" +
            "  c.level  like concat('%',?1,'%') or c.section  like concat('%',?1,'%') or c.day     like concat('%',?1,'%') or" +
            "  c.time   like concat('%',?1,'%') or c.capacity like concat('%',?1,'%') or c.remain  like concat('%',?1,'%') or" +
            "  c.status like concat('%',?1,'%') or c.faculty  like concat('%',?1,'%') or c.date    like concat('%',?1,'%') or c.updateTime like concat('%',?1,'%'))")
    Page<CourseView> findWithFacultyIdAndInfo(String search, String facultyId, String info, Pageable pageable);

    @Query("select c from CourseView c where c.facultyId = ?2 and " +
            " (c.crn    like concat('%',?1,'%') or c.name     like concat('%',?1,'%') or c.credits like concat('%',?1,'%') or" +
            "  c.level  like concat('%',?1,'%') or c.section  like concat('%',?1,'%') or c.day     like concat('%',?1,'%') or" +
            "  c.time   like concat('%',?1,'%') or c.capacity like concat('%',?1,'%') or c.remain  like concat('%',?1,'%') or" +
            "  c.status like concat('%',?1,'%') or c.faculty  like concat('%',?1,'%') or c.date    like concat('%',?1,'%') or c.updateTime like concat('%',?1,'%'))")
    Page<CourseView> findWithFacultyId(String search, String facultyId, Pageable pageable);

    @Query("select c from CourseView c where c.info = ?2 and " +
            " (c.crn    like concat('%',?1,'%') or c.name     like concat('%',?1,'%') or c.credits like concat('%',?1,'%') or" +
            "  c.level  like concat('%',?1,'%') or c.section  like concat('%',?1,'%') or c.day     like concat('%',?1,'%') or" +
            "  c.time   like concat('%',?1,'%') or c.capacity like concat('%',?1,'%') or c.remain  like concat('%',?1,'%') or" +
            "  c.status like concat('%',?1,'%') or c.faculty  like concat('%',?1,'%') or c.date    like concat('%',?1,'%') or c.updateTime like concat('%',?1,'%'))")
    Page<CourseView> findWithInfo(String search, String info, Pageable pageable);


    @Query("select c from CourseView c where " +
            " (c.crn    like concat('%',?1,'%') or c.name     like concat('%',?1,'%') or c.credits like concat('%',?1,'%') or" +
            "  c.level  like concat('%',?1,'%') or c.section  like concat('%',?1,'%') or c.day     like concat('%',?1,'%') or" +
            "  c.time   like concat('%',?1,'%') or c.capacity like concat('%',?1,'%') or c.remain  like concat('%',?1,'%') or" +
            "  c.status like concat('%',?1,'%') or c.faculty  like concat('%',?1,'%') or c.date    like concat('%',?1,'%') or c.updateTime like concat('%',?1,'%'))")
    Page<CourseView> findSearchOnly(String search, Pageable pageable);
}
