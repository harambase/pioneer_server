package com.harambase.pioneer.dao.mapper;

import com.harambase.pioneer.pojo.Role;
import org.springframework.stereotype.Component;

@Component
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}