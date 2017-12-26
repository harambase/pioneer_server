package com.harambase.pioneer.controller;

import com.harambase.common.HaramMessage;
import com.harambase.common.Tags;
import com.harambase.pioneer.pojo.base.Student;
import com.harambase.pioneer.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/student")
@Api(value = "/student", description = "学生管理接口")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ApiOperation(value = "新增用户", notes = "创建一个新的用户", response = Map.class, tags = {Tags.STUDENT})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{studentId}/transcript", method = RequestMethod.GET)
    public ResponseEntity getTranscriptDetail(@PathVariable(value = "studentId") String studentid) {
        HaramMessage haramMessage = studentService.transcriptDetail(studentid);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "删除一个用户", notes = "删除一个用户", response = Map.class, tags = {Tags.STUDENT})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{studentId}/available/credit", method = RequestMethod.GET)
    public ResponseEntity getAvailableCredit(@PathVariable(value = "studentId") String studentId,
                                             @RequestParam(value = "info") String info) {
        HaramMessage haramMessage = studentService.getAvailableCredit(studentId, info);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "更新用户", notes = "更新一个用户", response = Map.class, tags = {Tags.STUDENT})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Student student) {
        HaramMessage haramMessage = studentService.update(student);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "用户列表", notes = "only登录用户", response = Map.class, tags = {Tags.STUDENT})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity list(@RequestParam(value = "start") Integer start,
                               @RequestParam(value = "length") Integer length,
                               @RequestParam(value = "search"   , required = false, defaultValue = "") String search,
                               @RequestParam(value = "order"    , required = false, defaultValue = "desc") String order,
                               @RequestParam(value = "orderCol" , required = false, defaultValue = "0") String orderCol,
                               @RequestParam(value = "status", required = false) String status) {
        HaramMessage message = studentService.studentList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol, status);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
