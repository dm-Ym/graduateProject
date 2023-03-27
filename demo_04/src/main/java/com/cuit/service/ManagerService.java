package com.cuit.service;

import com.cuit.pojo.Manager;
import com.cuit.pojo.Notice;
import com.cuit.pojo.ResidentUser;
import com.cuit.resultAPI.CommonResult;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public interface ManagerService {
    CommonResult<Manager> findMan(String id);
    /**
     * 管理员
     *
     */
    // 使用add方法新增用户
    CommonResult<Manager> register(Manager manager);
    // get
    CommonResult<Manager> login(Manager manager);
    // get
    CommonResult<Manager> isLogin(HttpSession session);

//    // 新增管理员  : 好像就是注册管理员？但是管理员应该没有新增管理员的权限
//    CommonResult<Manager> saveManager(Manager manager);

    // 更改信息
    CommonResult<Manager> update(Manager manager);

    /**
     * 用户管理
     *
     */

    // 增加普通用户
    CommonResult<ResidentUser> addUser(ResidentUser residentUser);
    // 删除用户
    CommonResult<ResidentUser> removeByNum(int id);

    // 管理员不能修改用户信息

    // 通过电话（主键）获取用户信息
    CommonResult<ResidentUser> getById(int id);
    // 通过姓名获取用户信息，用于"搜索"功能
    CommonResult<ResidentUser> getByName(ResidentUser residentUser);
    // 查询所有用户
   CommonResult<List<ResidentUser>>  getAllUser();

    /**
     * 通知管理
     *
     */
    // 新增公告
    CommonResult<Notice> addNotice(Notice notice);
    // 删除公告
    CommonResult<Notice> removeNotice(Notice notice);
    // 更新
    CommonResult<Notice> updateNotice(Notice notice);
    // 获取最新公告 : 要么获取最后一条，要么都给传到前端。
    CommonResult<Notice> getNoticeLastest(Notice notice);
}

