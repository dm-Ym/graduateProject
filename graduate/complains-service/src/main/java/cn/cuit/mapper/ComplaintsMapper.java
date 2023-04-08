package cn.cuit.mapper;

import cn.cuit.entity.Complaints;
import cn.cuit.entity.Reply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ComplaintsMapper extends BaseMapper<Complaints> {
//	List<Complaints> selectAllInfo();
	List<Reply> selectById(@Param("id") int id);
	List<Complaints> selectAllCom();
//	List<Complaints> selectAll();

	int insertComplaints(Complaints complaints);
}
