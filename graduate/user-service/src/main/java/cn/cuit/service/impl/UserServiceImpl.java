package cn.cuit.service.impl;

import cn.cuit.entity.User;
import cn.cuit.mapper.UserMapper;
import cn.cuit.resultAPI.Result;
import cn.cuit.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.constraints.NotNull;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public Result<User> addUser(@NotNull User user) {
		//todo md5
//		user.setPwd(DigestUtils.md5Hex(user.getPwd()));
		User insert = userMapper.selectByPhone(user.getPhone());
		if (insert != null){
			Result.failed("已经存在用户"+user.getFullName());
		}
		try {
			userMapper.insertNewUser(user);
		} catch (Exception e) {
			if (e.getCause() instanceof SQLIntegrityConstraintViolationException){
				return Result.failed("错误!电话重复,请仔细检查.");
			}
		}
		return Result.success("新增用户成功");
	}

	@Override
	public Result<User> deleteUser(int id) {
		if (userMapper.deleteById(id) == 0){
			Result.failed("用户不存在");
		}
		return Result.success("删除用户成功");
	}

	@Override
	public Result<User> updateUser(User user) {
		userMapper.updateInfo(user);
		return Result.success(userMapper.selectById(user.getUserId()),"更新信息成功");
	}

	@Override
	public Result<User> updateUserPwd(String pwd,int id) {
		if (userMapper.updateUserPwd(pwd,id) == 0){
			Result.failed("failed");
		}
		return Result.success("success");
	}

	@Override
	public Result<User> queryUserById(int id) {
		return Result.success(userMapper.selectById(id));
	}

//	@Override
//	public Result<User> queryUserByName(String fullName) {
//		return Result.success(userMapper.selectByName(fullName));
//	}

	@Override
	public Result<List<User>> queryAllUser() {
		return Result.success(userMapper.selectAllUser());
	}
}
