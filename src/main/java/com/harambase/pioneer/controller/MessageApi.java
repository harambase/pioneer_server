package com.harambase.pioneer.controller;

import com.harambase.common.Tags;
import com.harambase.pioneer.pojo.Person;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Api(value = "/user", description = "用户系统管理接口")
public interface MessageApi {

    @ApiOperation(value = "新增用户", notes = "创建一个新的用户", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity create(@ApiParam(value = "用户", required = true) Person user);

    @ApiOperation(value = "删除一个用户", notes = "删除一个用户", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity delete(@ApiParam(value = "用户ID", required = true) String userId);

    @ApiOperation(value = "更新用户", notes = "更新一个用户", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity update(@ApiParam(value = "用户", required = true) Person user);

    @ApiOperation(value = "获取用户信息", notes = "获取一个用户信息", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity get(@ApiParam(value = "用户ID", required = true) String userId);

    @ApiOperation(value = "获取当前登录的用户信息", notes = "获取一个用户信息", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity getCurrentUser(HttpSession session);

    @ApiOperation(value = "用户列表", notes = "only登录用户", response = Map.class, tags = {Tags.PERSON})
    ResponseEntity list(@ApiParam(value = "start") Integer start,
                        @ApiParam(value = "length") Integer length,
                        @ApiParam(value = "draw") Integer draw,
                        @ApiParam(value = "搜索关键字") String search,
                        @ApiParam(value = "排序方式") String order,
                        @ApiParam(value = "排序列") String orderCol,
                        @ApiParam(value = "用户类型") String type,
                        @ApiParam(value = "用户状态") String status);


    @ApiOperation(value = "用户列表", notes = "only登录用户", response = Map.class, tags = {Tags.PERSON})
    ResponseEntity searcUser(@ApiParam(value = "search") String search,
                             @ApiParam(value = "type") String type,
                             @ApiParam(value = "status") String status);

}
