package com.cuit.service.Impl;

import com.cuit.controller.ResedentUserController;
import com.cuit.dao.ResidentUserDao;
import com.cuit.pojo.ResidentUser;
import com.cuit.resultAPI.CommonResult;
import com.cuit.service.ResidentUserService;
import com.cuit.utils.ClassExamine;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Service
public class ResidentUserServiceImpl implements ResidentUserService {
    @Autowired
    private ResidentUserDao residentUserDao;

    public void setResidentUserDao(ResidentUserDao residentUserDao) {
        this.residentUserDao = residentUserDao;
    }

    /**
     * 注册
     * @param residentUser
     * @return
     */
    @Override
    public CommonResult<ResidentUser> register(ResidentUser residentUser) {
        CommonResult<ResidentUser> result = new CommonResult<>();
        ResidentUser getByName = residentUserDao.getByName(residentUser.getFullName());
        // 判断用户是否存在
        if (getByName != null){
           return result.failed("用户已存在,请直接登录");
        }
        // 加密密码
        residentUser.setPwd(DigestUtils.md5Hex(residentUser.getPwd()));
        residentUserDao.register(residentUser.getFullName(),residentUser.getPwd());
        return result.success(residentUser,"注册用户成功");
    }

    /**
     * 登录
     * @param residentUser
     * @return
     */
    @Override
    public CommonResult<ResidentUser> login(ResidentUser residentUser) {
        // 去数据库找用户名
        ResidentUser getByName = residentUserDao.getByName(residentUser.getFullName());
        if (getByName == null){
           return CommonResult.failed("用户不存在，请先注册");
        }
        // 比较密码
        if (!getByName.getPwd().equals(DigestUtils.md5Hex(residentUser.getPwd()))){
            return CommonResult.failed("用户名或密码错误");
        }
//        residentUserDao.login(residentUser.getFullName(),residentUser.getPwd());
        return CommonResult.success(residentUser,"登录成功");
    }

    @Override
    public CommonResult<ResidentUser> isLogin(HttpSession session) {
        // 从session中取出用户数据
        ResidentUser sessionUser = (ResidentUser) session.getAttribute(ResedentUserController.SESSION_NAME);
        if (sessionUser == null){
            return CommonResult.failed("您尚未登录，请先登录");
        }
        // 将数据与数据库中的数据进行对比
        ResidentUser findUserById = residentUserDao.getById(sessionUser.getResidentUserId());
//        if (findUserById == null || !findUserById.getPwd().equals(Dig))
        return null;
    }

    /**
     * 新增用户
     * @param residentUser
     * @return
     */
    @Override
    public CommonResult<ResidentUser> saveUser(ResidentUser residentUser) {
        ResidentUser findUserById = residentUserDao.getById(residentUser.getResidentUserId());
        if (findUserById != null){
            return CommonResult.failed("尝试添加一个已经存在的用户-"+residentUser.getFullName());
        }
        // 加密密码
        residentUser.setPwd(DigestUtils.md5Hex(residentUser.getPwd()));
        // 存入数据库
        residentUserDao.add(residentUser);
        return CommonResult.success("新增用户成功！恭喜你");
    }

    @Override
    public CommonResult<ResidentUser> removeById(int id) {
        ResidentUser findUserById = residentUserDao.getById(id);
        if (findUserById == null){
            return CommonResult.failed("用户不存在,删除用户行动失败");
        }
        residentUserDao.removeById(id);
        return CommonResult.success("删除用户-"+findUserById.getFullName()+"-成功");
    }

//    @Override
//    public CommonResult<ResidentUser> removeById(ResidentUser residentUser) {
//        ResidentUser findUserById = residentUserDao.getById(residentUser.getResidentUserId());
//        if (findUserById == null){
//            return CommonResult.failed("用户不存在,删除用户行为失败");
//        }
//        residentUserDao.removeById(residentUser.getResidentUserId());
//        return CommonResult.success(residentUser,"删除用户成功成功");
//    }


    /**
     * 可能有问题
     * @param residentUser
     * @return
     */
    @Override
    public CommonResult<ResidentUser> updateUser(ResidentUser residentUser){
        ResidentUser getUser = residentUserDao.getById(residentUser.getResidentUserId());
        if (getUser == null){
            return CommonResult.failed("用户不存在");
        }
        // 对密码加密
        if (!StringUtils.isEmpty(residentUser.getPwd())){
            residentUser.setPwd(DigestUtils.md5Hex(residentUser.getPwd()));
        }
        // 对象互补  抛一个异常
        try {
        ClassExamine.objectOverlap(residentUser,getUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 存入数据库
        residentUserDao.update(residentUser);
        CommonResult<ResidentUser> result = CommonResult.success(residentUser, "修改成功");
        return result;
    }
    @Override
    public List<ResidentUser> getAllUser() {
        List<ResidentUser> allUser = residentUserDao.getAllUser();
        return allUser;
    }

    @Override
    public CommonResult<ResidentUser> getById(int id) {
        ResidentUser getById = residentUserDao.getById(id);
        if (getById == null){
            return CommonResult.failed("未找到用户");
        }
        return CommonResult.success(getById);
    }

    @Override
    public CommonResult<ResidentUser> getByName(String fullName) {
        ResidentUser findByName = residentUserDao.getByName(fullName);
        if (findByName == null){
            return CommonResult.failed("试图寻找数据库中不存在的用户-"+fullName+"-失败");
        }
        return CommonResult.success(findByName,"根据姓名查询用户操作成功");
    }
}
