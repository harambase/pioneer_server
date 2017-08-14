package com.harambase.pioneer.service.Impl;

import com.harambase.common.DateUtil;
import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.common.PageUtil;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.CourseMapper;
import com.harambase.pioneer.dao.TranscriptMapper;
import com.harambase.pioneer.pojo.Course;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.pojo.Transcript;
import com.harambase.pioneer.pojo.dto.Option;
import com.harambase.pioneer.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linsh on 7/12/2017.
 */
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseMapper courseMapper;
    private final TranscriptMapper transcriptMapper;

    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper,
                             TranscriptMapper transcriptMapper){
        this.courseMapper = courseMapper;
        this.transcriptMapper = transcriptMapper;
    }
    
    @Override
    public HaramMessage add(Course course) {
        HaramMessage haramMessage = new HaramMessage();
        try {
            course.setCreatetime(DateUtil.DateToStr(new Date()));
            course.setUpdatetime(DateUtil.DateToStr(new Date()));
            course.setStatus("1");

            String info = course.getInfo();
            Integer last = (int)(Math.random() * (99 - 10 + 1) + 10);
            String crn = "1" + info.split("-")[0] + info.split("-")[1] + last;

            course.setCrn(crn);
            int ret = courseMapper.insert(course);
            if(ret == 1){
                haramMessage.setCode(FlagDict.SUCCESS.getV());
                haramMessage.setMsg(FlagDict.SUCCESS.getM());
                haramMessage.setData(course);
            }
            else{
                haramMessage.setCode(FlagDict.FAIL.getV());
                haramMessage.setMsg(FlagDict.FAIL.getM());
            }
            return haramMessage;
        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
    }

    @Override
    public HaramMessage remove(Course course) {
        return null;
    }

    @Override
    public HaramMessage get(String courseid) {
        return null;
    }

    @Override
    public HaramMessage updateCourse(Course course) {
        HaramMessage haramMessage = new HaramMessage();
        try{
            course.setUpdatetime(DateUtil.DateToStr(new Date()));
            int ret = courseMapper.updateByPrimaryKeySelective(course);
            if(ret == 1){
                haramMessage.setCode(FlagDict.SUCCESS.getV());
                haramMessage.setMsg(FlagDict.SUCCESS.getM());
                haramMessage.setData(course);
            }
            else{
                haramMessage.setCode(FlagDict.FAIL.getV());
                haramMessage.setMsg(FlagDict.FAIL.getM());
            }
            return haramMessage;
        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
    }

    @Override
    public HaramMessage assignFac2Cou(String facultyid, String courseid) {
        return null;
    }

    @Override
    public HaramMessage addStu2Cou(Option option) {
        HaramMessage haramMessage = new HaramMessage();
        try{
            Transcript transcript = new Transcript();
            String crn = option.getCrn();
            String studentid = option.getStudentid();
            Course course = courseMapper.selectByPrimaryKey(crn);
            transcript.setAssigntime(DateUtil.DateToStr(new Date()));
            transcript.setComplete("In Process");
            transcript.setGrade("*");
            transcript.setCrn(crn);
            transcript.setStudentid(studentid);

            if(option.isCapacity() && option.isPrereq() && option.isTime()) {
                int ret = transcriptMapper.insert(transcript);
                if (ret == 1) {
                    haramMessage.setMsg(FlagDict.SUCCESS.getM());
                    haramMessage.setCode(FlagDict.SUCCESS.getV());

                } else {
                    haramMessage.setMsg(FlagDict.FAIL.getM());
                    haramMessage.setCode(FlagDict.FAIL.getV());
                }
            }
            return haramMessage;

        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            return haramMessage;
        }
    }

    @Override
    public HaramMessage removeFacFromCou(String facultyid, String courseid) {
        return null;
    }

    @Override
    public HaramMessage removeStuFromCou(String studentid, String courseid) {
        return null;
    }

    @Override
    public HaramMessage countStudent(String courseid) {
        return null;
    }

    @Override
    public HaramMessage updateGrade(Transcript transcript) {
        HaramMessage message = new HaramMessage();
        try {
            transcript.setAssigntime(DateUtil.DateToStr(new Date()));
            int ret = transcriptMapper.updateByPrimaryKey(transcript);
            if(ret == 1){
                message.setData(transcript);
                message.setMsg(FlagDict.SUCCESS.getM());
                message.setCode(FlagDict.SUCCESS.getV());
            }
            else {
                message.setMsg(FlagDict.FAIL.getM());
                message.setCode(FlagDict.FAIL.getV());
            }
            return message;
        }catch (Exception e){
            e.printStackTrace();
            message.setMsg(FlagDict.SYSTEM_ERROR.getM());
            message.setCode(FlagDict.SYSTEM_ERROR.getV());
            return message;
        }
    }

    @Override
    public HaramMessage listBySearch(String search) {
        HaramMessage message = new HaramMessage();
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("search", search);
            param.put("status", "1");

            if(search.equals(""))
                param.put("search", null);

            List<Course> courses = courseMapper.getCourseBySearch(param);

            message.setData(courses);
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
    public HaramMessage courseList(String currentPage, String pageSize, String search, String order, String orderColumn){
        HaramMessage message = new HaramMessage();
        switch (Integer.parseInt(orderColumn)) {
            case 0:
                orderColumn = "id";
                break;
            case 1:
                orderColumn = "crn";
                break;
            case 2:
                orderColumn = "name";
                break;
            case 3:
                orderColumn = "coulev";
                break;
            case 4:
                orderColumn = "cousec";
                break;
            case 5:
                orderColumn = "capa";
                break;
            case 6:
                orderColumn = "status";
                break;
            case 7:
                orderColumn = "day";
                break;
            case 8:
                orderColumn = "facultyid";
                break;
            default:
                orderColumn = "updatetime";
                break;
        }
        long totalSize = 0;
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("search", search);

            if(search.equals(""))
                param.put("search", null);



            totalSize = courseMapper.getCourseCountByMapPageSearchOrdered(param); //startTime, endTime);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            param.put("currentIndex", page.getCurrentIndex());
            param.put("pageSize",  page.getPageSize());
            param.put("order",  order);
            param.put("orderColumn",  orderColumn);

            //(int currentIndex, int pageSize, String search, String order, String orderColumn);
            List<Person> msgs = courseMapper.getCourseByMapPageSearchOrdered(param);

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
    public HaramMessage transcriptList(String currentPage, String pageSize, String search, String order, String orderColumn, String studentid, String crn){
        HaramMessage message = new HaramMessage();
        switch (Integer.parseInt(orderColumn)) {
            case 0:
                orderColumn = "id";
                break;
            case 1:
                orderColumn = "studentid";
                break;
            case 2:
                orderColumn = "slast";
                break;
            case 3:
                orderColumn = "sfirst";
                break;
            case 4:
                orderColumn = "crn";
                break;
            case 5:
                orderColumn = "grade";
                break;
            case 6:
                orderColumn = "complete";
                break;
            case 7:
                orderColumn = "facultyid";
                break;
            case 8:
                orderColumn = "assigntime";
                break;
            default:
                orderColumn = "id";
                break;
        }
        long totalSize = 0;
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("search", search);
            param.put("studentid", studentid);
            param.put("crn", crn);

            if(search.equals(""))
                param.put("search", null);
            if(studentid.equals(""))
                param.put("studentid", null);
            if(crn.equals(""))
                param.put("crn", null);

            totalSize = transcriptMapper.getTranscriptCountByMapPageSearchOrdered(param); //startTime, endTime);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            param.put("currentIndex", page.getCurrentIndex());
            param.put("pageSize",  page.getPageSize());
            param.put("order",  order);
            param.put("orderColumn",  orderColumn);

            //(int currentIndex, int pageSize, String search, String order, String orderColumn);
            List<Person> msgs = transcriptMapper.getTranscriptByMapPageSearchOrdered(param);

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
}

