package com.harambase.pioneer.server.controller;

import com.harambase.pioneer.common.ResultMap;
import com.harambase.pioneer.common.Tags;
import com.harambase.pioneer.server.pojo.base.Pin;
import com.harambase.pioneer.server.service.PinService;
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
    public PinController(PinService pinService) {
        this.pinService = pinService;
    }

    @ApiOperation(value = "批量生成识别码", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createAll(@RequestParam(value = "startTime") String startTime,
                                    @RequestParam(value = "endTime") String endTime,
                                    @RequestParam(value = "role") int role,
                                    @RequestParam(value = "info") String info,
                                    @RequestParam(value = "remark") String remark) {
        ResultMap resultMap = pinService.generateAll(startTime, endTime, role, info, remark);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "生成一个识别码", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public ResponseEntity createOne(@RequestParam(value = "startTime") String startTime,
                                    @RequestParam(value = "endTime") String endTime,
                                    @RequestParam(value = "role") int role,
                                    @RequestParam(value = "info") String info,
                                    @RequestParam(value = "remark") String remark,
                                    @PathVariable(value = "userId") String userId) {
        ResultMap resultMap = pinService.generateOne(startTime, endTime, role, info, remark, userId);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "识别一个识别码", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{pin}", method = RequestMethod.GET)
    public ResponseEntity validate(@PathVariable(value = "pin") Integer pin, @RequestHeader String userId) {
        ResultMap resultMap = pinService.validate(pin, userId);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "识别码按INFO列表", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity list(@RequestParam(value = "start") Integer start,
                               @RequestParam(value = "length") Integer length,
                               @RequestParam(value = "search", required = false, defaultValue = "") String search,
                               @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
                               @RequestParam(value = "orderCol", required = false, defaultValue = "0") String orderCol,
                               @RequestParam(value = "ownerId", required = false, defaultValue = "") String ownerId,
                               @RequestParam(value = "info", required = false) String info) {
        ResultMap resultMap = pinService.listByInfo(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol, info, ownerId);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "删除一个识别码", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{pin}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(value = "pin") Integer pin) {
        ResultMap resultMap = pinService.deleteSingleByPin(pin);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "删除所有识别码", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity deleteAll(@RequestParam(value = "info") String info) {
        ResultMap resultMap = pinService.deleteAllByInfo(info);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "获取所有的INFO信息", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseEntity getAllInfo() {
        ResultMap resultMap = pinService.getAllInfo();
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "更新一个PIN信息", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{pin}", method = RequestMethod.GET)
    public ResponseEntity updateOne(@PathVariable Integer pinNum, @RequestBody Pin pin) {
        ResultMap resultMap = pinService.updateOne(pinNum, pin);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
