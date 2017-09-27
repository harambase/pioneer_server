package com.harambase.pioneer.security.service.impl;

import com.harambase.pioneer.dao.mapper.PersonMapper;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.security.service.ShiroService;
import com.harambase.pioneer.security.util.SpringContextHolder;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private PersonMapper personMapper;


    public static ShiroServiceImpl me() {
        return SpringContextHolder.getBean(ShiroServiceImpl.class);
    }

    @Override
    public Person user(String username) {
        List<Person> users = personMapper.selectByUsername(username);

        // 账号不存在
        if (users.isEmpty())
            throw new CredentialsException();

        Person person = users.get(0);
        // 账号被冻结
        if (person.getStatus().equals("0")) {
            throw new LockedAccountException();
        }

        return person;
    }

    @Override
    public SimpleAuthenticationInfo info(Person user, String realmName) {
        String credentials = user.getPassword();
        // 密码加盐处理
        String source = user.getUserid();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(credentials, credentialsSalt, realmName);
    }
}
