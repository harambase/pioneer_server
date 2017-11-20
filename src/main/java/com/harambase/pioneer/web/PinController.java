package com.harambase.pioneer.web;

import com.harambase.common.HaramMessage;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin
@RequestMapping(value = "/pin")
public class PinController {
    private final PinService pinService;
    
    @Autowired
    public PinController(PinService pinService){
        this.pinService = pinService;
    }
    
    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public ResponseEntity validate(@RequestParam(value = "pin") Integer pin,
                                    HttpSession session) {
        Person user = (Person)session.getAttribute("user");
        HaramMessage haramMessage = pinService.validate(pin, user);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public ResponseEntity generate(@RequestParam(value = "startTime") String startTime,
                                   @RequestParam(value = "endTime") String endTime,
                                   @RequestParam(value = "role") int role,
                                   @RequestParam(value = "info") String info,
                                   @RequestParam(value = "remark") String remark){
        HaramMessage haramMessage = pinService.generate(startTime, endTime, role, info, remark);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/clear/all", method = RequestMethod.GET)
    public ResponseEntity clearAll(@RequestParam(value = "info") String info, HttpSession session){
        Person user = (Person)session.getAttribute("user");
        HaramMessage haramMessage = pinService.clearAll(user);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/send/faculty/pin", method = RequestMethod.GET)
    public ResponseEntity sendFacultyPin(@RequestParam(value = "info") String info, HttpSession session){
        Person user = (Person)session.getAttribute("user");
        HaramMessage haramMessage = pinService.sendFacultyPin(info, user.getUserid());
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/send/advisor/pin", method = RequestMethod.GET)
    public ResponseEntity sendAdvisorPin(@RequestParam(value = "info") String info, HttpSession session){
        Person user = (Person)session.getAttribute("user");
        HaramMessage haramMessage = pinService.sendAdvisorPin(info, user.getUserid());
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }


}
