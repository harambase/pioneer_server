package com.harambase.pioneer.server.core.controller;

import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.common.constant.ApiTags;
import com.harambase.pioneer.server.core.pojo.base.Message;
import com.harambase.pioneer.server.core.service.MessageService;
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
@RequestMapping(value = "/message")
@Api(value = "/message", description = "消息中心接口")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @ApiOperation(value = "新增信息", notes = "权限：用户", response = Map.class, tags = {ApiTags.MESSAGE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Message message) {
        HaramMessage haramMessage = messageService.createMessage(message);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "删除一个消息", notes = "权限：用户", response = Map.class, tags = {ApiTags.MESSAGE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(value = "id") Integer id) {
        HaramMessage haramMessage = messageService.delete(id);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "更新消息", notes = "权限：用户", response = Map.class, tags = {ApiTags.MESSAGE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable(value = "id") Integer id,
                                 @RequestBody Message message) {
        HaramMessage haramMessage = messageService.update(id, message);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "获取一条消息", notes = "权限：用户", response = Map.class, tags = {ApiTags.MESSAGE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@RequestParam(value = "id") Integer id) {
        HaramMessage haramMessage = messageService.getMessageView(id);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "计数", notes = "权限：用户", response = Map.class, tags = {ApiTags.MESSAGE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity count(@RequestParam(value = "status") String status,
                                @RequestParam(value = "box") String box,
                                @RequestParam(value = "userId") String userId) {

        String receiverId = null;
        String senderid = null;

        if (box.contains("inbox") || box.contains("important"))
            receiverId = userId;
        if (box.contains("sent") || box.contains("draft"))
            senderid = userId;
        if (box.contains("trash")) {
            receiverId = userId;
            senderid = userId;
        }

        HaramMessage haramMessage = messageService.countMessageByStatus(receiverId, senderid, box.toLowerCase(), status.toLowerCase());
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "消息列表", notes = "权限：用户", response = Map.class, tags = {ApiTags.MESSAGE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity list(@RequestParam(value = "start") Integer start,
                               @RequestParam(value = "length") Integer length,
                               @RequestParam(value = "search", required = false, defaultValue = "") String search,
                               @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
                               @RequestParam(value = "orderCol", required = false, defaultValue = "0") String orderCol,
                               @RequestParam(value = "box") String box,
                               @RequestParam(value = "userId") String userId) {
        String receiverId = null;
        String senderid = null;

        if (box.contains("inbox") || box.contains("important"))
            receiverId = userId;
        if (box.contains("sent") || box.contains("draft"))
            senderid = userId;
        if (box.contains("trash")) {
            receiverId = userId;
            senderid = userId;
        }
        HaramMessage message = messageService.list(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol, receiverId, senderid, box);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
