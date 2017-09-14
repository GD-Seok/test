package gd;

import java.util.*; //List
import org.springframework.dao.DataAccessException; //spring 예외처리 Class

//게시판에서 공통으로 사용할 기능 -> 추상메소드 선언
public interface BoardDAO{
	
	//필요한 메소드 목록들
	//1. 글목록보기
	public List list() throws DataAccessException;

	//2-1. 최대값 구하기
	public int getNewNum() throws DataAccessException;
	//2-2. 글쓰기
	public void write(BoardCommand data) throws DataAccessException;
	
	//3-1. 글 조회수 증가하기
	public void updateReadcnt(String num) throws DataAccessException;
	//3-2. 글 상세보기
	public BoardCommand retrieve(String num) throws DataAccessException;
	
	//4. 글 수정하기
	public void update(BoardCommand data) throws DataAccessException;
	
	//5. 글 삭제하기
	public void delete(String num) throws DataAccessException;
	
	//6. 글 검색하기	 (String searchName, String searchValue) ->HashMap처리, 그러나, 이미 BoardCommand 에 두 매개변수를 넣어둠 
	public List search(BoardCommand data) throws DataAccessException;
}









/*package gd;

import java.sql.*; //Connection, PreparedStatement,,
import java.util.*; //ArrayList, List ~
//---------------------------------------------------
//추가 (JNDI 방식)
import javax.sql.*; //DataSource 객체 -> getConnection()
import javax.naming.*; // Context(Interface이다.), InitialContext 객체
								//lookup(찾고자하는 이름(JNDI명)) -> 탐색기에서 검색하는 것과 같은 느낌
//---------------------------------------------------
public class BoardDAO{
   
	DataSource ds; //has a 관계
	
   public BoardDAO(){
		//생성자 : DataSource 얻기 : InitialContext와 JNDI명
	   try {
		 //InitialContext ctx=new InitialContext(); 이것도 가능.
		 Context ctx=new InitialContext();
		 //lookup("java:comp/env/찾고자하는 JNDI이름")
		 ds=(DataSource)ctx.lookup("java:comp/env/jdbc/orcl");
		 System.out.println("ds : "+ds);
	   }catch(Exception e) { e.printStackTrace();		   
	   }
	}
	  
	public ArrayList  list(){  // 목록보기
		
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
	
	public int getNewNum(){ //글쓰기 번호 얻기 -> 최대값 +1
		int newNum = 1;
		try {
			String sql = "select max(num) from springboard";
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs= stmt.executeQuery(sql);
			if(rs.next()) {
				newNum=rs.getInt(1)+1; //가상필드이기 때문에, 필드명을 쓸 수 없어서 순서(1)를 주었다.
			}
		}catch(Exception e){e.printStackTrace();}
		
	  	return newNum;
	}//end getNewNum();
	
	//public void write(BoardCommand boardDTO){} 가 정석이다. 그러나, 3개만 받으려고 밑과 같이 만들었다.
	public void write(String author, String title , String content){
		try{
			int newNum = getNewNum(); // 최대값을 구해오는 메소드 호출
			System.out.println("newNum : "+newNum);
			String sql ="insert into springboard(num,author,title,content) values(";
			sql +=  newNum + ",'" + author + "','" + title + "','" + content + "')";
			System.out.println(sql); //한글 깨지는지 확인
			
	  	  	Connection con = ds.getConnection();
	  	  	PreparedStatement stmt = con.prepareStatement(sql);
	  	  	stmt.execute(sql); // == stmt.executeUpdate(sql);
	  	  	stmt.close(); con.close();
	  	}catch(Exception e ) {e.printStackTrace();}
	}//end write
	
	//select * from springboard where num=?
	public BoardDTO retrieve(String num){ // 글 자세히 보기 -> 누를 때 마다 조회수 증가 기능 
		BoardDTO data = new BoardDTO();
		
		try {
			//1.조회수 증가시키기
			
			String sql = "update springboard set readcnt=readcnt+1 where num="+num;
			Connection con = ds.getConnection();			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			int update=stmt.executeUpdate(sql);
			System.out.println("BoardDAO 에서 retrieve 조회수 증가유무(update) : "+update);
			
			stmt=null; //한 개의 stmt을 2번 쓸 수 있는 방법.
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
			            String title , String content){ // 글 수정하기
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

	
	  public void delete( String num){ //글 삭제하기
		  try{
			  String sql ="delete springboard where num=" + num;
			  System.out.println(sql);

			  Connection con = ds.getConnection();
			  PreparedStatement stmt = con.prepareStatement(sql);  
			  int delete = stmt.executeUpdate(sql);
			  System.out.println("DAO에서 delete 성공유무 : "+delete);
			  stmt.close();  con.close();
		     }catch(Exception e){e.printStackTrace();}
		}//end delete

	  public ArrayList search( String name , String value ){ //검색하기
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