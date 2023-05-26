package cn.cuit.service;

import cn.cuit.entity.Comments;
import cn.cuit.resultAPI.Result;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentsService {
	Result<Comments> addNewCommet(Comments comments);
	Result<Comments> deleteComment(int id);
	Result<Comments> updateComment(Comments comments);
	Result<List<Comments>> queryById(int id);
	Result<List<Comments>> queryAll();
}
