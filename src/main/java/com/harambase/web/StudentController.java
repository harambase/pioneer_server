package com.harambase.web;

import com.harambase.pojo.Student;
import com.harambase.service.CourseService;
import com.harambase.service.FacultyService;
import com.harambase.service.StudentService;
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
