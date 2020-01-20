package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PayDB {

	Connection con;
	String sql = "";
	String url = "";
	String user = "";
	String pass = "";

	public PayDB(){
		
	}
	
	public String[] comList() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		url = "jdbc:oracle:thin:@192.168.0.104:1521:orcl";
		user = "AREUM";
		pass = "0611";
		con = DriverManager.getConnection(url, user, pass);
		int i = 0;
		String sql = "SELECT dname FROM discount ORDER BY dpersent";
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = st.executeQuery(sql);
		rs.last();
		int count = rs.getRow();
		String [] colName = new String[count];
		rs.first();rs.previous();
		
		
		while (rs.next()) {
			colName[i] = rs.getString("DNAME");
			i++;		
		}
		return colName;
	}

	public int decSearch(String dname) throws NumberFormatException, SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		url = "jdbc:oracle:thin:@192.168.0.104:1521:orcl";
		user = "AREUM";
		pass = "0611";
		con = DriverManager.getConnection(url, user, pass);
		String sql = "SELECT dpersent FROM discount where dname = '"+dname+"'";
		System.out.println(sql);
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		int disnum = Integer.parseInt(rs.getString("dpersent"));
		return disnum;
	}//해당하는 할인이름의 할인되는 비율을 가져오는 디비

	

	public void order(String stretc, String ono, ArrayList list, String eid,String pack) throws SQLException, ClassNotFoundException {
		String mmenu;
		int midx;
		
		PreparedStatement ps;
		for(int i=0;i<list.size();i++) {
			Class.forName("oracle.jdbc.driver.OracleDriver");
				url = "jdbc:oracle:thin:@192.168.0.104:1521:orcl";
				user = "AREUM";
				pass = "0611";
				con = DriverManager.getConnection(url, user, pass);
			ArrayList temp = (ArrayList)list.get(i);
			mmenu = String.valueOf(temp.get(0)); 
		
			midx = getMmenu(mmenu);
			
			sql = "Insert into ord(oidx,ono,ocnt,odate,opack,oetc,ocookstate,eid,midx) values(seq_ord_oidx.nextval,?,?,sysdate,?,?,'조리중',?,?)";
			ps =  con.prepareStatement(sql);
			
			ps.setString(1, ono);
			
			ps.setString(2,String.valueOf(temp.get(2)));
		
			ps.setString(3,pack);
		
			ps.setString(4,stretc);
		
			ps.setString(5,eid);
		
			ps.setInt(6,midx);
			
			ps.executeUpdate();
			
			con.close();
			
		} 
		
	} //결제를 눌렀을시 주문 데이터를 집어 넣는다

	public int getMmenu(String mmenu) throws SQLException {
		String sql = "Select midx from menu where mmenu = '"+mmenu+"'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		return Integer.parseInt(rs.getString("midx"));
	}//메뉴 이름에 해당하는 메뉴 아이디를 가져온다

	
	public void pay(String how, int cost, String text) throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		url = "jdbc:oracle:thin:@192.168.0.104:1521:orcl";
		user = "AREUM";
		pass = "0611";
		con = DriverManager.getConnection(url, user, pass);
	
	
		sql = "Insert into pay(pidx,pno,payment,pprice) values(seq_pay.nextval,?,?,?)";
		PreparedStatement ps =  con.prepareStatement(sql);
		ps.setString(1, text);
		ps.setString(2, how);
		ps.setInt(3, cost);
		ps.executeUpdate();
		
	}//결제를 눌렀을시 결제 데이터를 집어 넣는다

}
