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

    List<CourseView> findCourseViewByFacultyid(String facultyid);

    @Query("select c from CourseView c where status = ?1 and (c.name like concat('%', ?2, '%') or c.comment like concat('%', ?2, '%')) order by c.id desc")
    List<CourseView> findTop5ByStatusAndSearch(String status, String search);

    @Query("SELECT c FROM CourseView c WHERE c.crn IN (SELECT t.crn FROM Transcript t WHERE t.studentid = ?1)")
    List<CourseView> findCourseViewByStudentId(String studentId);


    @Query("select c from CourseView c where c.facultyid = ?14 and c.info = ?15 and " +
            " (c.crn    like concat('%',?1 ,'%')  or c.name    like concat('%',?2 ,'%')  or c.credits like concat('%',?3 ,'%') or" +
            "  c.coulev like concat('%',?4 ,'%')  or c.cousec  like concat('%',?5 ,'%')  or c.day     like concat('%',?6 ,'%') or" +
            "  c.time   like concat('%',?7 ,'%')  or c.capa    like concat('%',?8 ,'%')  or c.remain  like concat('%',?9 ,'%') or" +
            "  c.status like concat('%',?10 ,'%') or c.faculty like concat('%',?11 ,'%') or c.date    like concat('%',?12 ,'%') or c.updatetime like  concat('%',?13 ,'%'))")
    Page<CourseView> findWithFacultyIdAndInfo(String search1, String search2, String search3, String search4,
                                              String search5, String search6, String search7, String search8,
                                              String search9, String search10, String search11, String search12, String search13,
                                              String facultyid, String info, Pageable pageable);

    @Query("select c from CourseView c where c.facultyid = ?14 and " +
            " (c.crn    like concat('%',?1 ,'%')  or c.name    like concat('%',?2 ,'%')  or c.credits like concat('%',?3 ,'%') or" +
            "  c.coulev like concat('%',?4 ,'%')  or c.cousec  like concat('%',?5 ,'%')  or c.day     like concat('%',?6 ,'%') or" +
            "  c.time   like concat('%',?7 ,'%')  or c.capa    like concat('%',?8 ,'%')  or c.remain  like concat('%',?9 ,'%') or" +
            "  c.status like concat('%',?10 ,'%') or c.faculty like concat('%',?11 ,'%') or c.date    like concat('%',?12 ,'%') or c.updatetime like  concat('%',?13 ,'%'))")
    Page<CourseView> findWithFacultyId(String search1, String search2, String search3, String search4,
                                       String search5, String search6, String search7, String search8,
                                       String search9, String search10, String search11, String search12, String search13,
                                       String facultyid, Pageable pageable);

    @Query("select c from CourseView c where c.info = ?14 and " +
            " (c.crn    like concat('%',?1 ,'%')  or c.name    like concat('%',?2 ,'%')  or c.credits like concat('%',?3 ,'%') or" +
            "  c.coulev like concat('%',?4 ,'%')  or c.cousec  like concat('%',?5 ,'%')  or c.day     like concat('%',?6 ,'%') or" +
            "  c.time   like concat('%',?7 ,'%')  or c.capa    like concat('%',?8 ,'%')  or c.remain  like concat('%',?9 ,'%') or" +
            "  c.status like concat('%',?10 ,'%') or c.faculty like concat('%',?11 ,'%') or c.date    like concat('%',?12 ,'%') or c.updatetime like  concat('%',?13 ,'%'))")
    Page<CourseView> findWithInfo(String search1, String search2, String search3, String search4,
                                  String search5, String search6, String search7, String search8,
                                  String search9, String search10, String search11, String search12, String search13,
                                  String info, Pageable pageable);


    @Query("select c from CourseView c where " +
            " (crn    like concat('%',?1 ,'%')  or name    like concat('%',?2 ,'%')  or credits like concat('%',?3 ,'%') or" +
            "  coulev like concat('%',?4 ,'%')  or cousec  like concat('%',?5 ,'%')  or day     like concat('%',?6 ,'%') or" +
            "  time   like concat('%',?7 ,'%')  or capa    like concat('%',?8 ,'%')  or remain  like concat('%',?9 ,'%') or" +
            "  status like concat('%',?10 ,'%') or faculty like concat('%',?11 ,'%') or date    like concat('%',?12 ,'%') or updatetime like  concat('%',?13 ,'%'))")
    Page<CourseView> findSearchOnly(String search, String search1, String search2, String search3, String search4, String search5, String search6, String search7, String search8, String search9, String search10, String search11, String search12, Pageable pageable);
}
