package com.harambase.pioneer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by shilei on 2017/08/07.
 */
@Controller
public class WebController {


    @RequestMapping("/reg")
    public String signUp(){
        return "reg";
    }
    @RequestMapping("/auth")
    public String auth(){
        return "auth";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
