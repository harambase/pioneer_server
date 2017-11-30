package com.harambase.pioneer.controller.api;

import com.harambase.common.Tags;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.pojo.Student;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Api(value = "/student", description = "学生管理接口")
public interface StudentApi {

    @ApiOperation(value = "新增用户", notes = "创建一个新的用户", response = Map.class, tags = {Tags.STUDENT})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity getTranscriptDetail(@ApiParam(value = "用户", required = true) String studentId);

    @ApiOperation(value = "删除一个用户", notes = "删除一个用户", response = Map.class, tags = {Tags.STUDENT})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity getAvailableCredit(@ApiParam(value = "用户ID", required = true) String studentId,
                                      HttpSession httpSession);

    @ApiOperation(value = "更新用户", notes = "更新一个用户", response = Map.class, tags = {Tags.STUDENT})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity update(@ApiParam(value = "用户", required = true) Student student);

    @ApiOperation(value = "用户列表", notes = "only登录用户", response = Map.class, tags = {Tags.STUDENT})
    ResponseEntity list(@ApiParam(value = "start") Integer start,
                        @ApiParam(value = "length") Integer length,
                        @ApiParam(value = "draw") Integer draw,
                        @ApiParam(value = "搜索关键字") String search,
                        @ApiParam(value = "排序方式") String order,
                        @ApiParam(value = "排序列") String orderCol,
                        @ApiParam(value = "用户类型") String type,
                        @ApiParam(value = "用户状态") String status);


}
