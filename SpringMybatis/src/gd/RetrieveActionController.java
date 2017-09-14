package gd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
//Controller를 상속받는 이유 => 요청을 받아서 처리해주려고.
public class RetrieveActionController implements Controller {

	BoardDAO dao; //BoardDAO dao = new BoardDAO();
	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
		System.out.println("setDao()호출됨(dao) : "+dao);
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("RetrieveActionController 실행됨!");
		String num= request.getParameter("num");
		System.out.println("num : "+num);
		
		//레코드 한 개를 담을 객체 필요 (MyBatis)
		//BoardDTO data = dao.retrieve(num);
		dao.updateReadcnt(num); //readcnt=readcnt+1
		BoardCommand data= dao.retrieve(num); //검색
		
		
		ModelAndView mav = new ModelAndView("retrieve");
		//mav.setViewName("retrieve"); //retrieve.jsp
		
		//request.setAttribute("data",data); 데이터 메모리에 올려서 다른 페이지에도 이어지게 하기
		mav.addObject("data", data); // ${data}로 해도 동일
		return mav; //메모리에 올림
	}

}
