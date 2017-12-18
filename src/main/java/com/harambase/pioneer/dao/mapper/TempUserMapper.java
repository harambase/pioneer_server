package com.harambase.pioneer.dao.mapper;

import com.harambase.pioneer.pojo.TempUser;
import com.harambase.pioneer.pojo.Advise;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface TempUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TempUser record);

    int insertSelective(TempUser record);

    TempUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TempUser record);

    int updateByPrimaryKeyWithBLOBs(TempUser record);

    int updateByPrimaryKey(TempUser record);

    long getTempUserCountByMapPageSearchOrdered(Map<String, Object> param);

    List<Advise> getTempUserByMapPageSearchOrdered(Map<String, Object> param);
}