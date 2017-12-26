package com.harambase.pioneer.server.core.service.impl;

import com.harambase.pioneer.common.HaramMessage;
import com.harambase.pioneer.common.Page;
import com.harambase.pioneer.common.constant.FlagDict;
import com.harambase.pioneer.server.core.dao.base.MessageDao;
import com.harambase.pioneer.server.core.dao.repository.MessageRepository;
import com.harambase.pioneer.server.core.dao.repository.PersonRepository;
import com.harambase.pioneer.server.core.pojo.base.Message;
import com.harambase.pioneer.server.core.pojo.base.Person;
import com.harambase.pioneer.server.core.pojo.view.MessageView;
import com.harambase.pioneer.server.core.service.MessageService;
import com.harambase.pioneer.support.util.DateUtil;
import com.harambase.pioneer.support.util.PageUtil;
import com.harambase.pioneer.support.util.ReturnMsgUtil;
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
    private final PersonRepository personRepository;
    private final MessageDao messageDao;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository,
                              PersonRepository personRepository,
                              MessageDao messageDao) {
        this.messageRepository = messageRepository;
        this.personRepository = personRepository;
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

            long totalSize = messageDao.getCountByMapPageSearchOrdered(receiverid, senderid, box, search); //startTime, endTime);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            List<MessageView> msgs = messageDao.getByMapPageSearchOrdered(receiverid, senderid, box, search,
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
            MessageView messageView = messageDao.findOne(id);
            String[] receiverIds = messageView.getReceiver().split("/");
            String receiverNames = "";
            for (String receiverId : receiverIds) {
                Person receiver = personRepository.findOne(receiverId);
                receiverNames += receiver.getLastName() + ", " + receiver.getFirstName() + "/";
            }
            messageView.setReceiver(receiverNames);
            return ReturnMsgUtil.success(messageView);
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
