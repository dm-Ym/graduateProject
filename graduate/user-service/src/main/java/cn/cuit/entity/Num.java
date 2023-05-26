package cn.cuit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用来统计接种率
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_recording")
public class Num {
	private int numfinish;
	private String finish;
}
