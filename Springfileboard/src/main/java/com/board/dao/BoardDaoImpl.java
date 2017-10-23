package com.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.board.domain.BoardCommand;

public class BoardDaoImpl extends SqlSessionDaoSupport implements BoardDao {

	//SqlSession sqlSession �� ������� �ʾƵ�, ��� �޾� getSqlSession�޼ҵ带 ����� ���ִ�.
	//ListController���� ȣ�� -> keyField, keyWord, start, end (����¡ó��)
	public List<BoardCommand> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("BoardDaoImpl Ŭ������ list()ȣ���!");
		List<BoardCommand> list=getSqlSession().selectList("selectList", map);
		System.out.println("list==>"+list);
		System.out.println("list() ȣ���!");
		return list;
	}

	public int getRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("getRowCount() ȣ���!");
		return getSqlSession().selectOne("selectCount",map);
	}
	
	//�ִ밪
	public int getNewSeq() {
		// TODO Auto-generated method stub
		//ó���� Object ���� �޾ƿ��� �� Object -> Integer -> int ���� ����ȯ �ؾߵ�����, �ڵ����� ��
		System.out.println("getSqlSession()=>"+getSqlSession());
		int newseq=getSqlSession().selectOne("getNewSeq");
		System.out.println("getNewSeq()�� newseq : "+newseq);
		return newseq;
	}
	
	//�۾���
	public void insert(BoardCommand board) {
		// TODO Auto-generated method stub
		getSqlSession().insert("insertBoard",board);
	}
	
	
	//�Խù��� ���� �Խù�(���ڵ�) �Ѱ� ã��
	public BoardCommand selectBoard(Integer seq) {
		// TODO Auto-generated method stub
		return (BoardCommand)getSqlSession().selectOne("selectBoard",seq);
	}
	
	//�Խù� ��ȣ�� �ش��ϴ� ��ȸ�� ����
	public void updateHit(Integer seq) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateHit", seq);
	}
	
	//����
	public void update(BoardCommand board) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateBoard",board);
	}
	
	//����
	public void delete(Integer seq) {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteBoard",seq);
	}
}