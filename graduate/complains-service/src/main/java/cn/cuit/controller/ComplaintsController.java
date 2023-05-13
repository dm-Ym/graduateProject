package cn.cuit.controller;

import cn.cuit.entity.Complaints;
import cn.cuit.mapper.ComplaintsMapper;
import cn.cuit.resultAPI.Result;
import cn.cuit.service.ComplaintsService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletionService;

@RestController
@RequestMapping("/complaints")
public class ComplaintsController {
	@Autowired
	private ComplaintsService complaintsService;

	@Autowired
	private ComplaintsMapper complaintsMapper;

	@GetMapping
	public Result<List<Complaints>> findAll(){
		return complaintsService.queryAll();
	}

	@GetMapping("/one/{uid}")
	public Result<List<Complaints>> findOneOfThem(@PathVariable int uid){
		return Result.success(complaintsMapper.queryOneOfThem(uid));
	}

	/***
	 * 不给游客新增投诉的页面便可
	 * @param complaints
	 * @return
	 */
	@PostMapping
	public Result<Complaints> createNewCom(@RequestBody Complaints complaints){
		return complaintsService.addNewComplaints(complaints);
	}

	@DeleteMapping("/{id}")
	public Result<Complaints> deleteCom(@PathVariable int id){
		return complaintsService.deleteComplaints(id);
	}

	@DeleteMapping("/one")
	public Result deleteComOne(@RequestBody Complaints complaints){
		return Result.success(complaintsMapper.deleteCompOne(complaints));
	}
}
