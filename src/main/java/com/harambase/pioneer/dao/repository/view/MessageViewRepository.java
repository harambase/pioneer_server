package com.harambase.pioneer.dao.repository.view;

import com.harambase.pioneer.pojo.view.MessageView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageViewRepository extends JpaRepository<MessageView, Integer> {
}
