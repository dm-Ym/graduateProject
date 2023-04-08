package cn.cuit.controller;

import cn.cuit.entity.User;
import cn.cuit.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {
	@Autowired
	private UserMapper userMapper;

	@GetMapping("/{phone}")
	public User selectByPhone(@PathVariable String phone){
		return userMapper.selectByPhone(phone);
	}
}
