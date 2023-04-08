package cn.cuit.service.impl;

import cn.cuit.entity.Complaints;
import cn.cuit.entity.Reply;
import cn.cuit.mapper.ComplaintsMapper;
import cn.cuit.resultAPI.Result;
import cn.cuit.service.ComplaintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintsServiceImpl implements ComplaintsService {
	@Autowired
	private ComplaintsMapper complaintsMapper;

	public void setComplaintsMapper(ComplaintsMapper complaintsMapper) {
		this.complaintsMapper = complaintsMapper;
	}

	@Override
	public Result<Complaints> addNewComplaints(Complaints complaints) {
		if (complaintsMapper.insertComplaints(complaints) == 0){
			Result.failed("评论失败");
		}
		return Result.success("评论成功");
	}

	/**
	 * 查询所有的投诉
	 */
	@Override
	public Result<List<Complaints>> queryAll() {
		List<Complaints> complaints = complaintsMapper.selectAllCom();
		for (Complaints c :
				complaints) {
			List<Reply> replies = complaintsMapper.selectById(c.getId());
			if (replies != null){
				c.setReplies(replies);
			}
		}
		return Result.success(complaints);
	}

	@Override
	public Result<Complaints> deleteComplaints(int id) {
		return Result.success(complaintsMapper.deleteById(id)+"成功");
	}
}
