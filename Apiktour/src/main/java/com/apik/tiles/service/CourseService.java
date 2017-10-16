package com.apik.tiles.service;

import java.util.List;
import java.util.Map;

import com.apik.tiles.vo.*;

public interface CourseService {
	
	CourseVO selectCourseOne(Integer lv) throws Exception;
	
	List<CourseVO> selectCourseList(String play, Integer lv) throws Exception;
	
	int CourseCount(String play, Integer lv) throws Exception;

	//int CourseCount(Map<String, Object> map) throws Exception;
	
}
