package com.board.dao;

import java.util.List;
import java.util.Map;

import com.board.domain.BoardCommand;

//게시판의 공통적으로 사용하는 추상 메소드
public interface BoardDao {

	//글목록 보기
	public List<BoardCommand> list(Map<String,Object> map); //검색 분야, 검색어
	
	//총 갯수 -> 페이징 처리 때문에(검색분야, 검색어) -> 검색어에 대해서 개수가 달라진다.
	public int getRowCount(Map<String,Object> map);
	//최대 글 번호 얻어오기
	public int getNewSeq();
	//게시판의 글쓰기
	public void insert(BoardCommand board);
	
	//게시물에 대한 게시물(레코드) 한개 찾기
	public BoardCommand selectBoard(Integer seq); //~(int seq);
	
	//게시물 번호에 해당하는 조회수 증가
	public void updateHit(Integer seq);
	
	//글 수정하기
	public void update(BoardCommand board);	
	
	//글 삭제하기
	public void delete(Integer seq);
	
	
}
