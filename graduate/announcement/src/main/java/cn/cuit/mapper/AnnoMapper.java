package cn.cuit.mapper;

import cn.cuit.entity.Anno;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AnnoMapper extends BaseMapper<Anno> {
	@Select("select * from t_announcement where status=1 order by create_time desc")
	List<Anno> selectListDesc();
	int updateStatus(Anno anno);
	int insertNewAnno(Anno anno);
	List<Anno> selectList();
}
