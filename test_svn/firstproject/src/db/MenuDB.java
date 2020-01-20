package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import obejct.Menu;
import obejct.Staff;

public class MenuDB {
	DBCon db;
	Connection con;
	public MenuDB(){
		con = db.getInstance();
	}
	public void Insert(Menu menu) throws SQLException {
		String sql = "Insert into menu Values(seq_menu_midx.nextval,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,menu.getMcategory() );
		ps.setString(2,menu.getMmenu() );
		ps.setString(3,menu.getMphoto_url() );
		ps.setInt(4,menu.getMprice() );
		
		ps.executeUpdate();
		
		ps.close();
	}
	public void Delete(String text) throws SQLException {
		String sql = "Delete from menu where mmenu = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,text);
		
		ps.executeUpdate();
		
	}
	public ArrayList Show() throws SQLException {
		ArrayList list = new ArrayList();
		
		String sql = "Select mcategory, mmenu,mprice from menu";
		Statement st = con.createStatement();
		ResultSet rs =st.executeQuery(sql);
		while(rs.next()) {
			ArrayList temp = new ArrayList();
		
			temp.add(rs.getString("MCATEGORY"));
			temp.add(rs.getString("mmenu"));
			temp.add(rs.getString("mprice"));
			list.add(temp);
			
		}
		
		return list;
	}
	public ArrayList Search(String mname, String search) throws SQLException {
		ArrayList list =new ArrayList();
		String searchstr = "";
		if(search.equals("카테고리")) {
			searchstr = "mcategory";
		}else if(search.equals("메뉴명")) {
			searchstr = "mmenu";
		}
		String sql = "Select * from menu where "+searchstr+" = '"+mname+"'";
		System.out.println(sql);
		System.out.println(sql);
		Statement st = con.createStatement();
		ResultSet rs =st.executeQuery(sql);
	
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("mcategory"));
			temp.add(rs.getString("mmenu"));
			temp.add(Integer.parseInt(rs.getString("mprice")));
			list.add(temp);
		}
		return list;
	}
	
	
	public void Modify(Menu menu, String text) throws SQLException {
		
		
			
			String sql = "Update menu Set mcategory = ?,mphoto_url = ?,mprice = ? where mmenu = '"+text+"'";
			System.out.println(sql);
			PreparedStatement ps  = con.prepareStatement(sql);
			ps.setString(1, menu.getMcategory());
			ps.setString(2, menu.getMphoto_url());
			ps.setInt(3, menu.getMprice());
			
			ps.executeQuery();
		
			
			
			
		
	}
	public Menu Mouse(String mname) throws SQLException {
		Menu menu = new Menu();
		String sql = "Select * from menu where mmenu = '"+mname+"'";
		Statement st = con.createStatement();
		ResultSet rs =st.executeQuery(sql);
		if(rs.next()) {
			menu.setMcategory(rs.getString("mcategory"));
			menu.setMmenu(rs.getString("mmenu"));
			menu.setMphoto_url(rs.getString("mphoto_url"));
			menu.setMprice(Integer.parseInt(rs.getString("mprice")));
		}
		return menu;
	}
	
	
	
}
