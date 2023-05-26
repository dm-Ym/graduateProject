package cn.cuit.controller;

import cn.cuit.entity.Abno;
import cn.cuit.mapper.AbnoMapper;
import cn.cuit.resultAPI.Result;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequiresRoles(value = {"admin","super"},logical = Logical.OR)
public class AbnoController {
	@Autowired
	private AbnoMapper abnoMapper;

	@PostMapping("/abno")
	public Result addAbno(@RequestBody Abno abno){
		return Result.success(abnoMapper.insertAbno(abno));
	}
	@DeleteMapping("/abno/{id}")
	public Result deleteAbno(@PathVariable("id") int id){
		return Result.success(abnoMapper.deleteById(id));
	}
	@PutMapping("/heal")
	public Result updateAbno(@RequestBody Abno abno){
		return Result.success(abnoMapper.updateInfo(abno));
	}

	/**
	 *  查找在病患者
	 */
//	@GetMapping("/abno/{id}")
//	public Result<Abno> queryAbnoById(@PathVariable("id") int id){
//		return Result.success(abnoMapper.selectById(id));
//	}
	@GetMapping("/abno/search/{abnoName}")
	public Result<List<Abno>> queryAbnoByName(@PathVariable("abnoName") String abnoName){
		return Result.success(abnoMapper.selectByName(abnoName));
	}
	@GetMapping("/abno")
	public Result<List<Abno>> queryAllAbno(){
		return Result.success(abnoMapper.selectAbnoList());
	}

	@GetMapping("/abno/today")
	public Result<List<Abno>> queryAbnoToday(){
		return Result.success(abnoMapper.selectAbnoToday());
	}

	@GetMapping("/abno/errtem")
	public Result<List<Abno>> queryErrTem(){
		return Result.success(abnoMapper.selectErrTem());
	}

	@GetMapping("/abno/risk")
	public Result<List<Abno>> queryIsRisk(){
		return Result.success(abnoMapper.selectIsRisk());
	}
	/**
	 *  查找康复患者
	 */
//	@GetMapping("/heal/{id}")
//	public Result<Abno> queryHealById(@PathVariable("id") int id){
//		return Result.success(abnoMapper.selectById(id));
//	}
	@GetMapping("/heal/{abnoName}")
	public Result<List<Abno>> queryHealByName(@PathVariable("abnoName") String abnoName){
		return Result.success(abnoMapper.selectByName(abnoName));
	}
	@GetMapping("/heal")
	public Result<List<Abno>> queryAllHeal(){
		return Result.success(abnoMapper.selectAbnoList());
	}
}
