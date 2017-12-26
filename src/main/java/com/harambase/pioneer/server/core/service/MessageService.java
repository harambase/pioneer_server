package com.harambase.pioneer.server.core.service;

import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.server.core.pojo.base.Message;

public interface MessageService {

    HaramMessage list(String currentPage, String pageSize, String search, String order,
                      String orderColumn, String receiverid, String senderid, String box);

    HaramMessage getMessageView(Integer id);

    HaramMessage countMessageByStatus(String receiverid, String senderid, String box, String status);

    HaramMessage createMessage(Message message);

    HaramMessage delete(Integer id);

    HaramMessage update(Integer id, Message message);
}
