package com.apik.tiles.service;

import java.util.List;

import com.apik.tiles.vo.BoardVO;

public interface BoardService {
	List<BoardVO> selectBoardList() throws Exception;
	BoardVO selectBoardView(Integer num) throws Exception;
}
