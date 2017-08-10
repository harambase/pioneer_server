package com.harambase.pioneer.web;

import com.harambase.common.HaramMessage;
import com.harambase.pioneer.pojo.Course;
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
