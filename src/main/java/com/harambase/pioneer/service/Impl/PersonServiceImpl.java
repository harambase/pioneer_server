package com.harambase.pioneer.service.Impl;

import com.harambase.common.DateUtil;
import com.harambase.common.HaramMessage;
import com.harambase.common.constant.FlagDict;
import com.harambase.common.constant.Type;
import com.harambase.pioneer.dao.PersonMapper;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonMapper personMapper;

    @Autowired
    public PersonServiceImpl(PersonMapper personMapper){
        this.personMapper = personMapper;
    }

    @Override
    public HaramMessage login(Person person) {
        HaramMessage haramMessage = new HaramMessage();
        try {
            Person user = personMapper.selectByPerson(person);
            if(user != null) {
                haramMessage.setData(user);
                haramMessage.setCode(FlagDict.SUCCESS.getV());
                haramMessage.setMsg(FlagDict.SUCCESS.getM());
            }
            else{
                haramMessage.setCode(FlagDict.FAIL.getV());
                haramMessage.setMsg(FlagDict.FAIL.getM());
            }
            return haramMessage;
        }catch (Exception e){
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
    }

    @Override
    public HaramMessage addUser(Person person) {
        HaramMessage haramMessage = new HaramMessage();
        try {
            person.setCreatetime(DateUtil.DateToStr(new Date()));
            person.setUpdatetime(DateUtil.DateToStr(new Date()));
            person.setStatus("1");

            String info = person.getInfo();
            Integer lastFour = (int)(Math.random() * (9999 - 1000 + 1) + 1000);
            Integer userid = 100000000;
            String username = "default";
            if(person.getType().equals(Type.STUDENT)) {
                userid = Integer.parseInt("900" + info.split("-")[0] + info.split("-")[1] + lastFour);
                username = "stu" + person.getLastname().substring(0,1)+ person.getFirstname();
            }else if(person.getType().equals(Type.FACULTY)) {
                userid = Integer.parseInt("800" + info.split("-")[0] + info.split("-")[1] + lastFour);
                username = "fau" + person.getLastname().substring(0,1)+ person.getFirstname();
            }else if(person.getType().equals(Type.ADMINISTRATOR)) {
                userid = Integer.parseInt("100" + info.split("-")[0] + info.split("-")[1] + lastFour);
                username = "adm" + person.getLastname().substring(0,1)+ person.getFirstname();
            }
            person.setUserid(userid);
            person.setUsername(username);
            int ret = personMapper.insert(person);
            if(ret == 1){
                haramMessage.setCode(FlagDict.SUCCESS.getV());
                haramMessage.setMsg(FlagDict.SUCCESS.getM());
                haramMessage.setData(person);
            }
            else{
                haramMessage.setCode(FlagDict.FAIL.getV());
                haramMessage.setMsg(FlagDict.FAIL.getM());
            }
            return haramMessage;
        }catch (Exception e){
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
    }
}
