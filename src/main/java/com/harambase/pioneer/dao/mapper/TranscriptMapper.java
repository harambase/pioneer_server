package com.harambase.pioneer.dao.mapper;

import com.harambase.pioneer.pojo.Course;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.pojo.Transcript;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
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

    List<Course> studentCourse(String studentid);

    List<Transcript> getAllTranscripts();

    int deleteByCRN(String crn);

    int deleteByStudentid(String userid);
}