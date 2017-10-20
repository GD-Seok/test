package com.apik.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class admin_main_Controller {

	@RequestMapping("/admin_main.do")
	public String process() {
		return "admin";
	}
}
