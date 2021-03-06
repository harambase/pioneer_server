package com.harambase.pioneer.server.service.impl;

import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.common.Page;
import com.harambase.pioneer.common.constant.FlagDict;
import com.harambase.pioneer.server.dao.base.CourseDao;
import com.harambase.pioneer.server.dao.base.StudentDao;
import com.harambase.pioneer.server.dao.repository.StudentRepository;
import com.harambase.pioneer.server.pojo.base.Student;
import com.harambase.pioneer.server.pojo.view.CourseView;
import com.harambase.pioneer.server.service.StudentService;
import com.harambase.pioneer.common.support.util.DateUtil;
import com.harambase.pioneer.common.support.util.PageUtil;
import com.harambase.pioneer.common.support.util.ReturnMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final StudentRepository studentRepository;

    private final StudentDao studentDao;
    private final CourseDao courseDao;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,
                              StudentDao studentDao, CourseDao courseDao) {
        this.studentRepository = studentRepository;
        this.studentDao = studentDao;
        this.courseDao = courseDao;
    }

    @Override
    public HaramMessage transcriptDetail(String studentId) {
        try {
            LinkedHashMap sv = studentDao.findOne(studentId);
            return ReturnMsgUtil.success(sv);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage update(String studentId, Student student) {
        try {
            student.setStudentId(studentId);
            student.setUpdateTime(DateUtil.DateToStr(new Date()));
            Student newStudent = studentRepository.save(student);
            return newStudent != null ? ReturnMsgUtil.success(newStudent) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage studentList(String currentPage, String pageSize, String search, String order, String orderColumn, String status) {
        HaramMessage message = new HaramMessage();

        switch (Integer.parseInt(orderColumn)) {
            case 0:
                orderColumn = "student_id";
                break;
            case 1:
                orderColumn = "max_credits";
                break;
            case 2:
                orderColumn = "status";
                break;
            case 3:
                orderColumn = "sname";
                break;
            case 4:
                orderColumn = "complete";
                break;
            case 5:
                orderColumn = "progress";
                break;
            case 6:
                orderColumn = "incomplete";
                break;
        }

        try {
            long totalSize = studentDao.getCountByMapPageSearchOrdered(search, status);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            List<LinkedHashMap> studentViews = studentDao.getByMapPageSearchOrdered(page.getCurrentIndex(), page.getPageSize(), search, order, orderColumn, status);

            message.setData(studentViews);
            message.put("page", page);
            message.setMsg(FlagDict.SUCCESS.getM());
            message.setCode(FlagDict.SUCCESS.getV());
            return message;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage getAvailableCredit(String studentId, String info) {

        try {
            List<CourseView> courseList = courseDao.findCourseViewByStudentId("", studentId);
            LinkedHashMap sv = studentDao.findOne(studentId);

            int use_credits = 0;
            int tol_credits = (Integer) sv.get("maxCredits");

            for (CourseView course : courseList) {
                if (course.getInfo().equals(info))
                    use_credits += course.getCredits();
            }

            int ava_credits = tol_credits - use_credits;

            Map<String, Integer> creditInfo = new HashMap<>();

            creditInfo.put("tol_credits", tol_credits);
            creditInfo.put("ava_credits", ava_credits);
            creditInfo.put("use_credits", use_credits);

            return ReturnMsgUtil.success(creditInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage courseList(String status, String studentId) {
        try {
            List<CourseView> courseList = courseDao.findCourseViewByStudentId(status, studentId);
            return ReturnMsgUtil.success(courseList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }
}
