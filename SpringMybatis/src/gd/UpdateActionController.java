package gd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

public class UpdateActionController extends AbstractCommandController {
	
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
	protected ModelAndView handle(HttpServletRequest request, 
			HttpServletResponse response, Object command, BindException error)
			throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		BoardCommand data = (BoardCommand)command; //BoardCommnad �� ��ӹ��� �θ��� class�̴�.
		/*String author = data.getAuthor();
		String content =data.getContent();
		String title = data.getTitle();
		String num = request.getParameter("num");*/

		//dao.update(num, author, title, content); //dao.update(data);
		dao.update(data);

		return new ModelAndView("redirect:/list.do"); //���� �ּ� ���� ���ٷ� ó�� �����ϴ�.
	}

}
