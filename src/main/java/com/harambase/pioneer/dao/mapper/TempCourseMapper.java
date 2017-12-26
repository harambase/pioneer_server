package com.harambase.pioneer.dao.mapper;

import com.harambase.pioneer.pojo.base.TempCourse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
@Deprecated
public interface TempCourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TempCourse record);

    int insertSelective(TempCourse record);

    TempCourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TempCourse record);

    int updateByPrimaryKey(TempCourse record);
}