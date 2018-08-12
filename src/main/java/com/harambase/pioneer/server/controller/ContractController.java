package com.harambase.pioneer.server.controller;

import com.harambase.pioneer.common.ResultMap;
import com.harambase.pioneer.common.Tags;
import com.harambase.pioneer.server.pojo.base.Contract;
import com.harambase.pioneer.server.service.ContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by linsh on 7/12/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/contract")
@Api(value = "/contract", description = "合同后勤，行政接口")
public class ContractController {

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @ApiOperation(value = "新增合同", notes = "权限：管理员，后勤，行政", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Contract contract) {
        ResultMap resultMap = contractService.addContract(contract);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "删除一个合同", notes = "权限：管理员，后勤，行政", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        ResultMap resultMap = contractService.removeContract(id);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "更新合同", notes = "权限：管理员，后勤，行政", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable Integer contractId, @RequestBody Contract contract) {
        ResultMap resultMap = contractService.update(contractId, contract);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "获取合同信息", notes = "权限：合同", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable(value = "id") Integer contractId) {
        ResultMap resultMap = contractService.getContract(contractId);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "搜索合同", notes = "权限：合同", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity search(@RequestParam(value = "search", required = false) String search,
                                 @RequestParam(value = "type", required = false) String type,
                                 @RequestParam(value = "maxLength", required = false) String maxLength,
                                 @RequestParam(value = "status", required = false) String status) {
        ResultMap resultMap = contractService.listContracts(search, type, status, maxLength);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "合同列表", notes = "权限：合同", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity list(@RequestParam(value = "start") Integer start,
                               @RequestParam(value = "length") Integer length,
                               @RequestParam(value = "search", required = false, defaultValue = "") String search,
                               @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
                               @RequestParam(value = "orderCol", required = false, defaultValue = "0") String orderCol,
                               @RequestParam(value = "type", required = false) String type,
                               @RequestParam(value = "status", required = false) String status) {
        ResultMap resultMap = contractService.contractList(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderCol, type, status);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "合同数量统计", notes = "权限：合同", response = Map.class, tags = {Tags.PERSON})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作成功", response = Map.class)})
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity count(@RequestParam(value = "type", required = false, defaultValue = "") String type) {
        ResultMap resultMap = contractService.count(type);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
