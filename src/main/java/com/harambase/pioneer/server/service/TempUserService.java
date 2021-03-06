package com.harambase.pioneer.server.service;

import com.alibaba.fastjson.JSONObject;
import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.server.pojo.base.TempUser;

public interface TempUserService {

    HaramMessage deleteTempUserById(Integer id);

    HaramMessage register(JSONObject jsonObject);

    HaramMessage updateTempUser(Integer id, TempUser tempUser);

    HaramMessage tempUserList(String s, String s1, String search, String order, String orderCol, String status);

    HaramMessage get(Integer id);
}
