package com.harambase.pioneer.dao;

import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.pojo.Transcript;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public interface TranscriptMapper {
    int deleteByPrimaryKey(Map<String, Object> param);

    int insert(Transcript record);

    int insertSelective(Transcript record);

    Transcript selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Transcript record);

    int updateByPrimaryKey(Transcript record);

    long getTranscriptCountByMapPageSearchOrdered(Map<String, Object> param);

    List<Person> getTranscriptByMapPageSearchOrdered(Map<String, Object> param);

    int count(Transcript transcript);

    int checkTime(Map<String, String> param);

    long getStudentInCourseCountByMapPageSearchOrdered(Map<String, Object> param);

    List<Person> getStudentInCourseByMapPageSearchOrdered(Map<String, Object> param);

    int deleteByCRN(String crn);

    List<Transcript> getTranscriptBySearch(Map<String, Object> param);

    List<Transcript> getAllTranscripts();
}