package cn.cuit.service.impl;

import cn.cuit.entity.Msg;
import cn.cuit.mapper.MsgMapper;
import cn.cuit.resultAPI.Result;
import cn.cuit.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MsgServiceImpl implements MsgService {

	@Autowired
	private MsgMapper msgMapper;

	public void setMsgMapper(MsgMapper msgMapper) {
		this.msgMapper = msgMapper;
	}

	@Override
	public Result<Msg> addMsg(Msg msg) {
		return Result.success(msgMapper.insertNewMsg(msg)+"信息上报成功");
	}

	@Override
	public Result<Msg> deleteMsg(int id) {
		return Result.success(msgMapper.deleteById(id)+"删除信息成功");
	}

//	@Override
//	public Result<Msg> updateUser(Msg msg) {
//		return Result.success(msgMapper.updateMsg(msg)+"");
//	}

	@Override
	public Result<List<Msg>> queryById(int id) {
		return Result.success(msgMapper.selectByIdMsg(id));
	}

	/**
	 * 感觉没必要要这个, 查询全部用户,然后看每个用户详情便可.
	 * @return
	 */
	@Override
	public Result<List<Msg>> queryAllMsg() {
		return Result.success(msgMapper.selectListOrder());
	}
}
