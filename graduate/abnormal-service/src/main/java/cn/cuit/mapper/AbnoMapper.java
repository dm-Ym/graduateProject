package cn.cuit.mapper;

import cn.cuit.entity.Abno;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AbnoMapper extends BaseMapper<Abno> {
	int updateInfo(Abno abno);

//	List<Abno> queryAllUser();

}
