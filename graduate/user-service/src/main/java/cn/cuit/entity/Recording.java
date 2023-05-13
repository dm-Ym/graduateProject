package cn.cuit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_recording")
public class Recording {
	private int id;
	private String uid;
	private String userName;
	private String manufacturers;
	private String types;
	private String finish;
	private String createTime;
}
