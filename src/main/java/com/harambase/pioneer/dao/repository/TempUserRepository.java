package com.harambase.pioneer.dao.repository;

import com.harambase.pioneer.pojo.base.TempUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempUserRepository extends JpaRepository<TempUser, Integer> {

    int countById(Integer id);
}