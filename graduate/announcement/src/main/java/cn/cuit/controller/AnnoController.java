package cn.cuit.controller;

import cn.cuit.entity.Anno;
import cn.cuit.feign.UserFeign;
import cn.cuit.resultAPI.Result;
import cn.cuit.service.AnnoService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anno")
public class AnnoController {
	@Autowired
	private AnnoService annoService;

	@RequiresRoles("admin")
	@PostMapping
	public Result<Anno> createNewAnno(@RequestBody Anno anno){
		return annoService.addNewAnno(anno);
	}

	@RequiresRoles("admin")
	@DeleteMapping
	public Result<Anno> deleteAnno(@RequestBody Anno anno){
		return annoService.deleteAnno(anno);
	}

	@GetMapping
	public Result<List<Anno>> findAll(){
		return annoService.queryAnno();
	}
}
