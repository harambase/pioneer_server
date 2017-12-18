package com.harambase.pioneer.dao.mapper;

import com.harambase.pioneer.pojo.base.Message;
import com.harambase.pioneer.pojo.base.MessageWithBLOBs;
import com.harambase.pioneer.pojo.MessageView;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageWithBLOBs record);

    int insertSelective(MessageWithBLOBs record);

    MessageWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MessageWithBLOBs record);

    int updateByPrimaryKey(Message record);

    MessageView selectViewByPrimaryKey(int i);

}