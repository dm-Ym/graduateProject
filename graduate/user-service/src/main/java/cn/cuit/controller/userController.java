package cn.cuit.controller;

import cn.cuit.entity.User;
import cn.cuit.resultAPI.Result;
import cn.cuit.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RequiresRoles(value = {"user","super"},logical = Logical.OR)
@RestController
public class userController {
	@Autowired
	private UserService userService;

	/**
	 * 更新个人信息
	 */
	@PutMapping("/user")
	public Result<User> updateInfo(@RequestBody @Valid User user, BindingResult errors){
		if (errors.hasErrors()){
			return Result.failed(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
		}
		return userService.updateUser(user);
	}

	/**
	 * 查看个人详情: 用户表+健康表
	 * 暂定,草
	 */
	@GetMapping("/user/{id}")
	public Result<User> queryById(@PathVariable int id){
		return userService.queryUserById(id);
	}
}
