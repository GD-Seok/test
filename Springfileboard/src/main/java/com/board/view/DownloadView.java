package com.board.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;


//DetailController.java 에서의 요청을 받아 return 해준 값.
//return new ModelAndView("downloadView","downloadFile",downloadFile);
public class DownloadView extends AbstractView {

	//생성자
	public DownloadView() {
		setContentType("application/download");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		//DetailController.java 에서의 요청을 받아 return 해준 값.
		//return new ModelAndView("downloadView","downloadFile",downloadFile);
		
		//다운로드 받을 파일의 정보를 얻어오기
		File file=(File)model.get("downloadFile");
		System.out.println("상세정보에 있는 File 객체 얻어옴 : "+file);
		
		//다운로드 받을 파일의 타입, 크기를 지정 (response의 내장객체를 사용)
		response.setContentType(getContentType());
		response.setContentLength((int)file.length()); //파일의 길이만큼 다운로드를 받게 함
		
		//브라우저 별로 한글처리
		String userAgent=request.getHeader("User-Agent"); //userAgent 속성으로 현재 user가 어떤 브라우저를 사용하는지 알 수있다.
		System.out.println("userAgent : "+userAgent);
		boolean ie=userAgent.indexOf("MSIE") > -1; // InternetExpolorer 는 MSIE로 표시된다. "MSIE"를 못 찾았다면 -1
		String fileName=null;
		if(ie==true) { //IE 면 -> (다운로드 받는 파일의 한글 처리) 
			fileName=URLEncoder.encode(file.getName(),"UTF-8");
		}
		else { // 그외의 브라우저라면 -> 다운로드 받을 파일이 UTF-8 코딩일 경우 -> 영문으로 변경 
			fileName=new String(file.getName().getBytes("UTF-8"),"iso-8859-1");
		}
		
		//다운로드 하려고 할시 -> 대화상자에서 원하는 위치를 지정할 수 있게 생성
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");
		//Content-Disposition 다운로드 받는 위치
		//attachment;filename=다운로드 받을 파일명
		
		//exe -> 2진 파일 -> 다운로드 가능하게 설정 (속성 키 Content-Transfer-Encoding 사용)
		response.setHeader("Content-Transfer-Encoding", "binary");//binary 파일도 다운로드 가능하게 설정
		
		//입출력 객체를 만들어서 전송을 받는 구문
		OutputStream out = response.getOutputStream(); //new OutputStream() 처럼 객체를 만드는 것 보다, 안쓰고 메모리를 아끼는 것이 낫다
		FileInputStream fis=null;
		
		try {
			fis = new FileInputStream(file);

			//서버로 부터 파일을 읽어 들여서 다운로드 받음 FilCopy Class 이용
			FileCopyUtils.copy(fis, out);//(다운로드 받는쪽 객체명, 출력 객체명)
		}catch(IOException e) {e.printStackTrace();}
		finally { //예외처리와 상관없이 항상 처리해야 할 기능
			if(fis!=null)
				try{
					fis.close();				
				}catch(IOException e) {}
		}
		out.flush();//입출력 바로 출력
	}

}
