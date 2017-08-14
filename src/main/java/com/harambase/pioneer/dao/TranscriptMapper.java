package com.harambase.pioneer.dao;

import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.pojo.Transcript;

import java.util.List;
import java.util.Map;

public interface TranscriptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Transcript record);

    int insertSelective(Transcript record);

    Transcript selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Transcript record);

    int updateByPrimaryKey(Transcript record);

    long getTranscriptCountByMapPageSearchOrdered(Map<String, Object> param);

    List<Person> getTranscriptByMapPageSearchOrdered(Map<String, Object> param);
}