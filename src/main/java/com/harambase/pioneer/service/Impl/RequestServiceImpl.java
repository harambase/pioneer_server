package com.harambase.pioneer.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.harambase.common.*;
import com.harambase.common.constant.FlagDict;
import com.harambase.pioneer.dao.mapper.MessageMapper;
import com.harambase.pioneer.dao.mapper.TempCourseMapper;
import com.harambase.pioneer.dao.mapper.TempUserMapper;
import com.harambase.pioneer.pojo.MessageWithBLOBs;
import com.harambase.pioneer.pojo.TempUser;
import com.harambase.pioneer.pojo.dto.AdviseView;
import com.harambase.pioneer.service.RequestSerivce;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RequestServiceImpl implements RequestSerivce{

    private final TempUserMapper tempUserMapper;
    private final TempCourseMapper tempCourseMapper;
    private final MessageMapper messageMapper;

    @Autowired
    public RequestServiceImpl(TempUserMapper tempUserMapper,
                              TempCourseMapper tempCourseMapper,
                              MessageMapper messageMapper){
        this.tempCourseMapper = tempCourseMapper;
        this.tempUserMapper = tempUserMapper;
        this.messageMapper = messageMapper;
    }

    @Override
    public HaramMessage deleteTempUserById(Integer id) {
        HaramMessage haramMessage = new HaramMessage();
        try{
            int ret = tempUserMapper.deleteByPrimaryKey(id);
            if(ret < 0)
                throw new RuntimeException("删除失败");

            haramMessage.setCode(FlagDict.SUCCESS.getV());
            haramMessage.setMsg(FlagDict.SUCCESS.getM());

        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
        }
        return haramMessage;
    }

    @Override
    @Transactional
    public HaramMessage register(JSONObject jsonObject) {
        HaramMessage haramMessage = new HaramMessage();
        try{

            String userid = IDUtil.genUserID(jsonObject.getString("info"));

            TempUser tempUser = new TempUser();
            tempUser.setUserid(userid);
            tempUser.setUserJson(jsonObject.toJSONString());
            tempUser.setCreatetime(DateUtil.DateToStr(new Date()));
            tempUser.setUpdatetime(DateUtil.DateToStr(new Date()));
            tempUser.setStatus("0");

            int ret = tempUserMapper.insert(tempUser);
            if(ret <= 0)
                throw new RuntimeException("TempUser 插入失败!");

            MessageWithBLOBs message = new MessageWithBLOBs();
            message.setDate(DateUtil.DateToStr(new Date()));
            message.setReceiverid("9000000000");
            message.setSenderid("9000000000");
            message.setBody("注意!接收到来自" + userid + "的请求注册信息");
            message.setTitle("注册信息");
            message.setStatus("UNREAD");
            message.setSubject("用户注册");
            message.setTag("work");
            message.setLabels("inbox/important/");

            ret = messageMapper.insertSelective(message);
            if(ret <= 0)
                throw new RuntimeException("MessageWithBLOBs 插入失败!");
            haramMessage.setCode(FlagDict.SUCCESS.getV());
            haramMessage.setMsg(FlagDict.SUCCESS.getM());

        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
        }
        return haramMessage;
    }

    @Override
    public HaramMessage updateTempUser(TempUser tempUser) {
        HaramMessage haramMessage = new HaramMessage();
        try {
            tempUser.setUpdatetime(DateUtil.DateToStr(new Date()));
            int ret = tempUserMapper.updateByPrimaryKeySelective(tempUser);

            if(ret <= 0)
                throw new RuntimeException("TempUser 更新失败!");

            haramMessage.setCode(FlagDict.SUCCESS.getV());
            haramMessage.setMsg(FlagDict.SUCCESS.getM());

        }catch (Exception e){
            e.printStackTrace();
            haramMessage.setCode(FlagDict.SYSTEM_ERROR.getV());
            haramMessage.setMsg(FlagDict.SYSTEM_ERROR.getM());
        }
        return haramMessage;
    }

    @Override
    public HaramMessage tempUserList(String currentPage, String pageSize, String search, String order, String orderColumn, String viewStatus) {
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
        long totalSize = 0;
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("search", search);
            param.put("status", viewStatus);

            if(StringUtils.isEmpty(search))
                param.put("search", null);
            if(StringUtils.isEmpty(viewStatus))
                param.put("status", null);

            totalSize = tempUserMapper.getTempUserCountByMapPageSearchOrdered(param); //startTime, endTime);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            param.put("currentIndex", page.getCurrentIndex());
            param.put("pageSize",  page.getPageSize());
            param.put("order",  order);
            param.put("orderColumn",  orderColumn);

            //(int currentIndex, int pageSize, String search, String order, String orderColumn);
            List<AdviseView> msgs = tempUserMapper.getTempUserByMapPageSearchOrdered(param);

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
