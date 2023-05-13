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
@TableName("t_trip")
public class Trip {
	private int id;
	private int uid;
	private String tripName;
	private String origin;
	private String end;
	private String startTime;
	private String endTime;
	private String transportation;
}
