package com.harambase.pioneer.web;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.pioneer.pojo.Course;
import com.harambase.pioneer.pojo.Transcript;
import com.harambase.pioneer.pojo.dto.Option;
import com.harambase.pioneer.service.StudentService;
import com.harambase.pioneer.service.CourseService;
import com.harambase.pioneer.service.FacultyService;
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
@RequestMapping(value = "/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity createdCourse(@RequestBody Course course){
        HaramMessage haramMessage = courseService.add(course);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);

    }
    @RequestMapping(value = "/list/search", method = RequestMethod.GET)
    public ResponseEntity listBySearch(@RequestParam("search") String search){
        HaramMessage haramMessage = courseService.listBySearch(search);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public ResponseEntity removeCourse(@RequestParam(value = "crn") String crn){
        HaramMessage haramMessage = courseService.remove(crn);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/student/remove", method = RequestMethod.DELETE)
    public ResponseEntity removeStuFromCourse(@RequestParam(value = "studentid") String studentid,
                                              @RequestParam(value = "crn") String crn){
        HaramMessage haramMessage = courseService.removeStuFromCou(studentid, crn);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity updateCourse(@RequestBody Course course){
        HaramMessage haramMessage = courseService.updateCourse(course);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/add/student", method = RequestMethod.POST)
    public ResponseEntity addStu2Cou(@RequestBody Option option){
        HaramMessage haramMessage = courseService.addStu2Cou(option);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/assign/faculty", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity assignF2Course(@RequestBody Course course){
        HaramMessage haramMessage = courseService.assignFac2Cou(course);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/list", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity listCourses(@RequestParam(value = "start") Integer start,
                                    @RequestParam(value = "length") Integer length,
                                    @RequestParam(value = "draw") Integer draw,
                                    @RequestParam(value = "search[value]") String search,
                                    @RequestParam(value = "order[0][dir]") String order,
                                    @RequestParam(value = "order[0][column]") String orderCol,
                                    HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            HaramMessage message = courseService.courseList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol);
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

    @RequestMapping(value = "/transcript/list", produces = "application/json", method = RequestMethod.GET)
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
            HaramMessage message = courseService.transcriptList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol, studentid, crn);
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

    @RequestMapping(value = "/transcript/update", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity updateTranscript(@RequestBody Transcript transcript){
        HaramMessage haramMessage = courseService.updateGrade(transcript);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/student/list", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity listStudent(@RequestParam(value = "start") Integer start,
                                      @RequestParam(value = "length") Integer length,
                                      @RequestParam(value = "draw") Integer draw,
                                      @RequestParam(value = "search[value]") String search,
                                      @RequestParam(value = "order[0][dir]") String order,
                                      @RequestParam(value = "order[0][column]") String orderCol,
                                      @RequestParam(value = "crn", required = false) String crn) {
        Map<String, Object> map = new HashMap<>();
        try {
            HaramMessage message = courseService.studentList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol, crn);
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
