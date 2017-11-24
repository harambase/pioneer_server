package com.harambase.pioneer.security.factory;

import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.security.entity.ShiroUser;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

import java.util.List;

/**
 * 定义shirorealm所需数据的接口
 *
 * @author youe
 * @date 2016年12月5日 上午10:23:34
 */
public interface ShiroService {

    Person user(String userid);

    ShiroUser shiroUser(Person user);

    List<String> findPermissionsByRoleId(Integer roleId) throws Exception;

    String findRoleNameByRoleId(Integer roleId);

    SimpleAuthenticationInfo info(ShiroUser shiroUser, Person user, String realmName);

}
