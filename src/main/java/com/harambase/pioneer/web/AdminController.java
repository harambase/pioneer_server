package com.harambase.pioneer.web;

import com.harambase.common.HaramMessage;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.service.CourseService;
import com.harambase.pioneer.service.PersonService;
import com.harambase.pioneer.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by linsh on 7/12/2017.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final CourseService courseService;
    private final StudentService studentService;
    private final PersonService personService;

    @Autowired
    public AdminController(CourseService courseService,
                           StudentService studentService,
                           PersonService personService){
        this.courseService = courseService;
        this.studentService = studentService;
        this.personService = personService;
    }



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody Person person, HttpSession session){
        HaramMessage message = personService.login(person);
        if(message.getCode() == 2001)
            session.setAttribute("user", message.getData());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity logout(HttpSession session){
        session.invalidate();
        HaramMessage message = new HaramMessage();
        message.setCode(FlagDict.SUCCESS.getV());
        message.setMsg(FlagDict.SUCCESS.getM());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody Person person, HttpSession session){
        HaramMessage message = personService.addUser(person);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    public String updateGrade(@RequestParam(value = "courseid") String courseid,
                              @RequestParam(value = "studentid") String studentid,
                              @RequestParam(value = "grade") String grade) {
        courseService.updateGrade(courseid, studentid, grade);
        return null;
    }

    public String assignMentor(@RequestParam(value = "studentid") String studentid,
                               @RequestParam(value = "facultyid") String facultyid){
        studentService.assignMentor(studentid, facultyid);
        return null;
    }

    public String removeMentor(@RequestParam(value = "studentid") String studentid,
                               @RequestParam(value = "facultyid") String facultyid) {
        studentService.removeMentor(studentid, facultyid);
        return null;
    }

    public String changeMentor(@RequestParam(value = "studentid") String studentid,
                               @RequestParam(value = "facultyid") String facultyid) {
        studentService.updateMentor(studentid, facultyid);
        return null;
    }
}
