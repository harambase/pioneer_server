package com.harambase.pioneer.service;

import com.harambase.common.HaramMessage;
import com.harambase.pioneer.pojo.Person;

public interface PinService {
    
    HaramMessage validate(Integer pin, Person user);
    
    HaramMessage generate(String startTime, String endTime, int role, String info, String remark);
    
    HaramMessage clearAll(Person user);
}
