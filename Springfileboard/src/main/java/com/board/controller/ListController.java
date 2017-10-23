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

import com.board.dao.BoardDao; //interface ��ü
import com.board.domain.BoardCommand;
import com.board.util.PagingUtil;

// /board/list.do ��û�� ���ý� ó���� ����
// ����¡ ó��, �˻��о�, �˻���
// Log��ü ���� -> Log ��ü ������ ó�������� ���

@Controller
public class ListController {
	
	//1) Logger ��ü ����
	//private Logger log=Logger.getLogger(ListController.class); //���� ��ų Class
	private Logger log=Logger.getLogger(this.getClass()); //���� �Ʒ������� ����.
	@Autowired
	private BoardDao boardDao; // BoardDaoImpl ��ü�� �����°Ͱ� ����.
	
	//@RequestParam(value="�Ű�������",defaultValue="�Ű����� ���� ���� �� �⺻���� ������ ��")
	@RequestMapping("/board/list.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
											@RequestParam(value="keyField",defaultValue="") String keyField,
											@RequestParam(value="keyWord",defaultValue="") String keyWord) {
		//if(request.getParameter(pageNum)==null) pageNum="1";
		//int currentPage=Integer(reqeust.getParameter("pageNum"));
		
		if(log.isDebugEnabled()) { //���� �αװ�ü�� ���� Debug ���¶��
			System.out.println("process() ȣ���");
			log.debug("currentPage : "+currentPage); //�������� ó������ Ȯ��
			log.debug("keyField : "+keyField);
			log.debug("keyWord : "+keyWord);			
		}
		
		//�˻��о�, �˻�� Map�� ��ƾ� �ȴ�.
		Map<String,Object>map = new HashMap<String,Object>();
		map.put("keyField", keyField); //map.get("keyField")�� ���� ������ �� �ִ�.
		map.put("keyWord", keyWord);
		//�� ���� 
		int count=boardDao.getRowCount(map);
		System.out.println("count=>"+count);
		//����¡ó�� (1.���������� 2.���� 3.���������� ���ڵ尳�� 4.���� �����ִ� ������ ���� 5.��ûurl)
		PagingUtil page=new PagingUtil(currentPage,count,3,3,"list.do");
		//list.do�� ��û�� ��������, ���� ���� �����Ѵ�. �� ������ page�� ��´�
		
		//map �߰�
		map.put("start", page.getStartCount()); //#{start}
		map.put("end", page.getEndCount()); //#{end}
		List<BoardCommand> list=null; //���ڵ� ���� ��ü ����
		
		if(count > 0) {
			list=boardDao.list(map); //4���� Ű���� ����
			System.out.println("ListController list=>"+list);
		}else {
			list=Collections.EMPTY_LIST;//������� ���� (�ƹ��͵� ����)
		}
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml()); //��ũ���ڿ�(����, ����)
	
		return mav;
	}
}

