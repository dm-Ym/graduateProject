package cn.cuit.mapper;

import cn.cuit.entity.Comments;
import cn.cuit.entity.Reply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReplyMapper extends BaseMapper<Reply> {
//	int updateInfo(Comments comments);

//	@Update("update t_comments set status=1 where ")
//	int updateStatusInt(Comments comments);

	@Select("select * from t_reply where pid=#{pid} order by re_time desc")
	List<Reply> selectByPid(@Param("pid")int pid);
//	int insertNew(Comments comments);
//
//	List<Comments> selectList();
//	@Select("select * from t_comments where status=0")
//	List<Comments> selectListComm();
//
//	@Select("select * from t_comments where status=1;")
//	List<Comments> selectDown();
//
//	@Select("select * from t_comments where com_name=#{comName};")
//	List<Comments> queryByName(String comName);
//
//	@Select("select * from t_comments where uid=#{uid};")
//	List<Comments> queryById(@Param("uid") int uid);

}
