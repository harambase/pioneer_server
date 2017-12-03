package com.harambase.common.util;

import com.harambase.pioneer.pojo.Person;
import org.apache.shiro.SecurityUtils;


public class SessionUtil {
    public static Person getUser(){
        return (Person)SecurityUtils.getSubject().getSession().getAttribute("user");
    }

    public static String getUserId(){
        return ((Person)SecurityUtils.getSubject().getSession().getAttribute("user")).getUserid();
    }
}
