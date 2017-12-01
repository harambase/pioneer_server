package com.harambase.pioneer.controller;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.common.Tags;
import com.harambase.pioneer.pojo.Advise;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.service.AdviseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/advise")
public class AdviseController {//implements AdviseApi {

    private AdviseService adviseService;
    
    @Autowired
    public  AdviseController(AdviseService adviseService){
        this.adviseService = adviseService;
    }

    @RequiresPermissions({"admin", "teach"})
    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    @ApiOperation(value = "新增导师关系", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.ADVISE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    public ResponseEntity create(@ApiParam(value = "导师关系", required = true) @RequestBody Advise advise,
                                 HttpSession session) {
        advise.setOperator(((Person)session.getAttribute("user")).getUserid());
        HaramMessage message = adviseService.assignMentor(advise);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequiresPermissions({"admin", "teach"})
    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除导师关系", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.ADVISE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    public ResponseEntity delete(@ApiParam(value = "关系ID", required = true) @PathVariable(value = "id") Integer id) {
        HaramMessage message = adviseService.removeMentor(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ApiOperation(value = "更新导师关系", notes = "权限：管理员，教务", response = Map.class, tags = {Tags.ADVISE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequiresPermissions({"admin", "teach"})
    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity update(@ApiParam(value = "关系ID", required = true) @PathVariable Integer id,
                                 @ApiParam(value = "导师关系", required = true) @RequestBody Advise advise,
                                 HttpSession session){
        advise.setOperator(((Person)session.getAttribute("user")).getUserid());
        advise.setId(id);
        HaramMessage message = adviseService.updateAdvise(advise);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ApiOperation(value = "获取导师关系信息", notes = "权限：管理员，教务，学生，老师", response = Map.class, tags = {Tags.ADVISE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequiresPermissions({"admin", "teach", "student", "faculty"})
    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@ApiParam(value = "关系ID", required = true) @RequestParam(value = "id") Integer id) {
        HaramMessage message = adviseService.getMentor(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ApiOperation(value = "导师关系列表", notes = "权限：管理员，教务，学生，老师", response = Map.class, tags = {Tags.ADVISE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequiresPermissions({"admin", "teach", "student", "faculty"})
    @RequestMapping(value = "/list", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity list(@ApiParam(value = "start") @RequestParam(value = "start") Integer start,
                               @ApiParam(value = "lengt") @RequestParam(value = "length") Integer length,
                               @ApiParam(value = "draw") @RequestParam(value = "draw") Integer draw,
                               @ApiParam(value = "搜索关键字") @RequestParam(value = "search[value]") String search,
                               @ApiParam(value = "排序方式") @RequestParam(value = "order[0][dir]") String order,
                               @ApiParam(value = "排序列") @RequestParam(value = "order[0][column]") String orderCol,
                               @ApiParam(value = "用户类型") @RequestParam(value = "studentid", required = false) String studentid,
                               @ApiParam(value = "用户状态") @RequestParam(value = "facultyid", required = false) String facultyid,
                               @ApiParam(value = "展示类型") @RequestParam(value = "mode", required = false) String mode,
                               HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            if(mode != null && mode.equals("faculty"))
                facultyid = ((Person)session.getAttribute("user")).getUserid();
            HaramMessage message = adviseService.advisingList(String.valueOf(start / length + 1), String.valueOf(length), search,
                    order, orderCol, studentid, facultyid);
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

}
