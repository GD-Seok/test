package com.apik.tour.person.dao;

import java.util.*;
import org.springframework.dao.DataAccessException;

//Person Table�� ������ �����ϴ� DAO ���� �κ� �Դϴ�.
public interface PersonDao {
	//1. ȸ�� ���
	public List personlist() throws DataAccessException;
	//2. ȸ�� ���
	//3. ȸ�� ���� ����
	//4. ȸ�� ���� ����
	//5. ȸ�� ����
	//6. ȸ�� id�� �˻�
}
