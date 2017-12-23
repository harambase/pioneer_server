package com.harambase.pioneer.service.impl;


import com.harambase.pioneer.dao.base.PersonDao;
import com.harambase.pioneer.pojo.base.Message;
import com.harambase.pioneer.pojo.base.Person;
import com.harambase.pioneer.pojo.base.Pin;
import com.harambase.support.util.DateUtil;
import com.harambase.common.HaramMessage;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.repository.base.AdviseRepository;
import com.harambase.pioneer.dao.repository.base.MessageRepository;
import com.harambase.pioneer.dao.repository.base.PersonRepository;
import com.harambase.pioneer.dao.repository.base.PinRepository;
import com.harambase.common.helper.TimeValidate;
import com.harambase.pioneer.service.PinService;
import com.harambase.support.util.ReturnMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class PinServiceImpl implements PinService{
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private final PinRepository pinRepository;
    private final PersonRepository personRepository;
    private final MessageRepository messageRepository;
    private final AdviseRepository adviseRepository;

    private final PersonDao personDao;

    @Autowired
    public PinServiceImpl(PersonRepository personRepository, PinRepository pinRepository,
                          MessageRepository messageRepository, AdviseRepository adviseRepository,
                          PersonDao personDao){
        this.personRepository = personRepository;
        this.pinRepository = pinRepository;
        this.messageRepository = messageRepository;
        this.adviseRepository = adviseRepository;
        this.personDao = personDao;
    }
    @Override
    public HaramMessage validate(Integer pinNum) {
        try{
            Pin pin = pinRepository.findByPin(pinNum);
            if(pin != null && TimeValidate.isPinValidate(pin)){
                return ReturnMsgUtil.success(pin);
            }
            return ReturnMsgUtil.fail();
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }
    
    @Override
    public HaramMessage generate(String startTime, String endTime, int role, String info, String remark) {

        try{
            Pin pin = new Pin();
            List<Person> personList = new ArrayList<>();
            
            switch (role){
                case 1:
                    personList = personDao.getPersonBySearch("", "s", "1");
                    break;
                case 2:
                    personList = personDao.getPersonBySearch("", "f", "1");
                    break;
            }

            int count = pinRepository.countByInfo(info);
            if(count > 0){
                return ReturnMsgUtil.custom(FlagDict.PIN_EXISTS);
            }


            for(Person person : personList){
                int pinNum;
                do{
                    pinNum = (int)(Math.random() * (999999 - 100000 + 1) + 100000);
                    count = pinRepository.countByPin(pinNum);
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
                        pin.setStudentid(person.getUserId());
                        break;
                    case 2:
                        pin.setFacultyid(person.getUserId());
                        break;
                }

                Pin newPin = pinRepository.save(pin);
                if(newPin == null)
                    throw new RuntimeException("PIN生成失败!");
            }
            return ReturnMsgUtil.success(null);

        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }

    }
    
    @Override
    public HaramMessage deleteAllByInfo(String info) {
        try {
            pinRepository.deleteByInfo(info);
            int count = pinRepository.countByInfo(info);
            return count == 0 ?  ReturnMsgUtil.success(null) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage deleteSingleByPin(Integer pin) {
        try{
            pinRepository.deleteByPin(pin);
            int count = pinRepository.countByPin(pin);
            return count == 0 ?  ReturnMsgUtil.success(null) : ReturnMsgUtil.fail();
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }
    
    @Override
    public HaramMessage listByInfo(String info) {
        try{
            List<Pin> pinInfoList = pinRepository.findByInfo(info);
            return ReturnMsgUtil.success(pinInfoList);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage sendFacultyPin(String info, String senderId){
        try{
            List<Pin> pinInfoList = pinRepository.findByInfo(info);

            String date = DateUtil.DateToStr(new Date());

            Message message = new Message();
            message.setDate(date);
            message.setStatus("UNREAD");
            message.setTitle("PIN的信息");
            message.setSenderid(senderId);
            message.setAttachment(null);
            message.setLabels("inbox/important/");
            message.setTag("work");

            for(Pin pin : pinInfoList){
                if(pin.getRole() == 2){
                    String facultyId = pin.getFacultyid();
                    String body = "您的账号用于管理学生的成绩的PIN号码是：" + pin.getPin() + "，有效期为："
                            + pin.getStarttime() + "至" + pin.getEndtime();
                    message.setReceiverid(facultyId);
                    message.setBody(body);
                    Message newMessage = messageRepository.save(message);
                    if(newMessage == null)
                        throw new RuntimeException("信息插入失败!");
                }
            }

        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }

        return ReturnMsgUtil.success(null);
    }
    
    @Override
    public HaramMessage sendAdvisorPin(String info, String senderId){
        HaramMessage haramMessage = new HaramMessage();
        try{
            List<Pin> pinInfoList = pinRepository.findByInfo(info);

            String date = DateUtil.DateToStr(new Date());
            Message message = new Message();
            message.setDate(date);
            message.setStatus("UNREAD");
            message.setTitle("您的辅导学生的PIN的信息");
            message.setSenderid(senderId);
            message.setAttachment(null);
            message.setLabels("inbox/important/");
            message.setTag("work");

            for(Pin pin : pinInfoList){
                if(pin.getRole() == 1){
                    String studentId = pin.getStudentid();
                    String facultyId = adviseRepository.findOneByStudentid(studentId).getFacultyid();
                    Person student = personRepository.findOne(studentId);
                    String studentName = student.getLastName() + ", " + student.getFirstName();

                    String body = "您的辅导学生"+ studentName +"用于选课的PIN是：" + pin.getPin() + "，有效期为："
                            + pin.getStarttime() + "至" + pin.getEndtime() + "请及时告知，谢谢！";
                    message.setReceiverid(facultyId);
                    message.setBody(body);
                    Message newMessage = messageRepository.save(message);
                    if(newMessage == null)
                        throw new RuntimeException("信息插入失败!");
                }
            }

            return ReturnMsgUtil.success(null);

        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }

    }
}
