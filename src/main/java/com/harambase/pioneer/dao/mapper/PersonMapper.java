package com.harambase.pioneer.dao.mapper;

import com.harambase.pioneer.pojo.base.Person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
@Deprecated
public interface PersonMapper {
    int deleteByPrimaryKey(String userid);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);

    Person selectByPerson(Person person);

    List<Person> selectByUsername(String username);

    long getCountByMapPageSearchOrdered(Map param);

    List<Person> getByMapPageSearchOrdered(Map param);

    List<Person> getUsersBySearch(Map param);

    int countStudent(Map param);

    int countFaculty(Map param);

    int countAdmin();

    int countMale();

    int countFemale();

    List<Person> getAllUsers();
    
    List<Person> getAllUsersWithInfo(String info);

    Person selectByUserId(String studentid);
}