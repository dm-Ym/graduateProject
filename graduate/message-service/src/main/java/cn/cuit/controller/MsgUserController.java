package cn.cuit.controller;

import cn.cuit.entity.Msg;
import cn.cuit.resultAPI.Result;
import cn.cuit.service.MsgService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/msguser")
@RequiresRoles("user")
public class MsgUserController {
	@Autowired
	private MsgService msgService;

	@PostMapping
	public Result<Msg> createNewMsg(@RequestBody Msg msg){
		return msgService.addMsg(msg);
	}
	@DeleteMapping("/{id}")
	public Result<Msg> deleteMsg(@PathVariable int id){
		return msgService.deleteMsg(id);
	}
//	@PutMapping
//	public Result<Msg> updateMsg(@RequestBody Msg msg){
//		return msgService.updateUser(msg);
//	}
	@GetMapping("/{uid}")
	public Result<List<Msg>> findMsgById(@PathVariable("uid") int uid){
		return msgService.queryById(uid);
	}
}
