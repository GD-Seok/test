package com.board.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.dao.BoardDao; //interface 객체
import com.board.domain.BoardCommand;
import com.board.util.PagingUtil;

// /board/list.do 요청이 들어올시 처리할 파일
// 페이징 처리, 검색분야, 검색어
// Log객체 생성 -> Log 객체 데이터 처리과정을 출력

@Controller
public class ListController {
	
	//1) Logger 객체 생성
	//private Logger log=Logger.getLogger(ListController.class); //적용 시킬 Class
	private Logger log=Logger.getLogger(this.getClass()); //위와 아래문장은 같다.
	@Autowired
	private BoardDao boardDao; // BoardDaoImpl 객체를 가져온것과 동일.
	
	//@RequestParam(value="매개변수명",defaultValue="매개변수 전달 못할 때 기본으로 설정할 값")
	@RequestMapping("/board/list.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
											@RequestParam(value="keyField",defaultValue="") String keyField,
											@RequestParam(value="keyWord",defaultValue="") String keyWord) {
		//if(request.getParameter(pageNum)==null) pageNum="1";
		//int currentPage=Integer(reqeust.getParameter("pageNum"));
		
		if(log.isDebugEnabled()) { //현재 로그객체가 현재 Debug 상태라면
			System.out.println("process() 호출됨");
			log.debug("currentPage : "+currentPage); //내부적인 처리과정 확인
			log.debug("keyField : "+keyField);
			log.debug("keyWord : "+keyWord);			
		}
		
		//검색분야, 검색어를 Map에 담아야 된다.
		Map<String,Object>map = new HashMap<String,Object>();
		map.put("keyField", keyField); //map.get("keyField")로 값을 가져올 수 있다.
		map.put("keyWord", keyWord);
		//총 개수 
		int count=boardDao.getRowCount(map);
		System.out.println("count=>"+count);
		//페이징처리 (1.현재페이지 2.개수 3.페이지별로 레코드개수 4.블럭당 보여주는 페이지 개수 5.요청url)
		PagingUtil page=new PagingUtil(currentPage,count,3,3,"list.do");
		//list.do로 요청이 들어왔을시, 위와 같이 설정한다. 그 정보를 page에 담는다
		
		//map 추가
		map.put("start", page.getStartCount()); //#{start}
		map.put("end", page.getEndCount()); //#{end}
		List<BoardCommand> list=null; //레코드 담을 객체 선언
		
		if(count > 0) {
			list=boardDao.list(map); //4개의 키값이 전달
			System.out.println("ListController list=>"+list);
		}else {
			list=Collections.EMPTY_LIST;//상수값을 설정 (아무것도 없다)
		}
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml()); //링크문자열(이전, 다음)
	
		return mav;
	}
}

