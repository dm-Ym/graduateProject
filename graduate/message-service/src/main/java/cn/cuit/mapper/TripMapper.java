package cn.cuit.mapper;

import cn.cuit.entity.Trip;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TripMapper extends BaseMapper<Trip> {

	@Select("select * from t_trip where uid=#{uid}")
	List<Trip> selectTripList(@Param("uid") int uid);
}
