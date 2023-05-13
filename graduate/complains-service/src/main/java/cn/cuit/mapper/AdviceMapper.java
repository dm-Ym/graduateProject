package cn.cuit.mapper;

import cn.cuit.entity.Advice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdviceMapper extends BaseMapper<Advice> {
	@Update("update t_advice set status=1 where id=#{id}")
	int updateByAdmin(Advice advice);

	@Update("update t_advice set status=0 where id=#{id}")
	int updateByAdminStatus(Advice advice);
	List<Advice> selectById(@Param("id") int id);
	@Select("select * from t_advice where uid=#{uid}")
	List<Advice> queryOneOfIt(@Param("uid")int uid);

	@Select("select * from t_advice")
	List<Advice> queryAll();

	@Delete("delete from t_advice where uid=#{uid} and id=#{id}")
	int deleteByUser(Advice advice);

	@Delete("delete from t_advice where id=#{id}")
	int deleteByAdmin(@Param("id")int id);
}
