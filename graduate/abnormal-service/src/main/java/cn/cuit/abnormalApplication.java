package cn.cuit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 异常用户管理
 */
@SpringBootApplication
@EnableFeignClients
public class abnormalApplication {
	public static void main(String[] args) {
		SpringApplication.run(abnormalApplication.class,args);
	}

}