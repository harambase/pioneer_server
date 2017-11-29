package com.harambase.pioneer.controller;

import com.harambase.common.HaramMessage;
import com.harambase.pioneer.service.CourseService;
import com.harambase.pioneer.service.PersonService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/system")
public class SystemController {
    private final CourseService courseService;
    private final PersonService personService;
    
    @Autowired
    public SystemController(CourseService courseService,
                            PersonService personService){
        this.courseService = courseService;
        this.personService = personService;
    }
    
    @RequiresPermissions("user")
    @RequestMapping(value = "/Info", method = RequestMethod.GET)
    public ResponseEntity getSystemInfo(){
        HaramMessage message = new HaramMessage();
        
        Map<String, Integer> data = new HashMap<>();
        int course, student, faculty;
        
        student = (Integer) personService.countActivePerson("s").getData();
        faculty = (Integer) personService.countActivePerson("f").getData();
        course  = (Integer) courseService.countActiveCourse().getData();
        
        data.put("student",student);
        data.put("faculty",faculty);
        data.put("teach", course);
        
        message.setData(data);
        return new ResponseEntity<>(message, HttpStatus.OK);
        
    }
    
    @RequiresPermissions("user")
    @RequestMapping(value = "/relation", method = RequestMethod.GET)
    public ResponseEntity getRelationChart(){
        HaramMessage haramMessage = personService.getRelationChart();
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }
    
    @RequiresPermissions("user")
    @RequestMapping(value = "/user/count", method = RequestMethod.GET)
    public ResponseEntity getUserCount(){
        HaramMessage haramMessage = personService.userChart();
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }
    
}
