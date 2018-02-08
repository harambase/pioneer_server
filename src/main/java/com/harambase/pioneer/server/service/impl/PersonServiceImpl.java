package com.harambase.pioneer.server.service.impl;

import com.harambase.pioneer.common.ResultMap;
import com.harambase.pioneer.common.Page;
import com.harambase.pioneer.common.constant.SystemConst;
import com.harambase.pioneer.server.dao.base.PersonDao;
import com.harambase.pioneer.server.dao.repository.*;
import com.harambase.pioneer.server.pojo.base.Course;
import com.harambase.pioneer.server.pojo.base.Message;
import com.harambase.pioneer.server.service.PersonService;
import com.harambase.pioneer.server.pojo.base.Person;
import com.harambase.pioneer.server.pojo.base.Student;
import com.harambase.pioneer.common.support.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PersonRepository personRepository;
    private final AdviseRepository adviseRepository;
    private final MessageRepository messageRepository;
    private final StudentRepository studentRepository;
    private final TranscriptRepository transcriptRepository;
    private final CourseRepository courseRepository;

    //Only for search and paging functionality
    private final PersonDao personDao;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, AdviseRepository adviseRepository,
                             TranscriptRepository transcriptRepository, MessageRepository messageRepository,
                             StudentRepository studentRepository, CourseRepository courseRepository,
                             PersonDao personDao) {
        this.personRepository = personRepository;
        this.adviseRepository = adviseRepository;
        this.transcriptRepository = transcriptRepository;
        this.messageRepository = messageRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.personDao = personDao;
    }

    @Override
    public ResultMap login(Person person) {
        try {
            Person user = personRepository.findByUserIdAndPassword(person.getUserId(), person.getPassword());
            if (user != null) {
                String status = user.getStatus();
                return status.equals("1") ? ReturnMsgUtil.success(user) : ReturnMsgUtil.custom(SystemConst.USER_DISABLED);
            }
            return ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap addUser(Person person) {

        try {
            String userid, password;
            String info = person.getInfo();

            List<Person> people = personRepository.findByInfo(info);

            if (person.getUserId() == null)
                userid = IDUtil.genUserID(info);
            else
                userid = person.getUserId();

            for (int i = 0; i < people.size(); i++) {
                Person p = people.get(i);
                if (userid.equals(p.getUserId())) {
                    userid = IDUtil.genUserID(info);
                    i = 0;
                }
            }
            person.setUserId(userid);

            if (person.getPassword() == null) {
                password = "Pioneer" + userid;
                person.setPassword(password);
            }

            String firstPY = Pinyin4jUtil.converterToFirstSpell(person.getLastName());
            String lastPY = Pinyin4jUtil.converterToFirstSpell(person.getFirstName());
            String username = lastPY + firstPY + userid.substring(7, 10);

            person.setUsername(username);
            person.setCreateTime(DateUtil.DateToStr(new Date()));
            person.setUpdateTime(DateUtil.DateToStr(new Date()));
            person.setStatus("1");
            Person newPerson = personRepository.save(person);

            if (person.getType().contains("s")) {
                Student student = new Student();
                student.setStudentId(userid);
                student.setMaxCredits(12);
                student.setUpdateTime(DateUtil.DateToStr(new Date()));
                studentRepository.save(student);
            }

            if (newPerson != null) {

                Message message = new Message();
                message.setDate(DateUtil.DateToStr(new Date()));
                message.setReceiverId(userid);
                message.setSenderId(IDUtil.ROOT);
                message.setBody("您的接收到来自管理员的一条消息:你的用户已成功创建");
                message.setTitle("账户信息");
                message.setStatus("UNREAD");
                message.setTag("work");
                message.setLabels("['inbox','important']");

                messageRepository.save(message);

                return ReturnMsgUtil.success(newPerson);

            }
            return ReturnMsgUtil.fail();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }

    }

    @Override
    public ResultMap removeUser(String userId) {

        try {
            if (userId.equals(IDUtil.ROOT))
                return ReturnMsgUtil.custom(SystemConst.DELETE_BLOCK);

            Person person = personRepository.findOne(userId);
            if (person == null) {
                return ReturnMsgUtil.fail();
            }

            if (person.getType().contains("s")) {
                studentRepository.delete(userId);
                transcriptRepository.deleteTranscriptByStudentId(person.getUserId());
            }

            adviseRepository.deleteByStudentIdOrFacultyId(person.getUserId(), person.getUserId());

            if (person.getType().contains("f")) {
                List<Course> courseList = courseRepository.findCourseByFacultyId(person.getUserId());

                for (Course c : courseList) {
                    String opTime = DateUtil.DateToStr(new Date());
                    c.setFacultyId(IDUtil.ROOT);
                    c.setComment(person.getLastName() + "," + person.getFirstName() + "老师被删除, 删除时间：" + opTime);
                    c.setUpdateTime(DateUtil.DateToStr(new Date()));
                    courseRepository.save(c);
                }
            }
            personRepository.delete(person);
            int count = personRepository.countByUserId(userId);
            return count == 0 ? ReturnMsgUtil.success(null) : ReturnMsgUtil.fail();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap update(String userId, Person person) {
        try {
            person.setUserId(userId);
            person.setUpdateTime(DateUtil.DateToStr(new Date()));
            Person newPerson = personRepository.save(person);
            return newPerson != null ? ReturnMsgUtil.success(newPerson) : ReturnMsgUtil.fail();
//            return ReturnMsgUtil.success(person);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap getUser(String userid) {
        try {
            Person person = personRepository.findOne(userid);
            return ReturnMsgUtil.success(person);
        } catch (Exception e) {
            logger.error(e.toString());
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap userList(String currentPage, String pageSize, String search, String order, String orderColumn,
                                 String type, String status) {
        ResultMap message = new ResultMap();
        try {
            if (StringUtils.isNotEmpty(type)) {
                switch (Integer.parseInt(orderColumn)) {
                    case 0:
                        orderColumn = "user_id";
                        break;
                    case 1:
                        orderColumn = "first_name";
                        break;
                    case 2:
                        orderColumn = "last_name";
                        break;
                }
            } else {
                switch (Integer.parseInt(orderColumn)) {
                    case 0:
                        orderColumn = "user_id";
                        break;
                    case 1:
                        orderColumn = "username";
                        break;
                    case 2:
                        orderColumn = "last_name";
                        break;
                    case 3:
                        orderColumn = "first_name";
                        break;
                    case 4:
                        orderColumn = "type";
                        break;
                    case 5:
                        orderColumn = "status";
                        break;
                    default:
                        orderColumn = "update_time";
                        break;
                }
            }

            long totalSize = personDao.getCountByMapPageSearchOrdered(search, type, status);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            List<Person> personList = personDao.getByMapPageSearchOrdered(page.getCurrentIndex(), page.getPageSize(), search, order, orderColumn, type, status);

            message.setData(personList);
            message.put("page", page);
            message.setMsg(SystemConst.SUCCESS.getMsg());
            message.setCode(SystemConst.SUCCESS.getCode());
            return message;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }


    @Override
    public ResultMap listUsers(String search, String type, String status) {
        try {
            List<Person> users = personDao.getPersonBySearch(search, type, status);
            return ReturnMsgUtil.success(users);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap countActivePerson(String type) {
        try {
            int count = personRepository.countByTypeAndStatus(type, "1");
            return ReturnMsgUtil.success(count);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

}
