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
    @RequestMapping("")
    public String login(){
        return "page/index";
    }
    @RequestMapping("/reg")
    public String signUp(){
        return "release/reg";
    }
    @RequestMapping("/auth")
    public String auth(){
        return "page/index";
    }
    @RequestMapping("/index")
    public String index(){
        return "page/index";
    }
    @RequestMapping("/welcomeStudent")
    public String welcomeStudent(){
        return "student/welcomeStudent";
    }
    @RequestMapping("/welcomeAdmin")
    public String welcomeAdmin(){
        return "page/welcomeAdmin";
    }
    @RequestMapping("/welcomeFaculty")
    public String welcomeFaculty(){
        return "page/faculty/welcomeFaculty";
    }
    @RequestMapping("/manageCourse")
    public String adminManageCourse(){
        return "page/course/manageCourse";
    }
    @RequestMapping("/manageTranscript")
    public String adminManageTranscript(){
        return "release/admin/course/viewTranscript";
    }
    @RequestMapping("/manageUser")
    public String adminManageUser(){
        return "page/account/manageUser";
    }
    @RequestMapping("/manageRequest")
    public String adminmanageRequest(){
        return "page/administration/userRequest";
    }
    @RequestMapping("/manageAdvising")
    public String adminManageAdvising(){
        return "page/advising/manageAdvising";
    }
    @RequestMapping("/adminProfile")
    public String adminProfile(){
        return "page/account/adminProfile";
    }
    @RequestMapping("/welcome")
    public String welcome(){
        return "page/welcome";
    }
    @RequestMapping("/messageCenter")
    public String messageCenter(){
        return "page/message/messageCenter";
    }
    @RequestMapping("/403")
    public String authError(){
        return "release/403";
    }
    @RequestMapping("/404")
    public String pageNotFound(){
        return "release/404";
    }

}
