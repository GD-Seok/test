package com.apik.admin.service;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

import com.apik.admin.Command.PersonCommand;
import com.apik.admin.Dao.PersonDao;

//PersonDao�� ��ӹ޾� Person Table�� ���� ������ �����ϴ� �κ� �Դϴ�.
//PersonCommand�� xml���� �ڵ����� ��ӹް� �Ǿ��ֽ��ϴ�.

public class PersonDaoImpl extends SqlSessionDaoSupport implements PersonDao {

	public List personlist() throws DataAccessException {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("personlist");
	}
	
	public void personin(PersonCommand person) throws DataAccessException {
		// TODO Auto-generated method stub
		getSqlSession().insert("personin",person);
	}

}
