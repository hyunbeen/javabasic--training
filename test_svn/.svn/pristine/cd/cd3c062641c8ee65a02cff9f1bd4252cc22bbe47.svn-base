package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.omg.PortableInterceptor.DISCARDING;

import db.LoginDB;
import obejct.Staff;
import view.GoOutView.ButtonEventHandler;

public class LoginView implements ActionListener {
	private Connection con;
	private LoginDB db;

	JFrame frame;
	
	JLabel		lbLogo;		// 로고 이미지 라벨

	JTextField	tfId;	// id
	JPasswordField tfPw;
	JButton		bLogin;		// 로그인 버튼

	public LoginView() {
		setUI();	// 
		display();	// 화면설계
		connectDB();
		evetproc();
	}

	public void connectDB() {	// 디비연결
		db = new LoginDB();
	}

	public void setUI() {
		frame	= new JFrame();
		
		// 로고 이미지 라벨
		lbLogo	= new JLabel(new ImageIcon("src/img/login.png"));

		tfId	= new JTextField();
		tfPw	= new JPasswordField();

		bLogin	= new JButton("LOGIN");
	}

	public void display() {
		db = new LoginDB();

		frame.setLayout(new BorderLayout());

		// 하단 왼쪽 영역
		JPanel pLbottom = new JPanel();
		pLbottom.setLayout(new GridLayout(2, 2));
		pLbottom.add(new JLabel("ID"));
		pLbottom.add(tfId);
		pLbottom.add(new JLabel("PW"));
		pLbottom.add(tfPw);
		pLbottom.setBackground(Color.WHITE);

		// 하단 붙이기
		JPanel pbottom = new JPanel();
		pbottom.setLayout(new GridLayout(1, 2));
		pbottom.add(pLbottom);
		pbottom.add(bLogin);
		bLogin.setBackground(new Color(154, 168, 237));
		pbottom.setBackground(Color.WHITE);


		// 전체 화면 붙이기
		frame.add(lbLogo, BorderLayout.CENTER);
		frame.add(pbottom, BorderLayout.SOUTH);
		
		frame.getContentPane().setBackground(Color.white);
		
		frame.pack();
		frame.setVisible(true);
	}

	void evetproc(){
	
		
		// 이벤트등록
		bLogin.addActionListener(this);
		tfPw.addActionListener(this);
		tfId.addActionListener(this);
		
	}
	
	

	public void login() {


		String id = tfId.getText();
		String pass = tfPw.getText();
		Staff admin = new Staff();
		try {
			
			admin  = db.login(id,pass);
			
		} catch (Exception	 e) {


		}
		
		if(admin.getEposition()==null) {
			JOptionPane.showMessageDialog(null, "로그인 실패");
			return;
		}
		
		try {
			db.gooff(id, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(!admin.getEposition().equals("")) {
		
			frame.setVisible(false);

			MainView mv = new MainView(admin);
		}






	}

	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();

		if(evt == bLogin) {
			login();
			clear();
		}else if(evt == tfId) {
			login();
			clear();
		}else if(evt == tfPw) {
			login();
			clear();
		}

	}
	
	public void clear() {
		tfId.setText("");
		tfPw.setText("");
	}


	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "룩앤필오류:"+ex.getMessage());
		}
		LoginView lv = new LoginView();

	}
}


