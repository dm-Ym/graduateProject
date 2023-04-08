package cn.cuit.controller;

import cn.cuit.entity.Comments;
import cn.cuit.resultAPI.Result;
import cn.cuit.service.CommentsService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comadmin")
@RequiresRoles(value = {"admin","super"},logical = Logical.OR)
public class AdminComController {
	@Autowired
	private CommentsService commentsService;

	@PostMapping
	public Result<Comments> createNewCom(@RequestBody Comments comments){
		return commentsService.addNewCommet(comments);
	}

	@DeleteMapping("/{id}")
	public Result<Comments> deleteCom(@PathVariable int id){
		return commentsService.deleteComment(id);
	}

//	@PutMapping
//	public Result<Comments> updateCom(@RequestBody Comments comments){
//		return commentsService.updateComment(comments);
//	}

	@GetMapping("/{id}")
	public Result<Comments> findById(@PathVariable int id){
		return commentsService.queryById(id);
	}

	/**
	 * 用id去调有留言的用户
	 */
	@GetMapping
	public Result<List<Comments>> findAll(){
		return commentsService.queryAll();
	}
}
