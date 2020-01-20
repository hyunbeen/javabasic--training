package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;

import db.MainDB;
import obejct.Menu;
import obejct.Staff;

public class MainView implements ActionListener {
	Staff admin;
	MainDB db;
	Menu menu;
	
	int price;

	// 프레임 선언
	JFrame mainFrame;

	// 메인 페널 선언
	JPanel pMain;

	// 결제 버튼 패널
	JPanel pPay;

	// 메뉴 테이블
	JTable tSet;
	JTable tBurger;
	JTable tSide;
	JTable tDrink;

	// 주문 테이블
	JTable tOrder;

	// 주문 테이블 모델
	OrderTableModel tbModelOrder;

	// 주문한 목록에 대한 ArrayList
	ArrayList orderlist;
//	ArrayList main_tmp;

	// 메뉴 테이블 모델
	SetTableModel tbModelSet;
	BurgerTableModel tbModelBurger;
	SideTableModel tbModelSide;
	DrinkTableModel tbModelDrink;

	// 로고 라벨
	JLabel lLogo;

	// 담당 카운터 이미지 라벨
	JLabel lStaffImage;

	// 요구 사항 라벨
	JLabel lOetc;

	// 카운터 담당 라벨
	JLabel lStaff;

	// 총 금액 라벨
	JLabel lSum;

	// 버튼
	JButton bManager;
	JButton bOrder;
	JButton bUp;
	JButton bDown;
	JButton bPay;
	JButton bReceipt;
	JButton bOff;

	// 손님 요구사항 텍스트필드
	JTextField tfOetc;

	// 메뉴 탭 패널
	JTabbedPane tpMenu;

	// 관리자여부
	boolean check;

	// 로그인한 관리자
	String user;

	public MainView() {
		db = new MainDB();
		menu = new Menu();
//		orderlist = new ArrayList();
		setUI();
		initial();
		display();
		evtProc();
	}

	public MainView(Staff login) {
		admin = login;
		db = new MainDB();
		user = admin.getEid();
		menu = new Menu();
		
//		orderlist = new ArrayList();

		setUI();
		initial();
		if (admin.getEposition().equals("직원")) {
			check = false;
			bManager.setVisible(check);
		}
		display();
		lStaff.setText(admin.getEid());
		evtProc();
	}

	public void setUI() {
		// 프레임 선언
		mainFrame = new JFrame("BurgerKing P.O.S Main");

		// 메인 페널 선언
		pMain = new JPanel();

		// 결제 패널 선언
		pPay = new JPanel();

		// 메뉴 테이블
		tbModelSet = new SetTableModel();
		tSet = new JTable(tbModelSet);
		tbModelBurger = new BurgerTableModel();
		tBurger = new JTable(tbModelBurger);
		tbModelSide = new SideTableModel();
		tSide = new JTable(tbModelSide);
		tbModelDrink = new DrinkTableModel();
		tDrink = new JTable(tbModelDrink);

		// 주문 테이블
		tbModelOrder = new OrderTableModel();
		tOrder = new JTable(tbModelOrder);

		// 로고 라벨
		lLogo = new JLabel(new ImageIcon("firstproject/src/img/logo.jpg"));

		// 카운터 담당 이미지 라벨
		lStaffImage = new JLabel(new ImageIcon("firstproject/src/img/cashier.png"));

		// 요구 사항 라벨
		lOetc = new JLabel("요구 사항");

		// 카운터 담당 라벨
		lStaff = new JLabel("카운터 담당 : ");

		// 총 금액 라벨
		lSum = new JLabel("총 금액 : 0");

		// 버튼
		bManager = new JButton("관리자 메뉴");
		bOrder = new JButton("주문내역");
		bUp = new JButton("+");
		bDown = new JButton("-");
		bPay = new JButton("결제");
		bReceipt = new JButton("이전 영수증");
		bOff = new JButton("퇴근");

		// 손님 요구사항 텍스트필드
		tfOetc = new JTextField();

		// 메뉴 탭 패널
		tpMenu = new JTabbedPane(JTabbedPane.TOP);

		// 객체 생성 끝!!!

	}

	public void display() {
		// 메인 패널 속성
		pMain.setBackground(Color.WHITE);
		pMain.setLayout(null);

		// 로고 라벨 속성
		lLogo.setBounds(410, 15, 180, 180);
		pMain.add(lLogo);

		// 관리자 메뉴 버튼 속성
		bManager.setBounds(865, 15, 120, 45);
		bManager.setForeground(SystemColor.text);
		bManager.setBackground(new Color(52, 152, 219));
		bManager.setBorderPainted(false);
		pMain.add(bManager);

		// -------------------------------------
		// +버튼 속성
		bUp.setBackground(new Color(220, 220, 220));
		bUp.setFont(new Font("굴림", Font.BOLD, 32));
		bUp.setBounds(460, 237, 80, 80);
		bUp.setBorderPainted(false);
		pMain.add(bUp);

		// -버튼 속성
		bDown.setBackground(new Color(220, 220, 220));
		bDown.setFont(new Font("굴림", Font.BOLD, 32));
		bDown.setBounds(460, 371, 80, 80);
		bDown.setBorderPainted(false);
		pMain.add(bDown);

		// 결제버튼 속성
		pPay.setBounds(604, 130, 357, 352);
		pPay.setBackground(Color.WHITE);
		pMain.add(pPay);
		pPay.setLayout(new BorderLayout(0, 0));

		// 총액 버튼 속성-----------------------(수정 필요)
		lSum.setFont(new Font("굴림", Font.PLAIN, 24));
		lSum.setHorizontalAlignment(SwingConstants.RIGHT);

		pPay.add(lSum, BorderLayout.SOUTH);

		// 요구사항 텍스트 필드 속성
		tfOetc.setBounds(701, 494, 260, 33);
		pMain.add(tfOetc);
		tfOetc.setColumns(10);

		// 결제 버튼 속성
		bPay.setBackground(new Color(220, 220, 220));
		bPay.setBounds(865, 541, 120, 47);
		bPay.setBorderPainted(false);
		pMain.add(bPay);

		// 이전 영수증 버튼 속성
		bReceipt.setBackground(new Color(220, 220, 220));
		bReceipt.setBounds(721, 541, 130, 47);
		bReceipt.setBorderPainted(false);
		pMain.add(bReceipt);

		// 라벨을 메인 패널에 add
		lOetc.setBounds(625, 501, 62, 18);
		pMain.add(lOetc);

		// 퇴근 버튼 속성
		bOff.setBackground(new Color(220, 220, 220));
		bOff.setBounds(303, 59, 93, 33);
		bOff.setBorderPainted(false);
		pMain.add(bOff);

		// 라벨을 메인 패널에 add
		lStaffImage.setBounds(30, 27, 68, 66);
		pMain.add(lStaffImage);

		// 라벨을 메인 패널에 add
		lStaff.setBounds(99, 59, 190, 18);
		pMain.add(lStaff);

		// 주문내역 버튼 속성
		bOrder.setBounds(731, 15, 120, 45);
		bOrder.setForeground(Color.WHITE);
		bOrder.setBackground(new Color(52, 152, 219));
		bOrder.setBorderPainted(false);
		pMain.add(bOrder);

		// 주문 테이블삽입을 위한 스크롤 패널
		pPay.add(new JScrollPane(tOrder), BorderLayout.CENTER);

		// 탭 패널 속성
		tpMenu.setFont(new Font("굴림", Font.PLAIN, 20));
		tpMenu.addTab("세트메뉴", new JScrollPane(tSet));
		tpMenu.addTab("버거", new JScrollPane(tBurger));
		tpMenu.addTab("사이드", new JScrollPane(tSide));
		tpMenu.addTab("드링크", new JScrollPane(tDrink));
		tpMenu.setSelectedIndex(0);
		tpMenu.setBounds(30, 105, 365, 420);
		pMain.add(tpMenu);

		// 메인 패널을 메인 프레임에 add
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(pMain);
		mainFrame.setSize(1015, 650);
		mainFrame.setVisible(true);
	}

	void initial() {
		orderlist = new ArrayList();
		menuSetOutput();
		menuBurgerOutput();
		menuSideOutput();
		menuDrinkOutput();

	}

	// 메뉴 출력 메소드 Start
	void menuSetOutput() {
		ArrayList list;
		try {
			list = db.selectByCategory("세트");
			tbModelSet.data = list;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "세트 테이블 출력실패" + e.getMessage());
		}
	}

	void menuBurgerOutput() {
		ArrayList list;
		try {
			list = db.selectByCategory("햄버거");
			tbModelBurger.data = list;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "버거 테이블 출력실패" + e.getMessage());
			e.printStackTrace();
		}
	}

	void menuSideOutput() {
		ArrayList list;
		try {
			list = db.selectByCategory("사이드메뉴");
			tbModelSide.data = list;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "사이드메뉴 테이블 출력실패" + e.getMessage());
			e.printStackTrace();
		}
	}

	void menuDrinkOutput() {
		ArrayList list;
		try {
			list = db.selectByCategory("드링크");
			tbModelDrink.data = list;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "드링크 테이블 출력실패" + e.getMessage());
			e.printStackTrace();
		}
	}
	// END 메뉴 출력 메소드

	// 주문 JTable에 메뉴 추가하는 메소드
	void addOrderByMenu() {
		
		boolean flag = false;
		int setMenu = tSet.getSelectedRow();
		int burgerMenu = tBurger.getSelectedRow();
		int sideMenu = tSide.getSelectedRow();
		int drinkMenu = tDrink.getSelectedRow();
		
		String selMenu;
		int selPrice;
		if(setMenu > -1) {
			selMenu = String.valueOf(tSet.getValueAt(setMenu, 0));
			selPrice =  (int) tSet.getValueAt(setMenu, 1);
		}else if(burgerMenu > -1) {
			selMenu = String.valueOf(tBurger.getValueAt(burgerMenu, 0));
			selPrice =  (int) tBurger.getValueAt(burgerMenu, 1);
		}else if(sideMenu > -1) {
			selMenu = String.valueOf(tSide.getValueAt(sideMenu, 0));
			selPrice =  (int) tSide.getValueAt(sideMenu, 1);
		}else{
			selMenu = String.valueOf(tDrink.getValueAt(drinkMenu, 0));
			selPrice =  (int) tDrink.getValueAt(drinkMenu, 1);
		}
		 

		for(int i =0; i < tbModelOrder.data.size() ; i++) {
			ArrayList rowTemp = ( ArrayList )  tbModelOrder.data.get(i);
				if (   ((String)rowTemp.get(0)).equals(selMenu ) ) {
					flag = true;
					int tmpPrice = (int) rowTemp.get(1);
					int tmpCnt = (int) rowTemp.get(2);
					rowTemp.set(1, tmpPrice + selPrice);
					rowTemp.set(2, tmpCnt + 1);
			}
		}
		if(  flag == false ) {
			ArrayList tmp = new ArrayList<>();
			tmp.add(selMenu);
			tmp.add(selPrice);
			tmp.add(1);
			tbModelOrder.data.add(tmp);
		}
		tOrder.setModel(tbModelOrder);
		tbModelOrder.fireTableDataChanged();
		
		int tmp_price = 0;
		for(int i = 0; i < tOrder.getRowCount();i++) {
			tmp_price += (int)tOrder.getValueAt(i, 1);
		}
		lSum.setText("총 금액 : "+Integer.toString(tmp_price));
	}

	// 세트 테이블 모델 클래스
	class SetTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "메뉴명", "가격" };

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.size();
		}

		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}
	}

	// 버거 테이블 모델 클래스
	class BurgerTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "메뉴명", "가격" };

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.size();
		}

		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}
	}

	// 사이드 테이블 모델 클래스
	class SideTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "메뉴명", "가격" };

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.size();
		}

		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}
	}

	// 드링크 테이블 모델 클래스
	class DrinkTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "메뉴명", "가격" };

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.size();
		}

		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}
	}

	// 주문 테이블 모델 클래스
	class OrderTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "메뉴명", "가격", "개수" };

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.size();
		}

		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}
	}

	// 이벤트 프로시져
	public void evtProc() {
		bManager.addActionListener(this);
		bOrder.addActionListener(this);
		bPay.addActionListener(this);
		bOff.addActionListener(this);
		bUp.addActionListener(this);
		tSet.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tBurger.clearSelection();
				tSide.clearSelection();
				tDrink.clearSelection();
			}
		});
		tBurger.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tSet.clearSelection();
				tSide.clearSelection();
				tDrink.clearSelection();
			}
		});
		tSide.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tBurger.clearSelection();
				tSet.clearSelection();
				tDrink.clearSelection();
			}
		});
		tDrink.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tBurger.clearSelection();
				tSide.clearSelection();
				tSet.clearSelection();
			}
		});
		
	}

	public void actionPerformed(ActionEvent e) {
		Object evt = (Object) e.getSource();
		if (evt == bManager) {
			BurgerKing bk = new BurgerKing(admin);
		} else if (evt == bOrder) {
			OrderView ov = new OrderView(admin);
		} else if (evt == bPay) {
			PayView pv = new PayView(admin,tOrder,lSum.getText());
		} else if (evt == bOff) {
			try {
				db.gooff(admin.getEid());
				System.exit(0);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if (evt == bUp) {
			addOrderByMenu();
		}
	}

	public static void main(String[] args) {
		MainView mv = new MainView();
	}

}
