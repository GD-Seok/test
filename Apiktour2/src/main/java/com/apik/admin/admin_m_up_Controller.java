package com.apik.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apik.admin.Dao.PersonDao;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class admin_m_up_Controller {
	
	@Autowired
	private PersonDao personDao;

	@RequestMapping("/05_admin_m_up.do")
	public String process() {
		return "member";
	}

}
