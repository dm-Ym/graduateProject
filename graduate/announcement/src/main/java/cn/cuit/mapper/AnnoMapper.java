package cn.cuit.mapper;

import cn.cuit.entity.Anno;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AnnoMapper extends BaseMapper<Anno> {
	int updateStatus(Anno anno);
	int insertNewAnno(Anno anno);
	List<Anno> selectList();
}
