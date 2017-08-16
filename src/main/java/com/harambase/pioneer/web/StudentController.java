package com.harambase.pioneer.web;

import com.harambase.common.HaramMessage;
import com.harambase.pioneer.pojo.Student;
import com.harambase.pioneer.pojo.dto.Option;
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

/**
 * Created by linsh on 7/12/2017.
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final FacultyService facultyService;

    @Autowired
    public StudentController(StudentService studentService,
                             CourseService courseService,
                             FacultyService facultyService){
        this.courseService = courseService;
        this.studentService = studentService;
        this.facultyService = facultyService;
    }

    @RequestMapping(value = "/transcript/info", method = RequestMethod.GET)
    public ResponseEntity getTranscriptDetail(@RequestParam(value = "studentid") String studentid,
                                              HttpSession session){
        HaramMessage haramMessage = studentService.transcriptDetail(studentid);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity update(@RequestBody Student student,
                                              HttpSession session){
        HaramMessage haramMessage = studentService.update(student);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }



}
