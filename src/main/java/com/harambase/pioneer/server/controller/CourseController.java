package com.harambase.pioneer.server.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.harambase.pioneer.common.ResultMap;
import com.harambase.pioneer.common.Tags;
import com.harambase.pioneer.server.pojo.base.Course;
import com.harambase.pioneer.server.pojo.dto.Option;
import com.harambase.pioneer.server.service.CourseService;
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
@Api(value = "/course", description = "课程中心接口")
@RequestMapping(value = "/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @ApiOperation(value = "新增课程", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Course course) {
        ResultMap resultMap = courseService.addCourse(course);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "删除一个课程", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{crn}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(value = "crn") String crn) {
        ResultMap resultMap = courseService.delete(crn);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "更新课程", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{crn}", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable String crn, @RequestBody Course course) {
        ResultMap resultMap = courseService.update(crn, course);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "获取课程信息", notes = "权限：用户", response = Map.class, tags = {Tags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{crn}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable("crn") String crn) {
        ResultMap resultMap = courseService.getCourseByCrn(crn);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "课程列表", notes = "权限：用户", response = Map.class, tags = {Tags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity list(@RequestParam(value = "start") Integer start,
                               @RequestParam(value = "length") Integer length,
                               @RequestParam(value = "search", required = false, defaultValue = "") String search,
                               @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
                               @RequestParam(value = "orderCol", required = false, defaultValue = "0") String orderCol,
                               @RequestParam(value = "facultyId", required = false) String facultyId,
                               @RequestParam(value = "info", required = false) String info) {
        ResultMap resultMap = courseService.courseList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol, facultyId, info);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "课程学生列表", notes = "权限：管理员，教务，学生", response = Map.class, tags = {Tags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/student/{crn}", method = RequestMethod.GET)
    public ResponseEntity studentList(@PathVariable String crn,
                                      @RequestParam(required = false, defaultValue = "") String search) {
        ResultMap resultMap = courseService.studentList(crn, search);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "课程INFO列表", notes = "权限：用户", response = Map.class, tags = {Tags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseEntity listInfo(@RequestParam(value = "search", required = false, defaultValue = "") String search) {
        ResultMap resultMap = courseService.courseListInfo(search);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "课程Ztree列表", notes = "权限：用户", response = Map.class, tags = {Tags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/zTree", method = RequestMethod.GET)
    public ResponseEntity zTreeList(@RequestParam(value = "facultyId", required = false) String facultyId,
                                    @RequestParam(value = "info", required = false) String info) {
        ResultMap resultMap = courseService.courseTreeList(facultyId, info);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "搜索课程", notes = "权限：用户", response = Map.class, tags = {Tags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity search(@RequestParam(required = false, defaultValue = "") String search,
                                 @RequestParam(required = false, defaultValue = "") String status) {
        ResultMap resultMap = courseService.getCourseBySearch(search, status);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "预选课程列表", notes = "权限：用户", response = Map.class, tags = {Tags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{crn}/precourse", method = RequestMethod.GET)
    public ResponseEntity preCourseList(@PathVariable("crn") String crn) {
        ResultMap resultMap = courseService.preCourseList(crn);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "从课程中移除学生", notes = "权限：用户", response = Map.class, tags = {Tags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{crn}/student/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity removeStuFromCourse(@PathVariable(value = "crn") String crn,
                                              @PathVariable(value = "userId") String studentId) {
        ResultMap resultMap = courseService.removeStuFromCou(crn, studentId);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "向课程添加学生", notes = "权限：用户", response = Map.class, tags = {Tags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{crn}/student/{studentId}", method = RequestMethod.PUT)
    public ResponseEntity assignStu2Course(@PathVariable(value = "crn") String crn,
                                           @PathVariable(value = "studentId") String studentId,
                                           @RequestBody Option option) {
        ResultMap resultMap = courseService.addStu2Cou(crn, studentId, option);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "向课程分配老师", notes = "权限：用户", response = Map.class, tags = {Tags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{crn}/faculty/{facultyId}", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity assignFac2Course(@PathVariable(value = "crn") String crn,
                                           @PathVariable(value = "facultyId") String facultyId) {
        ResultMap resultMap = courseService.assignFac2Cou(crn, facultyId);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "学生选课", notes = "权限：用户", response = Map.class, tags = {Tags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{studentId}/choose", method = RequestMethod.POST)
    public ResponseEntity courseChoice(@PathVariable(value = "studentId") String studentId,
                                       @RequestBody JSONArray choiceList) {
        ResultMap resultMap = courseService.reg2Course(studentId, choiceList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

}
