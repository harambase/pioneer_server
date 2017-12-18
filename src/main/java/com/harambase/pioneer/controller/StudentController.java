package com.harambase.pioneer.controller;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.common.Tags;
import com.harambase.support.util.SessionUtil;
import com.harambase.pioneer.pojo.Student;
import com.harambase.pioneer.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by linsh on 7/12/2017.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/student")
@Api(value = "/student", description = "学生管理接口")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @ApiOperation(value = "新增用户", notes = "创建一个新的用户", response = Map.class, tags = {Tags.STUDENT})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequiresPermissions({"admin", "student"})
    @RequestMapping(value = "/{studentId}/transcript", method = RequestMethod.GET)
    public ResponseEntity getTranscriptDetail(@PathVariable(value = "studentId") String studentid) {
        HaramMessage haramMessage = studentService.transcriptDetail(studentid);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "删除一个用户", notes = "删除一个用户", response = Map.class, tags = {Tags.STUDENT})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequiresPermissions({"admin", "student", "teach"})
    @RequestMapping(value = "/{studentId}/available/credit", method = RequestMethod.GET)
    public ResponseEntity getAvailableCredit(@PathVariable(value = "studentId") String studentId){
        HaramMessage haramMessage = studentService.getAvailableCredit(studentId, SessionUtil.getPin().getInfo());
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "更新用户", notes = "更新一个用户", response = Map.class, tags = {Tags.STUDENT})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequiresPermissions({"admin", "student"})
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Student student){
        HaramMessage haramMessage = studentService.update(student);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "用户列表", notes = "only登录用户", response = Map.class, tags = {Tags.STUDENT})
    @RequiresPermissions({"admin", "teach", "system"})
    @RequestMapping(value = "/list", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity list(@RequestParam(value = "start") Integer start,
                               @RequestParam(value = "length") Integer length,
                               @RequestParam(value = "draw") Integer draw,
                               @RequestParam(value = "search[value]") String search,
                               @RequestParam(value = "order[0][dir]") String order,
                               @RequestParam(value = "order[0][column]") String orderCol,
                               @RequestParam(value = "type", required = false) String type,
                               @RequestParam(value = "status", required = false) String status) {
        Map<String, Object> map = new HashMap<>();
        try {
            HaramMessage message = studentService.studentList(String.valueOf(start / length + 1), String.valueOf(length), search,
                    order, orderCol, type, status);
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

}
