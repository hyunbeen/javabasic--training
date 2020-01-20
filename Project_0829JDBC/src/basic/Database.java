package basic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class Database {
	String url = "";
	String user = "";
	String pass = "";
	
	Connection con = null;
	
	public Database()throws ClassNotFoundException {
		
		
		Statement st = null;
		//1.드라이버를 로딩
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//2.연결 객체 얻어오기
			String url = "jdbc:oracle:thin:@192.168.0.156:1521:orcl";
			String user = "scott";
			String pass = "tiger";
			try {
				con = DriverManager.getConnection(url, user, pass);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("연결성공");
		
	}
	
	public void insert(Record r) throws SQLException {
		String sql = "INSERT INTO info_tab VALUES(?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,r.getName());
		ps.setString(2,r.getTel());
		ps.setString(3,r.getJumin());
		ps.setInt(4,r.getAge());
		ps.setString(5,r.getHome());
		ps.setString(6,r.getGender());
		ps.executeUpdate();
		ps.close();
	}

	public Record selectByPk(String text) throws SQLException {
		Record r = null;
		String sql = "SELECT * FROM info_tab WHERE tel = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,text);
		ps.executeQuery();
		ResultSet r1 = ps.getResultSet();
		if(r1.next()) {
		r= new Record();
		r.setName(r1.getString("NAME"));
		r.setTel(r1.getString("tel"));
		r.setJumin(r1.getString("jumin"));
		r.setAge((int)r1.getLong("age"));
		r.setHome(r1.getString("home"));
		r.setGender(r1.getString("gender"));
		}
	
		
		return r;
	}
	
	public ArrayList<Record> selectAll() throws Exception{
		String sql = "SELECT * FROM info_tab";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		ArrayList<Record> list = new ArrayList();
		while(rs.next()) {
			Record r = new Record();
			r.setName(rs.getString("NAME"));
			r.setTel(rs.getString("tel"));
			r.setJumin(rs.getString("jumin"));
			r.setAge((int)rs.getLong("age"));
			r.setHome(rs.getString("home"));
			r.setGender(rs.getString("gender"));
			list.add(r);
		}
		return list;
	}

	public void modify(Record r1) throws SQLException {
		String sql = "Update info_tab set name = ? ,jumin = ?,age = ?,home = ?,gender = ? where tel = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,r1.getName());
		ps.setString(2,r1.getJumin());
		ps.setString(3,String.valueOf(r1.getAge()));
		ps.setString(4,r1.getHome());
		ps.setString(5,r1.getGender());
		ps.setString(6,r1.getTel());
		ps.executeUpdate();
		
	}

	public void delete(String text) throws SQLException {
		String sql = "Delete from info_tab where tel = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,text);
		ps.executeUpdate();
		
		
	}
}
