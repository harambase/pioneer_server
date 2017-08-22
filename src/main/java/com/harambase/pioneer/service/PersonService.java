package com.harambase.pioneer.service;

import com.harambase.common.HaramMessage;
import com.harambase.pioneer.pojo.Person;

public interface PersonService {
    HaramMessage login(Person person);

    HaramMessage addUser(Person person);

    HaramMessage userList(String s, String s1, String search, String order,
                          String orderCol, String type, String status);

    HaramMessage getUser(String userid);

    HaramMessage update(Person person);

    HaramMessage listFaculties(String search);

    HaramMessage listStudents(String serach);

    HaramMessage countPerson();
}
