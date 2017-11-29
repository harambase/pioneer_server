package com.harambase.pioneer.controller;

import com.harambase.common.HaramMessage;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.pojo.Pin;
import com.harambase.pioneer.service.PinService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequiresPermissions({"admin", "teach"})
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestParam(value = "startTime") String startTime,
                                 @RequestParam(value = "endTime") String endTime,
                                 @RequestParam(value = "role") int role,
                                 @RequestParam(value = "info") String info,
                                 @RequestParam(value = "remark") String remark){
        HaramMessage haramMessage = pinService.generate(startTime, endTime, role, info, remark);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }


    @RequiresPermissions({"admin", "teach"})
    @RequestMapping(value = "/{pin}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(value = "pin") String pin, HttpSession session){
        HaramMessage haramMessage = pinService.delete(pin);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @RequiresPermissions({"admin", "teach"})
    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity deleteAll(@RequestParam(value = "info") String info, HttpSession session){
        HaramMessage haramMessage = pinService.clearAll(info);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @RequiresPermissions("user")
    @RequestMapping(value = "/{pin}", method = RequestMethod.GET)
    public ResponseEntity validate(@PathVariable(value = "pin") Integer pin, HttpSession session) {
        HaramMessage haramMessage = pinService.validate(pin);
        if(haramMessage.getCode() == FlagDict.SUCCESS.getV())
            session.setAttribute("pin", haramMessage.getData());
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @RequiresPermissions("user")
    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public ResponseEntity verify(HttpSession session) {
        Pin pin = (Pin)session.getAttribute("pin");
        HaramMessage haramMessage = pinService.validate(pin.getPin());
        if(haramMessage.getCode() == FlagDict.SUCCESS.getV())
            session.setAttribute("pin", haramMessage.getData());
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @RequiresPermissions({"admin", "teach"})
    @RequestMapping(value = "/send/faculty/{info}", method = RequestMethod.GET)
    public ResponseEntity sendFacultyPin(@PathVariable(value = "info") String info, HttpSession session){
        Person user = (Person)session.getAttribute("user");
        HaramMessage haramMessage = pinService.sendFacultyPin(info, user.getUserid());
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @RequiresPermissions({"admin", "teach"})
    @RequestMapping(value = "/send/advisor/{pin}", method = RequestMethod.GET)
    public ResponseEntity sendAdvisorPin(@PathVariable(value = "info") String info, HttpSession session){
        Person user = (Person)session.getAttribute("user");
        HaramMessage haramMessage = pinService.sendAdvisorPin(info, user.getUserid());
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }


}
