package com.apik.tiles.service.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apik.tiles.service.*;
import com.apik.tiles.service.dao.*;
import com.apik.tiles.vo.*;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDAO courseMapper;
	
	
	@Override
	public CourseVO selectCourseOne(Integer lv) throws Exception {
		// TODO Auto-generated method stub
		return courseMapper.selectCourseOne(lv);
	}
	
	@Override
	public List<CourseVO> selectCourseList(String play, Integer lv) throws Exception {
		return courseMapper.selectCourseList(play, lv);
	}

	@Override
	public int CourseCount(String play, Integer lv) throws Exception {
		// TODO Auto-generated method stub
		return courseMapper.CourseCount(play, lv);
	}




}
