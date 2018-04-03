package com.harambase.pioneer.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.harambase.pioneer.common.ResultMap;
import com.harambase.pioneer.common.Page;
import com.harambase.pioneer.common.constant.SystemConst;
import com.harambase.pioneer.server.dao.base.TempCourseDao;
import com.harambase.pioneer.server.dao.repository.TempCourseRepository;
import com.harambase.pioneer.server.pojo.base.Message;
import com.harambase.pioneer.server.pojo.base.TempCourse;
import com.harambase.pioneer.server.service.TempCourseService;
import com.harambase.pioneer.server.dao.repository.MessageRepository;
import com.harambase.pioneer.common.support.util.DateUtil;
import com.harambase.pioneer.common.support.util.IDUtil;
import com.harambase.pioneer.common.support.util.PageUtil;
import com.harambase.pioneer.common.support.util.ReturnMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TempCourseServiceImpl implements TempCourseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TempCourseRepository tempCourseRepository;
    private final MessageRepository messageRepository;

    private final TempCourseDao tempCourseDao;

    public TempCourseServiceImpl(TempCourseRepository tempCourseRepository, MessageRepository messageRepository,
                                 TempCourseDao tempCourseDao) {
        this.tempCourseRepository = tempCourseRepository;
        this.messageRepository = messageRepository;
        this.tempCourseDao = tempCourseDao;
    }

    @Override
    public ResultMap register(String facultyId, JSONObject jsonObject) {
        try {
            String crn = IDUtil.genTempCRN(jsonObject.getString("info"));

            TempCourse tempCourse = new TempCourse();
            tempCourse.setFacultyId(facultyId);
            tempCourse.setCrn(crn);
            tempCourse.setCourseJson(jsonObject.toJSONString());
            tempCourse.setCreateTime(DateUtil.DateToStr(new Date()));
            tempCourse.setUpdateTime(DateUtil.DateToStr(new Date()));
            tempCourse.setStatus("0");

            TempCourse newTempCourse = tempCourseRepository.save(tempCourse);
            if (newTempCourse == null)
                return ReturnMsgUtil.fail();

            //todo:向所有教务发送
            Message message = new Message();
            message.setDate(DateUtil.DateToStr(new Date()));
            message.setReceiverId("9000000000");
            message.setSenderId("9000000000");
            message.setBody("注意!接收到来自" + crn + "的请求注册信息");
            message.setTitle("注册信息");
            message.setStatus("UNREAD");
            message.setSubject("用户注册");
            message.setTag("work");
            message.setLabels("inbox/important/");

            Message newMsg = messageRepository.save(message);
            if (newMsg == null)
                throw new RuntimeException("Message 插入失败!");

            return ReturnMsgUtil.success(newTempCourse);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap deleteTempCourseById(Integer id) {
        try {
            tempCourseRepository.delete(id);
            int count = tempCourseRepository.countById(id);
            return count == 0 ? ReturnMsgUtil.success(null) : ReturnMsgUtil.fail();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap updateTempCourse(Integer id, TempCourse tempCourse) {
        try {
            tempCourse.setId(id);
            tempCourse.setUpdateTime(DateUtil.DateToStr(new Date()));
            TempCourse newTempCourse = tempCourseRepository.save(tempCourse);
            return newTempCourse != null ? ReturnMsgUtil.success(newTempCourse) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap tempCourseList(String currentPage, String pageSize, String search, String order, String orderColumn, String status, String facultyId) {
        ResultMap message = new ResultMap();

        try {

            long totalSize = tempCourseDao.getCountByMapPageSearchOrdered(search, status, facultyId);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            List<TempCourse> tempCourses = tempCourseDao.getByMapPageSearchOrdered(page.getCurrentIndex(), page.getPageSize(), search, order, orderColumn, status, facultyId);

            message.setData(tempCourses);
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
    public ResultMap get(Integer id) {
        try {
            TempCourse tempCourse = tempCourseRepository.findOne(id);
            return ReturnMsgUtil.success(tempCourse);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }
}
