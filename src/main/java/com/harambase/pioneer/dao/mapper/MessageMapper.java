package com.harambase.pioneer.dao.mapper;

import com.harambase.pioneer.pojo.Message;
import com.harambase.pioneer.pojo.MessageWithBLOBs;
import com.harambase.pioneer.pojo.dto.MessageView;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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