package com.harambase.pioneer.security;

import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.security.service.ShiroService;
import com.harambase.pioneer.security.service.impl.ShiroServiceImpl;
import com.harambase.pioneer.service.Impl.PersonServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

public class ShiroDbRealm extends AuthorizingRealm {


    /**
     * 登录认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        config.debug("登录验证后进行权限认证....");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        config.debug("登录操作进行登录认证......");
        System.out.println("登录操作进行登录认证......");
        ShiroService shiroService = ShiroServiceImpl.me();
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        Person user = shiroService.user(token.getUsername());
        if (user == null) {
            // 没找到帐号
            throw new UnknownAccountException("没有在本系统中找到对应的用户信息。");
        }
        //简单验证
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user.getUsername(),user.getPassword(),getName());

        return info;
    }
}
