package com.harambase.pioneer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by shilei on 2017/08/07.
 */
@Controller
@CrossOrigin
public class WebController {

    @RequestMapping("/reg")
    public String signUp(){
        return "release/reg";
    }
    @RequestMapping("/auth")
    public String auth(){
        return "release/auth";
    }
    @RequestMapping(value="/index")
    public String index(){
        return "release/index";
    }
    @RequestMapping("/welcomeStudent")
    public String welcomeStudent(){
        return "student/welcomeStudent";
    }
    @RequestMapping("/welcomeAdmin")
    public String welcomeAdmin(){
        return "page/admin/welcomeAdmin";
    }
    @RequestMapping("/welcomeFaculty")
    public String welcomeFaculty(){
        return "page/faculty/welcomeFaculty";
    }
    @RequestMapping("/studentHeader")
    public String header(){
        return "page/student/studentHeader";
    }
    @RequestMapping("/manageCourse")
    public String adminManageCourse(){
        return "page/admin/manageCourse";
    }
    @RequestMapping("/manageTranscript")
    public String adminManageTranscript(){
        return "page/admin/manageTranscript";
    }
    @RequestMapping("/manageUser")
    public String adminManageUser(){
        return "page/admin/manageUser";
    }
    @RequestMapping("/manageAdvising")
    public String adminManageAdvising(){
        return "page/admin/manageAdvising";
    }
    @RequestMapping("/adminProfile")
    public String adminProfile(){
        return "page/admin/adminProfile";
    }
    @RequestMapping("/welcome")
    public String welcome(){
        return "page/welcome";
    }

    public static void basePath(HttpServletRequest request){
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
        request.setAttribute("basePath", basePath);
    }
}
