package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import obejct.Staff;

public class BurgerKing extends JFrame {
	JPanel panel;
	JPanel back;
	EmployeeView employee;
	MenuView menu;
	DecreaseView decrease;
	GoOutView goout;
	GraphView graph;
	JLabel badmin;// 관리자 메뉴 나가기
	Staff admin;
	Font bold;
	Font plain;

	public BurgerKing() {

	}

	public BurgerKing(Staff login) {
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		bold = new Font("인터파크고딕 L",Font.BOLD, 24);
		plain = new Font("인터파크고딕 L",Font.PLAIN,15);
		admin = login;
		// 각각의 화면을 관리하는 클래스 객체 생성
		employee = new EmployeeView();
		menu = new MenuView();
		decrease = new DecreaseView();
		goout = new GoOutView();
		graph = new GraphView();
		back = new JPanel();
		badmin = new JLabel(new ImageIcon("src/img/back.png"));
		back.setBackground(Color.WHITE);
		back.add(badmin,BorderLayout.NORTH);
		badmin.setBackground(Color.WHITE);
		
		
		// 각각 정의한 화면을 tab으로 붙인다
		JTabbedPane pane = new JTabbedPane();
		pane.setFont(bold);
		pane.addTab("직원관리", employee);
		pane.addTab("메뉴관리", menu);
		pane.addTab("할인관리", decrease);
		pane.addTab("출퇴근일지", goout);
		pane.addTab("매출관리", graph);
		pane.setSelectedIndex(0);
		//pane.setSize(400, 400);
		panel.setLayout(new BorderLayout());
		panel.add(pane,BorderLayout.CENTER);
		//panel.setSize(400,400);
		

		// 화면크기지정
		add("Center", panel);
		add("West", back);

		badmin.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				MainView mv = new MainView(admin);

			}// 관리자가 왼쪽위 화살표를 누르면 다시 결제하는 화면으로 옮겨진다
		});

		setSize(745, 625);
		setVisible(true);

	}

}
