package cn.cuit.service;

import cn.cuit.entity.Msg;
import cn.cuit.resultAPI.Result;

import java.util.List;

public interface MsgService {

	Result<Msg> addMsg(Msg msg);
	Result<Msg> deleteMsg(int id);
//	Result<Msg> updateUser(Msg msg);
	Result<List<Msg>> queryById(int id);
	Result<List<Msg>> queryAllMsg();
}
