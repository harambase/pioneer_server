package com.harambase.pioneer.controller;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.pojo.Transcript;
import com.harambase.pioneer.service.TranscriptService;
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
@RequestMapping(value = "/transcript")
public class TranscriptController {
    
    private final TranscriptService transcriptService;
    
    @Autowired
    public TranscriptController(TranscriptService transcriptService){
        this.transcriptService = transcriptService;
    }
    
    @RequestMapping(value = "/list", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity listTranscripts(@RequestParam(value = "start") Integer start,
                                          @RequestParam(value = "length") Integer length,
                                          @RequestParam(value = "draw") Integer draw,
                                          @RequestParam(value = "search[value]") String search,
                                          @RequestParam(value = "order[0][dir]") String order,
                                          @RequestParam(value = "order[0][column]") String orderCol,
                                          @RequestParam(value = "studentid", required = false) String studentid,
                                          @RequestParam(value = "crn", required = false) String crn) {
        Map<String, Object> map = new HashMap<>();
        try {
            HaramMessage message = transcriptService.transcriptList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol, studentid, crn);
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
    
    @RequestMapping(produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity updateTranscript(@RequestBody Transcript transcript, HttpSession session){
        transcript.setOperator(((Person)session.getAttribute("user")).getUserid());
        HaramMessage haramMessage = transcriptService.updateGrade(transcript);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/list", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity listStudent(@RequestParam(value = "start") Integer start,
                                      @RequestParam(value = "length") Integer length,
                                      @RequestParam(value = "draw") Integer draw,
                                      @RequestParam(value = "search[value]") String search,
                                      @RequestParam(value = "order[0][dir]") String order,
                                      @RequestParam(value = "order[0][column]") String orderCol,
                                      @RequestParam(value = "crn", required = false) String crn) {
        Map<String, Object> map = new HashMap<>();
        try {
            HaramMessage message = transcriptService.studentList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol, crn);
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
