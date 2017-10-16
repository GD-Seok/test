package com.apik.tour.person.service;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.apik.tour.person.dao.PersonDao;

//PersonDao�� ��ӹ޾� Person Table�� ���� ������ �����ϴ� �κ� �Դϴ�.
//PersonCommand�� xml���� �ڵ����� ��ӹް� �Ǿ��ֽ��ϴ�.


public class PersonDaoImpl extends SqlSessionDaoSupport implements PersonDao {
	@Override
	public List personlist() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("personlist");
	}
}
