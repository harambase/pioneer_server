package com.harambase.pioneer.controller;

import com.harambase.common.HaramMessage;
import com.harambase.common.Tags;
import com.harambase.pioneer.pojo.base.Transcript;
import com.harambase.pioneer.service.TranscriptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping(value = "/transcript")
@Api(value = "/transcript", description = "成绩单管理系统接口")
public class TranscriptController {

    private final TranscriptService transcriptService;

    @Autowired
    public TranscriptController(TranscriptService transcriptService) {
        this.transcriptService = transcriptService;
    }

    @ApiOperation(value = "更新成绩单", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.TRANSCRIPT})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Transcript transcript) {
        HaramMessage haramMessage = transcriptService.updateGrade(transcript);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "成绩单列表", notes = "权限：管理员，教务，教师，学生", response = Map.class, tags = {Tags.TRANSCRIPT})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = {"/{studentId}/course", "/{crn}/student"}, produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity list(@RequestParam(value = "start") Integer start,
                               @RequestParam(value = "length") Integer length,
                               @RequestParam(value = "search", required = false) String search,
                               @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
                               @RequestParam(value = "orderCol", required = false, defaultValue = "0") String orderCol,
                               @PathVariable(value = "studentId", required = false) String studentId,
                               @PathVariable(value = "crn", required = false) String crn) {

        HaramMessage message = transcriptService.transcriptList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol, studentId, crn);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
