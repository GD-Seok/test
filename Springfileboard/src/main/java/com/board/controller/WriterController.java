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
import com.board.validator.BoardValidator;

@Controller
public class WriterController {
	//Logger객체
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private BoardDao boardDao;
	
	//하나의 컨트롤러 -> 여러개의 요청명령어를 등록해서 사용가능
	
	//1.글쓰기 폼으로 이동 (GET 방식) /board/write
	@RequestMapping(value="/board/write.do",method=RequestMethod.GET)
	public String form() { //단순 페이지 이동. GET 방식일 때 동작
		System.out.println("POST 방식으로 들어와서 forBacking이 호출되어 초기화 된 뒤에 GET 방식으로 들어옴");
		return "boardWrite"; //return "이동할 페이지명"; //definition의 name과 동일해야함
	}
	
	//에러메세지 출력 -> 다시 초기화가 가능하게 설정 @ModelAttribute("별칭")
	@ModelAttribute("command")
	public BoardCommand forBacking() { //반환형(BoardCommand) 메소드형
		System.out.println("forBacking() 호출됨!");
		return new BoardCommand(); 
	}
	
	//(POST방식)	
	//입력 -> 유효성 검사 -> 에러발생 -> 에러를 DB에 저장후 -> boardList.jsp로 이동
	//BindingResult 유효검사 때문에 필요(에러정보객체)
	@RequestMapping(value="/board/write.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")BoardCommand command,
														BindingResult result) { //단순 페이지 이동. POST 방식일 때 동작
		if(log.isDebugEnabled()) {
			log.debug("BoardCommand : "+command); //입력받은 값을 출력
		}
		//유효성 검사 -> command 조사 -> 문제 발생 -> result에 에러정보를 담음
		new BoardValidator().validate(command,result);
		//에러정보가 있다면
		if(result.hasErrors()) {
			return form(); //"boardWrite" -> boardWrite.jsp
		}
		//업로드 -> 입출력->예외처리
		try {
			String newName="";//업로드한 파일의 변경된 파일명
			//업로드 되어있다면
			if(!command.getUpload().isEmpty()) {
				//탐색기에서 선택한 파일 이름
				newName=FileUtil.rename(command.getUpload().getOriginalFilename());
				System.out.println("newName : "+newName);
				command.setFilename(newName); //변경된 이름이 filename으로 들어감
				// rename으로 인해서 본 java 파일이 해킹당하더라도, 해당 파일이 이름만 봐서는 무슨 파일인지 모름							
			}
			//최대값을 구해서+1
			System.out.println("boardDao객체=>"+boardDao);
			int newSeq=boardDao.getNewSeq()+1;
			System.out.println("newSeq : "+newSeq);
			//추가
			command.setSeq(newSeq);
			//글쓰기 호출
			boardDao.insert(command); //DB상에 파일의 정보가 저장됨.
			
			//★실제로 upload 폴더로 전송
			if(!command.getUpload().isEmpty()) {
				File file=new File(FileUtil.UPLOAD_PATH+"/"+newName);
				//물리적으로 데이터 전송(파일전송)
				command.getUpload().transferTo(file); //실제 파일이 이동함.
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		//return "redirect:요청명령어"
		return "redirect:/board/list.do"; //return "이동할 페이지명"; //definition의 name과 동일해야함
	}
}
