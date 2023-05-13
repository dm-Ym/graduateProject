package cn.cuit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_reply")
public class Reply {
	private int id;
	private int pid;
	private int uid;
	private String reName;
	private String reContent;
	private String reTime;
	private boolean showText;
	private int toUid;
}
