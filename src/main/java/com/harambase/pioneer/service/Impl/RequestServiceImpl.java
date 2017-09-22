package com.harambase.pioneer.service.Impl;

import com.harambase.common.HaramMessage;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.mapper.TempCourseMapper;
import com.harambase.pioneer.dao.mapper.TempUserMapper;
import com.harambase.pioneer.service.RequestSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestSerivce{

    private final TempUserMapper tempUserMapper;
    private final TempCourseMapper tempCourseMapper;

    @Autowired
    public RequestServiceImpl(TempUserMapper tempUserMapper, TempCourseMapper tempCourseMapper){
        this.tempCourseMapper = tempCourseMapper;
        this.tempUserMapper = tempUserMapper;
    }

    @Override
    public HaramMessage deleteTempUserById(Integer id) {
        HaramMessage haramMessage = new HaramMessage();
        try{
            int ret = tempUserMapper.deleteByPrimaryKey(id);
            if(ret < 0)
                throw new RuntimeException("删除失败");

            haramMessage.setCode(FlagDict.SUCCESS.getV());
            haramMessage.setMsg(FlagDict.SUCCESS.getM());

        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
        }
        return haramMessage;
    }
}
