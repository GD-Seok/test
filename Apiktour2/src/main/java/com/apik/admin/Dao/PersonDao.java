package com.apik.admin.Dao;

import java.util.*;

import org.springframework.dao.DataAccessException;
import com.apik.admin.Command.*;

//Person Table의 동작을 설정하는 DAO 선언 부분 입니다.
public interface PersonDao {
	//1. 회원 목록
	public List personlist() throws DataAccessException;
	//2. 회원 등록
	public  void personin(PersonCommand person) throws DataAccessException;
	//3. 회원 정보 보기
	//4. 회원 정보 수정
	//5. 회원 삭제
	//6. 회원 id로 검색
}
