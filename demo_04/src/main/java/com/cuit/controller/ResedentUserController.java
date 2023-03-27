package com.cuit.controller;

import com.cuit.jwt.JWTUtil;
import com.cuit.pojo.ResidentUser;
import com.cuit.resultAPI.CommonResult;
import com.cuit.service.ResidentUserService;
import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
//@RequiresAuthentication
public class ResedentUserController {

    public static final String SESSION_NAME="userInfo";
    @Autowired
    private ResidentUserService residentUserService;

    @PostMapping("/register")
    public CommonResult<ResidentUser> register(@RequestBody ResidentUser residentUser){
//        // 校验字段
//        if (errors.hasErrors()){
//            return CommonResult.failed(errors.getFieldError().getDefaultMessage());
//        }
        return residentUserService.register(residentUser);
    }

    @PostMapping("/login")
    public CommonResult<Map> login(@RequestBody ResidentUser residentUser, HttpServletRequest request){

//        CommonResult<ResidentUser> result = residentUserService.login(residentUser);
        ResidentUser user = residentUserService.login(residentUser);
        String token= null;
        try {
            token = JWTUtil.createToken(user);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        HashMap<String, Object> result = new HashMap<>();
        result.put("token",token);
//        result.put("data",user);
//        request.getSession().setAttribute(SESSION_NAME,result.getData());
        return CommonResult.success(result,"成功");
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

    @RequiresPermissions("男")
    @GetMapping("/all")
    public CommonResult<List<ResidentUser>> findAllUser(){
        List<ResidentUser> allUser = residentUserService.getAllUser();
        return CommonResult.success(allUser);
    }

    @RequiresPermissions("男")  // 第一种方式，在JWTRealm授权方法中 gender字段
    @GetMapping("/{id}")
    public CommonResult<ResidentUser> getById(@PathVariable int id){
        return residentUserService.getById(id);
    }


    @RequiresRoles("addd")     // fullName字段
    @RequiresAuthentication
    @GetMapping("/fullname")
    public ResidentUser getByName(@RequestBody ResidentUser residentUser){
        return residentUserService.getByName(residentUser.getFullName());
    }

}
