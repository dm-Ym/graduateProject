package cn.cuit.mapper;

import cn.cuit.entity.Abno;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AbnoMapper extends BaseMapper<Abno> {
//	int updateInfo(Abno abno);
	@Insert("insert into t_abno (abno_name,temperature,other_symptom,risk,localtion,add_time)" +
			"values (#{abnoName},#{temperature},#{otherSymptom},#{risk},#{location},now()) ")
	int insertAbno(Abno abno);

	@Select("select * from t_abno where abno_name=#{abnoName} and status=1;")
	List<Abno> selectByName(@Param("abnoName")String abnoName);

	@Select("select * from t_abno where status=0;")
	List<Abno> selectAbnoList();

	@Select("select * from t_abno where TO_DAYS(add_time) = TO_DAYS(NOW());")
	List<Abno> selectAbnoToday();

	@Select("select * from t_abno where not(temperature between 36.2 and 37.2) and status=1 ")
	List<Abno> selectErrTem();

	@Select("select * from t_abno where risk='是' and status=1 ")
	List<Abno> selectIsRisk();

	/**
	 * 查找康复用户
	 */
	@Select("select abno_name,heal_time from t_abno where abno_name=#{abnoName} and status=0;")
	List<Abno> selectForHeal(@Param("abnoName")String abnoName);

	@Select("select abno_name,heal_time from t_abno where status=0;")
	List<Abno> selectAllHeal();

	@Update("update t_abno set status=#{status} where id=#{id}")
	int updateInfo(Abno abno);
}
