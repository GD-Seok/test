package com.apik.tiles.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apik.tiles.service.BoardService;
import com.apik.tiles.service.dao.BoardDAO;
import com.apik.tiles.vo.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardMapper;
	
	@Override
	@Transactional
	public List<BoardVO> selectBoardList() throws Exception {
		
		return boardMapper.selectBoardList();
		
	}
	
	@Override
	@Transactional
	public BoardVO selectBoardView(Integer num) throws Exception {
		return boardMapper.selectBoardView(num);
	}

}
