package com.harambase.Web;

import com.harambase.Pojo.Course;
import com.harambase.Pojo.Student;
import com.harambase.Service.CourseService;
import com.harambase.Service.FacultyService;
import com.harambase.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
