package cn.cuit.controller;

import cn.cuit.entity.Msg;
import cn.cuit.resultAPI.Result;
import cn.cuit.service.MsgService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/msgadmin")
//@RequiresRoles("admin")
public class MsgAdminController {

	@Autowired
	private MsgService msgService;

//	@PostMapping
//	public Result<Msg> createNewMsg(@RequestBody Msg msg){
//		return msgService.addUser(msg);
//	}
//	@DeleteMapping("/{id}")
//	public Result<Msg> deleteMsg(@PathVariable int id){
//		return msgService.deleteUser(id);
//	}
//	@PutMapping
//	public Result<Msg> updateMsg(@RequestBody Msg msg){
//		return msgService.updateUser(msg);
//	}
	@GetMapping("/{id}")
	public Result<List<Msg>> findMsgById(@PathVariable int id){
		return msgService.queryById(id);
	}
	@GetMapping
	public Result<List<Msg>> findAllMsg(){
		return msgService.queryAllMsg();
	}
}
