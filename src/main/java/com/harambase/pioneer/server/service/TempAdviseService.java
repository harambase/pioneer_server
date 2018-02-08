package com.harambase.pioneer.server.service;

import com.alibaba.fastjson.JSONObject;
import com.harambase.pioneer.common.ResultMap;
import com.harambase.pioneer.server.pojo.base.TempAdvise;

public interface TempAdviseService {

    ResultMap register(String studentId, JSONObject jsonObject);

    ResultMap deleteTempAdviseById(Integer id);

    ResultMap get(Integer id);

    ResultMap tempAdviseList(String s, String s1, String search, String order, String orderCol);

    ResultMap updateTempAdvise(Integer id, TempAdvise tempAdvise);
}
