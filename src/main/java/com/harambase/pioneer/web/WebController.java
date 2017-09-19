package com.harambase.pioneer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String index(){
        return "release/index";
    }
}
