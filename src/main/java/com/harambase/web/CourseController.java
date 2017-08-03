package com.harambase.web;

import com.harambase.pojo.Course;
import com.harambase.service.CourseService;
import com.harambase.service.FacultyService;
import com.harambase.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by linsh on 7/12/2017.
 */
@Controller
public class CourseController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private FacultyService facultyService;

    public String createdCourse(@RequestBody Course course){
        courseService.add(course);
        return null;
    }

    public String removeCourse(@RequestBody Course course){
        courseService.remove(course);
        return null;
    }

    public String getCourse(@RequestParam String courseid){
        courseService.get(courseid);
        return null;
    }

    public String updateCourse(@RequestParam Course course){
        courseService.updateCourse(course);
        return null;
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
}
