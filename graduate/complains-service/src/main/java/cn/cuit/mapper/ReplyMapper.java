package cn.cuit.mapper;

import cn.cuit.entity.Complaints;
import cn.cuit.entity.Reply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper extends BaseMapper<Reply> {
//	List<Reply> selectAllReply();
    int insertNew(Reply reply);
}
