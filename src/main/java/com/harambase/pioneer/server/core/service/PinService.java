package com.harambase.pioneer.server.core.service;

import com.harambase.pioneer.common.HaramMessage;

public interface PinService {

    HaramMessage validate(Integer pin, String userId);

    HaramMessage generateAll(String startTime, String endTime, int role, String info, String remark);

    HaramMessage deleteAllByInfo(String info);

    HaramMessage listByInfo(String currentPage, String pageSize, String search, String order, String orderColumn, String info);

    HaramMessage sendAdvisorPin(String info, String senderId);

    HaramMessage sendFacultyPin(String info, String senderId);

    HaramMessage deleteSingleByPin(Integer pin);

    HaramMessage generateOne(String startTime, String endTime, int role, String info, String remark, String userId);

    HaramMessage getAllInfo();
}
