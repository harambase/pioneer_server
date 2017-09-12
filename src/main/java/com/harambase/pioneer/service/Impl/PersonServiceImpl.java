package com.harambase.pioneer.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.harambase.common.*;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.charts.StaticGexfGraph;
import com.harambase.pioneer.dao.mapper.*;
import com.harambase.pioneer.pojo.*;
import com.harambase.pioneer.pojo.dto.AdviseView;
import com.harambase.pioneer.pojo.dto.CourseView;
import com.harambase.pioneer.service.PersonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class PersonServiceImpl implements PersonService {

    private final PersonMapper personMapper;
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;
    private final TranscriptMapper transcriptMapper;
    private final AdviseMapper adviseMapper;
    private final TempUserMapper tempUserMapper;
    private final RoleMapper roleMapper;
    private final MessageMapper messageMapper;

    @Autowired
    public PersonServiceImpl(PersonMapper personMapper, StudentMapper studentMapper,
                             CourseMapper courseMapper, TranscriptMapper transcriptMapper,
                             AdviseMapper adviseMapper, TempUserMapper tempUserMapper,
                             RoleMapper roleMapper, MessageMapper messageMapper){
        this.personMapper = personMapper;
        this.studentMapper = studentMapper;
        this.courseMapper = courseMapper;
        this.transcriptMapper = transcriptMapper;
        this.adviseMapper = adviseMapper;
        this.tempUserMapper = tempUserMapper;
        this.roleMapper = roleMapper;
        this.messageMapper = messageMapper;
    }

    @Override
    public HaramMessage login(Person person) {
        HaramMessage haramMessage = new HaramMessage();
        try {
            Person user = personMapper.selectByPerson(person);
            if(user != null && user.getStatus().equals("1")) {
                haramMessage.setData(user);
                haramMessage.setCode(FlagDict.SUCCESS.getV());
                haramMessage.setMsg(FlagDict.SUCCESS.getM());
            }
            else if(user != null && user.getStatus().equals("0")){
                haramMessage.setCode(FlagDict.USER_DISABLED.getV());
                haramMessage.setMsg(FlagDict.USER_DISABLED.getM());
            }
            else{
                haramMessage.setCode(FlagDict.FAIL.getV());
                haramMessage.setMsg(FlagDict.FAIL.getM());
            }
            return haramMessage;
        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
    }

    @Override
    public HaramMessage addUser(Person person) {
        HaramMessage haramMessage = new HaramMessage();
        try {
            String info = person.getInfo();
            List<Person> people = personMapper.getAllUsersWithInfo(info);
            
            String userid = IDUtil.genUserID(info);
            for(int i = 0; i<people.size(); i++){
                Person p = people.get(i);
                if(userid.equals(p.getUserid())){
                    userid = IDUtil.genUserID(info);
                    i = 0;
                }
            }
            person.setCreatetime(DateUtil.DateToStr(new Date()));
            person.setUpdatetime(DateUtil.DateToStr(new Date()));
            person.setStatus("1");
            
            String password = "Pioneer" + userid;
            
            String firstPY = Pinyin4jUtil.converterToFirstSpell(person.getLastname());
            String lastPY = Pinyin4jUtil.converterToFirstSpell(person.getFirstname());
            String username = lastPY + firstPY + userid.substring(7,10);
           
            person.setUserid(userid);
            person.setUsername(username);
            person.setPassword(password);

            if(person.getType().contains("s")){
                Student student = new Student();
                student.setStudentid(userid);
                student.setMaxCredits(12);
                studentMapper.insert(student);
            }
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
            e.printStackTrace();
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
    }

    @Override
    public HaramMessage userList(String currentPage, String pageSize, String search, String order, String orderColumn,
                                 String type, String status) {
        HaramMessage message = new HaramMessage();
        if(StringUtils.isNotEmpty(type)){
            switch (Integer.parseInt(orderColumn)) {
                case 1:
                    orderColumn = "userid";
                    break;
                case 2:
                    orderColumn = "firstname";
                    break;
                case 3:
                    orderColumn = "lastname";
                    break;
                default:
                    orderColumn = "id";
                    break;
            }
        }
        else {
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
                    orderColumn = "firstname";
                    break;
                case 4:
                    orderColumn = "lastname";
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
            Map<String, Object> param = new HashMap<>();
            param.put("search", search);
            param.put("type", type);
            param.put("status", status);

            if(StringUtils.isEmpty(search))
                param.put("search", null);

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
            message.setMsg(FlagDict.SUCCESS.getM());
            message.setCode(FlagDict.SUCCESS.getV());
            return message;

        }catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
    }

    @Override
    public HaramMessage update(Person person) {
        HaramMessage haramMessage = new HaramMessage();
        try {
            person.setUpdatetime(DateUtil.DateToStr(new Date()));
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
            e.printStackTrace();
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
    }

    @Override
    public HaramMessage listFaculties(String search) {
        HaramMessage message = new HaramMessage();
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("search", search);
            param.put("type", "f");
            param.put("status", "1");

            if(StringUtils.isEmpty(search))
                param.put("search", null);

            List<Person> users = personMapper.getUsersBySearch(param);

            message.setData(users);
            message.setMsg(FlagDict.SUCCESS.getM());
            message.setCode(FlagDict.SUCCESS.getV());

            return message;

        }catch (Exception e) {
            e.printStackTrace();
            message.setMsg(FlagDict.SYSTEM_ERROR.getM());
            message.setCode(FlagDict.SYSTEM_ERROR.getV());
            return message;
        }
    }

    @Override
    public HaramMessage listStudents(String search) {
        HaramMessage message = new HaramMessage();
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("search", search);
            param.put("type", "s");
            param.put("status", "1");

            if(StringUtils.isEmpty(search))
                param.put("search", null);

            List<Person> users = personMapper.getUsersBySearch(param);

            message.setData(users);
            message.setMsg(FlagDict.SUCCESS.getM());
            message.setCode(FlagDict.SUCCESS.getV());
            return message;

        }catch (Exception e) {
            e.printStackTrace();
            message.setMsg(FlagDict.SYSTEM_ERROR.getM());
            message.setCode(FlagDict.SYSTEM_ERROR.getV());
            return message;
        }
    }

    @Override
    public HaramMessage userChart() {
        HaramMessage message = new HaramMessage();
        //统计用户种类
        List<Map<String, String>> data1 = new ArrayList<>();
        Map<String, String> param = new HashMap<>();
        param.put("status", null);

        int s = personMapper.countStudent(param);
        int f = personMapper.countFaculty(param);
        int a = personMapper.countAdmin();

        data1.add(MapParam.pieChartValue(String.valueOf(s), "Student"));
        data1.add(MapParam.pieChartValue(String.valueOf(f), "Faculty"));
        data1.add(MapParam.pieChartValue(String.valueOf(a), "Administrator"));


        //统计性别
        List<Map<String, String>> data2 = new ArrayList<>();
        int male = personMapper.countMale();
        int female = personMapper.countFemale();

        data2.add(MapParam.pieChartValue(String.valueOf(male), "Male"));
        data2.add(MapParam.pieChartValue(String.valueOf(female), "Female"));

        message.put("dataBeast", data1);
        message.put("xAxisData", data2);

        return message;
    }

    @Override
    public HaramMessage getRelationChart() {
        HaramMessage message = new HaramMessage();
        try {

            List<Person> personList = personMapper.getAllUsers();
            List<CourseView> courseList = courseMapper.getAllActiveCourses();
            List<Transcript> transcriptList = transcriptMapper.getAllTranscripts();
            List<Advise> adviseList = adviseMapper.getAllAdvise();

            StaticGexfGraph.graphGenerator(personList, courseList, transcriptList, adviseList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public HaramMessage advisingList(String currentPage, String pageSize, String search, String order, String orderColumn,
                                     String studentid, String facultyid) {
        HaramMessage message = new HaramMessage();
        switch (Integer.parseInt(orderColumn)) {
            case 1:
                orderColumn = "studentid";
                break;
            case 2:
                orderColumn = "sfirst";
                break;
            case 3:
                orderColumn = "slast";
                break;
            case 4:
                orderColumn = "facultyid";
                break;
            case 5:
                orderColumn = "ffirst";
                break;
            case 6:
                orderColumn = "flast";
                break;
            default:
                orderColumn = "id";
                break;
        }
        long totalSize = 0;
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("search", search);
            param.put("studentid", studentid);
            param.put("facultyid", facultyid);
            if(StringUtils.isEmpty(studentid))
                param.put("studentid", null);
            if(StringUtils.isEmpty(facultyid))
                param.put("facultyid", null);
            if(StringUtils.isEmpty(search))
                param.put("search", null);

            totalSize = adviseMapper.getAdvisingCountByMapPageSearchOrdered(param); //startTime, endTime);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            param.put("currentIndex", page.getCurrentIndex());
            param.put("pageSize",  page.getPageSize());
            param.put("order",  order);
            param.put("orderColumn",  orderColumn);

            //(int currentIndex, int pageSize, String search, String order, String orderColumn);
            List<AdviseView> msgs = adviseMapper.getAdvisingByMapPageSearchOrdered(param);

            message.setData(msgs);
            message.put("page", page);
            message.setMsg(FlagDict.SUCCESS.getM());
            message.setCode(FlagDict.SUCCESS.getV());
            return message;

        }catch (Exception e) {
            e.printStackTrace();
            message.setMsg(FlagDict.SYSTEM_ERROR.getM());
            message.setCode(FlagDict.SYSTEM_ERROR.getV());
            return message;
        }
    }

    @Override
    public HaramMessage updateAdvise(Advise advise) {
        HaramMessage haramMessage = new HaramMessage();
        try {
            int ret = adviseMapper.updateByPrimaryKeySelective(advise);
            if(ret == 1) {
                haramMessage.setData(advise);
                haramMessage.setCode(FlagDict.SUCCESS.getV());
                haramMessage.setMsg(FlagDict.SUCCESS.getM());
            }
            else{
                haramMessage.setCode(FlagDict.FAIL.getV());
                haramMessage.setMsg(FlagDict.FAIL.getM());
            }
            return haramMessage;
        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
    }

    @Override
    public HaramMessage assignMentor(Advise advise) {
        HaramMessage haramMessage = new HaramMessage();
        try {
            Advise a = adviseMapper.selectByPrimaryKey(advise);
            if(a != null){
                haramMessage.setCode(FlagDict.ADVISE_DUPLICATE.getV());
                haramMessage.setMsg(FlagDict.ADVISE_DUPLICATE.getM());
                return haramMessage;
            }
            int ret = adviseMapper.insertSelective(advise);
            if(ret == 1) {
                haramMessage.setData(advise);
                haramMessage.setCode(FlagDict.SUCCESS.getV());
                haramMessage.setMsg(FlagDict.SUCCESS.getM());
            }
            else{
                haramMessage.setCode(FlagDict.FAIL.getV());
                haramMessage.setMsg(FlagDict.FAIL.getM());
            }
            return haramMessage;
        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
    }

    @Override
    public HaramMessage removeMentor(Integer id) {
        HaramMessage haramMessage = new HaramMessage();
        try {
            int ret = adviseMapper.deleteByPrimaryKey(id);
            if(ret == 1) {
                haramMessage.setCode(FlagDict.SUCCESS.getV());
                haramMessage.setMsg(FlagDict.SUCCESS.getM());
            }
            else{
                haramMessage.setCode(FlagDict.FAIL.getV());
                haramMessage.setMsg(FlagDict.FAIL.getM());
            }
            return haramMessage;
        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            return haramMessage;
        }
    }

    @Override
    public HaramMessage countActivePerson(String type) {
        HaramMessage message = new HaramMessage();
        Map<String, String> param = new HashMap<>();
        param.put("status", "1");
        //统计用户种类
        switch (type){
            case "s":
                int s = personMapper.countStudent(param);
                message.setData(s);
                return message;
            case "f":
                int f = personMapper.countFaculty(param);
                message.setData(f);
                return message;
        }

        message.setData(0);
        return message;
    }

    @Override
    @Transactional
    public HaramMessage removeUser(String userid) {
        HaramMessage message = new HaramMessage();
        try {
            if(!userid.equals("9000000000")) {
                Person p = personMapper.selectByPrimaryKey(userid);
                if (p != null) {
                    String type = p.getType();
                    if (type.contains("s")) {
                        studentMapper.deleteByPrimaryKey(userid);
                        transcriptMapper.deleteByStudentid(userid);
                    } else if (type.contains("f")) {
                        List<CourseView> courseViewList = courseMapper.getAllActiveCourses();
                        for (CourseView c : courseViewList) {
                            String facultyid = c.getFacultyid();
                            if (facultyid.equals(userid)) {
                                String opTime = DateUtil.DateToStr(new Date());
                                c.setFacultyid("9000000000");
                                c.setComment(c.getFaculty() + "老师被删除, 删除时间：" + opTime);
                                c.setUpdatetime(DateUtil.DateToStr(new Date()));
                                courseMapper.updateByPrimaryKeySelective(c);
                            }
                        }
                    }
                    adviseMapper.deleteByUserID(userid);
                    personMapper.deleteByPrimaryKey(userid);
                }
                message.setMsg(FlagDict.SUCCESS.getM());
                message.setCode(FlagDict.SUCCESS.getV());
            }
            else{
                message.setCode(FlagDict.DELETE_BLOCK.getV());
                message.setMsg(FlagDict.DELETE_BLOCK.getM());
            }
            return message;
        }catch (Exception e){
            e.printStackTrace();
            message.setCode(FlagDict.SYSTEM_ERROR.getV());
            message.setMsg(FlagDict.SYSTEM_ERROR.getM());
        }
        return message;
    }

    @Override
    public HaramMessage register(JSONObject jsonObject) {
        HaramMessage haramMessage = new HaramMessage();
        try{

            String userid = IDUtil.genUserID(jsonObject.getString("info"));

            TempUser tempUser = new TempUser();
            tempUser.setUserid(userid);
            tempUser.setUserJson(jsonObject.toJSONString());

            int ret = tempUserMapper.insertSelective(tempUser);
            if(ret <= 0)
                throw new RuntimeException("TempUser 插入失败!");

            Message message = new Message();
            message.setCreatetime(DateUtil.DateToStr(new Date()));
            message.setReceiverid("9000000000");
            message.setSenderid("9000000000");
            message.setContent("注意!接收到来自" + userid + "的请求注册信息");
            message.setTitle("注册信息");
            message.setStatus("UNREAD");
            message.setType("用户注册");

            ret = messageMapper.insertSelective(message);
            if(ret <= 0)
                throw new RuntimeException("Message 插入失败!");

        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
        }
        return haramMessage;
    }

}
