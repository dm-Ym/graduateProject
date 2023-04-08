package cn.cuit.controller;

import cn.cuit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/super")
public class superController {
	@Autowired
	private UserService userService;
}
