package com.apik.admin.Dao;

import java.util.*;

import org.springframework.dao.DataAccessException;
import com.apik.admin.Command.*;

//Person Table�� ������ �����ϴ� DAO ���� �κ� �Դϴ�.
public interface PersonDao {
	//1. ȸ�� ���
	public List personlist() throws DataAccessException;
	//2. ȸ�� ���
	public  void personin(PersonCommand person) throws DataAccessException;
	//3. ȸ�� ���� ����
	//4. ȸ�� ���� ����
	//5. ȸ�� ����
	//6. ȸ�� id�� �˻�
}
