package com.harambase.pioneer.controller;

import com.harambase.common.Tags;
import com.harambase.pioneer.pojo.Person;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Api(value = "/system", description = "系统管理接口")
public interface SystemApi {

    @ApiOperation(value = "登录", notes = "权限：所有人", response = Map.class, tags = {Tags.SYSTEM})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity login(@ApiParam(value = "用户", required = true) Person person, HttpSession session);

    @ApiOperation(value = "登出", notes = "权限：所有人", response = Map.class, tags = {Tags.SYSTEM})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity logout(HttpSession session);

    @ApiOperation(value = "系统信息", notes = "权限：用户", response = Map.class, tags = {Tags.SYSTEM})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity systemInfo();

    @ApiOperation(value = "关系图表", notes = "权限：用户", response = Map.class, tags = {Tags.SYSTEM})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity relationChart();

    @ApiOperation(value = "系统用户计数", notes = "权限：用户", response = Map.class, tags = {Tags.SYSTEM})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity userCount();

}
