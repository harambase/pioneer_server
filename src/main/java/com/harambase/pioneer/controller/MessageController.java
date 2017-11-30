package com.harambase.pioneer.controller;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.pioneer.pojo.MessageWithBLOBs;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.service.MessageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/message")
public class MessageController implements MessageApi {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @Override
    @RequiresPermissions("user")
    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody MessageWithBLOBs message, HttpSession session){
        Person user = (Person)session.getAttribute("user");
        message.setSenderid(user.getUserid());
        HaramMessage haramMessage = messageService.createMessage(message);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions("user")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(value = "id") Integer id) {
        HaramMessage haramMessage = messageService.delete(id);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions("user")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable(value = "id") Integer id,
                                 @RequestBody MessageWithBLOBs message) {
        HaramMessage haramMessage = messageService.update(id, message);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @RequiresPermissions("user")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@RequestParam(value = "id") Integer id) {
        HaramMessage haramMessage = messageService.getMessageView(id);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions("user")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity count(@RequestParam(value = "status") String status,
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

    @Override
    @RequiresPermissions("user")
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
