package cn.cuit.service;


import cn.cuit.entity.User;
import cn.cuit.resultAPI.Result;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface UserService {
	Result<User> addUser(User user);
	Result<User> deleteUser(int id);
	Result<User> updateUser(User user);
	Result<User> updateUserPwd(String pwd,int id);
	Result<User> queryUserById(int id);
	Result<User> queryUserByName(String fullName);
	Result<List<User>> queryAllUser();
}
