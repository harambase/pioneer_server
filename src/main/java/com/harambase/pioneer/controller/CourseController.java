package com.harambase.pioneer.controller;

import com.harambase.common.HaramMessage;
import com.harambase.common.constant.ApiTags;
import com.harambase.pioneer.pojo.base.Course;
import com.harambase.pioneer.pojo.dto.Option;
import com.harambase.pioneer.service.CourseService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@Api(value = "/course", description = "课程中心接口")
@RequestMapping(value = "/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @ApiOperation(value = "新增课程", notes = "权限：管理员，教务", response = Map.class, tags = {ApiTags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity create(@ApiParam(value = "课程", required = true) @RequestBody Course course) {
        HaramMessage haramMessage = courseService.create(course);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "删除一个课程", notes = "权限：管理员，教务", response = Map.class, tags = {ApiTags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{crn}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@ApiParam(value = "课程CRN", required = true) @PathVariable(value = "crn") String crn) {
        HaramMessage haramMessage = courseService.delete(crn);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "更新课程", notes = "权限：管理员，教务", response = Map.class, tags = {ApiTags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Course course) {
        HaramMessage haramMessage = courseService.update(course);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "获取课程信息", notes = "权限：用户", response = Map.class, tags = {ApiTags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{crn}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable("crn") String crn) {
        HaramMessage haramMessage = courseService.getCourseByCrn(crn);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "课程列表", notes = "权限：用户", response = Map.class, tags = {ApiTags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity list(@RequestParam(value = "start") Integer start,
                               @RequestParam(value = "length") Integer length,
                               @RequestParam(value = "search", required = false, defaultValue = "") String search,
                               @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
                               @RequestParam(value = "orderCol", required = false, defaultValue = "0") String orderCol,
                               @RequestParam(value = "facultyId", required = false) String facultyId,
                               @RequestParam(value = "info", required = false) String info) {
        HaramMessage message = courseService.courseList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol, facultyId, info);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ApiOperation(value = "课程Ztree列表", notes = "权限：用户", response = Map.class, tags = {ApiTags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/zTree", method = RequestMethod.GET)
    public ResponseEntity zTreeList(@RequestParam(value = "facultyId", required = false) String facultyid,
                                    @RequestParam(value = "info", required = false) String info) {
        HaramMessage message = courseService.courseTreeList(facultyid, info);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @ApiOperation(value = "搜索课程", notes = "权限：用户", response = Map.class, tags = {ApiTags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity search(@RequestParam("search") String search,
                                 @RequestParam("status") String status) {
        HaramMessage haramMessage = courseService.getCourseBySearch(search, status);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "预选课程列表", notes = "权限：用户", response = Map.class, tags = {ApiTags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{crn}/precourse", method = RequestMethod.GET)
    public ResponseEntity preCourseList(@PathVariable("crn") String crn) {
        HaramMessage haramMessage = courseService.preCourseList(crn);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "从课程中移除学生", notes = "权限：用户", response = Map.class, tags = {ApiTags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{crn}/student/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity removeStuFromCourse(@PathVariable(value = "crn") String crn,
                                              @PathVariable(value = "userId") String studentId) {
        HaramMessage haramMessage = courseService.removeStuFromCou(crn, studentId);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "向课程添加学生", notes = "权限：用户", response = Map.class, tags = {ApiTags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{crn}/student/{userId}", method = RequestMethod.PUT)
    public ResponseEntity assignStu2Course(@PathVariable(value = "crn") String crn,
                                           @PathVariable(value = "userId") String studentId,
                                           @RequestBody Option option) {
        HaramMessage haramMessage = courseService.addStu2Cou(crn, studentId, option);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "向课程分配老师", notes = "权限：用户", response = Map.class, tags = {ApiTags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{crn}/faculty/{userId}", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity assignFac2Course(@PathVariable(value = "crn") String crn,
                                           @PathVariable(value = "userId") String facultyId) {
        HaramMessage haramMessage = courseService.assignFac2Cou(crn, facultyId);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "学生选课", notes = "权限：用户", response = Map.class, tags = {ApiTags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{studentId}/choose", method = RequestMethod.PUT)
    public ResponseEntity courseChoice(@PathVariable(value = "studentId") String studentId,
                                       @RequestParam(value = "choiceList[]") String[] choices) {
        HaramMessage message = courseService.reg2Course(studentId, choices);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
