package db;

import java.sql.*;
import java.util.*;

public class GoOutDB {

	Connection con;

	public GoOutDB() throws Exception {

		con = DBCon.getInstance();

	}
	
	public ArrayList setTable() throws Exception {
		ArrayList list = new ArrayList();
		String sql = "SELECT * FROM v_worklist";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("WDATE"));
			temp.add(rs.getString("EID"));
			temp.add(rs.getString("ENAME"));
			temp.add(rs.getString("GOTIME"));
			temp.add(rs.getString("OFFTIME"));
			
			list.add(temp);
		}
		return list;
	}

	public ArrayList Search (int idx, String word) throws Exception {
		ArrayList list = new ArrayList();
		String [] colName = {"wdate", "eid", "ename"};
		String sql = "SELECT * FROM v_worklist WHERE " + colName[idx] + "='"+word+"'";

		PreparedStatement ps = con.prepareStatement(sql);
		System.out.println(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("WDATE"));
			temp.add(rs.getString("EID"));
			temp.add(rs.getString("ENAME"));
			temp.add(rs.getString("GOTIME"));
			temp.add(rs.getString("OFFTIME"));
			list.add(temp);
		}
		return list;

	}
}
