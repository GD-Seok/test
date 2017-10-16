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
public class ResultController {
	
	private static final Logger logger = LoggerFactory.getLogger(ResultController.class);
	
	
	// Autowired inject
	//@Autowired
	//private CourseDAO
		
	@RequestMapping(value = "result.do", method=RequestMethod.POST)
	public String result(@RequestParam String mode, 
			Model model) throws Exception {
		logger.info("result.do.", model);
		
		 System.out.println("===result.jsp===");	
			String type="";
			System.out.println("mode > "+mode);
			//System.out.println("level > "+level);	
			
			
			//System.out.println("type > "+type);
			
			//int lv=Integer.parseInt(level);
			String play=mode;
			int count=3;		
			
			//System.out.println("play:"+play+" , lv:"+lv+", count :"+count);	
			System.out.println("===========");		
		
		model.addAttribute("mode", mode);
		model.addAttribute("type", type);
		//model.addAttribute("level", level);
		model.addAttribute("count", count);
		
		return "result/result";
	}
	

}
