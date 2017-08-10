package com.harambase.pioneer.service.Impl;

import com.harambase.common.DateUtil;
import com.harambase.common.HaramMessage;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.CourseMapper;
import com.harambase.pioneer.pojo.Course;
import com.harambase.pioneer.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by linsh on 7/12/2017.
 */
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper){
        this.courseMapper = courseMapper;
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
        return null;
    }

    @Override
    public HaramMessage assignFac2Cou(String facultyid, String courseid) {
        return null;
    }

    @Override
    public HaramMessage addStu2Cou(String studentid, String courseid) {
        return null;
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
    public HaramMessage updateGrade(String courseid, String studentid, String grade) {
        return null;
    }
}

