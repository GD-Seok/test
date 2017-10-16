package com.apik.tour.person.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//PersonDaoImpl에서 설정한 동작을 어떤 동작으로 설정할지에 대한 Controller 부분입니다.

@Controller
public class PersonController {
	
	@RequestMapping("personlist")
	public String process() {
		return "personlist";
	}
}
