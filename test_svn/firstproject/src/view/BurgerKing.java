package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import obejct.Staff;


public class BurgerKing extends JFrame 
{
	EmployeeView	employee;
	MenuView        menu;
	DecreaseView	decrease;
	GoOutView       goout;
	GraphView       graph;
	JButton 		badmin;//관리자 메뉴 나가기		
	Staff           admin;
	public BurgerKing(){
		
	}
	public BurgerKing(Staff login) {
		admin = login;
		//각각의 화면을 관리하는 클래스 객체 생성
				employee = new EmployeeView	();
				menu = new MenuView() ;
				decrease = new DecreaseView();
				goout = new GoOutView();
				graph = new GraphView();
				badmin = new JButton("나가기");
					
					JTabbedPane  pane = new JTabbedPane();
					pane.addTab("직원관리", employee);
					pane.addTab("메뉴관리", menu );
					pane.addTab("할인관리", decrease );
					pane.addTab("출퇴근일지",goout);
					pane.addTab("매출관리", graph );

					pane.setSelectedIndex(0);

					// 화면크기지정
					add("Center", pane );
					add("South",badmin);
					
					badmin.addActionListener(new ActionListener() {
						
					
						public void actionPerformed(ActionEvent e) {
							setVisible(false);
							MainView  mv = new MainView(admin);
							
						}
					});
					
					setSize(656,608);
					setVisible( true );
					
					
					
					
	}
	public static void main(String[] args) 
	{
			new BurgerKing();
	}
}
