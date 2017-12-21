package com.harambase.pioneer.service.impl;

import com.harambase.pioneer.pojo.base.Message;
import com.harambase.support.util.DateUtil;
import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.support.util.PageUtil;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.MessageDao;
import com.harambase.pioneer.dao.mapper.MessageMapper;
import com.harambase.pioneer.pojo.view.MessageView;
import com.harambase.pioneer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageMapper messageMapper;
    private final MessageDao messageDao;

    @Autowired
    public MessageServiceImpl(MessageMapper messageMapper,
                              MessageDao messageDao){
        this.messageMapper = messageMapper;
        this.messageDao = messageDao;
    }

    @Override
    public HaramMessage list(String currentPage, String pageSize, String search, String order, String orderColumn,
                             String receiverid, String senderid, String box) {
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
        long totalSize = 0;
        try {
            totalSize = messageDao.getMessageCountByMapPageSearchOrdered(receiverid, senderid, box, search); //startTime, endTime);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            List<MessageView> msgs = messageDao.getMessageByMapPageSearchOrdered(receiverid, senderid, box, search,
                    page.getCurrentIndex(),page.getPageSize(),order,orderColumn);

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
    public HaramMessage getMessageView(Integer id) {
        HaramMessage haramMessage = new HaramMessage();
        try{
            MessageView messageViewView = messageMapper.selectViewByPrimaryKey(id);
            haramMessage.setData(messageViewView);
            haramMessage.setMsg(FlagDict.SUCCESS.getM());
            haramMessage.setCode(FlagDict.SUCCESS.getV());
            return haramMessage;
        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            return haramMessage;
        }
    }
    
    @Override
    public HaramMessage countMessageByStatus(String receiverid, String senderid, String box, String status) {
        HaramMessage haramMessage = new HaramMessage();
        try{
            int count = messageDao.countMessageByStatus(receiverid, senderid, box, status);
            haramMessage.setData(count);
            haramMessage.setMsg(FlagDict.SUCCESS.getM());
            haramMessage.setCode(FlagDict.SUCCESS.getV());
            return haramMessage;
        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            return haramMessage;
        }
    }
    
    @Override
    public HaramMessage update(Integer id, Message message) {
        HaramMessage haramMessage = new HaramMessage();
        try{
            message.setId(id);
            int ret = messageMapper.updateByPrimaryKeySelective(message);
            if(ret != 1)
                throw new RuntimeException("更新失败");
            
            haramMessage.setMsg(FlagDict.SUCCESS.getM());
            haramMessage.setCode(FlagDict.SUCCESS.getV());
            return haramMessage;
        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            return haramMessage;
        }
    }
    
    @Override
    public HaramMessage createMessage(Message message) {
        HaramMessage haramMessage = new HaramMessage();
        try{
            message.setDate(DateUtil.DateToStr(new Date()));
            int ret = messageMapper.insert(message);
            if(ret != 1)
                throw new RuntimeException("插入失败");
        
            haramMessage.setMsg(FlagDict.SUCCESS.getM());
            haramMessage.setCode(FlagDict.SUCCESS.getV());
            return haramMessage;
            
        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            return haramMessage;
        }
    }

    @Override
    public HaramMessage delete(Integer id) {
        HaramMessage haramMessage = new HaramMessage();
        try{
            int ret =  messageMapper.deleteByPrimaryKey(id);
            if(ret != 1)
                throw new RuntimeException("插入失败");

            haramMessage.setMsg(FlagDict.SUCCESS.getM());
            haramMessage.setCode(FlagDict.SUCCESS.getV());
            return haramMessage;

        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            return haramMessage;
        }
    }

}
