package com.harambase.pioneer.server.controller;

import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.common.constant.ApiTags;
import com.harambase.pioneer.server.pojo.base.Advise;
import com.harambase.pioneer.server.service.AdviseService;
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
@RequestMapping(value = "/advise")
public class AdviseController {

    private AdviseService adviseService;

    @Autowired
    public AdviseController(AdviseService adviseService) {
        this.adviseService = adviseService;
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    @ApiOperation(value = "新增导师关系", notes = "权限：管理员，教务", response = Map.class, tags = {ApiTags.ADVISE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    public ResponseEntity create(@RequestBody Advise advise) {
        HaramMessage message = adviseService.assignMentor(advise);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除导师关系", notes = "权限：管理员，教务", response = Map.class, tags = {ApiTags.ADVISE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    public ResponseEntity delete(@PathVariable(value = "id") Integer id) {
        HaramMessage message = adviseService.removeMentor(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ApiOperation(value = "更新导师关系", notes = "权限：管理员，教务", response = Map.class, tags = {ApiTags.ADVISE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable Integer id,
                                 @RequestParam(value = "studentId") String studentId,
                                 @RequestParam(value = "facultyId") String facultyId) {
        HaramMessage message = adviseService.updateAdvise(id, studentId, facultyId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ApiOperation(value = "获取导师关系信息", notes = "权限：管理员，教务，学生，老师", response = Map.class, tags = {ApiTags.ADVISE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable(value = "id") Integer id) {
        HaramMessage message = adviseService.getMentor(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ApiOperation(value = "导师关系列表", notes = "权限：管理员，教务，学生，老师", response = Map.class, tags = {ApiTags.ADVISE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity list(@RequestParam(value = "start") Integer start,
                               @RequestParam(value = "length") Integer length,
                               @RequestParam(value = "search", required = false, defaultValue = "") String search,
                               @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
                               @RequestParam(value = "orderCol", required = false, defaultValue = "0") String orderCol,
                               @RequestParam(value = "studentId", required = false) String studentId,
                               @RequestParam(value = "facultyId", required = false) String facultyId) {
        HaramMessage message = adviseService.advisingList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol, studentId, facultyId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
