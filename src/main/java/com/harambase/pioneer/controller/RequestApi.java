package com.harambase.pioneer.controller;

import com.alibaba.fastjson.JSONObject;
import com.harambase.common.Tags;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.pojo.TempUser;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Api(value = "/request", description = "申请管理接口")
public interface RequestApi {

    @ApiOperation(value = "新增用户", notes = "创建一个新的用户", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity updateRequest(@ApiParam(value = "用户", required = true) TempUser user,
                                 HttpSession session);

    @ApiOperation(value = "删除一个用户", notes = "删除一个用户", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    ResponseEntity register(@ApiParam(value = "用户ID", required = true) JSONObject jsonObject);

    @ApiOperation(value = "用户列表", notes = "only登录用户", response = Map.class, tags = {Tags.PERSON})
    ResponseEntity userList(@ApiParam(value = "start") Integer start,
                            @ApiParam(value = "length") Integer length,
                            @ApiParam(value = "draw") Integer draw,
                            @ApiParam(value = "搜索关键字") String search,
                            @ApiParam(value = "排序方式") String order,
                            @ApiParam(value = "排序列") String orderCol,
                            @ApiParam(value = "用户状态") String status);


}
