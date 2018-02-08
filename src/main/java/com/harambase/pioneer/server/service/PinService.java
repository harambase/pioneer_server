package com.harambase.pioneer.server.service;

import com.harambase.pioneer.common.ResultMap;

public interface PinService {

    ResultMap validate(Integer pin, String userId);

    ResultMap generateAll(String startTime, String endTime, int role, String info, String remark);

    ResultMap deleteAllByInfo(String info);

    ResultMap listByInfo(String currentPage, String pageSize, String search, String order, String orderColumn, String info);

    ResultMap sendAdvisorPin(String info, String senderId);

    ResultMap sendFacultyPin(String info, String senderId);

    ResultMap deleteSingleByPin(Integer pin);

    ResultMap generateOne(String startTime, String endTime, int role, String info, String remark, String userId);

    ResultMap getAllInfo();
}
