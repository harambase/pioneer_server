package com.harambase.pioneer.server.core.service.impl;

import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.server.core.dao.repository.RoleRepository;
import com.harambase.pioneer.server.core.pojo.base.Role;
import com.harambase.pioneer.server.core.service.RoleService;
import com.harambase.pioneer.support.util.ReturnMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImpl implements RoleService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public HaramMessage get(Integer roleId) {
        try{
            Role role = roleRepository.findByRoleId(roleId);
            return role != null ? ReturnMsgUtil.success(role) : ReturnMsgUtil.fail();
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }
}
