package gd;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

public class SqlMapBoardDao extends SqlSessionDaoSupport implements BoardDAO {

	//sqlSession sqlSession ������� ���� X
	//�� ��Ϻ��� -> ListActionController -> list() ȣ��
	@Override
	public List list() throws DataAccessException {
		// TODO Auto-generated method stub
		//����) sqlSession ��ü��.selectOne(�����ų sql������ id��, �Ű�������~) -> ���ڵ� 1��
		//����) sqlSession ��ü��.selectList(�����ų sql������ id��, �Ű�������~) -> ���ڵ� ������
		return getSqlSession().selectList("list");
	}
	
	@Override
	public int getNewNum() throws DataAccessException {
		// TODO Auto-generated method stub
		//�޸𸮻��� ��(Object)->(Integer)->int
		//id���� �ν�X->���ӽ����̽��̸�.�����ų id�� �ο�
		//<mapper namespace="board">=>board.getNewNum
		return (Integer)getSqlSession().selectOne("getNewNum");
	}
	
	@Override
	public void write(BoardCommand data) throws DataAccessException {
		// TODO Auto-generated method stub
		//insert�±�->����)sqlSession��ü��.insert("�����ų id",�Ű�������)
		getSqlSession().insert("write",data);
	}
	
	//3-1. �� ��ȸ�� �����ϱ�
	@Override
	public void updateReadcnt(String num) throws DataAccessException {
		// TODO Auto-generated method stub
		//update �±� -> ����)sqlSession��ü��.update("�����ų id", �Ű�������)
		getSqlSession().update("updateReadcnt",num);		
	}
		
	//3-2. �� �󼼺���
	@Override
		public BoardCommand retrieve(String num) throws DataAccessException {
			// TODO Auto-generated method stub
			return (BoardCommand)getSqlSession().selectOne("retrieve",num);
		}	
	//4. �� �����ϱ�
	@Override
		public void update(BoardCommand data) throws DataAccessException {
			// TODO Auto-generated method stub
			getSqlSession().update("update",data);			
		}	
	//5. �� �����ϱ�
	@Override
		public void delete(String num) throws DataAccessException {
			// TODO Auto-generated method stub
			getSqlSession().delete("delete", num);			
		}
	
	//6. �� �˻��ϱ�
	@Override
	public List search(BoardCommand data) throws DataAccessException {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("search", data);
	}
}
