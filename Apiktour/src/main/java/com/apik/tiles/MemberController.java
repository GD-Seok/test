package com.apik.tiles;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apik.tiles.service.*;
import com.apik.tiles.vo.*;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	
	// Autowired inject
	//@Autowired
	//private CourseDAO
		
	@RequestMapping(value = "join.do", method=RequestMethod.GET)
	public String join(Model model) throws Exception {
		logger.info("join.do.", model);
		

		return "member/member_in";
	}
	

}
