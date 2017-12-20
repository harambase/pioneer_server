package com.harambase.pioneer.dao.repository.base;

import com.harambase.pioneer.pojo.Pin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PinRepository extends JpaRepository<Pin, Integer> {

}