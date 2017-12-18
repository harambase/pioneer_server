package com.harambase.pioneer.controller;

import com.harambase.common.HaramMessage;
import com.harambase.common.Tags;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.service.CourseService;
import com.harambase.pioneer.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/system")
@Api(value = "/system", description = "系统管理接口")
public class SystemController {

    private final CourseService courseService;
    private final PersonService personService;
    
    @Autowired
    public SystemController(CourseService courseService,
                            PersonService personService){
        this.courseService = courseService;
        this.personService = personService;
    }

    @ApiOperation(value = "登录", notes = "权限：所有人", response = Map.class, tags = {Tags.SYSTEM})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody Person person){
        HaramMessage message = personService.login(person);
        if(message.getCode() == 2001) {
            person = (Person)message.getData();
            UsernamePasswordToken token = new UsernamePasswordToken(person.getUserid(),person.getPassword().toCharArray()) ;
            Subject subject = SecurityUtils.getSubject();
            subject.login(token); //完成登录
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ApiOperation(value = "登出", notes = "权限：所有人", response = Map.class, tags = {Tags.SYSTEM})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity logout(){
        HaramMessage message = new HaramMessage();
        SecurityUtils.getSubject().logout();
        message.setCode(FlagDict.SUCCESS.getV());
        message.setMsg(FlagDict.SUCCESS.getM());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ApiOperation(value = "系统信息", notes = "权限：用户", response = Map.class, tags = {Tags.SYSTEM})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/info", method = RequestMethod.GET)
//    @RequiresPermissions("user")
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
//    @RequiresPermissions("user")
    public ResponseEntity relationChart(){
        HaramMessage haramMessage = personService.getRelationChart();
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "系统用户计数", notes = "权限：用户", response = Map.class, tags = {Tags.SYSTEM})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/user/count", method = RequestMethod.GET)
//    @RequiresPermissions("user")
    public ResponseEntity userCount(){
        HaramMessage haramMessage = personService.userChart();
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

}
