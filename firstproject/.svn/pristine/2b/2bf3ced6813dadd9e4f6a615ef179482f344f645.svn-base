package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import db.LoginDB;
import obejct.Staff;

public class LoginView implements ActionListener {
	private Connection con;
	private LoginDB db;

	JFrame frame;

	JLabel lbLogo; // 로고 이미지 라벨
	JLabel lbID;
	JLabel lbPw;

	JTextField tfId; // id
	JPasswordField tfPw;
	JButton bLogin; // 로그인 버튼
	
	Font bold;
	Font plain;

	public LoginView() {

		setUI(); //
		display(); // 화면설계
		connectDB();
		evetproc();
	}

	public void connectDB() { // 디비연결
		db = new LoginDB();
	}

	public void setUI() {
		bold = new Font("인터파크고딕 L",Font.BOLD, 16);
		plain = new Font("인터파크고딕 L",Font.PLAIN,15);
		frame = new JFrame("로그인");

		// 로고 이미지 라벨
		lbLogo = new JLabel(new ImageIcon("src/img/burgers.png"));

		lbID = new JLabel("ID");
		lbID.setFont(bold);
		lbPw = new JLabel("PW");
		lbPw.setFont(bold);
		
		tfId = new JTextField();
		tfId.setFont(plain);
		tfPw = new JPasswordField();
		tfId.setFont(plain);

		bLogin = new JButton("LOGIN");
		bLogin.setFont(plain);
	}

	public void display() {
		db = new LoginDB();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLayout(new BorderLayout());

		// 하단 왼쪽 영역
		JPanel pLbottom = new JPanel();
		pLbottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pLbottom.setLayout(new GridLayout(2, 2));
		lbID.setHorizontalAlignment(SwingConstants.RIGHT);
		lbID.setBorder(BorderFactory.createEmptyBorder(0, 0,0,20));
		pLbottom.add(lbID);
		pLbottom.add(tfId);
		pLbottom.add(lbPw);
		lbPw.setHorizontalAlignment(SwingConstants.RIGHT);
		lbPw.setBorder(BorderFactory.createEmptyBorder(0, 0,0,20));
		pLbottom.add(tfPw);
		pLbottom.setBackground(Color.WHITE);

		// 하단 오른쪽 영역
		JPanel pRbottom = new JPanel();
		pRbottom.setLayout(new GridLayout(1, 2, 10 ,10));
		pRbottom.setBorder(BorderFactory.createEmptyBorder(0,50,0,0));
		pRbottom.add(bLogin);
		pRbottom.add(new JLabel());
		
		pRbottom.setBackground(Color.WHITE);
		
		// 하단 붙이기
		JPanel pbottom = new JPanel();
		pbottom.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
		pbottom.setLayout(new GridLayout(1, 2));
		pbottom.add(pLbottom);
		pbottom.add(pRbottom);
		
		bLogin.setBackground(new Color(154, 168, 237));
		bLogin.setSize(100, 100);
		pbottom.setBackground(Color.WHITE);

		// 전체 화면 붙이기
		frame.add(lbLogo, BorderLayout.CENTER);
		frame.add(pbottom, BorderLayout.SOUTH);

		frame.getContentPane().setBackground(Color.white);

		frame.pack();
		frame.setVisible(true);
	}

	void evetproc() {

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

			admin = db.login(id, pass);

		} catch (Exception e) {

		}

		if (admin.getEposition() == null) {
			JOptionPane.showMessageDialog(null, "로그인 실패");
			return;
		}

		try {
			db.gooff(id, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (!admin.getEposition().equals("")) {

			frame.setVisible(false);

			MainView mv = new MainView(admin);
		}

	}

	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();

		if (evt == bLogin) {
			login();
			clear();
		} else if (evt == tfId) {
			login();
			clear();
		} else if (evt == tfPw) {
			login();
			clear();
		}

	}

	public void clear() {
		tfId.setText("");
		tfPw.setText("");
	}

}
