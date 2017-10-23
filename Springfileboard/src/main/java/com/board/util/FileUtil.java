package com.board.util;

import java.io.File;

//���� ���ε�� ���ε� �� ������� �� ������ �� �̸��� �ο�(���� ���)

public class FileUtil {

	//���ε� ���
	public static final String UPLOAD_PATH="C:/webtest/4.jsp/sou/Springfileboard/src/main/webapp/upload";
	
	//Ž������ ���� ���ϸ� �޴� ����
	public static String rename(String filename) throws Exception{
		if(filename == null) return null; //���ε� X �̸����� X ���ߴٸ�.
		//�� �̸� ��Ģ -> �ý��� ��¥ + ���� ����(0~49)
		String newName=Long.toString(System.currentTimeMillis())+
							   (int)(Math.random()*50);
		System.out.println("newName (����) : "+newName);
		return rename(filename,newName);
	}
	//������ ���ο� ���ϸ��� �����ϴ� ����( Ȯ���� ���� -> ����� �̸��� ���ؼ�)
	public static String rename(String filename, String newName) throws Exception{
		if(filename==null) return null;
		//Ȯ���� ���ϱ� (Ȯ���ڴ� �ڿ� �ֱ� ������, �ڿ������� ã�´� , ex) test.txt)
		int idx=filename.lastIndexOf("."); //.�� �ִ� ��ġ�� �ڿ������� ã��. ��ã���� -1 return��
		String extention=""; //Ȯ���ڸ� ������ ����
		String newFileName="";//�� ���ϸ��� ������ ����
		if(idx!=-1) { //-1�� �ƴ϶�� == .�� ��ġ�� ã�Ҵٸ�
			extention=filename.substring(idx);
			System.out.println("extention : "+extention);			
		}
		//�� ���ϸ�
		int newIdx=newName.lastIndexOf(".");
		if(newIdx!=-1) {
			newName=newName.substring(0,newIdx); //0��°�� ���Եȴ�.
			System.out.println("newName(����� ���ϸ�)"+newName);
		}
		newFileName=newName+extention.toLowerCase(); //Ȯ����(�빮�ڷ� ���͵� �ҹ��ڷ� ����)
		
		return newFileName; //���������� ���ε�� ���ϸ�
	}
	
	//�ۼ��� -> ���ε�� ���ϵ� ���� -> �������ε�� ���ϻ��� -> ���� ���ε��
	//���� ����
	public static void removeFile(String filename) {
		File file = new File(UPLOAD_PATH,filename); //��θ�, ���ϸ�
		if(file.exists()) {// ������ �����Ѵٸ�
			file.delete(); //�ش� ���� ����
		}
	}
	
}
