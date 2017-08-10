package com.harambase.pioneer.dao;

import com.harambase.pioneer.pojo.Person;

import java.util.List;
import java.util.Map;

public interface PersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);

    Person selectByPerson(Person person);

    long getCountByMapPageSearchOrdered(Map param);

    List<Person> getByMapPageSearchOrdered(Map param);

    List<Person> getUsersBySearch(Map param);
}