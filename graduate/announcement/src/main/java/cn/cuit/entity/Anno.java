package cn.cuit.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_announcement")
public class Anno {
	private int id;
	private String title;
	private String content;
	private String createName;
	private String createTime;
	private String deleteName;
	private String deleteTime;
	private int status;
}
