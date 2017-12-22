package com.harambase.pioneer.service.impl;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.PersonDao;
import com.harambase.pioneer.dao.repository.base.*;
import com.harambase.pioneer.pojo.base.*;
import com.harambase.pioneer.service.PersonService;
import com.harambase.support.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final AdviseRepository adviseRepository;
    private final MessageRepository messageRepository;
    private final StudentRepository studentRepository;
    private final TranscriptRepository transcriptRepository;
    private final CourseRepository courseRepository;

    private final PersonDao personDao;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, AdviseRepository adviseRepository,
                             TranscriptRepository transcriptRepository, MessageRepository messageRepository, StudentRepository studentRepository,
                             PersonDao personDao, CourseRepository courseRepository) {
        this.personRepository = personRepository;
        this.adviseRepository = adviseRepository;
        this.transcriptRepository = transcriptRepository;
        this.messageRepository = messageRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.personDao = personDao;
    }

    @Override
    public HaramMessage login(Person person) {
        Person user = personRepository.findOne(person.getUserid());
        if (user != null) {
            String status = user.getStatus();
            return status.equals("1") ? ReturnMsgUtil.success(user) : ReturnMsgUtil.custom(FlagDict.USER_DISABLED);
        }
        return ReturnMsgUtil.fail();
    }

    @Override
    public HaramMessage addUser(Person person) {

        String userid, password;
        String info = person.getInfo();

        List<Person> people = personRepository.findByInfo(info);

        if (person.getUserid() == null)
            userid = IDUtil.genUserID(info);
        else
            userid = person.getUserid();

        for (int i = 0; i < people.size(); i++) {
            Person p = people.get(i);
            if (userid.equals(p.getUserid())) {
                userid = IDUtil.genUserID(info);
                i = 0;
            }
        }
        person.setUserid(userid);

        if (person.getPassword() == null) {
            password = "Pioneer" + userid;
            person.setPassword(password);
        }

        String firstPY = Pinyin4jUtil.converterToFirstSpell(person.getLastname());
        String lastPY = Pinyin4jUtil.converterToFirstSpell(person.getFirstname());
        String username = lastPY + firstPY + userid.substring(7, 10);

        person.setUsername(username);
        person.setCreatetime(DateUtil.DateToStr(new Date()));
        person.setUpdatetime(DateUtil.DateToStr(new Date()));
        person.setStatus("1");
        Person newPerson = personRepository.save(person);

        if (person.getType().contains("s")) {
            Student student = new Student();
            student.setStudentid(userid);
            student.setMaxCredits(12);
            studentRepository.save(student);
        }

        if (newPerson != null) {
            Message message = new Message();
            message.setDate(DateUtil.DateToStr(new Date()));
            message.setReceiverid(userid);
            message.setSenderid(IDUtil.ROOT);
            message.setBody("您的接收到来自管理员的一条消息:你的用户已成功创建");
            message.setTitle("账户信息");
            message.setStatus("UNREAD");
            message.setTag("work");
            message.setLabels("['inbox','important']");

            messageRepository.save(message);

            return ReturnMsgUtil.success(newPerson);

        }
        return ReturnMsgUtil.fail();

    }

    @Override
    public HaramMessage removeUser(String userid) {

        if (userid.equals(IDUtil.ROOT))
            return ReturnMsgUtil.custom(FlagDict.DELETE_BLOCK);

        Person person = personRepository.findOne(userid);
        if(person == null){
            return ReturnMsgUtil.fail();
        }

        adviseRepository.deleteByStudentidOrFacultyid(person.getUserid(), person.getUserid());
//        if(person.getType().equals("s"))
            transcriptRepository.deleteTranscriptByStudentid(person.getUserid());
//        else{
//            List<Course> courseList = courseRepository.findCourseByFacultyid(person.getUserid());
//            for (Course c : courseList) {
//                    String facultyid = c.getFacultyid();
//                    if (facultyid.equals(userid)) {
//                        String opTime = DateUtil.DateToStr(new Date());
//                        c.setFacultyid(IDUtil.ROOT);
//                        c.setComment(person.getLastname() + "," + person.getFirstname() + "老师被删除, 删除时间：" + opTime);
//                        c.setUpdatetime(DateUtil.DateToStr(new Date()));
//                        courseRepository.save(c);
//                    }
//                }
//        }

        //会自动删除学生表
        personRepository.delete(person);
        return ReturnMsgUtil.success(null);
    }

    @Override
    public HaramMessage update(Person person) {
        person.setUpdatetime(DateUtil.DateToStr(new Date()));
        Person newPerson = personRepository.save(person);
        return newPerson != null ? ReturnMsgUtil.success(newPerson) : ReturnMsgUtil.fail();
    }

    @Override
    public HaramMessage getUser(String userid) {
        Person person = personRepository.findOne(userid);
        return ReturnMsgUtil.success(person);
    }


    @Override
    public HaramMessage userList(String currentPage, String pageSize, String search, String order, String orderColumn,
                                 String type, String status) {
        HaramMessage message = new HaramMessage();
        if (StringUtils.isNotEmpty(type)) {
            switch (Integer.parseInt(orderColumn)) {
                case 0:
                    orderColumn = "userid";
                    break;
                case 1:
                    orderColumn = "firstname";
                    break;
                case 2:
                    orderColumn = "lastname";
                    break;
            }
        } else {
            switch (Integer.parseInt(orderColumn)) {
                case 0:
                    orderColumn = "id";
                    break;
                case 1:
                    orderColumn = "userid";
                    break;
                case 2:
                    orderColumn = "username";
                    break;
                case 3:
                    orderColumn = "lastname";
                    break;
                case 4:
                    orderColumn = "firstname";
                    break;
                case 5:
                    orderColumn = "password";
                    break;
                case 6:
                    orderColumn = "type";
                    break;
                case 7:
                    orderColumn = "status";
                    break;
                default:
                    orderColumn = "updatetime";
                    break;
            }
        }
        long totalSize = 0;
        try {
//            Map<String, Object> param = new HashMap<>();
//            param.put("search", search);
//            param.put("type", type);
//            param.put("status", status);
//
//            if(StringUtils.isEmpty(search))
//                param.put("search", null);

//            totalSize = personMapper.getCountByMapPageSearchOrdered(param); //startTime, endTime);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

//            param.put("currentIndex", page.getCurrentIndex());
//            param.put("pageSize",  page.getPageSize());
//            param.put("order",  order);
//            param.put("orderColumn",  orderColumn);

            //(int currentIndex, int pageSize, String search, String order, String orderColumn);
//            List<Person> msgs = personMapper.getByMapPageSearchOrdered(param);

            Sort sort = new Sort(Sort.Direction.DESC, orderColumn);
            Pageable pageable = new PageRequest(page.getCurrentIndex(), page.getPageSize(), sort);
            List<Person> personList = personRepository.findAll(pageable).getContent();
            message.setData(personList);
            message.put("page", page);
            message.setMsg(FlagDict.SUCCESS.getM());
            message.setCode(FlagDict.SUCCESS.getV());
            return message;

        } catch (Exception e) {
            e.printStackTrace();
            message.setMsg(FlagDict.SYSTEM_ERROR.getM());
            message.setCode(FlagDict.SYSTEM_ERROR.getV());
            return message;
        }
    }


    @Override
    public HaramMessage listUsers(String search, String type, String status) {
        HaramMessage message = new HaramMessage();
        try {
            List<Person> users = personDao.getPersonBySearch(search, type, status);

            message.setData(users);
            message.setMsg(FlagDict.SUCCESS.getM());
            message.setCode(FlagDict.SUCCESS.getV());

            return message;

        } catch (Exception e) {
            e.printStackTrace();
            message.setMsg(FlagDict.SYSTEM_ERROR.getM());
            message.setCode(FlagDict.SYSTEM_ERROR.getV());
            return message;
        }
    }

    @Override
    public HaramMessage countActivePerson(String type) {
        int count = personRepository.countByTypeAndStatus(type, "1");
        return ReturnMsgUtil.success(count);
    }

}
