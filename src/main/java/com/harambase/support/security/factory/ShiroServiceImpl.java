package com.harambase.support.security.factory;

import com.harambase.pioneer.dao.repository.PersonRepository;
import com.harambase.pioneer.dao.repository.RoleRepository;
import com.harambase.pioneer.pojo.Person;
import com.harambase.support.security.SpringContextHolder;
import com.harambase.support.security.helper.CollectionKit;
import com.harambase.support.security.entity.ShiroUser;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class ShiroServiceImpl implements ShiroService {

    private final RoleRepository roleRepository;
    private final PersonRepository personRepository;

    public static ShiroService me() {
        return SpringContextHolder.getBean(ShiroService.class);
    }

    @Autowired
    public ShiroServiceImpl(RoleRepository roleRepository, PersonRepository personRepository){
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }
    
    @Override
    public Person user(String userid) {

        Person person = personRepository.findByUserid(userid);

        // 账号不存在
        if (person == null)
            throw new CredentialsException();

        // 账号被冻结
        if (person.getStatus().equals("0")) {
            throw new LockedAccountException();
        }

        return person;
    }

    @Override
    public ShiroUser shiroUser(Person user) {
        ShiroUser shiroUser = new ShiroUser();

        shiroUser.setUserId(user.getUserid());    // 用户id
        shiroUser.setUsername(user.getUsername());// 用户名

        Integer[] roleArray = CollectionKit.toIntArray("/", user.getRoleId());// 角色集

        List<Integer> roleList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();
        List<String> roleCodeList = new ArrayList<>();

        for (int roleId : roleArray) {
            roleList.add(roleId);
            roleNameList.add(roleRepository.findRoleByRoleid(roleId).getRoleName());
            roleCodeList.add(roleRepository.findRoleByRoleid(roleId).getRoleCode());
        }

        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);
        shiroUser.setRoleCodes(roleCodeList);

        return shiroUser;
    }

    @Override
    public String findRoleNameByRoleId(Integer roleId) {
        return roleRepository.findRoleByRoleid(roleId).getRoleName();
    }

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, Person user, String realmName) {
        //String credentials = user.getPassword();
        // 密码加盐处理
        String source = user.getPassword();
        //ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser,source,realmName);
    }

}