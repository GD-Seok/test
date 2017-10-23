package com.board.dao;

import java.util.List;
import java.util.Map;

import com.board.domain.BoardCommand;

//�Խ����� ���������� ����ϴ� �߻� �޼ҵ�
public interface BoardDao {

	//�۸�� ����
	public List<BoardCommand> list(Map<String,Object> map); //�˻� �о�, �˻���
	
	//�� ���� -> ����¡ ó�� ������(�˻��о�, �˻���) -> �˻�� ���ؼ� ������ �޶�����.
	public int getRowCount(Map<String,Object> map);
	//�ִ� �� ��ȣ ������
	public int getNewSeq();
	//�Խ����� �۾���
	public void insert(BoardCommand board);
	
	//�Խù��� ���� �Խù�(���ڵ�) �Ѱ� ã��
	public BoardCommand selectBoard(Integer seq); //~(int seq);
	
	//�Խù� ��ȣ�� �ش��ϴ� ��ȸ�� ����
	public void updateHit(Integer seq);
	
	//�� �����ϱ�
	public void update(BoardCommand board);	
	
	//�� �����ϱ�
	public void delete(Integer seq);
	
	
}
