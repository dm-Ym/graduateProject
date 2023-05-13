package cn.cuit.mapper;

import cn.cuit.entity.Complaints;
import cn.cuit.entity.Reply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ComplaintsMapper extends BaseMapper<Complaints> {
//	List<Complaints> selectAllInfo();
	List<Reply> selectById(@Param("id") int id);
	List<Complaints> selectAllCom();
//	List<Complaints> selectAll();

	int insertComplaints(Complaints complaints);

	@Select("select * from t_complaints where uid=#{uid}")
	List<Complaints> queryOneOfThem(@Param("uid")int uid);

	@Delete("delete from t_complaints where uid=#{uid} and id=#{id}")
	int deleteCompOne(Complaints complaints);
}
