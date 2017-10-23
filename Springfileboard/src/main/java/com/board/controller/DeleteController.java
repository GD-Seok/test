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
	//Logger��ü
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private BoardDao boardDao;
	
	//1.�ۻ��� ������ �̵� (GET ���)
	@RequestMapping(value="/board/delete.do",method=RequestMethod.GET)
	public String form() { //�ܼ� ������ �̵�. GET ����� �� ����
		return "boardDelete"; //return "�̵��� ��������"; //definition�� name�� �����ؾ���
	}
	
	//�����޼��� ��� -> �ٽ� �ʱ�ȭ�� �����ϰ� ���� @ModelAttribute("��Ī")
	@ModelAttribute("command")
	public BoardCommand forBacking() { //��ȯ��(BoardCommand) �޼ҵ���
		return new BoardCommand(); 
	}
	
	//(POST���)	
	//�Է� -> ��ȿ�� �˻� -> �����߻� -> ������ DB�� ������ -> boardList.jsp�� �̵�
	//BindingResult ��ȿ�˻� ������ �ʿ�(����������ü)
	@RequestMapping(value="/board/delete.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")BoardCommand command,
														BindingResult result) { //�ܼ� ������ �̵�. POST ����� �� ����
		//��ȿ�� �˻� -> command ���� -> ���� �߻� -> result�� ���������� ����
		new BoardDeleteValidator().validate(command,result);
		//���������� �ִٸ�
		if(result.hasErrors()) {
			return form(); //"boardDelete" -> boardDelete.jsp
		}
		
		
		BoardCommand board=boardDao.selectBoard(command.getSeq());
		//DB���� ��ȣ == ������ ��ȣ�� ��
		// ��й�ȣ üũ
		if (!board.getPwd().equals(command.getPwd())) { // ������ ��й�ȣ�� DB Pwd�� Ʋ���ٸ�
			result.rejectValue("pwd", "invalidPassword"); // ���� �ʵ��, �����ڵ��
			return form(); //boardDelete.jsp
		}
		else { // ��ȣ�� ������
			boardDao.delete(command.getSeq()); //DB���� ������ ����
			//�ش� ������� ���ε��� ���� ����
			if(board.getFilename()!=(null)) { //���ε��� ������ �����Ѵٸ�
				FileUtil.removeFile(board.getFilename());
			}
		}
		
		
		//return "redirect:��û��ɾ�"
		return "redirect:/board/list.do"; //return "�̵��� ��������"; //definition�� name�� �����ؾ���
	}
}
