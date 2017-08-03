package com.harambase.pioneer.dao;

import com.harambase.pioneer.pojo.Faculty;

public interface FacultyMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Faculty record);

    int insertSelective(Faculty record);

    Faculty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Faculty record);

    int updateByPrimaryKey(Faculty record);
}