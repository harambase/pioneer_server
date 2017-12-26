package com.harambase.pioneer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.base.TempUserDao;
import com.harambase.pioneer.dao.repository.base.MessageRepository;
import com.harambase.pioneer.dao.repository.base.TempUserRepository;
import com.harambase.pioneer.pojo.base.Message;
import com.harambase.pioneer.pojo.base.TempUser;
import com.harambase.pioneer.service.TempUserService;
import com.harambase.support.util.DateUtil;
import com.harambase.support.util.IDUtil;
import com.harambase.support.util.PageUtil;
import com.harambase.support.util.ReturnMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TempUserImpl implements TempUserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TempUserRepository tempUserRepository;
    private final MessageRepository messageRepository;

    private final TempUserDao tempUserDao;

    @Autowired
    public TempUserImpl(TempUserRepository tempUserRepository,
                        MessageRepository messageRepository,
                        TempUserDao tempUserDao) {
        this.tempUserRepository = tempUserRepository;
        this.messageRepository = messageRepository;
        this.tempUserDao = tempUserDao;
    }

    @Override
    public HaramMessage deleteTempUserById(Integer id) {

        try {
            tempUserRepository.delete(id);
            int count = tempUserRepository.countById(id);
            return count == 0 ? ReturnMsgUtil.success(null) : ReturnMsgUtil.fail();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage register(JSONObject jsonObject) {

        try {
            String userid = IDUtil.genUserID(jsonObject.getString("info"));

            TempUser tempUser = new TempUser();
            tempUser.setUserid(userid);
            tempUser.setUserJson(jsonObject.toJSONString());
            tempUser.setCreatetime(DateUtil.DateToStr(new Date()));
            tempUser.setUpdatetime(DateUtil.DateToStr(new Date()));
            tempUser.setStatus("0");

            TempUser newTempUser = tempUserRepository.save(tempUser);
            if (newTempUser == null)
                return ReturnMsgUtil.fail();

            Message message = new Message();
            message.setDate(DateUtil.DateToStr(new Date()));
            message.setReceiverId("9000000000");
            message.setSenderId("9000000000");
            message.setBody("注意!接收到来自" + userid + "的请求注册信息");
            message.setTitle("注册信息");
            message.setStatus("UNREAD");
            message.setSubject("用户注册");
            message.setTag("work");
            message.setLabels("inbox/important/");

            Message newMsg = messageRepository.save(message);
            if (newMsg == null)
                throw new RuntimeException("Message 插入失败!");

            return ReturnMsgUtil.success(newTempUser);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }

    }

    @Override
    public HaramMessage updateTempUser(TempUser tempUser) {
        try {
            tempUser.setUpdatetime(DateUtil.DateToStr(new Date()));
            TempUser newTempUser = tempUserRepository.save(tempUser);
            return newTempUser != null ? ReturnMsgUtil.success(newTempUser) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }

    }

    @Override
    public HaramMessage tempUserList(String currentPage, String pageSize, String search, String order, String orderColumn, String status) {
        HaramMessage message = new HaramMessage();
        switch (Integer.parseInt(orderColumn)) {
            case 1:
                orderColumn = "userid";
                break;
            case 2:
                orderColumn = "createtime";
                break;
            default:
                orderColumn = "id";
                break;
        }
        try {

            long totalSize = tempUserDao.getCountByMapPageSearchOrdered(search, status);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            List<TempUser> tempUsers = tempUserDao.getByMapPageSearchOrdered(page.getCurrentIndex(), page.getPageSize(), search, order, orderColumn, status);

            message.setData(tempUsers);
            message.put("page", page);
            message.setMsg(FlagDict.SUCCESS.getM());
            message.setCode(FlagDict.SUCCESS.getV());
            return message;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

}
