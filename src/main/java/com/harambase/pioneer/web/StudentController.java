package com.harambase.pioneer.web;

import com.harambase.pioneer.service.StudentService;
import com.harambase.pioneer.service.CourseService;
import com.harambase.pioneer.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by linsh on 7/12/2017.
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private FacultyService facultyService;

    public String createStudent(@RequestBody Student student){
        return null;
    }


}
