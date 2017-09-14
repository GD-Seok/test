package gd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import org.springframework.web.servlet.mvc.Controller;

public class DeleteActionController implements  Controller {
	
	BoardDAO dao; //BoardDAO dao = new BoardDAO();
	//<property name="commandClass" value="gd.BoardCommand"/>
	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
		System.out.println("setDao()ȣ���(dao) : "+dao);
	}
	
	//AbstractCommandController �� ������ �ִ� ��ü
	//1.Request ��û��ü
	//2.Response ���䰴ü
	//3.Object �Է¹��� ���� �����ϴ� ��ü
	//4.BindException ����ڷκ��� ���� �Է½� ������ �߻��ϸ� ó�����ִ� class
		
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String num = request.getParameter("num");
		
		dao.delete(num);
		
		return new ModelAndView("redirect:/list.do"); //���� �ּ� ���� ���ٷ� ó�� �����ϴ�.
	}
}
