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


//DetailController.java ������ ��û�� �޾� return ���� ��.
//return new ModelAndView("downloadView","downloadFile",downloadFile);
public class DownloadView extends AbstractView {

	//������
	public DownloadView() {
		setContentType("application/download");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		//DetailController.java ������ ��û�� �޾� return ���� ��.
		//return new ModelAndView("downloadView","downloadFile",downloadFile);
		
		//�ٿ�ε� ���� ������ ������ ������
		File file=(File)model.get("downloadFile");
		System.out.println("�������� �ִ� File ��ü ���� : "+file);
		
		//�ٿ�ε� ���� ������ Ÿ��, ũ�⸦ ���� (response�� ���尴ü�� ���)
		response.setContentType(getContentType());
		response.setContentLength((int)file.length()); //������ ���̸�ŭ �ٿ�ε带 �ް� ��
		
		//������ ���� �ѱ�ó��
		String userAgent=request.getHeader("User-Agent"); //userAgent �Ӽ����� ���� user�� � �������� ����ϴ��� �� ���ִ�.
		System.out.println("userAgent : "+userAgent);
		boolean ie=userAgent.indexOf("MSIE") > -1; // InternetExpolorer �� MSIE�� ǥ�õȴ�. "MSIE"�� �� ã�Ҵٸ� -1
		String fileName=null;
		if(ie==true) { //IE �� -> (�ٿ�ε� �޴� ������ �ѱ� ó��) 
			fileName=URLEncoder.encode(file.getName(),"UTF-8");
		}
		else { // �׿��� ��������� -> �ٿ�ε� ���� ������ UTF-8 �ڵ��� ��� -> �������� ���� 
			fileName=new String(file.getName().getBytes("UTF-8"),"iso-8859-1");
		}
		
		//�ٿ�ε� �Ϸ��� �ҽ� -> ��ȭ���ڿ��� ���ϴ� ��ġ�� ������ �� �ְ� ����
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");
		//Content-Disposition �ٿ�ε� �޴� ��ġ
		//attachment;filename=�ٿ�ε� ���� ���ϸ�
		
		//exe -> 2�� ���� -> �ٿ�ε� �����ϰ� ���� (�Ӽ� Ű Content-Transfer-Encoding ���)
		response.setHeader("Content-Transfer-Encoding", "binary");//binary ���ϵ� �ٿ�ε� �����ϰ� ����
		
		//����� ��ü�� ���� ������ �޴� ����
		OutputStream out = response.getOutputStream(); //new OutputStream() ó�� ��ü�� ����� �� ����, �Ⱦ��� �޸𸮸� �Ƴ��� ���� ����
		FileInputStream fis=null;
		
		try {
			fis = new FileInputStream(file);

			//������ ���� ������ �о� �鿩�� �ٿ�ε� ���� FilCopy Class �̿�
			FileCopyUtils.copy(fis, out);//(�ٿ�ε� �޴��� ��ü��, ��� ��ü��)
		}catch(IOException e) {e.printStackTrace();}
		finally { //����ó���� ������� �׻� ó���ؾ� �� ���
			if(fis!=null)
				try{
					fis.close();				
				}catch(IOException e) {}
		}
		out.flush();//����� �ٷ� ���
	}

}
