package com.harambase.pioneer.server.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.harambase.pioneer.common.ResultMap;
import com.harambase.pioneer.common.Page;
import com.harambase.pioneer.common.constant.SystemConst;
import com.harambase.pioneer.server.dao.base.CourseDao;
import com.harambase.pioneer.server.dao.base.StudentDao;
import com.harambase.pioneer.server.dao.repository.StudentRepository;
import com.harambase.pioneer.server.pojo.base.Student;
import com.harambase.pioneer.server.pojo.view.CourseView;
import com.harambase.pioneer.server.service.StudentService;
import com.harambase.pioneer.common.support.util.DateUtil;
import com.harambase.pioneer.common.support.util.PageUtil;
import com.harambase.pioneer.common.support.util.ReturnMsgUtil;
import org.apache.commons.lang3.StringUtils;
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
    public ResultMap transcriptDetail(String studentId) {
        try {
            LinkedHashMap sv = studentDao.findOne(studentId);
            return ReturnMsgUtil.success(sv);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap update(String studentId, Student student) {
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
    public ResultMap studentList(String currentPage, String pageSize, String search, String order, String orderColumn, String status) {
        ResultMap message = new ResultMap();

        try {
            long totalSize = studentDao.getCountByMapPageSearchOrdered(search, status);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            List<LinkedHashMap> studentViews = studentDao.getByMapPageSearchOrdered(page.getCurrentIndex(), page.getPageSize(), search, order, orderColumn, status);

            message.setData(studentViews);
            message.put("page", page);
            message.setMsg(SystemConst.SUCCESS.getMsg());
            message.setCode(SystemConst.SUCCESS.getCode());
            return message;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap getAvailableCredit(String studentId, String info) {

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
    public ResultMap courseList(String status, String studentId) {
        try {
            List<CourseView> courseList = courseDao.findCourseViewByStudentId(status, studentId);
            return ReturnMsgUtil.success(courseList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap getContract(String studentId) {
        try {
            JSONArray contractArray = new JSONArray();
            Map<String, JSONArray> studentMap = new HashMap<>();

            if (StringUtils.isNotEmpty(studentId)) {
                Student student = studentRepository.findOne(studentId);
                studentMap.put(student.getStudentId(), JSONArray.parseArray(student.getContractInfo()));
                contractArray.add(studentMap);
            } else {
                List<Student> studentList = studentRepository.findAll();
                for (Student s : studentList) {
                    studentMap.put(s.getStudentId(), JSONArray.parseArray(s.getContractInfo()));
                    contractArray.add(studentMap);
                }
            }
            return ReturnMsgUtil.success(contractArray.toJSONString());

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap updateContract(String studentId, String contractString) {
        try {
            Student student = studentRepository.findOne(studentId);
            student.setStudentId(studentId);
            student.setContractInfo(contractString);
            Student newStudent = studentRepository.save(student);
            student.setUpdateTime(DateUtil.DateToStr(new Date()));
            return newStudent != null ? ReturnMsgUtil.success(newStudent) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }
}
