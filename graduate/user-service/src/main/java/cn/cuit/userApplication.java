package cn.cuit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("cn.cuit.mapper")
@EnableDiscoveryClient
public class userApplication {
	public static void main(String[] args) {
		SpringApplication.run(userApplication.class,args);
	}
}