package cn.cuit.mapper;

import cn.cuit.entity.Comments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentsMapper extends BaseMapper<Comments> {
	int updateInfo(Comments comments);

	int insertNew(Comments comments);

	List<Comments> selectList();
}
