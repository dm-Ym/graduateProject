package com.cuit.service.Impl;

import com.cuit.dao.ManagerDao;
import com.cuit.pojo.Manager;
import com.cuit.pojo.Notice;
import com.cuit.pojo.ResidentUser;
import com.cuit.resultAPI.CommonResult;
import com.cuit.service.ManagerService;
import com.cuit.utils.ClassExamine;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    @Override
    public CommonResult<Manager> findMan(String id) {
        return CommonResult.success(managerDao.getMan(id));
    }
    /**
     * 管理员
     *
     */
    @Override
    public CommonResult<Manager> register(Manager manager) {
        /**
         *  1. 去数据库查询是否已经存在用户
         *  2. 对密码加密
         *  3. 存入数据库
         */
        // 1.
        Manager getMan = managerDao.findMan(manager.getManNumber());
        if (getMan != null){
            return CommonResult.failed("用户已存在，请直接登录!");
        }
//        2.
        manager.setPwd(DigestUtils.md5Hex(manager.getPwd()));
//        3.
        managerDao.addMan(manager);
        return CommonResult.success("管理员注册成功");
    }

    @Override
    public CommonResult<Manager> login(Manager manager) {
        /**
         *  1. 查找是否存在管理员
         *  2. 比较密码
         *  3. 登录成功
         */
        // 1.
        Manager man = managerDao.findMan(manager.getManNumber());
        if (man == null){
            return CommonResult.failed("管理员不存在");
        }
        // 2.
        if (!man.getPwd().equals(DigestUtils.md5Hex(manager.getPwd()))){
            return CommonResult.failed("密码错误");
        }
        // 3.
        return CommonResult.success("登录成功");
    }

    @Override
    public CommonResult<Manager> isLogin(HttpSession session) {
        return null;
    }

    @Override
    public CommonResult<Manager> update(Manager manager) {
        // 从数据库拿已存数据
        Manager man = managerDao.findMan(manager.getManNumber());
        // 对密码加密
        // 判断密码是否为空
        if (!StringUtils.isEmpty(manager.getPwd())){
            manager.setPwd(DigestUtils.md5Hex(manager.getPwd()));
        }
        // 对象互补
        try {
            ClassExamine.objectOverlap(manager,man);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 存入数据库
        managerDao.updateMan(manager);
        return CommonResult.success(manager,"修改成功");
    }




    /**
     * 用户管理
     *
     */

    @Override
    public CommonResult<ResidentUser> addUser(ResidentUser residentUser) {
        /**
         *  1. 判断用户是否存在
         *  2. 新增
         */
        // 1.
        ResidentUser getUser = managerDao.getById(residentUser.getResidentUserId());
        if (getUser != null){
            return CommonResult.failed("用户已存在！新增用户失败！");
        }
        managerDao.addUser(residentUser);
        return CommonResult.success("新增用户成功");
    }

    @Override
    public CommonResult<ResidentUser> removeByNum(int id) {
        /**
         *  1. 查询用户是否存在
         *  2. 删除
         *  3. 完成
         */
        // 1.
        ResidentUser getUser = managerDao.getById(id);
        if (getUser == null){
            return CommonResult.failed("用户不存在");
        }
        managerDao.removeById(id);
        return CommonResult.success("删除用户"+getUser.getFullName()+"成功");
    }

    @Override
    public CommonResult<ResidentUser> getById(int id) {
        ResidentUser getUser = managerDao.getById(id);
        if (getUser == null){
            return CommonResult.failed("没有该用户,查询失败");
        }
        return CommonResult.success(getUser,"查询用户成功");
    }

    @Override
    public CommonResult<ResidentUser> getByName(ResidentUser residentUser) {
        ResidentUser getUser = managerDao.getByName(residentUser.getFullName());
        if (getUser == null){
            return CommonResult.failed("没有该用户"+residentUser+", 查询失败！");
        }
        return CommonResult.success(getUser,"查询成功!");
    }

    @Override
    public CommonResult<List<ResidentUser>>  getAllUser() {
        List<ResidentUser> getAllUser = managerDao.getAllUser();
        if (getAllUser ==null){
            return CommonResult.failed("没有用户存在");
        }
        return CommonResult.success(getAllUser,"查询所有用户成功");
    }


    /**
     *
     * 公告管理
     *
     */

    @Override
    public CommonResult<Notice> addNotice(Notice notice) {
        managerDao.addNotice(notice.getNotice());
        return CommonResult.success("添加公告成功");
    }

    @Override
    public CommonResult<Notice> removeNotice(Notice notice) {
        managerDao.removeNotice(notice.getNoticeId());
        return CommonResult.success("删除公告成功");
    }

    @Override
    public CommonResult<Notice> updateNotice(Notice notice) {
        managerDao.updateNotice(notice);
        return CommonResult.success(notice,"更新公告成功");
    }


    // 先不用管
    @Override
    public CommonResult<Notice> getNoticeLastest(Notice notice) {
        return CommonResult.success(managerDao.getLatest());
    }
}
