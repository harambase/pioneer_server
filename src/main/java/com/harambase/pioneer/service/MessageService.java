package com.harambase.pioneer.service;

import com.harambase.common.HaramMessage;

public interface MessageService {
    HaramMessage list(String currentPage, String pageSize, String search, String order, String orderColumn,String receiverid);
}
