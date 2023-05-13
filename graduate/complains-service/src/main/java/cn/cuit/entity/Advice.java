package cn.cuit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_advice")
public class Advice {
	private int	id;
	private int	uid;
	private String adTime;
	private String adContent;
	private int	status;
}
