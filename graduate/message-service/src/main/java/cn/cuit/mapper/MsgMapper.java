package cn.cuit.mapper;

import cn.cuit.entity.Msg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MsgMapper extends BaseMapper<Msg> {
	int updateMsg(Msg msg);

	int insertNewMsg(Msg msg);

	@Select("select * from t_message where uid=#{uid}")
	List<Msg> selectByIdMsg(@Param("uid")int uid);

//	@Select("select * from t_message where ")
//	List<Msg> selectAllUser();
}
