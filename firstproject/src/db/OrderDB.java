package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDB {
	
	Connection con;
	public OrderDB(){
		
	}
	public String orderThink() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			
		}
		
		//2.연결 객체 얻어오기
			String url = "jdbc:oracle:thin:@192.168.0.104:1521:orcl";
			String user = "AREUM";
			String pass = "0611";
			con = DriverManager.getConnection(url, user, pass);
		
		String sql = "select o2.ono ono,m.mmenu||'외'||tot||'개' order1,o2.oetc oetc\r\n" + 
				"from (select count(o.oidx)-1 tot , min(o.oidx) oidx \r\n" + 
				"from ord o \r\n" + 
				"group by o.ono) o1,Menu m , Ord o2\r\n" + 
				"where o1.oidx = o2.oidx and o2.midx = m.midx and o2.ocookstate = '조리중'";
		String oetc = "";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) {
			oetc += "\n";
			oetc += "주문번호 : "+rs.getString("ono")+"\n"; 
			oetc += "주문내용  :"+rs.getString("order1")+"\n"; 
			oetc += "요구사항  : "+rs.getString("oetc")+"\n"; 
			oetc += "\n";
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//서버 초기화
		rs.close();
		con.close();
		
		return oetc;
	}//기타 주문 요구사항만을 threading 돌려 주방에서 확인 할수 있도록 한다.
	
	public ArrayList show() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			
		}
		int i = 0;
		ArrayList list = new ArrayList();
		String url = "jdbc:oracle:thin:@192.168.0.104:1521:orcl";
		String user = "AREUM";
		String pass = "0611";
		con = DriverManager.getConnection(url, user, pass);
		
		String sql = "select m.mmenu||'외'||tot||'개' order1,o2.oetc oetc,o2.ono ono  \r\n" + 
				"from (select count(o.oidx)-1 tot , min(o.oidx) oidx \r\n" + 
				"from ord o\r\n" + 
				"group by o.ono) o1,Menu m , Ord o2\r\n" + 
				"where o1.oidx = o2.oidx and o2.midx = m.midx and o2.ocookstate = '조리중' order by o2.ono";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("ono"));
			temp.add(rs.getString("order1"));
			temp.add(rs.getString("oetc"));
			temp.add("-");
			list.add(temp);
			i++;
		}
		rs.close();
		con.close();
		return list;
	}//주문한 내용을 주방에서 확인할수 있도록 데이터를 가져온다
	
	public void update(String number) throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
		
		}
		
		ArrayList list = new ArrayList();
		String url = "jdbc:oracle:thin:@192.168.0.104:1521:orcl";
		String user = "AREUM";
		String pass = "0611";
		con = DriverManager.getConnection(url, user, pass);
		
		String sql = "Update ord Set ocookstate = '조리완료' where ono = "+number;
		Statement st = con.createStatement();
		st.executeUpdate(sql);
		st.close();
	}//조리 완료가 된 내용은 table클릭을 통해 조리완료로 바뀐다
}
