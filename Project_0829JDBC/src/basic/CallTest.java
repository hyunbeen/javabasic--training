package basic;

import java.sql.*;

public class CallTest {

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
		
		//3.sql 문장 만들기
		
		String sql="{call ins_account(?,?,?)}";
		//4.전송객체 얻어오기
		System.out.println(sql);
		CallableStatement cs = con.prepareCall(sql);
		cs.setString(1, "333-33-33");
		cs.setString(2, "김씨");
		cs.setInt(3, 50000);
		//5.전송
		int result = cs.executeUpdate();
		System.out.println(result);
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
