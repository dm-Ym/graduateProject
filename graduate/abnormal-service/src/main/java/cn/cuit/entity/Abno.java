package cn.cuit.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_abno")
public class Abno {
	private int id;
	private String abnoName;
	private double temperature;
	private String otherSymptom;
	private String risk;
	private String location;
	private String addTime;
	private String healTime;
	private int status;
}
