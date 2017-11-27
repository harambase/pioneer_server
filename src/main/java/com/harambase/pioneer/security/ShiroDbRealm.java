package com.harambase.pioneer.security;

import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.security.entity.ShiroUser;
import com.harambase.pioneer.security.factory.ShiroService;
import com.harambase.pioneer.security.factory.ShiroServiceImpl;
import com.harambase.pioneer.security.helper.ShiroKit;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ShiroDbRealm extends AuthorizingRealm {

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        ShiroService shiroService = ShiroServiceImpl.me();
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        Person user = shiroService.user(token.getUsername());
        ShiroUser shiroUser = shiroService.shiroUser(user);
        //SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
        SimpleAuthenticationInfo info = shiroService.info(shiroUser, user, super.getName());
        return info;
    }

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroService shiroService = ShiroServiceImpl.me();
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        List<Integer> roleList = shiroUser.getRoleList();

        Set<String> permissionSet = new HashSet<>();
        Set<String> roleNameSet = new HashSet<>();

        for (Integer roleId : roleList) {
            List<String> permissions = null;
            try {
                permissions = shiroService.findPermissionsByRoleId(roleId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (permissions != null) {
                for (String permission : permissions) {
                    if (StringUtils.isNotEmpty(permission)) {
                        permissionSet.add(permission);
                    }
                }
            }
            String roleName = shiroService.findRoleNameByRoleId(roleId);
            roleNameSet.add(roleName);
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionSet);
        info.addRoles(roleNameSet);
        return info;
    }

//    /**
//     * 设置认证加密方式
//     */
//    @Override
//    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
//        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
//        md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
//        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
//        super.setCredentialsMatcher(md5CredentialsMatcher);
//    }
}
