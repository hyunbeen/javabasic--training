package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import obejct.Decrease;

public class CostGraphDB {
	Decrease dec;
	Connection con;
	DBCon db;

	public CostGraphDB() {
		con = db.getInstance();
	}

	public ArrayList showTable() throws SQLException {
		ArrayList list = new ArrayList();
		String sql = "select m1.mcategory mcategory, m1.mmenu mmenu, nvl(sum(o1.ocnt),0) sum" + 
				" from (select m.mcategory mcategory,m.mmenu mmenu,m.midx midx" + 
				" from Menu m) m1 , Ord o1" + 
				" where m1.midx = o1.midx(+)"+ "and to_char(o1.odate,'YYYY') = '2017' and to_char(o1.odate,'MM') = '09' " + " group by m1.mcategory,m1.mmenu"+" order by sum desc";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("mcategory"));
			temp.add(rs.getString("mmenu"));
			temp.add(rs.getString("sum"));
			list.add(temp);
		}
		return list;
	}//현재 월에 대한 판매량을 표시

	public ArrayList search(String string, String string2) throws SQLException {
		ArrayList list = new ArrayList();
		String sql = "select m1.mcategory mcategory, m1.mmenu mmenu, nvl(sum(o1.ocnt),0) sum" + 
				" from (select m.mcategory mcategory,m.mmenu mmenu,m.midx midx" + 
				" from Menu m) m1 , Ord o1" + 
				" where m1.midx = o1.midx(+)"+ "and to_char(o1.odate,'YYYY') = ? and to_char(o1.odate,'MM') = ? " + " group by m1.mcategory,m1.mmenu"+" order by sum desc";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, string);
		System.out.println(string);
		switch(string2) {
		case "1" : string2 = "01"; break;
		case "2" : string2 = "02"; break;
		case "3" : string2 = "03"; break;
		case "4" : string2 = "04"; break;
		case "5" : string2 = "05"; break;
		case "6" : string2 = "06"; break;
		case "7" : string2 = "07"; break;
		case "8" : string2 = "08"; break;
		case "9" : string2 = "09"; break;
		}
		System.out.println(string2);
		ps.setString(2, string2);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("mcategory"));
			temp.add(rs.getString("mmenu"));
			temp.add(rs.getString("sum"));
			list.add(temp);
		}
		return list;
	}//검색한 월에대한 판매를 표시
	
	

	
}
