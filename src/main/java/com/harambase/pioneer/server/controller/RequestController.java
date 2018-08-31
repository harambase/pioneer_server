package com.harambase.pioneer.server.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.harambase.pioneer.common.ResultMap;
import com.harambase.pioneer.common.Tags;
import com.harambase.pioneer.common.constant.SystemConst;
import com.harambase.pioneer.server.pojo.base.*;
import com.harambase.pioneer.server.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/request")
@Api(value = "/request", description = "申请管理接口")
public class RequestController {

    private final TempUserService tempUserService;
    private final PersonService personService;
    private final TempCourseService tempCourseService;
    private final CourseService courseService;
    private final TempAdviseService tempAdviseService;

    @Autowired
    public RequestController(TempUserService tempUserService, PersonService personService,
                             TempCourseService tempCourseService, CourseService courseService,
                             TempAdviseService tempAdviseService) {
        this.tempUserService = tempUserService;
        this.personService = personService;
        this.tempCourseService = tempCourseService;
        this.courseService = courseService;
        this.tempAdviseService = tempAdviseService;
    }

    @ApiOperation(value = "查找一个用户申请", notes = "权限：管理员，行政", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity getUserRequest(@PathVariable Integer id) {
        ResultMap resultMap = tempUserService.get(id);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "更新临时用户", notes = "权限：行政", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/user/{id}", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity updateRequest(@PathVariable Integer id, @RequestBody TempUser tempUser) {
        if (tempUser.getStatus().equals("1")) {
            ResultMap resultMap = personService.addUser(JSONObject.parseObject(tempUser.getUserJson(), Person.class));
            if (resultMap.getCode() == SystemConst.SUCCESS.getCode()) {
                resultMap = tempUserService.updateTempUser(id, tempUser);
                return new ResponseEntity<>(resultMap, HttpStatus.OK);
            }
        }
        ResultMap message = tempUserService.updateTempUser(id, tempUser);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ApiOperation(value = "注册用户申请", notes = "权限：所有人", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody JSONObject jsonObject) {
        ResultMap resultMap = tempUserService.register(jsonObject);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "删除一个用户申请", notes = "权限：管理员，行政", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeUserRequest(@PathVariable Integer id) {
        ResultMap resultMap = tempUserService.deleteTempUserById(id);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "临时用户列表", notes = "权限：管理员，行政", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/user", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity userList(@RequestParam(value = "start") Integer start,
                                   @RequestParam(value = "length") Integer length,
                                   @RequestParam(value = "search", required = false, defaultValue = "") String search,
                                   @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
                                   @RequestParam(value = "orderCol", required = false, defaultValue = "0") String orderCol,
                                   @RequestParam(value = "status", required = false) String status) {
        ResultMap resultMap = tempUserService.tempUserList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol, status);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "查找一个课程申请", notes = "权限：教师， 教务", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
    public ResponseEntity getCourseRequest(@PathVariable Integer id) {
        ResultMap resultMap = tempCourseService.get(id);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "更新临时课程", notes = "权限：行政", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/course/{id}", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity updateCourseRequest(@PathVariable Integer id, @RequestBody TempCourse tempCourse) {
        if (tempCourse.getStatus().equals("1")) {
            ResultMap resultMap = courseService.addCourse(JSONObject.parseObject(tempCourse.getCourseJson(), Course.class));
            if (resultMap.getCode() == SystemConst.SUCCESS.getCode()) {
                resultMap = tempCourseService.updateTempCourse(id, tempCourse);
                return new ResponseEntity<>(resultMap, HttpStatus.OK);
            }
        }
        ResultMap resultMap = tempCourseService.updateTempCourse(id, tempCourse);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "新课程申请", notes = "权限：教师", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/course/register/{facultyId}", method = RequestMethod.POST)
    public ResponseEntity registerNewCourse(@PathVariable String facultyId, @RequestBody JSONObject jsonObject) {
        ResultMap resultMap = tempCourseService.register(facultyId, jsonObject);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "删除一个课程申请", notes = "权限：教师， 教务", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/course/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeCourseRequest(@PathVariable Integer id) {
        ResultMap resultMap = tempCourseService.deleteTempCourseById(id);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "临时课程列表", notes = "权限：教师， 教务", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/course", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity courseList(@RequestParam(value = "start") Integer start,
                                     @RequestParam(value = "length") Integer length,
                                     @RequestParam(value = "search", required = false, defaultValue = "") String search,
                                     @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
                                     @RequestParam(value = "orderCol", required = false, defaultValue = "0") String orderCol,
                                     @RequestParam(value = "status", required = false) String status,
                                     @RequestParam(value = "facultyId", required = false) String facultyId) {
        ResultMap resultMap = tempCourseService.tempCourseList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol, status, facultyId);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "新导师申请", notes = "权限：学生", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/advise", method = RequestMethod.POST)
    public ResponseEntity newAdvisorRequest(@RequestBody TempAdvise tempAdvise) {
        ResultMap resultMap = tempAdviseService.register(tempAdvise);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "删除一个导师申请", notes = "权限：学生, 系统", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/advise/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeAdvisorRequest(@PathVariable Integer id) {
        ResultMap resultMap = tempAdviseService.deleteTempAdviseById(id);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "查找一个导师申请", notes = "权限：教师， 教务", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/advise/{id}", method = RequestMethod.GET)
    public ResponseEntity getAdviseRequest(@PathVariable String studentId) {
        ResultMap resultMap = tempAdviseService.get(studentId);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "更新临时课程", notes = "权限：行政", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/advise/{id}", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity updateAdviseRequest(@PathVariable Integer id, @RequestBody TempAdvise tempAdvise) {
        ResultMap resultMap = tempAdviseService.updateTempAdvise(id, tempAdvise);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "临时导师列表", notes = "权限：管理员， 教务", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/advise", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity adviseList(@RequestParam(value = "start") Integer start,
                                     @RequestParam(value = "length") Integer length,
                                     @RequestParam(value = "search", required = false, defaultValue = "") String search,
                                     @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
                                     @RequestParam(value = "orderCol", required = false, defaultValue = "0") String orderCol) {
        ResultMap resultMap = tempAdviseService.tempAdviseList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

}
