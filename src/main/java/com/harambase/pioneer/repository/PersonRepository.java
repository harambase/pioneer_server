package com.harambase.pioneer.repository;

import com.harambase.pioneer.pojo.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

    Person findByUserid(String userid);
}
