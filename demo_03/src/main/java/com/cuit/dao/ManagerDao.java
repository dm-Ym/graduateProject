package com.cuit.dao;

import com.cuit.pojo.Manager;
import com.cuit.pojo.Notice;
import com.cuit.pojo.ResidentUser;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface ManagerDao {

//    @Select("select * from graduation.tb_manager where man_number=#{id};")
    Manager getMan(@Param("id") String id);
    /**
     *  管理员管理
     *
     */
    // 管理员新增
    @Insert("insert into graduation.tb_manager (man_number, man_name, pwd) " +
            "VALUES (#{manNumber},#{manName},#{pwd})")
    int addMan(Manager manager);

    // 管理员查询
    @Select("select * from graduation.tb_manager where man_number=#{manNumber};")
    Manager findMan(String manNumber);

//    // 新增
//    @Insert("insert into graduation.tb_manager (man_number, man_name, pwd) VALUES " +
//            "(#{manNumber},#{manName},#{pwd})")
//    int add(Manager manager);

    // 管理员更改自己的信息
    @Update("update graduation.tb_manager set man_number=#{manNumber},man_name=#{manName},pwd=#{pwd}")
    int updateMan(Manager manager);

    /**
     * 公告管理
     */

    @Insert("insert into graduation.tb_notice (notice) values (notice)")
    int addNotice(String notice);

    @Delete("delete from graduation.tb_notice where notice_id=#{id}")
    int removeNotice(@Param("id") int id);

    // 更新
    @Update("update graduation.tb_notice set notice=#{notice} where notice_id=#{noticeId}")
    int updateNotice(Notice notice);

    @Select("select * from graduation.tb_notice")
    Notice getLatest();


    /**
     *
     *  对普通用户的管理
     */

    // 增加普通用户
    @Insert("insert into graduation.tb_user (full_name, gender, age, phone_number, pwd, community, community_name, detailed_address, health_status) " +
            "VALUES (#{fullName},#{gender},#{age},#{phoneNumber},#{pwd},#{community},#{communityName},#{detailedAddress},#{healthStatus})")
    int addUser(ResidentUser residentUser);

    // 管理员移除用户 : 后续可追加功能：移除的人。不是被移除的人
    @Delete("delete from graduation.tb_user where resident_user_id=#{id};")
    int removeById(@Param("id") int id);

    // 管理员没有修改用户的权限：防人乱搞，增加麻烦

    // 查询所有用户
    @Select("select * from graduation.tb_user")
    List<ResidentUser> getAllUser();

    // 查普通用户:通过用户电话
    @Select("select * from graduation.tb_user where resident_user_id=#{id}")
    ResidentUser getById(@Param("id") int id);


    // 查普通用户：通过用户名
    @Select("select * from graduation.tb_user where full_name=#{fullName};")
    ResidentUser getByName(@Param("fullName") String fullName);
}
