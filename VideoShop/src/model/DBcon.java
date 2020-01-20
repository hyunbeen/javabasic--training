package model;

import java.sql.Connection;
import java.sql.DriverManager;

class DBCon{
	static DBCon db;
	static Connection con;
	
	private DBCon(){
		//1.드라이버를 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//2.연결 객체 얻어오기
			String url = "jdbc:oracle:thin:@192.168.0.104:1521:orcl";
			String user = "AREUM";
			String pass = "0611";
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("연결성공");
		
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
			 System.out.println("접속에러");
				System.out.println(e.getMessage());
		
			}
		
	}
	
	public static Connection getInstance(){
	
		if(db==null){
			db = new DBCon();
			return con;
		}
		else {
				return con;
		}
	}
	
}