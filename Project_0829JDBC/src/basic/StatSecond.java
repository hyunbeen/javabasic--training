package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class StatSecond {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		//1.드라이버를 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//2.연결 객체 얻어오기
			String url = "jdbc:oracle:thin:@192.168.0.156:1521:orcl";
			String user = "scott";
			String pass = "tiger";
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("연결성공");
		//3.sql 만들기
		
		
		
		String sql = "SELECT * FROM emp where ename = 'SMITH'";
		System.out.println(">"+sql);
		//4.sql 전송 객체 얻어오기 - statement /preparedstatement /callablestatement
		st = con.createStatement();
	
		//5.sql 전송
		ResultSet rs = st.executeQuery(sql);
		//6.결과처리
		while(rs.next()) {
			System.out.print(rs.getInt("empno")+" ");
			System.out.print(rs.getString("ename")+" ");
			System.out.print(rs.getString("job")+" ");
			System.out.println();
		}
		
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				//7.닫기
				try {
				st.close();
				con.close();
				}catch(Exception ex) {
					
				}
			}
	}
}
