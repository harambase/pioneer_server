package com.harambase.pioneer.server.core.controller;

import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.common.constant.ApiTags;
import com.harambase.pioneer.server.core.dao.DatabaseManager;
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
@RequestMapping("/database")
@Api(value = "/connection", description = "数据库管理接口")
public class DatabaseController {

    private final DatabaseManager databaseManager;

    @Autowired
    public DatabaseController(DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
    }

    @ApiOperation(value = "重启数据库", notes = "权限：管理员，行政", response = Map.class, tags = {ApiTags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity restart() {
        HaramMessage haramMessage = databaseManager.restart();
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "重置数据库（清除数据）", notes = "权限：管理员，行政", response = Map.class, tags = {ApiTags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity reset(@RequestParam String username, @RequestParam String password) {
        HaramMessage haramMessage = databaseManager.reset(username, password);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "备份数据库", notes = "权限：管理员，行政", response = Map.class, tags = {ApiTags.REQUEST})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity backup(@RequestParam String username, @RequestParam String password) {
        HaramMessage haramMessage = databaseManager.backup(username, password);
        return new ResponseEntity<>(haramMessage, HttpStatus.OK);
    }
}
