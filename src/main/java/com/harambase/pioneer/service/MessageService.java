package com.harambase.pioneer.service;

import com.harambase.common.HaramMessage;
import com.harambase.pioneer.pojo.base.MessageWithBLOBs;

public interface MessageService {

    HaramMessage list(String currentPage, String pageSize, String search, String order,
                      String orderColumn,String receiverid, String senderid, String box);

    HaramMessage getMessageView(Integer id);
    
    HaramMessage countMessageByStatus(String receiverid, String senderid, String box, String status);

    HaramMessage createMessage(MessageWithBLOBs message);

    HaramMessage delete(Integer id);

    HaramMessage update(Integer id, MessageWithBLOBs message);
}
