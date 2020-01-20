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

	public LoginDB() {
		con = db.getInstance();
	}

	public Staff login(String eid, String pw) throws Exception {

		Staff staff = new Staff();

		String eposition = new String();
		String sql = "SELECT * FROM emp WHERE eid=? AND epw=? AND estate = 'Y'";
		// 재직상태가 참이고 아이디와 패스워드 존재시 로그인을 시켜준다

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, eid);
		ps.setString(2, pw);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

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

		} // 로그인한 직원의 정보를 가져온다

		return staff;
	}

	// 로그인시 wgodate insert 만약 같은 날짜에 woffdate가 null이라면 현재시간으로 update
	public void gooff(String id, String pass) throws SQLException {
		String sqlIn = "Insert into work(widx,wgodate,eid) Values(seq_widx.nextval,sysdate,?)";
		PreparedStatement psIn = con.prepareStatement(sqlIn);
		psIn.setString(1, id);
		psIn.executeUpdate();

		String sqlUp = "UPDATE work SET woffdate=sysdate WHERE eid = ? AND wgodate = sysdate AND woffdate is null";
		PreparedStatement psUp = con.prepareStatement(sqlUp);
		psUp.setString(1, id);
		psUp.executeUpdate();

		psIn.close();
		psUp.close();
	}

}
