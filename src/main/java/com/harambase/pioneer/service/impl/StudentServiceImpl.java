package com.harambase.pioneer.service.impl;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.common.PageUtil;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.mapper.StudentMapper;
import com.harambase.pioneer.dao.mapper.TranscriptMapper;
import com.harambase.pioneer.pojo.Course;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.pojo.Student;
import com.harambase.pioneer.pojo.dto.StudentView;
import com.harambase.pioneer.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linsh on 7/12/2017.
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;
    private final TranscriptMapper transcriptMapper;


    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper, TranscriptMapper transcriptMapper){
        this.transcriptMapper = transcriptMapper;
        this.studentMapper = studentMapper;
    }

    @Override
    public HaramMessage transcriptDetail(String studentid) {
       HaramMessage haramMessage = new HaramMessage();
       try{
           StudentView sv = studentMapper.transcriptDetail(studentid);
           haramMessage.setData(sv);
           haramMessage.setCode(FlagDict.SUCCESS.getV());
           haramMessage.setMsg(FlagDict.SUCCESS.getM());
           return haramMessage;
       }catch(Exception e){
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
       }

    }

    @Override
    public HaramMessage update(Student student) {
        HaramMessage haramMessage = new HaramMessage();
        try{
            int ret = studentMapper.updateByPrimaryKey(student);
            if(ret == 1) {
                haramMessage.setData(student);
                haramMessage.setCode(FlagDict.SUCCESS.getV());
                haramMessage.setMsg(FlagDict.SUCCESS.getM());
                return haramMessage;
            }else{
                haramMessage.setCode(FlagDict.FAIL.getV());
                haramMessage.setMsg(FlagDict.FAIL.getM());
                return haramMessage;
            }
        }catch(Exception e){
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
    }

    @Override
    public HaramMessage studentList(String currentPage, String pageSize, String search, String order, String orderColumn,
                                    String type, String status) {
        HaramMessage message = new HaramMessage();

        switch (Integer.parseInt(orderColumn)) {
            case 0:
                orderColumn = "studentid";
                break;
            case 1:
                orderColumn = "firstname";
                break;
            case 2:
                orderColumn = "lastname";
                break;
            case 3:
                orderColumn = "max_credits";
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
        long totalSize = 0;
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("search", search);
            param.put("type", type);
            param.put("status", status);

            if(StringUtils.isEmpty(search))
                param.put("search", null);

            totalSize = studentMapper.getStudentCountByMapPageSearchOrdered(param); //startTime, endTime);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            param.put("currentIndex", page.getCurrentIndex());
            param.put("pageSize",  page.getPageSize());
            param.put("order",  order);
            param.put("orderColumn",  orderColumn);

            //(int currentIndex, int pageSize, String search, String order, String orderColumn);
            List<Person> msgs = studentMapper.getStudentByMapPageSearchOrdered(param);

            message.setData(msgs);
            message.put("page", page);
            message.setMsg(FlagDict.SUCCESS.getM());
            message.setCode(FlagDict.SUCCESS.getV());
            return message;

        }catch (Exception e) {
            e.printStackTrace();
            message.setMsg(FlagDict.SYSTEM_ERROR.getM());
            message.setCode(FlagDict.SYSTEM_ERROR.getV());
            return message;
        }
    }

    @Override
    public HaramMessage getAvaliableCredit(String studentid, String info) {
        HaramMessage haramMessage = new HaramMessage();
        try{
            Map<String, Integer> creditInfo = new HashMap<>();
            List<Course> courseList = transcriptMapper.studentCourse(studentid);
            StudentView sv = studentMapper.transcriptDetail(studentid);
            int use_credits = 0;
            int ava_credits = 0;
            int tol_credits = sv.getMax_credits();
            for(Course course:courseList){
                if(course.getInfo().equals(info))
                    use_credits += course.getCredits();
            }
            ava_credits = tol_credits - use_credits;
            creditInfo.put("tol_credits", tol_credits);
            creditInfo.put("ava_credits", ava_credits);
            creditInfo.put("use_credits", use_credits);

            haramMessage.setData(creditInfo);
            haramMessage.setMsg(FlagDict.SUCCESS.getM());
            haramMessage.setCode(FlagDict.SUCCESS.getV());
            return haramMessage;
        }catch(Exception e){
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
    }
}
