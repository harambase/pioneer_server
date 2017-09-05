package com.harambase.pioneer.helper;

import com.harambase.common.HaramMessage;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.mapper.CourseMapper;
import com.harambase.pioneer.pojo.Course;

import java.util.HashMap;
import java.util.Map;

public class CheckTime {

    public static boolean isTimeConflict(CourseMapper courseMapper, HaramMessage haramMessage, Course course) {
        String time = course.getStarttime() + "-" + course.getEndtime();
        String date = course.getStartdate() + " to " + course.getEnddate();
        String day = course.getDay();
        Map<String, String> param = new HashMap<>();
        param.put("facultyid", course.getFacultyid());
        param.put("time", time);
        param.put("date", date);
        param.put("day", day);
        param.put("crn", course.getCrn());
        int count = courseMapper.facultyTime(param);
        if (count != 0) {
            haramMessage.setMsg(FlagDict.TIMECONFLICT.getM());
            haramMessage.setCode(FlagDict.TIMECONFLICT.getV());
            return true;
        }
        return false;
    }
}
