package com.harambase.pioneer.service.impl;

import com.harambase.common.DateUtil;
import com.harambase.common.HaramMessage;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.mapper.PersonMapper;
import com.harambase.pioneer.dao.mapper.PinMapper;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.pojo.Pin;
import com.harambase.pioneer.service.PinService;
import javafx.beans.binding.ObjectExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PinServiceImpl implements PinService{
    private final PersonMapper personMapper;
    private final PinMapper pinMapper;
    
    @Autowired
    public PinServiceImpl(PersonMapper personMapper, PinMapper pinMapper){
        this.personMapper = personMapper;
        this.pinMapper = pinMapper;
    }
    @Override
    public HaramMessage validate(Integer pin, Person user) {
        return null;
    }
    
    @Override
    @Transactional
    public HaramMessage generate(String startTime, String endTime, int role, String info, String remark) {
        HaramMessage haramMessage = new HaramMessage();
        try{
            Map<String, Object> param = new HashMap<>();
            Pin pin = new Pin();
            int pinNum, count;
            
            switch (role){
                case 1:
                    param.put("type", "s");
                    param.put("status", "1");
                    break;
                case 2:
                    param.put("type", "f");
                    param.put("status", "1");
                    break;
            }
            param.put("role", role);

            Object intObject = pinMapper.countByInfo(param);
            count = 0;
            if(intObject != null)
                count = (Integer) intObject;

            if(count > 0){
                haramMessage.setCode(FlagDict.PIN_EXISTS.getV());
                haramMessage.setMsg(FlagDict.PIN_EXISTS.getM());
                return haramMessage;
            }
            
            List<Person> personList = personMapper.getUsersBySearch(param);


            for(Person person : personList){
                do{
                    pinNum = (int)(Math.random() * (999999 - 100000 + 1) + 100000);
                    param.put("pin", pin);
                    count = pinMapper.countByPin(pinNum);
                }while(count != 0);

                pin.setPin(pinNum);
                pin.setStarttime(startTime);
                pin.setEndtime(endTime);
                pin.setCreatetime(DateUtil.DateToStr(new Date()));
                pin.setRole(role);
                pin.setInfo(info);
                pin.setRemark(remark);

                switch (role){
                    case 1:
                        pin.setStudentid(person.getUserid());
                        break;
                    case 2:
                        pin.setFacultyid(person.getUserid());
                        break;
                }

                int ret = pinMapper.insert(pin);
                if(ret != 1)
                    throw new RuntimeException("插入失败");
            }
            
        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
        haramMessage.setCode(FlagDict.SUCCESS.getV());
        haramMessage.setMsg(FlagDict.SUCCESS.getM());
        return haramMessage;
    }
    
    @Override
    public HaramMessage clearAll(Person user) {
        return null;
    }
    
    @Override
    public HaramMessage listByInfo(String info) {
        HaramMessage haramMessage = new HaramMessage();
        try{
            Map<String, Object> param = new HashMap<>();
            param.put("info", info);
            List<String> pinInfoList = pinMapper.listByInfo(param);
            haramMessage.setData(pinInfoList);
        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
        haramMessage.setCode(FlagDict.SUCCESS.getV());
        haramMessage.setMsg(FlagDict.SUCCESS.getM());
        return haramMessage;
    }
}
