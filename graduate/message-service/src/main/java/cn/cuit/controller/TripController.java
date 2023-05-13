package cn.cuit.controller;


import cn.cuit.entity.Trip;
import cn.cuit.mapper.TripMapper;
import cn.cuit.resultAPI.Result;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
//@RequiresRoles("user")
public class TripController {
	@Autowired
	private TripMapper tripMapper;

	@PostMapping
	public Result createNewTrip(@RequestBody Trip trip){
		return Result.success(tripMapper.insert(trip));
	}
	@DeleteMapping("/{id}")
	public Result deleteTrip(@PathVariable int id){
		return Result.success(tripMapper.deleteById(id));
	}

	@GetMapping
	public Result getAll() {
		return Result.success(tripMapper.selectList(null));
	}

	/**
	 * 用户查询自己的行程
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Result<List<Trip>> findTripById(@PathVariable("id") int id){
		return Result.success(tripMapper.selectTripList(id));
	}
}
