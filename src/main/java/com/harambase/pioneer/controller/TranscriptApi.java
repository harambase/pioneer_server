package com.harambase.pioneer.controller;

import com.harambase.common.Tags;
import com.harambase.pioneer.pojo.Transcript;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Api(value = "/transcript", description = "用户系统管理接口")
public interface TranscriptApi {

    @ApiOperation(value = "更新成绩单", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.TRANSCRIPT})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity update(@ApiParam(value = "成绩单", required = true) Transcript transcript, HttpSession session);

    @ApiOperation(value = "成绩单列表", notes = "权限：管理员，教务，教师，学生", response = Map.class, tags = {Tags.TRANSCRIPT})
    ResponseEntity list(@ApiParam(value = "start") Integer start,
                        @ApiParam(value = "length") Integer length,
                        @ApiParam(value = "draw") Integer draw,
                        @ApiParam(value = "搜索关键字") String search,
                        @ApiParam(value = "排序方式") String order,
                        @ApiParam(value = "排序列") String orderCol,
                        @ApiParam(value = "学生ID") String studentId,
                        @ApiParam(value = "课程CRN") String crn);



}
