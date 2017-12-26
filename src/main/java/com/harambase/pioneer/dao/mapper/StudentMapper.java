package com.harambase.pioneer.dao.mapper;

import com.harambase.pioneer.pojo.view.StudentView;
import com.harambase.pioneer.pojo.base.Person;
import com.harambase.pioneer.pojo.base.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
@Deprecated
public interface StudentMapper {
    int deleteByPrimaryKey(String userid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    StudentView creditsDetail(String studentid);

    long getStudentCountByMapPageSearchOrdered(Map<String, Object> param);

    List<Person> getStudentByMapPageSearchOrdered(Map<String, Object> param);
}