package com.harambase.pioneer.service.Impl;

import com.harambase.common.HaramMessage;
import com.harambase.common.Page;
import com.harambase.common.PageUtil;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.mapper.MessageMapper;
import com.harambase.pioneer.pojo.Message;
import com.harambase.pioneer.pojo.dto.AdviseView;
import com.harambase.pioneer.pojo.dto.MessageView;
import com.harambase.pioneer.service.MessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageMapper messageMapper;

    @Autowired
    public MessageServiceImpl(MessageMapper messageMapper){
        this.messageMapper = messageMapper;
    }

    @Override
    public HaramMessage list(String currentPage, String pageSize, String search, String order, String orderColumn,String receiverid) {
        HaramMessage message = new HaramMessage();
        switch (Integer.parseInt(orderColumn)) {
            case 1:
                orderColumn = "semail";
                break;
            case 2:
                orderColumn = "subject";
                break;
            case 3:
                orderColumn = "title";
                break;
            case 4:
                orderColumn = "status";
                break;
            case 5:
                orderColumn = "slast";
                break;
            case 6:
                orderColumn = "sfirst";
                break;
            case 7:
                orderColumn = "date";
                break;
            default:
                orderColumn = "id";
                break;
        }
        long totalSize = 0;
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("search", search);
            param.put("receiverid", receiverid);
            if(StringUtils.isEmpty(receiverid))
                param.put("receiverid", null);
            if(StringUtils.isEmpty(search))
                param.put("search", null);

            totalSize = messageMapper.getMessageCountByMapPageSearchOrdered(param); //startTime, endTime);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            param.put("currentIndex", page.getCurrentIndex());
            param.put("pageSize",  page.getPageSize());
            param.put("order",  order);
            param.put("orderColumn",  orderColumn);

            //(int currentIndex, int pageSize, String search, String order, String orderColumn);
            List<MessageView> msgs = messageMapper.getMessageByMapPageSearchOrdered(param);

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
}
