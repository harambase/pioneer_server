package com.harambase.pioneer.service;

import com.alibaba.fastjson.JSONObject;
import com.harambase.common.HaramMessage;
import com.harambase.pioneer.pojo.Advise;
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

    HaramMessage userChart();

    HaramMessage getRelationChart();

    HaramMessage advisingList(String s, String s1, String search, String order, String orderCol, String studentid, String facultyid);

    HaramMessage updateAdvise(Advise advise);

    HaramMessage assignMentor(Advise advise);

    HaramMessage removeMentor(Integer id);

    HaramMessage countActivePerson(String type);

    HaramMessage removeUser(String userid);

    HaramMessage register(JSONObject jsonObject);

    HaramMessage approve(int userid, boolean discard);
}
