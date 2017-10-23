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
	//Logger��ü
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private BoardDao boardDao;
	
	//�ϳ��� ��Ʈ�ѷ� -> �������� ��û��ɾ ����ؼ� ��밡��
	
	//1.�۾��� ������ �̵� (GET ���) /board/write
	@RequestMapping(value="/board/write.do",method=RequestMethod.GET)
	public String form() { //�ܼ� ������ �̵�. GET ����� �� ����
		System.out.println("POST ������� ���ͼ� forBacking�� ȣ��Ǿ� �ʱ�ȭ �� �ڿ� GET ������� ����");
		return "boardWrite"; //return "�̵��� ��������"; //definition�� name�� �����ؾ���
	}
	
	//�����޼��� ��� -> �ٽ� �ʱ�ȭ�� �����ϰ� ���� @ModelAttribute("��Ī")
	@ModelAttribute("command")
	public BoardCommand forBacking() { //��ȯ��(BoardCommand) �޼ҵ���
		System.out.println("forBacking() ȣ���!");
		return new BoardCommand(); 
	}
	
	//(POST���)	
	//�Է� -> ��ȿ�� �˻� -> �����߻� -> ������ DB�� ������ -> boardList.jsp�� �̵�
	//BindingResult ��ȿ�˻� ������ �ʿ�(����������ü)
	@RequestMapping(value="/board/write.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")BoardCommand command,
														BindingResult result) { //�ܼ� ������ �̵�. POST ����� �� ����
		if(log.isDebugEnabled()) {
			log.debug("BoardCommand : "+command); //�Է¹��� ���� ���
		}
		//��ȿ�� �˻� -> command ���� -> ���� �߻� -> result�� ���������� ����
		new BoardValidator().validate(command,result);
		//���������� �ִٸ�
		if(result.hasErrors()) {
			return form(); //"boardWrite" -> boardWrite.jsp
		}
		//���ε� -> �����->����ó��
		try {
			String newName="";//���ε��� ������ ����� ���ϸ�
			//���ε� �Ǿ��ִٸ�
			if(!command.getUpload().isEmpty()) {
				//Ž���⿡�� ������ ���� �̸�
				newName=FileUtil.rename(command.getUpload().getOriginalFilename());
				System.out.println("newName : "+newName);
				command.setFilename(newName); //����� �̸��� filename���� ��
				// rename���� ���ؼ� �� java ������ ��ŷ���ϴ���, �ش� ������ �̸��� ������ ���� �������� ��							
			}
			//�ִ밪�� ���ؼ�+1
			System.out.println("boardDao��ü=>"+boardDao);
			int newSeq=boardDao.getNewSeq()+1;
			System.out.println("newSeq : "+newSeq);
			//�߰�
			command.setSeq(newSeq);
			//�۾��� ȣ��
			boardDao.insert(command); //DB�� ������ ������ �����.
			
			//�ڽ����� upload ������ ����
			if(!command.getUpload().isEmpty()) {
				File file=new File(FileUtil.UPLOAD_PATH+"/"+newName);
				//���������� ������ ����(��������)
				command.getUpload().transferTo(file); //���� ������ �̵���.
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		//return "redirect:��û��ɾ�"
		return "redirect:/board/list.do"; //return "�̵��� ��������"; //definition�� name�� �����ؾ���
	}
}
