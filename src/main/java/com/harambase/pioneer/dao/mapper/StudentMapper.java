package com.harambase.pioneer.dao.mapper;

import com.harambase.pioneer.pojo.Student;
import com.harambase.pioneer.pojo.dto.StudentView;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface StudentMapper {
    int deleteByPrimaryKey(String userid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    StudentView transcriptDetail(String studentid);
}