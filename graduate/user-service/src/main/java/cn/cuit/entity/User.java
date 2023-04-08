package cn.cuit.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(value = "pwd",allowSetters = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
public class User {
	@TableId("user_id")  // 定义主键名称
	private int userId;
	private String fullName;
	private String pwd;
	@Min(value = 0,message = "年龄不能低于0岁")
	@Max(value = 120,message = "年龄不能超过120岁")
	private int age;
	private String gender;
	@Size(min = 11,max = 11,message = "电话长度不合法,必须等于11位")
	private String phone;
	private String neighborhood;
	private String community;
	private String residential;
	private String doorplate;
	private String roles;
	private String permissions;
}
