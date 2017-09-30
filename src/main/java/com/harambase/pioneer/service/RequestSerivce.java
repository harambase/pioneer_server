package com.harambase.pioneer.service;

import com.alibaba.fastjson.JSONObject;
import com.harambase.common.HaramMessage;
import com.harambase.pioneer.pojo.TempUser;

public interface RequestSerivce {

    HaramMessage deleteTempUserById(Integer id);

    HaramMessage register(JSONObject jsonObject);

    HaramMessage updateTempUser(TempUser tempUser);
}
