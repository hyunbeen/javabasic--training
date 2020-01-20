package db;

import java.sql.*;
import java.util.*;

public class PayDB {

	Connection con;

	public PayDB(){
		con = DBCon.getInstance();
	}
	
	public String[] comList() throws Exception {
		
		int i = 0;
		String sql = "SELECT dname FROM discount ORDER BY didx";
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

}
