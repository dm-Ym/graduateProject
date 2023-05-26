package cn.cuit.mapper;

import cn.cuit.entity.Booking;
import cn.cuit.entity.Num;
import cn.cuit.entity.Recording;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RecordingMapper extends BaseMapper<Recording> {
	@Select("select finish,count(*) as numfinish from t_recording group by finish")
	List<Num> selectJz();
	@Select("select * from t_recording where uid=#{uid}")
	List<Recording> selectByUid(@Param("uid")int uid);

	@Select("select * from t_recording order by uid asc")
	List<Recording> selectListGroup();

	@Insert("insert into graduation.t_recording (uid,user_name,finish,manufacturers,types,create_time)" +
			"values (#{uid},#{userName},#{finish},#{manufacturers},#{types},now())")
	int insertRe(Recording recording);

	@Update("update graduation.t_recording set user_name=#{userName},finish=#{finish},manufacturers=#{manufacturers},types=#{types},create_time=now()" +
			"where id=#{id}")
	int updateRe(Recording recording);
}
