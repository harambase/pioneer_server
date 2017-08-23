package com.harambase.pioneer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by shilei on 2017/08/07.
 */
@Controller
public class WebController {

    @RequestMapping("/welcomeStudent")
    public String welcomeStudent(){
        return "student/welcomeStudent";
    }
    @RequestMapping("/welcomeAdmin")
    public String welcomeAdmin(){
        return "admin/welcomeAdmin";
    }
    @RequestMapping("/welcomeFaculty")
    public String welcomeFaculty(){
        return "faculty/welcomeFaculty";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/studentHeader")
    public String header(){
        return "student/studentHeader";
    }
    @RequestMapping("/manageCourse")
    public String adminManageCourse(){
        return "admin/manageCourse";
    }
    @RequestMapping("/manageTranscript")
    public String adminManageTranscript(){
        return "admin/manageTranscript";
    }
    @RequestMapping("/manageUser")
    public String adminManageUser(){
        return "admin/manageUser";
    }
    @RequestMapping("/manageAdvising")
    public String adminManageAdvising(){
        return "admin/manageAdvising";
    }
    @RequestMapping("/adminProfile")
    public String adminProfile(){
        return "admin/adminProfile";
    }
    
}
