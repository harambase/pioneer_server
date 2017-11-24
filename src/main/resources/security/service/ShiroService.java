package com.harambase.pioneer.security.service;

import com.harambase.pioneer.pojo.Person;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

public interface ShiroService {
    Person user(String username);

    SimpleAuthenticationInfo info(Person person, String name);
}
