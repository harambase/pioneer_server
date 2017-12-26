package com.harambase.pioneer.dao.repository;

import com.harambase.pioneer.pojo.base.Pin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PinRepository extends JpaRepository<Pin, Integer> {

    Pin findByPin(Integer pinNum);

    void deleteByInfo(String info);

    int countByInfo(String info);

    void deleteByPin(Integer pin);

    int countByPin(Integer pin);

    Page<Pin> findByInfo(String info, Pageable pageable);

    List<Pin> findByInfo(String info);
}