package com.harambase.pioneer.server.controller;

import com.harambase.pioneer.common.ResultMap;
import com.harambase.pioneer.common.Tags;
import com.harambase.pioneer.server.service.RoleService;
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
@RequestMapping("/role")
@Api(value = "/role", description = "权限管理接口")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation(value = "通过ROLE_ID获取权限", notes = "权限：所有人", response = Map.class, tags = {Tags.ROLE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable(value = "roleId") Integer roleId) {
        ResultMap resultMap = roleService.get(roleId);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "获取ROLE列表", notes = "权限：所有人", response = Map.class, tags = {Tags.ROLE})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity list(@RequestParam(required = false) String search, @RequestParam String order, @RequestParam String orderCol) {
        ResultMap resultMap = roleService.list(search, order, orderCol);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
