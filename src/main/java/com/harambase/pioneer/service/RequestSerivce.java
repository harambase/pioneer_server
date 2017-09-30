package com.harambase.pioneer.service;

import com.alibaba.fastjson.JSONObject;
import com.harambase.common.HaramMessage;
import com.harambase.pioneer.pojo.TempUser;

public interface RequestSerivce {

    HaramMessage deleteTempUserById(Integer id);

    HaramMessage register(JSONObject jsonObject);

    HaramMessage updateTempUser(TempUser tempUser);

    HaramMessage tempUserList(String s, String s1, String search, String order, String orderCol, String viewStatus);
}
