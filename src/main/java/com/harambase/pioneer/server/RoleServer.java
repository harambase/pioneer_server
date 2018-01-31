package com.harambase.pioneer.server;

import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.common.constant.ApiTags;
import com.harambase.pioneer.server.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Component
public class RoleServer {

    private final RoleService roleService;

    @Autowired
    public RoleServer(RoleService roleService) {
        this.roleService = roleService;
    }
    
    public HaramMessage get(Integer roleId) {
        return roleService.get(roleId);
    }

    public HaramMessage list(String search, String order, String orderCol) {
        return roleService.list(search, order, orderCol);
    }
}
