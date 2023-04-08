package cn.cuit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * 公告
 */
@SpringBootApplication
@EnableFeignClients
public class announcementApplication {
	public static void main(String[] args) {
		SpringApplication.run(announcementApplication.class,args);
	}
}