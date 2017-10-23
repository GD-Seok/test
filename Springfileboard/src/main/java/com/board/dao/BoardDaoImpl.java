package com.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.board.domain.BoardCommand;

public class BoardDaoImpl extends SqlSessionDaoSupport implements BoardDao {

	//SqlSession sqlSession 을 사용하지 않아도, 상속 받아 getSqlSession메소드를 사용할 수있다.
	//ListController에서 호출 -> keyField, keyWord, start, end (페이징처리)
	public List<BoardCommand> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("BoardDaoImpl 클래스의 list()호출됨!");
		List<BoardCommand> list=getSqlSession().selectList("selectList", map);
		System.out.println("list==>"+list);
		System.out.println("list() 호출됨!");
		return list;
	}

	public int getRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("getRowCount() 호출됨!");
		return getSqlSession().selectOne("selectCount",map);
	}
	
	//최대값
	public int getNewSeq() {
		// TODO Auto-generated method stub
		//처음에 Object 값을 받아오게 됨 Object -> Integer -> int 으로 형변환 해야되지만, 자동으로 됨
		System.out.println("getSqlSession()=>"+getSqlSession());
		int newseq=getSqlSession().selectOne("getNewSeq");
		System.out.println("getNewSeq()의 newseq : "+newseq);
		return newseq;
	}
	
	//글쓰기
	public void insert(BoardCommand board) {
		// TODO Auto-generated method stub
		getSqlSession().insert("insertBoard",board);
	}
	
	
	//게시물에 대한 게시물(레코드) 한개 찾기
	public BoardCommand selectBoard(Integer seq) {
		// TODO Auto-generated method stub
		return (BoardCommand)getSqlSession().selectOne("selectBoard",seq);
	}
	
	//게시물 번호에 해당하는 조회수 증가
	public void updateHit(Integer seq) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateHit", seq);
	}
	
	//수정
	public void update(BoardCommand board) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateBoard",board);
	}
	
	//삭제
	public void delete(Integer seq) {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteBoard",seq);
	}
}