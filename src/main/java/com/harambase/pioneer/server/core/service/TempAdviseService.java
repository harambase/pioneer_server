package com.harambase.pioneer.server.core.service;

import com.alibaba.fastjson.JSONObject;
import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.server.core.pojo.base.TempAdvise;

public interface TempAdviseService {

    HaramMessage register(String studentId, JSONObject jsonObject);

    HaramMessage deleteTempAdviseById(Integer id);

    HaramMessage get(Integer id);

    HaramMessage tempAdviseList(String s, String s1, String search, String order, String orderCol);

    HaramMessage updateTempAdvise(Integer id, TempAdvise tempAdvise);
}
