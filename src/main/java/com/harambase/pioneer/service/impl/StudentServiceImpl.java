package com.harambase.pioneer.service.impl;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.pioneer.dao.base.StudentDao;
import com.harambase.pioneer.dao.repository.view.CourseViewRepository;
import com.harambase.pioneer.dao.repository.view.StudentViewRepository;
import com.harambase.pioneer.pojo.view.CourseView;
import com.harambase.pioneer.pojo.view.StudentView;
import com.harambase.pioneer.pojo.base.Student;
import com.harambase.support.util.DateUtil;
import com.harambase.support.util.PageUtil;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.repository.base.StudentRepository;
import com.harambase.pioneer.pojo.base.Person;
import com.harambase.pioneer.service.StudentService;
import com.harambase.support.util.ReturnMsgUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final StudentRepository studentRepository;
    private final CourseViewRepository courseViewRepository;
    private final StudentViewRepository studentViewRepository;

    private final StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,
                              StudentViewRepository studentViewRepository,
                              CourseViewRepository courseViewRepository,
                              StudentDao studentDao){
        this.courseViewRepository = courseViewRepository;
        this.studentViewRepository = studentViewRepository;
        this.studentRepository = studentRepository;
        this.studentDao = studentDao;
    }

    @Override
    public HaramMessage transcriptDetail(String studentid) {
       try{
           StudentView sv = studentViewRepository.findOne(studentid);
           return ReturnMsgUtil.success(sv);
       }catch(Exception e){
           logger.error(e.getMessage(), e);
           return ReturnMsgUtil.systemError();
       }
    }

    @Override
    public HaramMessage update(Student student) {
        try{
            student.setUpdateTime(DateUtil.DateToStr(new Date()));
            Student newStudent = studentRepository.save(student);
            return newStudent != null ? ReturnMsgUtil.success(newStudent) : ReturnMsgUtil.fail();
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage studentList(String currentPage, String pageSize, String search, String order, String orderColumn, String status) {
        HaramMessage message = new HaramMessage();

        switch (Integer.parseInt(orderColumn)) {
            case 0:
                orderColumn = "studentid";
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

            List<StudentView> studentViews = studentDao.getByMapPageSearchOrdered(page.getCurrentIndex(), page.getPageSize(), search, order, orderColumn, status);

            message.setData(studentViews);
            message.put("page", page);
            message.setMsg(FlagDict.SUCCESS.getM());
            message.setCode(FlagDict.SUCCESS.getV());
            return message;

        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage getAvailableCredit(String studentid, String info) {

        try{
            List<CourseView> courseList = courseViewRepository.findCourseViewByStudentId(studentid);
            StudentView sv = studentViewRepository.findOne(studentid);

            int use_credits = 0;
            int tol_credits = sv.getMaxCredits();

            for(CourseView course : courseList){
                if(course.getInfo().equals(info))
                    use_credits += course.getCredits();
            }

            int ava_credits = tol_credits - use_credits;

            Map<String, Integer> creditInfo = new HashMap<>();

            creditInfo.put("tol_credits", tol_credits);
            creditInfo.put("ava_credits", ava_credits);
            creditInfo.put("use_credits", use_credits);

            return ReturnMsgUtil.success(creditInfo);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }
}
