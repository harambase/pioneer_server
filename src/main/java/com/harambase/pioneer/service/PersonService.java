package com.harambase.pioneer.service;

import com.harambase.common.HaramMessage;
import com.harambase.pioneer.pojo.Person;

public interface PersonService {
    HaramMessage login(Person person);

    HaramMessage addUser(Person person);
}
