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
		System.out.println("setDao()호출됨(dao) : "+dao);
	}
	
	//AbstractCommandController 가 가지고 있는 객체
	//1.Request 요청객체
	//2.Response 응답객체
	//3.Object 입력받은 값을 저장하는 객체
	//4.BindException 사용자로부터 값을 입력시 에러가 발생하면 처리해주는 class
		
	@Override
	protected ModelAndView handle(HttpServletRequest request, 
			HttpServletResponse response, Object command, BindException error)
			throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		BoardCommand data = (BoardCommand)command; //BoardCommnad 는 상속받은 부모의 class이다.
		/*String author = data.getAuthor();
		String content =data.getContent();
		String title = data.getTitle();
		String num = request.getParameter("num");*/

		//dao.update(num, author, title, content); //dao.update(data);
		dao.update(data);

		return new ModelAndView("redirect:/list.do"); //위의 주석 문을 한줄로 처리 가능하다.
	}

}
