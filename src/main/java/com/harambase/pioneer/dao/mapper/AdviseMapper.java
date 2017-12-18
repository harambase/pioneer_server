package com.harambase.pioneer.dao.mapper;

import com.harambase.pioneer.pojo.base.AdviseBase;
import com.harambase.pioneer.pojo.Advise;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface AdviseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdviseBase record);

    int insertSelective(AdviseBase record);

    AdviseBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdviseBase record);

    int updateByPrimaryKey(AdviseBase record);

    long getAdvisingCountByMapPageSearchOrdered(Map<String, Object> param);

    List<Advise> getAdvisingByMapPageSearchOrdered(Map<String, Object> param);

    List<AdviseBase> getAllAdvise();

    int deleteByUserID(String userid);

    String selectFacultyByStudent(String studentid);

    AdviseBase selectByAdvise(AdviseBase advise);
}