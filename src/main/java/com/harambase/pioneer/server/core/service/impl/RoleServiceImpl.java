package com.harambase.pioneer.server.core.service.impl;

import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.common.Page;
import com.harambase.pioneer.common.constant.FlagDict;
import com.harambase.pioneer.server.core.dao.base.RoleDao;
import com.harambase.pioneer.server.core.dao.repository.RoleRepository;
import com.harambase.pioneer.server.core.pojo.base.Role;
import com.harambase.pioneer.server.core.service.RoleService;
import com.harambase.pioneer.common.support.util.PageUtil;
import com.harambase.pioneer.common.support.util.ReturnMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleServiceImpl implements RoleService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RoleRepository roleRepository;

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleDao roleDao) {
        this.roleDao = roleDao;
        this.roleRepository = roleRepository;
    }

    @Override
    public HaramMessage get(Integer roleId) {
        try {
            Role role = roleRepository.findByRoleId(roleId);
            return role != null ? ReturnMsgUtil.success(role) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage list(String search, String order, String orderColumn) {
        HaramMessage message = new HaramMessage();
        try {
            switch (Integer.parseInt(orderColumn)) {
                case 0:
                    orderColumn = "id";
                    break;
                case 1:
                    orderColumn = "role_id";
                    break;
                case 2:
                    orderColumn = "role_name";
                    break;
                case 3:
                    orderColumn = "role_code";
                    break;
                default:
                    orderColumn = "id";
                    break;
            }


            List<Role> roleList = roleDao.getByMapPageSearchOrdered(search, order, orderColumn);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg("1"));
            page.setPageSize(PageUtil.getLimit("10"));
            page.setTotalRows(roleList.size());

            message.setData(roleList);
            message.put("page", page);
            message.setMsg(FlagDict.SUCCESS.getM());
            message.setCode(FlagDict.SUCCESS.getV());
            return message;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }
}
