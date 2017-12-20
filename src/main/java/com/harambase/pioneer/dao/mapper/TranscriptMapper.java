package com.harambase.pioneer.dao.mapper;

import com.harambase.pioneer.pojo.base.CourseBase;
import com.harambase.pioneer.pojo.base.Person;
import com.harambase.pioneer.pojo.base.TranscriptBase;
import com.harambase.pioneer.pojo.Transcript;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface TranscriptMapper {
    int deleteByPrimaryKey(Map<String, Object> param);

    int insert(TranscriptBase record);

    int insertSelective(TranscriptBase record);

    TranscriptBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TranscriptBase record);

    int updateByPrimaryKey(TranscriptBase record);

    long getTranscriptCountByMapPageSearchOrdered(Map<String, Object> param);

    List<Person> getTranscriptByMapPageSearchOrdered(Map<String, Object> param);

    int count(TranscriptBase transcript);

    List<CourseBase> studentCourse(String studentid);

    List<TranscriptBase> getAllTranscripts();

    int deleteByCRN(String crn);

    int deleteByStudentid(String userid);

    List<Transcript> studentTranscripts(String studentid);
}