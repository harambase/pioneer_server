package com.harambase.pioneer.dao.repository.view;

import com.harambase.pioneer.pojo.AdviseView;
import com.harambase.pioneer.pojo.base.Advise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdviseViewRepository extends JpaRepository<AdviseView, Integer> {

    int countByFacultyidAndStudentid(String facultyId, String studentId);

//     "FUNCTION('get_name' , a.studentid) AS sname, a.facultyid AS facultyid, " +
//             "FUNCTION('get_name' , a.facultyid) AS fname, a.status AS status, a.update_time AS update_time, a.operator " +
//             "AS operator, FUNCTION('get_name', a.operator) AS oname
    @Query("SELECT a FROM AdviseView a where a.id = ?1")
    AdviseView findOne(Integer id);

//    @Query("select AdviseView from adviseview where (studentid like ?1 or facultyid like ?1 or sname like  ?1 or operator like  ?1 or fname like  ?1)")
//    Page<AdviseView> findAllBySearch(String search, Pageable pageable);
//
//    @Query("select AdviseView from adviseview where studentid = ?1 and (studentid like ?2 or facultyid like ?2 or sname like  ?2 or operator like  ?2 or fname like  ?2)")
//    Page<AdviseView> findAllByStudentidAndFacultyidLikeOrStudentidLikeOrFnameLikeOrSnameLike(String studentId, String search, Pageable pageable);
//
////    Page<AdviseView> findAllByFacultyidAndLikeStudentIdOrFacultyidOrSnameOrOperatorOrFname(String facultyId, String search, Pageable pageable);
////

}