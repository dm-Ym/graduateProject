package cn.cuit.service;

import cn.cuit.entity.Reply;
import cn.cuit.resultAPI.Result;
import org.springframework.stereotype.Component;

@Component
public interface ReplyService {
	/**
	 * 用户新增投诉
	 */
	Result<Reply> addNewReply(Reply reply);

	/**
	 *  写错了,改一下.
	 *  同一
	 */
//	Result<Reply> updateReply(Reply reply);

//	Result<Reply> queryAll();
	Result<Reply> deleteReply(int id);
}
