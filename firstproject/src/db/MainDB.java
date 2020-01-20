package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainDB {
	DBCon db;
	Connection con;
	public MainDB(){
		con = db.getInstance();
	}

	
	public ArrayList selectByCategory(String category) throws Exception{ //선택한 카테고리의 메뉴명과 값을 가져오기 위함
		ArrayList list = new ArrayList();
		String sql = "SELECT mmenu, mprice FROM menu WHERE mcategory = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, category);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("mmenu"));
			temp.add(rs.getInt("mprice"));
			list.add(temp);
		}
		
		return list;
	}
	
	public void gooff(String id) throws SQLException { //퇴근 도장을 찍기위함
		String sql = "update work set woffdate = sysdate where eid = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ps.executeUpdate();
	
	}


	public ArrayList printRec() throws SQLException {
		ArrayList list = new ArrayList();
		String sql = "select o2.ono ono,o2.odate odate, o2.opack opack,m.mmenu mmenu, m.mprice mprice,o2.ocnt cnt ,o2.eid eid \r\n" + 
				"from (select max(ono) maxono\r\n" + 
				"from ord)o1 , ord o2 , menu m\r\n" + 
				"where o2.ono = o1.maxono and o2.midx = m.midx"; //조인하여 해당하는 메뉴의 이름과 가격을 가져온다
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
	} //이전영수증을 출력하기 위함
}
