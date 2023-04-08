package cn.cuit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 用户留言/商品代买
 */
@EnableFeignClients
@SpringBootApplication
public class commentsApplication {
	public static void main(String[] args) {
		SpringApplication.run(commentsApplication.class,args);
	}
}