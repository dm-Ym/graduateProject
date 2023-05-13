package cn.cuit.mapper;

import cn.cuit.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
	int updateInfo(User user);
//	List<User> queryAllUser();

	@Update("update graduation.t_user set pwd=#{pwd} where user_id=#{id} and roles='user' ")
	int updateUserPwd(@Param("pwd") String pwd,@Param("id")int id);

	@Select("select * from t_user where full_name=#{str} or phone=#{str}")
	List<User> selectByName(String str);

	@Insert("insert into graduation.t_user (full_name,pwd,age,gender,phone,neighborhood,community,residential,doorplate,roles,permissions)" +
			"values (#{fullName},#{pwd},#{age},#{gender},#{phone},#{neighborhood},#{community},#{residential},#{doorplate},'user','select')")
	int insertNewUser(User user);

	@Select("select * from t_user where phone=#{phone}")
	User selectByPhone(@Param("phone") String phone);

	@Select("select * from t_user where phone=#{phone} and roles=#{roles}")
	User login(@Param("phone") String phone,@Param("roles") String roles);


	@Select("select * from t_user where roles='user'")
	List<User> selectAllUser();
	
/*  查询用户,不查admin
	@Select("select * from t_user where user_id=#{id} and roles=#{}")
	User selectByUserIdUser(@Param("id") int id);
*/
}
