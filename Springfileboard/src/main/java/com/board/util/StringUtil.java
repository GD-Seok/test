package com.board.util;

//�� ���� -> enter�� ���� -> web�� ��� (enter -> <br>�� �����ϴ� ����)
//����� <pre></pre> �±׾ȿ� ���ڿ��� �Է��ϸ�, ���鵵 ��� �����ϱ� ������ �� ������� ����.
public class StringUtil {
	public static String parseBr(String msg){
		
		if(msg == null) return null;
		
		return msg.replace("\r\n", "<br>")
                  .replace("\n", "<br>");
	}
}