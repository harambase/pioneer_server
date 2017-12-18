package com.harambase.pioneer.controller;

import com.harambase.common.HaramMessage;
import com.harambase.common.Tags;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.service.PinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/pin")
@Api(value = "/pin", description = "识别码管理接口")
public class PinController {

    private final PinService pinService;
    
    @Autowired
    public PinController(PinService pinService){
        this.pinService = pinService;
    }

    @ApiOperation(value = "新生成识别码", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestParam(value = "startTime") String startTime,
                                 @RequestParam(value = "endTime") String endTime,
                                 @RequestParam(value = "role") int role,
                                 @RequestParam(value = "info") String info,
                                 @RequestParam(value = "remark") String remark){
        HaramMessage haramMessage = pinService.generate(startTime, endTime, role, info, remark);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "删除一个识别码", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{pin}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(value = "pin") String pin) {
        HaramMessage haramMessage = pinService.delete(pin);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "删除所有识别码", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity deleteAll(@RequestParam(value = "info") String info) {
        HaramMessage haramMessage = pinService.clearAll(info);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "向教师发送识别码", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/send/faculty/{info}", method = RequestMethod.GET)
    public ResponseEntity sendFacultyPin(@PathVariable(value = "info") String info,
                                         @RequestParam(value = "senderId") String senderId){
        HaramMessage haramMessage = pinService.sendFacultyPin(info, senderId);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "向导师发送识别码", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/send/advisor/{info}", method = RequestMethod.GET)
    public ResponseEntity sendAdvisorPin(@PathVariable(value = "info") String info,
                                         @RequestParam(value = "senderId") String senderId){
        HaramMessage haramMessage = pinService.sendAdvisorPin(info, senderId);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

}
