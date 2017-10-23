package com.board.controller;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.dao.BoardDao;
import com.board.domain.BoardCommand;
import com.board.util.FileUtil;
import com.board.validator.BoardDeleteValidator;
import com.board.validator.BoardValidator;

@Controller
public class DeleteController {
	//Logger객체
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private BoardDao boardDao;
	
	//1.글삭제 폼으로 이동 (GET 방식)
	@RequestMapping(value="/board/delete.do",method=RequestMethod.GET)
	public String form() { //단순 페이지 이동. GET 방식일 때 동작
		return "boardDelete"; //return "이동할 페이지명"; //definition의 name과 동일해야함
	}
	
	//에러메세지 출력 -> 다시 초기화가 가능하게 설정 @ModelAttribute("별칭")
	@ModelAttribute("command")
	public BoardCommand forBacking() { //반환형(BoardCommand) 메소드형
		return new BoardCommand(); 
	}
	
	//(POST방식)	
	//입력 -> 유효성 검사 -> 에러발생 -> 에러를 DB에 저장후 -> boardList.jsp로 이동
	//BindingResult 유효검사 때문에 필요(에러정보객체)
	@RequestMapping(value="/board/delete.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")BoardCommand command,
														BindingResult result) { //단순 페이지 이동. POST 방식일 때 동작
		//유효성 검사 -> command 조사 -> 문제 발생 -> result에 에러정보를 담음
		new BoardDeleteValidator().validate(command,result);
		//에러정보가 있다면
		if(result.hasErrors()) {
			return form(); //"boardDelete" -> boardDelete.jsp
		}
		
		
		BoardCommand board=boardDao.selectBoard(command.getSeq());
		//DB상의 암호 == 웹상의 암호를 비교
		// 비밀번호 체크
		if (!board.getPwd().equals(command.getPwd())) { // 웹상의 비밀번호와 DB Pwd가 틀리다면
			result.rejectValue("pwd", "invalidPassword"); // 적용 필드명, 에러코드명
			return form(); //boardDelete.jsp
		}
		else { // 암호가 맞으면
			boardDao.delete(command.getSeq()); //DB상의 데이터 삭제
			//해당 사용자의 업로드한 파일 삭제
			if(board.getFilename()!=(null)) { //업로드한 파일이 존재한다면
				FileUtil.removeFile(board.getFilename());
			}
		}
		
		
		//return "redirect:요청명령어"
		return "redirect:/board/list.do"; //return "이동할 페이지명"; //definition의 name과 동일해야함
	}
}
