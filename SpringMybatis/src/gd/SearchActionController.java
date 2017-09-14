package gd;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
//Controller�� ��ӹ޴� ���� => ��û�� �޾Ƽ� ó�����ַ���.
public class SearchActionController implements Controller {

	BoardDAO dao; //BoardDAO dao = new BoardDAO();
	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
		System.out.println("setDao()ȣ���(dao) : "+dao);
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("SearchActionController �����!");
		String searchName=request.getParameter("searchName");
		String searchValue=request.getParameter("searchValue");
		
		//ArrayList list = dao.search(searchName,searchValue);
		BoardCommand data = new BoardCommand();
		data.setSearchName(searchName);
		data.setSearchValue(searchValue);
		List list=dao.search(data);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("list"); //list.jsp
		
		//request.setAttribute("list",list);
		mav.addObject("list", list); // ${list}�� �ص� ����
		return mav;
	}

}
