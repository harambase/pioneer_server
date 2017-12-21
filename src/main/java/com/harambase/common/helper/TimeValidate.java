package com.harambase.common.helper;

import com.harambase.pioneer.pojo.view.CourseView;
import com.harambase.support.util.DateUtil;
import com.harambase.pioneer.pojo.Pin;

import java.util.*;

public class TimeValidate {

    public static boolean isTimeConflict(List<CourseView> courseList, CourseView c2){
        for(CourseView c1: courseList){
            Date c1_start = DateUtil.StrToDateOnly(c1.getStartdate());
            Date c1_end   = DateUtil.StrToDateOnly(c1.getEnddate());
            Date c2_start = DateUtil.StrToDateOnly(c2.getStartdate());
            Date c2_end   = DateUtil.StrToDateOnly(c2.getEnddate());
            Date c1_t_str = DateUtil.StrToDateTimeOnly(c1.getStarttime());
            Date c1_t_end = DateUtil.StrToDateTimeOnly(c1.getEndtime());
            Date c2_t_str = DateUtil.StrToDateTimeOnly(c2.getStarttime());
            Date c2_t_end = DateUtil.StrToDateTimeOnly(c2.getEndtime());

            Set<String> c1_day = getDay(c1.getDay().split("/"));
            Set<String> c2_day = getDay(c2.getDay().split("/"));

            //retainAll:true if this set changed as a result of the call
            if(c1_day.retainAll(c2_day)) {//如果包含相同天
                if(c1_start.compareTo(c2_start) == 0       //c1、c2开始时间相同
                        || c1_end.compareTo(c2_start) >= 0 //c1结束时间等于或晚于c2开始时间
                        || c2_end.compareTo(c1_start) >= 0){ //c2结束时间等于或晚于c1开始时间
                    if (c1_t_str.compareTo(c2_t_str) == 0) return true;//开始时间相同
                    if (c1_t_end.compareTo(c2_t_str) > 0) return true;//1结束晚于2开始
                    if (c2_t_end.compareTo(c1_t_str) > 0) return true;//2结束晚于1开始
                }
            }
        }
        return false;
    }

    private static Set<String> getDay(String[] split) {
        Set<String> result = new HashSet<>();
        for(String s : split){
            result.add(s);
        }
        return result;
    }

    public static boolean isPinValidate(Pin pin) {

        Date startTime = DateUtil.StrToDate(pin.getStarttime());
        Date endTime   = DateUtil.StrToDate(pin.getEndtime());
        Date nowTime   = DateUtil.StrToDate(DateUtil.DateToStr(new Date()));

        return startTime.compareTo(nowTime) <= 0 && endTime.compareTo(nowTime) >= 0;
    }
}
