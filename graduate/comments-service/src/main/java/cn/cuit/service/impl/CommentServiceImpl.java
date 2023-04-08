package cn.cuit.service.impl;

import cn.cuit.entity.Comments;
import cn.cuit.entity.User;
import cn.cuit.mapper.CommentsMapper;
import cn.cuit.resultAPI.Result;
import cn.cuit.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentsService {
	@Autowired
	private CommentsMapper commentsMapper;

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
	public Result<Comments> queryById(int id) {
		return Result.success(commentsMapper.selectById(id));
	}

	@Override
	public Result<List<Comments>> queryAll() {
		return Result.success(commentsMapper.selectList(null));
//		使用nacos注册中新和xxxx框架向user-service服务发请求
//		return null;
	}
}
