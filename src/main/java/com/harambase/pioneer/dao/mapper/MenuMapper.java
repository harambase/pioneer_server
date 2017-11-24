package com.harambase.pioneer.dao.mapper;

import com.harambase.pioneer.pojo.Menu;
import org.springframework.stereotype.Component;

@Component
public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}