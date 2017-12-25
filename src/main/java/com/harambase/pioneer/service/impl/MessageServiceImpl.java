package com.harambase.pioneer.service.impl;

import com.harambase.pioneer.dao.repository.view.MessageViewRepository;
import com.harambase.pioneer.pojo.base.Message;
import com.harambase.support.util.DateUtil;
import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.support.util.PageUtil;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.base.MessageDao;
import com.harambase.pioneer.dao.repository.base.MessageRepository;
import com.harambase.pioneer.pojo.view.MessageView;
import com.harambase.pioneer.service.MessageService;
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
public class MessageServiceImpl implements MessageService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MessageRepository messageRepository;
    private final MessageViewRepository messageViewRepository;
    private final MessageDao messageDao;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository,
                              MessageViewRepository messageViewRepository,
                              MessageDao messageDao) {
        this.messageRepository = messageRepository;
        this.messageViewRepository = messageViewRepository;
        this.messageDao = messageDao;
    }

    @Override
    public HaramMessage list(String currentPage, String pageSize, String search, String order, String orderColumn,
                             String receiverid, String senderid, String box) {
        try {
            HaramMessage message = new HaramMessage();
            switch (Integer.parseInt(orderColumn)) {
                case 1:
                    orderColumn = "sender";
                    break;
                case 2:
                    orderColumn = "title";
                    break;
                case 3:
                    orderColumn = "body";
                    break;
                case 4:
                    orderColumn = "status";
                    break;
                default:
                    orderColumn = "date";
                    break;
            }

            long totalSize = messageDao.getMessageCountByMapPageSearchOrdered(receiverid, senderid, box, search); //startTime, endTime);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            List<MessageView> msgs = messageDao.getMessageByMapPageSearchOrdered(receiverid, senderid, box, search,
                    page.getCurrentIndex(), page.getPageSize(), order, orderColumn);

            message.setData(msgs);
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
    public HaramMessage getMessageView(Integer id) {
        try {
            MessageView messageViewView = messageViewRepository.findOne(id);
            return ReturnMsgUtil.success(messageViewView);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage countMessageByStatus(String receiverid, String senderid, String box, String status) {
        try {
            int count = messageDao.countMessageByStatus(receiverid, senderid, box, status);
            return ReturnMsgUtil.success(count);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage update(Integer id, Message message) {
        try {
            message.setId(id);
            Message newMessage = messageRepository.save(message);
            return newMessage != null ? ReturnMsgUtil.success(newMessage) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage createMessage(Message message) {
        try {
            message.setDate(DateUtil.DateToStr(new Date()));
            Message newMessage = messageRepository.save(message);
            return newMessage != null ? ReturnMsgUtil.success(newMessage) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public HaramMessage delete(Integer id) {
        try {
            messageRepository.delete(id);
            int count = messageRepository.countById(id);
            return count == 0 ? ReturnMsgUtil.success(null) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

}
