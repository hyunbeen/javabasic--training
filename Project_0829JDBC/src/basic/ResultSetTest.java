package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetTest {

	public static void main(String[] args) {
		Connection con = null;
	
		//1.드라이버를 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//2.연결 객체 얻어오기
			String url = "jdbc:oracle:thin:@192.168.0.156:1521:orcl";
			String user = "scott";
			String pass = "tiger";
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("연결성공");
		
		//3.sql 문장 만들기
		
		String sql = "SELECT * FROM account";
		//4.전송객체 얻어오기
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet rs = st.executeQuery(sql);
//		rs.last();
//		int count = rs.getRow();
//		System.out.println(count + "행");
//		rs.first();
//		System.out.print(rs.getString("acnum"));
//		System.out.print(rs.getString("acname"));
//		System.out.println(rs.getString("amount"));
		while(rs.next()) {
			System.out.print(rs.getString("acnum"));
			rs.updateInt(3, 100);
			rs.updateRow();
		};
//		System.out.println("---------------------------");
//		while(rs.previous()) {
//			System.out.print(rs.getString("acnum"));
//			System.out.print(rs.getString("acname"));
//			System.out.println(rs.getString("amount"));
//		};
//		
		//5.전송
	
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
