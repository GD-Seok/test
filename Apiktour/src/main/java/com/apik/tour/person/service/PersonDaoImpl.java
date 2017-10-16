package com.apik.tour.person.service;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.apik.tour.person.dao.PersonDao;

//PersonDao를 상속받아 Person Table의 상세한 동작을 설정하는 부분 입니다.
//PersonCommand는 xml에서 자동으로 상속받게 되어있습니다.


public class PersonDaoImpl extends SqlSessionDaoSupport implements PersonDao {
	@Override
	public List personlist() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("personlist");
	}
}
