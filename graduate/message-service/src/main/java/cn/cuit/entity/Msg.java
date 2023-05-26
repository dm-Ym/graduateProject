package cn.cuit.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_message")
public class Msg {
	private int id;
	private int uid;
	private String msgName;
	private double temperature;
	private String health;
	private String suspected;
	private String symptom;
	private String location;
	private String risk;
	private String remark;
	private String destination;
	private String content;
	private String start;
	private String end;
}
