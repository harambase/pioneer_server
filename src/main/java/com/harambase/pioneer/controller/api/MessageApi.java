package com.harambase.pioneer.controller.api;

import com.harambase.common.Tags;
import com.harambase.pioneer.pojo.MessageWithBLOBs;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Api(value = "/message", description = "消息中心接口")
public interface MessageApi {

    @RequiresPermissions("user")
    @ApiOperation(value = "新增信息", notes = "权限：用户", response = Map.class, tags = {Tags.MESSAGE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity create(@ApiParam(value = "消息", required = true) MessageWithBLOBs message,
                          HttpSession session);

    @RequiresPermissions("user")
    @ApiOperation(value = "删除一个消息", notes = "权限：用户", response = Map.class, tags = {Tags.MESSAGE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity delete(@ApiParam(value = "消息ID", required = true) Integer id);

    @RequiresPermissions("user")
    @ApiOperation(value = "更新消息", notes = "权限：用户", response = Map.class, tags = {Tags.MESSAGE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity update(@ApiParam(value = "消息ID", required = true) Integer id,
                          @ApiParam(value = "消息", required = true) MessageWithBLOBs message);

    @RequiresPermissions("user")
    @ApiOperation(value = "获取一条消息", notes = "权限：用户", response = Map.class, tags = {Tags.MESSAGE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity get(@ApiParam(value = "消息ID", required = true) Integer id);

    @RequiresPermissions("user")
    @ApiOperation(value = "消息列表", notes = "权限：用户", response = Map.class, tags = {Tags.MESSAGE})
    ResponseEntity list(@ApiParam(value = "start") Integer start,
                        @ApiParam(value = "length") Integer length,
                        @ApiParam(value = "draw") Integer draw,
                        @ApiParam(value = "搜索关键字") String search,
                        @ApiParam(value = "排序方式") String order,
                        @ApiParam(value = "排序列") String orderCol,
                        @ApiParam(value = "box") String box,
                        HttpSession session);

    @RequiresPermissions("user")
    @ApiOperation(value = "计数", notes = "权限：用户", response = Map.class, tags = {Tags.MESSAGE})
    ResponseEntity count(@ApiParam(value = "状态") String status,
                         @ApiParam(value = "box") String box,
                         HttpSession session);

}
