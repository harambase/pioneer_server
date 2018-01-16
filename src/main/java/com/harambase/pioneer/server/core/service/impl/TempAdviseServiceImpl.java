package com.harambase.pioneer.server.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.common.Page;
import com.harambase.pioneer.common.constant.FlagDict;
import com.harambase.pioneer.server.core.dao.base.TempAdviseDao;
import com.harambase.pioneer.server.core.dao.repository.MessageRepository;
import com.harambase.pioneer.server.core.dao.repository.TempAdviseRepository;
import com.harambase.pioneer.server.core.pojo.base.Message;
import com.harambase.pioneer.server.core.pojo.base.TempAdvise;
import com.harambase.pioneer.server.core.pojo.base.TempCourse;
import com.harambase.pioneer.server.core.service.TempAdviseService;
import com.harambase.pioneer.support.util.DateUtil;
import com.harambase.pioneer.support.util.PageUtil;
import com.harambase.pioneer.support.util.ReturnMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TempAdviseServiceImpl implements TempAdviseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TempAdviseRepository tempAdviseRepository;
    private final MessageRepository messageRepository;

    private final TempAdviseDao tempAdviseDao;

    @Autowired
    public TempAdviseServiceImpl(TempAdviseRepository tempAdviseRepository,
                                 MessageRepository messageRepository,
                                 TempAdviseDao tempAdviseDao){
        this.tempAdviseRepository = tempAdviseRepository;
        this.messageRepository = messageRepository;
        this.tempAdviseDao = tempAdviseDao;
    }

    @Override
    public HaramMessage register(String studentId, JSONObject jsonObject) {
        try {

            TempAdvise tempAdvise = new TempAdvise();
            tempAdvise.setUpdateTime(DateUtil.DateToStr(new Date()));
            tempAdvise.setCreateTime(DateUtil.DateToStr(new Date()));
            tempAdvise.setStudentId(studentId);
            tempAdvise.setFacultyIds(jsonObject.toJSONString());
            tempAdvise.setStatus("1");

            TempAdvise newTempAdvise = tempAdviseRepository.save(tempAdvise);
            if (newTempAdvise == null)
                return ReturnMsgUtil.fail();

            //todo:向所有导师发送
            Message message = new Message();
            message.setDate(DateUtil.DateToStr(new Date()));
            message.setReceiverId("9000000000");
            message.setSenderId("9000000000");
            message.setBody("注意!接收到来自" + studentId + "的请求注册信息");
            message.setTitle("注册信息");
            message.setStatus("UNREAD");
            message.setSubject("用户注册");
            message.setTag("work");
            message.setLabels("inbox/important/");

            Message newMsg = messageRepository.save(message);
            if (newMsg == null)
                throw new RuntimeException("Message 插入失败!");

            return ReturnMsgUtil.success(newTempAdvise);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage deleteTempAdviseById(Integer id) {
        try{
            tempAdviseRepository.delete(id);
            int count = tempAdviseRepository.countById(id);
            return count == 0? ReturnMsgUtil.success(null) : ReturnMsgUtil.fail();
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage get(Integer id) {
        try{
            TempAdvise tempAdvise = tempAdviseRepository.findOne(id);
            return ReturnMsgUtil.success(tempAdvise);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage tempAdviseList(String currentPage, String pageSize, String search, String order, String orderColumn) {
        HaramMessage message = new HaramMessage();
        switch (Integer.parseInt(orderColumn)) {
            case 2:
                orderColumn = "student_id";
                break;
            case 4:
                orderColumn = "faculty_ids";
                break;
            case 5:
                orderColumn = "create_time";
                break;
            case 6:
                orderColumn = "status";
                break;
            default:
                orderColumn = "id";
                break;
        }
        try {

            long totalSize = tempAdviseDao.getCountByMapPageSearchOrdered(search);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            List<TempCourse> tempCourses = tempAdviseDao.getByMapPageSearchOrdered(page.getCurrentIndex(), page.getPageSize(), search, order, orderColumn);

            message.setData(tempCourses);
            message.put("page", page);
            message.setMsg(FlagDict.SUCCESS.getM());
            message.setCode(FlagDict.SUCCESS.getV());
            return message;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage updateTempAdvise(Integer id, TempAdvise tempAdvise) {
        try{
            tempAdvise.setId(id);
            TempAdvise newTempAdvise = tempAdviseRepository.save(tempAdvise);
            return ReturnMsgUtil.success(newTempAdvise);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }
}
