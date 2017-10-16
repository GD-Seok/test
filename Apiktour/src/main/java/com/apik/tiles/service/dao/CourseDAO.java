package com.apik.tiles.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.apik.tiles.vo.*;

public interface CourseDAO {

	CourseVO selectCourseOne(@Param(value="lv") Integer lv) throws Exception;
	
	List<CourseVO> selectCourseList(@Param(value="play") String play, 
														@Param(value="lv") Integer lv) throws Exception;
	
	Integer CourseCount(@Param(value="play") String play, 
			@Param(value="lv") Integer lv) throws Exception;
	
	
}
