package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomDB {
	Connection con;
	DBCon db;

	public CustomDB(){
		con = db.getInstance();
	}

	public String labelimage(String mmenu) throws SQLException {
		String sql = "select mphoto_url from menu where mmenu = '"+mmenu+"'";
		System.out.println(sql);
		Statement st =con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		return rs.getString("mphoto_url");
	}//메뉴 이름에 해당하는 path를 리턴한다

	public String labelSet(String mmenu) throws SQLException {
		String sql = "select mphoto_url from menu where mmenu = '"+mmenu+"'";
		
		Statement st =con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		return rs.getString("mphoto_url");
		
	}//table의 이름에 해당하는 이미지를 차례대로 셋팅

	public String popular(String mmenu) throws SQLException {
		String token = null;
		String sql = "select sum(o.ocnt) sum\r\n" + 
				"from ord o, menu m\r\n" + 
				"where o.midx = m.midx and m.mmenu = '"+mmenu+"'";
		Statement st =con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		if(rs.getInt("sum")>15) {
			token = "*";
		}
		return token;
	}//메뉴의 판매 개수를 비교하여 인기메뉴를 설정한다

}
