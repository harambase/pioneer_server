package com.harambase.pioneer.repository;

import com.harambase.pioneer.pojo.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person,String>{
    @Query()
    List<Person> findByUserIdPageAble();
}
