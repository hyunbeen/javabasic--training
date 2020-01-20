package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class MonthGraphDB {
	DBCon db;
	Connection con;
	public MonthGraphDB(){
		con = db.getInstance();
	}
	public int input(String month) throws SQLException {
	
		String sql = "select sum(msum) msum\r\n" + 
				"from\r\n" + 
				"(select t.mcategory,t.mmenu,t.count,m2.mprice,t.count * m2.mprice msum \r\n" + 
				"from (select m1.mcategory mcategory, m1.mmenu mmenu, sum(o1.ocnt) count \r\n" + 
				"from (select m.mcategory mcategory,m.mmenu mmenu,m.midx midx\r\n" + 
				"from Menu m) m1 , Ord o1\r\n" + 
				"where m1.midx = o1.midx(+) and to_char(o1.odate,'YYYY') = '2017' and to_char(o1.odate,'MM') = ? \r\n" + 
				"group by m1.mcategory,m1.mmenu)t , Menu m2\r\n" + 
				"where t.mmenu = m2.mmenu) t2";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, month);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt("msum");
	}
	public int binput(String month) throws SQLException {
		
		String sql = "select sum(msum) msum\r\n" + 
				"from\r\n" + 
				"(select t.mcategory,t.mmenu,t.count,m2.mprice,t.count * m2.mprice msum \r\n" + 
				"from (select m1.mcategory mcategory, m1.mmenu mmenu, sum(o1.ocnt) count \r\n" + 
				"from (select m.mcategory mcategory,m.mmenu mmenu,m.midx midx\r\n" + 
				"from Menu m) m1 , Ord o1\r\n" + 
				"where m1.midx = o1.midx(+) and to_char(o1.odate,'YYYY') = '2017' and to_char(o1.odate,'MM') = ? and mmenu = '드링크' \r\n" + 
				"group by m1.mcategory,m1.mmenu)t , Menu m2\r\n" + 
				"where t.mmenu = m2.mmenu) t2";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, month);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt("msum");
	}
	public int iinput(String month) throws SQLException {
		
		String sql = "select sum(msum) msum\r\n" + 
				"from\r\n" + 
				"(select t.mcategory,t.mmenu,t.count,m2.mprice,t.count * m2.mprice msum \r\n" + 
				"from (select m1.mcategory mcategory, m1.mmenu mmenu, sum(o1.ocnt) count \r\n" + 
				"from (select m.mcategory mcategory,m.mmenu mmenu,m.midx midx\r\n" + 
				"from Menu m) m1 , Ord o1\r\n" + 
				"where m1.midx = o1.midx(+) and to_char(o1.odate,'YYYY') = '2017' and to_char(o1.odate,'MM') = ? and mmenu = '사이드메뉴' \r\n" + 
				"group by m1.mcategory,m1.mmenu)t , Menu m2\r\n" + 
				"where t.mmenu = m2.mmenu) t2";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, month);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt("msum");
	}
	public int buinput(String month) throws SQLException {
		
		String sql = "select sum(msum) msum\r\n" + 
				"from\r\n" + 
				"(select t.mcategory,t.mmenu,t.count,m2.mprice,t.count * m2.mprice msum \r\n" + 
				"from (select m1.mcategory mcategory, m1.mmenu mmenu, sum(o1.ocnt) count \r\n" + 
				"from (select m.mcategory mcategory,m.mmenu mmenu,m.midx midx\r\n" + 
				"from Menu m) m1 , Ord o1\r\n" + 
				"where m1.midx = o1.midx(+) and to_char(o1.odate,'YYYY') = '2017' and to_char(o1.odate,'MM') = ? and mmenu = '햄버거'  \r\n" + 
				"group by m1.mcategory,m1.mmenu)t , Menu m2\r\n" + 
				"where t.mmenu = m2.mmenu) t2";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, month);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt("msum");
	}
	public int sinput(String month) throws SQLException {
		
		String sql = "select sum(msum) msum\r\n" + 
				"from\r\n" + 
				"(select t.mcategory,t.mmenu,t.count,m2.mprice,t.count * m2.mprice msum \r\n" + 
				"from (select m1.mcategory mcategory, m1.mmenu mmenu, sum(o1.ocnt) count \r\n" + 
				"from (select m.mcategory mcategory,m.mmenu mmenu,m.midx midx\r\n" + 
				"from Menu m) m1 , Ord o1\r\n" + 
				"where m1.midx = o1.midx(+) and to_char(o1.odate,'YYYY') = '2017' and to_char(o1.odate,'MM') = ? and mmenu = '세트' \r\n" + 
				"group by m1.mcategory,m1.mmenu)t , Menu m2\r\n" + 
				"where t.mmenu = m2.mmenu) t2";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, month);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt("msum");
	}
}
