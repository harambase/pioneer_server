package com.harambase.pioneer.web;

import com.harambase.pioneer.service.CourseService;
import com.harambase.pioneer.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by linsh on 7/12/2017.
 */
@Controller
public class AdminController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;

    public String updateGrade(@RequestParam(value = "courseid") String courseid,
                              @RequestParam(value = "studentid") String studentid,
                              @RequestParam(value = "grade") String grade) {
        courseService.updateGrade(courseid, studentid, grade);
        return null;
    }

    public String assignMentor(@RequestParam(value = "studentid") String studentid,
                               @RequestParam(value = "facultyid") String facultyid){
        studentService.assignMentor(studentid, facultyid);
        return null;
    }

    public String removeMentor(@RequestParam(value = "studentid") String studentid,
                               @RequestParam(value = "facultyid") String facultyid) {
        studentService.removeMentor(studentid, facultyid);
        return null;
    }

    public String changeMentor(@RequestParam(value = "studentid") String studentid,
                               @RequestParam(value = "facultyid") String facultyid) {
        studentService.updateMentor(studentid, facultyid);
        return null;
    }
}
