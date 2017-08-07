package com.harambase.pioneer.service.Impl;

import com.harambase.common.HaramMessage;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.PersonMapper;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
