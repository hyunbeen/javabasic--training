package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextField;


public class RentModel {
	
	
	Connection con;
	
	public RentModel() throws Exception{
		
		con = DBCon.getInstance();
	}

	
	public String nameSearch(String text) throws SQLException {
		String name = "";
		String sql = "Select c_name from Cus_Info where tel_id = ? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, text);
		ResultSet rs = ps.executeQuery();
		rs.next();
		name = rs.getString("c_name");
		return name;
	}


	public int rent(String tel, int vnum) throws SQLException {
		String sql1 = "SELECT * FROM Rent_info WHERE video_id = "+vnum+" and return_YN = 'N'";
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = st.executeQuery(sql1);
		rs.last();
		
	
		if(rs.getRow()>0) {
			return 1;
		}else {
			String sql = "Insert into Rent_info(rent_id, rent_date, return_date, return_pre_date, return_YN, tel_id, video_id) "
					+ " Values(seq_rent_id.nextval, sysdate, null,sysdate+3, 'N' , ?, ?)";
		
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(tel));
			ps.setInt(2, vnum);
			ps.executeUpdate();
			return 0;
		}
		
	}


	public ArrayList search() throws SQLException {
		ArrayList<ArrayList> list  = new ArrayList<ArrayList>();
		String sql = "Select v.video_id video_id,v.title title,c.c_name c_name,c.tel_id tel_id,r.return_pre_date return_pre_date,r.return_YN return_YN from Cus_info c,Video_info v,Rent_info r where r.video_id = v.video_id and r.tel_id = c.tel_id and r.return_YN = 'N'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("video_id"));
			temp.add(rs.getString("title"));
			temp.add(rs.getString("c_name"));
			temp.add(rs.getString("tel_id"));
			temp.add(rs.getString("return_pre_date"));
			temp.add(rs.getString("return_YN"));
		
			list.add(temp);
		}
		
		return list;
	}


	public void Return(String text) throws NumberFormatException, SQLException {
		String sql = "Update Rent_info set return_YN = 'Y', return_date = sysdate  where video_id = ? and return_YN = 'N'";
	
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, Integer.parseInt(text));
		
		ps.executeUpdate();
		
		
	}




	
	
	
}
