package com.harambase.pioneer.server.controller;

import com.harambase.pioneer.common.ResultMap;
import com.harambase.pioneer.common.Tags;
import com.harambase.pioneer.server.pojo.base.Student;
import com.harambase.pioneer.server.service.StudentService;
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

    @ApiOperation(value = "获取学生成绩单", notes = "权限：管理员，教务，学生，导师", response = Map.class, tags = {Tags.STUDENT})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{studentId}/transcript", method = RequestMethod.GET)
    public ResponseEntity getTranscriptDetail(@PathVariable(value = "studentId") String studentid) {
        ResultMap resultMap = studentService.transcriptDetail(studentid);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "获取学生学分信息", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.STUDENT})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{studentId}/available/credit", method = RequestMethod.GET)
    public ResponseEntity getAvailableCredit(@PathVariable(value = "studentId") String studentId,
                                             @RequestParam(value = "info") String info) {
        ResultMap resultMap = studentService.getAvailableCredit(studentId, info);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "更新学生", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.STUDENT})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{studentId}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable String studentId, @RequestBody Student student) {
        ResultMap resultMap = studentService.update(studentId, student);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "学生列表", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.STUDENT})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity list(@RequestParam(value = "start") Integer start,
                               @RequestParam(value = "length") Integer length,
                               @RequestParam(value = "search", required = false, defaultValue = "") String search,
                               @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
                               @RequestParam(value = "orderCol", required = false, defaultValue = "0") String orderCol,
                               @RequestParam(value = "status", required = false) String status) {
        ResultMap resultMap = studentService.studentList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol, status);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "学生课程列表", notes = "权限：管理员，教务，学生", response = Map.class, tags = {Tags.STUDENT})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{studentId}/course", method = RequestMethod.GET)
    public ResponseEntity courseList(@RequestParam(value = "status", required = false, defaultValue = "") String status,
                                     @PathVariable String studentId) {
        ResultMap resultMap = studentService.courseList(status, studentId);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

}
