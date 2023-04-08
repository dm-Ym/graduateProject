package cn.cuit.service.impl;

import cn.cuit.entity.Complaints;
import cn.cuit.entity.Reply;
import cn.cuit.mapper.ReplyMapper;
import cn.cuit.resultAPI.Result;
import cn.cuit.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyMapper replyMapper;

	public void setReplyMapper(ReplyMapper replyMapper) {
		this.replyMapper = replyMapper;
	}

	@Override
	public Result<Reply> addNewReply(Reply reply) {
		return Result.success(replyMapper.insertNew(reply)+"");
	}

//	@Override
//	public Result<List<Reply>> queryAll() {
//		return Result.success(replyMapper.selectList(null));
//	}

	@Override
	public Result<Reply> deleteReply(int id) {
		return Result.success(replyMapper.deleteById(id)+"");
	}
}
