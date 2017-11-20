package com.harambase.pioneer.web;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.service.MessageService;
import com.harambase.pioneer.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/list")
    public ResponseEntity listUsers(@RequestParam(value = "start") Integer start,
                                    @RequestParam(value = "length") Integer length,
                                    @RequestParam(value = "draw") Integer draw,
                                    @RequestParam(value = "search[value]") String search,
                                    @RequestParam(value = "order[0][dir]") String order,
                                    @RequestParam(value = "order[0][column]") String orderCol,
                                    HttpSession session) {

        Person user = (Person)session.getAttribute("user");
        String receiverid = user.getUserid();
        Map<String, Object> map = new HashMap<>();
        try {
            HaramMessage message = messageService.list(String.valueOf(start / length + 1), String.valueOf(length), search,
                    order, orderCol, receiverid);
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
    @RequestMapping(value = "/show/img")
    public Object getImg(HttpServletRequest request, HttpServletResponse response, @PathVariable("sender") String sender){

        Person image = (Person)personService.getUser(sender).getData();
        response.setContentType("image/png");
        OutputStream output ;
        try {
            output = response.getOutputStream();
            ByteArrayInputStream in = new ByteArrayInputStream(image.getProfile().getBytes());//获取实体类对应Byte
            int len;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) != -1) {
                output.write(buf, 0, len);
            }
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
