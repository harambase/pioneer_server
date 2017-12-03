package com.harambase.pioneer.controller;

import com.harambase.common.HaramMessage;
import com.harambase.common.Tags;
import com.harambase.common.constant.FlagDict;
import com.harambase.common.util.SessionUtil;
import com.harambase.pioneer.pojo.Pin;
import com.harambase.pioneer.service.PinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    @RequiresPermissions({"admin", "teach"})
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
    @RequiresPermissions({"admin", "teach"})
    @RequestMapping(value = "/{pin}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(value = "pin") String pin) {
        HaramMessage haramMessage = pinService.delete(pin);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "删除所有识别码", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequiresPermissions({"admin", "teach"})
    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity deleteAll(@RequestParam(value = "info") String info) {
        HaramMessage haramMessage = pinService.clearAll(info);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "验证PIN信息", notes = "权限：用户", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequiresPermissions("user")
    @RequestMapping(value = "/{pin}", method = RequestMethod.GET)
    public ResponseEntity validate(@PathVariable(value = "pin") Integer pin, HttpSession session) {
        HaramMessage haramMessage = pinService.validate(pin);
        if(haramMessage.getCode() == FlagDict.SUCCESS.getV())
            session.setAttribute("pin", haramMessage.getData());
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "当前用户的PIN信息", notes = "权限：用户", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequiresPermissions("user")
    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public ResponseEntity sessionValidate(HttpSession session) {
        Pin pin = (Pin)session.getAttribute("pin");
        HaramMessage haramMessage = pinService.validate(pin.getPin());
        if(haramMessage.getCode() == FlagDict.SUCCESS.getV())
            session.setAttribute("pin", haramMessage.getData());
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "向教师发送识别码", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequiresPermissions({"admin", "teach"})
    @RequestMapping(value = "/send/faculty/{info}", method = RequestMethod.GET)
    public ResponseEntity sendFacultyPin(@PathVariable(value = "info") String info){
        HaramMessage haramMessage = pinService.sendFacultyPin(info, SessionUtil.getUserId());
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "向导师发送识别码", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.PIN})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequiresPermissions({"admin", "teach"})
    @RequestMapping(value = "/send/advisor/{info}", method = RequestMethod.GET)
    public ResponseEntity sendAdvisorPin(@PathVariable(value = "info") String info){
        HaramMessage haramMessage = pinService.sendAdvisorPin(info, SessionUtil.getUserId());
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    //todo:update,get
}
