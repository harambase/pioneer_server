package com.harambase.pioneer.web;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    @RequestMapping(value = "/student/count", method = RequestMethod.GET)
    public ResponseEntity getStudentCount(){
        HaramMessage haramMessage = personService.countPerson();
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity getUser(@RequestParam(value = "userid") String userid){
        HaramMessage haramMessage = personService.getUser(userid);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody Person person, HttpSession session){
        HaramMessage message = personService.login(person);
        if(message.getCode() == 2001)
            session.setAttribute("user", message.getData());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/current", method = RequestMethod.GET)
    public ResponseEntity getCurrentUser(HttpSession session){
        HaramMessage message = new HaramMessage();

        try {
            Person p = (Person) session.getAttribute("user");

            if (p != null) {
                message.setData(p);
                message.setCode(FlagDict.SUCCESS.getV());
                message.setMsg(FlagDict.SUCCESS.getM());
            } else {
                message.setCode(FlagDict.FAIL.getV());
                message.setMsg(FlagDict.FAIL.getM());
            }
        }catch (Exception e){
            message.setMsg(FlagDict.SYSTEM_ERROR.getM());
            message.setCode(FlagDict.SYSTEM_ERROR.getV());
        }

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

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody Person person, HttpSession session){
        HaramMessage message = personService.addUser(person);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public ResponseEntity updateUser(@RequestBody Person person){
        HaramMessage message = personService.update(person);
        return new ResponseEntity<>(message, HttpStatus.OK);
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

    @RequestMapping(value = "/user/list", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity listUsers(@RequestParam(value = "start") Integer start,
                                    @RequestParam(value = "length") Integer length,
                                    @RequestParam(value = "draw") Integer draw,
                                    @RequestParam(value = "search[value]") String search,
                                    @RequestParam(value = "order[0][dir]") String order,
                                    @RequestParam(value = "order[0][column]") String orderCol,
                                    @RequestParam(value = "type", required = false) String type,
                                    @RequestParam(value = "status", required = false) String status,
                                    HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            HaramMessage message = personService.userList(String.valueOf(start / length + 1), String.valueOf(length), search,
                    order, orderCol, type, status);
            map.put("draw", draw);
            map.put("recordsTotal", ((Page) message.get("page")).getTotalRows());
            map.put("recordsFiltered", ((Page) message.get("page")).getTotalRows());
            map.put("data", message.getData());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("draw", 1);
            map.put("data", new ArrayList<>());
            map.put("recordsTotal", 0);
            map.put("recordsFiltered", 0);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/list/faculty", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity searchUser(@RequestParam(value = "search") String search){
        HaramMessage message = personService.listFaculties(search);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
