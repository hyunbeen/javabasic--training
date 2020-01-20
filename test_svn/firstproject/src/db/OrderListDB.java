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
		String sql = "select o2.odate odate , m.mmenu||'외'||tot||'개' order1, p.payment pay"+ 
					" from (select count(o.oidx)-1 tot , min(o.oidx) oidx from ord o group by o.ono) o1, Pay p , Menu m , Ord o2"+
					" where o1.oidx = p.oidx and o1.oidx = o2.oidx and o2.midx = m.midx";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("odate"));
			temp.add(rs.getString("order1"));
			temp.add(rs.getString("pay"));
			list.add(temp);
		}
		return list;
	}
	
	

	
}
