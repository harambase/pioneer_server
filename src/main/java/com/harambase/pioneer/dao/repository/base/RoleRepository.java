package com.harambase.pioneer.dao.repository.base;

import com.harambase.pioneer.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByRoleid(int roleId);
}
