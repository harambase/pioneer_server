package com.harambase.pioneer.controller;

import com.harambase.common.Tags;
import com.harambase.pioneer.pojo.MessageWithBLOBs;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Api(value = "/message", description = "消息中心接口")
public interface MessageApi {

    @ApiOperation(value = "新增信息", notes = "权限：用户", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity create(@ApiParam(value = "消息", required = true) MessageWithBLOBs message,
                          HttpSession session);

    @ApiOperation(value = "删除一个消息", notes = "删除一个消息", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity delete(@ApiParam(value = "消息ID", required = true) Integer id);

    @ApiOperation(value = "更新消息", notes = "更新一个消息", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity update(@ApiParam(value = "消息ID", required = true) Integer id,
                          @ApiParam(value = "消息", required = true) MessageWithBLOBs message);

    @ApiOperation(value = "获取用户信息", notes = "获取一个用户信息", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity get(@ApiParam(value = "消息ID", required = true) Integer id);

    @ApiOperation(value = "用户列表", notes = "only登录用户", response = Map.class, tags = {Tags.PERSON})
    ResponseEntity list(@ApiParam(value = "start") Integer start,
                        @ApiParam(value = "length") Integer length,
                        @ApiParam(value = "draw") Integer draw,
                        @ApiParam(value = "搜索关键字") String search,
                        @ApiParam(value = "排序方式") String order,
                        @ApiParam(value = "排序列") String orderCol,
                        @ApiParam(value = "box") String box,
                        HttpSession session);


    @ApiOperation(value = "用户列表", notes = "only登录用户", response = Map.class, tags = {Tags.PERSON})
    ResponseEntity count(@ApiParam(value = "状态") String status,
                         @ApiParam(value = "box") String box,
                         HttpSession session);

}
