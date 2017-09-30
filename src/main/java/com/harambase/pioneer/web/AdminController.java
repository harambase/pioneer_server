package com.harambase.pioneer.web;

import com.alibaba.fastjson.JSONObject;
import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.pojo.Advise;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.service.CourseService;
import com.harambase.pioneer.service.PersonService;
import com.harambase.pioneer.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by linsh on 7/12/2017.
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/admin")
public class AdminController {

    private final CourseService courseService;
    private final PersonService personService;

    @Autowired
    public AdminController(CourseService courseService,
                           PersonService personService){
        this.courseService = courseService;
        this.personService = personService;
    }

    @RequestMapping(value = "/student/count", method = RequestMethod.GET)
    public ResponseEntity getStudentCount(){
        HaramMessage haramMessage = personService.userChart();
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/relation/chart", method = RequestMethod.GET)
    public ResponseEntity getRelationChart(){
        HaramMessage haramMessage = personService.getRelationChart();
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity getUser(@RequestParam(value = "userid") String userid){
        HaramMessage haramMessage = personService.getUser(userid);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
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

    @RequestMapping(value ="/advise/assign", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity assignMentor(@RequestBody Advise advise){
        HaramMessage message = personService.assignMentor(advise);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value ="/advise/remove", produces = "application/json", method = RequestMethod.DELETE)
    public ResponseEntity removeMentor(@RequestParam(value = "id") Integer id ) {
        HaramMessage message = personService.removeMentor(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
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

    @RequestMapping(value = "/advise/list", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity advisingList(@RequestParam(value = "start") Integer start,
                                       @RequestParam(value = "length") Integer length,
                                       @RequestParam(value = "draw") Integer draw,
                                       @RequestParam(value = "search[value]") String search,
                                       @RequestParam(value = "order[0][dir]") String order,
                                       @RequestParam(value = "order[0][column]") String orderCol,
                                       @RequestParam(value = "studentid", required = false) String studentid,
                                       @RequestParam(value = "facultyid", required = false) String facultyid,
                                       HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            HaramMessage message = personService.advisingList(String.valueOf(start / length + 1), String.valueOf(length), search,
                    order, orderCol, studentid, facultyid);
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
    public ResponseEntity searchFaculty(@RequestParam(value = "search") String search){
        HaramMessage message = personService.listFaculties(search);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/list/student", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity searchStudent(@RequestParam(value = "search") String search){
        HaramMessage message = personService.listStudents(search);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/advise/update", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity adviseUpdate(@RequestBody Advise advise){
        HaramMessage message = personService.updateAdvise(advise);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/system/info", produces = "application/json")
    public ResponseEntity getSystemInfo(){
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

    @RequestMapping(value = "/remove/user", method = RequestMethod.DELETE)
    public ResponseEntity removeUser(@RequestParam("userid") String userid){
        HaramMessage message = personService.removeUser(userid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
