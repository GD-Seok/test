package com.apik.tiles.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.apik.tiles.vo.BoardVO;

public interface BoardDAO {
	List<BoardVO> selectBoardList() throws Exception;
	BoardVO selectBoardView(@Param(value = "num") Integer num) throws Exception;
}
