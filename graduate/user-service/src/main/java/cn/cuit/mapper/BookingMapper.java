package cn.cuit.mapper;

import cn.cuit.entity.Booking;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookingMapper extends BaseMapper<Booking> {
	@Select("select * from t_booking where uid=#{uid}")
	List<Booking> selectListUid(@Param("uid")int uid);
}
