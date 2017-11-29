package com.harambase.pioneer.controller;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.pioneer.pojo.Advise;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.service.AdviseService;
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

@Controller
@CrossOrigin
@RequestMapping(value = "/advise")
public class AdviseController {

    private AdviseService adviseService;
    
    @Autowired
    public  AdviseController(AdviseService adviseService){
        this.adviseService = adviseService;
    }
    
    @RequiresPermissions({"admin", "teach"})
    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity assignMentor(@RequestBody Advise advise, HttpSession session){
        advise.setOperator(((Person)session.getAttribute("user")).getUserid());
        HaramMessage message = adviseService.assignMentor(advise);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
    @RequiresPermissions({"admin", "teach"})
    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeMentor(@RequestParam(value = "id") Integer id) {
        HaramMessage message = adviseService.removeMentor(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
    @RequiresPermissions({"admin", "teach"})
    @RequestMapping(produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity adviseUpdate(@RequestBody Advise advise, HttpSession session){
        advise.setOperator(((Person)session.getAttribute("user")).getUserid());
        HaramMessage message = adviseService.updateAdvise(advise);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
    @RequiresPermissions("user")
    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public ResponseEntity getMentor(@RequestParam(value = "id") Integer id) {
        //HaramMessage message = adviseService.getMentor(id);
        //return new ResponseEntity<>(message, HttpStatus.OK);
        return null;
    }
    
    @RequiresPermissions("user")
    @RequestMapping(value = "/get", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity advisingList(@RequestParam(value = "start") Integer start,
                                       @RequestParam(value = "length") Integer length,
                                       @RequestParam(value = "draw") Integer draw,
                                       @RequestParam(value = "search[value]") String search,
                                       @RequestParam(value = "order[0][dir]") String order,
                                       @RequestParam(value = "order[0][column]") String orderCol,
                                       @RequestParam(value = "studentid", required = false) String studentid,
                                       @RequestParam(value = "facultyid", required = false) String facultyid,
                                       @RequestParam(value = "mode", required = false) String mode,
                                       HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            if(mode != null && mode.equals("faculty"))
                facultyid = ((Person)session.getAttribute("user")).getUserid();
            HaramMessage message = adviseService.advisingList(String.valueOf(start / length + 1), String.valueOf(length), search,
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

}
