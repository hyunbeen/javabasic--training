package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class PreStatFirst {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement st = null;
		//1.드라이버를 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//2.연결 객체 얻어오기
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			String user = "scott";
			String pass = "tiger";
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("연결성공");
		//3.sql 만들기
		
		int sabun = 2001;	
		String ilum ="갑순이2";
		int wolgub = 5000;
		
		String sql = "update emp set ename = ? , sal = ? where empno = ? ";
		System.out.println(">"+sql);
		//4.sql 전송 객체 얻어오기 - statement /preparedstatement /callablestatement
		st = con.prepareStatement(sql);
		st.setString(1,ilum);
		st.setInt(2,wolgub);
		st.setInt(3,sabun);
		//5.sql 전송
		int result = st.executeUpdate();
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
