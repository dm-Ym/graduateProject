package cn.cuit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 投诉
 */
@SpringBootApplication
public class complaintsApplication {
	public static void main(String[] args) {
		SpringApplication.run(complaintsApplication.class,args);
	}
}