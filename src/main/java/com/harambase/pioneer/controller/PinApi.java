package com.harambase.pioneer.controller;

import com.harambase.common.Tags;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Api(value = "/pin", description = "识别码管理接口")
public interface PinApi {

    @ApiOperation(value = "新生成识别码", notes = "创建一个新的用户", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity create(@ApiParam(value = "生效时间") String startTime,
                          @ApiParam(value = "失效时间") String endTime,
                          @ApiParam(value = "类型(1:S,2:F)") int role,
                          @ApiParam(value = "年份-学期") String info,
                          @ApiParam(value = "备注") String remark);

    @ApiOperation(value = "删除一个识别码", notes = "删除一个用户", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity delete(@ApiParam(value = "识别码", required = true) String pin);

    @ApiOperation(value = "删除一个识别码", notes = "删除一个用户", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity deleteAll(@ApiParam(value = "年份-学期") String info);

    @ApiOperation(value = "验证PIN信息", notes = "权限：用户", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity validate(@ApiParam(value = "识别码", required = true) Integer pin,
                            HttpSession httpSession);

    @ApiOperation(value = "当前用户的PIN信息", notes = "权限：用户", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity sessionValidate(HttpSession session);

    @ApiOperation(value = "向教师发送识别码", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity sendFacultyPin(@ApiParam(value = "年份-学期") String info, HttpSession session);

    @ApiOperation(value = "向导师发送识别码", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity sendAdvisorPin(@ApiParam(value = "年份-学期") String info, HttpSession session);

}
