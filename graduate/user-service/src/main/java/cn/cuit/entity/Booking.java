package cn.cuit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_booking")
public class Booking {
	private int	id;
	private String	hospital;
	private String	vaccine;
	private String	boTime;
	private String	perNum;
}
