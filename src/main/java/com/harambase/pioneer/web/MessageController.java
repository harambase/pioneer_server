package com.harambase.pioneer.web;

import com.harambase.common.HaramMessage;
import com.harambase.pioneer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/message")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @RequestMapping(value = "/list")
    public ResponseEntity list(){
        String userid = "";
        HaramMessage haramMessage = messageService.list(userid);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }
}
