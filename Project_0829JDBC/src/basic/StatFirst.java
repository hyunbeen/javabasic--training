package basic;

import java.sql.*;
public class StatFirst {
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
		String sql = "delete from emp where ename like '%홍홍홍%' ";
		//4.sql 전송 객체 얻어오기 - statement /preparedstatement /callablestatement
		st = con.createStatement();
		//5.sql 전송
		int result = st.executeUpdate(sql);
		//6.결과처리
		System.out.println(result+"행을 입력");
		
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
