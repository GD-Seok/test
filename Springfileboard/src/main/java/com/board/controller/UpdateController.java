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
	// Logger��ü
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private BoardDao boardDao;

	// �ϳ��� ��Ʈ�ѷ� -> �������� ��û��ɾ ����ؼ� ��밡��

	// 1.�� ���� ������ �̵� (GET ���) /board/write
	@RequestMapping(value = "/board/update.do", method = RequestMethod.GET)
	public ModelAndView form(@RequestParam("seq") int seq) { // �ܼ� ������ �̵�. GET ����� �� ����
		BoardCommand boardCommand = boardDao.selectBoard(seq); // ������ �ִ� ���ڵ��� ������ ������.
		return new ModelAndView("boardModify", "command", boardCommand); // return "�̵��� ��������"; //definition�� name�� �����ؾ���
	}

	// �����޼��� ��� -> �ٽ� �ʱ�ȭ�� �����ϰ� ���� @ModelAttribute("��Ī")
	@ModelAttribute("command")
	public BoardCommand forBacking() { // ��ȯ��(BoardCommand) �޼ҵ���
		System.out.println("forBacking() ȣ���!");
		return new BoardCommand();
	}

	// (POST���)
	// BindingResult ��ȿ�˻� ������ �ʿ�(����������ü)
	@RequestMapping(value = "/board/update.do", method = RequestMethod.POST)
	public String submit(@ModelAttribute("command") BoardCommand command, BindingResult result) { // �ܼ� ������ �̵�. POST �����
																									// �� ����
		if (log.isDebugEnabled()) {
			log.debug("BoardCommand : " + command); // ������ ���� ���
		}
		// ��ȿ�� �˻� -> command ���� -> ���� �߻� -> result�� ���������� ����
		new BoardValidator().validate(command, result);
		// ���������� �ִٸ�
		if (result.hasErrors()) { // ������ �ִٸ�
			return "boardModify"; // "boardModify" -> boardModify.jsp �� ���ư�.
		}

		// �������� �����͸� �ҷ����� -> ��й�ȣ ������ -> ���󿡼� �Է��� ��й�ȣ�� ��Ī -���� ���
		BoardCommand board = null;
		String oldFileName = "";// ������ ���ε� ���ϸ�
		board = boardDao.selectBoard(command.getSeq());

		// ��й�ȣ üũ
		if (!board.getPwd().equals(command.getPwd())) { // ������ ��й�ȣ�� DB Pwd�� Ʋ���ٸ�
			result.rejectValue("pwd", "invalidPassword"); // ���� �ʵ��, �����ڵ��
			return "boardModify";
		} else {

			// ���� ���� �� -> ���� ���� ���� -> ���ο� ���� ����
			// ���ε�� ������ ������ ���������� �����

			oldFileName = board.getFilename();

			if (!command.getUpload().isEmpty()) {
				// Ž���⿡�� ������ ���� �̸�
				try {
					command.setFilename(FileUtil.rename(command.getUpload().getOriginalFilename()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else { // ������ ���� �����
				command.setFilename(oldFileName);
			}

			// �ۼ��� ȣ��
			boardDao.update(command); // DB�� ������ ������ �����.

			// �ڽ����� �ٲ� ������ upload ������ ����
			if (!command.getUpload().isEmpty()) {
				try {
					File file = new File(FileUtil.UPLOAD_PATH + "/" + command.getFilename());
					// ���������� ������ ����(��������)
					command.getUpload().transferTo(file); // ���� ������ �̵���.

				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

				// ���� �����Ͱ� �����ϸ� ����
				if (oldFileName != null) {
					// ���� ���� ����
					FileUtil.removeFile(oldFileName);
				}
			}
		}
		// return "redirect:��û��ɾ�"
		return "redirect:/board/list.do"; // return "�̵��� ��������"; //definition�� name�� �����ؾ���
	}
}
