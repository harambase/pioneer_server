//package com.harambase.pioneer.security;
//
//import com.harambase.pioneer.pojo.Person;
//import com.harambase.pioneer.security.service.ShiroService;
//import com.harambase.pioneer.security.service.impl.ShiroServiceImpl;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authc.credential.CredentialsMatcher;
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//
//public class ShiroDbRealm extends AuthorizingRealm {
//
//    private ShiroService shiroService;
//
//    @Autowired
//    public ShiroDbRealm(ShiroService shiroService){
//        this.shiroService = shiroService;
//    }
//    /**
//     * 登录认证
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
//            throws AuthenticationException {
//        ShiroService shiroService = ShiroServiceImpl.me();
//        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
//        Person person = shiroService.user(token.getUsername());
//        SimpleAuthenticationInfo info = shiroService.info(shiroUser, person, super.getName());
//        return info;
//    }
//
//    /**
//     * 权限认证
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        IShiro shiroFactory = ShiroFactroy.me();
//        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
//        List<Integer> roleList = shiroUser.getRoleList();
//
//        Set<String> permissionSet = new HashSet<>();
//        Set<String> roleNameSet = new HashSet<>();
//
//        for (Integer roleId : roleList) {
//            List<String> permissions = shiroFactory.findPermissionsByRoleId(roleId);
//            if (permissions != null) {
//                for (String permission : permissions) {
//                    if (ToolUtil.isNotEmpty(permission)) {
//                        permissionSet.add(permission);
//                    }
//                }
//            }
//            String roleName = shiroFactory.findRoleNameByRoleId(roleId);
//            roleNameSet.add(roleName);
//        }
//
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermissions(permissionSet);
//        info.addRoles(roleNameSet);
//        return info;
//    }
//
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
//}
