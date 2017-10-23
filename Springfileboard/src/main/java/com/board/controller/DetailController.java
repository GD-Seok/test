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
	
	//public String -> �������� �̵� 
	//public ModelAndView -> �������� �̵��ϸ鼭 �����͵� �Բ� ����
	@RequestMapping("/board/detail.do")
	public ModelAndView process(@RequestParam("seq") int seq) {

		//public ModelAndView process(HttpServletRequest request)
		//int seq=Integer.parseInt(request.getParameter("seq"));
		//������ ���� ���� ���� �ʾƵ� @RequestParam Annotation �������, ������ ����� �ʿ䰡 ����. 
		
		if(log.isDebugEnabled()) { //log ��ü�� �۵����̶�� (Debug ���·� �۵����̶��)
			log.debug("seq : " + seq); //System.out.println("seq : "+seq); �� ���� ������ ���ش�.
		}
		
		//��ȸ�� ����
		boardDao.updateHit(seq);
		BoardCommand board=boardDao.selectBoard(seq);
		
		//�� ���뿡 Enter(\r\n)�� �� ������ ó��
		board.setContent(StringUtil.parseBr(board.getContent()));
		
		//���1
		/*ModelAndView mav=new ModelAndView("boardView");
		mav.addObject("board", board);
		return mav;*/
		
		//���2
		/*return (new ModelAndView("boardView").addObject("board",board));*/
		
		//���3
		return new ModelAndView("boardView","board",board);
	}
	
	
	
	//�߰� �޼ҵ� �ڵ� 
	//�ٿ�ε� ��û�� ó��
	@RequestMapping("/board/file.do")
	public ModelAndView download(@RequestParam("filename") String filename) {
		//�ٿ�ε� ���� ������ ��ġ�� �̸�
		File downloadFile=new File(FileUtil.UPLOAD_PATH+"/"+filename);
		
		
		/*return new ModelAndView("download").addObject("downloadFile", downloadFile);*/
		
		return new ModelAndView("downloadView","downloadFile",downloadFile);
		//���⼭�� �� ���� "downlaod" �� jsp ������ �̸��� �ƴϴ�
		//("�ٿ�ε� ���� View��ü", "�� ��ü��(Key)", ������ ��(�ٿ�ε� ���� ���ϸ�));
	}
}
