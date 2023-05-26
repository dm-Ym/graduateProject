package cn.cuit.service.impl;

import cn.cuit.entity.Comments;
import cn.cuit.entity.Reply;
import cn.cuit.mapper.CommentsMapper;
import cn.cuit.mapper.ReplyMapper;
import cn.cuit.resultAPI.Result;
import cn.cuit.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentsService {
	@Autowired
	private CommentsMapper commentsMapper;

	@Autowired
	private ReplyMapper replyMapper;

	public void setCommentsMapper(CommentsMapper commentsMapper) {
		this.commentsMapper = commentsMapper;
	}

	@Override
	public Result<Comments> addNewCommet(Comments comments) {
		return Result.success(commentsMapper.insertNew(comments)+"增加留言成功");
	}

	@Override
	public Result<Comments> deleteComment(int id) {
		return Result.success(commentsMapper.deleteById(id)+"删除留言成功");
	}

	@Override
	public Result<Comments> updateComment(Comments comments) {
		return Result.success(commentsMapper.updateInfo(comments)+"");
	}

	@Override
	public Result<List<Comments>> queryById(int id) {
		List<Comments> complaints = commentsMapper.selectByUid(id);
		for (Comments c :
				complaints) {
			List<Reply> replies = replyMapper.selectByPid(c.getId());
			if (replies != null){
				c.setReplies(replies);
			}
		}
		return Result.success(complaints);
//		return Result.success(commentsMapper.selectByUid(id));
	}

	@Override
	public Result<List<Comments>> queryAll() {
		return Result.success(commentsMapper.selectListComm());
//		使用nacos注册中新和xxxx框架向user-service服务发请求
//		return null;
	}
}
