package com.harambase.pioneer.server.service;

import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.server.pojo.base.Person;

public interface PersonService {

    HaramMessage login(Person person);

    HaramMessage addUser(Person person);

    HaramMessage userList(String s, String s1, String search, String order, String orderCol, String type, String status);

    HaramMessage getUser(String userid);

    HaramMessage update(String userId, Person person);

    HaramMessage listUsers(String search, String type, String status);

    HaramMessage countActivePerson(String type);

    HaramMessage removeUser(String userid);

}
