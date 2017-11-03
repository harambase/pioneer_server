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
    
    //before login
    @RequestMapping("")
    public String login(){
        return "release/index";
    }
    @RequestMapping("/index")
    public String index(){
        return "release/index";
    }
    @RequestMapping("/welcome")
    public String welcome(){
        return "release/welcome";
    }
    @RequestMapping("/reg")
    public String signUp(){
        return "release/web/reg";
    }
    
    //index
    @RequestMapping("/welcomeStudent")
    public String welcomeStudent(){
        return "release/student/index";
    }
    @RequestMapping("/welcomeAdmin")
    public String welcomeAdmin(){
        return "release/admin/index";
    }
    @RequestMapping("/welcomeFaculty")
    public String welcomeFaculty(){
        return "release/faculty/index";
    }

    //faculty
    //course
    @RequestMapping("/faculty/course/create")
    public String fCreateCourse(){
        return "release/faculty/course/createCourse";
    }
    @RequestMapping("/faculty/course/view")
    public String fViewCourse(){
        return "release/faculty/course/viewCourse";
    }
    @RequestMapping("/faculty/course/edit")
    public String fEditCourse(){
        return "release/faculty/course/editCourse";
    }
    //administration
    @RequestMapping("/faculty/advising")
    public String fViewAdvising(){
        return "release/faculty/administration/viewAdvising";
    }

    //manage
    //course
    @RequestMapping("/manage/course/create")
    public String createCourse(){
        return "release/admin/course/createCourse";
    }
    @RequestMapping("/manage/course/view")
    public String viewCourse(){
        return "release/admin/course/viewCourse";
    }
    @RequestMapping("/manage/course/edit")
    public String editCourse(){
        return "release/admin/course/editCourse";
    }
    @RequestMapping("/manage/course/time")
    public String setTime(){
        return "release/admin/course/setTime";
    }
    @RequestMapping("/manage/transcript/view")
    public String viewTranscript(){
        return "release/admin/course/viewTranscript";
    }
    @RequestMapping("/manage/transcript/edit")
    public String editTranscript(){
        return "release/admin/course/editTranscript";
    }
    @RequestMapping("/manage/student/credit")
    public String setCredit(){
        return "release/admin/course/setCredit";
    }
    @RequestMapping("/manage/course/request")
    public String viewCourseRequest(){
        return "release/admin/course/viewCourseRequest";
    }
    
    //administration
    @RequestMapping("/manage/leave")
    public String viewLeave(){
        return "release/admin/administration/viewLeave";
    }
    @RequestMapping("/manage/dorm")
    public String viewDorm(){
        return "release/admin/administration/viewDorm";
    }
    @RequestMapping("/manage/advising")
    public String viewAdvising(){
        return "release/admin/administration/viewAdvising";
    }
    
    //system
    @RequestMapping("/manage/user/create")
    public String createUser(){
        return "release/admin/system/createUser";
    }
    @RequestMapping("/manage/user/view")
    public String viewUser(){
        return "release/admin/system/viewUser";
    }
    @RequestMapping("/manage/user/detail")
    public String userDetail(){
        return "release/admin/system/userDetail";
    }
    @RequestMapping("/manage/user/request")
    public String viewRegister(){
        return "release/admin/system/viewRegister";
    }
    @RequestMapping("/manage/user/request/detail")
    public String userRegisterDetail(){
        return "release/admin/system/userRegisterDetail";
    }
    
    //web
    @RequestMapping("/manage/message")
    public String message(){
        return "release/web/message";
    }
    @RequestMapping("/manage/profile")
    public String profile() {
        return "release/web/profile";
    }
    @RequestMapping("/403")
    public String authError(){
        return "release/web/403";
    }
    @RequestMapping("/404")
    public String pageNotFound(){
        return "release/web/404";
    }

}
