package com.cuit.dao;

import com.cuit.pojo.ResidentUser;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface ResidentUserDao{

    @Insert("insert into graduation.tb_user (full_name,pwd) " +
            "values (#{fullName},#{pwd})")
    int register(@Param("fullName")String fullName, @Param("pwd")String pwd);

//    @Select("select * from graduation.tb_user where full_name=#{fullName} and pwd=#{pwd}")
//    int login(@Param("fullName")String fullName, @Param("pwd")String pwd);

//    @Results(id = "ResidentUser",value = {
//            @Result(column = "resident_user_id",property = "residentUserId",id = true),
//            @Result(column = "full_name",property = "residentUserId"),
//            @Result(column = "resident_user_id",property = "residentUserId"),
//            @Result(column = "resident_user_id",property = "residentUserId"),
//    })
    @Insert("insert into graduation.tb_user (full_name, gender, age, phone_number, pwd, community, community_name, detailed_address, health_status) " +
            "VALUES (#{fullName},#{gender},#{age},#{phoneNumber},#{pwd},#{community},#{communityName},#{detailedAddress},#{healthStatus})")
    int add(ResidentUser residentUser);

    @Delete("delete from graduation.tb_user where resident_user_id=#{id}")
    int removeById(@Param("id") int id);

    @Update("update graduation.tb_user set full_name=#{fullName},gender=#{gender},age=#{age},phone_number=#{phoneNumber},pwd=#{pwd},community=#{community},community_name=#{communityName},detailed_address=#{detailedAddress},fill_in_time=now(),health_status=#{healthStatus} where resident_user_id=#{residentUserId}")
    int update(ResidentUser residentUser);

    @Select("select * from graduation.tb_user")
    List<ResidentUser>  getAllUser();

    @Select("select * from graduation.tb_user where resident_user_id=#{id}")
    ResidentUser getById(@Param("id") int id);

    @Select("select * from graduation.tb_user where full_name=#{fullName}")
    ResidentUser getByName(@Param("fullName") String fullName);

}
