package com.harambase.pioneer.dao.repository.base;

import com.harambase.pioneer.pojo.base.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

    Person findByUserid(String userid);

    List<Person> findByInfo(String info);

    int countByTypeAndStatus(String type, String status);

    int countByGenderAndStatus(String gender, String status);


}
