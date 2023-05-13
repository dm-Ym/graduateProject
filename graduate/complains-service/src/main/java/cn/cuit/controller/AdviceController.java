package cn.cuit.controller;

import cn.cuit.entity.Advice;
import cn.cuit.entity.Reply;
import cn.cuit.mapper.AdviceMapper;
import cn.cuit.resultAPI.Result;
import cn.cuit.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advice")
public class AdviceController {
	@Autowired
	private AdviceMapper adviceMapper;
	@GetMapping
	public Result<List<Advice>> findAll(){
		return Result.success(adviceMapper.queryAll());
	}

	@GetMapping("/one/{uid}")
	public Result<List<Advice>> findOne(@PathVariable int uid){
		return Result.success(adviceMapper.queryOneOfIt(uid));
	}

	@PostMapping
	public Result createNewCom(@RequestBody Advice advice){
		return Result.success(adviceMapper.insert(advice));
	}

	@DeleteMapping
	public Result deleteAdvice(@RequestBody Advice advice){
		return Result.success(adviceMapper.deleteByUser(advice));
	}

	@PutMapping
	public Result updateStatus(@RequestBody Advice advice){
		return Result.success(adviceMapper.updateByAdmin(advice));
	}
	@PutMapping("/status")
	public Result updateStatusAdmin(@RequestBody Advice advice){
		return Result.success(adviceMapper.updateByAdminStatus(advice));
	}

	@DeleteMapping("/{id}")
	public Result deleteAdAdmin(@PathVariable int id ){
		return Result.success(adviceMapper.deleteByAdmin(id));
	}
}
