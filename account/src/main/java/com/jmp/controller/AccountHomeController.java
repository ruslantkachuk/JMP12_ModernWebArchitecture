package com.jmp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountHomeController {
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}

}
