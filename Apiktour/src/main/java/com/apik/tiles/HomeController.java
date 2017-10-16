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
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	// Autowired inject
	//@Autowired
	//private CourseDAO
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "intro/intro";
	}
	

	@RequestMapping(value = "/choice.do", method = RequestMethod.GET)
	public String choice(@RequestParam String mode, Model model) {
		logger.info("choice.do.", model);
		
		  System.out.println("===choice.jsp===");	
		   	String level="";
			String type="";
			System.out.println("mode > "+mode);
			
			String m = mode; 
			switch(m) { 
			case "rest": type="피로도"; break; 
			case "play": type="에너지"; break; 
			}
			
			System.out.println("type > "+type); 
			System.out.println("===========");		
		
		model.addAttribute("mode", mode);
		model.addAttribute("type", type);
		
		return "intro/choice";
	}
	
	
	//@Service(name = "courseService")
	//private CourseService courseService;
		
	@RequestMapping(value = "playPro.do", method=RequestMethod.POST)
	public String playpro(@RequestParam String mode, 
									@RequestParam String level, 
			Model model) throws Exception {
		logger.info("playPro.do.", model);
		
		 System.out.println("===palyPro.jsp===");	
			String type="";
			System.out.println("mode > "+mode);
			System.out.println("level > "+level);	
			
			String m = mode; 
			switch(m) { 
			case "rest": type="피로도"; break; 
			case "play": type="에너지"; break; 
			}
			
			System.out.println("type > "+type);
			
			int lv=Integer.parseInt(level);
			String play=mode;
			int count=3;		
			//count=courseService.CourseCount(play, lv);
			
			//Map<String, Object> map=new HashMap<String, Object>();
			//map.put("play", play);
			//map.put("lv", lv);			
			//int count=courseService.CourseCount(map);
			
			System.out.println("play:"+play+" , lv:"+lv+", count :"+count);	
			System.out.println("===========");		
		
		model.addAttribute("mode", mode);
		model.addAttribute("type", type);
		model.addAttribute("level", level);
		model.addAttribute("count", count);
		
		return "intro/playPro";
	}
	
	
	

}
