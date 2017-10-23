package com.board.controller;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.dao.BoardDao;
import com.board.domain.BoardCommand;
import com.board.util.FileUtil;
import com.board.util.StringUtil;

@Controller
public class DetailController {
	
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private BoardDao boardDao;
	
	//public String -> 페이지만 이동 
	//public ModelAndView -> 페이지가 이동하면서 데이터도 함께 전달
	@RequestMapping("/board/detail.do")
	public ModelAndView process(@RequestParam("seq") int seq) {

		//public ModelAndView process(HttpServletRequest request)
		//int seq=Integer.parseInt(request.getParameter("seq"));
		//이제는 위와 같이 하지 않아도 @RequestParam Annotation 사용으로, 강제로 사용할 필요가 없다. 
		
		if(log.isDebugEnabled()) { //log 객체가 작동중이라면 (Debug 상태로 작동중이라면)
			log.debug("seq : " + seq); //System.out.println("seq : "+seq); 와 같은 동작을 해준다.
		}
		
		//조회수 증가
		boardDao.updateHit(seq);
		BoardCommand board=boardDao.selectBoard(seq);
		
		//글 내용에 Enter(\r\n)가 들어간 경우까지 처리
		board.setContent(StringUtil.parseBr(board.getContent()));
		
		//방법1
		/*ModelAndView mav=new ModelAndView("boardView");
		mav.addObject("board", board);
		return mav;*/
		
		//방법2
		/*return (new ModelAndView("boardView").addObject("board",board));*/
		
		//방법3
		return new ModelAndView("boardView","board",board);
	}
	
	
	
	//추가 메소드 코딩 
	//다운로드 요청시 처리
	@RequestMapping("/board/file.do")
	public ModelAndView download(@RequestParam("filename") String filename) {
		//다운로드 받을 파일의 위치와 이름
		File downloadFile=new File(FileUtil.UPLOAD_PATH+"/"+filename);
		
		
		/*return new ModelAndView("download").addObject("downloadFile", downloadFile);*/
		
		return new ModelAndView("downloadView","downloadFile",downloadFile);
		//여기서는 맨 앞의 "downlaod" 가 jsp 파일의 이름이 아니다
		//("다운로드 받을 View객체", "모델 객체명(Key)", 전달할 값(다운로드 받을 파일명));
	}
}
