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



}
