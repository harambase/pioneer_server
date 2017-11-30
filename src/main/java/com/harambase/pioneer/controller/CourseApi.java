package com.harambase.pioneer.controller;

import com.harambase.common.Tags;
import com.harambase.pioneer.pojo.Course;
import com.harambase.pioneer.pojo.dto.Option;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Api(value = "/course", description = "课程系统管理接口")
public interface CourseApi {

    @ApiOperation(value = "新增课程", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity create(@ApiParam(value = "课程", required = true) Course course);

    @ApiOperation(value = "删除一个课程", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity delete(@ApiParam(value = "课程CRN", required = true) String crn);

    @ApiOperation(value = "更新课程", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity update(@ApiParam(value = "课程", required = true) Course course);

    @ApiOperation(value = "获取课程信息", notes = "权限：用户", response = Map.class, tags = {Tags.COURSE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity get(@ApiParam(value = "课程CRN", required = true) String crn);

    @ApiOperation(value = "课程列表", notes = "权限：用户", response = Map.class, tags = {Tags.COURSE})
    ResponseEntity list(@ApiParam(value = "start") Integer start,
                        @ApiParam(value = "length") Integer length,
                        @ApiParam(value = "draw") Integer draw,
                        @ApiParam(value = "搜索关键字") String search,
                        @ApiParam(value = "排序方式") String order,
                        @ApiParam(value = "排序列") String orderCol,
                        @ApiParam(value = "查看类型") String mode,
                        HttpSession session);

    @ApiOperation(value = "搜索课程", notes = "权限：用户", response = Map.class, tags = {Tags.COURSE})
    ResponseEntity search(@ApiParam(value = "search") String search,
                          @ApiParam(value = "status") String status);

    @ApiOperation(value = "预选课程列表", notes = "权限：用户", response = Map.class, tags = {Tags.COURSE})
    ResponseEntity preCourseList(@ApiParam(value = "crn") String crn);

    @ApiOperation(value = "从课程中移除学生", notes = "权限：用户", response = Map.class, tags = {Tags.COURSE})
    ResponseEntity removeStuFromCourse(@ApiParam(value = "crn") String crn,
                                       @ApiParam(value = "userId") String studentId);

    @ApiOperation(value = "向课程添加学生", notes = "权限：用户", response = Map.class, tags = {Tags.COURSE})
    ResponseEntity assignStu2Course(@ApiParam(value = "crn") String crn,
                                    @ApiParam(value = "userId") String studentId,
                                    @ApiParam(value = "option") Option option);

    @ApiOperation(value = "向课程分配老师", notes = "权限：用户", response = Map.class, tags = {Tags.COURSE})
    ResponseEntity assignFac2Course(@ApiParam(value = "crn") String crn,
                                    @ApiParam(value = "userId") String facultyId);

    @ApiOperation(value = "学生选课", notes = "权限：用户", response = Map.class, tags = {Tags.COURSE})
    ResponseEntity courseChoice(@ApiParam(value = "choiceList[]") String[] choices,
                                HttpSession session);

}
