package com.cuit.controller;

import com.cuit.pojo.Manager;
import com.cuit.pojo.Notice;
import com.cuit.pojo.ResidentUser;
import com.cuit.resultAPI.CommonResult;
import com.cuit.service.ManagerService;
import jdk.internal.org.objectweb.asm.util.TraceSignatureVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 字段校验和Session校验全交给拦截器
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {
    public static final String SESSION_MAN_NAME="manInfo";
    @Autowired
    private ManagerService managerService;


    @GetMapping("/{id}")
    public CommonResult<Manager> findMan(@PathVariable String id){
        return managerService.findMan(id);
    }

    @PostMapping("/register")
    public CommonResult<Manager> register(@RequestBody Manager residentUser){
        return managerService.register(residentUser);
    }

    @PostMapping("/login")
    public CommonResult<Manager> login(@RequestBody  Manager residentUser, HttpServletRequest request){
        CommonResult<Manager> result = managerService.login(residentUser);
        // 判断是否登录成功，然后设置session
        if (result.isSuccess()){
            request.getSession().setAttribute(SESSION_MAN_NAME,result.getData());
        }
        return result;
    }

    public CommonResult<Manager> isLogin(HttpServletRequest request){
        return managerService.isLogin(request.getSession());
    }

    // 更新信息
    @PutMapping("/mman")
    public CommonResult<Manager> updateManage(@RequestBody Manager manager){
        return managerService.update(manager);
    }


    /**
     *  用户管理
     *
     */

    @PostMapping("/uadd")
    public CommonResult<ResidentUser> addUser(@RequestBody ResidentUser residentUser){
        return managerService.addUser(residentUser);
    }


    @DeleteMapping("/urm/{id}")
    public CommonResult<ResidentUser> deleteUserById(@PathVariable int id){
        return managerService.removeByNum(id);
    }

    @GetMapping("/uid/{id}")
    public CommonResult<ResidentUser> findById(@PathVariable int id){
        return managerService.getById(id);
    }

    @GetMapping("/uname")
    public CommonResult<ResidentUser> findByName(@RequestBody ResidentUser residentUser){
        return managerService.getByName(residentUser);
    }

    @GetMapping("/users")
    public CommonResult<List<ResidentUser>> findAll(){
        return managerService.getAllUser();
    }


    /**
     * 通知管理
     */
    @PostMapping("/nadd")
    public CommonResult<Notice> addNotice(@RequestBody Notice notice){
        return managerService.addNotice(notice);
    }

    @DeleteMapping("/nrm")
    public CommonResult<Notice> deleteNotice(@PathVariable Notice notice){
        return managerService.removeNotice(notice);
    }

    @PutMapping("/uman")
    public CommonResult<Notice> updateNotice(@RequestBody Notice notice){
        return managerService.updateNotice(notice);
    }
}
