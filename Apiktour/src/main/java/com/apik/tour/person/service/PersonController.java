package com.apik.tour.person.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//PersonDaoImpl���� ������ ������ � �������� ���������� ���� Controller �κ��Դϴ�.

@Controller
public class PersonController {
	
	@RequestMapping("personlist")
	public String process() {
		return "personlist";
	}
}
