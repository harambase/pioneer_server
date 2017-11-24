package com.harambase.pioneer.service.impl;

import com.harambase.common.util.DateUtil;
import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.common.util.PageUtil;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.MessageDao;
import com.harambase.pioneer.dao.mapper.MessageMapper;
import com.harambase.pioneer.pojo.MessageWithBLOBs;
import com.harambase.pioneer.pojo.dto.MessageView;
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
    public HaramMessage getMessageView(String id) {
        HaramMessage haramMessage = new HaramMessage();
        try{
            MessageView messageView = messageMapper.selectViewByPrimaryKey(Integer.parseInt(id));
            haramMessage.setData(messageView);
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
    public HaramMessage updateStatus(String id, String status) {
        HaramMessage haramMessage = new HaramMessage();
        try{
            MessageWithBLOBs message = new MessageWithBLOBs();
            message.setId(Integer.parseInt(id));
            message.setStatus(status.toLowerCase());
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
    public HaramMessage createMessage(MessageWithBLOBs message) {
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
    
}
