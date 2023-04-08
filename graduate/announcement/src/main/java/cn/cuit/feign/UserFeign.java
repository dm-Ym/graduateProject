package cn.cuit.feign;

import cn.cuit.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserFeign{
	@GetMapping("/{phone}")
	User selectPhone(@PathVariable("phone") String phone);
}
