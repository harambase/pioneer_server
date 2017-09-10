package com.harambase.pioneer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by shilei on 2017/08/07.
 */
@Controller
public class WebController {
    
    @RequestMapping("/welcomeAdmin")
    public String welcomeAdmin(){
        return "welcomeAdmin";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/manageCourse")
    public String adminManageCourse(){
        return "course/manageCourse";
    }
    @RequestMapping("/manageTranscript")
    public String adminManageTranscript(){
        return "transcript/manageTranscript";
    }
    @RequestMapping("/manageUser")
    public String adminManageUser(){
        return "account/manageUser";
    }
    @RequestMapping("/manageAdvising")
    public String adminManageAdvising(){
        return "advising/manageAdvising";
    }
    @RequestMapping("/adminProfile")
    public String adminProfile(){
        return "account/adminProfile";
    }
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
    
}
