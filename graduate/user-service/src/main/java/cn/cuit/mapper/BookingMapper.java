package cn.cuit.mapper;

import cn.cuit.entity.Booking;
import cn.cuit.entity.Pernum;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BookingMapper extends BaseMapper<Booking> {
	@Select("select * from t_booking where uid=#{uid}")
	List<Booking> selectListUid(@Param("uid")int uid);

	@Insert("insert into t_pernum (pid,uid) values (#{pid},#{uid})")
	int updateNum(Pernum pernum);

	@Select("select * from t_pernum where uid=#{uid}")
	Pernum selectSGA(Pernum pernum);

	@Select("select pid,count(*) as num from t_pernum group by pid")
	List<Pernum> selectListPer();
}
