package com.harambase.pioneer.dao;

import com.harambase.pioneer.pojo.Advise;
import com.harambase.pioneer.pojo.dto.AdviseView;

import java.util.List;
import java.util.Map;

public interface AdviseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Advise record);

    int insertSelective(Advise record);

    Advise selectByPrimaryKey(Advise advise);

    int updateByPrimaryKeySelective(Advise record);

    int updateByPrimaryKey(Advise record);

    long getAdvisingCountByMapPageSearchOrdered(Map<String, Object> param);

    List<AdviseView> getAdvisingByMapPageSearchOrdered(Map<String, Object> param);

    List<Advise> getAllAdvise();
}