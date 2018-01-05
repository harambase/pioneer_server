package com.harambase.pioneer.server.core.service.impl;


import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.common.Page;
import com.harambase.pioneer.common.constant.FlagDict;
import com.harambase.pioneer.common.helper.TimeValidate;
import com.harambase.pioneer.server.core.dao.base.PersonDao;
import com.harambase.pioneer.server.core.dao.repository.AdviseRepository;
import com.harambase.pioneer.server.core.dao.repository.MessageRepository;
import com.harambase.pioneer.server.core.dao.repository.PersonRepository;
import com.harambase.pioneer.server.core.dao.repository.PinRepository;
import com.harambase.pioneer.server.core.pojo.base.Message;
import com.harambase.pioneer.server.core.pojo.base.Person;
import com.harambase.pioneer.server.core.pojo.base.Pin;
import com.harambase.pioneer.server.core.service.PinService;
import com.harambase.pioneer.support.util.DateUtil;
import com.harambase.pioneer.support.util.PageUtil;
import com.harambase.pioneer.support.util.ReturnMsgUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PinServiceImpl implements PinService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PinRepository pinRepository;
    private final PersonRepository personRepository;
    private final MessageRepository messageRepository;
    private final AdviseRepository adviseRepository;

    private final PersonDao personDao;

    @Autowired
    public PinServiceImpl(PersonRepository personRepository, PinRepository pinRepository,
                          MessageRepository messageRepository, AdviseRepository adviseRepository,
                          PersonDao personDao) {
        this.personRepository = personRepository;
        this.pinRepository = pinRepository;
        this.messageRepository = messageRepository;
        this.adviseRepository = adviseRepository;
        this.personDao = personDao;
    }

    @Override
    public HaramMessage validate(Integer pinNum) {
        try {
            Pin pin = pinRepository.findByPin(pinNum);
            if (pin != null && TimeValidate.isPinValidate(pin)) {
                return ReturnMsgUtil.success(pin);
            }
            return ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage generateAll(String startTime, String endTime, int role, String info, String remark) {

        try {
            Pin pin = new Pin();
            List<Person> personList = new ArrayList<>();

            switch (role) {
                case 1:
                    personList = personDao.getPersonBySearch("", "s", "1");
                    break;
                case 2:
                    personList = personDao.getPersonBySearch("", "f", "1");
                    break;
            }

            int count = pinRepository.countByInfo(info);
            if (count > 0) {
                return ReturnMsgUtil.custom(FlagDict.PIN_EXISTS);
            }


            for (Person person : personList) {
                int pinNum;
                do {
                    pinNum = (int) (Math.random() * (999999 - 100000 + 1) + 100000);
                    count = pinRepository.countByPin(pinNum);
                } while (count != 0);

                pin.setPin(pinNum);
                pin.setStartTime(startTime);
                pin.setEndTime(endTime);
                pin.setCreateTime(DateUtil.DateToStr(new Date()));
                pin.setRole(role);
                pin.setInfo(info);
                pin.setRemark(remark);

                switch (role) {
                    case 1:
                        pin.setStudentId(person.getUserId());
                        break;
                    case 2:
                        pin.setFacultyId(person.getUserId());
                        break;
                }

                Pin newPin = pinRepository.save(pin);
                if (newPin == null)
                    throw new RuntimeException("PIN生成失败!");
            }
            return ReturnMsgUtil.success(null);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }

    }

    @Override
    public HaramMessage deleteAllByInfo(String info) {
        try {
            pinRepository.deleteByInfo(info);
            int count = pinRepository.countByInfo(info);
            return count == 0 ? ReturnMsgUtil.success(null) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage deleteSingleByPin(Integer pin) {
        try {
            pinRepository.deleteByPin(pin);
            int count = pinRepository.countByPin(pin);
            return count == 0 ? ReturnMsgUtil.success(null) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage generateOne(String startTime, String endTime, int role, String info, String remark, String userId) {
        try {
            Pin pin = new Pin();
            int pinNum, count;

            do {
                pinNum = (int) (Math.random() * (999999 - 100000 + 1) + 100000);
                count = pinRepository.countByPin(pinNum);
            } while (count != 0);

            pin.setPin(pinNum);
            pin.setStartTime(startTime);
            pin.setEndTime(endTime);
            pin.setCreateTime(DateUtil.DateToStr(new Date()));
            pin.setRole(role);
            pin.setInfo(info);
            pin.setRemark(remark);

            switch (role) {
                case 1:
                    pin.setStudentId(userId);
                    break;
                case 2:
                    pin.setFacultyId(userId);
                    break;
            }

            Pin newPin = pinRepository.save(pin);
            return newPin != null ? ReturnMsgUtil.success(newPin) : ReturnMsgUtil.fail();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage getAllInfo() {
        try {
            List<String> infoList = pinRepository.findInfo();
            return ReturnMsgUtil.success(infoList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage listByInfo(String currentPage, String pageSize, String search, String order, String orderColumn, String info) {
        try {
            HaramMessage message = new HaramMessage();
            switch (Integer.parseInt(orderColumn)) {
                case 0:
                    orderColumn = "pin";
                    break;
                case 1:
                    orderColumn = "faculty_id";
                    break;
                case 2:
                    orderColumn = "student_id";
                    break;
                case 3:
                    orderColumn = "role";
                    break;
                default:
                    orderColumn = "create_time";
                    break;
            }

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));

            Pageable pageable;
            if (StringUtils.isEmpty(order) || order.toLowerCase().equals("desc")) {
                pageable = new PageRequest(page.getCurrentIndex(), page.getPageSize(), Sort.Direction.DESC, orderColumn);
            } else {
                pageable = new PageRequest(page.getCurrentIndex(), page.getPageSize(), Sort.Direction.ASC, orderColumn);
            }

            List<Pin> pinList = pinRepository.findByInfo(info, pageable).getContent();

            for(Pin pin: pinList){
                int role = pin.getRole();
                String userId = "";
                switch (role){
                    case 1:
                        userId = pin.getStudentId();
                        break;
                    case 2:
                        userId = pin.getFacultyId();
                        break;
                }
                String owner = personRepository.getName(userId);
                pin.setOwner(owner);
            }
            page.setTotalRows(pinList.size());

            message.setData(pinList);
            message.put("page", page);
            message.setMsg(FlagDict.SUCCESS.getM());
            message.setCode(FlagDict.SUCCESS.getV());
            return message;

        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage sendFacultyPin(String info, String senderId) {
        try {
            List<Pin> pinInfoList = pinRepository.findByInfo(info);

            String date = DateUtil.DateToStr(new Date());

            Message message = new Message();
            message.setDate(date);
            message.setStatus("UNREAD");
            message.setTitle("PIN的信息");
            message.setSenderId(senderId);
            message.setAttachment(null);
            message.setLabels("inbox/important/");
            message.setTag("work");

            for (Pin pin : pinInfoList) {
                if (pin.getRole() == 2) {
                    String facultyId = pin.getFacultyId();
                    String body = "您的账号用于管理学生的成绩的PIN号码是：" + pin.getPin() + "，有效期为："
                            + pin.getStartTime() + "至" + pin.getEndTime();
                    message.setReceiverId(facultyId);
                    message.setBody(body);
                    Message newMessage = messageRepository.save(message);
                    if (newMessage == null)
                        throw new RuntimeException("信息插入失败!");
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }

        return ReturnMsgUtil.success(null);
    }

    @Override
    public HaramMessage sendAdvisorPin(String info, String senderId) {

        try {
            List<Pin> pinInfoList = pinRepository.findByInfo(info);

            String date = DateUtil.DateToStr(new Date());
            Message message = new Message();
            message.setDate(date);
            message.setStatus("UNREAD");
            message.setTitle("您的辅导学生的PIN的信息");
            message.setSenderId(senderId);
            message.setAttachment(null);
            message.setLabels("inbox/important/");
            message.setTag("work");

            for (Pin pin : pinInfoList) {
                if (pin.getRole() == 1) {
                    String studentId = pin.getStudentId();
                    String facultyId = adviseRepository.findOneByStudentId(studentId).getFacultyId();
                    Person student = personRepository.findOne(studentId);
                    String studentName = student.getLastName() + ", " + student.getFirstName();

                    String body = "您的辅导学生" + studentName + "用于选课的PIN是：" + pin.getPin() + "，有效期为："
                            + pin.getStartTime() + "至" + pin.getEndTime() + "请及时告知，谢谢！";
                    message.setReceiverId(facultyId);
                    message.setBody(body);
                    Message newMessage = messageRepository.save(message);
                    if (newMessage == null)
                        throw new RuntimeException("信息插入失败!");
                }
            }

            return ReturnMsgUtil.success(null);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }

    }
}
