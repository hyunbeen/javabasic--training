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

	public MenuDB() {
		con = db.getInstance();
	}

	// 메뉴삽입
	public void Insert(Menu menu) throws SQLException {
		String sql = "Insert into menu Values(seq_menu_midx.nextval,?,?,?,?,'Y')";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, menu.getMcategory());
		ps.setString(2, menu.getMmenu());
		ps.setString(3, menu.getMphoto_url());
		ps.setInt(4, menu.getMprice());

		ps.executeUpdate();

		ps.close();
	}
	
	//메뉴삭제
	public int Delete(String text) throws SQLException {
		String sql = "UPDATE menu SET mstate='N' where mmenu = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, text);

		int result = ps.executeUpdate();
		
		return result;
	}

	//전체 메뉴 보여주기
	public ArrayList Show() throws SQLException {
		ArrayList list = new ArrayList();

		String sql = "Select mcategory, mmenu,mprice, mstate from menu ORDER BY mstate DESC";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			ArrayList temp = new ArrayList();

			temp.add(rs.getString("MCATEGORY"));
			temp.add(rs.getString("mmenu"));
			temp.add(rs.getString("mprice"));
			temp.add(rs.getString("MSTATE"));
			list.add(temp);

		}

		return list;
	}

	//메뉴 이름을 통한 해당 메뉴 검색
	public ArrayList Search(String mname, String search) throws SQLException {
		ArrayList list = new ArrayList();
		String searchstr = "";
		if (search.equals("카테고리")) {
			searchstr = "mcategory";
		} else if (search.equals("메뉴명")) {
			searchstr = "mmenu";
		}
		String sql = "Select * from menu where " + searchstr + " = '" + mname + "' ORDER BY mstate DESC";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("mcategory"));
			temp.add(rs.getString("mmenu"));
			temp.add(Integer.parseInt(rs.getString("mprice")));
			temp.add(rs.getString("MSTATE"));
			list.add(temp);
		}
		return list;
	}

	// 메뉴수정
	public int Modify(Menu menu, String text) throws SQLException {
		String sql = "Update menu Set mstate='Y', mcategory = ?,mphoto_url = ?,mprice = ? where mmenu = '" + text + "'";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, menu.getMcategory());
		ps.setString(2, menu.getMphoto_url());
		ps.setInt(3, menu.getMprice());

		int result = ps.executeUpdate();
		
		return result;

	}

	// 테이블 메뉴 클릭시 보이기
	public Menu Mouse(String mname) throws SQLException {
		Menu menu = new Menu();
		String sql = "Select * from menu where mmenu = '" + mname + "'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if (rs.next()) {
			menu.setMcategory(rs.getString("mcategory"));
			menu.setMmenu(rs.getString("mmenu"));
			menu.setMphoto_url(rs.getString("mphoto_url"));
			menu.setMprice(Integer.parseInt(rs.getString("mprice")));
		}
		return menu;
	}

}
