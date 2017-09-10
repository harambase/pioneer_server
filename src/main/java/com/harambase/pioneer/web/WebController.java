package com.harambase.pioneer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by shilei on 2017/08/07.
 */
@Controller
public class WebController {
    
//    @RequestMapping("/welcomeAdmin")
//    public String welcomeAdmin(){
//        return "welcomeAdmin";
//    }
   
    @RequestMapping("/signIn")
    public String login(){
        return "release/auth";
    }
    
}
