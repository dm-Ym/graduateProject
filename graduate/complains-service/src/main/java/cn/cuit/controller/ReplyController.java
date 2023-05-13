package cn.cuit.controller;

import cn.cuit.entity.Reply;
import cn.cuit.resultAPI.Result;
import cn.cuit.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replies")
public class ReplyController {
	@Autowired
	private ReplyService replyService;

	@GetMapping
	public Result<List<Reply>> findAll(){
		return replyService.queryAll();
	}

	@PostMapping
	public Result<Reply> createNewCom(@RequestBody Reply reply){
		return replyService.addNewReply(reply);
	}

	@DeleteMapping("/{id}")
	public Result<Reply> deleteCom(@PathVariable int id){
		return replyService.deleteReply(id);
	}
}
