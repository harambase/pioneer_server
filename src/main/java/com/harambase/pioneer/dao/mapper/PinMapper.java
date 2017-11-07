package com.harambase.pioneer.dao.mapper;

import com.harambase.pioneer.pojo.Pin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface PinMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Pin record);

    int insertSelective(Pin record);

    Pin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pin record);

    int updateByPrimaryKeyWithBLOBs(Pin record);

    int updateByPrimaryKey(Pin record);

    Object countByInfo(Map<String, Object> param);
    
    List<String> listByInfo(Map<String, Object> param);

    int countByPin(int pinNum);
}