package com.harambase.pioneer.service.Impl;

import com.harambase.common.DateUtil;
import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.common.PageUtil;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.PersonMapper;
import com.harambase.pioneer.pojo.Person;
import com.harambase.pioneer.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            Integer last = (int)(Math.random() * (999 - 100 + 1) + 100);
            String userid = "9" + info.split("-")[0] + info.split("-")[1] + last;
            String username = person.getLastname().substring(0,1)+ person.getFirstname();
            String password = "pioneer" + userid;

            person.setUserid(userid);
            person.setUsername(username);
            person.setPassword(password);
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

    @Override
    public HaramMessage userList(String currentPage, String pageSize, String search, String order, String orderColumn) {
        HaramMessage message = new HaramMessage();
        switch (Integer.parseInt(orderColumn)) {
            case 1:
                orderColumn = "userid";
                break;
            case 2:
                orderColumn = "name";
                break;
            case 3:
                orderColumn = "password";
                break;
            case 4:
                orderColumn = "nickname";
                break;
            case 5:
                orderColumn = "status";
                break;
            case 6:
                orderColumn = "createtime";
                break;
            default:
                orderColumn = "updatetime";
                break;
        }
        long totalSize = 0;
        try {
            Map<String, Object> param = new HashMap<>();
            if(search.equals(""))
                param.put("search", null);
            else
                param.put("search", search);

            totalSize = personMapper.getCountByMapPageSearchOrdered(param); //startTime, endTime);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            param.put("currentIndex", page.getCurrentIndex());
            param.put("pageSize",  page.getPageSize());
            param.put("order",  order);
            param.put("orderColumn",  orderColumn);

            //(int currentIndex, int pageSize, String search, String order, String orderColumn);
            List<Person> msgs = personMapper.getByMapPageSearchOrdered(param);

            message.setData(msgs);
            message.put("page", page);
            return message;

        }catch (Exception e) {
            message.setMsg(FlagDict.SYSTEM_ERROR.getM());
            message.setCode(FlagDict.SYSTEM_ERROR.getV());
            return message;
        }
    }

    @Override
    public HaramMessage getUser(String userid) {
        HaramMessage haramMessage = new HaramMessage();
        try {
            Person user = personMapper.selectByPrimaryKey(userid);
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
    public HaramMessage update(Person person) {
        HaramMessage haramMessage = new HaramMessage();
        try {
            int ret = personMapper.updateByPrimaryKeySelective(person);
            if(ret == 1) {
                haramMessage.setData(person);
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
