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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.dao.BoardDao;
import com.board.domain.BoardCommand;
import com.board.util.FileUtil;
import com.board.validator.BoardValidator;

@Controller
public class UpdateController {
	// Logger객체
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private BoardDao boardDao;

	// 하나의 컨트롤러 -> 여러개의 요청명령어를 등록해서 사용가능

	// 1.글 수정 폼으로 이동 (GET 방식) /board/write
	@RequestMapping(value = "/board/update.do", method = RequestMethod.GET)
	public ModelAndView form(@RequestParam("seq") int seq) { // 단순 페이지 이동. GET 방식일 때 동작
		BoardCommand boardCommand = boardDao.selectBoard(seq); // 기존에 있던 레코드의 정보를 가져옴.
		return new ModelAndView("boardModify", "command", boardCommand); // return "이동할 페이지명"; //definition의 name과 동일해야함
	}

	// 에러메세지 출력 -> 다시 초기화가 가능하게 설정 @ModelAttribute("별칭")
	@ModelAttribute("command")
	public BoardCommand forBacking() { // 반환형(BoardCommand) 메소드형
		System.out.println("forBacking() 호출됨!");
		return new BoardCommand();
	}

	// (POST방식)
	// BindingResult 유효검사 때문에 필요(에러정보객체)
	@RequestMapping(value = "/board/update.do", method = RequestMethod.POST)
	public String submit(@ModelAttribute("command") BoardCommand command, BindingResult result) { // 단순 페이지 이동. POST 방식일
																									// 때 동작
		if (log.isDebugEnabled()) {
			log.debug("BoardCommand : " + command); // 수정한 값을 출력
		}
		// 유효성 검사 -> command 조사 -> 문제 발생 -> result에 에러정보를 담음
		new BoardValidator().validate(command, result);
		// 에러정보가 있다면
		if (result.hasErrors()) { // 에러가 있다면
			return "boardModify"; // "boardModify" -> boardModify.jsp 로 돌아감.
		}

		// 변경전의 데이터를 불러오기 -> 비밀번호 가져옴 -> 웹상에서 입력한 비밀번호와 매칭 -수정 허용
		BoardCommand board = null;
		String oldFileName = "";// 기존에 업로드 파일명
		board = boardDao.selectBoard(command.getSeq());

		// 비밀번호 체크
		if (!board.getPwd().equals(command.getPwd())) { // 웹상의 비밀번호와 DB Pwd가 틀리다면
			result.rejectValue("pwd", "invalidPassword"); // 적용 필드명, 에러코드명
			return "boardModify";
		} else {

			// 기존 파일 명 -> 기존 파일 삭제 -> 새로운 파일 세팅
			// 업로드된 파일이 없으면 기존파일은 덮어쓰기

			oldFileName = board.getFilename();

			if (!command.getUpload().isEmpty()) {
				// 탐색기에서 선택한 파일 이름
				try {
					command.setFilename(FileUtil.rename(command.getUpload().getOriginalFilename()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else { // 기존의 파일 덮어쓰기
				command.setFilename(oldFileName);
			}

			// 글수정 호출
			boardDao.update(command); // DB상에 파일의 정보가 저장됨.

			// ★실제로 바뀐 파일을 upload 폴더로 전송
			if (!command.getUpload().isEmpty()) {
				try {
					File file = new File(FileUtil.UPLOAD_PATH + "/" + command.getFilename());
					// 물리적으로 데이터 전송(파일전송)
					command.getUpload().transferTo(file); // 실제 파일이 이동함.

				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

				// 전의 데이터가 존재하면 삭제
				if (oldFileName != null) {
					// 이전 파일 삭제
					FileUtil.removeFile(oldFileName);
				}
			}
		}
		// return "redirect:요청명령어"
		return "redirect:/board/list.do"; // return "이동할 페이지명"; //definition의 name과 동일해야함
	}
}
