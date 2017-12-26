package mybatis.mapper;

import com.harambase.pioneer.pojo.view.MessageView;
import com.harambase.pioneer.pojo.base.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
@Deprecated
public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKeyWithBLOBs(Message record);

    int updateByPrimaryKey(Message record);

    MessageView selectViewByPrimaryKey(int i);

}