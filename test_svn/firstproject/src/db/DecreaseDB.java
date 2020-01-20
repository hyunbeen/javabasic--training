package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import obejct.Decrease;

public class DecreaseDB {
	Decrease dec;
	Connection con;
	DBCon db;

	public DecreaseDB() {
		con = db.getInstance();
	}
	
	public void insertDc(Decrease dec) throws Exception{
		String sql = "INSERT INTO discount(didx, dname, dpersent) "
				+ "VALUES(seq_discount_didx.nextval, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dec.getDecName());
		ps.setInt(2, dec.getDecRate());
		ps.executeUpdate();
		
		ps.close();
	}
	public ArrayList selectByKeyword(String keyword) throws Exception{
		ArrayList list = new ArrayList();
		
		String sql = "SELECT * FROM discount "
				+ "WHERE dname like '%"+ keyword +"%'";
		
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("dname"));
			temp.add(rs.getInt("dpersent"));
			
			list.add(temp);
		}
		
		return list;
	}
	
	public ArrayList selectByAll() throws Exception{
		ArrayList list = new ArrayList();
		String [] colName = {"dname","dpersent"};
		
		String sql = "SELECT * FROM discount";
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("DNAME"));
			temp.add(rs.getInt("dpersent"));
			
			list.add(temp);
		}
		
		return list;
	}
	
	public void modifyDc(Decrease dec,String sDecName, int sDecRate) throws Exception{
		String sql = "UPDATE discount "
				+ "SET dName = ?, dpersent = ? WHERE dName = ? AND dpersent = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dec.getDecName());
		ps.setInt(2, dec.getDecRate());
		ps.setString(3, sDecName);
		ps.setInt(4, sDecRate);
		ps.executeUpdate();
		
		ps.close();
	}
	
	public void deleteDc(String sDecName, int sDecRate) throws Exception{
		String sql = "DELETE FROM discount "
				+ "WHERE dName = ? AND dpersent = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, sDecName);
		ps.setInt(2, sDecRate);
		ps.executeUpdate();
		
		ps.close();
	}

	
}
