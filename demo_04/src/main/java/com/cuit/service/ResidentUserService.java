package com.cuit.service;

import com.cuit.pojo.ResidentUser;
import com.cuit.resultAPI.CommonResult;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public interface ResidentUserService {
    CommonResult<ResidentUser> register(ResidentUser residentUser);
    ResidentUser login(ResidentUser residentUser);
    CommonResult<ResidentUser> isLogin(HttpSession session);
    CommonResult<ResidentUser> saveUser(ResidentUser residentUser);

//    CommonResult<ResidentUser> removeById(ResidentUser residentUser);
    CommonResult<ResidentUser> removeById(int id);
    CommonResult<ResidentUser> updateUser(ResidentUser residentUser);
    List<ResidentUser> getAllUser();
    CommonResult<ResidentUser> getById(int id);
    ResidentUser getByName(String fullName);
}
