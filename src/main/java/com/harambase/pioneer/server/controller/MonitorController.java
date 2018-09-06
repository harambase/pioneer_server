package com.harambase.pioneer.server.controller;

import com.harambase.pioneer.common.ResultMap;
import com.harambase.pioneer.common.Tags;
import com.harambase.pioneer.server.service.MonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/monitor")
@Api(value = "/monitor", description = "系统监控接口")
public class MonitorController {

    private final MonitorService monitorService;

    @Autowired
    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @ApiOperation(value = "系统信息", notes = "权限：用户", response = Map.class, tags = {Tags.SYSTEM})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseEntity systemInfo() {
        ResultMap resultMap = monitorService.systemCount();
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "关系图表", notes = "权限：用户", response = Map.class, tags = {Tags.SYSTEM})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/relation", method = RequestMethod.GET)
    public ResponseEntity relationChart() {
        ResultMap resultMap = monitorService.relationChart();
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "系统用户计数", notes = "权限：用户", response = Map.class, tags = {Tags.SYSTEM})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/user/chart", method = RequestMethod.GET)
    public ResponseEntity userChart() {
        ResultMap resultMap = monitorService.userChart();
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }


}
