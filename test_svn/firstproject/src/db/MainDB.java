package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainDB {
	DBCon db;
	Connection con;
	public MainDB(){
		con = db.getInstance();
	}

	
	public ArrayList selectByCategory(String category) throws Exception{
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
	
	public void gooff(String id) throws SQLException {
		String sql = "update work set woffdate = sysdate where eid = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ps.executeUpdate();
	
	}
}
