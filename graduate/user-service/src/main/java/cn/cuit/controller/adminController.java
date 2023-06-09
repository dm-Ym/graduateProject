package cn.cuit.controller;

import cn.cuit.entity.User;
import cn.cuit.mapper.UserMapper;
import cn.cuit.resultAPI.Result;
import cn.cuit.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequiresRoles(value = {"admin","super"},logical = Logical.OR)
@RequestMapping("/admin")
public class adminController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	/**
	 * 查询全部用户: 分社区,小区,楼宇查看用户
	 * 目前只有查寻全部
	 */
	@GetMapping
	public Result<List<User>> findAllUser(){
		return userService.queryAllUser();
	}

	/**
	 * 查看用户详情
	 */
	@GetMapping("/{phone}")
	public Result<User> findByPhone(@PathVariable String phone){
		return Result.success(userMapper.selectByPhone(phone));
	}
	@GetMapping("/search/{str}")
	public Result<List<User>> findByName(@PathVariable String str){
		return Result.success(userMapper.selectByName(str));
	}

	/**
	 * 修改用户密码
	 */
	@PutMapping
	public Result<User> updatePwd(@RequestBody User user){
		// todo md5加密密码,测试接口先注释掉
//		user.setPwd(DigestUtils.md5Hex(user.getPwd()));
		return userService.updateUserPwd(user.getPwd(),user.getUserId());
	}

	/**
	 *  新增用户
	 */
	@PostMapping
	public Result<User> createNewUser(@RequestBody User user){
		return userService.addUser(user);
	}

	/**
	 * 删除用户 : 后面加一个删除人
	 */
	@DeleteMapping("/{id}")
	public Result<User> deleteUser(@PathVariable int id){
		return userService.deleteUser(id);
	}

	/**
	 * 用户行程信息
	 */
/*	@GetMapping("/{id}")
	public Result findXingcheng(@PathVariable int id){
		return null;
	}*/
}
