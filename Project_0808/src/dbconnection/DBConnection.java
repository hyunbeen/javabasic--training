package dbconnection;

import java.sql.SQLException;

import javax.swing.JOptionPane;

class DBConnection {
	private static DBConnection db;
	private DBConnection()throws SQLException {
		System.out.println("��񿬰�");
	}
	public static DBConnection getInstance()throws SQLException {
		if(db==null) 
			db = new DBConnection();
			return db;
	
	}
}

class UserA
{
	
	public UserA() {
		try {
			DBConnection.getInstance();
			System.out.println("���Ἲ��");
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"��Ʈ��ũ�Ҿ���");
			e.printStackTrace();
		}
		
	}
}

class UserB
{

	public UserB() {
		try {
			DBConnection.getInstance();
		
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"��Ʈ��ũ�Ҿ���");
			e.printStackTrace();
		}
		
	}
}

class UserC
{
	
	public UserC() {
		try {
			DBConnection.getInstance();
			
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"��Ʈ��ũ�Ҿ���");
			e.printStackTrace();
		}
		
	}
}