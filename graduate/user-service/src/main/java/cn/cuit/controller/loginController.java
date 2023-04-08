package cn.cuit.controller;

import cn.cuit.entity.User;
import cn.cuit.jwt.JWTUtil;
import cn.cuit.mapper.UserMapper;
import cn.cuit.resultAPI.Result;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

@RestController
public class loginController {
	@Autowired
	private UserMapper userMapper;

	/**
	 * 提交电话、密码、权限
	 * @param user
	 * @return
	 */
	@PostMapping("/login")
	public Result<String> login(@RequestBody User user){
		User toUser = userMapper.login(user.getPhone(),user.getRoles());
		if (toUser == null){
			return Result.failed("用户名不存在");
		}
		if (!user.getPwd().equals(toUser.getPwd())){
			return Result.failed("密码错误");
		}
		String token= null;
		try {
			token = JWTUtil.createToken(user.getPhone());
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return Result.success(token,"登录成功");
	}

	@PostMapping("/register")
	public Result<User> register(@RequestBody @Valid User user, BindingResult errors){
		if (errors.hasErrors()) {
			return Result.failed(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
		}
		user.setPwd(DigestUtils.md5Hex(user.getPwd()));
		int insert = userMapper.insertNewUser(user);
		return Result.success("注册成功");
	}
}
