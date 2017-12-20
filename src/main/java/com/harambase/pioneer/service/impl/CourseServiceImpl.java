package com.harambase.pioneer.service.impl;

import com.harambase.common.*;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.repository.base.CourseRepository;
import com.harambase.pioneer.dao.repository.base.TranscriptRepository;
import com.harambase.pioneer.dao.repository.view.CourseViewRepository;
import com.harambase.pioneer.pojo.base.Course;
import com.harambase.pioneer.pojo.view.CourseView;
import com.harambase.pioneer.pojo.base.Transcript;
import com.harambase.support.util.DateUtil;
import com.harambase.support.util.IDUtil;
import com.harambase.support.util.PageUtil;
import com.harambase.pioneer.dao.mapper.CourseMapper;
import com.harambase.pioneer.dao.mapper.TranscriptMapper;
import com.harambase.common.helper.TimeValidate;
import com.harambase.pioneer.pojo.dto.Option;
import com.harambase.pioneer.service.CourseService;
import com.harambase.support.util.ReturnMsgUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;
    private final TranscriptMapper transcriptMapper;

    private final CourseRepository courseRepository;
    private final CourseViewRepository courseViewRepository;
    private final TranscriptRepository transcriptRepository;


    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper,
                             TranscriptMapper transcriptMapper,
                             CourseRepository courseRepository, CourseViewRepository courseViewRepository,TranscriptRepository transcriptRepository) {
        this.courseMapper = courseMapper;
        this.transcriptMapper = transcriptMapper;
        this.transcriptRepository = transcriptRepository;
        this.courseRepository = courseRepository;
        this.courseViewRepository = courseViewRepository;
    }

    @Override
    public HaramMessage create(Course course) {

        course.setCreatetime(DateUtil.DateToStr(new Date()));
        course.setUpdatetime(DateUtil.DateToStr(new Date()));
        String facultyid = course.getFacultyid();

        //生成CRN
        String info = course.getInfo();
        List<Course> courses = courseRepository.findAllCoursesByInfo(info);
        String crn = IDUtil.genCRN(info);
        for(int i = 0; i<courses.size(); i++){
            Course c = courses.get(i);
            if(crn.equals(c.getCrn())){
                crn = IDUtil.genCRN(info);
                i = 0;
            }
        }
        course.setCrn(crn);
        //检查教师时间冲突
        if (TimeValidate.isTimeConflict(courseRepository.findCourseByFacultyid(facultyid), course)) {
            return ReturnMsgUtil.custom(FlagDict.TIMECONFLICT);
        }
        course.setFacultyid(facultyid);
        //插入课程
        Course newCourse = courseRepository.save(course);

        return newCourse != null ? ReturnMsgUtil.success(newCourse) : ReturnMsgUtil.fail();

    }

    @Override
    public HaramMessage delete(String crn) {
        courseRepository.deleteCourseByCrn(crn);
        int count = courseRepository.countCourseByCrn(crn);
        return count == 0 ? ReturnMsgUtil.success(null) : ReturnMsgUtil.fail();
    }

    @Override
    public HaramMessage update(Course course) {
        course.setUpdatetime(DateUtil.DateToStr(new Date()));
        Course newCourse = courseRepository.save(course);
        return newCourse != null ? ReturnMsgUtil.success(newCourse) : ReturnMsgUtil.fail();
    }

    @Override
    public HaramMessage getCourseByCrn(String crn) {
        CourseView courseView = courseViewRepository.findByCrn(crn);
        return ReturnMsgUtil.success(courseView);
    }

    @Override
    public HaramMessage courseTreeList(String facultyid, String info) {
        HaramMessage message = new HaramMessage();
        try{
            try {
                Map<String, Object> param = new HashMap<>();

//                param.put("facultyid", facultyid);
                param.put("facultyid", null);
                param.put("info", info);

                if(facultyid.equals(""))
                    param.put("facultyid", null);
                if(info.equals(""))
                    param.put("info", null);

                param.put("search", null);
                param.put("currentIndex", 0);
                param.put("pageSize", Integer.MAX_VALUE);
                param.put("order", "desc");
                param.put("orderColumn", "crn");

                List<Map<String, String>> infoList = new ArrayList<>();
                Set<String> infoSet = new HashSet<>();
                Map<String, String> infoMap;
                List<Course> courses = courseMapper.getCourseByMapPageSearchOrdered(param);
                for (Course course: courses){
                    infoSet.add(course.getInfo());
                }
                for (String i : infoSet){
                    infoMap = new HashMap<>();
                    infoMap.put("node", "true");
                    infoMap.put("info", i);

                    infoList.add(infoMap);
                }

                List<Object> treeList = new ArrayList<>();
                treeList.addAll(infoList);
                treeList.addAll(courses);

                message.setData(treeList);
                message.setMsg(FlagDict.SUCCESS.getM());
                message.setCode(FlagDict.SUCCESS.getV());
                return message;

            } catch (Exception e) {
                e.printStackTrace();
                message.setMsg(FlagDict.SYSTEM_ERROR.getM());
                message.setCode(FlagDict.SYSTEM_ERROR.getV());
                return message;
            }
        }catch (Exception e){
            message.setCode(FlagDict.SYSTEM_ERROR.getV());
            message.setMsg(FlagDict.SYSTEM_ERROR.getM());
        }
        return message;
    }

    @Override
    public HaramMessage getCourseBySearch(String search, String status) {
        HaramMessage message = new HaramMessage();
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("search", search);
            param.put("status", status);

            if (search.equals(""))
                param.put("search", null);
            if (status.equals(""))
                param.put("status", null);

            List<CourseView> results = courseMapper.getCourseBySearch(param);

            message.setData(results);
            message.setMsg(FlagDict.SUCCESS.getM());
            message.setCode(FlagDict.SUCCESS.getV());
            return message;

        } catch (Exception e) {
            e.printStackTrace();
            message.setMsg(FlagDict.SYSTEM_ERROR.getM());
            message.setCode(FlagDict.SYSTEM_ERROR.getV());
            return message;
        }
    }

    @Override
    public HaramMessage assignFac2Cou(String crn, String facultyId) {
        Course course = courseRepository.findByCrn(crn);

        //检查时间冲突
        if (TimeValidate.isTimeConflict(courseRepository.findCourseByFacultyid(facultyId), course)){
            return ReturnMsgUtil.custom(FlagDict.TIMECONFLICT);
        }

        course.setUpdatetime(DateUtil.DateToStr(new Date()));
        Course newCourse = courseRepository.save(course);
        return newCourse != null ? ReturnMsgUtil.success(newCourse) : ReturnMsgUtil.fail();
    }

    @Override
    public HaramMessage addStu2Cou(String crn, String studentId, Option option) {
        HaramMessage haramMessage = new HaramMessage();
        try {
            Transcript transcript = new Transcript();
            Course course = courseMapper.selectByPrimaryKey(crn);
            String status = courseMapper.getStatus(crn);
            //检查课程状态
            if (status.equals("-1")) {
                haramMessage.setMsg(FlagDict.COURSE_DISABLED.getM());
                haramMessage.setCode(FlagDict.COURSE_DISABLED.getV());
                return haramMessage;
            }
            //检查时间冲突
            if (!option.isTime() && TimeValidate.isTimeConflict(transcriptMapper.studentCourse(studentId), course)) {
                haramMessage.setMsg(FlagDict.TIMECONFLICT.getM());
                haramMessage.setCode(FlagDict.TIMECONFLICT.getV());
                return haramMessage;
            }
            //检查课程容量
            int remain = courseMapper.getRemain(crn);
            if (remain <= 0 && !option.isCapacity()) {
                haramMessage.setMsg(FlagDict.MAX_CAPACITY.getM());
                haramMessage.setCode(FlagDict.MAX_CAPACITY.getV());
                return haramMessage;
            }
            transcript.setAssigntime(DateUtil.DateToStr(new Date()));
            transcript.setComplete("0");
            transcript.setGrade("*");
            transcript.setCrn(crn);
            transcript.setStudentid(studentId);
            //检查预选
            String precrn = course.getPrecrn();
            if (StringUtils.isNotEmpty(precrn)) {
                Transcript preTranscript = new Transcript();
                preTranscript.setComplete("1");
                preTranscript.setStudentid(studentId);
                preTranscript.setCrn(precrn);
                int ret = transcriptMapper.count(preTranscript);
                if (ret != 1 && !option.isPrereq()) {
                    haramMessage.setMsg(FlagDict.UNMET_PREREQ.getM());
                    haramMessage.setCode(FlagDict.UNMET_PREREQ.getV());
                    return haramMessage;
                }
            }
            //检查复选
            if (transcriptMapper.count(transcript) == 0) {
                transcript.setOperator(IDUtil.ROOT);
                int ret = transcriptMapper.insert(transcript);
                if (ret == 1) {
                    haramMessage.setMsg(FlagDict.SUCCESS.getM());
                    haramMessage.setCode(FlagDict.SUCCESS.getV());
                    return haramMessage;
                } else {
                    haramMessage.setMsg(FlagDict.FAIL.getM());
                    haramMessage.setCode(FlagDict.FAIL.getV());
                }
            } else {
                haramMessage.setMsg(FlagDict.REPEAT.getM());
                haramMessage.setCode(FlagDict.REPEAT.getV());
            }

            return haramMessage;

        } catch (Exception e) {
            e.printStackTrace();
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            return haramMessage;
        }
    }

    @Override
    public HaramMessage removeStuFromCou(String crn, String studentid) {
        HaramMessage haramMessage = new HaramMessage();
        try{
            Map<String, Object> param = new HashMap<>();
            param.put("studentid", studentid);
            param.put("crn", crn);
            int ret = transcriptMapper.deleteByPrimaryKey(param);
            if(ret == 1){
                haramMessage.setCode(FlagDict.SUCCESS.getV());
                haramMessage.setMsg(FlagDict.SUCCESS.getM());
                return haramMessage;
            }
            haramMessage.setCode(FlagDict.FAIL.getV());
            haramMessage.setMsg(FlagDict.FAIL.getM());
            return haramMessage;

        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
    }

    @Override
    public HaramMessage courseList(String currentPage, String pageSize, String search, String order, String orderColumn,
                                   String facultyid, String info) {
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
                orderColumn = "remain";
                break;
            case 7:
                orderColumn = "status";
                break;
            case 8:
                orderColumn = "date";
                break;
            case 9:
                orderColumn = "time";
                break;
            case 10:
                orderColumn = "day";
                break;
            case 11:
                orderColumn = "faculty";
                break;
            default:
                orderColumn = "updatetime";
                break;
        }
        long totalSize = 0;
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("search", search);
            param.put("facultyid", facultyid);
            param.put("info", info);

            if(search.equals(""))
                param.put("search", null);
            if(facultyid.equals(""))
                param.put("facultyid", null);
            if(info.equals(""))
                param.put("info", null);


            totalSize = courseMapper.getCourseCountByMapPageSearchOrdered(param); //startTime, endTime);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            param.put("currentIndex", page.getCurrentIndex());
            param.put("pageSize", page.getPageSize());
            param.put("order", order);
            param.put("orderColumn", orderColumn);

            List<Course> courses = courseMapper.getCourseByMapPageSearchOrdered(param);

            message.setData(courses);
            message.put("page", page);
            message.setMsg(FlagDict.SUCCESS.getM());
            message.setCode(FlagDict.SUCCESS.getV());
            return message;

        } catch (Exception e) {
            e.printStackTrace();
            message.setMsg(FlagDict.SYSTEM_ERROR.getM());
            message.setCode(FlagDict.SYSTEM_ERROR.getV());
            return message;
        }
    }

    @Override
    public HaramMessage countActiveCourse() {
        HaramMessage message = new HaramMessage();
        int c = courseMapper.countActiveCourse();
        message.setData(c);
        return message;
    }

    @Override
    public HaramMessage preCourseList(String crn) {
        HaramMessage haramMessage = new HaramMessage();
        try{
            Course course = courseMapper.selectByPrimaryKey(crn);
            String[] precrns = course.getPrecrn().split("/");
            List<Course> preCourses = new ArrayList<>();

            for(String precrn: precrns){
                if(StringUtils.isNotEmpty(precrn))
                    preCourses.add(courseMapper.selectByPrimaryKey(precrn));
            }
            haramMessage.setData(preCourses);
            haramMessage.setCode(FlagDict.SUCCESS.getV());
            haramMessage.setMsg(FlagDict.SUCCESS.getM());

        }catch (Exception e){
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
        }
        return haramMessage;
    }

    @Override
    @Transactional
    public HaramMessage reg2Course(String studentid, String[] choices) {
        HaramMessage haramMessage = new HaramMessage();
        Map<String, Object> result = new HashMap<>();
        List<String> failList = new ArrayList<>();
        try{
            for(String crn: choices){
                Transcript transcript = new Transcript();
                Course course = courseMapper.selectByPrimaryKey(crn);
                String status = courseMapper.getStatus(crn);
                String courseInfo = "CRN：" + crn + ", 课程名：" + course.getName() + "，失败原因:";
                //检查课程状态
                if (status.equals("-1")) {
                    failList.add(courseInfo + FlagDict.COURSE_DISABLED.getM());
                    continue;
                }
                //检查时间冲突
                if (TimeValidate.isTimeConflict(transcriptMapper.studentCourse(studentid), course)) {
                    failList.add(courseInfo + FlagDict.TIMECONFLICT.getM());
                    continue;
                }
                //检查课程容量
                int remain = courseMapper.getRemain(crn);
                if (remain <= 0) {
                    failList.add(courseInfo + FlagDict.MAX_CAPACITY.getM());
                    continue;
                }
                transcript.setAssigntime(DateUtil.DateToStr(new Date()));
                transcript.setComplete("0");
                transcript.setGrade("*");
                transcript.setCrn(crn);
                transcript.setStudentid(studentid);
                //检查预选
                String[] precrns = course.getPrecrn().split("/");
                Transcript preTranscript = new Transcript();
                boolean pre = true;
                for(String precrn: precrns){
                    preTranscript.setComplete("1");
                    preTranscript.setStudentid(studentid);
                    preTranscript.setCrn(precrn);
                    int ret = transcriptMapper.count(preTranscript);
                    if (ret != 1) {
                        failList.add(courseInfo + FlagDict.UNMET_PREREQ.getM());
                        pre = false;
                        break;
                    }
                }
                if(!pre)
                    continue;

                //检查复选
                if (transcriptMapper.count(transcript) == 0) {
                    transcript.setOperator(IDUtil.ROOT);
                    int ret = transcriptMapper.insert(transcript);
                    if (ret != 1){
                        haramMessage.setMsg(FlagDict.FAIL.getM());
                        haramMessage.setCode(FlagDict.FAIL.getV());
                        failList.add(courseInfo + FlagDict.FAIL.getM());
                    }
                } else {
                    haramMessage.setMsg(FlagDict.REPEAT.getM());
                    haramMessage.setCode(FlagDict.REPEAT.getV());
                    failList.add(courseInfo + FlagDict.REPEAT.getM());
                }
            }
            haramMessage.setMsg(FlagDict.SUCCESS.getM());
            haramMessage.setCode(FlagDict.SUCCESS.getV());
            result.put("failList", failList);
            haramMessage.setData(result);
            return haramMessage;

        }catch (Exception e){
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
    }

    
}
