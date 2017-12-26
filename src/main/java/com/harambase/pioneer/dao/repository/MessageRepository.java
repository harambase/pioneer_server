package com.harambase.pioneer.dao.repository;

import com.harambase.pioneer.pojo.base.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    int countById(Integer id);
}