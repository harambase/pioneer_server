package com.harambase.pioneer.security.factory;

import com.harambase.pioneer.dao.MenuDao;
import com.harambase.pioneer.dao.RoleDao;
import com.harambase.pioneer.dao.mapper.PersonMapper;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.security.SpringContextHolder;
import com.harambase.pioneer.security.helper.CollectionKit;
import com.harambase.pioneer.security.entity.ShiroUser;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
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

    private final PersonMapper personMapper;
    private final MenuDao menuDao;
    private final RoleDao roleDao;

    public static ShiroService me() {
        return SpringContextHolder.getBean(ShiroService.class);
    }

    @Autowired
    public ShiroServiceImpl(PersonMapper personMapper, MenuDao menuDao, RoleDao roleDao){
        this.personMapper = personMapper;
        this.menuDao = menuDao;
        this.roleDao = roleDao;
    }
    
    @Override
    public Person user(String userid) {

        Person person = personMapper.selectByPrimaryKey(userid);

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
        shiroUser.setDeptId(user.getDeptId());    // 部门id
        shiroUser.setDeptName(roleDao.getDeptName(user.getDeptId()));// 部门名称
        shiroUser.setUsername(user.getUsername());// 用户名

        Integer[] roleArray = CollectionKit.toIntArray(",", user.getRoleId());// 角色集

        List<Integer> roleList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();

        for (int roleId : roleArray) {
            roleList.add(roleId);
            roleNameList.add(roleDao.getSingleRoleName(roleId));
        }
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);

        return shiroUser;
    }

    @Override
    public List<String> findPermissionsByRoleId(Integer roleId) throws Exception{
        return  menuDao.getResUrlsByRoleId(roleId);
    }

    @Override
    public String findRoleNameByRoleId(Integer roleId) {
        return roleDao.getSingleRoleTip(roleId);
    }

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, Person user, String realmName) {
        String credentials = user.getPassword();
        // 密码加盐处理
        String source = user.getPassword();
        //ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(credentials,source,realmName);
    }

}
