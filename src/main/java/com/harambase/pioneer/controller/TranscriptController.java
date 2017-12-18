package com.harambase.pioneer.controller;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.common.Tags;
import com.harambase.support.util.SessionUtil;
import com.harambase.pioneer.pojo.base.Transcript;
import com.harambase.pioneer.service.TranscriptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping(value = "/transcript")
@Api(value = "/transcript", description = "成绩单管理系统接口")
public class TranscriptController {
    
    private final TranscriptService transcriptService;
    
    @Autowired
    public TranscriptController(TranscriptService transcriptService){
        this.transcriptService = transcriptService;
    }

    @ApiOperation(value = "更新成绩单", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.TRANSCRIPT})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequiresPermissions({"admin", "teach"})
    @RequestMapping(produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Transcript transcript) {
        transcript.setOperator(SessionUtil.getUserId());
        HaramMessage haramMessage = transcriptService.updateGrade(transcript);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "成绩单列表", notes = "权限：管理员，教务，教师，学生", response = Map.class, tags = {Tags.TRANSCRIPT})
    @RequiresPermissions({"admin", "teach", "student", "faculty"})
    @RequestMapping(value = {"/{studentId}/course","/{crn}/student"}, produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity list(@RequestParam(value = "start") Integer start,
                               @RequestParam(value = "length") Integer length,
                               @RequestParam(value = "draw") Integer draw,
                               @RequestParam(value = "search[value]") String search,
                               @RequestParam(value = "order[0][dir]") String order,
                               @RequestParam(value = "order[0][column]") String orderCol,
                               @PathVariable(value = "studentId") String studentId,
                               @PathVariable(value = "crn") String crn) {
        Map<String, Object> map = new HashMap<>();
        try {
            HaramMessage message = transcriptService.transcriptList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol, studentId, crn);
            map.put("draw", draw);
            map.put("recordsTotal", ((Page) message.get("page")).getTotalRows());
            map.put("recordsFiltered", ((Page) message.get("page")).getTotalRows());
            map.put("data", message.getData());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("draw", 1);
            map.put("recordsTotal", 0);
            map.put("recordsFiltered", 0);
            map.put("data", new ArrayList<>());
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
//    @RequiresPermissions({"admin", "teach", "advisor", "student"})
//    @RequestMapping(value = "/{studentId}/report", method = RequestMethod.GET)
//    public void getStudentReport(@PathVariable(value = "studentId") String studentId, HttpServletResponse httpServletResponse){
//
//    }
}
