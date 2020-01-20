package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import obejct.Decrease;

public class OrderListDB {
	Decrease dec;
	Connection con;
	DBCon db;

	public OrderListDB() {
		con = db.getInstance();
	}

	public ArrayList orderTable() throws SQLException {
		ArrayList list = new ArrayList();
		String sql = "select o2.ono ono,o2.odate odate ,m.mmenu||'외'||tot||'개' order1\r\n" + 
				"from (select count(o.oidx)-1 tot , min(o.oidx) oidx\r\n" + 
				"from ord o\r\n" + 
				"group by o.ono) o1,Menu m , Ord o2\r\n" + 
				"where o1.oidx = o2.oidx and o2.midx = m.midx";
					
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("ono"));
			temp.add(rs.getString("odate"));
			temp.add(rs.getString("order1"));
			
			list.add(temp);
		}
		return list;
	} //해당주문의 처음주문한 메뉴와 그외 메뉴를 표시하고 주문 번호를 표시한다



	public ArrayList repay(String value) throws SQLException {
		ArrayList list = new ArrayList();
		String sql2 = "select m.mmenu mmenu , m.mprice mprice , o.ocnt ocnt\r\n" + 
				"from ord o,menu m  where o.midx = m.midx and o.ono = "+value;
		System.out.println(sql2);
		Statement st2 = con.createStatement();
		ResultSet rs = st2.executeQuery(sql2);
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("mmenu"));
			temp.add(rs.getInt("mprice"));
			temp.add(rs.getInt("ocnt"));
			list.add(temp);
		}
		
		String sql = "delete from ord where ono = "+value;
		Statement st = con.createStatement();
		st.executeUpdate(sql);
		
		return list;
		
	}//제 결제를 눌렀을시에 해당하는 테이블의 주문이 취소된다

	public ArrayList receipt(String ono) throws SQLException {
		ArrayList list = new ArrayList();
		String sql = "select o2.ono ono,o2.odate odate, o2.opack opack,m.mmenu mmenu, m.mprice mprice,o2.ocnt cnt ,o2.eid eid \r\n" + 
				"from ord o2 , menu m \r\n" + 
				"where o2.ono = "+ono+" and o2.midx = m.midx";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("ono"));
			temp.add(rs.getString("odate"));
			temp.add(rs.getString("opack"));
			temp.add(rs.getString("mmenu"));
			temp.add(rs.getString("mprice"));
			temp.add(rs.getString("cnt"));
			temp.add(rs.getString("eid"));
			list.add(temp);
		}
		return list;
	}//주문 결제를 할시에 주문에 해당하는 모든 메뉴의 가격과 데이터를 가져온다
	
	

	
}
