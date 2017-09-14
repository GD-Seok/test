package gd;

import java.util.*; //List
import org.springframework.dao.DataAccessException; //spring ����ó�� Class

//�Խ��ǿ��� �������� ����� ��� -> �߻�޼ҵ� ����
public interface BoardDAO{
	
	//�ʿ��� �޼ҵ� ��ϵ�
	//1. �۸�Ϻ���
	public List list() throws DataAccessException;

	//2-1. �ִ밪 ���ϱ�
	public int getNewNum() throws DataAccessException;
	//2-2. �۾���
	public void write(BoardCommand data) throws DataAccessException;
	
	//3-1. �� ��ȸ�� �����ϱ�
	public void updateReadcnt(String num) throws DataAccessException;
	//3-2. �� �󼼺���
	public BoardCommand retrieve(String num) throws DataAccessException;
	
	//4. �� �����ϱ�
	public void update(BoardCommand data) throws DataAccessException;
	
	//5. �� �����ϱ�
	public void delete(String num) throws DataAccessException;
	
	//6. �� �˻��ϱ�	 (String searchName, String searchValue) ->HashMapó��, �׷���, �̹� BoardCommand �� �� �Ű������� �־�� 
	public List search(BoardCommand data) throws DataAccessException;
}









/*package gd;

import java.sql.*; //Connection, PreparedStatement,,
import java.util.*; //ArrayList, List ~
//---------------------------------------------------
//�߰� (JNDI ���)
import javax.sql.*; //DataSource ��ü -> getConnection()
import javax.naming.*; // Context(Interface�̴�.), InitialContext ��ü
								//lookup(ã�����ϴ� �̸�(JNDI��)) -> Ž���⿡�� �˻��ϴ� �Ͱ� ���� ����
//---------------------------------------------------
public class BoardDAO{
   
	DataSource ds; //has a ����
	
   public BoardDAO(){
		//������ : DataSource ��� : InitialContext�� JNDI��
	   try {
		 //InitialContext ctx=new InitialContext(); �̰͵� ����.
		 Context ctx=new InitialContext();
		 //lookup("java:comp/env/ã�����ϴ� JNDI�̸�")
		 ds=(DataSource)ctx.lookup("java:comp/env/jdbc/orcl");
		 System.out.println("ds : "+ds);
	   }catch(Exception e) { e.printStackTrace();		   
	   }
	}
	  
	public ArrayList  list(){  // ��Ϻ���
		
		ArrayList list = new ArrayList();
		try{
			String sql = "SELECT * FROM springboard ORDER BY num desc";
			Connection con = ds.getConnection();
			//Connection con = pool.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				BoardDTO data = new BoardDTO();
				data.setNum( rs.getInt( "num" ) );
				data.setAuthor(rs.getString( "author" ));
				data.setTitle(rs.getString( "title"));
				data.setContent(rs.getString( "content" ));
				data.setDate(rs.getString( "writeday" ));
				data.setReadcnt(rs.getInt( "readcnt" ));
				list.add( data );
			}//end while
			rs.close();	stmt.close(); con.close();
		}catch(Exception e){ e.printStackTrace(); }
		
		return  list;
	}//end list
	
	public int getNewNum(){ //�۾��� ��ȣ ��� -> �ִ밪 +1
		int newNum = 1;
		try {
			String sql = "select max(num) from springboard";
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs= stmt.executeQuery(sql);
			if(rs.next()) {
				newNum=rs.getInt(1)+1; //�����ʵ��̱� ������, �ʵ���� �� �� ��� ����(1)�� �־���.
			}
		}catch(Exception e){e.printStackTrace();}
		
	  	return newNum;
	}//end getNewNum();
	
	//public void write(BoardCommand boardDTO){} �� �����̴�. �׷���, 3���� �������� �ذ� ���� �������.
	public void write(String author, String title , String content){
		try{
			int newNum = getNewNum(); // �ִ밪�� ���ؿ��� �޼ҵ� ȣ��
			System.out.println("newNum : "+newNum);
			String sql ="insert into springboard(num,author,title,content) values(";
			sql +=  newNum + ",'" + author + "','" + title + "','" + content + "')";
			System.out.println(sql); //�ѱ� �������� Ȯ��
			
	  	  	Connection con = ds.getConnection();
	  	  	PreparedStatement stmt = con.prepareStatement(sql);
	  	  	stmt.execute(sql); // == stmt.executeUpdate(sql);
	  	  	stmt.close(); con.close();
	  	}catch(Exception e ) {e.printStackTrace();}
	}//end write
	
	//select * from springboard where num=?
	public BoardDTO retrieve(String num){ // �� �ڼ��� ���� -> ���� �� ���� ��ȸ�� ���� ��� 
		BoardDTO data = new BoardDTO();
		
		try {
			//1.��ȸ�� ������Ű��
			
			String sql = "update springboard set readcnt=readcnt+1 where num="+num;
			Connection con = ds.getConnection();			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			int update=stmt.executeUpdate(sql);
			System.out.println("BoardDAO ���� retrieve ��ȸ�� ��������(update) : "+update);
			
			stmt=null; //�� ���� stmt�� 2�� �� �� �ִ� ���.
			sql = "select * from springboard where num="+num;
			stmt=con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){				
				data.setNum( rs.getInt( "num" ) );
				data.setAuthor(rs.getString( "author" ));
				data.setTitle(rs.getString( "title"));
				data.setContent(rs.getString( "content" ));				
			}//end while
			rs.close();	stmt.close(); con.close();
		}catch(Exception e) {e.printStackTrace();}
		return data;
		
	}//end retrieve
   
	public void update( String num , String author, 
			            String title , String content){ // �� �����ϱ�
	     try{
		  String sql ="update springboard set title='" + title + "',";
		  sql += " content='" + content+"',";
		  sql += " author ='" + author+"'";
		  sql += " where num=" + num;
		  System.out.println(sql);

		  Connection con = ds.getConnection();
		  PreparedStatement stmt = con.prepareStatement(sql);  
		  stmt.executeUpdate(sql);
		  stmt.close();  con.close();
	     }catch(Exception e){e.printStackTrace();}
	  }//end update

	
	  public void delete( String num){ //�� �����ϱ�
		  try{
			  String sql ="delete springboard where num=" + num;
			  System.out.println(sql);

			  Connection con = ds.getConnection();
			  PreparedStatement stmt = con.prepareStatement(sql);  
			  int delete = stmt.executeUpdate(sql);
			  System.out.println("DAO���� delete �������� : "+delete);
			  stmt.close();  con.close();
		     }catch(Exception e){e.printStackTrace();}
		}//end delete

	  public ArrayList search( String name , String value ){ //�˻��ϱ�
		    ArrayList list = new ArrayList();
		    try{
		  	  String sql = "SELECT * FROM springboard";
			  sql += " WHERE  " + name + " LIKE  '%" + value + "%' "; 
			  System.out.println( sql );
		  
			  Connection con = ds.getConnection();
		    	  PreparedStatement stmt = con.prepareStatement(sql);
		    	  ResultSet rs = stmt.executeQuery( sql );
		    	  while( rs.next()){
		    		BoardDTO data = new BoardDTO();
		    		data.setNum(rs.getInt( "num" ));
		    		data.setAuthor(rs.getString( "author" ));
		    		data.setTitle(rs.getString( "title"));
		    		data.setContent(rs.getString( "content" ));
		    		data.setDate(rs.getString( "writeday" ));
		    		data.setReadcnt(rs.getInt( "readcnt" ));
		    		list.add( data );
		    	  }
		    	  rs.close();	stmt.close(); con.close();
		    	}catch( Exception e){ e.printStackTrace();}
		    	return list;
    }

}
*/