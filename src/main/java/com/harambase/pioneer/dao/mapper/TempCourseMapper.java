package com.harambase.pioneer.dao.mapper;

import com.harambase.pioneer.pojo.TempCourse;

public interface TempCourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TempCourse record);

    int insertSelective(TempCourse record);

    TempCourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TempCourse record);

    int updateByPrimaryKey(TempCourse record);
}