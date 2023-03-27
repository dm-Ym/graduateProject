package com.cuit.controller;

import com.cuit.pojo.ResidentUser;
import com.cuit.resultAPI.CommonResult;
import com.cuit.service.ResidentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class ResedentUserController {

    public static final String SESSION_NAME="userInfo";
    @Autowired
    private ResidentUserService residentUserService;

    @PostMapping("/register")
    public CommonResult<ResidentUser> register(@RequestBody @Valid ResidentUser residentUser, BindingResult errors){
        // 校验字段
        if (errors.hasErrors()){
            return CommonResult.failed(errors.getFieldError().getDefaultMessage());
        }
        return residentUserService.register(residentUser);
    }

    @PostMapping("/login")
    public CommonResult<ResidentUser> login(@RequestBody @Valid ResidentUser residentUser, BindingResult errors,HttpServletRequest request){
        if (errors.hasErrors()){
            return CommonResult.failed(errors.getFieldError().getDefaultMessage());
        }
        CommonResult<ResidentUser> result = residentUserService.login(residentUser);
        // 如果登录成功，则设定Session
//       if (result.isS){
//       }
        request.getSession().setAttribute(SESSION_NAME,result.getData());
        return result;
    }

    public CommonResult<ResidentUser> isLogin(HttpServletRequest request){
        return residentUserService.isLogin(request.getSession());
    }

    @PostMapping("/add")
    public CommonResult<ResidentUser> addResidentUser(@RequestBody @Valid ResidentUser residentUser, BindingResult errors){
        return residentUserService.saveUser(residentUser);
    }

    @DeleteMapping("/del/{id}")
    public CommonResult<ResidentUser> deleteUserById(@PathVariable int id){
        return residentUserService.removeById(id);
    }

    @PutMapping("/usersupdate")
    public CommonResult<ResidentUser> updateUserMsg(@RequestBody @Valid ResidentUser residentUser, BindingResult errors, HttpServletRequest request){
        if (errors.hasErrors()){
//            System.out.println(errors.getFieldError().getDefaultMessage());
            return CommonResult.failed(errors.getFieldError().getDefaultMessage());
        }
        return residentUserService.updateUser(residentUser);
    }

    @GetMapping("/all")
    public CommonResult<List<ResidentUser>> findAllUser(){
        List<ResidentUser> allUser = residentUserService.getAllUser();
        return CommonResult.success(allUser);
    }

    @GetMapping("/{id}")
    public CommonResult<ResidentUser> getById(@PathVariable int id){
        return residentUserService.getById(id);
    }

    @GetMapping("/fullname")
    public CommonResult<ResidentUser> getByName(@RequestBody ResidentUser residentUser){
        return residentUserService.getByName(residentUser.getFullName());
    }

}
