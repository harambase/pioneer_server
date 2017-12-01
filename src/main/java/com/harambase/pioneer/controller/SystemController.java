package com.harambase.pioneer.controller;

import com.harambase.common.HaramMessage;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.controller.api.SystemApi;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.service.CourseService;
import com.harambase.pioneer.service.PersonService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/system")
public class SystemController {//implements SystemApi {
    private final CourseService courseService;
    private final PersonService personService;
    
    @Autowired
    public SystemController(CourseService courseService,
                            PersonService personService){
        this.courseService = courseService;
        this.personService = personService;
    }

    //@Override
    @RequestMapping(value = "/login", method = RequestMethod.POST)
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

    //@Override
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity logout(HttpSession session){
        HaramMessage message = new HaramMessage();
        session.invalidate();
        SecurityUtils.getSubject().logout();
        message.setCode(FlagDict.SUCCESS.getV());
        message.setMsg(FlagDict.SUCCESS.getM());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //@Override
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @RequiresPermissions("user")
    public ResponseEntity systemInfo(){
        HaramMessage message = new HaramMessage();

        Map<String, Integer> data = new HashMap<>();
        int course, student, faculty;

        student = (Integer) personService.countActivePerson("s").getData();
        faculty = (Integer) personService.countActivePerson("f").getData();
        course  = (Integer) courseService.countActiveCourse().getData();

        data.put("student",student);
        data.put("faculty",faculty);
        data.put("course", course);

        message.setData(data);
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    //@Override
    @RequestMapping(value = "/relation", method = RequestMethod.GET)
    @RequiresPermissions("user")
    public ResponseEntity relationChart(){
        HaramMessage haramMessage = personService.getRelationChart();
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    //@Override
    @RequestMapping(value = "/user/count", method = RequestMethod.GET)
    @RequiresPermissions("user")
    public ResponseEntity userCount(){
        HaramMessage haramMessage = personService.userChart();
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

}
