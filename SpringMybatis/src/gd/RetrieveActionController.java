package gd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
//Controller�� ��ӹ޴� ���� => ��û�� �޾Ƽ� ó�����ַ���.
public class RetrieveActionController implements Controller {

	BoardDAO dao; //BoardDAO dao = new BoardDAO();
	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
		System.out.println("setDao()ȣ���(dao) : "+dao);
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("RetrieveActionController �����!");
		String num= request.getParameter("num");
		System.out.println("num : "+num);
		
		//���ڵ� �� ���� ���� ��ü �ʿ� (MyBatis)
		//BoardDTO data = dao.retrieve(num);
		dao.updateReadcnt(num); //readcnt=readcnt+1
		BoardCommand data= dao.retrieve(num); //�˻�
		
		
		ModelAndView mav = new ModelAndView("retrieve");
		//mav.setViewName("retrieve"); //retrieve.jsp
		
		//request.setAttribute("data",data); ������ �޸𸮿� �÷��� �ٸ� ���������� �̾����� �ϱ�
		mav.addObject("data", data); // ${data}�� �ص� ����
		return mav; //�޸𸮿� �ø�
	}

}
