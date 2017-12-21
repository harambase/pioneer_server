package com.harambase.pioneer.controller;

import com.harambase.common.HaramMessage;
import com.harambase.common.Tags;
import com.harambase.pioneer.service.CourseService;
import com.harambase.pioneer.service.MonitorService;
import com.harambase.pioneer.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/system")
@Api(value = "/system", description = "系统管理接口")
public class MonitorController {

    private final MonitorService monitorService;
    private final PersonService personService;
    private final CourseService courseService;

    @Autowired
    public MonitorController(MonitorService monitorService, PersonService personService, CourseService courseService){
        this.monitorService = monitorService;
        this.personService = personService;
        this.courseService = courseService;
    }

    @ApiOperation(value = "系统信息", notes = "权限：用户", response = Map.class, tags = {Tags.SYSTEM})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseEntity systemInfo(){
        HaramMessage message = new HaramMessage();

        Map<String, Integer> data = new HashMap<>();
        int course, student, faculty;

        student = (Integer) personService.countActivePerson("s").getData();
        faculty = (Integer) personService.countActivePerson("f").getData();
        course  = (Integer) courseService.countActiveCourse().getData();

        data.put("student",student);
        data.put("faculty",faculty);
        data.put("course", course);

        message.setData(data);
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    @ApiOperation(value = "关系图表", notes = "权限：用户", response = Map.class, tags = {Tags.SYSTEM})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/relation", method = RequestMethod.GET)
    public ResponseEntity relationChart(){
        HaramMessage haramMessage = monitorService.getRelationChart();
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "系统用户计数", notes = "权限：用户", response = Map.class, tags = {Tags.SYSTEM})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/user/count", method = RequestMethod.GET)
    public ResponseEntity userCount(){
        HaramMessage haramMessage = monitorService.userChart();
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

}
