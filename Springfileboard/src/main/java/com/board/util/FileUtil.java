package com.board.util;

import java.io.File;

//파일 업로드시 업로드 할 경로지정 및 파일의 새 이름을 부여(공통 모듈)

public class FileUtil {

	//업로드 경로
	public static final String UPLOAD_PATH="C:/webtest/4.jsp/sou/Springfileboard/src/main/webapp/upload";
	
	//탐색기의 원본 파일명만 받는 역할
	public static String rename(String filename) throws Exception{
		if(filename == null) return null; //업로드 X 이름변경 X 안했다면.
		//새 이름 규칙 -> 시스템 날짜 + 랜덤 숫자(0~49)
		String newName=Long.toString(System.currentTimeMillis())+
							   (int)(Math.random()*50);
		System.out.println("newName (난수) : "+newName);
		return rename(filename,newName);
	}
	//실제로 새로운 파일명을 변경하는 역할( 확장자 구분 -> 변경된 이름만 구해서)
	public static String rename(String filename, String newName) throws Exception{
		if(filename==null) return null;
		//확장자 구하기 (확장자는 뒤에 있기 때문에, 뒤에서부터 찾는다 , ex) test.txt)
		int idx=filename.lastIndexOf("."); //.이 있는 위치를 뒤에서부터 찾음. 못찾으면 -1 return됨
		String extention=""; //확장자를 저장할 공간
		String newFileName="";//새 파일명을 저장할 공간
		if(idx!=-1) { //-1이 아니라면 == .의 위치를 찾았다면
			extention=filename.substring(idx);
			System.out.println("extention : "+extention);			
		}
		//새 파일명
		int newIdx=newName.lastIndexOf(".");
		if(newIdx!=-1) {
			newName=newName.substring(0,newIdx); //0번째는 포함된다.
			System.out.println("newName(변경된 파일명)"+newName);
		}
		newFileName=newName+extention.toLowerCase(); //확장자(대문자로 들어와도 소문자로 변경)
		
		return newFileName; //실질적으로 업로드된 파일명
	}
	
	//글수정 -> 업로드된 파일도 수정 -> 기존업로드된 파일삭제 -> 새로 업로드됨
	//파일 삭제
	public static void removeFile(String filename) {
		File file = new File(UPLOAD_PATH,filename); //경로명, 파일명
		if(file.exists()) {// 파일이 존재한다면
			file.delete(); //해당 파일 삭제
		}
	}
	
}
