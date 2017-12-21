package com.harambase.pioneer.controller;

import com.alibaba.fastjson.JSONObject;
import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.common.Tags;
import com.harambase.pioneer.pojo.base.TempUser;
import com.harambase.pioneer.service.RequestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/request")
@Api(value = "/request", description = "申请管理接口")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService){
        this.requestService = requestService;
    }

    @ApiOperation(value = "更新临时用户", notes = "创建一个新的用户", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/user", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity updateRequest(@RequestBody TempUser tempUser){
        HaramMessage message = requestService.updateTempUser(tempUser);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ApiOperation(value = "删除一个用户", notes = "删除一个用户", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody JSONObject jsonObject){
        HaramMessage haramMessage = requestService.register(jsonObject);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "临时用户列表", notes = "only登录用户", response = Map.class, tags = {Tags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/user/list", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity userList(@RequestParam(value = "start") Integer start,
                                   @RequestParam(value = "length") Integer length,
                                   @RequestParam(value = "draw") Integer draw,
                                   @RequestParam(value = "search[value]") String search,
                                   @RequestParam(value = "order[0][dir]") String order,
                                   @RequestParam(value = "order[0][column]") String orderCol,
                                   @RequestParam(value = "viewStatus") String viewStatus){
        Map<String, Object> map = new HashMap<>();
        try {
            HaramMessage message = requestService.tempUserList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol, viewStatus);
            map.put("draw", draw);
            map.put("recordsTotal", ((Page) message.get("page")).getTotalRows());
            map.put("recordsFiltered", ((Page) message.get("page")).getTotalRows());
            map.put("data", message.getData());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("draw", 1);
            map.put("data", new ArrayList<>());
            map.put("recordsTotal", 0);
            map.put("recordsFiltered", 0);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

//    @RequestMapping(value = "/teach/list", produces = "application/json", method = RequestMethod.GET)
//    public ResponseEntity courseList(@RequestParam(value = "start") Integer start,
//                                     @RequestParam(value = "length") Integer length,
//                                     @RequestParam(value = "draw") Integer draw,
//                                     @RequestParam(value = "search[value]") String search,
//                                     @RequestParam(value = "order[0][dir]") String order,
//                                     @RequestParam(value = "order[0][column]") String orderCol,
//                                     HttpSession session){
//        Map<String, Object> map = new HashMap<>();
//        try {
//            HaramMessage message = courseService.tempCourseList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol);
//            map.put("draw", draw);
//            map.put("recordsTotal", ((Page) message.get("page")).getTotalRows());
//            map.put("recordsFiltered", ((Page) message.get("page")).getTotalRows());
//            map.put("data", message.getData());
//        } catch (Exception e) {
//            e.printStackTrace();
//            map.put("draw", 1);
//            map.put("data", new ArrayList<>());
//            map.put("recordsTotal", 0);
//            map.put("recordsFiltered", 0);
//        }
//        return new ResponseEntity<>(map, HttpStatus.OK);
//    }

}
