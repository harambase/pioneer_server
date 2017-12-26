package com.harambase.pioneer.dao.repository.base;

import com.harambase.pioneer.pojo.base.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
