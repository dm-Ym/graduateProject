package cn.cuit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_complaints")
public class Complaints {
	private int id;
	private int uid;
	private String coName;
	private String coContent;
	private String coTime;
	private int status;
	private List<Reply> replies;
}
