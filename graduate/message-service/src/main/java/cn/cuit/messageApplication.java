package cn.cuit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 用户健康信息上报.功能包括健康打卡
 */
@SpringBootApplication
@EnableFeignClients
public class messageApplication {
	public static void main(String[] args) {
		SpringApplication.run(messageApplication.class,args);
	}
}