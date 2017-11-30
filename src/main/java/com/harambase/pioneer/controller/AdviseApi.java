package com.harambase.pioneer.controller;

import com.harambase.common.Tags;
import com.harambase.pioneer.pojo.Advise;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Api(value = "/advise", description = "导师系统接口")
public interface AdviseApi {

    @ApiOperation(value = "新增导师关系", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.ADVISE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity create(@ApiParam(value = "导师关系", required = true) Advise advise, HttpSession session);

    @ApiOperation(value = "删除导师关系", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.ADVISE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity delete(@ApiParam(value = "关系ID", required = true) Integer id);

    @ApiOperation(value = "更新导师关系", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.ADVISE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity update(@ApiParam(value = "关系ID", required = true) Integer id,
                          @ApiParam(value = "导师关系", required = true) Advise advise, 
                          HttpSession session);

    @ApiOperation(value = "获取导师关系信息", notes = "权限：管理员，教务，学生，老师", response = Map.class, tags = {Tags.ADVISE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity get(@ApiParam(value = "关系ID", required = true) Integer id);

    @ApiOperation(value = "导师关系列表", notes = "权限：管理员，教务，学生，老师", response = Map.class, tags = {Tags.ADVISE})
    ResponseEntity list(@ApiParam(value = "start") Integer start,
                        @ApiParam(value = "length") Integer length,
                        @ApiParam(value = "draw") Integer draw,
                        @ApiParam(value = "搜索关键字") String search,
                        @ApiParam(value = "排序方式") String order,
                        @ApiParam(value = "排序列") String orderCol,
                        @ApiParam(value = "用户类型") String studentid,
                        @ApiParam(value = "用户状态") String facultyid,
                        @ApiParam(value = "展示类型") String mode,
                        HttpSession session);


}
