package com.harambase.pioneer.dao.mapper;

import com.harambase.pioneer.pojo.TempUser;

public interface TempUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TempUser record);

    int insertSelective(TempUser record);

    TempUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TempUser record);

    int updateByPrimaryKeyWithBLOBs(TempUser record);

    int updateByPrimaryKey(TempUser record);
}