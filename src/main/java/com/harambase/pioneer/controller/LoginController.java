package com.harambase.pioneer.controller;

import com.harambase.common.HaramMessage;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.service.PersonService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final PersonService personService;

    @Autowired
    public LoginController(PersonService personService){
        this.personService = personService;
    }

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody Person person, HttpSession session){
        HaramMessage message = personService.login(person);

        if(message.getCode() == 2001) {
            person = (Person)message.getData();
            UsernamePasswordToken token = new UsernamePasswordToken(person.getUserid(),person.getPassword().toCharArray()) ;
            Subject subject = SecurityUtils.getSubject();
            subject.login(token); //完成登录
            session.setAttribute("user", person);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/logout", method = RequestMethod.POST)
    public ResponseEntity logout(HttpSession session){
        HaramMessage message = new HaramMessage();
        session.invalidate();
        SecurityUtils.getSubject().logout();
        message.setCode(FlagDict.SUCCESS.getV());
        message.setMsg(FlagDict.SUCCESS.getM());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
