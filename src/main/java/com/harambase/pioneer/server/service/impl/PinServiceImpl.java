package com.harambase.pioneer.server.service.impl;


import com.harambase.pioneer.common.ResultMap;
import com.harambase.pioneer.common.Page;
import com.harambase.pioneer.common.constant.SystemConst;
import com.harambase.pioneer.server.dao.base.PersonDao;
import com.harambase.pioneer.server.dao.repository.PinRepository;
import com.harambase.pioneer.server.helper.TimeValidate;
import com.harambase.pioneer.server.pojo.base.Message;
import com.harambase.pioneer.server.pojo.base.Pin;
import com.harambase.pioneer.server.service.PinService;
import com.harambase.pioneer.server.dao.repository.AdviseRepository;
import com.harambase.pioneer.server.dao.repository.MessageRepository;
import com.harambase.pioneer.server.dao.repository.PersonRepository;
import com.harambase.pioneer.server.pojo.base.Person;
import com.harambase.pioneer.common.support.util.DateUtil;
import com.harambase.pioneer.common.support.util.PageUtil;
import com.harambase.pioneer.common.support.util.ReturnMsgUtil;
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
    public ResultMap validate(Integer pinNum, String userId) {
        try {
            Pin pin = pinRepository.findByPin(pinNum);

            if (pin != null) {
                String ownerId = StringUtils.isNotEmpty(pin.getFacultyId()) ? pin.getFacultyId() : pin.getStudentId();
                return (ownerId.equals(userId) && TimeValidate.isPinValidate(pin)) ? ReturnMsgUtil.success(pin) : ReturnMsgUtil.fail();
            }
            return ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap generateAll(String startTime, String endTime, int role, String info, String remark) {

        try {

            int count = pinRepository.countByInfoAndRole(info, role);
            if (count != 0) {
                return ReturnMsgUtil.custom(SystemConst.PIN_EXISTS);
            }

            List<Person> personList = new ArrayList<>();

            switch (role) {
                case 1:
                    personList = personDao.getPersonBySearch("", "s","", "1", "");
                    break;
                case 2:
                    personList = personDao.getPersonBySearch("", "f", "","1", "");
                    break;
            }

            for (Person person : personList) {
                Pin pin = new Pin();
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
    public ResultMap deleteAllByInfo(String info) {
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
    public ResultMap deleteSingleByPin(Integer pin) {
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
    public ResultMap generateOne(String startTime, String endTime, int role, String info, String remark, String userId) {
        try {

            //检查是否存在该类型的pin
            int stuCount = pinRepository.countByInfoAndStudentId(info, userId);
            int facCount = pinRepository.countByInfoAndFacultyId(info, userId);

            if (stuCount != 0 || facCount != 0) {
                return ReturnMsgUtil.custom(SystemConst.PIN_EXISTS);
            }

            //生成pin
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
    public ResultMap getAllInfo() {
        try {
            List<String> infoList = pinRepository.findInfo();
            return ReturnMsgUtil.success(infoList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap listByInfo(String currentPage, String pageSize, String search, String order, String orderColumn, String info) {
        try {
            ResultMap message = new ResultMap();
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

            for (Pin pin : pinList) {
                int role = pin.getRole();
                String userId = "";
                switch (role) {
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
            message.setMsg(SystemConst.SUCCESS.getMsg());
            message.setCode(SystemConst.SUCCESS.getCode());
            return message;

        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap sendFacultyPin(String info, String senderId) {
        try {
            List<Pin> pinInfoList = pinRepository.findByInfo(info);

            String date = DateUtil.DateToStr(new Date());


            for (Pin pin : pinInfoList) {
                if (pin.getRole() == 2) {

                    Message message = new Message();
                    message.setDate(date);
                    message.setStatus("UNREAD");
                    message.setTitle("PIN的信息");
                    message.setSenderId(senderId);
                    message.setAttachment(null);
                    message.setLabels("inbox/important/");
                    message.setTag("work");

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
    public ResultMap sendAdvisorPin(String info, String senderId) {

        try {
            List<Pin> pinInfoList = pinRepository.findByInfo(info);

            String date = DateUtil.DateToStr(new Date());


            for (Pin pin : pinInfoList) {
                if (pin.getRole() == 1) {

                    Message message = new Message();
                    message.setDate(date);
                    message.setStatus("UNREAD");
                    message.setTitle("您的辅导学生的PIN的信息");
                    message.setSenderId(senderId);
                    message.setAttachment(null);
                    message.setLabels("inbox/important/");
                    message.setTag("work");

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
