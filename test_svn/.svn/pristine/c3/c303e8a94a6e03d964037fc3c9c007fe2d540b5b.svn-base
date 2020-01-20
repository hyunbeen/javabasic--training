package db;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import obejct.Staff;

public class LoginDB {
	DBCon db;
	Connection con;
	public LoginDB(){
		con = db.getInstance();
	}

	
	public Staff login(String eid, String pw) throws Exception {
	
		Staff staff = new Staff();
	
		String eposition = new String();
		String sql = "SELECT * FROM emp WHERE eid=? AND epw=? AND estate = 'Y'";
		
		
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, eid);
		ps.setString(2, pw);
		ResultSet rs = ps.executeQuery();
		
		
		while(rs.next()) {
		
			staff.setEaddress(rs.getString("eaddress"));
			staff.setEgender(rs.getString("egender"));
			staff.setEh_wage(Integer.parseInt(rs.getString("eh_wage")));
			staff.setEid(rs.getString("eid"));
			staff.setEidentify(rs.getString("eidentify"));
			staff.setEname(rs.getString("ename"));
			staff.setEposition(rs.getString("eposition"));
			staff.setEtask(rs.getString("etask"));
			staff.setEtel(rs.getString("etel"));
			staff.setPw(rs.getString("epw"));
			
		}

		
		return staff;
	}


	public void gooff(String id, String pass) throws SQLException {
		String sql = "Select * from "
				+ "work where eid = '"+id+"'";
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet rs = st.executeQuery(sql);
		rs.last();
		System.out.println(rs.getRow());
		if(rs.getRow()!=1) {
			System.out.println("실행");
			String sql2 = "Insert into work(widx,wgodate,eid) Values(seq_widx.nextval,sysdate,?)";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, id);
			ps2.executeUpdate();
		}
		
		
	}


}













