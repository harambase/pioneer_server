package com.harambase.pioneer.controller;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.service.CourseService;
import com.harambase.pioneer.service.PersonService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping(value = "/user")
public class PersonController {
    
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }
    
    @RequiresPermissions({"admin", "system"})
    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody Person person){
        HaramMessage message = personService.addUser(person);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
    @RequiresPermissions({"admin", "system"})
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity removeUser(@RequestParam("userId") String userid){
        HaramMessage message = personService.removeUser(userid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
    @RequiresPermissions({"admin", "system"})
    @RequestMapping(produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody Person person){
        HaramMessage message = personService.update(person);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
    @RequiresPermissions("user")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable(value = "userId") String userId){
        HaramMessage haramMessage = personService.getUser(userId);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }
    
    @RequiresPermissions("user")
    @RequestMapping(value = "/current", method = RequestMethod.GET)
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
    
    @RequiresPermissions("user")
    @RequestMapping(value = "/faculty",method = RequestMethod.GET)
    public ResponseEntity searchFaculty(@RequestParam(value = "search") String search){
        HaramMessage message = personService.listUsers(search,"f", "1");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
    @RequiresPermissions("user")
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ResponseEntity searchStudent(@RequestParam(value = "search") String search){
        HaramMessage message = personService.listUsers(search, "s", "1");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
    @RequiresPermissions("user")
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public ResponseEntity listActiveUsers(@RequestParam(value = "search") String search){
        HaramMessage message = personService.listUsers(search, "", "1");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
    @RequiresPermissions("user")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity listUsers(@RequestParam(value = "start") Integer start,
                                    @RequestParam(value = "length") Integer length,
                                    @RequestParam(value = "draw") Integer draw,
                                    @RequestParam(value = "search[value]") String search,
                                    @RequestParam(value = "order[0][dir]") String order,
                                    @RequestParam(value = "order[0][column]") String orderCol,
                                    @RequestParam(value = "type", required = false) String type,
                                    @RequestParam(value = "status", required = false) String status) {
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
    
}
