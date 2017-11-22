package com.harambase.pioneer.service;

import com.harambase.common.HaramMessage;

public interface MessageService {

    HaramMessage list(String currentPage, String pageSize, String search, String order,
                      String orderColumn,String receiverid, String senderid, String box);

    HaramMessage getMessageView(String id);
    
    HaramMessage countMessageByStatus(String receiverid, String senderid, String box, String status);
    
    HaramMessage updateStatus(String id, String status);
}
