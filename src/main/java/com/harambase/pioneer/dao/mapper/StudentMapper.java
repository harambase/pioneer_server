package com.harambase.pioneer.dao.mapper;

import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.pojo.base.StudentBase;
import com.harambase.pioneer.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface StudentMapper {
    int deleteByPrimaryKey(String userid);

    int insert(StudentBase record);

    int insertSelective(StudentBase record);

    StudentBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentBase record);

    int updateByPrimaryKey(StudentBase record);

    Student creditsDetail(String studentid);

    long getStudentCountByMapPageSearchOrdered(Map<String, Object> param);

    List<Person> getStudentByMapPageSearchOrdered(Map<String, Object> param);
}