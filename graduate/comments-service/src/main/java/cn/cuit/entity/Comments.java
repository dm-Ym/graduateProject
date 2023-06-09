package cn.cuit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_comments")
public class Comments {
	private int id;
	private int uid;
	private int pid;
	private String comName;
	private String content;
	private String createTime;
	private int status;
	private List<Reply> replies;
}
