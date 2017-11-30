package com.harambase.pioneer.controller;

import com.harambase.common.Tags;
import com.harambase.pioneer.pojo.Person;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Api(value = "/user", description = "用户系统管理接口")
public interface PersonApi {

    @ApiOperation(value = "新增用户", notes = "权限：管理员，系统", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity create(@ApiParam(value = "用户", required = true) Person user);

    @ApiOperation(value = "删除一个用户", notes = "权限：管理员，系统", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity delete(@ApiParam(value = "用户ID", required = true) String userId);

    @ApiOperation(value = "更新用户", notes = "权限：管理员，系统", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity update(@ApiParam(value = "用户", required = true) Person user);

    @ApiOperation(value = "获取用户信息", notes = "权限：用户", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity get(@ApiParam(value = "用户ID", required = true) String userId);

    @ApiOperation(value = "获取当前登录的用户信息", notes = "权限：管理员，系统", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity getCurrentUser(HttpSession session);

    @ApiOperation(value = "用户列表", notes = "权限：用户", response = Map.class, tags = {Tags.PERSON})
    ResponseEntity list(@ApiParam(value = "start") Integer start,
                        @ApiParam(value = "length") Integer length,
                        @ApiParam(value = "draw") Integer draw,
                        @ApiParam(value = "搜索关键字") String search,
                        @ApiParam(value = "排序方式") String order,
                        @ApiParam(value = "排序列") String orderCol,
                        @ApiParam(value = "用户类型") String type,
                        @ApiParam(value = "用户状态") String status);


    @ApiOperation(value = "搜索用户", notes = "权限：用户", response = Map.class, tags = {Tags.PERSON})
    ResponseEntity search(@ApiParam(value = "search") String search,
                          @ApiParam(value = "type") String type,
                          @ApiParam(value = "status") String status);

}