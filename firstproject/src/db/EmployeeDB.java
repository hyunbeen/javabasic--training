package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import obejct.Staff;

public class EmployeeDB {
	DBCon db;
	Connection con;
	public EmployeeDB(){
		con = db.getInstance();
	}
	public void Insert(Staff staff) throws SQLException {
		String sql = "Insert into Emp(eid,epw,ename,etel,eidentify,egender,eaddress,eh_wage,etask,eposition) values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, staff.getEid());
		ps.setString(2, staff.getPw());
		ps.setString(3, staff.getEname());
		ps.setString(4, staff.getEtel());
		ps.setString(5, staff.getEidentify());
		ps.setString(6, staff.getEgender());
		ps.setString(7, staff.getEaddress());
		ps.setInt(8, staff.getEh_wage());
		ps.setString(9,staff.getEtask());
		ps.setString(10,staff.getEposition());
		ps.executeUpdate();
	}
	public void Delete(String text) throws SQLException {
		String sql = "Update Emp set estate = 'N' where eid = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,text);
		ps.executeUpdate();
		
	}
	
	// TEXTFIELD에서 검색
	// JTABLE에서 TEXTFIELD로 값 가져오기
	public Staff Search(String eid) throws SQLException {
		Staff staff = new Staff();
		
		String sql = "Select * from Emp where eid = '" + eid + "'" + "and estate = 'Y'";
		Statement st = con.createStatement();
		ResultSet rs =st.executeQuery(sql);
		rs.next();
		staff.setEid(rs.getString("eid"));
		staff.setPw(rs.getString("epw"));
		staff.setEname(rs.getString("ename"));
		staff.setEtel(rs.getString("etel"));
		staff.setEidentify(rs.getString("eidentify"));
		staff.setEgender(rs.getString("egender"));
		staff.setEaddress(rs.getString("eaddress"));
		staff.setEh_wage(Integer.parseInt(rs.getString("eh_wage")));
		staff.setEtask(rs.getString("etask"));
		staff.setEposition(rs.getString("eposition"));
		return staff;
	}
	
	public ArrayList Show() throws SQLException {
		ArrayList list = new ArrayList();
		int i = 1;
		String sql = "Select eid,ename from Emp where estate ='Y'";
		Statement st = con.createStatement();
		ResultSet rs =st.executeQuery(sql);
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(i);
			temp.add(rs.getString("eid"));
			temp.add(rs.getString("ename"));
			list.add(temp);
			i++;
		}
		
		return list;
	}
	public ArrayList Table(String search,String text2) throws SQLException {
		ArrayList list = new ArrayList();
		int i = 1;
		String text = "";
		if(search.equals("ID")) {
			text = "eid";
		}else if(search.equals("NAME")) {
			text = "ename";
		}
		String sql = "Select eid,ename from Emp where "+text+" = "+"'"+text2+"'" +"and estate = 'Y'";
		System.out.println(sql);
		Statement st = con.createStatement();
		ResultSet rs =st.executeQuery(sql);
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(i);
			temp.add(rs.getString("eid"));
			temp.add(rs.getString("ename"));
			list.add(temp);
			i++;
		}
		return list;
	}
	
	// 수정
	public void Modify(Staff staff, String text) throws SQLException {
		String sql = "Update Emp Set epw = ?,ename = ?,etel = ?,eidentify = ?,egender = ?,eaddress = ?,eh_wage = ?,etask = ?,eposition = ? where eid = ? and estate = 'Y'";
		System.out.println(sql);
		PreparedStatement ps = con.prepareStatement(sql);
		
		
		
		ps.setString(1, staff.getPw());
		ps.setString(2, staff.getEname());
		ps.setString(3, staff.getEtel());
		ps.setString(4, staff.getEidentify());
		ps.setString(5, staff.getEgender());
		ps.setString(6, staff.getEaddress());
		ps.setInt(7, staff.getEh_wage());
		ps.setString(8,staff.getEtask());
		ps.setString(9,staff.getEposition());
		ps.setString(10, staff.getEid());
		ps.executeUpdate();
	}

}
