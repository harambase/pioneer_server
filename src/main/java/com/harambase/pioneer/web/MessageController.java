package com.harambase.pioneer.web;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.pioneer.pojo.MessageWithBLOBs;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.service.MessageService;
import com.harambase.pioneer.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping(value = "/message")
public class MessageController {

    private final MessageService messageService;
    private final PersonService personService;


    @Autowired
    public MessageController(MessageService messageService, PersonService personService){
        this.messageService = messageService;
        this.personService = personService;
    }

    @RequestMapping(value = "/create", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity createMessage(@RequestBody MessageWithBLOBs message, HttpSession session){
        Person user = (Person)session.getAttribute("user");
        message.setSenderid(user.getUserid());
        HaramMessage haramMessage = messageService.createMessage(message);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ResponseEntity getMessageView(@RequestParam(value = "id") String id){
        HaramMessage haramMessage = messageService.getMessageView(id);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/update/status", method = RequestMethod.PUT)
    public ResponseEntity updateStatus(@RequestParam(value = "id") String id,
                                       @RequestParam(value = "status") String status){
        HaramMessage haramMessage = messageService.updateStatus(id, status);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity countByStatus(@RequestParam(value = "status") String status,
                                        @RequestParam(value = "box") String box,
                                        HttpSession session){
        Person user = (Person)session.getAttribute("user");
        String receiverid = null;
        String senderid = null;
    
        if(box.contains("inbox") || box.contains("important"))
            receiverid = user.getUserid();
        if(box.contains("sent") || box.contains("draft"))
            senderid = user.getUserid();
        if(box.contains("trash")) {
            receiverid = user.getUserid();
            senderid = user.getUserid();
        }
       
        HaramMessage haramMessage = messageService.countMessageByStatus(receiverid, senderid, box.toLowerCase(), status.toLowerCase());
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity list(@RequestParam(value = "start") Integer start,
                               @RequestParam(value = "length") Integer length,
                               @RequestParam(value = "draw") Integer draw,
                               @RequestParam(value = "search[value]") String search,
                               @RequestParam(value = "order[0][dir]") String order,
                               @RequestParam(value = "order[0][column]") String orderCol,
                               @RequestParam(value = "box") String box,
                               HttpSession session) {

        Person user = (Person)session.getAttribute("user");
        String receiverid = null;
        String senderid = null;

        if(box.contains("inbox") || box.contains("important"))
            receiverid = user.getUserid();
        if(box.contains("sent") || box.contains("draft"))
            senderid = user.getUserid();
        if(box.contains("trash")) {
            receiverid = user.getUserid();
            senderid = user.getUserid();
        }

        Map<String, Object> map = new HashMap<>();
        try {
            HaramMessage message = messageService.list(String.valueOf(start / length + 1), String.valueOf(length), search,
                    order, orderCol, receiverid, senderid, box);
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
