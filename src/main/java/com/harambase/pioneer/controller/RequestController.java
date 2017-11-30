package com.harambase.pioneer.controller;

import com.alibaba.fastjson.JSONObject;
import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.pojo.TempUser;
import com.harambase.pioneer.service.CourseService;
import com.harambase.pioneer.service.PersonService;
import com.harambase.pioneer.service.RequestSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/request")
public class RequestController {

    private final CourseService courseService;
    private final PersonService personService;
    private final RequestSerivce requestSerivce;

    @Autowired
    public RequestController(CourseService courseService, PersonService personService, RequestSerivce requestSerivce){
        this.courseService = courseService;
        this.personService = personService;
        this.requestSerivce = requestSerivce;
    }

    @RequestMapping(value = "/update/user", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity updateRequest(@RequestBody TempUser tempUser, HttpSession session){
        Person person = (Person) session.getAttribute("user");
        tempUser.setOperator(person.getUserid());
        HaramMessage message = requestSerivce.updateTempUser(tempUser);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody JSONObject jsonObject){
        HaramMessage haramMessage = requestSerivce.register(jsonObject);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/list", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity userList(@RequestParam(value = "start") Integer start,
                                   @RequestParam(value = "length") Integer length,
                                   @RequestParam(value = "draw") Integer draw,
                                   @RequestParam(value = "search[value]") String search,
                                   @RequestParam(value = "order[0][dir]") String order,
                                   @RequestParam(value = "order[0][column]") String orderCol,
                                   @RequestParam(value = "viewStatus") String viewStatus){
        Map<String, Object> map = new HashMap<>();
        try {
            HaramMessage message = requestSerivce.tempUserList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol, viewStatus);
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

//    @RequestMapping(value = "/teach/list", produces = "application/json", method = RequestMethod.GET)
//    public ResponseEntity courseList(@RequestParam(value = "start") Integer start,
//                                     @RequestParam(value = "length") Integer length,
//                                     @RequestParam(value = "draw") Integer draw,
//                                     @RequestParam(value = "search[value]") String search,
//                                     @RequestParam(value = "order[0][dir]") String order,
//                                     @RequestParam(value = "order[0][column]") String orderCol,
//                                     HttpSession session){
//        Map<String, Object> map = new HashMap<>();
//        try {
//            HaramMessage message = courseService.tempCourseList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol);
//            map.put("draw", draw);
//            map.put("recordsTotal", ((Page) message.get("page")).getTotalRows());
//            map.put("recordsFiltered", ((Page) message.get("page")).getTotalRows());
//            map.put("data", message.getData());
//        } catch (Exception e) {
//            e.printStackTrace();
//            map.put("draw", 1);
//            map.put("data", new ArrayList<>());
//            map.put("recordsTotal", 0);
//            map.put("recordsFiltered", 0);
//        }
//        return new ResponseEntity<>(map, HttpStatus.OK);
//    }

}
