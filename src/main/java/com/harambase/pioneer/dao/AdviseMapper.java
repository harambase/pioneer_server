package com.harambase.pioneer.dao;

import com.harambase.pioneer.pojo.Advise;

public interface AdviseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Advise record);

    int insertSelective(Advise record);

    Advise selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Advise record);

    int updateByPrimaryKey(Advise record);
}