package com.harambase.pioneer.controller;

import com.alibaba.fastjson.JSONObject;
import com.harambase.common.HaramMessage;
import com.harambase.common.Tags;
import com.harambase.pioneer.pojo.base.TempUser;
import com.harambase.pioneer.service.TempUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/request")
@Api(value = "/request", description = "申请管理接口")
public class RequestController {

    private final TempUserService tempUserService;

    @Autowired
    public RequestController(TempUserService tempUserService) {
        this.tempUserService = tempUserService;
    }

    @ApiOperation(value = "更新临时用户", notes = "权限：行政", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/user", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity updateRequest(@RequestBody TempUser tempUser) {
        HaramMessage message = tempUserService.updateTempUser(tempUser);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ApiOperation(value = "注册用户申请", notes = "权限：所有人", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody JSONObject jsonObject) {
        HaramMessage haramMessage = tempUserService.register(jsonObject);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "删除一个用户申请", notes = "权限：管理员，行政", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeUserRequest(@PathVariable Integer id) {
        HaramMessage haramMessage = tempUserService.deleteTempUserById(id);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "临时用户列表", notes = "权限：管理员，行政", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/user", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity userList(@RequestParam(value = "start") Integer start,
                                   @RequestParam(value = "length") Integer length,
                                   @RequestParam(value = "search"   , required = false, defaultValue = "") String search,
                                   @RequestParam(value = "order"    , required = false, defaultValue = "desc") String order,
                                   @RequestParam(value = "orderCol" , required = false, defaultValue = "0") String orderCol,
                                   @RequestParam(value = "status", required = false) String status) {
        HaramMessage message = tempUserService.tempUserList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol, status);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
