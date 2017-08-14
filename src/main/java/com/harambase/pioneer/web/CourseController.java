package com.harambase.pioneer.web;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.pioneer.pojo.Course;
import com.harambase.pioneer.pojo.Transcript;
import com.harambase.pioneer.service.StudentService;
import com.harambase.pioneer.service.CourseService;
import com.harambase.pioneer.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by linsh on 7/12/2017.
 */
@Controller
@RequestMapping(value = "/course")
public class CourseController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final FacultyService facultyService;

    @Autowired
    public CourseController(StudentService studentService,
                            CourseService courseService,
                            FacultyService facultyService){
        this.studentService = studentService;
        this.courseService = courseService;
        this.facultyService = facultyService;
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

    public String removeCourse(@RequestBody Course course){
        courseService.remove(course);
        return null;
    }

    public String getCourse(@RequestParam String courseid){
        courseService.get(courseid);
        return null;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity updateCourse(@RequestBody Course course){
        HaramMessage haramMessage = courseService.updateCourse(course);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    public String assignFac2Cou(@RequestParam(value = "facultyid") String facultyid,
                                @RequestParam(value = "courseid") String courseid){
        courseService.assignFac2Cou(facultyid, courseid);
        return null;
    }

    public String removeFacFromCou(@RequestParam(value = "facultyid") String facultyid,
                                   @RequestParam(value = "courseid") String courseid){
        courseService.removeFacFromCou(facultyid, courseid);
        return null;
    }

    public String addStu2Cou(@RequestParam(value = "studentid") String studentid,
                             @RequestParam(value = "facultyid") String facultyid,
                             @RequestParam(value = "courseid") String courseid){
        courseService.addStu2Cou(studentid, courseid);
        return null;
    }

    public String removeStuFromCourse(@RequestParam(value = "studentid") String studentid,
                                @RequestParam(value = "courseid") String courseid){
        courseService.removeStuFromCou(studentid, courseid);
        return null;
    }

    public String getNumOfStudent(@RequestParam(value = "courseid") String courseid){
        courseService.countStudent(courseid);
        return null;
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
    public ResponseEntity listCourses(@RequestParam(value = "start") Integer start,
                                      @RequestParam(value = "length") Integer length,
                                      @RequestParam(value = "draw") Integer draw,
                                      @RequestParam(value = "search[value]") String search,
                                      @RequestParam(value = "order[0][dir]") String order,
                                      @RequestParam(value = "order[0][column]") String orderCol,
                                      @RequestParam(value = "studentid", required = false) String studentid,
                                      @RequestParam(value = "crn", required = false) String crn,
                                      HttpSession session) {
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
}
