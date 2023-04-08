package cn.cuit.controller;

import cn.cuit.service.AbnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AbnoController {
	@Autowired
	private AbnoService abnoService;


}
